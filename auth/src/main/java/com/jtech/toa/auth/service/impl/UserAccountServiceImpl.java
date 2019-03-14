/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.auth.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.jtech.toa.auth.exception.PasswordErrorException;
import com.jtech.toa.auth.model.dto.AccountLoginDTO;
import com.jtech.toa.auth.service.IJwtTokenService;
import com.jtech.toa.auth.service.IUserAccountService;
import com.jtech.toa.core.CacheConst;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.oa.OAWS_Service;
import com.jtech.toa.user.service.IUserLangService;
import com.jtech.toa.user.service.IUserService;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class UserAccountServiceImpl implements IUserAccountService {

    private static final String TOKEN_UAT = "uat:";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
    private final IUserService userService;
    private final IJwtTokenService jwtTokenService;
    private final RedisTemplate<String, String> tokenCacheRedis;
    private final IUserLangService userLangService;

    @Value("${oa.connect-oa-crm-interface}")
    private boolean connectOaCrmInterface;

    @Autowired
    public UserAccountServiceImpl(IUserService userService,
                                  IJwtTokenService jwtTokenService,
                                  RedisTemplate<String, String> tokenCacheRedis, IUserLangService userLangService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.tokenCacheRedis = tokenCacheRedis;
        this.userLangService = userLangService;
    }


    @Override
    @CacheEvict(value = CacheConst.LOGIN_USER, key = "'user:'+#userId.toString()")
    public void logout(long userId) {
        // 判断缓存是否存在，如果存在，则清除
        boolean tokenExist = tokenCacheRedis.hasKey(TOKEN_UAT + userId);
        if (tokenExist) {
            LOGGER.info("User {} logout token has remove!", userId);
            tokenCacheRedis.delete(TOKEN_UAT + userId);
        } else {
            LOGGER.info("User {} token not exist!", userId);
        }
    }


    @Override
    public AccountLoginDTO login(String username, String password, String language,boolean rememberMe) {

        Optional<User> userOpt = userService.findByUsername(username);
        if (!userOpt.isPresent()) {
            String error = StringUtils.equals("zh", language) ? "该账号不存在" : "This account does not exist";
            throw new UnknownAccountException(error);
        }
        final User user = userOpt.get();


        Map<String, String> loginParam = Maps.newHashMap();

        loginParam.put("lid", user.getWorkNo());
        loginParam.put("pwd", password);
        LOGGER.debug("connectOaCrmInterface:"+connectOaCrmInterface);
        if(connectOaCrmInterface){
            OAWS_Service oaws_service = new OAWS_Service();
            boolean checkPassword = oaws_service.getOAWSPort()
                    .checkLogin(JSONObject.toJSONString(loginParam));
            if (!checkPassword) {
                throw new PasswordErrorException("密码错误");
            }
        }
        final int userId = user.getId();
        AccountLoginDTO loginDTO = new AccountLoginDTO();
        loginDTO.setUserId(userId);
        final String token;
        int expiresDays = 30;
        if (rememberMe) {
            expiresDays = 90; // 3个月
            token = jwtTokenService.createToken(user.getId(), expiresDays, language);
        } else {
            token = jwtTokenService.createToken(user.getId(), expiresDays, language);
        }
        // 写入缓存，如果存在，则会进行覆盖
        if(connectOaCrmInterface) {
            final ValueOperations<String, String> tokenValueOper = tokenCacheRedis.opsForValue();
            tokenValueOper.set(TOKEN_UAT + user.getId(), token);
        }
        loginDTO.setToken(token);
        loginDTO.setBasics(user);
        loginDTO.setId(user.getId());
        return loginDTO;
    }

    @Override
    public AccountLoginDTO autologin(int userId) {
        Optional<User> userOpt = userService.findByUserId(userId);
        if (!userOpt.isPresent()) {
            throw new UnknownAccountException("该账号不存在");
        }
        final User user = userOpt.get();
        AccountLoginDTO loginDTO = new AccountLoginDTO();
        loginDTO.setUserId(user.getId());
        final String token;
        int expiresDays = 30;
        token = jwtTokenService.createToken(userId, expiresDays);
        // 写入缓存，如果存在，则会进行覆盖
        final ValueOperations<String, String> tokenValueOper = tokenCacheRedis.opsForValue();
        tokenValueOper.set(TOKEN_UAT + userId, token);
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    @Cacheable(value = CacheConst.LOGIN_USER, key = "'user:'+ #userId.toString()")
    public User findByUserId(int userId) {
        final Optional<User> userOpt = this.userService.findByUserId(userId);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            return null;
        }
    }
}

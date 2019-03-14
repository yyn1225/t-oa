/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.controller.api;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.mapper.Condition;
import com.xiaoleilu.hutool.crypto.SecureUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.util.PasswordUtil;
import com.jtech.marble.web.cookie.Cookies;
import com.jtech.toa.auth.exception.PasswordErrorException;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.auth.model.dto.AccountLoginDTO;
import com.jtech.toa.auth.service.IJwtTokenService;
import com.jtech.toa.auth.service.IUserAccountService;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IUserService;

import static com.jtech.toa.auth.constants.WebConst.NORMAL_EXPIRES;
import static com.jtech.toa.auth.constants.WebConst.REQUEST_CURRENT_KEY;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api")
public class SecurityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    private static final String QRCODE_SESSION = "qrcode:session:";
    /**
     * 二维码过期时间
     */
    private static final int QRCODE_EXPIRATION = 5 * 60 * 1000;
    private static final String USER_LOGIN_TAG = "OALOGIN";
    private final IUserAccountService userAccountService;
    private final IUserService userService;
    private final RedisTemplate<String, String> captchaRedis;
    private final IJwtTokenService jwtTokenService;
    private final RedisTemplate<String, String> redisCache;

    @Autowired
    public SecurityController(IUserAccountService userAccountService,
                              IUserService userService,
                              RedisTemplate<String, String> captchaRedis,
                              IJwtTokenService jwtTokenService, RedisTemplate<String, String> redisCache) {
        this.userAccountService = userAccountService;
        this.userService = userService;
        this.captchaRedis = captchaRedis;
        this.jwtTokenService = jwtTokenService;
        this.redisCache = redisCache;
    }

    /**
     * 登出操作
     */
    @GetMapping("logout")
    public ResponseEntity logout(@RequestUser RequestSubject requestSubject) {
        userAccountService.logout(requestSubject.getId());
        Map<String,Object> map = Maps.newHashMap();
        final ArrayList<String> emptyTables = Lists.newArrayList(
                AppConstant.Customer,
                AppConstant.Dict,
                AppConstant.Rate,
                AppConstant.Product,
                AppConstant.ProductPrice,
                AppConstant.SparePrice,
                AppConstant.Spare,
                AppConstant.SpareProduct,
                AppConstant.Series,
                AppConstant.Area,
                AppConstant.FileSeries,
                AppConstant.FilesPackages,
                AppConstant.FileMarket,
                AppConstant.Files);

        map.put("code",200);
        map.put("data",true);
        map.put("emptyTable", emptyTables);
        return ResponseEntity.ok(map);
    }

    /**
     * 登录提交数据
     */
    @PostMapping("login")
    public ResponseEntity login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "language", required = false,defaultValue = "zh") String language) {
        if (StringUtils.isEmpty(username)) {
            ErrorModel errorModel = ErrorModel.builder().setCode(UserCode.PWD_IS_SPACE)
                    .setMessage("请输入用户名称！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        if (StringUtils.isEmpty(password)) {
            ErrorModel errorModel = ErrorModel.builder().setCode(UserCode.PWD_IS_SPACE)
                    .setMessage("请输入登录密码！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        password = SecureUtil.md5(password);
        final AccountLoginDTO loginDTO = userAccountService.login(username, password, language, false);

        return ResponseEntity.ok(loginDTO);
    }

    /**
     * 获取验证码接口
     */
    @GetMapping("/captcha")
    public ResponseEntity captcha(@RequestParam("phone") String phone) {
        User user = userService.selectOne(Condition.create().eq("phone", phone));
        if (null == user) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.USER_NOT_EXITS)
                    .setMessage("用户不存在！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        captchaRedis.opsForValue();
        // 写入缓存，如果存在，则会进行覆盖
        final ValueOperations<String, String> captchaValueOper = captchaRedis.opsForValue();
//        String capt = captchaValueOper.get(phone);
//        if (!Strings.isNullOrEmpty(capt)) {
//            return ResponseEntity.ok().build();
//        }
        String captcha = RandomStringUtils.randomNumeric(4);
        captchaValueOper.set(phone, captcha);
        captchaRedis.expire(phone, 15, TimeUnit.MINUTES);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pwd/rest")
    public ResponseEntity restPwd(@RequestParam("phone") String phone,
                                  @RequestParam("captcha") String captcha,
                                  @RequestParam("pwd") String pwd,
                                  @RequestParam("rpwd") String rpwd
    ) {
        final ValueOperations<String, String> opsForValue = captchaRedis.opsForValue();
        // 写入缓存，如果存在，则会进行覆盖
        final ValueOperations<String, String> captchaValueOper = opsForValue;
        String capt = captchaValueOper.get(phone);
        Optional<User> userOptional = userService.findByPhone(phone);
        if (!userOptional.isPresent()) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.USER_NOT_EXITS)
                    .setMessage("用户不存在！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        User user = userOptional.get();
        if (Strings.isNullOrEmpty(captcha)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.CAPTCHA_NOT_BLACK)
                    .setMessage("验证码不能为空！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        if (Strings.isNullOrEmpty(pwd) || Strings.isNullOrEmpty(rpwd)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.PWD_IS_SPACE)
                    .setMessage("密码不能为空！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        if (!StringUtils.equals(pwd, rpwd)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.REPWD_ERROR)
                    .setMessage("两次密码不正确！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        if (null == user) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.USER_NOT_EXITS)
                    .setMessage("用户不存在！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        if (!Strings.isNullOrEmpty(capt)) {
            if (!StringUtils.equals(capt, captcha)) {
                ErrorModel errorModel = ErrorModel.builder()
                        .setCode(UserCode.CAPTCHA_ERROR)
                        .setMessage("验证码不正确！").createErrorModel();
                return ResponseEntity.badRequest().body(errorModel);
            }
            byte[] bsalt = PasswordUtil.generateSalt();
            String salt = PasswordUtil.salt(bsalt);
            String dbPwd = PasswordUtil.password(bsalt, pwd);
            user.setSalt(salt);
            user.setPassword(dbPwd);
            if (userService.updateById(user)) {
                captchaRedis.delete(phone);
                ResponseEntity.ok().build();
            } else {
                ErrorModel errorModel = ErrorModel.builder()
                        .setCode(UserCode.REST_ERROR)
                        .setMessage("找回密码失败！").createErrorModel();
                return ResponseEntity.badRequest().body(errorModel);
            }
        } else {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.CAPTCHA_ERROR)
                    .setMessage("验证码错误或失效！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PasswordErrorException.class)
    @ResponseBody
    public ErrorModel passwordErrorException(PasswordErrorException e)
            throws Exception {
        LOGGER.error("The user password exception!", e);
        final String message = e.getMessage();
        return ErrorModel.builder().setCode(UserCode.PASSWORD_ERROR)
                .setMessage(message).createErrorModel();
    }

    @GetMapping("/qcode")
    public ResponseEntity createQcode() {
        final String qcode = RandomStringUtils.randomNumeric(20);
        redisCache.opsForValue().set(QRCODE_SESSION + qcode, USER_LOGIN_TAG, QRCODE_EXPIRATION,
                TimeUnit.MILLISECONDS);
        return ResponseEntity.ok(qcode);
    }

    @GetMapping("/islogin")
    public ResponseEntity autoLogin(@RequestParam("qcode") String qcode, HttpServletResponse
            response) {
        if (!Strings.isNullOrEmpty(qcode)) {
            qcode = qcode.replace("\"", "");
        }
        String redisStr = redisCache.opsForValue().get(QRCODE_SESSION + qcode);
        if (StringUtils.equals(redisStr, USER_LOGIN_TAG) || Strings.isNullOrEmpty(redisStr)) {
            return ResponseEntity.ok().build();
        } else {
            Optional<User> userOpt = userService.findByUserId(Integer.valueOf(redisStr));
            if (!userOpt.isPresent()) {
                ErrorModel errorModel = ErrorModel.builder()
                        .setCode(UserCode.USER_NOT_EXITS)
                        .setMessage("用户不存在！").createErrorModel();
                return ResponseEntity.badRequest().body(errorModel);
            }
            final User user = userOpt.get();
            final String token = jwtTokenService.createToken(user.getId(), 1);
            Cookies.addCookie(response, REQUEST_CURRENT_KEY, token, NORMAL_EXPIRES);
            redisCache.delete(QRCODE_SESSION + qcode);
            return ResponseEntity.ok("OK");
        }
    }
}

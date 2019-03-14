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

package com.jtech.toa.user.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.user.dao.UserLangMapper;
import com.jtech.toa.user.entity.UserLang;
import com.jtech.toa.user.model.dto.UserLangDto;
import com.jtech.toa.user.service.IUserLangService;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
@Service
public class UserLangServiceImpl extends ServiceImpl<UserLangMapper, UserLang> implements IUserLangService {

    @Override
    public List<UserLangDto> findByUserId(int userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateNameLangByUserId(int userId, String nameLang, String lang) {

        String exitsLang = baseMapper.selectInLang(lang, userId);
        boolean isOk = true;
        UserLang userLang;
        userLang = new UserLang();
        userLang.setNameLang(nameLang);
        userLang.setLanguage(lang);
        userLang.setUser(userId);
        if (!Strings.isNullOrEmpty(exitsLang)) {
            isOk = baseMapper.updateByUserIdAndLang(userLang) >= 0;
            if (!isOk) {
                throw new DaoException("更新用户语言信息失败");
            }
        } else {
            isOk = baseMapper.insertUserId(userLang) >= 0;
            if (!isOk) {
                throw new DaoException("插入用户语言信息失败");
            }
        }
        return isOk;
    }

    /**
     * 构建更新或插入对象
     *
     * @param jsonObject 语言的json对象
     * @param langs      语言的code信息
     * @param userId     用户的id
     * @return 用户语言集合
     */
    private List<UserLang> genderUserLangList(JSONObject jsonObject, List<String> langs, int userId) {
        if (CollectionUtils.isNotEmpty(langs)) {
            UserLang userLang;
            List<UserLang> userLangs = Lists.newArrayList();
            for (String lang : langs) {
                String val = jsonObject.getString(lang);
                if (!Strings.isNullOrEmpty(val)) {
                    userLang = new UserLang();
                    userLang.setLanguage(lang);
                    userLang.setUser(userId);
                    userLang.setNameLang(val);
                    userLangs.add(userLang);
                }
            }
            return userLangs;
        }
        return Collections.EMPTY_LIST;
    }
}

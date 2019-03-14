/*
 * Copyright (c)2012-2017 JingTong RDC(Research and Development Centre), Inc. All rights reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of JingTong RDC ,
 *         if any. The intellectual and technical concepts contained herein are proprietary
 *         to JingTong RDC and covered by China and Foreign Patents, patents in process,
 *         and are protected by trade secret or copyright law. Dissemination of this information
 *         or reproduction of this material is strictly forbidden unless prior written permission
 *         is obtained from JingTong RDC.
 */

package com.jtech.toa.controller.api;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.util.PasswordUtil;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserLangService;
import com.jtech.toa.user.service.IUserService;
import com.xiaoleilu.hutool.crypto.SecureUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final IUserService userService;
    private final IUserLangService userLangService;

    @Autowired
    public ProfileController(IUserService userService,
                             IUserLangService userLangService) {
        this.userService = userService;
        this.userLangService = userLangService;
    }

    /**
     * 更改用户密码
     *
     * @param user    当前登录用户
     * @param oldPsw  原密码
     * @param newPsw  新密码
     * @param surePsw 确定密码
     * @return 是否更改成功
     */
    @PostMapping("/password")
    public ResponseEntity resetPassword(
            @RequestUser RequestSubject user,
            @RequestParam(name = "oldPsw") String oldPsw,
            @RequestParam(name = "newPsw") String newPsw,
            @RequestParam(name = "surePsw") String surePsw
    ) {
        long userId = user.getId();
        if (Strings.isNullOrEmpty(newPsw) || Strings.isNullOrEmpty(oldPsw) || Strings
                .isNullOrEmpty(surePsw)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.PWD_IS_SPACE)
                    .setMessage("密码不能为空！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }

        if (!StringUtils.equals(newPsw, surePsw)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.REPWD_ERROR)
                    .setMessage("两次密码输入不一致！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }

        User userInfo = userService.selectById(userId);
        if (!PasswordUtil.checkPassword(StringUtils.upperCase(userInfo.getSalt()), userInfo
                .getPassword(), SecureUtil.md5(oldPsw))) {

            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.PASSWORD_ERROR)
                    .setMessage("原密码错误！").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        } else {
            byte[] bsalt = PasswordUtil.generateSalt();
            String salt = PasswordUtil.salt(bsalt);
            String dbPwd = PasswordUtil.password(bsalt, SecureUtil.md5(newPsw));
            userInfo.setSalt(salt);
            userInfo.setPassword(dbPwd);
            if (userService.updateById(userInfo)) {
                return ResponseEntity.ok(true);
            } else {
                ErrorModel errorModel = ErrorModel.builder()
                        .setCode(UserCode.REST_ERROR)
                        .setMessage("密码更改失败！").createErrorModel();
                return ResponseEntity.badRequest().body(errorModel);
            }
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateProfile( String language, String nameLang, String avatar,
                                         @RequestUser RequestSubject requestSubject) {
        final int userId = requestSubject.getId();
        User user = userService.selectById(userId);
        if (!Strings.isNullOrEmpty(avatar)) {
            user.setAvatar(avatar);
        }
        if (!Strings.isNullOrEmpty(nameLang)) {
            if (!userLangService.updateNameLangByUserId(userId, nameLang,language)) {
                ErrorModel errorModel = ErrorModel.builder().setCode(UserCode.REST_ERROR).createErrorModel();
                return ResponseEntity.badRequest().body(errorModel);
            }
        }
        if (!userService.updateById(user)) {
            ErrorModel errorModel = ErrorModel.builder().setCode(UserCode.REST_ERROR).createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        return ResponseEntity.ok(Collections.EMPTY_MAP);
    }

    @GetMapping("/info")
    public ResponseEntity updateProfile(@RequestUser RequestSubject requestSubject) {
        final int userId = requestSubject.getId();
        UserAppDto user = userService.findForAppByUserId(userId);
        Map<String, Object> result = Maps.newHashMap();
        result.put("basic", user);
        result.put("lang",  userLangService.findByUserId(userId));
        return ResponseEntity.ok(result);
    }
}

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

package com.jtech.toa.auth.service;

import com.jtech.toa.auth.model.dto.AccountLoginDTO;
import com.jtech.toa.user.entity.User;

/**
 * <p>用户身份服务 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public interface IUserAccountService {

    /**
     * 退出用户信息，某个用户退出
     *
     * @param userId 用户ID
     */
    void logout(long userId);

    /**
     * 登录处理逻辑
     *
     * @param username   登录帐号
     * @param password   登录密码
     * @param rememberMe 是否记住，如果记住的话，则生成过期3个月的token
     * @return 是否成功
     */
    AccountLoginDTO login(String username, String password, String language, boolean rememberMe);

    /**
     * 根据用户id自动登录
     *
     * @return 是否成功
     */
    AccountLoginDTO autologin(int userId);

    /**
     * 查找某个用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    User findByUserId(int userId);
}

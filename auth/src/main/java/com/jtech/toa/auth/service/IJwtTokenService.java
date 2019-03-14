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

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * <p>JWt TOKEN 服务 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public interface IJwtTokenService {


    /**
     * 生成token，该方法只在用户登录成功后调用
     *
     * @param userId      用户ID
     * @param expiresDays 过期天数
     * @return token字符串, 若失败则返回null
     */
    String createToken(long userId, int expiresDays);

    /**
     * 生成token，
     * token中带有用户的登录选择语言
     * @param userId      用户ID
     * @param expiresDays 过期天数
     * @Param language    登录语言
     * @return token字符串, 若失败则返回null
     */
    public String createToken(long userId, int expiresDays,String language);

    /**
     * 取得加密jwt包含的信息
     *
     * @param jwt jwt信息
     * @return 包含的具体信息
     */
    DecodedJWT verify(String jwt);
}

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

package com.jtech.toa.auth.constants;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public interface WebConst {

    String HTTP_HEADER_APP = "EStarApp";
    String HTTP_HEADER_LANGUAGE="ESLanguage";

    //记住我的过期时间
    int AUTO_EXPIRES = 10 * 24 * 60 * 60;
    //正常过期时间
    int NORMAL_EXPIRES = 24 * 60 * 60;

    /**
     * 存放登录用户模型Key的Request Key
     */
    String REQUEST_CURRENT_KEY = "TOA_REQUEST_CURRENT_KEY";

    String JWT_CLAIM_UID = "uid";
}

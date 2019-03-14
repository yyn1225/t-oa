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

package com.jtech.toa.auth.model;

import com.jtech.marble.shiro.ShiroUser;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
@Builder
public class RequestSubject implements Serializable{
    private static final long serialVersionUID = 5897378342770756144L;
    private int id;
    private String userName;
    private String phone;
    private String avatar;
    private int area;
    private String language;
    /**
     * 手机访问信息，如果为空 标识没有手机访问
     */
    private String appVersion;

    /**
     * 是否手机访问
     */
    private boolean app;


    public ShiroUser toUser(){
        return ShiroUser.builder()
                .id(id).name(userName).account(userName)
                .build();
    }
}

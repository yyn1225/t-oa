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

package com.jtech.toa.auth.model.dto;

import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;
import com.jtech.toa.user.entity.User;

import java.util.Map;

import lombok.Data;

/**
 * <p> 用户登录返回的DTO</p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AccountLoginDTO {
    /**
     * 用户ID
     */
    private long id;
    private long userId;
    private String token;
    private String basics;

    public void setBasics(User user){
        Map<String,Object> map = Maps.newHashMap();
        map = JSONObject.parseObject(JSONObject.toJSONString(user),map.getClass());
        map.remove("password");
        map.remove("salt");
        this.basics = JSONObject.toJSONString(map);
    }
}

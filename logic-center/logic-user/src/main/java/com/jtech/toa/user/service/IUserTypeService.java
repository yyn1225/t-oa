/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-$today.yea
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.user.service;

import com.baomidou.mybatisplus.service.IService;

import com.jtech.toa.user.entity.UserType;

import java.util.List;

/**
 * <p>
 * 用户类型表 服务类
 * </p>
 *
 * @author jtech tao.ding
 * @since 2017-07-10
 */
public interface IUserTypeService extends IService<UserType> {

    List<UserType> selectAll();
}

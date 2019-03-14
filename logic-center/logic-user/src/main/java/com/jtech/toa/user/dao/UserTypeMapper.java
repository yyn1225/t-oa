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

package com.jtech.toa.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.jtech.toa.user.entity.UserType;

import java.util.List;

/**
 * <p>
 * 用户类型表 Mapper 接口
 * </p>
 *
 * @author jtech tao.ding
 * @since 2017-07-10
 */
public interface UserTypeMapper extends BaseMapper<UserType> {

    /**
     * 下拉框查询所有用户类型
     * @return
     */
    List<UserType> selectAll();
}
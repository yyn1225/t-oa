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

package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.system.UserResource;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface UserResourceMapper extends BaseMapper<UserResource> {

    /**
     * 删除原来的用户资源数据
     * @param userId 用户的id
     * @return 删除的数量
     */
    int deleteByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户查询资源的id
     * @param userId 用户的id
     * @return 用户的资源集合
     */
    List<UserResource> selectByUserId(@Param("userId") Integer userId);


    List<UserResource> selectByResourceId(@Param("resource") Integer resource);
}
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
import com.jtech.toa.entity.system.RoleResource;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> Mapper 接口 </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {


    /**
     * 根据角色删除之前的资源关联数据
     *
     * @param roleId 角色的id
     * @return 删除的行数
     */
    int deleteByRoleId(@Param("roleId") Integer roleId);


    /**
     * 根据角色查询拥有的资源
     *
     * @param roleId 角色的id
     * @return 角色资源的集合
     */
    List<RoleResource> selectByRoleId(@Param("roleId") Integer roleId);
    List<RoleResource> selectByResourceId(@Param("resource") Integer resource);
}
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
import com.jtech.toa.entity.system.RoleUser;

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
public interface RoleUserMapper extends BaseMapper<RoleUser> {


    /**
     * 通过用户id查询用户角色
     * @param userId 用户的id
     * @return 角色用户的id
     */
    List<RoleUser> selectByUserId(Integer userId);

    /**
     * 根据用户删除用户角色数据
     * @param userId 用户的id
     * @return 删除的行数
     */
    int deleteByUser(Integer userId);

    /**
     * 根据角色id查询角色的select2下拉框的数据
     * @param roleId 角色id
     * @return select2dto的集合
     */
    List<Long> selectByRoleId(@Param("roleId") Integer roleId);

    /**
     * 更具角色id删除之前的数据
     * @param roleId 角色的id
     * @return 删除的行数
     */
    int deleteByRoleId(@Param("roleId") Integer roleId);

    List<RoleUser> findUserRoleByIdAndRole(@Param("userId")int userId, @Param("role")String role);

    /**
     * 根据用户删除用户角色数据  批量
     * @param ids 用户的id 集合
     * @return 删除的行数
     */
    int deleteByUserIds(@Param("ids") List<Integer> ids);
}
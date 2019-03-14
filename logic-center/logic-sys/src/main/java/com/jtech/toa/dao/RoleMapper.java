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
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.system.Role;
import com.jtech.toa.model.dto.files.FileSecurityRoleDto;
import com.jtech.toa.model.dto.sys.RoleDto;
import com.jtech.toa.model.query.RoleQuery;
import com.jtech.toa.user.model.dto.UserRoleDto;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> Mapper 接口 </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据分页信息获取角色数据
     *
     * @param query  分页及查询参数
     * @return 角色数据
     */
    List<RoleDto> selectRoleByPage(Page<RoleDto> requestPage, @Param("query") RoleQuery query);

    /**
     * 查询全部可用的角色
     *
     * @return 角色的集合
     */
    List<Role> selectAll();

    /**
     * 查询用户的角色
     *
     * @param userId 用户的id
     * @return 角色的对象集合
     */
    List<Role> selectByUserId(@Param("userId") int userId);

    /**
     * 查询所有角色及文件权限
     * @param fileId 文件id
     * @return 角色权限对象集合
     */
    List<FileSecurityRoleDto> selectRoleSecurity(Long fileId);

    /**
     * 查询所有用户角色
     * @param userId 用户id
     * @return 用户角色对象集合
     */
    List<UserRoleDto> selectUserRoleList(@Param("userId")int userId);

    /**
     * 查询所有有效角色
     * @return  角色列表
     */
    List<Role> selectRoleList();
}
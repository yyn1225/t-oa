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

package com.jtech.toa.service.system;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.system.Role;
import com.jtech.toa.model.dto.files.FileSecurityRoleDto;
import com.jtech.toa.model.dto.sys.RoleDto;
import com.jtech.toa.model.query.RoleQuery;
import com.jtech.toa.user.model.dto.UserRoleDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据分页信息获取角色数据
     * @param query 分页及查询参数
     */
    void selectRoleByPage(Page<RoleDto> requestPage, RoleQuery query);

    /**
     * 逻辑删除一条数据
     * @param id 主键
     * @return 布尔值
     */
    boolean deleteRole(int id);

    /**
     * 插入角色
     * @param role 角色实体类
     * @param langVal 语言json
     * @return 布尔值
     */
    boolean insertRole(Role role, String langVal);

    /**
     * 编辑角色
     * @param role 角色实体类
     * @param langVal 语言json
     * @return 布尔值
     */
    boolean updateRole(Role role, String langVal);

    /**
     * 查询全部可用的角色
     * @return 角色的集合
     */
    List<Role> findAll();

    /**
     * 查询用户的角色
     * @param userId 用户的id
     * @return 角色的对象集合
     */
    List<Role> findByUserId(int userId);

    /**
     * 查询所有角色及文件权限
     * @param fileId  文件id
     * @return 角色权限对象集合
     */
    List<FileSecurityRoleDto> selectRoleSecurity(Long fileId);

    /**
     * 查询所有用户角色
     * @param userId 用户id
     * @return 用户角色对象集合
     */
    List<UserRoleDto> selectUserRoleList(int userId);

    /**
     * 查询所有有效角色
     * @return  角色列表
     */
    List<Role> selectRoleList();
}

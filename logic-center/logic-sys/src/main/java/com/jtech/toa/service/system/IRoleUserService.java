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

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.system.RoleUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface IRoleUserService extends IService<RoleUser> {

    /**
     * 通过用户id查询用户角色
     * @param userId 用户的id
     * @return 角色用户的id
     */
    List<RoleUser> findByUserId(Integer userId);

    /**
     * 根据用户的角色id更新用户角色数据
     * @param roleIds 角色id
     * @param userId 用户的id
     * @return 是否成功
     */
    boolean updateResourceByRoleIdAndUserId(String roleIds, Integer userId);

    /**
     * 根据角色id查询角色的select2下拉框的数据
     * @param roleId 角色id
     * @return select2dto的集合
     */
    List<Long> findByRoleId(Integer roleId);

    /**
     * 更新角色用户的信息
     * @param roleId 角色的id
     * @param userIds 用户的id字符串
     * @return 是否成功了
     */
    boolean updateByRoleIdAndUserIds(Integer roleId, String userIds);

    /**
     * 判断 用户Id是否对应职责名称
     * @param userId
     * @param role
     * @return
     */
    boolean findUserRoleByIdAndRole(int userId, String role);

    boolean newSaveByRoleIdAndUserId(String roleIds, String userIds);
}

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
import com.jtech.toa.entity.system.RoleResource;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface IRoleResourceService extends IService<RoleResource> {

    /**
     * 更新角色资源信息
     * @param roleId 角色id
     * @param resourceIds 资源的ids
     * @return 是否成功
     */
    boolean updateResourceByResourceIdsAndRoleId(Integer roleId, String resourceIds);

    /**
     * 根据角色查询拥有的资源
     * @param roleId 角色的id
     * @return 角色资源的集合
     */
    List<RoleResource> findByRoleId(Integer roleId);
    List<RoleResource> selectByResourceId(Integer resource);

}

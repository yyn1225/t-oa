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
import com.jtech.toa.entity.system.UserResource;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface IUserResourceService extends IService<UserResource> {


    /**
     * 更新用户的资源
     * @param userId 用户的id
     * @param resourceIds 资源id字符串
     * @return 是否成功
     */
    boolean updateResourceByResourceIdsAndUserId(Integer userId, String resourceIds);

    /**
     * 查询用户的资源信息
     * @param roleId 用户的id
     * @return 资源的集合
     */
    List<UserResource> findByUserId(Integer roleId);

    List<UserResource> selectByResourceId(Integer resource);
}

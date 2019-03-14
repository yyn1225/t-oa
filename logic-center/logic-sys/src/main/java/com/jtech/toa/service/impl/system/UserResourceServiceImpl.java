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

package com.jtech.toa.service.impl.system;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.UserResourceMapper;
import com.jtech.toa.entity.system.UserResource;
import com.jtech.toa.service.system.IUserResourceService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
@Service
public class UserResourceServiceImpl extends ServiceImpl<UserResourceMapper, UserResource> implements IUserResourceService {

    @Override
    public boolean updateResourceByResourceIdsAndUserId(Integer userId, String resourceIds) {
        boolean isOk = true;
        isOk = baseMapper.deleteByUserId(userId) >= 0;
        if(!isOk){
            throw new DaoException("删除用户资源失败");
        }
        String[] rids = resourceIds.split(StringPool.COMMA);
        List<UserResource> roleResources = Lists.newArrayList();
        UserResource userResource;
        for (String rid : rids) {
            userResource = new UserResource();
            userResource.setResource(Ints.tryParse(rid));
            userResource.setUsers(userId);
            isOk = insert(userResource);
            if(!isOk){
                throw new DaoException("保存用户资源失败");
            }
            roleResources.add(userResource);
        }
        return isOk;
    }

    @Override
    public List<UserResource> findByUserId(Integer userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<UserResource> selectByResourceId(Integer resource) {
        return baseMapper.selectByResourceId(resource);
    }
}

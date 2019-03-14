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

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.StringPool;
import com.jtech.toa.dao.RoleResourceMapper;
import com.jtech.toa.entity.system.RoleResource;
import com.jtech.toa.service.system.IRoleResourceService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

    @Override
    public boolean updateResourceByResourceIdsAndRoleId(Integer roleId, String resourceIds) {
        boolean isOk = true;
        if (Strings.isNullOrEmpty(resourceIds) || roleId == 0) {
            return false;
        }
        isOk = baseMapper.deleteByRoleId(roleId) >= 0;
        String[] rids = resourceIds.split(StringPool.COMMA);
        List<RoleResource> roleResources = Lists.newArrayList();
        RoleResource roleResource;
        for (String rid : rids) {
            roleResource = new RoleResource();
            roleResource.setResource(Ints.tryParse(rid));
            roleResource.setRole(roleId);
            isOk = insert(roleResource);
            roleResources.add(roleResource);
        }
        return isOk;
    }

    @Override
    public List<RoleResource> findByRoleId(Integer roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public List<RoleResource> selectByResourceId(Integer resource) {
        return baseMapper.selectByResourceId(resource);
    }
}

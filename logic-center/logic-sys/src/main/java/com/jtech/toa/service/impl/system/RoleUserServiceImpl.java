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

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.RoleUserMapper;
import com.jtech.toa.entity.system.RoleUser;
import com.jtech.toa.service.system.IRoleUserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {



    @Override
    public List<RoleUser> findByUserId(Integer userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateResourceByRoleIdAndUserId(String roleIds, Integer userId) {
        boolean isOk = true;
        isOk = baseMapper.deleteByUser(userId) > 0;
        if (!Strings.isNullOrEmpty(roleIds) && userId > 0) {
//            List<RoleUser> roleUsers = Lists.newArrayList();
            RoleUser roleUser;
            String[] idsArray = roleIds.split(StringPool.COMMA);
            for (String s : idsArray) {
                roleUser = new RoleUser();
                roleUser.setRole(Ints.tryParse(s));
                roleUser.setUsers(userId);
                isOk = insert(roleUser);
                if (!isOk) {
                    throw new DaoException("保存失败");
                }
            }
        }
        return isOk;
    }

    @Override
    public List<Long> findByRoleId(Integer roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public boolean updateByRoleIdAndUserIds(Integer roleId, String userIds) {
        boolean isOk = true;
        if (roleId > 0 && !Strings.isNullOrEmpty(userIds)) {
            isOk = baseMapper.deleteByRoleId(roleId) >= 0;
            List<RoleUser> users = Lists.newArrayList();
            RoleUser roleUser;
            String[] ids = userIds.split(StringPool.COMMA);
            for (String id : ids) {
                roleUser = new RoleUser();
                roleUser.setUsers(Ints.tryParse(id));
                roleUser.setRole(roleId);
                isOk = insert(roleUser);
                users.add(roleUser);
            }
//            isOk = insertBatch(users);
            return isOk;
        }
        return false;
    }

    @Override
    public boolean findUserRoleByIdAndRole(int userId, String role) {
        List<RoleUser> users = this.baseMapper.findUserRoleByIdAndRole(userId, role);
        return users.size() > 0;
    }

    @Override
    public boolean newSaveByRoleIdAndUserId(String roleIds, String userIds) {
        boolean isOk = true;
        List<Integer> userList = JSON.parseArray(userIds, Integer.class);
        isOk = baseMapper.deleteByUserIds(userList) > 0;
        String[] idsArray = roleIds.split(StringPool.COMMA);
        RoleUser roleUser;
        for (Integer userId : userList) {
            for (String s : idsArray) {
                roleUser = new RoleUser();
                roleUser.setRole(Ints.tryParse(s));
                roleUser.setUsers(userId);
                isOk = insert(roleUser);
                if (!isOk) {
                    throw new DaoException("保存失败");
                }
            }
        }
        return isOk;
    }
}

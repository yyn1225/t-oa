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

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.dao.RoleMapper;
import com.jtech.toa.entity.system.Role;
import com.jtech.toa.model.dto.files.FileSecurityRoleDto;
import com.jtech.toa.model.dto.sys.RoleDto;
import com.jtech.toa.model.query.RoleQuery;
import com.jtech.toa.service.system.IRoleLangService;
import com.jtech.toa.service.system.IRoleService;

import com.jtech.toa.user.model.dto.UserRoleDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final IRoleLangService roleLangService;

    @Autowired
    public RoleServiceImpl(IRoleLangService roleLangService) {
        this.roleLangService = roleLangService;
    }

    @Override
    public void selectRoleByPage(Page<RoleDto> requestPage, RoleQuery query) {
        //状态为1，表示有效
        List<RoleDto> roleDtos = baseMapper.selectRoleByPage(requestPage, query);
        requestPage.setRecords(roleDtos);
    }

    @Override
    public boolean deleteRole(int id) {
        Role role = new Role();
        //0状态表示无效数据
        role.setStatus(0);
        role.setId(id);
        return baseMapper.updateById(role) == 1;
    }

    @Override
    public boolean insertRole(Role role, String langVal) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        role.setCreater((int) shiroUser.getId());
        role.setCreateTime(new Date());
        //默认插入状态为1-有效
        role.setStatus(1);
        //角色编码默认为1
        role.setRole("1");
        boolean isOk = insert(role);
        if (!isOk) {
            throw new DaoException("添加角色失败");
        }
        isOk = roleLangService.insertRoleLang(langVal, role.getId());
        return isOk;
    }

    @Override
    public boolean updateRole(Role role, String langVal) {
        boolean isOk = updateById(role);
        if (!isOk) {
            throw new DaoException("更新角色失败");
        }
        isOk = roleLangService.insertRoleLang(langVal, role.getId());
        return isOk;
    }

    @Override
    public List<Role> findAll() {
        return baseMapper.selectAll();
    }

    @Override
    public List<Role> findByUserId(int userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<FileSecurityRoleDto> selectRoleSecurity(Long fileId) {
        return baseMapper.selectRoleSecurity(fileId);
    }

    @Override
    public List<UserRoleDto> selectUserRoleList(int userId) {
        return baseMapper.selectUserRoleList(userId);
    }

    @Override
    public List<Role> selectRoleList() {
        return baseMapper.selectRoleList();
    }
}

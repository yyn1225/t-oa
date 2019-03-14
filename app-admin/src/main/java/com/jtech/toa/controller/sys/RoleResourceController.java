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

package com.jtech.toa.controller.sys;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.system.Role;
import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.service.system.IResourceService;
import com.jtech.toa.service.system.IRoleService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/role/resouce")
public class RoleResourceController {
    private final IResourceService resourceService;
    private final IRoleService roleService;

    @Autowired
    public RoleResourceController(IResourceService resourceService, IRoleService roleService) {
        this.resourceService = resourceService;
        this.roleService = roleService;
    }

    @GetMapping("/item")
    public String resouce(Integer roleId, Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        List<ResouceDto> resouceDtos = resourceService.genderForMenu(shiroUser.getDeptName(), 0);
        Role role;
        if (roleId > 0) {
            role = roleService.selectById(roleId);
            model.addAttribute("role", role);
        }
        model.addAttribute("resources", resouceDtos);
        return "sys/role_resource";
    }
}

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

import com.jtech.toa.entity.system.Role;
import com.jtech.toa.service.system.IRoleService;
import com.jtech.toa.service.system.IRoleUserService;
import com.jtech.toa.user.model.dto.UserSelect2Dto;
import com.jtech.toa.user.service.IUserService;

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
@RequestMapping("/role/user")
public class RoleUserController {
    private final IRoleService roleService;
    private final IRoleUserService roleUserService;
    private final IUserService userService;

    @Autowired
    public RoleUserController(IRoleService roleService, IRoleUserService roleUserService, IUserService userService) {
        this.roleService = roleService;
        this.roleUserService = roleUserService;
        this.userService = userService;
    }

    @GetMapping("/item")
    public String item(Integer roleId, Model model) {
        String lang = "zh";
        Role role = roleService.selectById(roleId);
        List<UserSelect2Dto> users = userService.findAllForSelect2(lang);
        model.addAttribute("role", role);
        model.addAttribute("users", users);
        return "sys/role_user";
    }
}

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

package com.jtech.toa.controller.file;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jtech.toa.entity.system.Role;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.system.IRoleService;
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
@RequestMapping("/file")
public class ManagerController {
    private final IRoleService roleService;

    @Autowired
    public ManagerController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/manager")
    public String list(Model model) {
        List<Role> roleList = roleService.selectRoleList();
        model.addAttribute("roles", roleList);
        return "/file/manager/index";
    }
}

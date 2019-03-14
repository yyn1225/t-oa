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

import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.service.system.IResourceService;
import com.jtech.toa.service.system.IRoleService;
import com.jtech.toa.service.system.IUserResourceService;
import com.jtech.toa.user.entity.User;
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
@RequestMapping("/user/resouce")
public class UserResourceController {
    private final IResourceService resourceService;
    private final IUserResourceService userResourceService;
    private final IUserService userService;

    @Autowired
    public UserResourceController(IResourceService resourceService, IRoleService roleService, IUserResourceService userResourceService, IUserService userService) {
        this.resourceService = resourceService;
        this.userResourceService = userResourceService;
        this.userService = userService;
    }

    @GetMapping("/item")
    public String resouce(Integer userId, Model model) {
        String lang = "zh";
        List<ResouceDto> resouceDtos = resourceService.genderForMenu(lang, 0);
        User user;
        if (userId > 0) {
            user = userService.selectById(userId);
            model.addAttribute("user", user);
        }
        model.addAttribute("resources", resouceDtos);
        return "sys/user_resource";
    }
}

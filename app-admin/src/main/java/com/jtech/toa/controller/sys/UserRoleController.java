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

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import com.jtech.toa.entity.system.Role;
import com.jtech.toa.service.system.IRoleService;
import com.jtech.toa.service.system.IRoleUserService;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/user/role")
public class UserRoleController {
    private final IRoleUserService roleUserService;
    private final IUserService userService;
    private final IRoleService roleService;

    @Autowired
    public UserRoleController(IRoleUserService roleUserService, IUserService userService, IRoleService roleService) {
        this.roleUserService = roleUserService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/item")
    public String item(Integer userId, Model model) {
        Map<String, Object> userMap = Maps.newHashMap();
        if (userId > 0) {
            Optional<User> userOptional = userService.findByUserId(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userMap.put("id", user.getId());
                userMap.put("name", user.getName());
                model.addAttribute("user", userMap);
                List<Role> roles = roleService.findAll();
                model.addAttribute("roles", roles);
            }
        }
        return "sys/user_role";
    }

    @GetMapping("/itemList")
    public String itemList(String userIds, Model model) {
        Map<String, Object> userMap = Maps.newHashMap();
        List<Integer> idList = JSON.parseArray(userIds, Integer.class);
        List<UserDto> userList = null ;
        if (idList.size() > 0) {
              userList = userService.selectUserListByIds(idList);
        }
        String[] strName =  new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            strName[i] = userList.get(i).getUsername();
        }
        model.addAttribute("userIds", userIds);
        model.addAttribute("strName",  StringUtils.join(strName, ","));
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "sys/user_list_role";
    }
}

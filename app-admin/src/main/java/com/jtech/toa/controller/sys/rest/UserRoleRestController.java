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

package com.jtech.toa.controller.sys.rest;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.jtech.toa.entity.system.RoleUser;
import com.jtech.toa.service.system.IRoleUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/user/role")
public class UserRoleRestController {
    private final IRoleUserService roleUserService;

    @Autowired
    public UserRoleRestController(IRoleUserService roleUserService) {
        this.roleUserService = roleUserService;
    }

    @PostMapping("/update")
    public ResponseEntity update(String roleIds, Integer userId) {
        if (!Strings.isNullOrEmpty(roleIds) && userId > 0) {
            boolean isOk =
                    roleUserService.updateResourceByRoleIdAndUserId(roleIds, userId);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checked")
    public ResponseEntity checked(Integer userId) {
        if (userId > 0) {
            List<RoleUser> roleUsers = roleUserService.findByUserId(userId);
            List<Integer> roleIds = Lists.newArrayList();
            for (RoleUser roleUser : roleUsers) {
                roleIds.add(roleUser.getRole());
            }
            return ResponseEntity.ok(roleIds);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/newSave")
    public ResponseEntity newSave(String roleIds, String userIds) {
        if (!Strings.isNullOrEmpty(roleIds) && !Strings.isNullOrEmpty(userIds)) {
            boolean isOk =
                    roleUserService.newSaveByRoleIdAndUserId(roleIds, userIds);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }
}

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
@RequestMapping("/api/role/user")
public class RoleUserRestController {
    private final IRoleUserService roleUserService;

    @Autowired
    public RoleUserRestController(IRoleUserService roleUserService) {
        this.roleUserService = roleUserService;
    }

    @PostMapping("/update")
    public ResponseEntity update(Integer roleId, String userIds) {
        if (roleId > 0 && !Strings.isNullOrEmpty(userIds)) {
            boolean isOk = roleUserService.updateByRoleIdAndUserIds(roleId, userIds);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checked")
    public ResponseEntity checked(Integer roleId) {
        if (roleId > 0) {
            List<Long> userIds = roleUserService.findByRoleId(roleId);
            return ResponseEntity.ok(userIds);
        }
        return ResponseEntity.noContent().build();
    }
}

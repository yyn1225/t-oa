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

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.toa.entity.system.RoleResource;
import com.jtech.toa.service.system.IRoleResourceService;

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
@RequestMapping("/api/role/resource")
public class RoleResourceRestController {
    private final IRoleResourceService roleResourceService;

    @Autowired
    public RoleResourceRestController(IRoleResourceService roleResourceService) {
        this.roleResourceService = roleResourceService;
    }

    @PostMapping("/update")
    public ResponseEntity update(Integer roleId, String resourceIds) {
        if (roleId > 0 && !Strings.isNullOrEmpty(resourceIds)) {
            boolean isOk =
                    roleResourceService.updateResourceByResourceIdsAndRoleId(roleId, resourceIds);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checked")
    public ResponseEntity getChecked(Integer roleId) {
        List<RoleResource> roleResources = roleResourceService.findByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(roleResources)) {
            return ResponseEntity.ok(roleResources);
        }
        return ResponseEntity.noContent().build();
    }
}
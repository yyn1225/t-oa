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

package com.jtech.toa.controller.org.rest;

import com.google.common.collect.Lists;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.jtech.toa.user.service.IDepartmentService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/organization/rest")
public class OrganizationRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationRestController.class);
    private final IDepartmentService departmentService;

    @Autowired
    public OrganizationRestController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 返回组织机构部门树
     *
     * @return 组织机构部门树
     */
    @GetMapping("/tree")
    public ResponseEntity organization(@RequestUser RequestSubject user,String q) {
        final List<ZTreeVO<TreeDataVO>> treeVOS = departmentService.findAllToTree(q, user.getLanguage());
        List<ZTreeVO<TreeDataVO>> parents = Lists.newArrayList();

        if (CollectionUtil.isEmpty(treeVOS)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("未找到部门").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        parents.addAll(treeVOS);
        return ResponseEntity.ok(parents);
    }

}

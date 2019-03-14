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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.model.dto.DepartmentDto;
import com.jtech.toa.user.model.query.DepartmentQuery;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.jtech.toa.user.service.IDepartmentService;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.util.CollectionUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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


    @PostMapping("/datagrid")
    public DataTablesOutput<DepartmentDto> datatables(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {
        String lang = "zh";//todo 应该修改为获取当前登录用户的语言环境
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();

        final Page<DepartmentDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        if (dataTablesInput.hasParasm()) {
            // 走查询方法
            DepartmentQuery organizationQuery = dataTablesInput
                    .getParams()
                    .toJavaObject(DepartmentQuery.class);

            LOGGER.debug("message {}", organizationQuery);
            departmentService.searchByPagination(requestPage, organizationQuery, lang);

        } else {

            departmentService.findByPagination(requestPage, orders, lang);
        }

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    /**
     * 返回组织机构部门树
     *
     * @return 组织机构部门树
     */
    @GetMapping("/tree")
    public ResponseEntity organization(String q) {
        String lang = "zh";//todo 应该修改为获取当前登录用户的语言环境
        final List<ZTreeVO<TreeDataVO>> treeVOS = departmentService.findAllToTree(q, lang);
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

    @PostMapping("save")
    public ResponseEntity save(Department department,
                               @RequestParam("langVal")
                                       String
                                       langVal) {
        final ShiroUser user = ShiroUtil.getUser();
        final long userId = user.getId();
        final int userIdInt = Integer.valueOf(userId + "");
        Optional<Department> checkObj = departmentService.findByCode(department.getCode());
        if (department.getId() != null && department.getId() > 0) {
            if (checkObj.isPresent() && !(department.getId().equals(checkObj.get().getId()))) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(UserCode.CODE_IS_EXIST)
                        .setMessage("编号已存在").createErrorModel());
            }
            department.setUpdater(userIdInt);
            department.setUpdateTime(DateTime.now().toSqlDate());
            departmentService.updateDepartment(department, langVal);
        } else {
            if (checkObj.isPresent()) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(UserCode.CODE_IS_EXIST)
                        .setMessage("编号已存在").createErrorModel());
            }
            department.setCreater(userIdInt);
            department.setCreateTime(DateTime.now().toSqlDate());
            departmentService.plusDepartment(department, langVal);
        }
        return ResponseEntity.ok(Collections.EMPTY_LIST);
    }

    @PostMapping("del")
    public ResponseEntity del(@RequestParam("id") int id) {
        if (id > 0) {
            List<Department> departments = departmentService.findByParentId(id);
            if (CollectionUtils.isNotEmpty(departments)) {
                ErrorModel errorModel = ErrorModel.builder()
                        .setCode(UserCode.EXIT_CHRILDREN_CAN_NOT_DEL)
                        .setMessage("存在子组织无法删除!")
                        .createErrorModel();
                return ResponseEntity.badRequest().body(errorModel);
            }
            boolean isOk = departmentService.deleteByDepartmentId(id);
            if (isOk) {
                return ResponseEntity.ok().build();
            } else {
                ErrorModel errorModel = ErrorModel.builder()
                        .setCode(UserCode.DELETE_ORGANIZATION_ERROR)
                        .setMessage("组织删除失败!")
                        .createErrorModel();
                return ResponseEntity.badRequest().body(errorModel);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/deparment/parents")
    public ResponseEntity getDepartByParent(int parent){
        if(0>=parent){
            return ResponseEntity.ok(new ArrayList<Department>());
        }
        List<Department> departments = departmentService.selectList(new EntityWrapper<Department>().eq("parent",parent));
        return ResponseEntity.ok(departments);
    }

}

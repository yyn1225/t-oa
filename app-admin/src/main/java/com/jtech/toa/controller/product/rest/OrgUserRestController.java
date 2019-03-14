package com.jtech.toa.controller.product.rest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.collect.Lists;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.system.RoleUser;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.model.query.UserQuery;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/user/rest")
public class OrgUserRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrgUserRestController.class);

    private final IUserService userService;
    private final IDepartmentService departmentService;

    @Autowired
    public OrgUserRestController(IUserService userService, IDepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    /**
     * 用户列表
     *
     * @return 用户用户列表
     */
    @PostMapping("/list")
    public DataTablesOutput<UserDto> userList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<UserDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        UserQuery query = dataTablesInput
                .getParams()
                .toJavaObject(UserQuery.class);

        LOGGER.debug("message {}", query);
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        List<Department> departmentList = departmentService.selectList(new EntityWrapper<Department>());
        User user = userService.selectById((int)shiroUser.getId());
        List<Integer> ids = Lists.newArrayList();
        List<Integer> areaIds = Lists.newArrayList();
        //这里的代码涉及到权限，通过角色放开资源，遍历所有部门领导，判断当前登录人是几级领导，一级不考虑，基于不跨区的原则，如果
        //部门领导是有二级的，则直接取二级的领导，如果只部门有三级的，则取三级的领导集合
        for (Department department : departmentList) {
            if (user.getId().equals(department.getLeader()) && user.getArea() != 0) {
                if (department.getLevel() == 1) {
                    ids.clear();
                    break;
                }else if (department.getLevel() == 2) {
                    areaIds.add(department.getId());
                }else if (department.getLevel() == 3) {
                    ids.add(department.getId());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(areaIds)) {
            query.setArea(areaIds);
        }else {
            if (CollectionUtils.isNotEmpty(ids)) {
                query.setAreaIds(JSON.toJSONString(ids));
            }
        }
        this.userService.selectUserListByPage(requestPage, query);
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
    @PostMapping("/delete")
    public ResponseEntity delete(int id){
        boolean ok = userService.deleteUser(id);
        if(!ok){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/enable")
    public ResponseEntity enable(int id){
        boolean ok = userService.enableUser(id);
        if(!ok){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    /**
     * 销售助理用户列表
     *
     * @return 销售助理用户列表
     */
    @PostMapping("/assistantList")
    public DataTablesOutput<UserDto> userAssistantList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<UserDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        UserQuery query = dataTablesInput
                .getParams()
                .toJavaObject(UserQuery.class);

        LOGGER.debug("message {}", query);
        this.userService.selectUserAssistantListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
}

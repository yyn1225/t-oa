package com.jtech.toa.controller.product.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.model.dto.sys.RoleDto;
import com.jtech.toa.model.query.RoleQuery;
import com.jtech.toa.service.system.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/role/rest")
public class OrgRoleRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrgRoleRestController.class);

    private final IRoleService roleService;

    @Autowired
    public OrgRoleRestController(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 角色列表
     *
     * @return 角色列表
     */
    @PostMapping("/list")
    public DataTablesOutput<RoleDto> userList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<RoleDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        RoleQuery query = dataTablesInput
                .getParams()
                .toJavaObject(RoleQuery.class);
        //状态默认为1
        query.setStatus(1);
        //语言默认为中文
        query.setLanguage("zh");
        final int status = 1;
        LOGGER.debug("message {}", query);
        this.roleService.selectRoleByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
}

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

package com.jtech.toa.controller.customer.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.base.Strings;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.model.dto.sys.CustomerDto;
import com.jtech.toa.model.query.CustomerQuery;
import com.jtech.toa.service.customer.ICustomerService;

import com.jtech.toa.user.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
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
@RequestMapping("/api/customer")
public class CustomerRestController {
    private final ICustomerService customerService;
    private final IDepartmentService departmentService;

    @Autowired
    public CustomerRestController(ICustomerService customerService,
                                  IDepartmentService departmentService
    ) {
        this.departmentService = departmentService;
        this.customerService = customerService;
    }

    @PostMapping("/datagrid")
    public DataTablesOutput<CustomerDto> datatables(
            @RequestUser RequestSubject user,
            @Valid @RequestBody DataTablesInput dataTablesInput,
            @RequestParam(value="type",required = false) String type
    ) {
        int userId = user.getId();
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final Page<CustomerDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        //获取一周前的时间
        Calendar week = Calendar.getInstance();
        week.setTime(new Date());
        week.add(Calendar.DATE, - 7);

        //获取一月前时间
        Calendar month = Calendar.getInstance();
        month.setTime(new Date());
        month.add(Calendar.MONTH, -1);

        //获取一年前的时间
        Calendar year = Calendar.getInstance();
        year.setTime(new Date());
        year.add(Calendar.YEAR, - 1);

        //获取三年前的时间
        Calendar threeYears = Calendar.getInstance();
        threeYears.setTime(new Date());
        threeYears.add(Calendar.YEAR, - 3);


        CustomerQuery customerQuery;
        if (dataTablesInput.hasParasm()) {
            // 走查询方法
            customerQuery = dataTablesInput .getParams().toJavaObject(CustomerQuery.class);
        } else {
            customerQuery = new CustomerQuery();
        }
        if (!Strings.isNullOrEmpty(type)) {
            if ("week".equals(type)) {
                customerQuery.setStartTime(week.getTime());
                customerQuery.setEndTime(new Date());
            }
            if ("month".equals(type)) {
                customerQuery.setStartTime(month.getTime());
                customerQuery.setEndTime(week.getTime());
            }
            if ("year".equals(type)) {
                customerQuery.setStartTime(year.getTime());
                customerQuery.setEndTime(month.getTime());
            }
            if ("threeYears".equals(type)) {
                customerQuery.setStartTime(threeYears.getTime());
                customerQuery.setEndTime(year.getTime());
            }
        }
        customerQuery.setUserId(userId);
        this.customerService.findCustomerListByPageForProtal(requestPage, customerQuery,userId, user.getArea());
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/save")
    public ResponseEntity save(Customer customer,
                               String shipAddress,
                               String billAddress,
                               @RequestUser RequestSubject requestSubject) {
        int userId = requestSubject.getId();
        if (customer.getId() != null && customer.getId() > 0) {
            customerService.updateInfo(customer, shipAddress, billAddress);
        } else {
            customerService.saveInfoForProtal(customer, shipAddress, billAddress, userId);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete")
    public ResponseEntity delete(Integer customerId) {
        if (customerId > 0) {
            boolean isOk = customerService.deleteByCustomer(customerId);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/autosuggest")
    public ResponseEntity autosugges(@RequestUser RequestSubject user,String name){
        CustomerQuery query = new CustomerQuery();
        query.setName(name);
        List<Customer> customerList = customerService.findMyAndAutosuggestCustomer(query,user.getId());
        List<Map<String,Object>> list = customerService.customers2Maps(customerList);
        return ResponseEntity.ok(list);
    }
}

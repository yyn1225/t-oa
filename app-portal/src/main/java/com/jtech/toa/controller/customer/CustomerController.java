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

package com.jtech.toa.controller.customer;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.entity.customer.SalesAddress;
import com.jtech.toa.model.dto.sys.CustomerDto;
import com.jtech.toa.model.query.CustomerQuery;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.customer.ISalesAddressService;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final ICustomerService customerService;
    private final IUserService userService;
    private final ISalesAddressService salesAddressService;
    private final IDictService dictService;
    String CUSTOMER_INDUSTRY = "customer_industry";//客户行业类型的字典类型
    String CUSTOMER_CARD_TYPE = "customer_card_type";//客户账号类型的字典类型

    @Autowired
    public CustomerController(ICustomerService customerService, IUserService userService, ISalesAddressService salesAddressService, IDictService dictService) {
        this.customerService = customerService;
        this.userService = userService;
        this.salesAddressService = salesAddressService;
        this.dictService = dictService;
    }

    @GetMapping("/list")
    public String list(@RequestUser RequestSubject user, Model model,@RequestParam(value = "type", required = false) String type) {

        UserAppDto userAppDto = userService.findForAppByUserId(user.getId());
        int area = userAppDto.getArea();
        List<CustomerDto> levels = customerService.selectCustomerLevel(area,user.getLanguage());
        model.addAttribute("levels", levels);
        model.addAttribute("type", type);
        return "customer/list";
    }

    @GetMapping("/item")
    public String item(Integer customerId, @RequestUser RequestSubject user, Model model) {

        Customer customer;
        UserAppDto userAppDto = userService.findForAppByUserId(user.getId());
        int area = userAppDto.getArea();
        List<CustomerDto> levels = customerService.selectCustomerLevel(area,user.getLanguage());
        model.addAttribute("levels", levels);
        List<Dict> industry = dictService.selectDictByCategory(CUSTOMER_INDUSTRY, user.getLanguage());
        model.addAttribute("industry", industry);
        List<Dict> accoutTypes = dictService.selectDictByCategory(CUSTOMER_CARD_TYPE, user.getLanguage());
        model.addAttribute("accoutTypes", accoutTypes);
        if (customerId == 0) {
            customer = new Customer();
        } else {
            customer = customerService.selectById(customerId);
        }
        model.addAttribute("customer", customer);
        Integer billingAddressId = customer.getBilling();
        Integer shippingAddressId = customer.getShipping();
        if (billingAddressId != null && billingAddressId != 0) {
            SalesAddress billAddress = salesAddressService.selectById(billingAddressId);
            model.addAttribute("billAddress", billAddress.getDetails());
        }
        if (shippingAddressId != null && shippingAddressId != 0) {
            SalesAddress billAddress = salesAddressService.selectById(shippingAddressId);
            model.addAttribute("shipAddress", billAddress.getDetails());
        }
        return "customer/item";
    }

    @GetMapping("/customer/list")
    public String list(@RequestUser RequestSubject user, Model model){
        CustomerQuery query = new CustomerQuery();

        List<Customer> customerList = customerService.findMyAndAutosuggestCustomer(query,user.getId());

        model.addAttribute("list", customerList);

        return "offer/customer";
    }

}

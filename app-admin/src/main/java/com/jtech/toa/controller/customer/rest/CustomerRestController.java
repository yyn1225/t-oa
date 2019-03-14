package com.jtech.toa.controller.customer.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.model.dto.sys.CustomerDto;
import com.jtech.toa.model.query.CustomerQuery;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.impl.product.BoxServiceImpl;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/customer/rest")
public class CustomerRestController {

    private final ICustomerService customerService;
    private final IUserService userService;

    @Autowired
    public CustomerRestController(ICustomerService customerService, IUserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxServiceImpl.class);

    /**
     * 箱体信息
     *
     * @return 客户信息
     */
    @PostMapping("/list")
    public DataTablesOutput<CustomerDto> customerList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        int area = userAppDto.getArea();
        final Page<CustomerDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        CustomerQuery query = dataTablesInput
                .getParams()
                .toJavaObject(CustomerQuery.class);
        query.setArea(area);
        LOGGER.debug("message {}", query);
        this.customerService.selectCustomerListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam("customer") String customer) {
        boolean result = customerService.deleteByCustomer(Integer.valueOf(customer));
        return result;
    }


    @PostMapping("/save")
    public ResponseEntity save(Customer customer,
                               String shipAddress,
                               String billAddress) {
        if (customer.getId() != null && customer.getId() > 0) {
            customerService.updateInfo(customer, shipAddress, billAddress);
        } else {
            customerService.saveInfoForAdmin(customer, shipAddress, billAddress);
        }
        return ResponseEntity.noContent().build();
    }


//    @GetMapping(value = "/customer/manage")
//    public String addOrEdit(@RequestParam("id") int id, Model model){
//        List<customer> customerList = customerService.selectcustomerList();
//        if (id == 0) {
//            customer customer = new customer();
//            model.addAttribute("id", id);
//            model.addAttribute("customer", customer);
//            model.addAttribute("customerList", customerList);
//            model.addAttribute("operate", "新增");
//        }else {
//            customer customer = customerService.selectById(id);
//            model.addAttribute("id", id);
//            model.addAttribute("customer", customer);
//            model.addAttribute("customerList", customerList);
//            model.addAttribute("operate", "编辑");
//        }
//        return "sys/customer/addOrEdit";
//    }
}

package com.jtech.toa.controller.customer.rest;


import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.customer.CustomerUser;
import com.jtech.toa.service.customer.ICustomerUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
@RestController
@RequestMapping("/customerUser/rest")
public class CustomerUserRestController {


    @Autowired
    ICustomerUserService customerUserService;

    @PostMapping("/user")
    public int getUserId(@RequestParam("customerId") int customerId){
        CustomerUser customerUser= customerUserService.getUserId(customerId);
        if(customerUser == null ){
            return 0;
        }
        return customerUser.getUsers();
    }

    @PostMapping("/assort")
    public boolean assort(@RequestParam("customerId") int customerId,
                       @RequestParam("user") int user){
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        CustomerUser customerUser = new CustomerUser();
        customerUser.setUsers(user);
        customerUser.setCustomer(customerId);
        customerUser.setAssigner((int) shiroUser.getId());
        customerUser.setAssignTime(new Date());
        CustomerUser cuser = customerUserService.getUserId(customerId);
        if(cuser != null){
            customerUserService.deleteCustomerUser(customerUser);
        }
        boolean result =  customerUserService.saveCustomerUser(customerUser);
        return result;
    }
}

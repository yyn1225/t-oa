package com.jtech.toa.controller.customer;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.entity.customer.SalesAddress;
import com.jtech.toa.model.dto.sys.CustomerDto;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.customer.ISalesAddressService;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class CustomerController {

    private final IUserService userService;
    private final ICustomerService customerService;
    private final IDictService dictService;
    private final ISalesAddressService salesAddressService;
    String CUSTOMER_INDUSTRY = "customer_industry";//客户行业类型的字典类型
    String CUSTOMER_CARD_TYPE = "customer_card_type";//客户账号类型的字典类型

    @Autowired
    public CustomerController(IUserService userService, ICustomerService customerService, IDictService dictService, ISalesAddressService salesAddressService){
        this.userService = userService;
        this.customerService = customerService;
        this.dictService = dictService;
        this.salesAddressService = salesAddressService;
    }

    @GetMapping(value = "/customer")
    public String importIndex(Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        int area= userAppDto.getArea();
        List<CustomerDto> levels = customerService.selectCustomerLevel(area,shiroUser.getDeptName());
        List<UserDto> users = userService.selectAllUser();
        model.addAttribute("area",area);
        model.addAttribute("users",users);
        model.addAttribute("levels",levels);
        return "/customer/list";
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

    @GetMapping("/customer/item")
    public String item(Integer customerId, Model model) {
        final Subject subject = SecurityUtils.getSubject();
        Long userId = ShiroUtil.getUser().getId();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        Customer customer;
        UserAppDto userAppDto = userService.findForAppByUserId(userId.intValue());
        int area = userAppDto.getArea();
        List<CustomerDto> levels = customerService.selectCustomerLevel(area,shiroUser.getDeptName());
        model.addAttribute("levels", levels);
        List<Dict> industry = dictService.selectDictByCategory(CUSTOMER_INDUSTRY,shiroUser.getDeptName());
        model.addAttribute("industry", industry);
        List<Dict> accoutTypes = dictService.selectDictByCategory(CUSTOMER_CARD_TYPE,shiroUser.getDeptName());
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
}

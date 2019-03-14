package com.jtech.toa.controller.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.system.ApprovalConfig;
import com.jtech.toa.entity.system.Role;
import com.jtech.toa.service.system.IApprovalConfigService;
import com.jtech.toa.service.system.IRoleService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.DepartmentApprovalDto;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/approval/config")
public class ApprovalConfigController {

    private final IUserService userService;
    private final IDepartmentService departmentService;
    private final IApprovalConfigService approvalConfigService;
    private final IRoleService roleService;

    @Autowired
    public ApprovalConfigController(IUserService userService,
                                    IRoleService roleService,
                                    IDepartmentService departmentService,
                                    IApprovalConfigService approvalConfigService) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.roleService = roleService;
        this.approvalConfigService = approvalConfigService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        User user = userService.selectById(shiroUser.getId());
        //查询所有等级为2的组织及对应的审批设置
        List<DepartmentApprovalDto> departmentList = departmentService.selectApprovalList();
        List<Role> roleList = roleService.selectRoleList();
        //用户所在地区
        model.addAttribute("area", user.getArea());
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("roleList", roleList);
        return "sys/approval/config_list";
    }
}

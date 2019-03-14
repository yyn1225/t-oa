/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.org;

import com.google.common.base.Optional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.system.Language;
import com.jtech.toa.service.system.ILanguageService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
public class OrgUserController {

    private final IUserService userService;
    private final IDepartmentService departmentService;
    private final ILanguageService languageService;

    @Autowired
    public OrgUserController(IUserService userService,
                             IDepartmentService departmentService,
                             ILanguageService languageService) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.languageService = languageService;
    }

    @GetMapping(value = "user/assistant")
    public String assistantList(){
        return "user/assistantList";
    }

    @GetMapping(value = "user")
    public String index(){
        return "user/list";
    }

    @GetMapping(value = "/user/manage")
    public String addOrEdit(@RequestParam("id") int id, Model model){
        List<Department> departments = departmentService.selectDepartmentList();
        List<Language> languages = languageService.selectLanguageList();

        model.addAttribute("assistantName",null);
        model.addAttribute("assistantId",null);

        if (id == 0) {
            User user = new User();
            model.addAttribute("id", user.getId());
            model.addAttribute("operate", "新增");
            model.addAttribute("user", user);
            model.addAttribute("languages", languages);
            model.addAttribute("departments", departments);
        }else {
            User user = userService.selectById(id);
            if(user.getAssistant() != null){
                Optional<User>  opUser= userService.findByUserId(user.getAssistant());
                model.addAttribute("assistantName",opUser.get().getName());
                model.addAttribute("assistantId",opUser.get().getId());
            }

            model.addAttribute("id", id);
            model.addAttribute("user", user);
            model.addAttribute("operate", "编辑");
            model.addAttribute("languages", languages);
            model.addAttribute("departments", departments);
            List<Department> departmentList = departmentService.selectDataByUser(id);
            List<Integer> ids = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(departmentList)) {
                for (Department department : departmentList) {
                    ids.add(department.getId());
                }
            }
            String idList = StringUtils.join(ids, ",");
            model.addAttribute("ids", idList);
        }
        return "user/addOrEdit";
    }

    /**
     * 用户新增
     *
     * @return 页面提示
     */
    @ResponseBody
    @PostMapping("/user/save")
    public ResponseEntity save(UserDto userDto, String deptIds) {
        if (Strings.isNullOrEmpty(deptIds)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage(String.valueOf("org.user.error.deptIdsIsNull")).createErrorModel());
        }
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        Optional<User> userByPhone = userService.findByUsername(userDto.getPhone()); //查询手机号用户
        Optional<User> userByLoginName = userService.findByUsername(userDto.getLoginName()); //查询手机号用户
        if (userDto.getId() <= 0) { //添加员工
            if (userByPhone.isPresent() || userByLoginName.isPresent()) { //用户名已存在
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorModel.builder().setMessage("创建用户失败，账号或手机号已存在").createErrorModel());
            }
            boolean ok = userService.addUser((int) shiroUser.getId(), userDto, deptIds);

            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorModel.builder().setMessage("创建用户失败").createErrorModel());
            }
        } else { //更新用户
            boolean phoneRepeat = userByPhone.isPresent() && !userDto.getId().equals(userByPhone.get().getId());
            boolean loginNameRepeat = userByLoginName.isPresent() && !userDto.getId().equals(userByLoginName.get().getId());
            //修改的用户名已存在
            if (phoneRepeat || loginNameRepeat) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorModel.builder().setMessage("更新用户失败，账号或手机号已存在").createErrorModel());
            }
            boolean ok = userService.updateUser((int) shiroUser.getId(), userDto, deptIds);

            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorModel.builder().setMessage("更新用户失败").createErrorModel());
            }
        }
        return ResponseEntity.ok(userDto);
    }


}

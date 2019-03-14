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

package com.jtech.toa.controller.org;

import com.alibaba.fastjson.JSONObject;
import com.jtech.toa.entity.system.Language;
import com.jtech.toa.service.system.ILanguageService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.DepartmentLang;
import com.jtech.toa.user.model.dto.DepartmentDto;
import com.jtech.toa.user.model.dto.UserSelect2Dto;
import com.jtech.toa.user.service.IDepartmentLangService;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

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
@RequestMapping("/organization")
public class OrganizationController {
    private final IDepartmentService departmentService;
    private final IUserService userService;
    private final ILanguageService languageService;
    private final IDepartmentLangService departmentLangService;

    @Autowired
    public OrganizationController(IDepartmentService departmentService, IUserService userService, ILanguageService languageService, IDepartmentLangService departmentLangService) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.languageService = languageService;
        this.departmentLangService = departmentLangService;
    }

    @GetMapping("list")
    public String index() {
        return "org/list";
    }

    @GetMapping("item")
    public String item(@RequestParam("id") Integer id, Model model) {
        String lang = "zh";//todo 需要获取当前登录人的语言code
        DepartmentDto department;
        if (id != 0) {
            department = departmentService.selectOneById(id);
        } else {
            department = new DepartmentDto();
        }
        List<Department> parents = departmentService.findAll(lang);
        List<UserSelect2Dto> users = userService.findAllForSelect2(lang);
        List<Language> languages = languageService.selectLanguageList();
        List<DepartmentLang> departmentLangs = departmentLangService.findByDepartmentId(id);
        JSONObject jsonObject = new JSONObject();
        for (DepartmentLang departmentLang : departmentLangs) {
            jsonObject.put(departmentLang.getLang(), departmentLang.getNameLang());
        }
        model.addAttribute("department", department);
        model.addAttribute("parents", parents);
        model.addAttribute("languages", languages);
        model.addAttribute("users", users);
        model.addAttribute("deptLang", departmentLangs);
        return "org/item";
    }
}

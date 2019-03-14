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

package com.jtech.toa.controller.file;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.file.FileMarketDetail;
import com.jtech.toa.entity.file.FileSecurityRole;
import com.jtech.toa.entity.file.FileSecurityUser;
import com.jtech.toa.model.dto.files.FileSecurityRoleDto;
import com.jtech.toa.model.dto.files.FolderNestableDto;
import com.jtech.toa.model.query.FileMarketDetailQuery;
import com.jtech.toa.service.file.IFileMarketDetailService;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.service.file.IFileSecurityRoleService;
import com.jtech.toa.service.file.IFileSecurityUserService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.system.IRoleService;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private final IFilePackageService filePackageService;
    private final IFileService fileService;
    private final IRoleService roleService;
    private final IFileSecurityRoleService fileSecurityRoleService;
    private final IFileSecurityUserService fileSecurityUserService;
    private final IFileMarketDetailService fileMarketDetailService;
    @Value("${oa.media-url}")
    private String mediaUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    public FileController(IFilePackageService filePackageService, IFileService fileService, IRoleService roleService,
                          IFileSecurityRoleService fileSecurityRoleService, IFileSecurityUserService fileSecurityUserService,
                          IFileMarketDetailService fileMarketDetailService ) {
        this.filePackageService = filePackageService;
        this.fileService = fileService;
        this.roleService = roleService;
        this.fileSecurityRoleService = fileSecurityRoleService;
        this.fileSecurityUserService = fileSecurityUserService;
        this.fileMarketDetailService = fileMarketDetailService;
    }


    @GetMapping("/index")
    public String file(Model model) {
        List<FolderNestableDto> folderNestableDtos = filePackageService.genderForNestable();
        if (CollectionUtils.isNotEmpty(folderNestableDtos)) {
            model.addAttribute("folders", folderNestableDtos);
        } else {
            model.addAttribute("folders", Collections.EMPTY_LIST);
        }
        return "file/index";
    }

    @GetMapping("item")
    public String item(int id, Model model) {
        File file;
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        FileMarketDetailQuery query = new FileMarketDetailQuery();
        query.setLang(shiroUser.getDeptName());
        List<FileMarketDetail> fileMarketDetails = fileMarketDetailService.selectSpareListAll(query);
        model.addAttribute("fileMarketDetails",fileMarketDetails);
        if (id > 0) {
            file = fileService.selectById(id);
            model.addAttribute("file", file);
            return "file/edit";
        } else {
            file = new File();
            model.addAttribute("file", file);
            return "file/item";
        }

    }

    @GetMapping("/edit")
    public String batchEdit(String ids, Model model) {
        List<Long> idList = JSON.parseArray(ids, Long.class);
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        FileMarketDetailQuery query = new FileMarketDetailQuery();
        query.setLang(shiroUser.getDeptName());
        List<FileMarketDetail> fileMarketDetails = fileMarketDetailService.selectSpareListAll(query);
        model.addAttribute("fileMarketDetails",fileMarketDetails);
        model.addAttribute("ids", idList.toString().replace("[","").replace("]","").trim().toString());
        return "file/manager/batch";
    }

    @GetMapping("security")
    public String security(Long id, Model model) {
        List<FileSecurityRoleDto> fileSecurityRoleDtoList = roleService.selectRoleSecurity(id);
        List<FileSecurityRole> fileSecurityRoleList = fileSecurityRoleService.securityRoleList(id.intValue());
        List<FileSecurityUser> fileSecurityUserList = fileSecurityUserService.selectSecurityUser(id.intValue());
        int security = 0;
        if (fileSecurityRoleList.size() > 0) {
            security = fileSecurityRoleList.get(0).getSecurity();
        }else {
            if (fileSecurityUserList.size() > 0) {
                security = fileSecurityUserList.get(0).getSecurity();
            }
        }
        model.addAttribute("security", security);
        model.addAttribute("roleList", fileSecurityRoleDtoList);
        model.addAttribute("fileId", id);
        return "file/security";
    }

    @GetMapping("view")
    public String view(int id, Model model) {
        File file = fileService.selectById(id);
        model.addAttribute("url", fileService.medialUrl(file.getUrl()));
        model.addAttribute("type", file.getExtend());
        if (file.getExtend().equals("png") || file.getExtend().equals("jpg") || file.getExtend().equals("gif") || file.getExtend().equals("jpeg")){
            return  "file/viewImg";
        }
        return "file/view";
    }
}

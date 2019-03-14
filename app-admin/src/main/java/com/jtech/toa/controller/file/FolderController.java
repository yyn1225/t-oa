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

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.toa.entity.file.FilePackage;
import com.jtech.toa.model.dto.files.FolderNestableDto;
import com.jtech.toa.service.file.IFilePackageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/folder")
public class FolderController {
    private final IFilePackageService filePackageService;

    @Autowired
    public FolderController(IFilePackageService filePackageService) {
        this.filePackageService = filePackageService;
    }

    @GetMapping("/index")
    public String file(Model model) {
        List<FolderNestableDto> folderNestableDtos = filePackageService.genderForNestable();
        if (CollectionUtils.isNotEmpty(folderNestableDtos)) {
            model.addAttribute("folders", folderNestableDtos);
        } else {
            model.addAttribute("folders", Collections.EMPTY_LIST);
        }
        return "folder/index";
    }

    @GetMapping("/item")
    public String item(int id, @RequestParam(required = false) int parentId, Model model) {
        if (id != 0) {
            FilePackage filePackage = filePackageService.selectById(id);
            model.addAttribute("filePackage", filePackage);
        } else {
            FilePackage filePackage = new FilePackage();
            filePackage.setParent(parentId);
            model.addAttribute("filePackage", filePackage);
        }
        return "/folder/item";
    }
}

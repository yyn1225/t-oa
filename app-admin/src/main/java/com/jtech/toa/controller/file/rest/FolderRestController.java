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

package com.jtech.toa.controller.file.rest;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.entity.file.FilePackage;
import com.jtech.toa.model.dto.files.FolderNestableDto;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.xiaoleilu.hutool.util.CollectionUtil;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/folder")
public class FolderRestController {
    private final IFilePackageService filePackageService;

    @Autowired
    public FolderRestController(IFilePackageService filePackageService) {
        this.filePackageService = filePackageService;
    }

    /**
     * 给nestable的数据接口
     */
    @GetMapping("list")
    public ResponseEntity list() {
        List<FolderNestableDto> folderNestableDtos = filePackageService.genderForNestable();
        if (CollectionUtils.isNotEmpty(folderNestableDtos)) {
            return ResponseEntity.ok(folderNestableDtos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/struct/update")
    public ResponseEntity update(String folderJson) {
        List<FolderNestableDto> folderNestableDtos = JSON.parseArray(folderJson, FolderNestableDto.class);
        boolean isOk = filePackageService.updateStruct(folderNestableDtos);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("save")
    public ResponseEntity updateOrSave(FilePackage filePackage) {
        final ShiroUser user = ShiroUtil.getUser();
        final Long userId = user.getId();//todo 需要修改成int类型
        boolean isOk;
        if (filePackage.getId() != null && filePackage.getId() > 0) {
            filePackage.setUpdater(userId.intValue());
            filePackage.setUpdateTime(DateTime.now().toDate());
            isOk = filePackageService.updateById(filePackage);
        } else {
            filePackage.setCreater(userId.intValue());
            filePackage.setCreateTime(DateTime.now().toDate());
            filePackage.setFullPath(filePackage.getName());
            isOk = filePackageService.insert(filePackage);
        }
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("delete")
    public ResponseEntity remove(Integer id) {
        if (id > 0) {
            boolean isOk = filePackageService.removeById(id);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("tree")
    public ResponseEntity tree(@RequestParam(required = false) String q) {
        final List<ZTreeVO<TreeDataVO>> treeVOS = filePackageService.findAllToTree(q);
        List<ZTreeVO<TreeDataVO>> parents = Lists.newArrayList();

        if (CollectionUtil.isEmpty(treeVOS)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("未找到文件夹").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        parents.addAll(treeVOS);
        return ResponseEntity.ok(parents);
    }
}

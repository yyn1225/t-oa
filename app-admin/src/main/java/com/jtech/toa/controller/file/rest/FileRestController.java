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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.file.FileSecurityRole;
import com.jtech.toa.entity.file.FileSecurityUser;
import com.jtech.toa.model.dto.files.FileDto;
import com.jtech.toa.model.query.AdminFileQuery;
import com.jtech.toa.service.file.*;

import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/file")
public class FileRestController {
    private final IFileService fileService;
    private final IFileSeriesService fileSeriesService;
    private final IFilePackageRelationService filePackageRelationService;
    private final IFileSecurityUserService fileSecurityUserService;
    private final IFileSecurityRoleService fileSecurityRoleService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileRestController.class);

    @Autowired
    public FileRestController(IFileService fileService, IFileSeriesService fileSeriesService,
                              IFilePackageRelationService filePackageRelationService,
                              IFileSecurityUserService fileSecurityUserService,
                              IFileSecurityRoleService fileSecurityRoleService) {
        this.fileService = fileService;
        this.fileSeriesService = fileSeriesService;
        this.filePackageRelationService = filePackageRelationService;
        this.fileSecurityUserService = fileSecurityUserService;
        this.fileSecurityRoleService = fileSecurityRoleService;
    }

    @PostMapping("/datagrid")
    public DataTablesOutput<FileDto> datagrid(@Valid @RequestBody DataTablesInput dataTablesInput) {
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        final Page<FileDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        if (dataTablesInput.hasParasm()) {
            // 走查询方法
            AdminFileQuery fileQuery = dataTablesInput
                    .getParams()
                    .toJavaObject(AdminFileQuery.class);
            fileQuery.setUserId(shiroUser.getId());

            LOGGER.debug("message {}", fileQuery);
            fileService.searchByPagination(requestPage, fileQuery);

        } else {
            AdminFileQuery fileQuery = new AdminFileQuery();
            fileQuery.setUserId(shiroUser.getId());
            fileService.searchByPagination(requestPage, fileQuery);
        }
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }


    @PostMapping("save")
    public ResponseEntity save(String ids, String packageIds, String seriesIds, Integer fileType,Integer securitys,Integer market) {
        if (!Strings.isNullOrEmpty(ids)) {
            boolean isOk = fileService.saveFile(ids, packageIds, seriesIds, fileType,securitys,market);
            if (isOk) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/video/save")
    public ResponseEntity videoSave(String packageIds, String seriesIds,Integer fileType, File file, String videoData) {
        List<File> fileList = JSON.parseArray(videoData, File.class);
        boolean isOk = fileService.saveVideoFile(packageIds, seriesIds, fileType, file, fileList);
        if (isOk) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
        }
    }

    @PostMapping("/video/update")
    public ResponseEntity videoUpdate(File file,String packageIds, String seriesIds,
                                 Integer fileType) {

        boolean isOk = fileService.updateVideo(file, packageIds, seriesIds, fileType);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
    }

    @PostMapping("update")
    public ResponseEntity update(Integer id, Integer attId, String packageIds, String seriesIds,
                                 Integer fileType,Integer securitys,Integer market) {
        if (id > 0) {
            boolean isOk = fileService.updateInfoByIdAndAttId(id, attId, packageIds, seriesIds, fileType,securitys,market);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();

    }

    @PostMapping("updateAll")
    public ResponseEntity updateAll(String ids, String packageIds, String seriesIds,
                                 Integer fileType,Integer securitys,Integer market) {

        if(ids.length()>0 && !"".equals(ids)){
            List<String> list = Arrays.asList(ids.split(","));
            boolean isOk = fileService.updateInfoByIds(list, packageIds, seriesIds, fileType,securitys,market);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("folder/update")
    public ResponseEntity folderUpdate(Integer id, Integer packageId, Integer oldPackageId) {
        if (id > 0 && packageId > 0) {
            boolean isOk = fileService.updatePackageById(id, packageId, oldPackageId);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("delete")
    public ResponseEntity del(Integer id) {
        if (id > 0) {
            boolean isOk = fileService.removeById(id);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("deleteAll")
    public ResponseEntity delAll(String ids) {
        List<Integer> idList = JSONArray.parseArray(ids, Integer.class);
        if (idList.size()>0){
            boolean isOk = fileService.removeByIds(idList);
            if (isOk) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/selected")
    public ResponseEntity treesSelected(Integer id) {
        List<Integer> seriesIds = fileSeriesService.findSeriesIdsByFileId(id);
        List<Integer> packageIds = filePackageRelationService.findPackageIdsByFileId(id);
        Map<String, Object> result = Maps.newHashMap();
        result.put("pids", packageIds);
        result.put("sids", seriesIds);
        return ResponseEntity.ok(result);
    }

    /**
     * 返回产品系列树
     *
     * @return 产品系列树
     */
    @GetMapping("/security/tree")
    public ResponseEntity series(String name) {
        final String lang = "zh";
//        query.setLang("zh");
//        query.setName(name);

        final List<ZTreeVO<TreeDataVO>> treeVOS = fileSecurityUserService.findAllToTree();

        List<ZTreeVO<TreeDataVO>> parents = Lists.newArrayList();

        if (CollectionUtil.isEmpty(treeVOS)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("未找到系列").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        parents.addAll(treeVOS);
        return ResponseEntity.ok(parents);
    }

    @GetMapping("/security/selected")
    public ResponseEntity securitySelected(Integer id) {
        List<FileSecurityUser> fileSecurityUserList = fileSecurityUserService.selectSecurityUser(id);
        List<Integer> securityIds = Lists.newArrayList();
        for (FileSecurityUser fileSecurityUser : fileSecurityUserList) {
            securityIds.add(fileSecurityUser.getUser());
        }
        Map<String, Object> result = Maps.newHashMap();
        result.put("sids", securityIds);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/security/save")
    public ResponseEntity securitySave(Long fileId, String fileSecurityRoleJson) {
        if (Strings.isNullOrEmpty(fileSecurityRoleJson)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("请选择授权角色").createErrorModel());
        }
        fileSecurityUserService.deleteByFile(fileId);
        fileSecurityRoleService.deleteByFile(fileId);

        List<FileSecurityRole> roleList = JSON.parseArray(fileSecurityRoleJson, FileSecurityRole.class);
        for (FileSecurityRole fileSecurityRole : roleList) {
            fileSecurityRole.setFile(fileId);
            fileSecurityRoleService.insertSecurity(fileSecurityRole);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/batch/save")
    public ResponseEntity batchSave(String ids, String fileSecurityRoleJson) {
        if (Strings.isNullOrEmpty(fileSecurityRoleJson)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("请选择授权角色").createErrorModel());
        }
        List<Long> idList = JSON.parseArray(ids, Long.class);
        fileSecurityRoleService.deleteByFileIds(idList);

        List<FileSecurityRole> roleList = JSON.parseArray(fileSecurityRoleJson, FileSecurityRole.class);
        for (FileSecurityRole fileSecurityRole : roleList) {
            for (Long id : idList) {
                fileSecurityRole.setFile(id);
                fileSecurityRoleService.insertSecurity(fileSecurityRole);
            }
        }
        return ResponseEntity.ok(true);
    }
}

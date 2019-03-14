package com.jtech.toa.controller.sys.rest;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Optional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.system.Resource;
import com.jtech.toa.entity.system.ResourceLang;
import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.model.query.ResourceQuery;
import com.jtech.toa.service.impl.system.ResourceServiceImpl;
import com.jtech.toa.service.system.IResourceLangService;
import com.jtech.toa.service.system.IResourceService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/resource/rest")
public class ResourceRestController {
    private final IResourceService resourceService;
    private final IResourceLangService resourceLangService;

    @Autowired
    public ResourceRestController (IResourceService resourceService, IResourceLangService resourceLangService){
        this.resourceService = resourceService;
        this.resourceLangService = resourceLangService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    /**
     * 菜单信息
     *
     * @return 菜单信息列表 
     */
    @PostMapping("/list")
    public DataTablesOutput<ResouceDto> ResourceList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        final Page<ResouceDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        ResourceQuery query = dataTablesInput
                .getParams()
                .toJavaObject(ResourceQuery.class);
        query.setLang(shiroUser.getDeptName());
        LOGGER.debug("message {}", query);
        this.resourceService.selectResourceListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
    /**
     * 菜单新增,编辑
     *
     * @return 页面提示
     */
    @PostMapping("/save")
    public ResponseEntity save(ResouceDto resourceDto, String lang) {
        List<ResourceLang> langLists= JSONArray.parseArray(lang, ResourceLang.class);
        for (ResourceLang langList : langLists) {
            if ("zh".equals(langList.getLanguage())) {
                resourceDto.setName(langList.getNameLang());
            }
        }
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        Optional<Resource> resourceByCode = resourceService.findByCode(resourceDto.getCode());

        if (resourceDto.getId() <= 0) {
            if(resourceByCode.isPresent()){
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = resourceService.addResource((int)shiroUser.getId(), resourceDto, langLists);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        } else {
            List<ResourceLang> resourceLangs = resourceLangService.selectByResource(resourceDto.getId());
            Map<String, ResourceLang> map = Maps.newHashMap();
            for (ResourceLang resourceLang : resourceLangs) {
                map.put(resourceLang.getLanguage(), resourceLang);
            }
            List<ResourceLang> updateList = Lists.newArrayList();
            List<ResourceLang> newList = Lists.newArrayList();

            for(ResourceLang lan:langLists) {
                if(map.containsKey(lan.getLanguage())) {
                    lan.setId(map.get(lan.getLanguage()).getId());
                    updateList.add(lan);
                }else{
                    newList.add(lan);
                }
            }
            if(resourceByCode.isPresent() && !(resourceDto.getId() == resourceByCode.get().getId())) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = resourceService.updateResource(resourceDto, updateList, newList);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        }
        return ResponseEntity.ok(resourceDto);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("resource")String resource){
        String result = resourceService.deleteByResourceId(Integer.valueOf(resource));
        if("false".equals(result)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
        }
        if("true".equals(result)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                .setCode(ProductCode.RESOURCE_REPEAT)
                .setMessage(String.valueOf(ProductCode.RESOURCE_REPEAT.getCode())).createErrorModel());

    }
}

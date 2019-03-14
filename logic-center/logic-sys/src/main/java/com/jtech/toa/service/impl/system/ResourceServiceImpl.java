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

package com.jtech.toa.service.impl.system;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.ResourceMapper;
import com.jtech.toa.entity.system.Resource;
import com.jtech.toa.entity.system.ResourceLang;
import com.jtech.toa.entity.system.RoleResource;
import com.jtech.toa.entity.system.UserResource;
import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.model.query.ResourceQuery;
import com.jtech.toa.service.impl.product.BoxServiceImpl;
import com.jtech.toa.service.system.IResourceLangService;
import com.jtech.toa.service.system.IResourceService;
import com.jtech.toa.service.system.IRoleResourceService;
import com.jtech.toa.service.system.IUserResourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    private final IRoleResourceService roleResourceService;
    private final IUserResourceService userResourceService;
    private final IResourceLangService resourceLangService;

    @Autowired
    public ResourceServiceImpl (IRoleResourceService roleResourceService, IUserResourceService userResourceService,
                                IResourceLangService resourceLangService){
        this.roleResourceService = roleResourceService;
        this.userResourceService = userResourceService;
        this.resourceLangService = resourceLangService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxServiceImpl.class);

    @Override
    public List<ResouceDto> genderForMenu(String lang, int userId) {
        List<Resource> resources = findByLangAndUserId(lang, userId);
        if (CollectionUtils.isEmpty(resources)) {
            return Collections.EMPTY_LIST;
        }
        List<ResouceDto> resouceDtos = Lists.newArrayList();
        ResouceDto folderNestableDto;
        for (Resource resource : resources) {
            folderNestableDto = new ResouceDto();
            folderNestableDto.setId(resource.getId());
            folderNestableDto.setName(resource.getName());
            folderNestableDto.setIcon(resource.getIcon());
            folderNestableDto.setUrl(resource.getUrl());
            folderNestableDto.setParent(resource.getParent());
            resouceDtos.add(folderNestableDto);
        }
        List<ResouceDto> result = buildByRecursive(resouceDtos);
        return result;
    }


    @Override
    @Transactional(rollbackFor = DaoException.class)
    public boolean addResource(int loginResourceId, ResouceDto resourceDto, List<ResourceLang> resourceLangs) {
        boolean isOk;
        Resource resource = resourceDto.toResouce();
        resource.setCreater(loginResourceId);
        resource.setCreateTime(new Date());
        resource.setType(1);
        isOk = insert(resource);
        for (ResourceLang resourceLang : resourceLangs) {
            resourceLang.setResource(resource.getId());
            isOk = resourceLangService.insert(resourceLang);
        }
        return isOk;
    }

    @Override
    @Transactional(rollbackFor = DaoException.class)
    public boolean updateResource(ResouceDto resourceDto, List<ResourceLang> updateList, List<ResourceLang> newList) {
        boolean isOk;
        Resource resource = resourceDto.toResouce();
        isOk = updateById(resource);
        if (updateList.size() > 0) {
            for (ResourceLang resourceLang : updateList) {
                resourceLang.setResource(resource.getId());
                isOk = resourceLangService.updateById(resourceLang);
            }
        }
        if (newList.size() > 0) {
            for (ResourceLang resourceLang : newList) {
                resourceLang.setResource(resource.getId());
                isOk = resourceLangService.insert(resourceLang);
            }
        }
        return isOk;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Resource> findByCode(String code) {
        if(Strings.isNullOrEmpty(code)){
            return Optional.absent();
    }
        Resource resource = baseMapper.selectByCode(code);
        if(resource == null){
            LOGGER.warn("菜单信息{} 查询菜单不存在",code);
            return Optional.absent();
        }
        return Optional.of(resource);
    }

    @Override
    public List<Resource> findByLangAndUserId(String code, int userId) {
        return baseMapper.selectByUserId(code, userId);
    }

    @Override
    public void selectResourceListByPage(Page<ResouceDto> requestPage, ResourceQuery query) {
        List<ResouceDto> resouceDtos = baseMapper.selectResourceListByPage(requestPage, query);
        requestPage.setRecords(resouceDtos);
    }

    @Override
    public List<Resource> selectResourceList(String lang) {
        return baseMapper.selectResourceList(lang);
    }

    @Override
    public List<Resource> selectResourceByParent(int resource) {
        return baseMapper.selectResourceByParent(resource);
    }

    @Override
    public String deleteByResourceId(int resource) {
        List<RoleResource> roleResources = roleResourceService.selectByResourceId(resource);
        List<UserResource> userResources = userResourceService.selectByResourceId(resource);
        List<Resource> resources = selectResourceByParent(resource);
        if (roleResources.size() > 0) {
            return "role";
        }
        if (userResources.size() > 0) {
            return "user";
        }
        if (resources.size() > 0) {
            return "parent";
        }
        boolean ok = baseMapper.deleteById(resource) == 1;
        if (!ok) {
            return "false";
        }
        return "true";
    }


    private List<ResouceDto> buildByRecursive(List<ResouceDto> resouceDtos) {
        List<ResouceDto> trees = Lists.newArrayList();
        for (ResouceDto resouceDto : resouceDtos) {
            if (0 == resouceDto.getParent()) {
                trees.add(findChildren(resouceDto, resouceDtos));
            }
        }
        return trees;
    }

    private ResouceDto findChildren(ResouceDto resouceDto, List<ResouceDto> resouceDtos) {
        for (ResouceDto it : resouceDtos) {
            if (resouceDto.getId() == it.getParent()) {
                if (resouceDto.getChildren() == null) {
                    resouceDto.setChildren(new ArrayList<ResouceDto>());
                }
                resouceDto.getChildren().add(findChildren(it, resouceDtos));
            }
        }
        return resouceDto;
    }

}

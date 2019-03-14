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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.ResourceLangMapper;
import com.jtech.toa.entity.system.ResourceLang;
import com.jtech.toa.service.system.IResourceLangService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
@Service
public class ResourceLangServiceImpl extends ServiceImpl<ResourceLangMapper, ResourceLang> implements IResourceLangService {

    @Override
    public List<ResourceLang> selectByResource(int resourceId) {
        return baseMapper.selectByResource(resourceId);
    }
}

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

package com.jtech.toa.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.system.ResourceLang;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface IResourceLangService extends IService<ResourceLang> {
    /**
     * 通过资源菜单id查询语言拓展列表
     * @param resourceId 资源菜单id
     * @return 资源菜单语言拓展列表
     */
    List<ResourceLang> selectByResource (int resourceId);
}

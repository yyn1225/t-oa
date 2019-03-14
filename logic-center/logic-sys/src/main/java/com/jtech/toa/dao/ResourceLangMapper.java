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

package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.system.ResourceLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface ResourceLangMapper extends BaseMapper<ResourceLang> {

    /**
     * 通过资源菜单id查询语言拓展列表
     * @param resourceId 资源菜单id
     * @return 资源菜单语言拓展列表
     */
    List<ResourceLang> selectByResource (@Param("resourceId") int resourceId);

    /**
     * 通过资源菜单id 删除语言拓展
     * @param resourceId 资源菜单id
     * @return 整形
     */
    int deleteByResourceId(@Param("resourceId") int resourceId);
}
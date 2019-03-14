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

package com.jtech.toa.user.dao;

import com.jtech.toa.user.entity.DepartmentLang;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
public interface DepartmentLangMapper extends BaseMapper<DepartmentLang> {

    /**
     * 根据组织的id删除语言的信息
     * @param deparmentId 组织的id
     * @return 影响的行数
     */
    int deleteByDeparmentId(@Param("departmentId") int deparmentId);

    /**
     * 更具组织查询它的语言信息
     * @param id 组织的id
     * @return 语言的集合
     */
    List<DepartmentLang> selectByDepartmentId(@Param("departmentId") Integer id);

}
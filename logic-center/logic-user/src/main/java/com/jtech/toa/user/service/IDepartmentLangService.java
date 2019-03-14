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

package com.jtech.toa.user.service;

import com.jtech.toa.user.entity.DepartmentLang;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p> 服务类 </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
public interface IDepartmentLangService extends IService<DepartmentLang> {
    /**
     * 删除掉之前的部门相关的语言数据
     * @param deparmentId 部门的id
     * @return 是否成功
     */
    boolean removeByDeparmentId(int deparmentId);


    /**
     * 插入组织的语言信息
     * @param langVal 语言信息的json
     * @param departmentId 部门的id
     * @return 是否成功
     */
    boolean insertDepartmentLang(String langVal,int departmentId);

    /**
     * 更具组织查询它的语言信息
     * @param id 组织的id
     * @return 语言的集合
     */
    List<DepartmentLang> findByDepartmentId(Integer id);
}

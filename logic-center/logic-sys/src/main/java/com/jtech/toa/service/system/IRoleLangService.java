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
import com.jtech.toa.entity.system.RoleLang;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface IRoleLangService extends IService<RoleLang> {

    /**
     *  根据角色id查询语言列表
     *  @param roleId 角色id
     *  @return 语言列表
     */
    List<RoleLang> selectByRoleId(int roleId);

    /**
     *  根据角色id刪除语言
     *  @param roleId 角色id
     *  @return 布尔值
     */
    boolean deleteByRoleId(int roleId);

    /**
     *  根据角色id刪除语言
     *  @param langVal 语言json
     *  @param roleId 角色id
     *  @return 布尔值
     */
    boolean insertRoleLang(String langVal, int roleId);
}

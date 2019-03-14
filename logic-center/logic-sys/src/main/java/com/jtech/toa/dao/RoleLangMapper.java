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
import com.jtech.toa.entity.system.RoleLang;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface RoleLangMapper extends BaseMapper<RoleLang> {

    /**
     *  根据角色id查询语言列表
     *  @param roleId 角色id
     *  @return 语言列表
     */
    List<RoleLang> selectByRoleId(@Param("roleId") int roleId);

    /**
     *  根据角色id刪除语言
     *  @param roleId 角色id
     *  @return 影响的行数
     */
    int deleteByRoleId(@Param("roleId") int roleId);
}
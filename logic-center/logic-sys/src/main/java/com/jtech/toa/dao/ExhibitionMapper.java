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
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.system.Exhibition;
import com.jtech.toa.model.dto.sys.ExhibitionAppDto;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
public interface ExhibitionMapper extends BaseMapper<Exhibition> {

    /**
     * 查询APP的轮播图
     * @return 轮播图的
     */
    List<ExhibitionAppDto> selectByType(@Param("type") int app);
    /**
     * 查询轮播图列表
     * @param requestPage 分页参数
     * @return 轮播图列表
     */
    List<Exhibition> selectFourList(Page<Exhibition> requestPage);
}
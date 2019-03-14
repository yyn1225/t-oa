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
import com.jtech.toa.entity.product.SeriesStandard;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.StandardQuery;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> Mapper 接口 </p>
 *
 * @author jtech
 * @since 2017-10-28
 */
public interface SeriesStandardMapper extends BaseMapper<SeriesStandard> {

    /**
     * 根据系列和备件的id查询关联信息
     *
     * @param seriesId 系列的id
     * @param spareId  备件的id
     * @return 备件关联的信息
     */
    SeriesStandard selectBySeriesIdAndSpareId(@Param("seriesId") Integer seriesId,
                                              @Param("spareId") Integer spareId);

    /**
     * 分页查询备件关联信息
     *
     * @param requestPage 分页对象
     * @param query       查询对象
     */
    List<StandardDto> selectStandardListByPage(Page<StandardDto> requestPage,
                                               @Param("query") StandardQuery query);
}
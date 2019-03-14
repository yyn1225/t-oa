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
import com.jtech.toa.entity.product.SeriesImages;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-28
 */
public interface SeriesImagesMapper extends BaseMapper<SeriesImages> {

    /**
     * 查询系列的展示图
     * @param seriesId 系列的id
     * @return 展示图的集合
     */
    List<SeriesImages> selectBySeriesId(@Param("seriesId") Integer seriesId);

    /**
     * 根据系列的id删除展示图片
     * @param seriesId 系列的id
     * @return 影响额行数
     */
    int deleteByImageId(@Param("image") Integer seriesId,@Param("updateTime") Date time);
}
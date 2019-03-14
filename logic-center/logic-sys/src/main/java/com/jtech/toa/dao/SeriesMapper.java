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

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.SeriesImages;
import com.jtech.toa.model.dto.products.SeriesAppDto;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.model.query.SeriesQuery;

/**
 * <p> 系列操作Dao </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
public interface SeriesMapper extends BaseMapper<Series> {
    /**
     * 查找所有的数据列表
     * @param line 产品线
     * @return 产品系列列表
     */
    List<Series> findSeriesList(@Param("line") Integer line);

    /**
     * 通过产品线和产品价格查询区域
     * @param line 产品线
     * @param area 区域
     * @return 产品系列列表
     */
    List<Series> findSeriesListByLineAndArea(@Param("line") Integer line, @Param("area") Integer area);

    /**
     * 通过产品线 查询区域
     * @param line 产品线
     * @return 产品系列列表
     */
    List<Series> findSeriesListByLineAll(@Param("line") Integer line);


    /**
     * 通过产品线查找对应的系列
     */
    List<Series> findSeriesListByLine(int line);

    /**
     * 根据类型查询系列
     *
     * @param type 类型的id
     */
    List<SeriesAppDto> selectByType(int type);

    /**
     * 通过id查找对应的系列
     */
    Series findSeriesById(int series);

    List<SeriesImages> findImagesBySeries(int series);

    /**
     * 查询所有系列
     *
     * @param query 系列查询对象
     * @return 系列集合
     */
    List<Series> findAllSeries(@Param("query") SeriesQuery query);

    /**
     * 查询这个名字的系列，包括他的子系列(只查询两层)
     *
     * @param names 系列的名字
     * @return 系列的id集合
     */
    List<Integer> selectAllByParentNames(@Param("names") String[] names);

    /**
     * 查询所有子系列的id
     *
     * @param ids 系列的名字
     * @return 系列的id集合
     */
    List<Integer> selectIdByParentIds(@Param("pids") List<Integer> ids);

    /**
     * 分页查询系列
     *
     * @param requestPage 分页对象
     * @param query       查询对象
     * @return 系列的对象集合
     */
    List<SeriesDto> selectSeriesListByPage(Page<SeriesDto> requestPage,
                                           @Param("query") SeriesQuery query);

    List<SeriesDto> findParentSeries();

    /**
     * 分页查询系列
     *
     * @param requestPage 分页对象
     * @param query       查询对象
     * @return 系列图片的对象集合
     */
    List<SeriesDto> selectSeriesImgListByPage(Page<SeriesDto> requestPage,
                                   @Param("query") SeriesQuery query);

    /**
     * 根据productId查询系列名称
     * @param productId 产品id
     * @return
     */
    List<Series> getByProductId(@Param("productId") Integer productId);
}



/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.product;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.model.dto.products.AppSeries;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.model.query.SeriesQuery;
import com.jtech.toa.user.model.vo.TreeDataVO;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
public interface ISeriesService extends IService<Series> {
    /**
     * 查找产品的系列列表
     * @return
     */
    public List<SeriesDto> findSeriesList();

    /**
     * 通过产品线和报价区域查找产品的系列列表（筛选价格）
     * @param line 产品线
     * @param area 报价区域
     * @return 系列列表
     */
    List<SeriesDto> findSeriesList(Integer line, Integer area);
    /**
     * 通过产品线查找产品的系列列表
     * @param line 产品线
     * @return 系列列表
     */
    List<SeriesDto> findSeriesList(Integer line );

    List<AppSeries> findApiSeriesList();


    /**
     * 查找某个产品线下的系列列表
     * @param line
     * @return
     */
    public List<Series> findSeriesList(int line);

    /**
     * 通过id查找对应的系列
     * @param series
     * @return
     */
    public Series findSeriesById(int series);

    /**
     * 产品系列树查询
     * @param query 系列查询对象
     * @return 树的集合
     */
    List<ZTreeVO<TreeDataVO>> findAllToTree(SeriesQuery query);

    /**
     * 产品系列查询
     * @param query 系列查询对象
     */
    List<Series> findAll(SeriesQuery query);

    /**
     * 查询这个名字的系列，包括他的子系列(只查询两层)
     * @param seriesName 系列的名字
     * @return 系列的id集合
     */
    List<Integer> findByParentName(String seriesName);

    /**
     * 查询系列的列表
     * @param requestPage 分页对象
     * @param query 查询对象
     */
    void findSeriesByPage(Page<SeriesDto> requestPage, SeriesQuery query);

    /**
     * 查找父级系列信息
     * @return
     */
    public List<SeriesDto> findParentSeriesList();

    /**
     * 分页查询系列
     *
     * @param requestPage 分页对象
     * @param query       查询对象
     */
    void selectSeriesImgListByPage(Page<SeriesDto> requestPage, SeriesQuery query);

    /**
     * 根据productId查询系列名称
     * @param productId 产品id
     * @return
     */
    List<Series> getByProductId(Integer productId);
}

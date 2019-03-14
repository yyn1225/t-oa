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

package com.jtech.toa.service.product;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.product.SeriesStandard;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.StandardQuery;

/**
 * <p> 服务类 </p>
 *
 * @author jtech
 * @since 2017-10-28
 */
public interface ISeriesStandardService extends IService<SeriesStandard> {

    /**
     * 分页查询备件关联信息
     * @param requestPage 分页对象
     * @param query 查询对象
     */
    void selectStandardListByPage(Page<StandardDto> requestPage, StandardQuery query);

    /**
     * 添加备件数量关联
     * @param standardIds 备件的ids
     * @param seriesId 系列的id
     * @param type 类型
     * @return 是否成功
     */
    boolean addStandard(String standardIds, Integer seriesId, Integer type);

    /**
     * 保存系列的保险配件数量
     * @param standardId 关联的id
     * @param yearsJsonStr 年的json对象字符串
     * @param auto 自动带出
     * @return 是否成功
     */
    boolean saveByStandard(Integer standardId, String yearsJsonStr, Integer auto);
}

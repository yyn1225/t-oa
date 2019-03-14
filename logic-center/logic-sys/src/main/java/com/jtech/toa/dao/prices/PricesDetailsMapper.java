/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.dao.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.PricesDetails;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.model.dto.prices.AreaPricesDetailDto;
import com.jtech.toa.model.dto.prices.PricesDetailDto;
import com.jtech.toa.model.dto.products.SparePriceDetailsDto;
import com.jtech.toa.model.query.AreaPricesDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-12-24
 */
public interface PricesDetailsMapper extends BaseMapper<PricesDetails> {
    /**
     * 通过屏体和区域查询一条数据
     * @param panel 屏体主键
     * @param area 区域
     * @return 一条数据
     */
    PricesDetails getByPanelAndArea(@Param("panel") int panel, @Param("area") int area, @Param("date") Date date);

    List<PricesDetailDto> getListBySystemsId(int systemId);

    /**
     * 查询单个区域产品价格明细列表
     * @param query 查询对象
     * @return 价格列表
     */
    List<AreaPricesDetailDto> findAreaPriceByPage(Page<AreaPricesDetailDto> requestPage, @Param("query") AreaPricesDetailQuery query);

    /**
     * 查询备件价格明细列表
     * @param systemId 查询
     * @return 价格列表
     */
    List<SparePriceDetailsDto> getListBySparePriceSystemsId(int systemId);
}
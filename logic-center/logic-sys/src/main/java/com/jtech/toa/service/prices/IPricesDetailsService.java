/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.PriceSystem;
import com.jtech.toa.entity.prices.PricesDetails;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.model.dto.prices.AreaPricesDetailDto;
import com.jtech.toa.model.dto.prices.PricesDetailDto;
import com.jtech.toa.model.dto.products.SparePriceDetailsDto;
import com.jtech.toa.model.query.AreaPricesDetailQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-12-24
 */
public interface IPricesDetailsService extends IService<PricesDetails> {

    /**
     * 保存价格
     * @param pricesDetailsList 价格明细集合
     * @param priceSystem 价格体系对象
     * @param userId 用户id
     * @param area 区域
     * @return 布尔
     */
    boolean savePrice(List<PricesDetails> pricesDetailsList, PriceSystem priceSystem, int userId, int area);

    /**
     * 保存价格
     * @param priceSystem 价格体系对象
     * @param details 价格明细json字符串
     * @return 布尔
     */
    boolean editPrice(PriceSystem priceSystem, String details) throws Exception;

    PricesDetails getDetailsByPanelAndArea(int panel,int area);

    /**
     * 通过体系id获取体系的屏体报价详情
     * @param systemId
     * @return
     */
    List<PricesDetailDto> getDetailList(int systemId);

    /**
     * 查询单个区域产品价格明细列表
     * @param query 查询对象
     */
    void findAreaPriceByPage(Page<AreaPricesDetailDto> requestPage, AreaPricesDetailQuery query);

    /**
     * 查询备件价格明细列表
     * @param systemId 查询对象
     */
    List<SparePriceDetailsDto> getListBySparePriceSystemsId(int systemId);
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.PriceSystem;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.prices.PricesDetails;
import com.jtech.toa.model.dto.prices.PriceSystemDto;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-12-24
 */
public interface IPriceSystemService extends IService<PriceSystem> {
    List<PriceSystemDto> findPageList(Page<PriceSystemDto> page, int area);

    List<PriceSystemDto> findPageListByUser(Page<PriceSystemDto> page, int user);

    PriceSystem getSystemByArea(int area,Date date);

    /**
     * 删除一个价格体系
     * @param id 主键
     * @param pricesDetailsList 价格明细集合
     * @return 布尔
     */
    boolean deletePriceSystems(int id, List<PricesDetails> pricesDetailsList) throws Exception;
}

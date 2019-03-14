/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.offer;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.model.dto.offer.AppOfferProductDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
public interface IOfferPanelsService extends IService<OfferPanels> {
    /**
     * 通过报价单id查询屏体列表
     * @param offerId 报价单id
     * @return 报价单屏体列表
     */
    List<OfferPanelsDto> selectListByOffer(Long offerId, String lang);

    /**
     * 通过报价单id集合查询屏体列表
     * @param offerIdList 报价单id
     * @param lang 语言
     * @return 报价单屏体列表
     */
    List<OfferPanelsDto> selectListByOfferList(List<Long> offerIdList, String lang);

    List<AppOfferProductDto> selectApiListByOfferData(List<Long> offerIdList, String lang);

    List<OfferPanelsDto> selectDtoList(long offerId, Long id);
}

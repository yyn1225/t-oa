/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.dao.offer;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.model.dto.offer.AppOfferProductDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
public interface OfferPanelsMapper extends BaseMapper<OfferPanels> {
    /**
     * 通过报价单id查询屏体列表
     * @param offerId 报价单id
     * @param lang 语言
     * @return 报价单屏体列表
     */
    List<OfferPanelsDto> selectListByOffer(@Param("offerId") Long offerId, @Param("lang") String lang);

    /**
     * 通过报价单id集合查询屏体列表
     * @param offerIdList 报价单id
     * @param lang 语言
     * @return 报价单屏体列表
     */
    List<OfferPanelsDto> selectListByOfferList(@Param("offerIdList") List<Long> offerIdList, @Param("lang") String lang);


    List<AppOfferProductDto> selectApiListByOfferData(@Param("offerIdList") List<Long> offerIdList, @Param("lang") String lang);

    List<OfferPanelsDto> selectDtoList(@Param("offerId") long offerId,@Param("id")  Long id);
}
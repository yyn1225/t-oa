/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.offer;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.jtech.toa.dao.offer.OfferPanelsMapper;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.model.dto.offer.AppOfferProductDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.service.offer.IOfferPanelsService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@Service
public class OfferPanelsServiceImpl extends ServiceImpl<OfferPanelsMapper, OfferPanels> implements IOfferPanelsService {

    @Override
    public List<OfferPanelsDto> selectListByOffer(Long offerId, String lang) {
        return baseMapper.selectListByOffer(offerId, lang);
    }

    @Override
    public List<OfferPanelsDto> selectListByOfferList(List<Long> offerIdList, String lang) {
        return baseMapper.selectListByOfferList(offerIdList, lang);
    }
    @Override
    public List<AppOfferProductDto> selectApiListByOfferData(List<Long> offerIdList, String lang) {
        return baseMapper.selectApiListByOfferData(offerIdList, lang);
    }

    @Override
    public List<OfferPanelsDto> selectDtoList(long offerId, Long id) {
        return baseMapper.selectDtoList(offerId, id);
    }
}

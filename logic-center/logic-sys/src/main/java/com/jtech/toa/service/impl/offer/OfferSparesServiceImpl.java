/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.offer;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.jtech.toa.dao.offer.OfferSparesMapper;
import com.jtech.toa.entity.offer.OfferSpares;
import com.jtech.toa.model.dto.products.SpareDetails;
import com.jtech.toa.service.offer.IOfferSparesService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@Service
public class OfferSparesServiceImpl extends ServiceImpl<OfferSparesMapper, OfferSpares> implements IOfferSparesService {

    @Override
    public List<SpareDetails> getSpareListByOffer(long offerId, long panel, int type, String lang) {
        return baseMapper.selectSparesByOffer(offerId, panel, type, lang);
    }
}

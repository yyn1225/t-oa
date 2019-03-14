/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.offer;

import com.jtech.toa.entity.offer.OfferService;
import com.jtech.toa.dao.offer.OfferServiceMapper;
import com.jtech.toa.model.dto.offer.OfferServiceDto;
import com.jtech.toa.service.offer.IOfferServiceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@Service
public class OfferServiceServiceImpl extends ServiceImpl<OfferServiceMapper, OfferService> implements IOfferServiceService {

    @Autowired
    private OfferServiceMapper offerServiceMapper;

    @Override
    public List<OfferServiceDto> selectListById(long id,String lang) {
        return offerServiceMapper.selectListById(id,lang);
    }
}

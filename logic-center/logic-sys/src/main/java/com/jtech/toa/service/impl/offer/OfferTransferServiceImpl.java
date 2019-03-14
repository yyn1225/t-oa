/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.offer;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.offer.OfferTransfer;
import com.jtech.toa.dao.offer.OfferTransferMapper;
import com.jtech.toa.model.dto.offer.OfferTransferDto;
import com.jtech.toa.service.offer.IOfferTransferService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-12-10
 */
@Service
public class OfferTransferServiceImpl extends ServiceImpl<OfferTransferMapper, OfferTransfer> implements IOfferTransferService {

    @Override
    public void selectByPage(Page<OfferTransferDto> requestPage) {
        List<OfferTransferDto> offerTransferList = baseMapper.selectByPage(requestPage);
        requestPage.setRecords(offerTransferList);
    }

    @Override
    public OfferTransferDto selectOneById(long id) {
        return baseMapper.selectOneById(id);
    }
}

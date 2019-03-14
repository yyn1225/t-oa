/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.offer;

import com.jtech.toa.entity.offer.OfferService;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.model.dto.offer.OfferServiceDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
public interface IOfferServiceService extends IService<OfferService> {

    List<OfferServiceDto> selectListById(long id, String lang);
}

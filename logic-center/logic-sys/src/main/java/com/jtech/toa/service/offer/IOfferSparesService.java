/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.offer;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.toa.entity.offer.OfferSpares;
import com.jtech.toa.model.dto.products.SpareDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
public interface IOfferSparesService extends IService<OfferSpares> {
	/**
	 * 通过报价单查询报价备件信息
	 * @param offerId 报价单id
	 * @param panel 屏体id
	 * @param type 备件类型：1-标配，2-选配，3-免费
	 * @param lang 语言
	 * @return List<SpareDetails> 备件明细集合
	 */
	List<SpareDetails> getSpareListByOffer(long offerId, long panel, int type, String lang);
}

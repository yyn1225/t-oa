/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.vo;

import com.jtech.toa.entity.offer.*;
import com.jtech.toa.model.dto.offer.MyOfferDto;

import java.io.Serializable;
import java.util.List;

import com.jtech.toa.model.dto.offer.OfferServiceDto;
import com.jtech.toa.model.dto.products.PanelDetails;
import com.jtech.toa.model.dto.products.SpareDetails;
import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class OfferVo implements Serializable {
    private static final long serialVersionUID = -3818453501089787141L;
    public Offer offer;
    public MyOfferDto myOffer;
    /**
     * 这个是用于保存的屏体信息
     */
    public List<PanelVo> panelList;
    public List<OfferService> serviceList;
    public List<OfferServiceDto> serviceListDto;
    public OfferTransfer transfer;
    public List<TransportPackage> transport;

    /**
     * 这个是用于编辑数据时，将原始的参数带出的
     */
    public List<PanelDetails> panelDetailList;
}

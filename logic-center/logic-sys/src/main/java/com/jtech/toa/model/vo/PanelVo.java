/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.vo;

import java.util.List;

import lombok.Data;

import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.offer.OfferSpareSelfdefine;
import com.jtech.toa.entity.offer.OfferSpares;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.dto.products.SpareDetails;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class PanelVo {
    public Long offer;
    public OfferPanels panels;
    public OfferPanelsDto panelsDto;
    public Params params;
    public Box box;
    public Module module;
    public List<OfferSpares> standardList;
    public List<OfferSpares> spareList;
    public List<OfferSpares> freeList;
    public List<OfferSpareSelfdefine> selfStandList;
    public List<OfferSpareSelfdefine> selfSpareList;
    public List<OfferSpareSelfdefine> selfFreeList;

    private List<SpareDetails> freeDetailList;
    private List<SpareDetails> spareDetailList;
    private List<SpareDetails> standardDetailList;
}

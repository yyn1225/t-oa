package com.jtech.toa.model.dto.products;

import java.util.List;

import lombok.Data;

import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.offer.OfferSpareSelfdefine;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;


/**
 * <p>
 *  报价单屏体明细
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@Data
public class PanelDetails {
    private long id;
    private OfferPanels offerPanels;
    private Module module;
    private Params params;
    private Price price;
    private Product product;
    private Box box;
    /**
     * 新增 拼屏 箱体
     */
    private List<OfferPanelsDto> splicingPanelsDto;

    /**
     * 新增 拼屏 产品
     */
    private List<Product> splicingProduct;

    /**
     * 新增 拼屏 箱体
      */
    private List<Box> splicingBoxList;

    private List<SpareDetails> freeDetailList;
    private List<SpareDetails> spareDetailList;
    private List<SpareDetails> standardDetailList;

    private List<OfferSpareSelfdefine> selfStandardList;
    private List<OfferSpareSelfdefine> selfSpareList;
    private List<OfferSpareSelfdefine> selfFreeList;
}

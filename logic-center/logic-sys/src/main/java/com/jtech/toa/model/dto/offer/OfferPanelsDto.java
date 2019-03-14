/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.offer;

import java.math.BigDecimal;

import lombok.Data;

import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class OfferPanelsDto {
    private Long id;
    private Long offer;
    private Integer panel;
    private Integer wcount;
    private Integer lcount;
    private BigDecimal horizontal;
    private BigDecimal longitudinal;
    private BigDecimal price;
    private Integer orders;
    private BigDecimal totalPrice;
    private Integer discount;
    private BigDecimal suggPrice;
    private BigDecimal costPrice;
    private Integer quantity;
    private Integer series;
    private BigDecimal standardPrice;
    private Integer standardDiscount;
    private BigDecimal freePrice;
    private BigDecimal sparePrice;
    private Integer spareDiscount;
    private String remark;
    private String seriesName;
    private Integer splitScreenParent;

    private Product product;
    private Box box;
    private Params params;

}

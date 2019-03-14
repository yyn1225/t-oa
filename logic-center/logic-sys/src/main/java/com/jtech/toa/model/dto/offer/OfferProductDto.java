/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.offer;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class OfferProductDto {
    private  Long id;
    private Long offer;
    private Long product;
    private Integer transverse;
    private Integer portrait;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal priceCost;
    private BigDecimal priceGuide;
    private BigDecimal priceReal;
    private String unit;
    private Integer count;
    private String partNo;
    private Integer powerAvg;
    private Integer powerMax;
    private Long productId;
}

/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SparePriceDetailsDto {
    private Integer id;
    private Integer spare;
    private String material;
    private BigDecimal price;
    private BigDecimal salePrice;
    private Integer systems;
    private String unit;

}

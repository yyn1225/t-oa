/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.prices;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PricesDetailDto {
    private Integer id;
    private Integer panel;
    private String scnNo;
    private BigDecimal price;
    private BigDecimal modual;
    private Integer systems;
    private String unit;
    private String productState;
}

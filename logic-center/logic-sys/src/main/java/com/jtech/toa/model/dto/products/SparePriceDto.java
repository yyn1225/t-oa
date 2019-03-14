/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.product.SparePrice;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class SparePriceDto {

    private Integer areaId;
    private String name;
    private Integer spareId;
    private BigDecimal price;
    private Integer sparePriceId;
    private BigDecimal salePrice;
    private String unit;

    public SparePrice toSparePrice() {
        SparePrice sparePrice = new SparePrice();
        sparePrice.setId(this.sparePriceId);
        sparePrice.setSpare(this.spareId);
        sparePrice.setUnit(this.unit);
        sparePrice.setArea(this.areaId);
        sparePrice.setPrice(this.price);
        sparePrice.setSalePrice(this.salePrice);
        return sparePrice;
    }
}

/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.prices;

import com.jtech.toa.entity.product.Price;
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
public class SavePriceDto {
    private BigDecimal basePrice;
    private BigDecimal salePrice;
    private BigDecimal floorPrice;
    private BigDecimal areaPrice;
    private Integer area;
    private Integer product;
    private Integer priceId;
    private Integer priceEditId;
    private String unit;
    private Integer spare;
    private Integer areaId;

    public Price toPrice() {
        Price price = new Price();
        price.setId(this.priceId);
        price.setPrice(this.basePrice);
        price.setSalePrice(this.salePrice);
        price.setUnit(this.unit);
        price.setProduct(this.product);
        return price;
    }

    public Price toPriceTwo() {
        Price price = new Price();
        price.setId(this.priceId);
        price.setPrice(this.floorPrice);
        price.setSalePrice(this.areaPrice);
        price.setUnit(this.unit);
        price.setProduct(this.product);
        return price;
    }
}

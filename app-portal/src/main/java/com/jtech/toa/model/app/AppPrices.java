/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.app;

import com.jtech.toa.entity.product.Price;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class AppPrices {
    private int id;
    private int product;
    private float prices;
    private String unit;
    private int area;

    public AppPrices(Price price){
        this.id=price.getId();
        this.area=price.getArea();
        this.prices=price.getPrice().floatValue();
        this.product=price.getProduct();
        this.unit=price.getUnit();
    }
}

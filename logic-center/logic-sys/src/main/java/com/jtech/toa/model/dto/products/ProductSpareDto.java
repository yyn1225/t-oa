/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class ProductSpareDto {
    private int id;
    private String productIndex;
    private int productId;
    private int product;
    private String brand;
    private String model;
    private String type;
    private int standard;
    private String material;
    private String description;
    private String unit;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private String moneyUnit;

    private int count2Year;
    private int type2Year;
    private String spel2Year;

    private int count3Year;
    private int type3Year;
    private String spel3Year;

    private int count4Year;
    private int type4Year;
    private String spel4Year;

    private int count5Year;
    private int type5Year;
    private String spel5Year;
}

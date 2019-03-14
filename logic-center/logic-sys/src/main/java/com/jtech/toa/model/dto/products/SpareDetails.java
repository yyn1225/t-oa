/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.offer.OfferFormula;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpareDetails {
    private int id;
    private String brand;
    private String model;
    private String type;

    private String material;
    private String description;
    private String unit;
    private BigDecimal realPrice;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private String moneyUnit;

    private Integer realCount;
    private Integer guideCount;

    private BigDecimal count2Year;
    private Integer type2Year;
    private String spel2Year;

    private BigDecimal count3Year;
    private Integer type3Year;
    private String spel3Year;

    private BigDecimal count4Year;
    private Integer type4Year;
    private String spel4Year;

    private BigDecimal count5Year;
    private Integer type5Year;
    private String spel5Year;

    /**
     * 报价配件id
     */
    private Long offerSpareId;

    private List<OfferFormula> formula;

    /**
     * 公式json字符串
     */
    private String formulaJSON;
}

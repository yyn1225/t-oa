/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AppBoxs {
    private int id;
    private String scnNo;
    private int modual;
    private int modual2;
    private int transverseCount;
    private int portraitCount;
    private BigDecimal weight;
    private Integer transversePix;
    private Integer portraitPix;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal thickness;
    private String boxType;
    private String boxTypeLang;
}

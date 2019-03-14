/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class BoxExportDto {

    private String scnNo;
    private Integer modual;
    private Integer transverseCount;
    private Integer portraitCount;
    private BigDecimal weight;
    private Integer transversePix;
    private Integer portraitPix;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal thickness;
    private String boxType;
}

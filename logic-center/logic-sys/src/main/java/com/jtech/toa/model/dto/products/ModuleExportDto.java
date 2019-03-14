/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

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
public class ModuleExportDto {


    private String scnNo;
    private BigDecimal height;
    private BigDecimal width;
    private Integer transverse;
    private Integer portrait;
    private BigDecimal pitch;
    private Integer density;
    private Integer lamp;
    private String configuration;
    private BigDecimal weight;
}

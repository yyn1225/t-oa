/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.imports;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class PriceDetailsImportDto {
    private Integer id;
    private Integer panel;
    private BigDecimal price;
    private BigDecimal modual;
    private Integer systems;
    private String partNo;
    private Integer rowNum;
    private String error;
    private String state;
}

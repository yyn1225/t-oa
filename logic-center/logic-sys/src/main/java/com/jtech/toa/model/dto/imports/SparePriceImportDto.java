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
public class SparePriceImportDto {
    private Integer area;
    private String name;
    private Integer spare;
    private BigDecimal price;
    private Integer id;
    private BigDecimal salePrice;
    private String unit;
    private Integer rowNum;
    private String error;
    private String material;
    private String remark;
}

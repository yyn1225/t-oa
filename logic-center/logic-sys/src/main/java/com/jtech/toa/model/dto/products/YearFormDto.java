/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class YearFormDto {
    private Integer year;
    private Integer unitType;
    private String spel;
    private BigDecimal counts;
    private Integer type;
}

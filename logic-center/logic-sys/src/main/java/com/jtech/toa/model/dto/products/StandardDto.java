/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;


import com.jtech.toa.entity.product.Standard;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class StandardDto {
    private int id;
    private String brand;
    private String material;
    private String model;
    private String description;
    private String unit;
    private String type;
    private String remark;
    private Date executeTime;
    private BigDecimal num;
    private int styType;
    private String formula;
    private int sid;
    private Integer standard;

    public Standard toStandard(){
        Standard standard = new Standard();
        standard.setId(this.sid);
        standard.setStandard(this.styType);
        return standard;
    }
}

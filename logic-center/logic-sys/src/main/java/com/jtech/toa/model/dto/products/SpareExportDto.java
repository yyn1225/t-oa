/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import lombok.Data;

/**
 * <p>备件DTO</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class SpareExportDto  {

    private String material;
    private String type;
    private String model;
    private String unit;
    private Integer classify;
    private String description;
}

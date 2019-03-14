/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto;

import com.google.common.collect.Maps;

import java.util.Map;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class UpdateParam{
    private int id;
    private Map<String,Object> values;

    public UpdateParam(){
        this.values = Maps.newHashMap();
    }
}
/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto;

import com.google.common.collect.Lists;

import java.util.List;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class UpdateData {
    private String table;
    List<UpdateParam> params;
    private int sort;

    public UpdateData(){
        this.params = Lists.newArrayList();
    }
}





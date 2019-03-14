/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto;

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
public class InsertData<T> {
    private String table;
    private int sort;
    private List<T> params;

    public InsertData(String table,List<T> params,int sort){
        this.params = params;
        this.sort = sort;
        this.table = table;
    }

    public InsertData(){

    }
}

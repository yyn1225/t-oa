/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.product.Params;
import lombok.Data;

@Data
public class AppParams {
    private int id;
    private Params params;
    private int panel;

    public AppParams(Params params){
        this.id=params.getId();
        this.params= params;
        this.panel = params.getProduct();
    }
}

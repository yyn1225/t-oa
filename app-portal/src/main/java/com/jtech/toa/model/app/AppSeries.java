/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.app;

import com.jtech.toa.entity.product.Series;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class AppSeries {
    private int id;
    private String name;
    private int parent;
    private String parentName;
    private int line;

    public AppSeries(Series series) {
        this.id = series.getId();
        this.name = series.getName();
        this.line = series.getLine();
        this.parent = series.getParent();
    }
}

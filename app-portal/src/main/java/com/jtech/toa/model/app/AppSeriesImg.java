/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.app;

import com.jtech.toa.entity.product.SeriesImages;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class AppSeriesImg {
    private int id;
    private String images;
    private int series;

    public AppSeriesImg(SeriesImages img){
        this.id= img.getId();
        this.images = img.getUrlThum();
        this.series = img.getSeries();
    }
}

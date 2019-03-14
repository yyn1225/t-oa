/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.product.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ProductDto implements Serializable {

    private static final long serialVersionUID = -3818453501089787141L;
    private Integer id;
    private Integer box;
    private Integer configuration;
    private String certification;
    private String partNo;
    private String state;
    private Integer series;
    private Integer featured;
    private Date executionTime;
    private String type;
    private String status;
    private String color;
    private Integer request;
    private String productConfiguration;
    private String productSeries;
    private String productType;
    private String productStatus;
    private Integer salesId;
    private Integer salesStatus;
    private Date updateTime;
    private BigDecimal suggestPrice;
    private BigDecimal floorPrice;
    private String code;
    private String parentSeriesName;

    private BigDecimal modualWidth;
    private BigDecimal modualHeight;
    private Integer transverseCount;
    private Integer portraitCount;
    private Integer sizeWidth;
    private Integer sizeHeight;

    /**
     * 计算尺寸信息
     */
    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setBox(this.box);
        product.setConfiguration(this.configuration);
        product.setCertification(this.certification);
        product.setPartNo(this.partNo);
        product.setState(this.state);
        product.setSeries(this.series);
        product.setFeatured(this.featured);
        product.setExecutionTime(this.executionTime);
        product.setType(this.type);
        product.setStatus(this.status);
        product.setColor(this.color);
        product.setRequest(this.request);
        return product;
    }
}

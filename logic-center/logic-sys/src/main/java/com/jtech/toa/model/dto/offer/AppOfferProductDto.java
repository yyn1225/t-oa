package com.jtech.toa.model.dto.offer;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppOfferProductDto {
    private long id;
    private long offer;
    private long panel;
    private BigDecimal wcount;
    private BigDecimal lcount;
    private BigDecimal horizontal;
    private BigDecimal longitudinal;
    private BigDecimal price;
    private int orders;
    private BigDecimal totalPrice;
    private int discount;
    private BigDecimal suggPrice;
    private BigDecimal costPrice;
    private int quantity;
    private int series;
    private String seriesName;
    private BigDecimal standardPrice;
    private int standardDiscount;
    private BigDecimal freePrice;
    private int spareDiscount;
    private String configuration;
    private String certification;
    private String partNo;
    private String state;
    private int featured;
    private String color;
}

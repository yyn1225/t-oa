/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.offer;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class OfferDto {
    private Long id;
    private String num;
    private Date createTime;
    private Integer creater;
    private Integer customer;
    private String customerName;
    private String payment;
    private String shipping;
    private Integer guarantee;
    private Integer waitingDate;
    private String trader;
    private BigDecimal cost;
    private String moneyUnit;
    private String version;
    private Long parent;
    private Integer status;
    private Integer valid;
    private String sizeUnit;
    private String series;
    private String remark;
    private String projectName;
}

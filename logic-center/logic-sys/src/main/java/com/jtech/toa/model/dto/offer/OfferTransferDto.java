/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.offer;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 物流补充数据传输对象
 * </p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class OfferTransferDto {
    private long id;
    private long orders;
    private String fromAddress;
    private String toAddress;
    private BigDecimal cost;
    private int submiter;
    private Integer status;
    private Date submitTime;
    private String transport;
    private String size;
    private String seriesName;
    private String bidder;
    private String offerTime;
    private String moneyUnit;
    private String customerName;
}

/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.offer;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class MyOfferDto {
    private Long id;
    /**
     * 客户名称
     */
    private String customerName;
    private String seriesName;
    private String series;
    private BigDecimal cost;
    private BigDecimal totalPrice;
    private Integer totalDiscount;
    private String levelName;
    private BigDecimal levelDiscount;
    private String creater;
    private String examiner;
    /**
     * 贸易公司
     */
    private String trader;
    private String traderAddress;
    private String traderPhone;
    /**
     * 尺寸单位
     */
    private String sizeUnit;
    /**
     * 付款方式
     */
    private String payment;
    /**
     * 交期
     */
    private int waitingDate;
    /**
     * 保质期
     */
    private int guarantee;
    /**
     * 认证
     */
    private String certification;
    /**
     * 配置
     */
    private String remark;
    /**
     * 状态
     */
    private Integer status;
    private String moneyUnit;
    private String moneyType;
    private String projectName;
    private Date createTime;
}

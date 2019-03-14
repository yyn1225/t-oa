/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.offer;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <p>报价单查询列表dto</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class OfferListDto {
    private Long id;
    private String seriesName;
    private String customerName;
    private Integer totalDiscount;
    private BigDecimal totalPrice;
    private Integer guarantee;
    private Integer waitingDate;
    private String payment;
    private String username;
    private Date createTime;
    private String levelName;
    private Integer approvalResult;
    private Long approvalId;
    private Integer approvalStatus;
    private String moneyUnit;
    private Integer offerStatus;
    private String projectName;
    private String remark;
    private String num;
    private Long preferenceId;
    private String preferenceName;
    private String rate;
    private String offere;
    private String offerJson;
    /**
     * 二维码访问路径
     */
    private String qcodeUrl;
    private List<OfferPanelsDto> panelsList = Lists.newArrayList();

    public void addList(OfferPanelsDto offerPanels) {
        panelsList.add(offerPanels);
    }
}

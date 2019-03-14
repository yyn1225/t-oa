/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class AppOffer {
    private long id;
    private String customer;
    private String seriesName;
    private BigDecimal prices;
    private Date createTime;
    private String unit;
    private String details;
    private int status;
    /**
     * 标注App端的数据是否已经同步到服务端
     */
    private boolean syncFlag;
    /**
     * 标注服务端是否有此数据
     */
    private boolean serviceFlag;
}

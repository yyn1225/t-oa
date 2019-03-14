/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.sys;

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
public class ApprovalTaskDto {
    private Long id;
    private Long approvalId;
    private Integer status;
    private Integer result;
    private String customer;
    private String productName;
    private String creater;
    private BigDecimal totalPrice;
    private String opinion;
    private Date createTime;
}

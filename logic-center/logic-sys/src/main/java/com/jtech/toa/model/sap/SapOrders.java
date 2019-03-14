/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.sap;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class SapOrders {
    /**
     * 销售和分销凭证号
     */
    String vbeln;
    /**
     * 销售和分销凭证的项目号
     */
    int posnr;
    /**
     * 物料号
     */
    String matnr;
    /**
     * 物料描述
     */
    String maktx;
    /**
     * 以销售单位表示的累计订单数量
     */
    int kwmeng;
    /**
     * 基本计量单位
     */
    String meins;
    /**
     * 报价和销售订单的拒绝原因
     */
    String abgru;
    /**
     * 利润中心
     */
    String prctr;
    /**
     * 计划行日期
     */
    String edatu;
    /**
     * SD凭证货币
     */
    int waerk;
    /**
     * 从条件定价过程小计1
     */
    int zkwi1;
}

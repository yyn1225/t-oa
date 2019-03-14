/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.sap;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class SapHeader {
    /**
     * 销售和分销凭证号
     */
    String vbeln;
    /**
     * 销售组织
     */
    String vkorg;
    /**
     * 名称
     */
    String vtext;
    /**
     * 销售部门
     */
    String vkbur;
    /**
     * 描述
     */
    String bezei;
    /**
     * 记录的创建日期
     */
    String erdat;
    /**
     * 请求交货日期
     */
    String vdatu;
    /**
     * 客户编号
     */
    String kunnr;
    /**
     * 名称1
     */
    String name1;
    /**
     * 名称2
     */
    String name2;
    /**
     * 国家键值
     */
    String land1;
    /**
     * 国家名称
     */
    String landx;
    /**
     * 地址
     */
    String adrnr;
    /**
     * 街道
     */
    String street;
    /**
     * 客户名称
     */
    String cname;
    /**
     * 客户编号
     */
    String kunnr2;
    /**
     * 销售员名称
     */
    String sname;
    /**
     * 凭证货币计量的净价值
     */
    String netwr;
    /**
     * SD凭证货币
     */
    int waerk;
    /**
     * 质保年限
     */
    BigDecimal zzwarranty;
    /**
     * 面积
     */
    BigDecimal zzarea;
    /**
     * 业绩分摊比例
     */
    BigDecimal zzproportion;
    /**
     * 交期类型
     */
    String zzintt;
    /**
     * 赊销方式
     */
    String credit;
    /**
     * 运输方式
     */
    String zztransport;
    /**
     * 付款方式
     */
    String zzpayment;
    /**
     * 产品名称
     */
    String zzproductname;
    /**
     * 订单细分市场
     */
    String ordermarketseg;
}

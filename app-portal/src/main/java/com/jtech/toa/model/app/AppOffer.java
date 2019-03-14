package com.jtech.toa.model.app;

import java.util.Date;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppOffer {
    private int id;
    /**
     * 报价单号
     */
    private String Quote_Num;
    /**
     * 客户编码
     */
    private String Zohu_ID;
    /**
     * 报价销售
     */
    private int sales;
    /**
     * 销售区域
     */
    private int areas;
    /**
     * 客户名称
     */
    private String customer;
    /**
     * 实际总价
     */
    private long price;
    /**
     * 单位
     */
    private String unit;
    /**
     * 当时汇率
     */
    private long exchange;
    /**
     * 详情
     */
    private String details;
    /**
     * 报价状态
     */
    private int status;


    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 创建人
     */
    private String create_userid;
    /**
     * 修改时间
     */
    private Date modify_time;
    /**
     * 修改人
     */
    private String modify_userid;
}

package com.jtech.toa.model.dto.offer;

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
public class AppOfferDto {
    private long id;
    /**
     * 报价单号
     */
    private String quoteNum;
    /**
     * 客户编码
     */
    private String zohuID;
    /**
     * 报价销售
     */
    private String sales;
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
    private Object details;
    /**
     * 报价状态
     */
    private int status;


    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUserid;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifyUserid;

    /**
     * 提交的JSON
     */
    private String offerJson;
}

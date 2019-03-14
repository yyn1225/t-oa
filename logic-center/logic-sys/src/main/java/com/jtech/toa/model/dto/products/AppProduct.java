/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import java.util.Date;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class AppProduct {
    private int id;
    /**
     * 配置
     */
    private String configuration;
    /**
     * 认证
     */
    private String certification;
    /**
     * 料号
     */
    private String partNo;
    /**
     * 系列名称
     * */
    private String series;
    /**
     * 系列ID
     */
    private Integer seriesId;
    /**
     * 状态
     */
    private int status;
    /**
     * 箱体-JSON
     */
    private String box;
    /**
     * 颜色
     */
    private String color;
    /**
     * 产品描述
     */
    private String remark;
    /**
     * 语言
     * 当为CN时，对应的都为中文包括box的json；
     * 当为EN时，对应为。。，也就是说一个物料号+一个语言是唯一的，
     * 这个地方与原来设计不一致，请考虑工作量
     */
    private String lang;

//    /**
//     * 创建时间
//     */
//    private Date createTime;
//    /**
//     * 创建人
//     */
//    private String createUserid;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifyUserid;
    /**
     * 同步状态
     */
    private int syncState;
    /**
     * 同步时间
     */
    private Date syncTime;
    /**
     * 同步人
     */
    private String syncUserid;
    /**
     * 同步异常
     */
    private String syncException;
}

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
     * 系列
     */
    private String series;
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
    /**
     * 同步状态
     */
    private int sync_state;
    /**
     * 同步时间
     */
    private Date sync_time;
    /**
     * 同步人
     */
    private String sync_userid;
    /**
     * 同步异常
     */
    private String sync_exception;

}

/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-$today.yea
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 用户类型表
 * </p>
 *
 * @author jtech
 * @since 2017-07-11
 */
@TableName("user_type")
public class UserType extends Model<UserType> {

    private static final long serialVersionUID = 1L;

    /**
     * 物理主键
     */
    private Long id;
    /**
     * 类型编码
     */
    private String code;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 类型排序
     */
    private Integer sort;
    /**
     * 系统默认
     */
    @TableField("default_flag")
    private Integer defaultFlag;
    /**
     * 类型状态;1-启用;0-禁用
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", code=" + code +
                ", name=" + name +
                ", sort=" + sort +
                ", defaultFlag=" + defaultFlag +
                ", status=" + status +
                "}";
    }
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.entity.product;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("absen_product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer box;
    private Integer configuration;
    private String certification;
    @TableField("part_no")
    private String partNo;
    private String state;
    private Integer series;
    private Integer featured;
    @TableField("update_time")
    private Date updateTime;
    private Integer updater;
    @TableField("execution_time")
    private Date executionTime;
    private String type;
    private String status;
    private String color;
    private Integer creater;
    @TableField("create_time")
    private Date createTime;
    private Integer request;
    @TableField(exist = false)
    private Integer transverseCount;
    @TableField(exist = false)
    private Integer portraitCount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

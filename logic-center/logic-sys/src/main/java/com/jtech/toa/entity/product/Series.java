/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.entity.product;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
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
@TableName("absen_product_series")
public class Series extends Model<Series> {
    private static final long serialVersionUID = 418361023653910558L;

    private Integer id;
    private String name;
    private Integer line;
    private Integer status;
    private Integer creater;
    @TableField("create_time")
    private Date createTime;
    private String image;
    private Integer parent;
    private String remark;
    private Integer type;
    private Integer featured;

    /**
     * 临时字段， 0-不可用 1-可用
     */
    @TableField(exist = false)
    private Integer enableProduct;

    public interface Line{
        public static final int Indoor=1;
        public static final int Outdoor=2;
        public static final int Rental=3;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

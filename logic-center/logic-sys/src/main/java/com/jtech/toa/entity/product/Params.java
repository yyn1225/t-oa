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
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("absen_product_params")
public class Params extends Model<Params> {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer product;
    private String control;
    private Integer calibration;
    private Integer intelligent;
    private Integer rigging;
    private Integer stack;
    private Integer front;
    @TableField("fix_modual")
    private String fixModual;
    @TableField("fix_psu")
    private String fixPsu;
    @TableField("ip_rating")
    private String ipRating;
    private String brightness;
    @TableField("contrast_ratio")
    private String contrastRatio;
    @TableField("gray_scale")
    private String grayScale;
    private String refresh;
    private String viewing;
    @TableField("power_max")
    private Integer powerMax;
    @TableField("power_avg")
    private Integer powerAvg;
    @TableField("driving_ic")
    private String drivingIc;
    @TableField("driving_type")
    private String drivingType;
    private String psu;
    @TableField("psu_power")
    private Integer psuPower;
    @TableField("psu_count")
    private Integer psuCount;
    @TableField("standard_carry_lower")
    private Integer standardCarryLower;
    @TableField("standard_carry_high")
    private Integer standardCarryHigh;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.entity.product;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p> 产品信息 箱体 实体类</p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@TableName("absen_product_box")
@Data
public class Box extends Model<Box> {

    private static final long serialVersionUID = 1L;
    private Integer id;

    @TableField("scn_no")
    private String scnNo;

    @TableField("modual")
    private int modual;

    @TableField("modual2")
    private int modual2;

    @TableField("transverse_count")
    private Integer transverseCount;

    @TableField("portrait_count")
    private Integer portraitCount;

    @TableField("weight")
    private BigDecimal weight;

    @TableField("transverse_pix")
    private Integer transversePix;

    @TableField("portrait_pix")
    private Integer portraitPix;

    @TableField("height")
    private BigDecimal height;

    @TableField("width")
    private BigDecimal width;

    @TableField("thickness")
    private BigDecimal thickness;

    @TableField("box_type")
    private String boxType;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

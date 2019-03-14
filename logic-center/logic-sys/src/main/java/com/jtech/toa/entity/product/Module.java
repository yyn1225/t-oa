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

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @since 2017-10-12
 */
@TableName("absen_product_modual")
public class Module extends Model<Module>{

    private static final long serialVersionUID = 1L;
    private Integer id;

    @TableField("scn_no")
    private String scnNo;

    @TableField("height")
    private BigDecimal height;

    @TableField("width")
    private BigDecimal width;

    @TableField("transverse")
    private Integer transverse;

    @TableField("portrait")
    private Integer portrait;

    @TableField("pitch")
    private BigDecimal pitch;

    @TableField("density")
    private Integer density;

    @TableField("lamp")
    private Integer lamp;

    @TableField("configuration")
    private String configuration;

    @TableField("weight")
    private BigDecimal weight;

    @TableField("description")
    private String description;

    @TableField("remark")
    private String remark;

    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScnNo() {
        return scnNo;
    }

    public void setScnNo(String scnNo) {
        this.scnNo = scnNo;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public Integer getTransverse() {
        return transverse;
    }

    public void setTransverse(Integer transverse) {
        this.transverse = transverse;
    }

    public Integer getPortrait() {
        return portrait;
    }

    public void setPortrait(Integer portrait) {
        this.portrait = portrait;
    }

    public BigDecimal getPitch() {
        return pitch;
    }

    public void setPitch(BigDecimal pitch) {
        this.pitch = pitch;
    }

    public Integer getDensity() {
        return density;
    }

    public void setDensity(Integer density) {
        this.density = density;
    }

    public Integer getLamp() {
        return lamp;
    }

    public void setLamp(Integer lamp) {
        this.lamp = lamp;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.entity.basic;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>产品配置信息实体类</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@TableName("absen_product_configuration")
public class Configuration extends Model<Configuration> {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer transverse;
    private Integer portrait;
    private String remark;
    @TableField("create_time")
    private Date createTime;
    private Integer creater;
    private Integer special;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getSpecial() {
        return special;
    }

    public void setSpecial(Integer special) {
        this.special = special;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

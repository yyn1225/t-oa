/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.entity.product;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

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
@TableName("absen_series_images")
public class SeriesImages extends Model<SeriesImages> {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private boolean defaults;
    @TableField("url_thum")
    private String urlThum;
    private Integer sorts;
    private Integer uploader;
    @TableField("upload_time")
    private Date uploadTime;
    private long attachment;
    private Integer series;
    @TableField("url_original")
    private String urlOriginal;
    @TableField("delete_flag")
    private Integer deleteFlag;
    @TableField("update_time")
    private Date updateTime;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

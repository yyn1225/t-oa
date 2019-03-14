/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.entity.file;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
@TableName("absen_file")
@Data
public class File extends Model<File> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String extend;
    private Integer type;
    private String url;
    private Long size;
    private String path;
    @TableField("upload_time")
    private Date uploadTime;
    private Integer uploader;
    @TableField("package")
    private Integer packages;
    private Integer category;
    @TableField("update_time")
    private Date updateTime;
    private Integer detail;
    private Integer securitys;
    private Integer market;

    /**
     * 文件类别
     */
    public interface FileCategory{
        /**
         * 新闻和活动
         */
        public static final int News=1;
        /**
         * 产品信息
         */
        public static final int Products=2;
        /**
         * 解决方案
         */
        public static final int Solutions=3;
        /**
         * 品牌
         */
        public static final int Brand=4;
    }

    public interface FileSecurity{
        /**
         * 公开，不区分权限控制
         */
        public static final int Commons=1;

        /**
         * 查看
         */
        public static final int Readonly=2;

        /**
         * 私密
         */
        public static final int Securitys=3;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

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
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jtech
 * @since 2017-07-11
 */
@Data
@TableName("absen_org_user")
public class User extends Model<User> {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phone;
    @TableField("work_no")
    private String workNo;
    @TableField("login_name")
    private String loginName;
    private String password;
    private String salt;
    private String language;
    @TableField("language_default")
    private String languageDefault;
    private int area;
    private String avatar;
    private String email;
    private int creater;
    @TableField("create_time")
    private Date createTime;
    private Integer status;
    @TableField("delete_flag")
    private Integer deleteFlag;
    private String company;
    @TableField("delete_able")
    private Integer deleteAble;
    private Integer assistant;
    private String crmid;

    public interface Status {
        /**
         * 有效
         */
        public static final Integer Valid = 1;
        /**
         * 无效或已失效
         */
        public static final Integer UnValid = 0;
    }

    public interface DeleteFlag {
        /**
         * 未删除
         */
        public static final Integer NO = 0;
        /**
         * 已经删除
         */
        public static final Integer YES = 1;
    }

    public interface DeleteAble {
        /**
         * 允许删除
         */
        public static final Integer YES = 0;
        /**
         * 不允许删除
         */
        public static final Integer NO = 1;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
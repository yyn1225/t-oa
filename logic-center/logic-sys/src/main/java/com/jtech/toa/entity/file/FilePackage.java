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
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
@Data
@TableName("absen_file_package")
public class FilePackage extends Model<FilePackage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	private Integer parent;
	private String icon;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	private Integer creater;
	private Integer updater;
	@TableField("full_path")
	private String fullPath;

	@TableField(exist=false)
	private List<FilePackage> children;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "FilePackage{" +
			"id=" + id +
			", name=" + name +
			", parent=" + parent +
			", icon=" + icon +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", creater=" + creater +
			", updater=" + updater +
			", fullPath=" + fullPath +
			"}";
	}
}

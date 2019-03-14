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

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
@TableName("absen_log_file")
public class LogFile extends Model<LogFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 1修改；2删除
     */
	private Integer id;
	private Long files;
	private String name;
	private String url;
	private Integer updater;
	private Integer type;
	@TableField("update_time")
	private Date updateTime;
	private String uploader;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getFiles() {
		return files;
	}

	public void setFiles(Long file) {
		this.files = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LogFile{" +
			"id=" + id +
			", files=" + files +
			", name=" + name +
			", url=" + url +
			", updater=" + updater +
			", type=" + type +
			", updateTime=" + updateTime +
			", uploader=" + uploader +
			"}";
	}
}

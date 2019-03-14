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
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-10-26
 */
@TableName("absen_file_package_relation")
public class FilePackageRelation extends Model<FilePackageRelation> {

    private static final long serialVersionUID = 1L;

	private Integer packages;
	private Integer files;


	public Integer getPackages() {
		return packages;
	}

	public void setPackages(Integer packages) {
		this.packages = packages;
	}

	public Integer getFiles() {
		return files;
	}

	public void setFiles(Integer files) {
		this.files = files;
	}

	@Override
	protected Serializable pkVal() {
		return null;
	}

	@Override
	public String toString() {
		return "FilePackageRelation{" +
			"packages=" + packages +
			", files=" + files +
			"}";
	}
}

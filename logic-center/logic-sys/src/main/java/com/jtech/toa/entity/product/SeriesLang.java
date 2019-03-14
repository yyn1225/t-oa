/*
<<<<<<< HEAD:t-oa/logic-center/logic-sys/src/java/com/jtech/toa/entity/product/SeriesLang.java
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
=======
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
>>>>>>> 4bc6f467da88d81c74ab071b465b91a6c683715c:t-oa/logic-center/logic-sys/src/java/com/jtech/toa/entity/product/SeriesLang.java
 */

package com.jtech.toa.entity.product;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
@TableName("absen_product_series_lang")
public class SeriesLang extends Model<SeriesLang> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String lang;
	private Integer series;
	@TableField("name_lang")
	private String nameLang;
	@TableField("remark_lang")
	private String remarkLang;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	public String getNameLang() {
		return nameLang;
	}

	public void setNameLang(String nameLang) {
		this.nameLang = nameLang;
	}

	public String getRemarkLang() {
		return remarkLang;
	}

	public void setRemarkLang(String remarkLang) {
		this.remarkLang = remarkLang;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SeriesLang{" +
			"id=" + id +
			", lang=" + lang +
			", series=" + series +
			", nameLang=" + nameLang +
			", remarkLang=" + remarkLang +
			"}";
	}
}

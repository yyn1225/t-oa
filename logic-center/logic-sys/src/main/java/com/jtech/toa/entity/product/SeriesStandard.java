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

package com.jtech.toa.entity.product;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-10-28
 */
@TableName("absen_product_series_standard")
public class SeriesStandard extends Model<SeriesStandard> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer spare;
	private Integer series;
	private Integer standard;
	@TableField("counts_two")
	private BigDecimal countsTwo;
    /**
     * 1.标配 2.选配 3.免费
     */
	private Integer type;
	@TableField("spel_two")
	private String spelTwo;
	@TableField("cal_type_two")
	private Integer calTypeTwo;
	@TableField("counts_three")
	private BigDecimal countsThree;
	@TableField("spel_three")
	private String spelThree;
	@TableField("cal_type_three")
	private Integer calTypeThree;
	@TableField("counts_four")
	private BigDecimal countsFour;
	@TableField("spel_four")
	private String spelFour;
	@TableField("cal_type_five")
	private Integer calTypeFive;
	@TableField("counts_five")
	private BigDecimal countsFive;
	@TableField("spel_five")
	private String spelFive;
	@TableField("cal_type_four")
	private Integer calTypeFour;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpare() {
		return spare;
	}

	public void setSpare(Integer spare) {
		this.spare = spare;
	}

	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	public Integer getStandard() {
		return standard;
	}

	public void setStandard(Integer standard) {
		this.standard = standard;
	}

	public BigDecimal getCountsTwo() {
		return countsTwo;
	}

	public void setCountsTwo(BigDecimal countsTwo) {
		this.countsTwo = countsTwo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSpelTwo() {
		return spelTwo;
	}

	public void setSpelTwo(String spelTwo) {
		this.spelTwo = spelTwo;
	}

	public Integer getCalTypeTwo() {
		return calTypeTwo;
	}

	public void setCalTypeTwo(Integer calTypeTwo) {
		this.calTypeTwo = calTypeTwo;
	}

	public BigDecimal getCountsThree() {
		return countsThree;
	}

	public void setCountsThree(BigDecimal countsThree) {
		this.countsThree = countsThree;
	}

	public String getSpelThree() {
		return spelThree;
	}

	public void setSpelThree(String spelThree) {
		this.spelThree = spelThree;
	}

	public Integer getCalTypeThree() {
		return calTypeThree;
	}

	public void setCalTypeThree(Integer calTypeThree) {
		this.calTypeThree = calTypeThree;
	}

	public BigDecimal getCountsFour() {
		return countsFour;
	}

	public void setCountsFour(BigDecimal countsFour) {
		this.countsFour = countsFour;
	}

	public String getSpelFour() {
		return spelFour;
	}

	public void setSpelFour(String spelFour) {
		this.spelFour = spelFour;
	}

	public Integer getCalTypeFive() {
		return calTypeFive;
	}

	public void setCalTypeFive(Integer calTypeFive) {
		this.calTypeFive = calTypeFive;
	}

	public BigDecimal getCountsFive() {
		return countsFive;
	}

	public void setCountsFive(BigDecimal countsFive) {
		this.countsFive = countsFive;
	}

	public String getSpelFive() {
		return spelFive;
	}

	public void setSpelFive(String spelFive) {
		this.spelFive = spelFive;
	}

	public Integer getCalTypeFour() {
		return calTypeFour;
	}

	public void setCalTypeFour(Integer calTypeFour) {
		this.calTypeFour = calTypeFour;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SeriesStandard{" +
			"id=" + id +
			", spare=" + spare +
			", series=" + series +
			", standard=" + standard +
			", countsTwo=" + countsTwo +
			", type=" + type +
			", spelTwo=" + spelTwo +
			", calTypeTwo=" + calTypeTwo +
			", countsThree=" + countsThree +
			", spelThree=" + spelThree +
			", calTypeThree=" + calTypeThree +
			", countsFour=" + countsFour +
			", spelFour=" + spelFour +
			", calTypeFive=" + calTypeFive +
			", countsFive=" + countsFive +
			", spelFive=" + spelFive +
			", calTypeFour=" + calTypeFour +
			"}";
	}
}

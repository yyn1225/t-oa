/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.entity.offer;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@TableName("absen_offer_spare_selfdefine")
@Data
public class OfferSpareSelfdefine extends Model<OfferSpareSelfdefine> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private Long offers;
	private Long panel;
	@TableField("spare_type")
	private String spareType;
	private String brand;
	private BigDecimal price;
	private Integer amount;
	private Integer type;
	private String unit;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OfferSpareSelfdefine{" +
			"id=" + id +
			", offers=" + offers +
			", panel=" + panel +
			", spareType=" + spareType +
			", brand=" + brand +
			", price=" + price +
			", amount=" + amount +
			", type=" + type +
			"}";
	}
}

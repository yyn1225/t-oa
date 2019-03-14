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
import java.util.List;

import com.jtech.toa.entity.product.Standard;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@TableName("absen_offer_spares")
@Data
public class OfferSpares extends Model<OfferSpares> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private Long offer;
	private Long panel;
	private Integer spare;
	@TableField("spare_name")
	private String spareName;
	private String brand;
	@TableField("count_guid")
	private Integer countGuid;
	@TableField("count_real")
	private Integer countReal;
	@TableField("price_cost")
	private BigDecimal priceCost;
	@TableField("price_sale")
	private BigDecimal priceSale;
	@TableField("price_guide")
	private BigDecimal priceGuide;
	private Integer type;

	/**
	 *存储公式集合
	**/
	@TableField(exist = false)
	private List<OfferFormula> formula;

	public interface Type{
		/**
		 * 标配
		 */
		public static final int Standard=1;
		/**
		 * 选配
		 */
		public static final int Spare=2;
		/**
		 * 免费
		 */
		public static final int Free=3;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OfferSpares{" +
			"id=" + id +
			", offer=" + offer +
			", panel=" + panel +
			", spare=" + spare +
			", spareName=" + spareName +
			", brand=" + brand +
			", countGuid=" + countGuid +
			", countReal=" + countReal +
			", priceCost=" + priceCost +
			", priceSale=" + priceSale +
			", priceGuide=" + priceGuide +
			", type=" + type +
			"}";
	}
}

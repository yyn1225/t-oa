package com.jtech.toa.entity.offer;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.model.dto.products.SeriesDto;

/**
 * <p>
 *
 * </p>
 *
 * @author jtech
 * @since 2018-03-20
 */
@Data
@TableName("absen_offer_panels")
public class OfferPanels extends Model<OfferPanels> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private Long offer;
	private Integer panel;
	private Integer wcount;
	private Integer lcount;
	private BigDecimal horizontal;
	private BigDecimal longitudinal;
	private BigDecimal price;
	private Integer orders;
	@TableField("total_price")
	private BigDecimal totalPrice;
	private BigDecimal discount;
	@TableField("sugg_price")
	private BigDecimal suggPrice;
	@TableField("cost_price")
	private BigDecimal costPrice;
	private Integer quantity;
	private Integer series;
	@TableField("standard_price")
	private BigDecimal standardPrice;
	@TableField("standard_discount")
	private BigDecimal standardDiscount;
	@TableField("free_price")
	private BigDecimal freePrice;
	@TableField("spare_price")
	private BigDecimal sparePrice;
	@TableField("spare_discount")
	private BigDecimal spareDiscount;
	@TableField("split_screen_parent")
	private Long splitScreenParent;
	@TableField("cust_horizontal")
	private BigDecimal custHorizontal;
	@TableField("cust_longitudinal")
	private BigDecimal custLongitudinal;

	@TableField(exist = false)
	private String description;
	@TableField(exist = false)
	private String partNo;
	@TableField(exist = false)
	private String remark;

	/**
	 * 拼屏子屏幕
	 */
	@TableField(exist = false)
	private List<OfferPanels> childPanels;

	@TableField(exist = false)
	private Product product;

	@TableField(exist = false)
	private Box box;

	@TableField(exist = false)
	private Params params;

	@TableField(exist = false)
	private Module module;

	@TableField(exist = false)
	private List<SeriesDto> seriesList;

	@TableField(exist = false)
	private String seriesName;

	@TableField(exist = false)
	private Integer line;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}

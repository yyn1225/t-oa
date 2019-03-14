package com.jtech.toa.entity.prices;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2018-03-22
 */
@TableName("absen_spare_price_details")
public class SparePriceDetails extends Model<SparePriceDetails> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer spare;
	private BigDecimal price;
	@TableField("sale_price")
	private BigDecimal salePrice;
	private Integer systems;
	private String unit;


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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getSystems() {
		return systems;
	}

	public void setSystems(Integer systems) {
		this.systems = systems;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SparePriceDetails{" +
			"id=" + id +
			", spare=" + spare +
			", price=" + price +
			", salePrice=" + salePrice +
			", systems=" + systems +
			", unit=" + unit +
			"}";
	}
}

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
 * @since 2017-12-26
 */
@Data
@TableName("absen_transport_package")
public class TransportPackage extends Model<TransportPackage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private Long transfer;
	private Long offer;
	private String packages;
	private BigDecimal price;
	private Integer number;
	@TableField("price_unit")
	private BigDecimal priceUnit;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}

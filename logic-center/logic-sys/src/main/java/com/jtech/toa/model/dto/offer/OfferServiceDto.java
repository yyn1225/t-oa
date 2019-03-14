/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.offer;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@Data
public class OfferServiceDto {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long offer;
	private String name;
//	private String langName;
	private Integer counts;
	private BigDecimal price;
	private String unit;



	@Override
	public String toString() {
		return "OfferService{" +
			"id=" + id +
			", offer=" + offer +
			", name=" + name +
			", counts=" + counts +
			", price=" + price +
			", unit=" + unit +
			"}";
	}
}

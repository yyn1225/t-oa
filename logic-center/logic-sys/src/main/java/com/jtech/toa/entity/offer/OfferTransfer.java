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
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-12-10
 */
@TableName("absen_offer_transfer")
@Data
public class OfferTransfer extends Model<OfferTransfer> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private Long orders;
	@TableField("from_address")
	private String fromAddress;
	@TableField("to_address")
	private String toAddress;
	private BigDecimal cost;
	private Integer submiter;
	private Integer status;
	@TableField("submit_time")
	private Date submitTime;
	private String transport;
	private String size;
	private String trade;

	/**
	 *
	 */
	public interface Status{
		public static final int Waiting=0;
		public static final int Confirmed=1;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.entity.prices;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-12-24
 */
@Data
@TableName("absen_price_system")
public class PriceSystem extends Model<PriceSystem> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	private Integer creater;
	@TableField("create_time")
	private Date createTime;
	private Integer area;
	@TableField("start_time")
	private Date startTime;
	@TableField("end_time")
	private Date endTime;
	private String unit;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}

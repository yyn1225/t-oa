package com.jtech.toa.entity.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2017-12-05
 */
@Data
@TableName("absen_sys_exchange")
public class SysExchange extends Model<SysExchange> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("valid_date")
	private String validDate;
	@TableField("sync_time")
	private Date syncTime;
	private String currency;
	private BigDecimal rmb;
	private String code;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}

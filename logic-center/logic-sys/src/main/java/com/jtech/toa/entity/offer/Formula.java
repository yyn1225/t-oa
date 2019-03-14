package com.jtech.toa.entity.offer;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2018-08-03
 */
@Data
@TableName("absen_offer_spares_formula")
public class Formula extends Model<Formula> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
    /**
     * 报价单id
     */
	private Long offer;
    /**
     * 报价屏体id
     */
	private Long panel;
    /**
     * 备件id
     */
	private Long spare;

	/**
	 * 两年数量
	 */
	@TableField("count_two")
	private BigDecimal countTwo;

	/**
	 * 两年计算类型
	 */
	@TableField("type_two")
	private Integer typeTwo;

    /**
     * 两年计算公式
     */
	@TableField("spel_two")
	private String spelTwo;

	/**
	 * 三年数量
	 */
	@TableField("count_three")
	private BigDecimal countThree;

	/**
	 * 三年计算类型
	 */
	@TableField("type_three")
	private Integer typeThree;

    /**
     * 三年计算公式
     */
	@TableField("spel_three")
	private String spelThree;

	/**
	 * 四年数量
	 */
	@TableField("count_four")
	private BigDecimal countFour;

	/**
	 * 四年计算类型
	 */
	@TableField("type_four")
	private Integer typeFour;

    /**
     * 四年计算公式
     */
	@TableField("spel_four")
	private String spelFour;

	/**
	 * 五年数量
	 */
	@TableField("count_five")
	private BigDecimal countFive;

	/**
	 * 五年计算类型
	 */
	@TableField("type_five")
	private Integer typeFive;

    /**
     * 五年计算公式
     */
	@TableField("spel_five")
	private String spelFive;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}

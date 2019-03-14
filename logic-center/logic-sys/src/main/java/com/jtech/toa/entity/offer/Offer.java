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
 * @since 2018-03-20
 */
@Data
@TableName("absen_offer")
public class Offer extends Model<Offer> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private String num;
	@TableField("create_time")
	private Date createTime;
	private Integer creater;
	private Integer customer;
	@TableField("customer_name")
	private String customerName;
	private String payment;
	private String shipping;
	private Integer guarantee;
	@TableField("waiting_date")
	private Integer waitingDate;
	private String trader;
	@TableField("total_price")
	private BigDecimal totalPrice;
	@TableField("money_unit")
	private String moneyUnit;
	private String version;
	private Integer status;
	private Integer valid;
	@TableField("size_unit")
	private Integer sizeUnit;
	private String remark;
	@TableField("project_name")
	private String projectName;
	@TableField("delete_flag")
	private String deleteFlag;
	private Integer offere;
	@TableField("update_time")
	private Date updateTime;
	private Integer updater;
	private Integer area;
	@TableField("size_type")
	private Integer sizeType;
	@TableField("total_discount")
	private BigDecimal totalDiscount;
	@TableField("service_price")
	private BigDecimal servicePrice;
	@TableField("service_discount")
	private BigDecimal serviceDiscount;
	private String rate;
	@TableField("offer_json")
	private String offerJson;
	@TableField("special_price")
	private BigDecimal specialPrice;
	@TableField("special_price_remark")
	private String specialPriceRemark;


	public interface Status{
		/**
		 * 草稿状态，尚未提交
		 */
		public final static int Draft=0;
		/**
		 * 提交审批中
		 */
		public final static int Submited=1;
		/**
		 * 已经审批通过
		 */
		public final static int Approved=2;

		/**
		 * 单子被打回
		 */
		public final static int Rejected=3;

		/**
		 * 单子被撤回
		 */
		public final static int Withdraw=4;

		/**
		 * 单子已完成
		 */
		public final static int Finish=5;
	}

	/**
	 * 删除状态
	 */
	public interface DeleteFlag {
		public final String Yes = "1";
		public final String No = "0";
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}

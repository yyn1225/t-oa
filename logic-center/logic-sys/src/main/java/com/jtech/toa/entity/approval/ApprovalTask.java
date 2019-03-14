package com.jtech.toa.entity.approval;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-11-29
 */
@TableName("absen_approval_task")
public class ApprovalTask extends Model<ApprovalTask> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	@TableField("opt_user")
	private Integer optUser;
	@TableField("opt_time")
	private Date optTime;
	@TableField("opt_status")
	private Integer optStatus;
	@TableField("opt_result")
	private Integer optResult;
	private Long orders;
	private String opinion;
	private Integer creater;
	@TableField("create_time")
	private Date createTime;

	public interface Status {
		/**
		 * 未审
		 */
		public static final Integer WAIT = 1;
		/**
		 * 已审
		 */
		public static final Integer ALREADY = 2;
		/**
		 * 撤回
		 */
		public static final Integer RETRACT = 3;
	}

	public interface Result {
		/**
		 * 同意
		 */
		public static final Integer Agree = 1;
		/**
		 * 拒绝
		 */
		public static final Integer DisAgree = 2;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOptUser() {
		return optUser;
	}

	public void setOptUser(Integer optUser) {
		this.optUser = optUser;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public Integer getOptStatus() {
		return optStatus;
	}

	public void setOptStatus(Integer optStatus) {
		this.optStatus = optStatus;
	}

	public Integer getOptResult() {
		return optResult;
	}

	public void setOptResult(Integer optResult) {
		this.optResult = optResult;
	}

	public Long getOrders() {
		return orders;
	}

	public void setOrders(Long orders) {
		this.orders = orders;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ApprovalTask{" +
			"id=" + id +
			", optUser=" + optUser +
			", optTime=" + optTime +
			", optStatus=" + optStatus +
			", optResult=" + optResult +
			", orders=" + orders +
			", opinion=" + opinion +
			", creater=" + creater +
			", createTime=" + createTime +
			"}";
	}
}

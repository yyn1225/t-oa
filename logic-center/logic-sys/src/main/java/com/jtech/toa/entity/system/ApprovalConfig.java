package com.jtech.toa.entity.system;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-12-09
 */
@TableName("absen_approval_config")
public class ApprovalConfig extends Model<ApprovalConfig> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private Integer area;
	private Integer approval;
	private Integer condition;
	private Integer auditor;
	private Integer export;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getApproval() {
		return approval;
	}

	public void setApproval(Integer approval) {
		this.approval = approval;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getAuditor() {
		return auditor;
	}

	public void setAuditor(Integer auditor) {
		this.auditor = auditor;
	}

	public Integer getExport() {
		return export;
	}

	public void setExport(Integer export) {
		this.export = export;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ApprovalConfig{" +
			"id=" + id +
			", area=" + area +
			", approval=" + approval +
			", condition=" + condition +
			", auditor=" + auditor +
			", export=" + export +
			"}";
	}
}

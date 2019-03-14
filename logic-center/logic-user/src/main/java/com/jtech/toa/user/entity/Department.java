/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.user.entity;

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
 * @since 2017-10-17
 */
@TableName("absen_org_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String code;
	private String name;
	private Integer parent;
	private Integer leader;
	private Integer assistanter;
	private Integer creater;
	@TableField("create_time")
	private Date createTime;
	private Integer level;
	private Integer status;
	private Integer updater;
	private Date updateTime;
	private String remark;
	private Integer maintainer;

	public interface Status {
		/**
		 * 有效
		 */
		public static final Integer Valid = 1;
		/**
		 * 无效或已失效
		 */
		public static final Integer UnValid = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getLeader() {
		return leader;
	}

	public void setLeader(Integer leader) {
		this.leader = leader;
	}

	public Integer getAssistanter() {
		return assistanter;
	}

	public void setAssistanter(Integer assistanter) {
		this.assistanter = assistanter;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMaintainer() {
		return maintainer;
	}

	public void setMaintainer(Integer maintainer) {
		this.maintainer = maintainer;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Department{" +
			"id=" + id +
			", code=" + code +
			", name=" + name +
			", parent=" + parent +
			", leader=" + leader +
			", assistanter=" + assistanter +
			", creater=" + creater +
			", createTime=" + createTime +
			", level=" + level +
			", status=" + status +
			"}";
	}
}

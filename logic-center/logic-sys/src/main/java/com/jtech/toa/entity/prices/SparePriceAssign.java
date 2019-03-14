package com.jtech.toa.entity.prices;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2018-03-22
 */
@TableName("absen_spare_price_assign")
public class SparePriceAssign extends Model<SparePriceAssign> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer systems;
	private Integer area;
	@TableField("assign_time")
	private Date assignTime;
	private Integer assigner;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSystems() {
		return systems;
	}

	public void setSystems(Integer systems) {
		this.systems = systems;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Date getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}

	public Integer getAssigner() {
		return assigner;
	}

	public void setAssigner(Integer assigner) {
		this.assigner = assigner;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SparePriceAssign{" +
			"id=" + id +
			", systems=" + systems +
			", area=" + area +
			", assignTime=" + assignTime +
			", assigner=" + assigner +
			"}";
	}
}

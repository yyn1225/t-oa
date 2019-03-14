package com.jtech.toa.entity.product;

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
 * @since 2017-11-27
 */
@TableName("absen_product_params_lang")
public class ProductParamsLang extends Model<ProductParamsLang> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer params;
	private String lang;
	@TableField("control_lang")
	private String controlLang;
	@TableField("fix_modual_lang")
	private String fixModualLang;
	@TableField("fix_psu")
	private String fixPsu;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParams() {
		return params;
	}

	public void setParams(Integer params) {
		this.params = params;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getControlLang() {
		return controlLang;
	}

	public void setControlLang(String controlLang) {
		this.controlLang = controlLang;
	}

	public String getFixModualLang() {
		return fixModualLang;
	}

	public void setFixModualLang(String fixModualLang) {
		this.fixModualLang = fixModualLang;
	}

	public String getFixPsu() {
		return fixPsu;
	}

	public void setFixPsu(String fixPsu) {
		this.fixPsu = fixPsu;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductParamsLang{" +
			"id=" + id +
			", parmas=" + params +
			", lang=" + lang +
			", controlLang=" + controlLang +
			", fixModuallLang=" + fixModualLang +
			", fixPsu=" + fixPsu +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}

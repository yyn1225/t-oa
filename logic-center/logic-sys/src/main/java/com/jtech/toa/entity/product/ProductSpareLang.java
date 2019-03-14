package com.jtech.toa.entity.product;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-11-28
 */
@TableName("absen_product_spare_lang")
public class ProductSpareLang extends Model<ProductSpareLang> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("spare")
	private Integer spare;
	@TableField("brand_lang")
	private String brandLang;
	@TableField("model_lang")
	private String modelLang;
	@TableField("description_lang")
	private String descriptionLang;
	@TableField("remark_lang")
	private Integer remarkLang;
	private String lang;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTIme;
	@TableField("type_lang")
	private String typeLang;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpare() {
		return spare;
	}

	public void setSpare(Integer spare) {
		this.spare = spare;
	}

	public String getBrandLang() {
		return brandLang;
	}

	public void setBrandLang(String brandLang) {
		this.brandLang = brandLang;
	}

	public String getModelLang() {
		return modelLang;
	}

	public void setModelLang(String modelLang) {
		this.modelLang = modelLang;
	}

	public String getDescriptionLang() {
		return descriptionLang;
	}

	public void setDescriptionLang(String descriptionLang) {
		this.descriptionLang = descriptionLang;
	}

	public Integer getRemarkLang() {
		return remarkLang;
	}

	public void setRemarkLang(Integer remarkLang) {
		this.remarkLang = remarkLang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTIme() {
		return updateTIme;
	}

	public void setUpdateTIme(Date updateTIme) {
		this.updateTIme = updateTIme;
	}

	public String getTypeLang() {
		return typeLang;
	}

	public void setTypeLang(String typeLang) {
		this.typeLang = typeLang;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductSpareLang{" +
			"id=" + id +
			", spare=" + spare +
			", brandLang=" + brandLang +
			", modelLang=" + modelLang +
			", descriptionLang=" + descriptionLang +
			", remarkLang=" + remarkLang +
			", lang=" + lang +
			", createTime=" + createTime +
			", updateTime=" + updateTIme +
			", typeLang=" + typeLang +
			"}";
	}
}

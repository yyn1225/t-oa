package com.jtech.toa.entity.product;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
@TableName("absen_product_lang")
public class ProductLang extends Model<ProductLang> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("product")
	private Integer product;
	private String lang;
	@TableField("product_lang")
	private String productLang;
	@TableField("remark_lang")
	private String remarkLang;
	@TableField("color_lang")
	private String colorLang;
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

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getProductLang() {
		return productLang;
	}

	public void setProductLang(String productLang) {
		this.productLang = productLang;
	}

	public String getRemarkLang() {
		return remarkLang;
	}

	public void setRemarkLang(String remarkLang) {
		this.remarkLang = remarkLang;
	}

	public String getColorLang() {
		return colorLang;
	}

	public void setColorLang(String colorLang) {
		this.colorLang = colorLang;
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
		return "ProductLang{" +
			"id=" + id +
			", lang=" + lang +
			", productLang=" + productLang +
			", remarkLang=" + remarkLang +
			", colorLang=" + colorLang +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}

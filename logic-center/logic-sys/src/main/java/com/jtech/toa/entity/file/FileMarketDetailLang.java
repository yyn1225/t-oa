package com.jtech.toa.entity.file;

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
 * @since 2017-12-23
 */
@TableName("absen_file_market_detail_lang")
public class FileMarketDetailLang extends Model<FileMarketDetailLang> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer detail;
	private String lang;
	@TableField("name_lang")
	private String nameLang;
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

	public Integer getDetail() {
		return detail;
	}

	public void setDetail(Integer detail) {
		this.detail = detail;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getNameLang() {
		return nameLang;
	}

	public void setNameLang(String nameLang) {
		this.nameLang = nameLang;
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
		return "FileMarketDetailLang{" +
			"id=" + id +
			", detail=" + detail +
			", lang=" + lang +
			", nameLang=" + nameLang +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}

package com.jtech.toa.user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2017-10-19
 */
@TableName("absen_org_user_lang")
public class UserLang extends Model<UserLang> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer user;
	private String language;
	@TableField("name_lang")
	private String nameLang;
	@TableField("nick_name_lang")
	private String nickNameLang;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNameLang() {
		return nameLang;
	}

	public void setNameLang(String nameLang) {
		this.nameLang = nameLang;
	}

	public String getNickNameLang() {
		return nickNameLang;
	}

	public void setNickNameLang(String nickNameLang) {
		this.nickNameLang = nickNameLang;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserLang{" +
			"id=" + id +
			", user=" + user +
			", language=" + language +
			", nameLang=" + nameLang +
			", nickNameLang=" + nickNameLang +
			"}";
	}
}

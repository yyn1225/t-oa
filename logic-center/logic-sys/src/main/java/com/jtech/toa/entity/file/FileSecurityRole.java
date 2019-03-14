package com.jtech.toa.entity.file;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtech
 * @since 2017-11-11
 */
@TableName("absen_file_security_role")
public class FileSecurityRole extends Model<FileSecurityRole> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Long file;
	private Integer role;
    /**
     * 1.可下载；2可查看；3可分享；4可删除；5可重命名
     */
	private Integer security;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getFile() {
		return file;
	}

	public void setFile(Long file) {
		this.file = file;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getSecurity() {
		return security;
	}

	public void setSecurity(Integer security) {
		this.security = security;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "FileSecurityRole{" +
			"id=" + id +
			", file=" + file +
			", role=" + role +
			", security=" + security +
			"}";
	}
}

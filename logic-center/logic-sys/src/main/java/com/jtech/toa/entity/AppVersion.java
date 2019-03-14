package com.jtech.toa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jtech
 * @since 2018-04-09
 */
@TableName("app_version")
public class AppVersion extends Model<AppVersion> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 系统类型  iOS : 1, Android:2
	 */
	private Integer type;
	/**
	 * 当前版本号
	 */
	private Integer version;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 应用更新信息
	 */
	@TableField("update_info")
	private String updateInfo;
	/**
	 * 应用下载url
	 */
	private String path;
	/**
	 * 是否有新版本。true：有，false：没有
	 */
	private Boolean update;
	/**
	 * 是否强制更新
	 */
	@TableField("must_update")
	private Boolean mustUpdate;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public Boolean getMustUpdate() {
		return mustUpdate;
	}

	public void setMustUpdate(Boolean mustUpdate) {
		this.mustUpdate = mustUpdate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AppVersion{" +
				"id=" + id +
				", type=" + type +
				", version=" + version +
				", status=" + status +
				", updateInfo=" + updateInfo +
				", path=" + path +
				", update=" + update +
				", mustUpdate=" + mustUpdate +
				"}";
	}

	public AppVersion(){

	}
	public AppVersion(Boolean update, Boolean mustUpdate, Integer type, String updateInfo, Integer version){
		this.update = update;
		this.mustUpdate = mustUpdate;
		this.type = type;
		this.updateInfo = updateInfo;
		this.version = version;
	}
}


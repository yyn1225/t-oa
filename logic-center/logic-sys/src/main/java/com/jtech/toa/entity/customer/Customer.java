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

package com.jtech.toa.entity.customer;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ruili
 * @since 2017-10-25
 */
@TableName("absen_sales_customer")
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	private String website;
	@TableField("ticker_symbol")
	private String tickerSymbol;
	private Integer parent;
	private Integer employees;
	private String ownership;
    /**
     * 字典
     */
	private String type;
    /**
     * 字典
     */
	@TableField("account_type")
	private String accountType;
	@TableField("account_number")
	private String accountNumber;
	private String location;
	private String phone;
	private String fax;
	private String email;
	private Integer rating;
	private Long revenue;
	@TableField("sic_code")
	private String sicCode;
	private Integer billing;
	private Integer shipping;
	private String description;
	private Integer status;
	@TableField("delete_able")
	private Integer deleteAble;
	@TableField("crm_id")
	private String crmId;
	@TableField("smowner_id")
	private String smownerId;

	public interface Status {
		/**
		 * 有效
		 */
		public static final Integer EFFECTIVE = 1;
		/**
		 * 无效
		 */
		public static final Integer INVALID = 0;
	}
	public interface DeleteAble {
		/**
		 * 允许删除
		 */
		public static final Integer NO = 0;
		/**
		 * 不允许删除
		 */
		public static final Integer YES = 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getEmployees() {
		return employees;
	}

	public void setEmployees(Integer employees) {
		this.employees = employees;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public String getSicCode() {
		return sicCode;
	}

	public void setSicCode(String sicCode) {
		this.sicCode = sicCode;
	}

	public Integer getBilling() {
		return billing;
	}

	public void setBilling(Integer billing) {
		this.billing = billing;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleteAble() {
		return deleteAble;
	}

	public void setDeleteAble(Integer deleteAble) {
		this.deleteAble = deleteAble;
	}

	public String getCrmId() {
		return crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public String getSmownerId() {
		return smownerId;
	}

	public void setSmownerId(String smownerId) {
		this.smownerId = smownerId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Customer{" +
			"id=" + id +
			", name=" + name +
			", website=" + website +
			", tickerSymbol=" + tickerSymbol +
			", parent=" + parent +
			", employees=" + employees +
			", ownership=" + ownership +
			", type=" + type +
			", accountType=" + accountType +
			", accountNumber=" + accountNumber +
			", location=" + location +
			", phone=" + phone +
			", fax=" + fax +
			", email=" + email +
			", rating=" + rating +
			", revenue=" + revenue +
			", sicCode=" + sicCode +
			", billing=" + billing +
			", shipping=" + shipping +
			", description=" + description +
			", status=" + status +
			", crmId=" + crmId +
			"}";
	}
}

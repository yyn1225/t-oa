package com.jtech.toa.entity.mail;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 邮件发送的文件
 * </p>
 *
 * @author jtech
 * @since 2018-04-14
 */
@TableName("absen_mail_file")
public class MailFile extends Model<MailFile> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 预览地址
     */
	private String url;
	/**
	 * 文件名称
	 */
	private String name;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 过期时间
     */
	@TableField("expired_time")
	private Date expiredTime;
	/**
	 * 邮件发送者
	 */
	@TableField("send_mail")
	private String sendMail;
	/**
	 * 邮件接收者
	 */
	@TableField("accept_mail")
	private String acceptMail;
	/**
	 * 邮件标题
	 */
	@TableField("mail_subject")
	private String mailSubject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSendMail() {
		return sendMail;
	}

	public void setSendMail(String sendMail) {
		this.sendMail = sendMail;
	}

	public String getAcceptMail() {
		return acceptMail;
	}

	public void setAcceptMail(String acceptMail) {
		this.acceptMail = acceptMail;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MailFile{" +
			"id=" + id +
			", url=" + url +
			", createTime=" + createTime +
			", expiredTime=" + expiredTime +
			"}";
	}
}

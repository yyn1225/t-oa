/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers, if any.
 */

package com.jtech.toa.config.properties;

import com.google.common.base.MoreObjects;
import com.google.common.io.Files;

import com.xiaoleilu.hutool.io.FileUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Component
@ConfigurationProperties(prefix = OaProperties.PREFIX)
public class OaProperties {

    static final String PREFIX = "oa";
    private static final Logger LOGGER = LoggerFactory.getLogger(OaProperties.class);
    /**
     * 开启验证码输入
     */
    private Boolean kaptchaOpen = false;

    private Boolean syncOa;

    /**
     * 系统资源文件（上传等）的文件存储路径
     */
   private String mediaPath;

    /**
     * session 失效时间（默认为30分钟 单位：秒）
     */
    private Integer sessionInvalidateTime = 30 * 60;

    /**
     * session 验证失效时间（默认为15分钟 单位：秒）
     */
    private Integer sessionValidationInterval = 15 * 60;

    /**
     * 是否启用Shiro，默认不启用
     */
    private boolean shiro = false;
    /**
     * TOKEN过期时间
     */
    private int tokenExpiresDay = 30;
    /**
     * 附件资源访问地址
     */
    private String mediaUrl = "http://127.0.0.1:8888/media/";

    /**
     * 邀请地址
     */
    private String mjoinUrl = "";

    /**
     * 是否开启短信发送功能
     */
    private boolean sms;

    /**
     * 一步线程的数量
     */
    private int asyncMaxPoolSize;

    /**
     * 邮件文件过期时长
     */
    private long mailExpiredTime = 10*60;

    /**
     * 部署服务器路径
     */
    private String deployServerPath;

    public int getAsyncMaxPoolSize() {
        return asyncMaxPoolSize;
    }

    public void setAsyncMaxPoolSize(int asyncMaxPoolSize) {
        this.asyncMaxPoolSize = asyncMaxPoolSize;
    }

    public String getMediaPath() {
        //如果没有写文件上传路径,保存到临时目录
        if (StringUtils.isEmpty(mediaPath)) {
            this.mediaPath = System.getProperty("user.dir") + File.separator + "media" + File.separator;
        } else {
            //判断有没有结尾符,没有得加上
            if (!mediaPath.endsWith(File.separator)) {
                mediaPath = mediaPath + File.separator;
            }
            boolean haveCreatePath = FileUtil.exist(mediaPath);
            //判断目录存不存在,不存在得加上
            if (!haveCreatePath) {
                File file = new File(mediaPath + "cp.txt");
                try {
                    Files.createParentDirs(file);
                } catch (IOException e) {
                    LOGGER.error("create meida path is error! the media path is {}", mediaPath, e);
                    this.mediaPath = System.getProperty("java.io.tmpdir");
                }
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The media path is {}", this.mediaPath);
            }
        }
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public boolean getSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public Boolean getKaptchaOpen() {
        return kaptchaOpen;
    }

    public void setKaptchaOpen(Boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public Integer getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Integer sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public boolean isShiro() {
        return shiro;
    }

    public boolean getShiro() {
        return shiro;
    }

    public void setShiro(boolean shiro) {
        this.shiro = shiro;
    }

    public int getTokenExpiresDay() {
        return tokenExpiresDay;
    }

    public void setTokenExpiresDay(int tokenExpiresDay) {
        this.tokenExpiresDay = tokenExpiresDay;
    }


    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMjoinUrl() {
        return mjoinUrl;
    }

    public void setMjoinUrl(String mjoinUrl) {
        this.mjoinUrl = mjoinUrl;
    }

    public long getMailExpiredTime() {
        return mailExpiredTime;
    }

    public void setMailExpiredTime(long mailExpiredTime) {
        this.mailExpiredTime = mailExpiredTime;
    }

    public String getDeployServerPath() {
        return deployServerPath;
    }

    public void setDeployServerPath(String deployServerPath) {
        this.deployServerPath = deployServerPath;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("kaptchaOpen", kaptchaOpen)
                .add("mediaPath", mediaPath)
                .add("sessionInvalidateTime", sessionInvalidateTime)
                .add("sessionValidationInterval", sessionValidationInterval)
                .add("shiro", shiro)
                .add("tokenExpiresDay", tokenExpiresDay)
                .add("mediaUrl", mediaUrl)
                .add("mjoinUrl", mjoinUrl)
                .add("sms", sms)
                .add("asyncMaxPoolSize", asyncMaxPoolSize)
                .toString();
    }
}

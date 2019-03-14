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

package com.jtech.toa.config.web;

import com.google.common.base.MoreObjects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     工作应用相关的配置
 * </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Component
@ConfigurationProperties(prefix = "oa.workapp")
public class WorkAppProp {

    private String viewlink;



    //fixed:修复地址从配置文件获取
    private String microurl;

    //fixed:版本从配置文件获取
    private String version;

    private String ieasurl;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("viewlink", viewlink)
                .add("microurl", microurl)
                .add("version", version)
                .toString();
    }

    public String getViewlink() {
        return viewlink;
    }

    public void setViewlink(String viewlink) {
        this.viewlink = viewlink;
    }

    public String getMicrourl() {
        return microurl;
    }

    public void setMicrourl(String microurl) {
        this.microurl = microurl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIeasurl() {
        return ieasurl;
    }

    public void setIeasurl(String ieasurl) {
        this.ieasurl = ieasurl;
    }
}

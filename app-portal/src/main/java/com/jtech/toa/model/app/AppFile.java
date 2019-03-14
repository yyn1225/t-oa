package com.jtech.toa.model.app;

import java.util.Date;

import lombok.Data;

import com.jtech.toa.entity.file.File;

/**
 * <p>app端文件实体类</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppFile {
    private int id;
    private File details;
    private int market;
    private Integer classify;
    private int types;
    private String extend;
    private Date uploadTime;
    private String service;
    private int status;
    private String names;
    private long size;
    private String local;

    public AppFile(File file) {
        this.id = file.getId();
        this.extend = file.getExtend();
        this.market = null == file.getMarket() ? 0 : file.getMarket();
        this.types = null == file.getType() ? 0 : file.getType();
        this.service = file.getUrl();
        this.uploadTime = file.getUploadTime();
        this.classify = null == file.getCategory() ? 0 : file.getCategory();
        this.status = 1;
        this.names=file.getName();
        this.size = file.getSize();
    }
}

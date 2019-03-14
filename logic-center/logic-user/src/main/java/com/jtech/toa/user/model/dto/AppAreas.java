/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.user.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AppAreas {
    private int id;
    private String name;
    private String code;
//    private String nameLang;
    private String unit;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifyUserid;
    /**
     * 同步状态
     */
    private int syncState;
    /**
     * 同步时间
     */
    private Date syncTime;
    /**
     * 同步人
     */
    private String syncUserid;
    /**
     * 同步异常
     */
    private String syncException;
}

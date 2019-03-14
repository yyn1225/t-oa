/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import java.util.Date;

import lombok.Data;

@Data
public class AppSpares {
    private int id;
    private String brand;
    private String model;
    private String material;
    private String description;
    private String type;
    private String unit;
    private int classify;

//    /**
//     * 创建时间
//     */
//    private Date createTime;
//    /**
//     * 创建人
//     */
//    private String createUserid;
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

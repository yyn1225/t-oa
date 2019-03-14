package com.jtech.toa.model.dto.products;

import java.util.Date;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppSpare {
    private int id;
    private String brand;
    private String model;
    private String material;
    private String description;
    private String type;
    private String unit;
    private String classify;
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

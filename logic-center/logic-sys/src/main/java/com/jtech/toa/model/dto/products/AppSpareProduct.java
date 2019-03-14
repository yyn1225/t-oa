package com.jtech.toa.model.dto.products;

import java.math.BigDecimal;
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
public class AppSpareProduct {
    private int id;
    private int product;
    private int spare;
    private String standard;
    private String type;
    private BigDecimal countsTwo;
    private String spelTwo;
    private int calTypeTwo;
    private BigDecimal countsThree;
    private String spelThree;
    private int calTypeThree;
    private BigDecimal countsFour;
    private String spelFour;
    private int calTypeFive;
    private BigDecimal countsFive;
    private String spelFive;
    private int calTypeFour;

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

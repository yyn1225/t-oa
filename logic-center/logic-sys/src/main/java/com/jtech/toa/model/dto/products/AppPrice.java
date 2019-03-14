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
public class AppPrice {
    private int id;
    /**
     * 物料编码
     */
    private String partNo;
    /**
     * 物料描述
     */
    private String description;
    /**
     * 类型
     */
    private String type;
    /**
     * 报价区域类型
     */
    private String parttype;
    /**
     * 货币单位
     */
    private String currency;
    /**
     * 成本价格
     */
    private long cost;
    /**
     * 区域
     */
    private String area;
    /**
     * 区域指导价格
     */
    private long price;
    /**
     * 单位
     */
    private String unit;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 是否自动带出
     */
    private String standard;
    /**
     * 两年数量
     */
    private String countsTwo;
    /**
     * 两年的计算公式
     */
    private String spelTwo;
    /**
     * 两年的计算方式
     */
    private String calTypeTwo;
    /**
     * 三年数量
     */
    private String countsThree;
    /**
     * 三年的计算公式
     */
    private String spelThree;
    /**
     * 三年的计算方式
     */
    private String calTypeThree;
    /**
     * 四年数量
     */
    private String  countsFour;
    /**
     * 四年的计算公式
     */
    private String spelFour;
    /**
     * 五年的计算方式
     */
    private String calTypeFive;
    /**
     * 五年数量
     */
    private String countsFive;
    /**
     * 五年计算公式
     */
    private String spelFive;
    /**
     * 四年的计算方式
     */
    private String calTypeFour;


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

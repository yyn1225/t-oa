/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import java.io.Serializable;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class SkimProductDto implements Serializable {

    private static final long serialVersionUID = 0L;
    /** 产品型号**/
    private String productSeries;
    /** 配置**/
    private String productConfiguration;
    /** 认证**/
    private String certification;
    /** 成品料号**/
    private String partNo;
    /** 上市时间**/
    private String releaseTime;
    /** 更新时间**/
    private String updateTime;
    /** 产品类型**/
    private String productType;
    /** 产品状态**/
    private String productStatus;
    /** 标准颜色**/
    private String color;
    /** 间距**/
    private String pitch;
    /** 标配控制系统**/
    private String control;
    /** 校正**/
    private String calibration;
    /** 智能监控&智慧模组**/
    private String intelligent;
    /** 吊装数量**/
    private String rigging;
    /** 落地**/
    private String stack;
    /** 前安装**/
    private String front;
    /** 模组**/
    private String fixModual;
    /** 电源&其它**/
    private String fixPsu;
    /** 箱体宽**/
    private String boxWidth;
    /** 箱体高**/
    private String boxHeight;
    /** 箱体厚**/
    private String boxThickness;
    /** 单箱重量**/
    private String boxWeight;
    /** 箱体类型**/
    private String boxType;
    /** 模组宽**/
    private String modualWidth;
    /** 模组高**/
    private String modualHeight;
    /** 模组重量**/
    private String modualWeight;
    /** 防护等级**/
    private String ipRating;
    /** 亮度**/
    private String brightness;
    /** 对比度**/
    private String contrastRatio;
    /** 灰度等级**/
    private String grayScale;
    /** 刷新率**/
    private String refresh;
    /** 可视角度**/
    private String viewing;
    /** 功耗最大**/
    private String powerMax;
    /** 功耗平均**/
    private String powerAvg;
    /** LED类型**/
    private String ledType;
    /** 驱动方式**/
    private String drivingType;
    /** 电源**/
    private String psu;
    /** 标准带载 220V**/
    private String carryHigh;
    /** 标准带载 110V**/
    private String carryLower;
    /** 接收卡**/
    private String receivedCard;

}

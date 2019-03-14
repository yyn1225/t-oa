/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ProductImportDto implements Serializable {
    private static final long serialVersionUID = -3818453501089787141L;

    private Integer id;
    @Excel(name="产品线")
    private String line;
    @Excel(name="系列")
    private String seriesParent;
    @Excel(name="产品")
    private String series;
    @Excel(name="箱体料号")
    private String box;
    @Excel(name="描述")
    private String state;
    @Excel(name="配置")
    private String configuration;
    @Excel(name="认证")
    private String certification;
    @Excel(name="产品类型")
    private String type;
    @Excel(name="主推")
    private String featured;
    @Excel(name="状态")
    private String status;
    @Excel(name="标准颜色")
    private String color;
    @Excel(name="标配控制系统")
    private String control;
    @Excel(name="校正")
    private String calibration;
    @Excel(name="吊装数量")
    private String rigging;
    @Excel(name="智能控制")
    private String intelligent;
    @Excel(name="落地")
    private String stack;
    @Excel(name="前安装")
    private String front;
    @Excel(name="模组维护方式")
    private String fixModual;
    @Excel(name="电源维护方式")
    private String fixPsu;
    @Excel(name="防护等级")
    private String ipRating;
    @Excel(name="亮度")
    private String brightness;
    @Excel(name="对比度")
    private String contrastRatio;
    @Excel(name="灰度")
    private String grayScale;
    @Excel(name="刷新率")
    private String refresh;
    @Excel(name="视角范围")
    private String viewing;
    @Excel(name="最大功率")
    private String powerMax;
    @Excel(name="平均功率")
    private String powerAvg;
    @Excel(name="驱动IC")
    private String drivingIc;
    @Excel(name="驱动方式")
    private String drivingType;
    @Excel(name="电源")
    private String psu;
    @Excel(name="电源原功率")
    private String psuPower;
    @Excel(name="电源数量")
    private String psuCount;
    @Excel(name="220V带载方式")
    private String standardCarryLower;
    @Excel(name="110V带载方式")
    private String standardCarryHigh;
}

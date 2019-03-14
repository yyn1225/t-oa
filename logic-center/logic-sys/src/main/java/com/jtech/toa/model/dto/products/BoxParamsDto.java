package com.jtech.toa.model.dto.products;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>箱体参数数据阐述对象</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @date 2018/8/1 15:12
 * @since JDK 1.8
 */
@Data
public class BoxParamsDto {
    private BigDecimal boxWidth;
    private BigDecimal boxHeight;
    private BigDecimal boxThickness;
    private Integer boxTransversePix;
    private Integer boxPortraitPix;
    private Integer boxTransverseCount;
    private Integer boxPortraitCount;
    private BigDecimal boxWeight;
    private Integer powerMax;
    private Integer powerAvg;
    private String boxType;
}

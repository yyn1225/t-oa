/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.imports;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class StandardImportDto {
    @Excel(name = "产品料号")
    private String partNo;
    @Excel(name = "备件物料号")
    private String spareNo;
    @Excel(name = "类型")
    private String type;
    @Excel(name = "名称")
    private String model;
    @Excel(name = "描述")
    private String description;
    @Excel(name="建议选择")
    private String suggest;
    @Excel(name = "产品")
    private String seriesName;

    private Integer standard;
    @Excel(name = "2年质保数量")
    private BigDecimal twoYearsCount;
    @Excel(name = "3年质保数量")
    private BigDecimal threeYearsCount;
    @Excel(name = "4年质保数量")
    private BigDecimal fourYearsCount;
    @Excel(name = "5年质保数量")
    private BigDecimal fiveYearsCount;
    @Excel(name = "数量计算方式")
    private String calType;
    @Excel(name = "表达式")
    private String expression;

}

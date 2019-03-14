package com.jtech.toa.model.dto.imports;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p>通用配件导入DTO类</p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class SpareImportDto implements Serializable {

    private static final long serialVersionUID = -3818453501089787141L;

    private Integer id;

    @Excel(name="品牌")
    private String brand;

    @Excel(name="名称")
    private String model;

    @Excel(name="料号")
    private String material;

    @Excel(name="物料描述")
    private String description;

    @Excel(name="备注")
    private String remark;

    @Excel(name="单位")
    private String unit;

    @Excel(name="类型")
    private String type;

}

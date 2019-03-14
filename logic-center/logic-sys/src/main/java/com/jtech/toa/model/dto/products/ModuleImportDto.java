package com.jtech.toa.model.dto.products;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p>模组导入DTO类</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ModuleImportDto {
    @Excel(name="物料号")
    private String scnNo;

    @Excel(name="高度(mm)")
    private String height;

    @Excel(name="宽度(mm)")
    private String width;

    @Excel(name="横向分辨率")
    private String transverse;

    @Excel(name="纵向分辨率")
    private String portrait;

    @Excel(name="间距(mm)")
    private String pitch;

    @Excel(name="密度")
    private String density;

    @Excel(name="LED类型")
    private String configuration;

    @Excel(name="单个重量(KG)")
    private String weight;

    @Excel(name="物料描述")
    private String description;

    @Excel(name="备注")
    private String remark;
}

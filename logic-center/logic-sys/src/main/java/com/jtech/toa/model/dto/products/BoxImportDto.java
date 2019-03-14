package com.jtech.toa.model.dto.products;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class BoxImportDto {
    private int id;
    @Excel(name="物料号")
    private String scnNo;
    @Excel(name = "横向模组数量(个)")
    private String transverseCount;
    @Excel(name = "纵向模组数量(个)")
    private String portraitCount;
    @Excel(name = "单个重量(KG)")
    private String weight;
    @Excel(name = "横向分辨率")
    private String transversePix;
    @Excel(name="纵向分辨率")
    private String portraitPix;
    @Excel(name="高度(mm)")
    private String height;
    @Excel(name = "宽度(mm)")
    private String width;
    @Excel(name = "厚度(mm)")
    private String thickness;
    @Excel(name = "箱体类型")
    private String boxType;
    @Excel(name="模组料号")
    private String modualNo;
}

/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.entity.basic.Configuration;

import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Series;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * <p> </p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class BoxDto implements Serializable {

   private static final long serialVersionUID = -1L;
    private int id;
    private String scnNo;
    private Integer modual;
    private Integer modual2;
    private Integer transverseCount;
    private Integer portraitCount;
    private BigDecimal weight;
    private Integer transversePix;
    private Integer portraitPix;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal thickness;
    private String boxType;
    private String modualNo;
    private String modualNo2;
    private String name;
    private String remark;
    private String certificationName;

    public Box toBox() {
        Box box =new Box();
        box.setId(id);
        box.setScnNo(scnNo);
        box.setModual(modual);
        if (modual2 != null) {
            box.setModual2(modual2);
        }
        box.setBoxType(boxType);
        box.setHeight(height);
        box.setWeight(weight);
        box.setThickness(thickness);
        box.setWidth(width);
        box.setTransverseCount(transverseCount);
        box.setPortraitCount(portraitCount);
        box.setTransversePix(transversePix);
        box.setPortraitPix(portraitPix);
        return box;
    }
}

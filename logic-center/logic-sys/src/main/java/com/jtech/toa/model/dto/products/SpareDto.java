/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.entity.product.SparePrice;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>备件DTO</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class SpareDto implements Serializable {

    private static final long serialVersionUID = -3818453501089787141L;

    private Integer id;
    private String brand;
    private String model;
    private Date executeTime;
    private String material;
    private String description;
    private String remark;
    private String unit;
    private String type;
    private Integer classify;

    private Integer priceId;
    private BigDecimal basePrice;
    private BigDecimal salePrice;
    private String priceUnit;

    public Spare toSpare() {
        Spare spare = new Spare();
        spare.setId(this.id);
        spare.setBrand(this.brand);
        spare.setModel(this.model);
        spare.setExecuteTime(this.executeTime);
        spare.setMaterial(this.material);
        spare.setDescription(this.description);
        spare.setRemark(this.remark);
        spare.setUnit(this.unit);
        spare.setType(this.type);
        spare.setClassify(this.classify);
        return spare;
    }

    public SparePrice toSparePrice() {
        SparePrice sparePrice = new SparePrice();
        sparePrice.setId(this.priceId);
        sparePrice.setPrice(this.basePrice);
        sparePrice.setSalePrice(this.salePrice);
        sparePrice.setUnit(this.priceUnit);
        return sparePrice;
    }
}

/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.product.Module;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ModuleDto implements Serializable {

    private static final long serialVersionUID = -3818453501089787141L;

    private Integer id;
    private String scnNo;
    private BigDecimal height;
    private BigDecimal width;
    private Integer transverse;
    private Integer portrait;
    private BigDecimal pitch;
    private Integer density;
    private Integer lamp;
    private String configuration;
    private BigDecimal weight;

    public Module toModule() {
        Module module = new Module();
        module.setId(this.id);
        module.setScnNo(this.scnNo);
        module.setHeight(this.height);
        module.setWidth(this.width);
        module.setTransverse(this.transverse);
        module.setPortrait(this.portrait);
        module.setPitch(this.pitch);
        module.setDensity(this.density);
        module.setLamp(this.lamp);
        module.setConfiguration(this.configuration);
        module.setWeight(this.weight);
        return module;
    }
}

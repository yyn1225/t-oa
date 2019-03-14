/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.jtech.toa.entity.product.Module;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AppModules {
    private int id;
    private String scnNo;
    private BigDecimal height;
    private BigDecimal width;
    private int transverse;
    private int portrait;
    private BigDecimal pitch;
    private int density;
    private int lamp;
    private String configuration;
    private BigDecimal weight;
    private String description;
    private String configurationLang;

    public AppModules(Module m){
        this.id = m.getId();
        this.scnNo=m.getScnNo();
        this.height=m.getHeight();
        this.width= m.getWidth();
        this.transverse = m.getTransverse();
        this.portrait=m.getPortrait();
        this.pitch = m.getPitch();
        this.density = m.getDensity();
        this.lamp = m.getLamp();
        this.configuration = m.getConfiguration();
        this.description = m.getDescription();
        this.configurationLang="";
    }

    public AppModules(){}
}

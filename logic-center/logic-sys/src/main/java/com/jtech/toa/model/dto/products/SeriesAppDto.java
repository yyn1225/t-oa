/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import java.util.Date;
import java.util.Map;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class SeriesAppDto {
    private int id;
    private String name;
    private String line;
    private int creater;
    private Date createTime;
    private String image;
    private int parent;
    private int type;
    private Map<String,String> nameLang;
    private Map<String,String> remarkLang;
}

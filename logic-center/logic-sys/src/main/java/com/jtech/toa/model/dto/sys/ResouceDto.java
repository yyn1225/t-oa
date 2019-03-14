/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.sys;

import com.jtech.toa.entity.system.Resource;

import java.util.List;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class ResouceDto {
    private int id;
    private int type;
    private String name;
    private String code;
    private String url;
    private String icon;
    private Integer parent;
    private String parentName;
    private List<ResouceDto> children;

    public Resource toResouce(){
        Resource resource = new Resource();
        resource.setId(id);
        resource.setName(name);
        resource.setCode(code);
        resource.setUrl(url);
        resource.setIcon(icon);
        resource.setParent(parent);
        return  resource;
    }
}


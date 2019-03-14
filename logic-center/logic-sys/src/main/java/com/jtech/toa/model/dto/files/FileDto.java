/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.files;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class FileDto {
    private Integer id;
    private String name;
    private int type;
    private String extend;
    private String url;
    private BigDecimal size;
    private String path;
    private String packageName;
    private String seriesName;
    private Integer parent;
    private Date uploadTime;
    private Integer userSecurity;
    private Integer roleSecurity;
    private Integer securitys;
    private Integer market;
    private String detailName;
    private Integer category;
}

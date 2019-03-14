/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.sys;

import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class RoleDto {
    private int id;
    private String name;
    private String role;
    private int status;
    private String username;
    private Date createTime;
}

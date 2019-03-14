/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.sys;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class RoleUserSelect2Dto {
    private Integer id;
    private Integer userId;
    private Integer roleId;
    private String userName;
    private String roleName;
}

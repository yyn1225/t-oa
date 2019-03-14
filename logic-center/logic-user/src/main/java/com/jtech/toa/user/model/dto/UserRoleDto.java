package com.jtech.toa.user.model.dto;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class UserRoleDto {
    private Integer userId;
    private String username;
    private Integer roleId;
    private String roleName;
}

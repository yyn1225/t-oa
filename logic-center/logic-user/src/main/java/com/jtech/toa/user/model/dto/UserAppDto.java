package com.jtech.toa.user.model.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author jtech tao.ding
 * @since 2017-07-11
 */
@Data
public class UserAppDto implements Serializable {

    private static final long serialVersionUID = -3818453501089787141L;

    private int userId;
    private String name;
    private String phone;
    private String language;
    private String langDefault;
    private int area;
    private String avatar;
    private String email;
    private String company;

}

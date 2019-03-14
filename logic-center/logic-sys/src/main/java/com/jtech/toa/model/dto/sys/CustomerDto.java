/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.sys;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class CustomerDto {
    private Integer id;
    private String name;
    private String tickerSymbol;
    private String ownership;
    private String type;
    private String accountType;
    private Integer employees;
    private String accountNumber;
    private String location;
    private String phone;
    private String rating;
    private String levelName;
    private Integer deleteAble;
    private String website;
    private String email;
}

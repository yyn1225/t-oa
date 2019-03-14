/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2018
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.constants;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author EE
 * @version 1.0
 * @since JDK 1.7
 */

@Data
public class AppMessage implements Serializable {
    private static final long serialVersionUID = -5490210200705551776L;

    private Integer errorCode;
    private String errorMessage;
    private Object data;
    private boolean success;
    private Integer httpStatus;
    private String throwable;
}

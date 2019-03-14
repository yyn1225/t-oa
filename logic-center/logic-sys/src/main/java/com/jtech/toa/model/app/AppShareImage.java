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

package com.jtech.toa.model.app;

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
public class AppShareImage implements Serializable {

    private static final long serialVersionUID = 8263074487598102695L;

    private String id;
    private String shareId;
    private String url;
    private String commentId;


}

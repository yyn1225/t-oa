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
import java.util.List;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author EE
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppShare implements Serializable{

    private static final long serialVersionUID = 6733579498246361557L;
    private String id;
    private String productId;
    private String productName;
    private String title;
    private String content;
    private String createUserId;
    private String createUserName;
    private String status;
    private String createTime;
    private List<AppShareImage> imageList;
    private List<AppShareComment> commentList;
}

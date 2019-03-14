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
public class AppShareComment implements Serializable{

    private static final long serialVersionUID = -6629590190051700969L;

    private String id;
    private String shareId;
    private String content;
    private String parentId;
    private List<AppShareImage> imageList;
    private String createUser;
    private String createTime;
    private List<AppShareSubComment> subCommentList;

}

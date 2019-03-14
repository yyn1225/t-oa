/*
 * Copyright (c)2012-2017 JingTong RDC(Research and Development Centre), Inc. All rights reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of JingTong RDC ,
 *         if any. The intellectual and technical concepts contained herein are proprietary
 *         to JingTong RDC and covered by China and Foreign Patents, patents in process,
 *         and are protected by trade secret or copyright law. Dissemination of this information
 *         or reproduction of this material is strictly forbidden unless prior written permission
 *         is obtained from JingTong RDC.
 */

package com.jtech.toa.ui;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class LayTableResponse<D> implements Serializable {

    private static final long serialVersionUID = -9130658083991531966L;
    /**
     * 状态码，0代表成功，其它失败
     */
    private int code;


    /**
     *  状态信息，一般可为空
     */
    private String msg;


    /**
     * 数据总量
     */
    private int count;


    /**
     * 数据，字段是任意的。
     */
    private List<D> data;

}

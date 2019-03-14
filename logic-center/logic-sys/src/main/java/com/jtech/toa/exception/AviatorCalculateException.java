/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers, if any.
 */

package com.jtech.toa.exception;

/**
 * <p>表达式计算错误. </p>
 *
 * @author yxcheng
 * @version 1.0
 * @since JDK 1.7
 */

public class AviatorCalculateException extends RuntimeException {

    private static final long serialVersionUID = -5509623297199319497L;

    public AviatorCalculateException() {
        super();
    }

    public AviatorCalculateException(String message) {
        super(message);
    }

    public AviatorCalculateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AviatorCalculateException(Throwable cause) {
        super(cause);
    }
}

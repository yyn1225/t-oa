/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.auth.exception;

/**
 * <p> TOken 没有传递的异常</p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class MissingTokenException extends Exception {
    private static final long serialVersionUID = 5592088235059008256L;

    public MissingTokenException() {
        super();
    }

    public MissingTokenException(String message) {
        super(message);
    }

    public MissingTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingTokenException(Throwable cause) {
        super(cause);
    }

    protected MissingTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

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

package com.jtech.toa.exception;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class LocalFileNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5967074516387905698L;

    public LocalFileNotFoundException() {
    }

    public LocalFileNotFoundException(String message) {
        super(message);
    }

    public LocalFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalFileNotFoundException(Throwable cause) {
        super(cause);
    }
}

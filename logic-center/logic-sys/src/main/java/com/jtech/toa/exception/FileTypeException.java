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
public class FileTypeException extends RuntimeException {
    private static final long serialVersionUID = -6861095175456453256L;
    private final String fileType;

    public FileTypeException(String fileType) {
        super();
        this.fileType = fileType;
    }

    public FileTypeException(String message, String fileType) {
        super(message);
        this.fileType = fileType;
    }

    public FileTypeException(String message, Throwable cause, String fileType) {
        super(message, cause);
        this.fileType = fileType;
    }

    public FileTypeException(Throwable cause, String fileType) {
        super(cause);
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}

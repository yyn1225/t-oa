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

package com.jtech.toa.constants;

import com.jtech.marble.error.ErrorCode;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public enum TokenCode implements ErrorCode {

    /**
     * token必须传入
     */
    TOKEN_IS_SPACE(100),
    /**
     * Token 已过期
     */
    TOKEN_IS_EXPRIED(101);

    private final int code;

    TokenCode(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}

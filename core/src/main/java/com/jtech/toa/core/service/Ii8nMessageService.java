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

package com.jtech.toa.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

import com.jtech.marble.StringPool;
import com.jtech.marble.error.ErrorCode;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class Ii8nMessageService {

    private final MessageSource messageSource;

    @Autowired
    public Ii8nMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(ErrorCode code) {
        return getMessage(code.getCode());
    }

    public String getMessage(int code) {
        return getMessage("code_" + code, null);
    }

    public String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return 配置内容文本
     */
    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, StringPool.EMPTY);
    }

    /**
     * @param code           ：对应messages配置的key.
     * @param args           : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return 配置内容文本
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}

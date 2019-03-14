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

package com.jtech.toa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import com.jtech.marble.listener.ConfigListener;
import com.jtech.marble.xss.XssFilter;

/**
 * <p> web 配置类</p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
public class AdminWebConfig {

//
//    /**
//     * xssFilter注册
//     */
//    @Bean
//    public FilterRegistrationBean xssFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
//        registration.addUrlPatterns("/*");
//        return registration;
//    }

    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    /**
     * ConfigListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigListener> configListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new ConfigListener());
    }




}

/*
 * Copyright (c)2012-2017 JingTong RDC(Research and Development Centre), Inc.
 * All rights reserved.
 * Used by permission.
 */

package com.jtech.toa.config.web;

import com.jtech.toa.config.context.SpringContextHolder;
import com.jtech.toa.config.properties.OaProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
public class ToaWebConfiguration extends WebMvcConfigurerAdapter {

    private final OaProperties oaProperties;

    @Autowired
    public ToaWebConfiguration(OaProperties oaProperties) {
        this.oaProperties = oaProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/media/**")
                .addResourceLocations("file:" + oaProperties.getMediaPath());
    }


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(mvcAsyncExecutor());
        configurer.setDefaultTimeout(-1);
    }

    @Bean
    public AsyncTaskExecutor mvcAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        final int asyncMaxPoolSize = oaProperties.getAsyncMaxPoolSize();
        if (asyncMaxPoolSize <= 0) {
            executor.setMaxPoolSize(10);
        } else {
            executor.setMaxPoolSize(asyncMaxPoolSize);
        }
        return executor;
    }
}

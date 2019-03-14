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

package com.jtech.toa.config.web;

import com.jtech.marble.freemarker.layout.BlockDirective;
import com.jtech.marble.freemarker.layout.ExtendsDirective;
import com.jtech.marble.freemarker.layout.OverrideDirective;
import com.jtech.marble.freemarker.layout.SuperDirective;
import com.jtech.marble.listener.ConfigListener;
import com.jtech.marble.shiro.freemarker.ShiroTags;
import com.jtech.toa.config.context.SpringContextHolder;
import com.jtech.toa.config.properties.OaProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
@DependsOn("configListenerRegistration")
public class FreemarkerConfig extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {


    private final OaProperties oaProperties;

    @Autowired
    public FreemarkerConfig(OaProperties oaProperties) {
        this.oaProperties = oaProperties;
    }

    @Override
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();

        Map<String, Object> sharedVariables = new HashMap<>();
        sharedVariables.put("block", new BlockDirective());

        sharedVariables.put("extends", new ExtendsDirective());
        sharedVariables.put("override", new OverrideDirective());
        sharedVariables.put("super", new SuperDirective());
        if (oaProperties.isShiro()) {
            sharedVariables.put("shiro", new ShiroTags());
        }
        final Map<String, String> conf = ConfigListener.getConf();
        sharedVariables.put("ctx", conf.get("ctx"));

        configurer.setFreemarkerVariables(sharedVariables);

        return configurer;
    }
}

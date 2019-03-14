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

package com.jtech.toa.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

import com.jtech.marble.listener.ConfigListener;
import com.jtech.marble.xss.XssFilter;
import com.jtech.toa.auth.handler.JwtInterceptor;
import com.jtech.toa.auth.handler.UserMethodArgumentResolver;

/**
 * <p> web 配置类</p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private final JwtInterceptor jwtInterceptor;
    private final UserMethodArgumentResolver userMethodArgumentResolver;

    @Value("${oa.media-path}")
    private String mediaPath;

    @Autowired
    public WebConfig(JwtInterceptor jwtInterceptor, UserMethodArgumentResolver userMethodArgumentResolver) {
        this.jwtInterceptor = jwtInterceptor;
        this.userMethodArgumentResolver = userMethodArgumentResolver;
    }

    /**
     * xssFilter注册
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
        registration.addUrlPatterns("/**");
        return registration;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true).maxAge(3600);
    }

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/offer/**")
                .addPathPatterns("/api/**")
                .addPathPatterns("/customer/**")
                .addPathPatterns("/api/customer/**")
                .addPathPatterns("/approval/**")
                .addPathPatterns("/file/**")
                .addPathPatterns("/awp/**")
                .addPathPatterns("/product/**")
                .addPathPatterns("/skimProduct/**")
                .addPathPatterns("/dashboard")
                .addPathPatterns("/organization/**")
                .addPathPatterns("/panel/rest/**")
                .addPathPatterns("/sp/tab")
                .addPathPatterns("/sp/rest/file")
                .addPathPatterns("/standard/rest/list")
                .addPathPatterns("/price/**")
                .addPathPatterns("/experience/**")
                .addPathPatterns("/upload/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/api/login","/api/captcha","/api/pwd/rest","/api/qcode",
                        "/api/islogin","/api/version/**","/mail/rest/preview",
                        "/file/view","/viewOfferFile");
        super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userMethodArgumentResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/OTA/**").addResourceLocations("file:"+ mediaPath);
    }

}

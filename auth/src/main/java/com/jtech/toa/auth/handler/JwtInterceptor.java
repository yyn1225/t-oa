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

package com.jtech.toa.auth.handler;

import com.google.common.base.Strings;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jtech.marble.StringPool;
import com.jtech.marble.web.RequestUtil;
import com.jtech.marble.web.cookie.Cookies;
import com.jtech.toa.auth.constants.WebConst;
import com.jtech.toa.auth.exception.MissingTokenException;
import com.jtech.toa.auth.service.IJwtTokenService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    //存放鉴权信息的Header名称，默认是Authorization
    public static final String HTTPHEADERNAME = "Authorization";
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtInterceptor.class);
    //鉴权信息的无用前缀，默认为空
    private static final String HTTPHEADERPREFIX = "Bearer:";
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private final IJwtTokenService jwtTokenService;

    @Autowired
    public JwtInterceptor(IJwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用， SpringMVC中的Interceptor拦截器是链式的，可以同时存在多个Interceptor，
     * 然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在 Controller方法调用之前调用。
     * SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返回值为false，
     * 当preHandle的返回值为false的时候整个请求就结束了。
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjMsImlzcyI6Imh0dHA6Ly94aC5qaW5nLXRvbmcuY29tIiwiZXhwIjoxNTE0NDUzMTY3LCJpYXQiOjE1MTE3NzQ3Njd9.EcNet9skZLVK3P8qkDaB1WfFvO5qGt69o8a8alTQkFs";
       String token;
        String appVersion = request.getHeader(WebConst.HTTP_HEADER_APP);

        String headToken = request.getHeader(HTTPHEADERNAME);
        if(!Strings.isNullOrEmpty(headToken) && StringUtils.startsWith(headToken, HTTPHEADERPREFIX)){
            token = StringUtils.replace(headToken, HTTPHEADERPREFIX, StringUtils.EMPTY);
        } else {
            final boolean ajax = RequestUtil.isAjax(request); // 是否为ajax
            Cookie tokenCookie = Cookies.getCookieByName(request, WebConst.REQUEST_CURRENT_KEY);
            if (tokenCookie == null) {
                if (ajax) {
                    throw new MissingTokenException();
                } else {
                    response.sendRedirect("/login");
                    return false;
                }
            }
            token = tokenCookie.getValue();
            appVersion = StringPool.EMPTY;
        }

        final DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtTokenService.verify(token);
        } catch (Exception e) {
            // fixed 修复token出现错误而错误界面
            LOGGER.error("the token {} is verify has error!", token, e);
            if (!Strings.isNullOrEmpty(appVersion)) {
                throw new TokenExpiredException("the token expried!");
            } else {
                response.sendRedirect("/login");
                return false;
            }
        }
        final Claim uidClaim = decodedJWT.getClaim(WebConst.JWT_CLAIM_UID);
        String userId;
        if (uidClaim.isNull()) {
            userId = StringPool.EMPTY;
        } else if (null != uidClaim.asString()) {
            userId = uidClaim.asString();
        } else if (null != uidClaim.asInt()) {
            userId = uidClaim.asInt() + "";
        } else {
            userId = StringPool.EMPTY;
        }
        if (StringUtils.equals(userId, StringPool.EMPTY)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        //如果token验证成功，将token对应的用户id存在request中，便于之后注入
        request.setAttribute(WebConst.REQUEST_CURRENT_KEY, userId);
        request.setAttribute(WebConst.HTTP_HEADER_LANGUAGE, decodedJWT.getClaim("language").asString());
        request.setAttribute(WebConst.HTTP_HEADER_APP, appVersion);
        return true;
    }
}

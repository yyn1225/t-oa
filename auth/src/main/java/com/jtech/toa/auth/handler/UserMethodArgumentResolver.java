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

import com.google.common.base.MoreObjects;
import com.google.common.primitives.Ints;

import com.jtech.toa.auth.constants.WebConst;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.auth.service.IUserAccountService;
import com.jtech.toa.user.entity.User;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * <p> 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(UserMethodArgumentResolver.class);
    private final IUserAccountService userAccountService;

    @Autowired
    public UserMethodArgumentResolver(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        return parameter.getParameterType().isAssignableFrom(RequestSubject.class)
                && parameter.hasParameterAnnotation(RequestUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory webDataBinderFactory
    ) throws Exception {
        //取出鉴权时存入的登录用户Id
        Object reqUserId = webRequest.getAttribute(WebConst.REQUEST_CURRENT_KEY, SCOPE_REQUEST);
        Object appVersion = webRequest.getAttribute(WebConst.HTTP_HEADER_APP, SCOPE_REQUEST);
        Object language = webRequest.getAttribute(WebConst.HTTP_HEADER_LANGUAGE,SCOPE_REQUEST);
        if (reqUserId != null) {
            int userId = MoreObjects.firstNonNull(Ints.tryParse(String.valueOf(reqUserId)), 0);
            //从数据库中查询并返回
            User loginUser = userAccountService.findByUserId(userId);
            if (loginUser != null) {
                RequestSubject.RequestSubjectBuilder requestUserBuilder = RequestSubject.builder()
                        .userName(loginUser.getName())
                        .id(loginUser.getId())
                        .phone(loginUser.getPhone())
                        .area(loginUser.getArea());
                if(appVersion != null && StringUtils.isNotEmpty(String.valueOf(appVersion))){
                    requestUserBuilder.app(true).appVersion(String.valueOf(appVersion));
                }
                requestUserBuilder.language(null==language?"zh":language.toString());
                return requestUserBuilder.build();
            }
            //有key但是得不到用户，抛出异常
            throw new MissingServletRequestPartException(WebConst.REQUEST_CURRENT_KEY);
        }
        //没有key就直接返回null
        return null;
    }
}

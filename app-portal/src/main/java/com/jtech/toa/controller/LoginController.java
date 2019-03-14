/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtech.marble.web.cookie.Cookies;
import com.jtech.toa.auth.service.IJwtTokenService;
import com.jtech.toa.constants.SsoSessionToken;
import com.jtech.toa.constants.SsoUserInfo;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.oa.OAWS_Service;
import com.jtech.toa.user.service.IUserService;

import static com.jtech.toa.auth.constants.WebConst.AUTO_EXPIRES;
import static com.jtech.toa.auth.constants.WebConst.REQUEST_CURRENT_KEY;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private static String SSOAuthorize = "/IAM/oauth/authorize";
    private static String SSOLogout = "/IAM/oauth/logout";
    private static String SSOMe = "/IAM/oauth/me";
    private static String SSOUserInfo = "/IAM/oauth/getUserInfo";

    @Value("${oa.internal-server-path}")
    private String internalServerPath;

    @Value("${oa.foreign-server-path}")
    private String foreignServerPath;

    @Value("${oa.connect-oa-crm-interface}")
    private boolean connectOaCrmInterface;

    private final IJwtTokenService jwtTokenService;
    private final IUserService userService;

    @Value("${oa.sso-client-id}")
    private String ssoClientId;
    @Value("${oa.sso-secret}")
    private String ssoSecret;
    @Value("${oa.sso-url}")
    private String ssoUrl;

    @Autowired
    public LoginController(IJwtTokenService jwtTokenService, IUserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String home(
            @CookieValue(value = REQUEST_CURRENT_KEY, required = false) String token,
            HttpServletRequest request
            , HttpServletResponse response,Model model
    ) {
        // 判断是否已经登录
        if (!Strings.isNullOrEmpty(token)) {
            try {
                DecodedJWT decodedJWT = jwtTokenService.verify(token);
                if (null != decodedJWT) {
                    return "redirect:/home";
                }
            } catch (JWTVerificationException e) {
                Cookies.deleteCookie(request, response, REQUEST_CURRENT_KEY);
                model.addAttribute("internal",internalServerPath);
                model.addAttribute("foreign",foreignServerPath);
                return "login";
            }
        }
        model.addAttribute("internal",internalServerPath);
        model.addAttribute("foreign",foreignServerPath);
        // 没有登录
        return "login";
    }

    /**
     * absen sso 登录逻辑验证
     * 此方法为sso调用的方法
     * @param token
     * @param request
     * @param accessToken sso acccessToken
     * @param language sso language(cn中文 en英文)
     * @param response
     * @param model
     * @return
     */
    @GetMapping("/SSOlogin")
    public String ssoLogin(
            @CookieValue(value = REQUEST_CURRENT_KEY, required = false) String token,
            HttpServletRequest request,String accessToken,String language,
            HttpServletResponse response,Model model) {
        LOGGER.debug("absen sso login validate begin...");
        LOGGER.debug("SSO AccessToken="+accessToken);
        LOGGER.debug("SSO language="+language);
        if(Strings.isNullOrEmpty(language)){
            //默认中文
            language = "zh";
        }else{
            if("cn".equals(language)){
                language = "zh";
            }
        }

        // 判断是否已经登录
        if (!Strings.isNullOrEmpty(token)) {
            try {
                DecodedJWT decodedJWT = jwtTokenService.verify(token);
                if (null != decodedJWT) {
                    Cookies.addCookie(response, "language", language, AUTO_EXPIRES);
                    return "redirect:/home";
                }
            } catch (JWTVerificationException e) {
                Cookies.deleteCookie(request, response, REQUEST_CURRENT_KEY);
                model.addAttribute("internal",internalServerPath);
                model.addAttribute("foreign",foreignServerPath);
                return "login";
            }
        }
        model.addAttribute("internal",internalServerPath);
        model.addAttribute("foreign",foreignServerPath);

        //accessToken为空（应用服务器将认证请求发送到统一认证服务）
        if(Strings.isNullOrEmpty(accessToken)){
            String redirectUrl = ssoUrl + SSOAuthorize+"?client_id="+ssoClientId;
            return "redirect:"+redirectUrl;
        }else{
            //accessToken不为空（根据accessToken获取sessionToken）
            try {
                String sessionTokenUrl = ssoUrl + SSOMe;
                String returnTokenJson = Request.Post(sessionTokenUrl).bodyForm(Form.form()
                        .add("accessToken",accessToken).build()).execute().returnContent().asString(Consts.UTF_8);
                SsoSessionToken sessionObject = JSON.parseObject(returnTokenJson,SsoSessionToken.class);
                //由统一认证返回的访问授权码
                String sessionToken = sessionObject.getOpenid();
                LOGGER.debug("get sessionToken:"+sessionToken);
                if(!Strings.isNullOrEmpty(sessionToken)){
                    //根据sessionToken获取用户信息
                    String userInfoUrl = ssoUrl + SSOUserInfo;
                    String returnUserInfoJson = Request.Post(userInfoUrl).bodyForm(Form.form()
                            .add("openId",sessionToken).add("oauth_consumer_key",ssoSecret).build())
                            .execute().returnContent().asString(Consts.UTF_8);
                    SsoUserInfo ssoUserInfo = JSON.parseObject(returnUserInfoJson,SsoUserInfo.class);
                    String ssouid = ssoUserInfo.getUid();
                    LOGGER.debug("sso user id:"+ssouid);
                    if(!Strings.isNullOrEmpty(ssouid)){
                        //处理登录业务逻辑
                        Optional<User> userOptional = userService.findByUsername(ssouid);
                        if (!userOptional.isPresent()) {
                            return "redirect:/login?msg=1&lang="+language;
                        }
                        User user = userOptional.get();
                        if(user.getDeleteFlag().equals(User.DeleteFlag.YES)){
                            return "redirect:/login?msg=2&lang="+language;
                        }
                        final String loginToken = jwtTokenService.createToken(user.getId(), 1, language);
                        Cookies.addCookie(response, REQUEST_CURRENT_KEY, loginToken, -1);
                        Cookies.addCookie(response, "language", language, AUTO_EXPIRES);
                        return "redirect:/home";
                    }
                }
            } catch (IOException e) {
                Cookies.deleteCookie(request, response, REQUEST_CURRENT_KEY);
                e.printStackTrace();
                return "login";
            }
        }
        // 没有登录
        return "login";
    }


    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("language") String language,
            @RequestParam(value = "remember", required = false) String remember,
            HttpServletResponse response,  RedirectAttributes redirectAttributes
    ) {
        if (StringUtils.isEmpty(username)) {
            return "redirect:/login?lang="+language;
        }
        redirectAttributes.addFlashAttribute("username", username);

        if (StringUtils.isEmpty(password)) {
            return "redirect:/login?lang="+language;
        }
        boolean rememberMe = !Strings.isNullOrEmpty(remember) && BooleanUtils.toBoolean(remember);
        Optional<User> userOptional = userService.findByUsername(username);
        if (!userOptional.isPresent()) {
            return "redirect:/login?msg=1&lang="+language;
        }
        User user = userOptional.get();
        if(user.getDeleteFlag().equals(User.DeleteFlag.YES)){
            return "redirect:/login?msg=2&lang="+language;
        }

        Map<String, String> loginParam = Maps.newHashMap();

        loginParam.put("lid",user.getWorkNo());
        loginParam.put("pwd",password);
        LOGGER.debug("connectOaCrmInterface:"+connectOaCrmInterface);
        if(connectOaCrmInterface) {
            OAWS_Service oaws_service = new OAWS_Service();
            boolean checkPassword = oaws_service.getOAWSPort()
                    .checkLogin(JSONObject.toJSONString(loginParam));
            if (!checkPassword) {
                return "redirect:/login?msg=3&lang=" + language;
            }
        }
        final String token = jwtTokenService.createToken(user.getId(), 1, language);
        if (rememberMe) {
            Cookies.addCookie(response, REQUEST_CURRENT_KEY, token, AUTO_EXPIRES);
        } else {
            Cookies.addCookie(response, REQUEST_CURRENT_KEY, token, -1);
        }
        Cookies.addCookie(response, "language", language, AUTO_EXPIRES);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookies.deleteCookie(request, response, REQUEST_CURRENT_KEY);
        return "redirect:/login";
    }
}

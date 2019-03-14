package com.jtech.toa.controller;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jtech.marble.web.cookie.Cookies;
import com.jtech.toa.auth.constants.WebConst;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.auth.service.IJwtTokenService;
import com.jtech.toa.service.system.IExhibitionService;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IUserService;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jtech.toa.auth.constants.WebConst.REQUEST_CURRENT_KEY;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RequestMapping("/")
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private final IJwtTokenService jwtTokenService;
    private final IUserService userService;
    private final IExhibitionService exhibitionService;

    @Autowired
    public HomeController(IJwtTokenService jwtTokenService, IUserService userService, IExhibitionService exhibitionService) {
        this.jwtTokenService = jwtTokenService;
        this.userService=userService;
        this.exhibitionService = exhibitionService;
    }

    @GetMapping(value = {"/", "/home"})
    public String home(
            @CookieValue(value = REQUEST_CURRENT_KEY, required = false) String token,
            Model model,
            HttpServletRequest request, HttpServletResponse response
    ) {
        final String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        Browser browser = UserAgent.parseUserAgentString(userAgent).getBrowser();
        switch (browser) {
            case IE5:
            case IE5_5:
            case IE6:
            case IE7:
            case IE8:
                return "redirect:/unsupport";
        }

        // 判断是否已经登录
        if (!Strings.isNullOrEmpty(token)) {
            DecodedJWT decodedJWT;
            try {
                decodedJWT = jwtTokenService.verify(token);
            } catch (JWTVerificationException e) {
                LOGGER.debug("the toekn has expire.", e);
                return "redirect:/login";
            }

            for (Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals("language")){
                    model.addAttribute("language",cookie.getValue());
                }
            }

            if (null != decodedJWT) {
                final Claim uidClaim = decodedJWT.getClaim(WebConst.JWT_CLAIM_UID);
                int userId = MoreObjects.firstNonNull(uidClaim.asInt(), 0);
                if (userId > 0) {
                    Optional<User> userInfo = userService.findByUserId(userId);
                    if(userInfo.isPresent()){
                        model.addAttribute("loginUser", userInfo.get());
                    }else{
                        model.addAttribute("loginUser", com.xiaoleilu.hutool.util.StrUtil.EMPTY_JSON);
                    }
                    return "main";
                } else {
                    // 此时的Cokie是有问题的，所以要清除掉
                    Cookies.deleteCookie(request, response, REQUEST_CURRENT_KEY);
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping(value = "dashboard")
    public String dashBoard(
            @RequestUser RequestSubject user,
            @CookieValue(value = REQUEST_CURRENT_KEY, required = false) String token,
            Model model,
            HttpServletRequest request, HttpServletResponse response
    ){
        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtTokenService.verify(token);
        } catch (JWTVerificationException e) {
            LOGGER.debug("the toekn has expire.", e);
            return "redirect:/login";
        }
        if(null==decodedJWT){
            return "redirect:/login";
        }
        final Claim uidClaim = decodedJWT.getClaim(WebConst.JWT_CLAIM_UID);
//        final Claim langClaim = decodedJWT.getClaim("language");
        int userId = MoreObjects.firstNonNull(uidClaim.asInt(), 0);
        if(userId==0){
            Cookies.deleteCookie(request, response, REQUEST_CURRENT_KEY);
        }
        Optional<User> userInfo = userService.findByUserId(userId);
        if(userInfo.isPresent()){
            model.addAttribute("loginUser", userInfo.get());
        }else{
            model.addAttribute("loginUser", com.xiaoleilu.hutool.util.StrUtil.EMPTY_JSON);
        }

        model.addAttribute("exhibitions", exhibitionService.findForPc());
        return "dashboard";
    }

    @GetMapping("/unsupport")
    public String unsupport() {
        return "unsupport";
    }
}

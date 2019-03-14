package com.jtech.toa.controller;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.crypto.SecureUtil;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.Const;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.oa.OAWS_Service;
import com.jtech.toa.user.service.IUserService;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    private IUserService userService;
    @Value("${oa.internal-server-path}")
    private String internalServerPath;

    @Value("${oa.foreign-server-path}")
    private String foreignServerPath;

    @Value("${oa.connect-oa-crm-interface}")
    private boolean connectOaCrmInterface;

    @Autowired
    public SecurityController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String viewLogin(
            @RequestParam(value = "username", required = false) String username,
            Model model
    ) {

        if (StringUtils.isNotEmpty(username)) {
            model.addAttribute("username", username);
        }
        model.addAttribute("internal",internalServerPath);
        model.addAttribute("foreign",foreignServerPath);
        return "login";
    }


    /**
     * 用户登录请求
     *
     * @param username           登录账号
     * @param password           登录密码
     * @param remember           是否记住密码
     * @param redirectAttributes falsh  attribute
     * @return 登录失败返回当前界面 如果登录成功，则跳转主界面.
     */
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("language") String language,
            @RequestParam(value = "remember", required = false) String remember,
            RedirectAttributes redirectAttributes
    ) {
        if (StringUtils.isEmpty(username)) {
            return "redirect:/login?lang="+language;
        }
        redirectAttributes.addFlashAttribute("username", username);

        if (StringUtils.isEmpty(password)) {
            return "redirect:/login?lang="+language;
        }

        Optional<User> userOpt = userService.findByUsername(username);
        if (!userOpt.isPresent()) {
            return "redirect:/login?msg=1&lang="+language;
        }
        if(userOpt.get().getDeleteFlag().equals(User.DeleteFlag.YES)){
            return "redirect:/login?msg=2&lang="+language;
        }

        if(userOpt.get().getStatus() == 0){
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "该账号已被禁用");
            return "redirect:/login";
        }


        Map<String, String> loginParam = Maps.newHashMap();

        loginParam.put("lid",userOpt.get().getWorkNo());
        loginParam.put("pwd",password);
        LOGGER.debug("connectOaCrmInterface:"+connectOaCrmInterface);
        if(connectOaCrmInterface) {
            OAWS_Service oaws_service = new OAWS_Service();
            boolean login = oaws_service.getOAWSPort().checkLogin(JSONObject.toJSONString(loginParam));
            if (!login) {
                redirectAttributes.addFlashAttribute(Const.MESSAGE, "用户名或密码错误");
                return "redirect:/login?msg=3&lang=" + language;
            }
        }

        final String md5password = SecureUtil.md5("123456");
        boolean rememberMe = !Strings.isNullOrEmpty(remember) && BooleanUtils.toBoolean(remember);

        UsernamePasswordToken token = new UsernamePasswordToken(username, md5password, rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
            ShiroUser user = (ShiroUser)currentUser.getPrincipal();
            // 我在这里做一个不该做的事情，将部门名称字段，存放语言编码。
            // 因为ShiroUser对象是被封装起来的，而我需要语言数据，所以拿个没被使用的字段存放一下
            user.setDeptName(language);
        } catch (UnknownAccountException uae) {
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "未知账户");
        } catch (IncorrectCredentialsException ice) {
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "密码不正确");
        } catch (LockedAccountException lae) {
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "用户名或密码不正确");
        }

        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            return "redirect:/";
        } else {
            token.clear();
            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if(null!=currentUser) {
            currentUser.logout();
        }
        return "redirect:/login";
    }
}

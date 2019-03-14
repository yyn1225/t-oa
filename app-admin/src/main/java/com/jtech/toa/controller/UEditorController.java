/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller;

import com.baidu.ueditor.ActionEnter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> </p>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/ueditor")
public class UEditorController {

    @GetMapping("/index")
    private String showPage() {
        return "ueditor/index";
    }

    @GetMapping(value = "/config")
    public ResponseEntity config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String exec = new ActionEnter(request, rootPath).exec();
        return ResponseEntity.ok(exec);
    }
}

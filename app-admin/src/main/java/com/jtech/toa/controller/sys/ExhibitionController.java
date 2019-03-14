package com.jtech.toa.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/config")
public class ExhibitionController {
    @GetMapping("/banner")
    public String list() {
        return "sys/exhibition_list";
    }

    @GetMapping("/banner/item")
    public String item() {
        return "sys/exhibition_item";
    }
}

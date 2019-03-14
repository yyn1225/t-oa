package com.jtech.toa.controller.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class FileSeriesController {
    @GetMapping("/file/series")
    public String index() {
        return "file/series/list";
    }
}

package com.jtech.toa.controller.importExcel;

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
public class ImportErrorController {
    @GetMapping("/import/error")
    public String index() {
        return "product/errorList";
    }
}

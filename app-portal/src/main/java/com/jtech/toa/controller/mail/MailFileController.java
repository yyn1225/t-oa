package com.jtech.toa.controller.mail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/mail")
public class MailFileController {
    @GetMapping("/expired")
    public String expired() {
        return "/mail/expired";
    }

    /**
     * 邮件发送编辑页
     *
     * @param fileIds 需要发送的文件ids
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String edit(String fileIds, Model model) {
        model.addAttribute("fileIds", fileIds);
        return "/mail/edit";
    }

}

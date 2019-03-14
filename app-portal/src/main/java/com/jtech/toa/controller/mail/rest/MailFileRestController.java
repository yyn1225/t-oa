package com.jtech.toa.controller.mail.rest;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.xiaoleilu.hutool.date.DateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtech.marble.StringPool;
import com.jtech.marble.error.ErrorModel;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.mail.MailFile;
import com.jtech.toa.model.vo.MailVo;
import com.jtech.toa.service.MailService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.mail.IMailFileService;
import com.jtech.toa.user.constants.UserCode;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/mail/rest")
public class MailFileRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    private final MailService mailService;
    private final IFileService fileService;
    private final IMailFileService mailFileService;
    private final OaProperties oaProperties;

    public MailFileRestController(
            final MailService mailService,
            final IFileService fileService,
            final IMailFileService mailFileService,
            final OaProperties oaProperties
    ) {
        this.mailService = mailService;
        this.fileService = fileService;
        this.mailFileService = mailFileService;
        this.oaProperties = oaProperties;
    }

    @PostMapping("/send")
    public ResponseEntity send(MailVo mailVo, String ids, HttpServletRequest request) {

        try {
            List<Integer> idList = JSON.parseArray(ids, Integer.class);
            List<File> files = fileService.selectBatchIds(idList);
            if (CollectionUtils.isNotEmpty(files)) {

                Map<String, Object> model = new HashMap<>(3);

                long now = System.currentTimeMillis();
                long expired = now + 24 * 3600 * 1000 * mailVo.getMailValidity();
//                long expired = now + oaProperties.getMailExpiredTime() * 1000;

                List<MailFile> mailFiles = Lists.newArrayList();
                files.forEach(file -> {
                    MailFile mailFile = new MailFile();
                    mailFile.setName(file.getName());
                    String url = file.getUrl();
                    if (file.getType() == 999) {
                        if (!url.startsWith("http://") && !url.startsWith("https://")) {
                            url = "http://" + url;
                        }
                    } else {
                        url = "/file/view?id=" + file.getId();
                    }
                    mailFile.setUrl(url);
                    mailFile.setCreateTime(new DateTime(now));
                    mailFile.setExpiredTime(new DateTime(expired));
                    mailFile.setSendMail(from);
                    mailFile.setAcceptMail(mailVo.getAcceptMail());
                    mailFile.setMailSubject(mailVo.getMailSubject());
                    mailFiles.add(mailFile);
                });

                // 保存邮件
                mailFileService.save(mailFiles);
                // 发送邮件
                model.put("mailFiles", mailFiles);
                model.put("deployServerPath", mailService.getDeployServerPath(request));
                mailService.sendHtmlMailUsingFreeMarker(from, mailVo.getAcceptMail(), mailVo.getMailSubject(), model);
            }

        } catch (Exception e) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("邮件发送失败").createErrorModel();
            ResponseEntity.badRequest().body(errorModel);
        }

        return ResponseEntity.ok(StringPool.EMPTY);
    }

    @GetMapping("/preview")
    public void previewFile(Long id, HttpServletResponse response) {
        try {
            MailFile mailFile = mailFileService.findNoExpiredById(id);
            if (null == mailFile) {
                response.sendRedirect("/mail/expired");
            } else {
                response.sendRedirect(mailFile.getUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

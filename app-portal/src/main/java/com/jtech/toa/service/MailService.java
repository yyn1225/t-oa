package com.jtech.toa.service;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import com.jtech.marble.StringPool;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender sender;
    private final Configuration configuration;

    public MailService(
            final JavaMailSender sender,
            final Configuration configuration
    ) {
        this.sender = sender;
        this.configuration = configuration;
    }

    /**
     * 普通的文本邮件
     *
     * @param from 发件人邮箱
     * @param to 接收人邮箱
     * @param subject 邮件标题
     * @param text 邮件内容
     */
    public void sendSimpleMail(String from, String to, String subject, String text) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
    }

    /**
     * 模板邮件
     *
     * @param from 发件人邮箱
     * @param to 接收人邮箱
     * @param subject 邮件标题
     * @param model 模型数据
     * @throws Exception
     */
    public void sendHtmlMailUsingFreeMarker(String from, String to, String subject, Map model) throws Exception {
        Template t = configuration.getTemplate("mail/content.ftl");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        sender.send(mimeMessage);
    }

    /**
     * 模板邮件 发送报价  通过附件
     *
     * @param from 发件人邮箱
     * @param to 接收人邮箱
     * @param subject 邮件标题
     * @param model 模型数据
     * @throws Exception
     */
    public void sendHtmlMailUsingFreeMarkerOffer(String from, String to, String subject, Map model) throws Exception {
        Template t = configuration.getTemplate("mail/content.ftl");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //整封邮件的MINE消息体  混合的组合关系
        MimeMultipart msgMultipart = new MimeMultipart("mixed");
        //设置邮件的MINE消息体
        mimeMessage.setContent(msgMultipart);

        //附件
        MimeBodyPart attch1 = new MimeBodyPart();
        //把内容，附件 加入到 MINE消息体中
        msgMultipart.addBodyPart(attch1);

        //把文件，添加到附件1中
        //数据源
        DataSource ds1 = new FileDataSource(new File(model.get("filePath").toString()));
        //数据处理器
        DataHandler dh1 = new DataHandler(ds1 );
        //设置第一个附件的数据
        attch1.setDataHandler(dh1);
        //设置第一个附件的文件名
        attch1.setFileName(model.get("filePath").toString().replace(model.get("path").toString(),""));

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        sender.send(mimeMessage);
    }

    /**
     * 获取项目部署路径
     *
     * @param request 请求
     * @return
     */
    public String getDeployServerPath(HttpServletRequest request) {
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String path = request.getContextPath();
        String deployServerPath = new StringBuilder("http://")
                .append(serverName)
                .append(StringPool.COLON)
                .append(serverPort)
                .append(path)
                .toString();
        return deployServerPath;
    }
}

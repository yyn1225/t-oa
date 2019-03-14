package com.jtech.toa.model.vo;

import lombok.Data;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class MailVo {
    private Long id;
    private String fileName;
    private String fileUrl;
    private String acceptMail;
    private String mailSubject;
    /**
     增加期限  （天）
     */
    private long  mailValidity;
}

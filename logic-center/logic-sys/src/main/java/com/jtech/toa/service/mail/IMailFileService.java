package com.jtech.toa.service.mail;


import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.toa.entity.mail.MailFile;

/**
 * <p>
 * 邮件发送的文件 服务类
 * </p>
 *
 * @author jtech
 * @since 2018-04-14
 */
public interface IMailFileService extends IService<MailFile> {

    /**
     * 保存邮件发送的文件
     *
     * @param files
     * @return
     */
    boolean save(List<MailFile> files);

    /**
     * 获取未过期的链接文件
     *
     * @param id
     * @return
     */
    MailFile findNoExpiredById(Long id);

}

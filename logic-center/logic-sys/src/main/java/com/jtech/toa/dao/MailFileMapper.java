package com.jtech.toa.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.mail.MailFile;

/**
 * <p>
  * 邮件发送的文件 Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2018-04-14
 */
public interface MailFileMapper extends BaseMapper<MailFile> {

    /**
     * 获取未过期的链接文件
     *
     * @param id 主键ID
     * @return
     */
    MailFile findNoExpiredById(Long id);
}
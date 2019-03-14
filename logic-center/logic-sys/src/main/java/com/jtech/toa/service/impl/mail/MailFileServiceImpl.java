package com.jtech.toa.service.impl.mail;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.MailFileMapper;
import com.jtech.toa.entity.mail.MailFile;
import com.jtech.toa.service.mail.IMailFileService;

/**
 * <p>
 * 邮件发送的文件 服务实现类
 * </p>
 *
 * @author jtech
 * @since 2018-04-14
 */
@Service
public class MailFileServiceImpl extends ServiceImpl<MailFileMapper, MailFile> implements IMailFileService {

    @Override
    public boolean save(List<MailFile> files) {
        boolean isOk = this.insertBatch(files);
        if (!isOk) {
            throw new DaoException("新增邮件发送的文件失败");
        }
        return true;
    }

    @Override
    public MailFile findNoExpiredById(Long id) {
        return baseMapper.findNoExpiredById(id);
    }

}

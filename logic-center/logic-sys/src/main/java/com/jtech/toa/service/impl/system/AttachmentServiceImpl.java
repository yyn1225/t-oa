/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.system;

import com.google.common.base.Strings;
import com.google.common.io.Files;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.util.DateUtil;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.dao.AttachmentMapper;
import com.jtech.toa.entity.system.Attachment;
import com.jtech.toa.exception.LocalFileNotFoundException;
import com.jtech.toa.service.system.IAttachmentService;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.io.FileUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.jtech.marble.StringPool.DASH;
import static com.jtech.marble.StringPool.EMPTY;


/**
 * <p> 用户表 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-07-10
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements
        IAttachmentService {


    private final String filePath;
    private final String mediaUrl;

    @Autowired
    public AttachmentServiceImpl(OaProperties oaProperties) {
        this.filePath = oaProperties.getMediaPath();
        this.mediaUrl = oaProperties.getMediaUrl();
    }


    /**
     * 按照文件类型，生成年月日格式的文件存储路径
     *
     * @return 文件存储路径
     */
    @Override
    public String genDatePath() {
        final String yyyyMMdd = DateUtil.yyyyMMddDash(new Date());
        return filePath + StringUtils.replace(yyyyMMdd, DASH, File.separator);
    }

    @Override
    @Transactional
    public Attachment saveFile(File file,
                               String uploldFileName,
                               int userId) {

        String fileType = Files.getFileExtension(file.getAbsolutePath());
        // 存储数据
        return saveAttachment(file, fileType, uploldFileName, userId);


    }

    @Transactional
    Attachment saveAttachment(
            File file,
            String fileType,
            String uploadFileName,
            int uploader
    ) {

        final String absolutePath = file.getAbsolutePath();
        String relativePath = StringUtils.replace(absolutePath, filePath, EMPTY);

        final Long fileSize = FileUtil.size(file);
        final Attachment attachment = new Attachment();
        attachment.setFileName(uploadFileName);
        attachment.setUploader(uploader);
        attachment.setFileSize(fileSize.intValue());
        attachment.setFileType(fileType);
        attachment.setFilePath(relativePath);
        attachment.setUploadTime(DateTime.now());
        attachment.setFileUrl(relativePath);
        final Integer insertRows = this.baseMapper.insert(attachment);
        if (insertRows > 0) {
            attachment.setFileUrl(medialUrl(attachment.getFileUrl()));
            return attachment;
        } else {
            throw new DaoException("保存附件信息失败");
        }
    }

    @Override
    public String medialUrl(String relativeURL) {
        if (Strings.isNullOrEmpty(relativeURL)) {
            return relativeURL;
        }
        if(relativeURL.toLowerCase().startsWith("http://") || relativeURL.toLowerCase().startsWith("https://")){
            return relativeURL;
        }
        String accessUrl = StringUtils.replace(relativeURL, File.separator, StringPool.SLASH);
        return this.mediaUrl + accessUrl;
    }

    @Override
    public List<Attachment> findByIds(String[] ids) {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return baseMapper.selectByIds(ids);
    }

}

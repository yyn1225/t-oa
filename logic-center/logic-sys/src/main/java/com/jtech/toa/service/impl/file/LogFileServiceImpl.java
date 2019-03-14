/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.service.impl.file;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.LogFileMapper;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.file.LogFile;
import com.jtech.toa.service.file.ILogFileService;

import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
@Service
public class LogFileServiceImpl extends ServiceImpl<LogFileMapper, LogFile> implements ILogFileService {

    @Override
    public boolean saveLog(File file) {
        LogFile logFile = new LogFile();
        logFile.setFiles(file.getId().longValue());
        logFile.setName(file.getName());
        logFile.setType(0);
        logFile.setUpdater(file.getUploader());
        logFile.setUpdateTime(file.getUploadTime());
        logFile.setUrl(file.getUrl());
        return insert(logFile);
    }
}

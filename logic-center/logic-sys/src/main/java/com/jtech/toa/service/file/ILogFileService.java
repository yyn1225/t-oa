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

package com.jtech.toa.service.file;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.file.LogFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
public interface ILogFileService extends IService<LogFile> {
    /**
     * 记录文件日志
     * @param file  文件对象
     * @return 是否成功
     */
    boolean saveLog(File file);
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.system.Attachment;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jtech
 * @since 2017-07-10
 */
public interface IAttachmentService extends IService<Attachment> {

    String genDatePath();

    /**
     * 普通文件附件保存，如果文件流是一个图片，则会进行图片附件的保存
     *
     * @param file           文件流
     * @param uploldFileName 上传文件名
     * @param userId         当前用户
     * @return 附件信息
     */
    Attachment saveFile(File file, String uploldFileName, int userId);

    /**
     * 通过相对文件地址访问路径
     *
     * @param relativeURL 相对地址文件
     * @return 访问地址
     */
    String medialUrl(String relativeURL);

    /**
     * 查询附件的信息
     * @param ids ids
     * @return 附件的集合
     */
    List<Attachment> findByIds(String[] ids);

}

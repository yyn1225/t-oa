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

package com.jtech.toa.service.impl.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.dao.SeriesImagesMapper;
import com.jtech.toa.entity.product.SeriesImages;
import com.jtech.toa.entity.system.Attachment;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.product.ISeriesImagesService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.system.IAttachmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-28
 */
@Service
public class SeriesImagesServiceImpl extends ServiceImpl<SeriesImagesMapper, SeriesImages> implements ISeriesImagesService {

    private final ISeriesService seriesService;
    private final IAttachmentService attachmentService;
    private final String path;

    @Autowired
    public SeriesImagesServiceImpl(ISeriesService seriesService, IAttachmentService attachmentService, OaProperties oaProperties) {
        this.seriesService = seriesService;
        this.attachmentService = attachmentService;
        this.path = oaProperties.getMediaPath();
    }

    @Override
    public List<SeriesImages> findBySeriesId(Integer seriesId) {
        return baseMapper.selectBySeriesId(seriesId);
    }

    @Override
    public boolean updateImageBySeriesIdAndAttIds(Integer seriesId, String attIds) {
        List<SeriesImages> images = baseMapper.selectBySeriesId(seriesId);
        Date now = new Date();
        Map<String,SeriesImages> existsMap = Maps.newHashMap();
        for(SeriesImages image:images){
            existsMap.put(String.valueOf(image.getAttachment()),image);
        }
        //这是刚刚上传的附件id列表
        List<String> uploadIds = Lists.newArrayList(attIds.split(","));
        //这是已经存在的附件id列表
        List<String> existIds = Lists.newArrayList(existsMap.keySet());
        for(String attach: uploadIds){
            if(!existIds.contains(attach)){
                //已存在列表中不包含维护的附件，说明是新增的，需要插入表中
                Attachment attachment = attachmentService.selectById(attach);
                SeriesImages seriesImages = new SeriesImages();
                seriesImages.setAttachment(attachment.getId());
                seriesImages.setSeries(seriesId);
                seriesImages.setUploadTime(now);
                //这里-2是用来标识压缩文件名称的了
                String compressUrl = attachment.getFileUrl().substring(0, attachment.getFileUrl().lastIndexOf(".")) +  "-2." + attachment.getFileUrl().substring(attachment.getFileUrl().lastIndexOf(".") + 1);
                File file = new File(path + compressUrl);
                if (file.exists()) {
                    seriesImages.setUrlThum(compressUrl);
                }else {
                    seriesImages.setUrlThum(attachment.getFileUrl());
                }
                seriesImages.setAttachment(attachment.getId());
                seriesImages.setUrlOriginal(attachment.getFileUrl());
                if(!insert(seriesImages)){
                    throw new RuntimeException();
                }
            }
        }

        for(String attach:existIds){
            if(!uploadIds.contains(attach)){
                //维护的图片列表不包含以前的，说明以前的数据被删除了
                SeriesImages image = existsMap.get(attach);
                if(1!=baseMapper.deleteByImageId(image.getId(),now)){
                    throw new RuntimeException();
                }
            }
        }
        return true;
    }
}

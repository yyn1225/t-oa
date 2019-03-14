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

package com.jtech.toa.controller.product.rest;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.SeriesImages;
import com.jtech.toa.service.product.ISeriesImagesService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.system.IAttachmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/series/image")
public class SeriesImageRestController {
    private final ISeriesImagesService seriesImagesService;
    private final ISeriesService seriesService;
    private final IAttachmentService attachmentService;

    @Autowired
    public SeriesImageRestController(ISeriesImagesService seriesImagesService,
                                     ISeriesService seriesService, IAttachmentService attachmentService) {
        this.seriesImagesService = seriesImagesService;
        this.seriesService = seriesService;
        this.attachmentService = attachmentService;
    }

    @PostMapping("/save")
    public ResponseEntity save(Integer seriesId, String attIds) {
        boolean isOk = seriesImagesService.updateImageBySeriesIdAndAttIds(seriesId, attIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/images")
    public ResponseEntity images(Integer seriesId) {
        Series series = seriesService.selectById(seriesId);
        if (series != null) {
            List<SeriesImages> images = Lists.newArrayList();
            images = seriesImagesService.findBySeriesId(seriesId);
            if (CollectionUtils.isEmpty(images)) {
                return ResponseEntity.ok(Collections.EMPTY_LIST);
            }
            for (SeriesImages image : images) {
                image.setUrlThum(attachmentService.medialUrl(image.getUrlThum()));
            }
            return ResponseEntity.ok(images);
        }
        return ResponseEntity.ok(Collections.EMPTY_LIST);
    }
}

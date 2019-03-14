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

package com.jtech.toa.controller.product;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.jtech.toa.entity.product.Series;
import com.jtech.toa.service.product.ISeriesImagesService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.system.IAttachmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/series/images")
public class SeriesImageController {
    private final ISeriesService seriesService;
    private final IAttachmentService attachmentService;
    private final ISeriesImagesService seriesImagesService;

    @Autowired
    public SeriesImageController(ISeriesService seriesService,
                                 IAttachmentService attachmentService,
                                 ISeriesImagesService seriesImagesService) {
        this.seriesService = seriesService;
        this.attachmentService = attachmentService;
        this.seriesImagesService = seriesImagesService;
    }

    @GetMapping("/list")
   public String list() {
        return "/product/series/images/list";
    }

    @GetMapping("/image")
    public String image(Integer seriesId, Model model) {
        Series series = seriesService.selectById(seriesId);
        if (series != null) {
            model.addAttribute("seriesId", seriesId);
            model.addAttribute("parent", series.getParent());
            List<String> result = Lists.newArrayList();
            if (series.getParent() == 0) {
                String url = series.getImage();
                if (Strings.isNullOrEmpty(url)) {
                    model.addAttribute("url", url);
                } else {
                    model.addAttribute("url", attachmentService.medialUrl(url));
                }

            }
            model.addAttribute("images", result);
        }
        return "/product/series/images/image";
    }
}

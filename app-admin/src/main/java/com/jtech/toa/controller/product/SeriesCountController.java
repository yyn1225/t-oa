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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.SeriesStandard;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISeriesStandardService;
import com.jtech.toa.service.product.ISpareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/series/count")
public class SeriesCountController {
    private final ISeriesStandardService seriesStandardService;
    private final ISeriesService seriesService;
    private final ISpareService spareService;

    @Autowired
    public SeriesCountController(ISeriesStandardService seriesStandardService, ISeriesService seriesService, ISpareService spareService) {
        this.seriesStandardService = seriesStandardService;
        this.seriesService = seriesService;
        this.spareService = spareService;
    }

    @GetMapping("/list")
    public String list() {
        return "/product/series/count/list";
    }
    @GetMapping("/item")
    public String item(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("seriesId", id);
        }
        return "product/series/count/item";
    }

    @GetMapping("/years")
    public String years(Integer standardId, Model model) {
        if (standardId > 0) {
            SeriesStandard standard = seriesStandardService.selectById(standardId);
            if (standard != null) {
                Integer seriesId = standard.getSeries();
                Integer sid = standard.getSpare();
                if (seriesId != null && sid != null) {
                    Series series = seriesService.selectById(seriesId);
                    Spare spare = spareService.selectById(sid);
                    model.addAttribute("years", getYears(standard));
                    model.addAttribute("spare", spare);
                    model.addAttribute("standard", standard);
                    model.addAttribute("series", series);
                }
            }
        }
        return "product/series/count/years";
    }

    private List<Map<String, Object>> getYears(SeriesStandard standard) {
        List<Map<String, Object>> years = Lists.newArrayList();
        Map<String, Object> year2 = Maps.newHashMap();
        year2.put("year", 2);
        year2.put("counts", standard.getCountsTwo());
        year2.put("unitType", standard.getCalTypeTwo());
        year2.put("spel", standard.getSpelTwo());
        Map<String, Object> year3 = Maps.newHashMap();
        year3.put("year", 3);
        year3.put("counts", standard.getCountsThree());
        year3.put("unitType", standard.getCalTypeThree());
        year3.put("spel", standard.getSpelThree());
        Map<String, Object> year4 = Maps.newHashMap();
        year4.put("year", 4);
        year4.put("counts", standard.getCountsFour());
        year4.put("unitType", standard.getCalTypeFour());
        year4.put("spel", standard.getSpelFour());
        Map<String, Object> year5 = Maps.newHashMap();
        year5.put("year", 5);
        year5.put("counts", standard.getCountsFive());
        year5.put("unitType", standard.getCalTypeFive());
        year5.put("spel", standard.getSpelFive());
        years.add(year2);
        years.add(year3);
        years.add(year4);
        years.add(year5);
        return years;
    }
}
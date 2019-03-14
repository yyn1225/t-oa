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

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.entity.product.Standard;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.product.IStandardService;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
@RequestMapping("/spare/count")
public class SpareCountController {
    private final IUserService userService;
    private final IStandardService standardService;
    private final IProductService productService;
    private final ISpareService spareService;
    private final ISeriesService seriesService;
    private final IConfigurationService configurationService;
    private final ICertificationService certificationService;
    private final IDictService dictService;

    @Autowired
    public SpareCountController(IUserService userService,
                                IStandardService standardService,
                                IProductService productService,
                                ISpareService spareService,
                                ISeriesService seriesService,
                                IConfigurationService configurationService,
                                ICertificationService certificationService,
                                IDictService dictService) {
        this.userService = userService;
        this.standardService = standardService;
        this.productService = productService;
        this.spareService = spareService;
        this.seriesService = seriesService;
        this.configurationService = configurationService;
        this.certificationService = certificationService;
        this.dictService = dictService;
    }

    @GetMapping("/list")
    public String index(Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        model.addAttribute("area", userAppDto.getArea());
        model.addAttribute("series", seriesService.findSeriesList());
        model.addAttribute("certificationList", certificationService.selectCertificationList());
        model.addAttribute("configurationList", configurationService.selectConfigurationList(shiroUser.getDeptName()));
        model.addAttribute("productStatus", dictService.selectDictByCategory("product_status",shiroUser.getDeptName()));
        return "product/spare/count/list";
    }

    @GetMapping("/item")
    public String item(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("productId", id);
        }
        return "product/spare/count/item";
    }

    @GetMapping("/years")
    public String years(Integer standardId, Model model) {
        if (standardId > 0) {
            Standard standard = standardService.selectById(standardId);
            if (standard != null) {
                Integer pid = standard.getProduct();
                Integer sid = standard.getSpare();
                if (pid != null && sid != null) {
                    Product product = productService.selectById(pid);
                    Series series = seriesService.selectById(product.getSeries());
                    Spare spare = spareService.selectById(sid);
                    model.addAttribute("years", getYears(standard));
                    model.addAttribute("product", product);
                    model.addAttribute("spare", spare);
                    model.addAttribute("standard", standard);
                    model.addAttribute("series", series);
                }
            }
        }
        return "product/spare/count/years";
    }

    private List<Map<String, Object>> getYears(Standard standard) {
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

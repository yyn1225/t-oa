/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.product;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.ProductLang;
import com.jtech.toa.entity.product.ProductParamsLang;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.service.*;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.product.*;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Controller
public class PanelController {
    private final IParamsService paramsService;
    private final IProductService productService;
    private final IBoxService boxService;
    private final IConfigurationService configurationService;
    private final ICertificationService certificationService;
    private final ISeriesService seriesService;
    private final ISpecialService specialService;
    private final IDictService dictService;
    private final IUserService userService;
    private final IPriceService priceService;
    private final IProductLangService productLangService;
    private final IProductParamsLangService productParamsLangService;
    private final ISysExchangeService sysExchangeService;

    @Autowired
    public PanelController(IParamsService paramsService, IProductService productService, IBoxService boxService,
                           IConfigurationService configurationService, ICertificationService certificationService,
                           ISeriesService seriesService, ISpecialService specialService, IDictService dictService,
                           IUserService userService, IPriceService priceService, IProductLangService productLangService,
                           IProductParamsLangService productParamsLangService, ISysExchangeService sysExchangeService) {
        this.paramsService = paramsService;
        this.productService = productService;
        this.boxService = boxService;
        this.configurationService = configurationService;
        this.certificationService = certificationService;
        this.seriesService = seriesService;
        this.specialService = specialService;
        this.dictService = dictService;
        this.userService = userService;
        this.priceService = priceService;
        this.productLangService = productLangService;
        this.productParamsLangService = productParamsLangService;
        this.sysExchangeService = sysExchangeService;
    }

    @GetMapping("/product")
    public String panel(Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        model.addAttribute("area", userAppDto.getArea());
        model.addAttribute("series", seriesService.findSeriesList());
        model.addAttribute("certificationList", certificationService.selectCertificationList());
        model.addAttribute("configurationList", configurationService.selectConfigurationList(shiroUser.getDeptName()));
        model.addAttribute("productStatus", dictService.selectDictByCategory("product_status",shiroUser.getDeptName()));
        return "product/panel/list";
    }

    @GetMapping("/product/addUI")
    public String addUI(@RequestParam("id") int id, Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        model.addAttribute("boxList", boxService.selectBoxList());
        model.addAttribute("configurationList", configurationService.selectConfigurationList(shiroUser.getDeptName()));
        model.addAttribute("certificationList", certificationService.selectCertificationList());
        model.addAttribute("seriesList", seriesService.findSeriesList());
        model.addAttribute("special", specialService.selectSpecialList());
        model.addAttribute("productStatus", dictService.selectDictByCategory("product_status",shiroUser.getDeptName()));
        model.addAttribute("productType", dictService.selectDictByCategory("product_type",shiroUser.getDeptName()));
        model.addAttribute("rates", sysExchangeService.getLastExchange());

        if (id != 0) {
            Params params = paramsService.selectByIdAndLang(id, shiroUser.getDeptName());
            if (params == null) {
                Params param = new Params();
                model.addAttribute("params", param);
            } else {
                model.addAttribute("params", params);
                List<ProductParamsLang> productParamsLangs = productParamsLangService.selectList(
                        new EntityWrapper<ProductParamsLang>().eq("params", params.getId()));
                Map<String, ProductParamsLang> paramsLangMap = Maps.newHashMap();
                for (ProductParamsLang productParamsLang : productParamsLangs) {
                    paramsLangMap.put(productParamsLang.getLang(), productParamsLang);
                }
                model.addAttribute("paramsLang", paramsLangMap);
            }
            Product product = productService.selectByIdAndLang(id, shiroUser.getDeptName());
            List<ProductLang> productLangs = productLangService.selectList(new EntityWrapper<ProductLang>().
                    eq("product", id));
            Map<String, ProductLang> productLangMap = Maps.newHashMap();
            for (ProductLang productLang : productLangs) {
                productLangMap.put(productLang.getLang(), productLang);
            }
            model.addAttribute("productLang", productLangMap);
            model.addAttribute("product", product);
            model.addAttribute("productId", id);
            model.addAttribute("cert", product.getCertification());
            return "product/panel/edit";
        } else {
            Params param = new Params();
            model.addAttribute("params", param);
            Product product = new Product();
            model.addAttribute("product", product);
            model.addAttribute("productId", id);
            return "product/panel/add";
        }
    }

    /**
     * 基本信息新增,编辑
     *
     * @return 页面提示
     */
    @ResponseBody
    @PostMapping("/product/basic/save")
    public ResponseEntity saveBasic(int productId, String execute, ProductDto productDto,
                                    String panelLang, String partNo) {
        productDto.setPartNo(partNo);
        List<ProductLang> productLangs = JSONArray.parseArray(panelLang, ProductLang.class);
        for (ProductLang productLang : productLangs) {
            if ("zh".equals(productLang.getLang())) {
                productDto.setColor(productLang.getColorLang());
            }
        }
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        productDto.setId(productId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = simpleDateFormat.parse(execute);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productDto.setExecutionTime(date);
//        Optional<Product> productByBox = productService.selectProductByBox(productDto.getBox());
        if (productId <= 0) {
//            if (productByBox.isPresent()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder().
//                        setCode(ProductCode.CODE_IS_EXIST).
//                        setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
//            }
            boolean ok = productService.addProduct((int) shiroUser.getId(), productDto);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder().
                        setCode(ProductCode.SYSTEM_INSIDE_ERROR).
                        setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        } else {
            List<ProductLang> productLangList = productLangService.selectList(new EntityWrapper<ProductLang>().
                    eq("product", productId));
            Map<String,ProductLang> map = Maps.newHashMap();
            for (ProductLang productLang : productLangList) {
                map.put(productLang.getLang(), productLang);
            }
            List<ProductLang> updateList = Lists.newArrayList();
            List<ProductLang> newList = Lists.newArrayList();

            for(ProductLang lan : productLangs) {
                if(map.containsKey(lan.getLang())) {
                    lan.setId(map.get(lan.getLang()).getId());
                    lan.setUpdateTime(new Date());
                    updateList.add(lan);
                }else{
                    lan.setCreateTime(new Date());
                    newList.add(lan);
                }
            }
//            if (productByBox.isPresent() && !(productDto.getId().equals(productByBox.get().getId()))) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder().
//                        setCode(ProductCode.CODE_IS_EXIST).
//                        setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
//            }
            boolean ok = productService.updateProduct((int) shiroUser.getId(), productDto, updateList, newList);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder().
                        setCode(ProductCode.SYSTEM_INSIDE_ERROR).
                        setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        }
        return ResponseEntity.ok(String.valueOf(ProductCode.SUCCESS_CODE.getCode()));
    }

    /**
     * 产品参数新增,编辑
     *
     * @return 页面提示
     */
    @ResponseBody
    @PostMapping("/product/params/save")
    public ResponseEntity saveParams(int productId, int paramsId, Params params, String paramsLang) {
        List<ProductParamsLang> paramsLangs = JSONArray.parseArray(paramsLang, ProductParamsLang.class);
        for (ProductParamsLang langs : paramsLangs) {
            if ("zh".equals(langs.getLang())) {
                params.setControl(langs.getControlLang());
                params.setFixModual(langs.getFixModualLang());
                params.setFixPsu(langs.getFixPsu());
            }
        }
        params.setId(paramsId);
        params.setProduct(productId);
        if (paramsId <= 0) {
            boolean ok = paramsService.insert(params);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        } else {
            List<ProductParamsLang> productParamsLangs = productParamsLangService.selectList(
                    new EntityWrapper<ProductParamsLang>().eq("params", params.getId()));
            Map<String,ProductParamsLang> map = Maps.newHashMap();
            for (ProductParamsLang productParamsLang : productParamsLangs) {
                map.put(productParamsLang.getLang(), productParamsLang);
            }
            List<ProductParamsLang> updateList = Lists.newArrayList();
            List<ProductParamsLang> newList = Lists.newArrayList();

            for(ProductParamsLang lan : paramsLangs) {
                if(map.containsKey(lan.getLang())) {
                    lan.setId(map.get(lan.getLang()).getId());
                    lan.setUpdateTime(new Date());
                    updateList.add(lan);
                }else{
                    lan.setCreateTime(new Date());
                    newList.add(lan);
                }
            }
            boolean ok = paramsService.updateAndLang(params, updateList, newList);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        }
        return ResponseEntity.ok(params);
    }

    @ResponseBody
    @PostMapping("/product/all/save")
    public ResponseEntity allSave(String execute, ProductDto productDto, Params params,
                                  String panelLang, String paramsLang, String partNo) {
        productDto.setPartNo(partNo);
        List<ProductLang> productLangs = JSONArray.parseArray(panelLang, ProductLang.class);
        List<ProductParamsLang> paramsLangs = JSONArray.parseArray(paramsLang, ProductParamsLang.class);
        for (ProductLang productLang : productLangs) {
            if ("zh".equals(productLang.getLang())) {
                productDto.setColor(productLang.getColorLang());
            }
        }
        for (ProductParamsLang langs : paramsLangs) {
            if ("zh".equals(langs.getLang())) {
                params.setControl(langs.getControlLang());
                params.setFixModual(langs.getFixModualLang());
                params.setFixPsu(langs.getFixPsu());
            }
        }
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(execute);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productDto.setExecutionTime(date);
//        Optional<Product> productByBox = productService.selectProductByBox(productDto.getBox());
//        if (productByBox.isPresent()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder().
//                    setCode(ProductCode.CODE_IS_EXIST).
//                    setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
//        }
        boolean ok = productService.addProductAndParams((int) shiroUser.getId(), productDto,
                params, productLangs, paramsLangs);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder().
                    setCode(ProductCode.SYSTEM_INSIDE_ERROR).
                    setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
        }
        return ResponseEntity.ok(String.valueOf(ProductCode.SUCCESS_CODE.getCode()));
    }
}

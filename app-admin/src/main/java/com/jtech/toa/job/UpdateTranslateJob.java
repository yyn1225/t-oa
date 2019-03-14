/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2018
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.job;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jtech.toa.config.context.SpringContextHolder;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.ProductBoxLang;
import com.jtech.toa.entity.product.ProductLang;
import com.jtech.toa.entity.product.ProductModualLang;
import com.jtech.toa.entity.product.ProductParamsLang;
import com.jtech.toa.entity.product.ProductSpareLang;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.system.ITranslateService;

/**
 * <p> </p>
 *
 * @author bing.xi
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class UpdateTranslateJob {

    private Logger logger= Logger.getLogger(UpdateTranslateJob.class);

    private final String SPARE_TYPE= "spare_type";
    private final String SPARE_BRAND = "spare_brand";
    private final String SPARE_MODEL = "spare_model";
    private final String ENGLISH = "en";
    private final String LED_TYPE = "led_type";
    private final String BOX_TYPE ="box_type";
    private final String PRODUCT_COLOR = "product_color";
    private final String PARAMS_CONTROL = "params_control";
    private final String PARAMS_FIX_MODUAL = "params_fixModule";
    private final String PARAMS_FIX_PSU = "params_fixPsu";
    /**
     * 同步翻译表（absen_sys_language_translate）的数据
     * 将已有的翻译表中的数据同步到业务数据表：
     * 1：absen_product_spare_lang（type_lang,brand_lang,model_lang）
     * 2：absen_product_modual_lang（type）
     * 3：absen_product_box_lang（type）
     * 4：absen_product_lang（color_lang）
     * 5：absen_product_params_lang（control_lang,fix_modual_lang,fix_psu）
     */
    @Scheduled(cron = "0 0 1,13 * * ?")
    public void updateTranslateData(){
        logger.info("synchronization translate data begin...");
        ITranslateService translateService = SpringContextHolder.getBean(ITranslateService.class);
        Map<String,String> translateMap = translateService.getForList();
        logger.info("translate table data size:"+translateMap.size());
        if(!translateMap.isEmpty()) {
            ISpareService spareService = SpringContextHolder.getBean(ISpareService.class);
//            IProductSpareLangService productSpareLangService = SpringContextHolder.getBean(IProductSpareLangService.class);
            //List<Spare> spareList = spareService.findAllWithoutSpareLang();
            List<Spare> spareBrandList = spareService.findAllWithoutSpareBrandLang();
            List<Spare> spareModelList = spareService.findAllWithoutSpareModelLang();
            List<Spare> spareTypeList = spareService.findAllWithoutSpareTypeLang();
            logger.info("absen_product_spare table need synchronization data size:spareBrandList:"
                    +spareBrandList.size()+",spareModelList:"+spareModelList.size()+",spareTypeList:"+spareTypeList.size());
            if (!spareBrandList.isEmpty()) {
                for (Spare spare : spareBrandList) {
                    if(translateMap.get(SPARE_BRAND + "-" + spare.getBrand()) != null) {
//                        ProductSpareLang productSpareLang = new ProductSpareLang();
//                        productSpareLang.setBrandLang(translateMap.get(SPARE_BRAND + "-" + spare.getBrand()));
//                        productSpareLang.setModelLang(translateMap.get(SPARE_MODEL + "-" + spare.getModel()));
//                        productSpareLang.setLang(ENGLISH);
//                        productSpareLang.setSpare(spare.getId());
//                        productSpareLang.setTypeLang(translateMap.get(SPARE_TYPE + "-" + spare.getType()));
//                        productSpareLang.setCreateTime(new Date());
//                        productSpareLangService.insert(productSpareLang);
                        ProductSpareLang lang = new ProductSpareLang();
                        lang = lang.selectOne(new EntityWrapper().eq("spare", spare.getId()).eq("lang","en"));
                        if(lang == null){
                            lang = new ProductSpareLang();
                        }
                        lang.setSpare(spare.getId());
                        lang.setCreateTime(new Date());
                        lang.setBrandLang(translateMap.get(SPARE_BRAND + "-" + spare.getBrand()));
                        lang.setLang("en");
                        lang.insertOrUpdate();
                    }
                }
            }

            if (!spareModelList.isEmpty()) {
                for (Spare spare : spareModelList) {
                    if(translateMap.get(SPARE_MODEL + "-" + spare.getModel()) != null) {
                        ProductSpareLang lang = new ProductSpareLang();
                        lang = lang.selectOne(new EntityWrapper().eq("spare", spare.getId()).eq("lang","en"));
                        if(lang == null){
                            lang = new ProductSpareLang();
                        }
                        lang.setSpare(spare.getId());
                        lang.setCreateTime(new Date());
                        lang.setModelLang(translateMap.get(SPARE_MODEL + "-" + spare.getModel()));
                        lang.setLang("en");
                        lang.insertOrUpdate();
                    }
                }
            }

            if (!spareTypeList.isEmpty()) {
                for (Spare spare : spareTypeList) {
                    if(translateMap.get(SPARE_TYPE + "-" + spare.getType()) != null) {
                        ProductSpareLang lang = new ProductSpareLang();
                        lang = lang.selectOne(new EntityWrapper().eq("spare", spare.getId()).eq("lang","en"));
                        if(lang == null){
                            lang = new ProductSpareLang();
                        }
                        lang.setSpare(spare.getId());
                        lang.setCreateTime(new Date());
                        lang.setTypeLang(translateMap.get(SPARE_TYPE + "-" + spare.getType()));
                        lang.setLang("en");
                        lang.insertOrUpdate();
                    }
                }
            }


            IModuleService moduleService = SpringContextHolder.getBean(IModuleService.class);
//            IProductModualLangService productModualLangService = SpringContextHolder.getBean(IProductModualLangService.class);
            List<Module> moduleList = moduleService.findAllWithoutModuleLang();
            logger.info("absen_product_modual table need synchronization data size:"+moduleList.size());
            if(!moduleList.isEmpty()){
                for(Module module:moduleList){
                    if(translateMap.get(LED_TYPE + "-" + module.getConfiguration()) != null) {
                        ProductModualLang productModualLang = new ProductModualLang();
                        productModualLang = productModualLang.selectOne(
                                new EntityWrapper().eq("modual", module.getId()).eq("lang","en"));
                        if(productModualLang == null){
                            productModualLang = new ProductModualLang();
                        }
                        productModualLang.setModual(module.getId());
                        productModualLang.setLang(ENGLISH);
                        productModualLang.setType(translateMap.get(
                                LED_TYPE + "-" + module.getConfiguration()));
                        productModualLang.setCreateTime(new Date());
                        productModualLang.insertOrUpdate();
                    }
                }
            }

            IBoxService boxService = SpringContextHolder.getBean(IBoxService.class);
//            IProductBoxLangService productBoxLangService = SpringContextHolder.getBean(IProductBoxLangService.class);
            List<Box> boxList = boxService.findAllWithoutBoxLang();
            logger.info("absen_product_box table need synchronization data size:"+boxList.size());
            if(!boxList.isEmpty()){
                for(Box box:boxList){
                    if(translateMap.get(BOX_TYPE + "-" + box.getBoxType()) != null) {
                        ProductBoxLang productBoxLang = new ProductBoxLang();
                        productBoxLang = productBoxLang.selectOne(
                                new EntityWrapper().eq("box", box.getId()).eq("lang","en"));
                        if(productBoxLang == null){
                            productBoxLang = new ProductBoxLang();
                        }
                        productBoxLang.setBox(box.getId());
                        productBoxLang.setLang(ENGLISH);
                        productBoxLang.setType(translateMap.get(BOX_TYPE + "-" + box.getBoxType()));
                        productBoxLang.setCreateTime(new Date());
                        productBoxLang.insertOrUpdate();
                    }
                }
            }

            IProductService productService = SpringContextHolder.getBean(IProductService.class);
//            IProductLangService productLangService = SpringContextHolder.getBean(IProductLangService.class);
            List<Product> productList = productService.findAllWithoutProductLang();
            logger.info("absen_product table need synchronization data size:"+productList.size());
            if(!productList.isEmpty()){
                for(Product product:productList){
                    if(translateMap.get(PRODUCT_COLOR + "-" + product.getColor()) != null) {
                        ProductLang productLang = new ProductLang();
                        productLang = productLang.selectOne(
                                new EntityWrapper().eq("product", product.getId()).eq("lang","en"));
                        if(productLang == null){
                            productLang = new ProductLang();
                        }
                        productLang.setLang(ENGLISH);
                        productLang.setProduct(product.getId());
                        productLang.setColorLang(translateMap.get(PRODUCT_COLOR + "-" + product.getColor()));
                        productLang.setCreateTime(new Date());
                        productLang.insertOrUpdate();
                    }
                }
            }

            IParamsService paramsService = SpringContextHolder.getBean(IParamsService.class);
//            IProductParamsLangService productParamsLangService = SpringContextHolder.getBean(IProductParamsLangService.class);
            //List<Params> paramsList = paramsService.findAllWithoutParamsLang();
            List<Params> paramsControlList = paramsService.findAllWithoutParamsControlLang();
            List<Params> paramsFixModualList = paramsService.findAllWithoutParamsFixModualLang();
            List<Params> paramsFixPsuList = paramsService.findAllWithoutParamsFixPsuLang();
            logger.info("absen_product_params table need synchronization data size:paramsControlList:"
                    +paramsControlList.size()+",paramsFixModualList:"+paramsFixModualList.size()
                    +",paramsFixPsuList"+paramsFixPsuList.size());
            if(!paramsControlList.isEmpty()){
                for (Params params:paramsControlList){
                    if(translateMap.get(PARAMS_CONTROL + "-" + params.getControl()) != null) {
                        ProductParamsLang productParamsLang = new ProductParamsLang();
                        productParamsLang = productParamsLang.selectOne(new EntityWrapper()
                                .eq("params", params.getId()).eq("lang","en"));
                        if(productParamsLang == null){
                            productParamsLang = new ProductParamsLang();
                        }
                        productParamsLang.setLang(ENGLISH);
                        productParamsLang.setParams(params.getId());
                        productParamsLang.setControlLang(translateMap.get(PARAMS_CONTROL + "-" + params.getControl()));
                        productParamsLang.setCreateTime(new Date());
                        productParamsLang.insertOrUpdate();
                    }
                }
            }
            if(!paramsFixModualList.isEmpty()){
                for (Params params:paramsFixModualList){
                    if(translateMap.get(PARAMS_FIX_MODUAL + "-" + params.getFixModual()) != null) {
                        ProductParamsLang productParamsLang = new ProductParamsLang();
                        productParamsLang = productParamsLang.selectOne(new EntityWrapper()
                                .eq("params", params.getId()).eq("lang","en"));
                        if(productParamsLang == null){
                            productParamsLang = new ProductParamsLang();
                        }
                        productParamsLang.setLang(ENGLISH);
                        productParamsLang.setParams(params.getId());
                        productParamsLang.setFixModualLang(translateMap.get(PARAMS_FIX_MODUAL + "-" + params.getFixModual()));
                        productParamsLang.setCreateTime(new Date());
                        productParamsLang.insertOrUpdate();
                    }
                }
            }
            if(!paramsFixPsuList.isEmpty()){
                for (Params params:paramsFixPsuList){
                    if(translateMap.get(PARAMS_FIX_PSU + "-" + params.getFixPsu()) != null) {
                        ProductParamsLang productParamsLang = new ProductParamsLang();
                        productParamsLang = productParamsLang.selectOne(new EntityWrapper()
                                .eq("params", params.getId()).eq("lang","en"));
                        if(productParamsLang == null){
                            productParamsLang = new ProductParamsLang();
                        }
                        productParamsLang.setLang(ENGLISH);
                        productParamsLang.setParams(params.getId());
                        productParamsLang.setFixPsu(translateMap.get(PARAMS_FIX_PSU + "-" + params.getFixPsu()));
                        productParamsLang.setCreateTime(new Date());
                        productParamsLang.insertOrUpdate();
                    }
                }
            }

        }
        logger.info("synchronization translate data end.");

    }
}

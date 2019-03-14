/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.sync.manage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xiaoleilu.hutool.date.DateTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import com.jtech.toa.config.context.SpringContextHolder;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.ProductLang;
import com.jtech.toa.entity.product.ProductSpareLang;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.service.product.IProductLangService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.sync.syncinterface.SapInterface;

@Component
public class SapMatnrManage {

    @Value("${oa.is-synchronization-data}")
    private boolean isSynchronizationData;

    private Logger logger=Logger.getLogger(SapMatnrManage.class);

    @Scheduled(cron = "0 0 4 * * *")//每天早上4点同步
    public void manageSapreList(){
        logger.info("begin sync sapre info,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin sync sapre data......");
            ISpareService spareService = SpringContextHolder.getBean(ISpareService.class);
            List<Spare> spareList = spareService.findSpareWithoutLang();
            SapInterface sapInterface = new SapInterface();
            for (Spare spare : spareList) {
                try {
                    Map<String, String> map = sapInterface.getSpareInfos(spare.getMaterial());
                    if (null != map) {
                        logger.debug(spare.getMaterial()+":"
                                +map.get("MAKTX")+":"+map.get("MEINS"));
                        spare.setUnit(map.get("MEINS"));
                        if (!StringUtils.isEmpty(spare.getUnit())) {
                            spare.updateById();
                        }
                        ProductSpareLang lang = new ProductSpareLang();
                        lang = lang.selectOne(new EntityWrapper()
                                .eq("spare", spare.getId()).eq("lang","en"));
                        if(lang == null){
                            lang = new ProductSpareLang();
                        }
                        lang.setSpare(spare.getId());
                        lang.setCreateTime(DateTime.now());
                        lang.setDescriptionLang(map.get("MAKTX"));
                        lang.setLang("en");
                        lang.insertOrUpdate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    continue;
                }
            }
            logger.info("begin sync sapre end.");
        }
    }

    /**
     * 定时任务，用来更新产品的物料中文描述和英文描述
     */
    @Scheduled(cron = "0 0 3 * * *")//每天早上3点同步
    public void manageProductList(){
        logger.info("begin sync product language info,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin sync product language data......");
            IProductService productService = SpringContextHolder.getBean(IProductService.class);
            IProductLangService productLangService = SpringContextHolder.getBean(IProductLangService.class);
            List<Product> productList = productService.findWithoutLang();
            SapInterface sapInterface = new SapInterface();
            for (Product product : productList) {
                try {
                    Map<String, String> zhMap = sapInterface.getProductInfos(product.getPartNo(), "1");
                    Map<String, String> enMap = sapInterface.getProductInfos(product.getPartNo(), "EN");
                    if (null != enMap) {
                        logger.debug(product.getPartNo()+":"
                                +zhMap.get("MAKTX")+":"+enMap.get("MAKTX"));
                        product.setState(zhMap.get("MAKTX"));
                        if (!StringUtils.isEmpty(product.getState())) {
                            product.updateById();
                        }
                        ProductLang lang = productLangService.selectOne(
                                new EntityWrapper<ProductLang>().eq("product", product.getId()).eq("lang", "en"));
                        if (null != lang) {
                            lang.setRemarkLang(enMap.get("MAKTX"));
                            productLangService.updateById(lang);
                        } else {
                            ProductLang newLang = new ProductLang();
                            newLang.setProduct(product.getId());
                            newLang.setCreateTime(DateTime.now());
                            newLang.setRemarkLang(enMap.get("MAKTX"));
                            newLang.setLang("en");
                            newLang.insert();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    continue;
                }
            }
            logger.info("begin sync product language end.");
        }
    }
}

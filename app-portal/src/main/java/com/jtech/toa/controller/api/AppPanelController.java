/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.api;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.dto.DeleteData;
import com.jtech.toa.model.dto.InsertData;
import com.jtech.toa.model.dto.prices.AppProductPrice;
import com.jtech.toa.model.dto.products.AppBoxs;
import com.jtech.toa.model.dto.products.AppConfiguration;
import com.jtech.toa.model.dto.products.AppModules;
import com.jtech.toa.model.dto.products.AppParams;
import com.jtech.toa.model.dto.products.AppProduct;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISparePriceService;
import com.jtech.toa.service.product.IStandardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@RestController
@RequestMapping("/api/panel")
public class AppPanelController {
    final IProductService productService;
    final IBoxService boxService;
    final IParamsService paramsService;
    final IPriceService priceService;
    final IModuleService moduleService;
    final IStandardService standardService;
    final IConfigurationService configurationService;
    final ISparePriceService sparePriceService;
    final ICertificationService certificationService;

    @Autowired
    public AppPanelController(IProductService productService,
                              IBoxService boxService,
                              IParamsService paramsService,
                              IModuleService moduleService,
                              IStandardService standardService,
                              ISparePriceService sparePriceService,
                              IConfigurationService configurationService,
                              ICertificationService certificationService,
                              IPriceService priceService) {
        this.productService = productService;
        this.boxService = boxService;
        this.paramsService = paramsService;
        this.priceService = priceService;
        this.certificationService = certificationService;
        this.moduleService = moduleService;
        this.standardService=standardService;
        this.sparePriceService =sparePriceService;
        this.configurationService = configurationService;
    }

    @GetMapping("/box/list")
    public ResponseEntity findList(int lastId) {
        JSONArray jsonArray = new JSONArray();
        if (lastId == 0) {
            DeleteData deleteData = new DeleteData();
            deleteData.setKey("id");
            deleteData.setType(DeleteData.Type.NotEq);
            deleteData.setTable(AppConstant.Box);
            deleteData.setValue("0");
            deleteData.setSort(1);
            JSONObject delete = new JSONObject();
            delete.put("dataDelete", deleteData);
            jsonArray.add(delete);
        }

        List<AppBoxs> boxList = boxService.findAppBoxs(lastId);
        InsertData<AppBoxs> insertData = new InsertData<>(AppConstant.Box, boxList, 2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert", insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/params/list")
    public ResponseEntity findParams(int lastId) {
        JSONArray jsonArray = new JSONArray();
        if (lastId == 0) {
            DeleteData deleteData = new DeleteData(AppConstant.Params);
            JSONObject delete = new JSONObject();
            delete.put("dataDelete", deleteData);
            jsonArray.add(delete);
        }
        List<Params> params = paramsService.findTop50List(lastId);
        final List<AppParams> paramList = Lists.newArrayList();
        params.forEach(x -> paramList.add(new AppParams(x)));
        JSONObject insert = new JSONObject();
        InsertData<AppParams> insertData = new InsertData<>(AppConstant.Params, paramList, 2);
        insert.put("dataInsert", insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/modual/list")
    public ResponseEntity findModuals(int lastId) {
        JSONArray jsonArray = new JSONArray();
        if (lastId == 0) {
            DeleteData deleteData = new DeleteData(AppConstant.Module);
            JSONObject delete = new JSONObject();
            delete.put("dataDelete", deleteData);
            jsonArray.add(delete);
        }
        List<AppModules> list = moduleService.selectTop50List(lastId);
        InsertData<AppModules> insertData = new InsertData<>(AppConstant.Module, list, 2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert", insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

//    @GetMapping("/spare/connect")
//    public ResponseEntity findProductSpareList(int lastId) {
//        List<Standard> list = standardService.findTop100List(lastId);
//        JSONArray jsonArray= new JSONArray();
//        if(0==lastId){
//            DeleteData deleteData=new DeleteData(AppConstant.ProductStandard);
//            JSONObject delete = new JSONObject();
//            delete.put("dataDelete",deleteData);
//            jsonArray.add(delete);
//        }
//
//        InsertData<Standard> insertData = new InsertData<>(AppConstant.ProductStandard,list,2);
//        JSONObject insert = new JSONObject();
//        insert.put("dataInsert",insertData);
//        jsonArray.add(insert);
//
//        return ResponseEntity.ok(jsonArray);
//    }

    @GetMapping("/prices")
    public ResponseEntity findPanelPrices(int lastId,int area){
        JSONArray jsonArray = new JSONArray();
        if(0==lastId){
            DeleteData deleteData = new DeleteData(AppConstant.ProductPrice);
            JSONObject delete = new JSONObject();
            delete.put("dataDelete",deleteData);
            jsonArray.add(delete);
        }

        List<AppProductPrice> list = priceService.findAppProductPrices(area,lastId);
        InsertData<AppProductPrice> insertData = new InsertData<>(AppConstant.ProductPrice,list,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);

        return ResponseEntity.ok(jsonArray);
    }

//    @GetMapping("/spare/prices")
//    public ResponseEntity getSparePrices(int lastId,int area){
//        JSONArray jsonArray = new JSONArray();
//        if(lastId==0){
//            DeleteData deleteData = new DeleteData(AppConstant.SparePrice);
//            JSONObject delete = new JSONObject();
//            delete.put("dataDelete",deleteData);
//            jsonArray.add(delete);
//        }
//
//        List<AppSparePrice> sparePrices=sparePriceService.findTop100List(area,lastId);
//        InsertData<AppSparePrice> insertData = new InsertData<>(AppConstant.SparePrice,sparePrices,2);
//        JSONObject insert = new JSONObject();
//        insert.put("dataInsert",insertData);
//        jsonArray.add(insert);
//
//        return ResponseEntity.ok(jsonArray);
//    }

    @GetMapping("/configs")
    public ResponseEntity getConfigs(){
        JSONArray jsonArray = new JSONArray();
        List<AppConfiguration> configs =configurationService.findAppList();
        DeleteData deleteData = new DeleteData(AppConstant.Configuration);
        JSONObject delete = new JSONObject();
        delete.put("dataDelete",deleteData);
        jsonArray.add(delete);

        InsertData<AppConfiguration> insertData = new InsertData<>(AppConstant.Configuration,configs,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);

        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/list")
    public ResponseEntity getList(int lastId){
        JSONArray jsonArray = new JSONArray();
        if(0==lastId){
            DeleteData deleteData = new DeleteData(AppConstant.Product);
            JSONObject delete = new JSONObject();
            delete.put("dataDelete",deleteData);
            jsonArray.add(delete);
        }

        List<AppProduct> list = productService.selectTop50List(lastId);
        InsertData<AppProduct> insertData = new InsertData<>(AppConstant.Product,list,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);

        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/certification")
    public ResponseEntity getCertification(){
        JSONArray jsonArray = new JSONArray();
        DeleteData deleteData = new DeleteData(AppConstant.Certification);
        JSONObject delete = new JSONObject();
        delete.put("dataDelete",deleteData);
        jsonArray.add(delete);
        List<Certification> list = certificationService.selectCertificationList();
        InsertData<Certification> insertData = new InsertData<>(AppConstant.Certification,list,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);

        return ResponseEntity.ok(jsonArray);
    }
}

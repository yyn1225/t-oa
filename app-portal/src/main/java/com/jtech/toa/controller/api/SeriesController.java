/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.SeriesImages;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.app.AppSeriesImg;
import com.jtech.toa.model.dto.DeleteData;
import com.jtech.toa.model.dto.InsertData;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesImagesService;
import com.jtech.toa.service.product.ISeriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/series")
public class SeriesController {
    private final ISeriesService seriesService;
    private final IProductService productService;
    private final ISeriesImagesService imagesService;
    private final IFileService fileService;

    @Autowired
    public SeriesController(ISeriesService seriesService,
                            IProductService productService,
                            ISeriesImagesService imagesService,
                            IFileService fileService) {
        this.seriesService = seriesService;
        this.productService = productService;
        this.imagesService = imagesService;
        this.fileService = fileService;
    }

    @GetMapping("/list")
    public ResponseEntity seriesTree(){
        List<Object> result = Lists.newArrayList();
        List<SeriesDto> list = seriesService.findSeriesList();

        DeleteData deleteSeriesData = new DeleteData();
        deleteSeriesData.setTable(AppConstant.Series);
        deleteSeriesData.setKey("id");
        deleteSeriesData.setType(DeleteData.Type.NotEq);
        deleteSeriesData.setValue("0");
        deleteSeriesData.setVtype(DeleteData.Vtype.Single);
        deleteSeriesData.setSort(1);

        JSONObject deleteSeriesJson = new JSONObject();
        deleteSeriesJson.put("dataDelete", deleteSeriesData);
        result.add(deleteSeriesJson);

        InsertData<SeriesDto> insertData = new InsertData<>();
        insertData.setParams(list);
        insertData.setSort(2);
        insertData.setTable(AppConstant.Series);

        JSONObject insertJson = new JSONObject();
        insertJson.put("dataInsert",insertData);
        result.add(insertJson);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/seriesList")
    public ResponseEntity seriesTreeInfo(){
        List<SeriesDto> list = seriesService.findSeriesList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/images")
    public ResponseEntity seiresImages(int series){
        List<Object> result = Lists.newArrayList();
        List<SeriesImages> seriesImages = imagesService.findBySeriesId(series);
        //系列图片删除数据构建
        JSONObject deleteImageJson = new JSONObject();
        DeleteData deleteImageData = new DeleteData();
        deleteImageData.setTable(AppConstant.SeriesImage);
        deleteImageData.setKey("series");
        deleteImageData.setType(DeleteData.Type.Eq);
        deleteImageData.setValue(String.valueOf(series));
        deleteImageData.setVtype(DeleteData.Vtype.Single);
        deleteImageData.setSort(1);
        deleteImageJson.put("dataDelete", deleteImageData);
        result.add(deleteImageJson);

        InsertData<AppSeriesImg> insertImg = new InsertData<>();
        List<AppSeriesImg> imgs = Lists.newArrayList();
        for(SeriesImages img:seriesImages){
            AppSeriesImg i = new AppSeriesImg(img);
            i.setImages(fileService.medialUrl(i.getImages()));
            imgs.add(i);
        }
        insertImg.setTable(AppConstant.SeriesImage);
        insertImg.setParams(imgs);
        insertImg.setSort(2);
        JSONObject insertData = new JSONObject();
        insertData.put("dataInsert",insertImg);
        result.add(insertData);

        return ResponseEntity.ok(result);
    }

    @RequestMapping("/product/list")
    public ResponseEntity productList(
            @RequestParam(value = "series",defaultValue = "0",required = false) int series,
            @RequestParam(value = "lastTime",defaultValue = "",required = false) String lastTime){
        Wrapper<Product> newParams = new EntityWrapper<>();
        Wrapper<Product> updateParams = new EntityWrapper<>();
        Map<String,Object> maps=Maps.newHashMap();
        if(0!=series){
            newParams.eq("series",series);
            updateParams.eq("series",series);
        }
        if(!StringUtils.isEmpty(lastTime)){
            newParams.ge("create_time",new Date(Long.valueOf(lastTime)));
            updateParams.ge("update_time",new Date(Long.valueOf(lastTime)));
            maps.put("updates",productService.selectList(updateParams));
        }
        maps.put("news",productService.selectList(newParams));
        return ResponseEntity.ok(maps);
    }
}

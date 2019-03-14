/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.dto.DeleteData;
import com.jtech.toa.model.dto.InsertData;
import com.jtech.toa.model.dto.sys.ExhibitionAppDto;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.system.IExhibitionService;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.user.model.dto.AppAreas;
import com.jtech.toa.user.service.IDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@RestController
@RequestMapping("/api/system")
public class SystemController {
    private final IExhibitionService exhibitionService;
    private final IDictService dictService;
    private final ISysExchangeService exchangeService;
    private final IDepartmentService departmentService;

    @Autowired
    public SystemController(IExhibitionService exhibitionService,
                            ISysExchangeService exchangeService,
                            IDepartmentService departmentService,
                            IDictService dictService) {
        this.exhibitionService =exhibitionService;
        this.dictService = dictService;
        this.exchangeService = exchangeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/exhibitions")
    public ResponseEntity exhibitionList(){
        List<ExhibitionAppDto> list =exhibitionService.findForApp();
        DeleteData delete = new DeleteData();
        delete.setSort(1);
        delete.setTable("app_exhibition");
        delete.setKey("id");
        delete.setType("!=");
        delete.setVtype("1");
        delete.setValue("0");

        InsertData<ExhibitionAppDto> exs = new InsertData<>();
        exs.setTable("app_exhibition");
        exs.setSort(2);
        exs.setParams(list);

        JSONArray array = new JSONArray();

        JSONObject map =  new JSONObject();
        map.put("dataDelete",delete);
        array.add(map);

        JSONObject obj = new JSONObject();
        obj.put("dataInsert",exs);
        array.add(obj);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/image")
    public File image(String path){
        return new File(path);
    }

    /**
     * 轮播图列表
     *
     * @return 轮播图列表
     */
    @GetMapping("/banner")
    public ResponseEntity bannerList() {
       return ResponseEntity.ok(exhibitionService.findForApp());
    }

    @GetMapping("/rate")
    public ResponseEntity rateList(){
        JSONArray jsonArray = new JSONArray();
        List<SysExchange> rates = exchangeService.getLastExchange();
        List<Map<String,Object>> maps = Lists.newArrayList();
        rates.forEach(x->{
            Map<String,Object> map = Maps.newHashMap();
            map.put("code",x.getCode());
            map.put("symbol",x.getCurrency());
            map.put("rate",x.getRmb());
            maps.add(map);
        });

        DeleteData delete = new DeleteData();
        delete.setSort(1);
        delete.setTable(AppConstant.Rate);
        delete.setKey("code");
        delete.setType(DeleteData.Type.NotEq);
        delete.setVtype(DeleteData.Vtype.Single);
        delete.setValue("0");

        JSONObject del = new JSONObject();
        del.put("dataDelete",delete);
        jsonArray.add(del);

        JSONObject insert = new JSONObject();
        insert.put("dataInsert",new InsertData<>(AppConstant.Rate,maps,2));
        jsonArray.add(insert);

        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/area")
    public ResponseEntity offerArea(@RequestUser RequestSubject user){
        JSONArray jsonArray = new JSONArray();
        DeleteData deleteData = new DeleteData(AppConstant.Area);
        JSONObject delete = new JSONObject();
        delete.put("dataDelete",deleteData);
        jsonArray.add(delete);

        List<AppAreas> list = departmentService.selectAppArea(user.getId());
        InsertData<AppAreas> insertData= new InsertData<>(AppConstant.Area,list,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);

        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/dict")
    public ResponseEntity getDict(){
        JSONArray jsonArray = new JSONArray();
        DeleteData deleteData = new DeleteData(AppConstant.Dict);
        JSONObject delete = new JSONObject();
        delete.put("dataDelete",deleteData);
        jsonArray.add(delete);

        List<Dict> dicts = dictService.selectList(new EntityWrapper<>());
        JSONObject insert = new JSONObject();
        InsertData insertData = new InsertData(AppConstant.Dict,dicts,2);
        insert.put("dataInsert",insertData);

        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }
}

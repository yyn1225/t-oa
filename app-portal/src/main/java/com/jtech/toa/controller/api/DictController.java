package com.jtech.toa.controller.api;

import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.service.basic.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 字典接口
 * Created by JiTong on 2017/12/25.
 */
@RestController
@RequestMapping("/api/dict")
public class DictController {
    private IDictService dictService;

    @Autowired
    public DictController(IDictService dictService){
        this.dictService = dictService;
    }

    @GetMapping("/find/category")
    public ResponseEntity findByCategory(
            @RequestUser RequestSubject user,
            @RequestParam(name = "category") String category
    ){
        List<Dict> dictList = this.dictService.selectDictByCategory(category,user.getLanguage());
        List<Map<String,Object>> list = this.dictService.list2ListMap(dictList);
        return ResponseEntity.ok(list);
    }



}

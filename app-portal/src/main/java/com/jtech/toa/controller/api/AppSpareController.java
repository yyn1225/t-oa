package com.jtech.toa.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.dto.DeleteData;
import com.jtech.toa.model.dto.InsertData;
import com.jtech.toa.model.dto.products.AppSpares;
import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.service.product.ISparePriceService;
import com.jtech.toa.service.product.ISpareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/spare")
public class AppSpareController {

    private final ISpareService spareService;
    private final ISparePriceService sparePriceService;
    @Autowired
    public AppSpareController(ISpareService spareService, ISparePriceService sparePriceService) {
        this.spareService = spareService;
        this.sparePriceService = sparePriceService;
    }

    @GetMapping("/list")
    public ResponseEntity findList(int lastId) {
        JSONArray jsonArray = new JSONArray();
        if(lastId==0){
            DeleteData deleteData = new DeleteData();
            deleteData.setKey("id");
            deleteData.setType(DeleteData.Type.NotEq);
            deleteData.setTable(AppConstant.Spares);
            deleteData.setValue("0");
            deleteData.setSort(1);
            JSONObject delete = new JSONObject();
            delete.put("dataDelete",deleteData);
            jsonArray.add(delete);
        }

        List<AppSpares> spares = spareService.findAppSpares(lastId);
        InsertData<AppSpares> insertData = new InsertData<>(AppConstant.Spares,spares,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }
}

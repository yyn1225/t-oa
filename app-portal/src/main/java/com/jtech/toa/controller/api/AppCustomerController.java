/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.app.AppCustomer;
import com.jtech.toa.model.dto.DeleteData;
import com.jtech.toa.model.dto.InsertData;
import com.jtech.toa.model.query.CustomerQuery;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.user.model.dto.DepartmentDto;
import com.jtech.toa.user.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/customer")
public class AppCustomerController {
    private final ICustomerService customerService;
    private final IDepartmentService departmentService;

    @Autowired
    public AppCustomerController(ICustomerService customerService,
                                 IDepartmentService departmentService){
        this.customerService = customerService;
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public ResponseEntity getCustomerList(@RequestUser RequestSubject user,
                                          @RequestParam(value="last", defaultValue = "0") int last,
                                          @RequestParam(required = false)String q){
        CustomerQuery query = JSONObject.parseObject(q, CustomerQuery.class);
        if(query == null){
            query = new CustomerQuery();
        }
        return ResponseEntity.ok(customerService.findCustomerListByLastIdForProtal(query, user.getId(), user.getArea(),last));
    }

    @GetMapping("/customers")
    public ResponseEntity GetCustomerLists(@RequestUser RequestSubject user,int lastId){
        List<Customer> customerList = customerService.findMyCustomerPage(user.getId(),lastId);
        List<Object> result = Lists.newArrayList();
        if (0==lastId){
            JSONObject deleteJson = new JSONObject();
            DeleteData delete = new DeleteData();
            delete.setTable(AppConstant.Customer);
            delete.setKey("id");
            delete.setType(DeleteData.Type.NotEq);
            delete.setValue("0");
            delete.setVtype(DeleteData.Vtype.Single);
            delete.setSort(1);
            deleteJson.put("dataDelete", delete);
            result.add(deleteJson);
        }
        InsertData<AppCustomer> insertCus = new InsertData<>();
        insertCus.setTable(AppConstant.Customer);
        insertCus.setParams(Lists.newArrayList());
        for (Customer customer : customerList) {
            insertCus.getParams().add(new AppCustomer(customer));
        }
        insertCus.setSort(2);
        JSONObject insertData = new JSONObject();
        insertData.put("dataInsert",insertCus);
        result.add(insertData);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/my/info")
    public ResponseEntity myInfo(@RequestUser RequestSubject user){
        DepartmentDto departmentDto = departmentService.selectOneById(user.getArea());
        Map<String,String> map = Maps.newHashMap();
        map.put("id",user.getId()+"");
        if(departmentDto != null){
            map.put("area", departmentDto.getName());
        }else{
            map.put("area", "");
        }
        map.put("phone",user.getPhone());
        map.put("userName",user.getUserName());
        return ResponseEntity.ok(map);
    }

    @GetMapping("/detail")
    public ResponseEntity detail(@RequestParam(value = "id") int id){
        Customer customer = customerService.selectById(id);
        return ResponseEntity.ok(customer);
    }
}

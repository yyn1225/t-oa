/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.product.rest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.prices.PriceAssign;
import com.jtech.toa.entity.prices.PriceSecurity;
import com.jtech.toa.entity.prices.PriceSystem;
import com.jtech.toa.entity.prices.PricesDetails;
import com.jtech.toa.model.dto.prices.PriceAssignDto;
import com.jtech.toa.model.dto.prices.PriceSystemDto;
import com.jtech.toa.service.prices.IPriceAssignService;
import com.jtech.toa.service.prices.IPriceSecurityService;
import com.jtech.toa.service.prices.IPriceSystemService;
import com.jtech.toa.service.prices.IPricesDetailsService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class PriceSystemController {
    @Autowired
    IUserService userService;

    @Autowired
    IPriceSystemService priceSystemService;

    @Autowired
    IPricesDetailsService pricesDetailsService;

    @Autowired
    IPriceAssignService assignService;

    @Autowired
    IPriceAssignService priceAssignService;

    @Autowired
    IDepartmentService departmentService;

    @Autowired
    IPriceSecurityService securityService;

    @PostMapping("/panel/price/systems")
    public DataTablesOutput getPriceSystemList(@Valid @RequestBody DataTablesInput dataTablesInput){
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        User user = userService.selectById(shiroUser.getId());
        List<Department> departments = departmentService.selectDataByUser((int)shiroUser.getId());
        int level=3;
        for (Department department:departments){
            level=Math.min(level,department.getLevel());
        }

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        Page<PriceSystemDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        if(level==3){
            List<PriceSystemDto> list = priceSystemService.findPageListByUser(requestPage,user.getId());
            requestPage.setRecords(list);
        }else if(level==2){
            List<PriceSystemDto> list = priceSystemService.findPageList(requestPage, user.getArea());
            requestPage.setRecords(list);
        }else{
            List<PriceSystemDto> list = priceSystemService.findPageList(requestPage, 0);
            requestPage.setRecords(list);
        }

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/panel/price/asssign")
    public DataTablesOutput getPriceAssigns(@Valid @RequestBody DataTablesInput dataTablesInput){
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        Page<PriceAssignDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        if(null != dataTablesInput.getParams().get("systems")){
            List<PriceAssignDto> assigns = assignService.getAssignList(dataTablesInput.getParams().getInteger("systems"));
            requestPage.setRecords(assigns);
            requestPage.setTotal(assigns.size());
        }
        return DataTablesUtils.buildOut(dataTablesInput,requestPage);
    }

    @PostMapping("/panel/price/save")
    public ResponseEntity save(String details, String startTime, String endTime, String name,String unit) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        PriceSystem priceSystem = new PriceSystem();
        priceSystem.setName(name);
        priceSystem.setUnit(unit);
        if (!Strings.isNullOrEmpty(startTime)) {
            priceSystem.setStartTime(DateTime.parse(startTime).toDate());
        }
        if (!Strings.isNullOrEmpty(endTime)) {
            priceSystem.setEndTime(DateTime.parse(endTime).toDate());
        }
        List<PricesDetails> pricesDetailsList;
        if (Strings.isNullOrEmpty(details)) {
            pricesDetailsList = Lists.newArrayList();
        }else {
            pricesDetailsList = JSON.parseArray(details, PricesDetails.class);
        }
        PriceSystem system = priceSystemService.selectOne(new EntityWrapper<PriceSystem>().
                eq("area", userAppDto.getArea()).eq("name", priceSystem.getName()));
        if (system != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.PRODUCT_IS_INVALID)
                    .setMessage("message.price.repeat").createErrorModel());
        }
        boolean ok = pricesDetailsService.savePrice(pricesDetailsList, priceSystem, (int)shiroUser.getId(), userAppDto.getArea());
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/panel/price/edit")
    public ResponseEntity save(String details, String startTime, String endTime, Integer id) throws Exception {
        if (id == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        PriceSystem priceSystem = priceSystemService.selectById(id);
        if (!Strings.isNullOrEmpty(startTime)) {
            priceSystem.setStartTime(DateTime.parse(startTime).toDate());
        }
        if (!Strings.isNullOrEmpty(endTime)) {
            priceSystem.setEndTime(DateTime.parse(endTime).toDate());
        }
        boolean ok = pricesDetailsService.editPrice(priceSystem, details);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/panel/price/delete")
    public ResponseEntity deletePrice(int id) throws Exception{
        if (id == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
//        PriceSystem priceSystem = priceSystemService.selectById(id);
        List<PriceAssign> priceAssign = priceAssignService.selectList(new EntityWrapper<PriceAssign>().eq("systems", id));
//        Date date = new Date();
//        boolean compareTime = (priceSystem.getStartTime().getTime() < date.getTime()) && (date.getTime() < priceSystem.getEndTime().getTime());
        if (CollectionUtils.isNotEmpty(priceAssign)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.RESOURCE_REPEAT)
                    .setMessage("alert.message.priceSystems").createErrorModel());
        }
        List<PricesDetails> pricesDetailsList = pricesDetailsService.selectList(new EntityWrapper<PricesDetails>().eq("systems", id));
        boolean ok = priceSystemService.deletePriceSystems(id, pricesDetailsList);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }
    /**
     * 保存报价信息
     */
    @PostMapping("/panel/price/assign/save")
    public ResponseEntity saveAssing(int systems,int area){
        PriceSecurity security =securityService.selectOne(new EntityWrapper<PriceSecurity>().eq("systems",systems));
        if(null!=security){
            return ResponseEntity.ok("message.price.security.done");
        }
        PriceAssign assign = assignService.selectOne(new EntityWrapper<PriceAssign>().eq("area",area));
        if(null!=assign){
            return ResponseEntity.ok("message.price.assigned");
        }
        assign=new PriceAssign();
        assign.setArea(area);
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        assign.setAssigner((int)shiroUser.getId());
        assign.setAssignTime(DateTime.now().toDate());
        assign.setSystems(systems);
        return ResponseEntity.ok(assignService.saveAssign(assign));
    }

    @PostMapping("/panel/price/assign/delete")
    public ResponseEntity deleteAssign(int assignId){
        PriceAssign assign = assignService.selectById(assignId);
        PriceSecurity security =securityService.selectOne(new EntityWrapper<PriceSecurity>().eq("systems",assign.getSystems()));
        if(security!=null){
            security.deleteById();
        }
        return ResponseEntity.ok(assignService.deleteById(assignId));
    }

    @GetMapping("/panel/price/securitys/users")
    public ResponseEntity getUserList(int systemId){
        List<PriceAssignDto> assigns = assignService.getAssignList(systemId);
        Map<String,Object> map = Maps.newHashMap();
        if(null==assigns || assigns.size()==0){
            map.put("code","0");
            map.put("message","price.assign.unused");
            return ResponseEntity.ok(map);
        }
        if(assigns.size()>1){
            map.put("code","0");
            map.put("message","price.assign.many");
        }

        int area = assigns.get(0).getArea();
        List<User> userList = userService.selectUserListByArea(area);
        List<User> dataList=Lists.newArrayList();
        for(User user:userList){
            if(user.getId()!=((ShiroUser)SecurityUtils.getSubject().getPrincipal()).getId()){
                dataList.add(user);
            }
        }
        map.put("code","1");
        map.put("data",dataList);
        return ResponseEntity.ok(map);
    }


    /**
     * 授权操作
     * @param systemId
     * @param user
     * @return 对屏体价格体系进行授权操作
     */
    @PostMapping("/panel/price/security/save")
    public ResponseEntity saveSecurity(int systemId,int user){
        PriceSecurity security = securityService.selectOne(new EntityWrapper<PriceSecurity>().eq("systems",systemId));
        if(user==0 && security==null){ //没有权限且目标操作为收回权限，直接提示成功
            return ResponseEntity.ok(true);
        }else if(user==0){ //目标是收回权限
            return ResponseEntity.ok(security.deleteById());
        }

        List<PriceAssign> assigns = assignService.selectList(new EntityWrapper<PriceAssign>().eq("systems",systemId));
        if(null==assigns || assigns.size()==0){
            return ResponseEntity.ok("price.assign.unused");
        }
        if(assigns.size()>1){
            return ResponseEntity.ok("price.assign.many");
        }
        if(null==security){
            security=new PriceSecurity();
            security.setSystems(systemId);
        }
        security.setAccepter(user);
        security.setArea(assigns.get(0).getArea());
        security.setAssigner((int)((ShiroUser)SecurityUtils.getSubject().getPrincipal()).getId());
        security.setAssignTime(new Date());
        return ResponseEntity.ok(security.insertOrUpdate());
    }

}

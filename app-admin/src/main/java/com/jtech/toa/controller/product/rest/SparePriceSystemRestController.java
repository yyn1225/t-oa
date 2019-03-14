package com.jtech.toa.controller.product.rest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.prices.SparePriceAssign;
import com.jtech.toa.entity.prices.SparePriceDetails;
import com.jtech.toa.entity.prices.SparePriceSystem;
import com.jtech.toa.model.dto.prices.PriceAssignDto;
import com.jtech.toa.model.dto.prices.PriceSystemDto;
import com.jtech.toa.service.prices.ISparePriceAssignService;
import com.jtech.toa.service.prices.ISparePriceDetailsService;
import com.jtech.toa.service.prices.ISparePriceSystemService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>备件价格体系控制器</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
public class SparePriceSystemRestController {

    private final IDepartmentService departmentService;
    private final ISparePriceSystemService sparePriceSystemService;
    private final ISparePriceAssignService sparePriceAssignService;
    private final ISparePriceDetailsService sparePriceDetailsService;
    private final IUserService userService;

    @Autowired
    public SparePriceSystemRestController(IDepartmentService departmentService,
                                          ISparePriceSystemService sparePriceSystemService,
                                          ISparePriceAssignService sparePriceAssignService,
                                          ISparePriceDetailsService sparePriceDetailsService,
                                          IUserService userService) {
        this.departmentService = departmentService;
        this.sparePriceSystemService = sparePriceSystemService;
        this.sparePriceAssignService = sparePriceAssignService;
        this.sparePriceDetailsService = sparePriceDetailsService;
        this.userService = userService;
    }

    @PostMapping("/spare/price/systems")
    public DataTablesOutput getPriceSystemList(@Valid @RequestBody DataTablesInput dataTablesInput){
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
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
        if(level==1){
            sparePriceSystemService.findListByPage(requestPage);
        }else{
            List<PriceSystemDto> list = Lists.newArrayList();
            requestPage.setRecords(list);
        }

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/spare/price/assign/table")
    public DataTablesOutput getPriceAssigns(@Valid @RequestBody DataTablesInput dataTablesInput){
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        Page<PriceAssignDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        if(null != dataTablesInput.getParams().get("systems")){
            sparePriceAssignService.findAssignList(requestPage, dataTablesInput.getParams().getInteger("systems"));
        }
        return DataTablesUtils.buildOut(dataTablesInput,requestPage);
    }


    /**
     * 备件价格体系应用区域关系
     */
    @PostMapping("/spare/price/assign/save")
    public ResponseEntity saveAssign(int systems, int area){
        SparePriceAssign assign = sparePriceAssignService.selectOne(new EntityWrapper<SparePriceAssign>().eq("area",area));
        if(null!=assign){
            return ResponseEntity.ok("message.price.assigned");
        }
        assign=new SparePriceAssign();
        assign.setArea(area);
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        assign.setAssigner((int)shiroUser.getId());
        assign.setAssignTime(DateTime.now().toDate());
        assign.setSystems(systems);
        return ResponseEntity.ok(sparePriceAssignService.insert(assign));
    }

    /**
     * 备件价格体系应用区域删除
     */
    @PostMapping("/spare/price/assign/delete")
    public ResponseEntity deleteAssign(int id) {
        if (id == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        SparePriceAssign sparePriceAssign = sparePriceAssignService.selectById(id);
        if (sparePriceAssign == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(sparePriceAssignService.deleteById(id));
    }

    /**
     * 备件价格体系删除
     */
    @PostMapping("/spare/price/system/delete")
    public ResponseEntity deleteSystems(int id) throws Exception{
        if (id == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        List<SparePriceDetails> sparePricesDetailsList = sparePriceDetailsService.selectList(new EntityWrapper<SparePriceDetails>().eq("systems", id));
        boolean ok = sparePriceSystemService.deleteSparePriceSystems(id, sparePricesDetailsList);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/spare/price/save")
    public ResponseEntity saveSparePrices(String details, String name, String unit) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        SparePriceSystem priceSystem = new SparePriceSystem();
        priceSystem.setName(name);
        priceSystem.setUnit(unit);
        List<SparePriceDetails> sparePricesDetailsList;
        if (Strings.isNullOrEmpty(details)) {
            sparePricesDetailsList = Lists.newArrayList();
        }else {
            sparePricesDetailsList = JSON.parseArray(details, SparePriceDetails.class);
        }
        SparePriceSystem system = sparePriceSystemService.selectOne(new EntityWrapper<SparePriceSystem>().
                eq("area", userAppDto.getArea()).eq("name", priceSystem.getName()));
        if (system != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.PRODUCT_IS_INVALID)
                    .setMessage("message.price.repeat").createErrorModel());
        }
        boolean ok = sparePriceSystemService.savePrice(sparePricesDetailsList, priceSystem, (int)shiroUser.getId(), userAppDto.getArea());
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/spare/price/edit")
    public ResponseEntity editSparePrices(String details,Integer id) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        SparePriceSystem priceSystem = sparePriceSystemService.selectById(id);
        List<SparePriceDetails> sparePricesDetailsList;
        if (Strings.isNullOrEmpty(details)) {
            sparePricesDetailsList = Lists.newArrayList();
        }else {
            sparePricesDetailsList = JSON.parseArray(details, SparePriceDetails.class);
        }

        boolean ok = sparePriceSystemService.editPrice(sparePricesDetailsList, priceSystem, (int)shiroUser.getId(), userAppDto.getArea(),id);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }
}

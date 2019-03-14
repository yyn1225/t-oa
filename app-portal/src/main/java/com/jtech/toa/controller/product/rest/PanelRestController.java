package com.jtech.toa.controller.product.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.collect.Maps;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.core.util.WebUtils;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.model.dto.products.SkimProductDto;
import com.jtech.toa.model.query.ProductQuery;
import com.jtech.toa.service.product.IProductService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/panel/rest")
public class PanelRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PanelRestController.class);

    private final IProductService productService;

    @Autowired
    public PanelRestController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * 产品列表
     *
     * @return 产品列表
     */
    @PostMapping("/list")
    public DataTablesOutput<ProductDto> userList(
            @RequestUser RequestSubject user,
            @Valid @RequestBody DataTablesInput dataTablesInput, int area
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<ProductDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        ProductQuery query = dataTablesInput
                .getParams()
                .toJavaObject(ProductQuery.class);
        query.setLang(user.getLanguage());
        LOGGER.debug("message {}", query);
        this.productService.selectProductListByPage(requestPage, query, area);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
    
    /**
     * 产品一览表
     * @param user
     * @param dataTablesInput
     * @param area
     * @return
     */
    @PostMapping("/skimProductList")
    public DataTablesOutput<SkimProductDto> skimProductList(
    		@RequestUser RequestSubject user,
    		@Valid @RequestBody DataTablesInput dataTablesInput
    		) {
    	
    	DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
    	final Pagination pagination = dataTablesPagination.getPagination();
    	final Page<SkimProductDto> requestPage = new Page<>(
    			pagination.getCurrent(),
    			pagination.getSize(),
    			pagination.getOrderByField()
    			);
    	
    	ProductQuery query = dataTablesInput
    			.getParams()
    			.toJavaObject(ProductQuery.class);
    	query.setLang(user.getLanguage());
    	LOGGER.debug("message {}", query);
    	this.productService.selectSkimProduct(requestPage, query);
    	
    	return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
    

    
    @GetMapping("/export")
    public void export(
    		String partNo,Integer parentSeries,
    		String productType,String certificationStr,
    		String color,String status,
    		@RequestUser RequestSubject user,
    		HttpServletResponse resp
    		) {
       
        Map<String, String> headerMap =new HashMap<>();
        headerMap.put("productSeries", "产品型号");
        headerMap.put("productConfiguration", "配置");
        headerMap.put("certification", "认证");
        headerMap.put("partNo", "成品料");
        headerMap.put("releaseTime", "上市时间");
        headerMap.put("updateTime", "更新时间");
        headerMap.put("productType", "产品类型");
        headerMap.put("productStatus", "产品状态");
        headerMap.put("color", "标准颜色");
        headerMap.put("pitch", "间距");
        headerMap.put("control", "标配控制系统");
        headerMap.put("calibration", "校正");
        headerMap.put("intelligent", "智能监控&智慧模组");
        headerMap.put("rigging", "吊装数量");
        headerMap.put("stack", "落地");
        headerMap.put("front", "前安装");
        headerMap.put("fixModual", "模组");
        headerMap.put("fixPsu", "电源&其它");
        headerMap.put("boxWidth", "箱体宽");
        headerMap.put("boxHeight", "箱体高");
        headerMap.put("boxThickness", "箱体厚");
        headerMap.put("boxWeight", "单箱重量");
        headerMap.put("boxType", "箱体类型");
        headerMap.put("modualWidth", "模组宽");
        headerMap.put("modualHeight", "模组高");
        headerMap.put("modualWeight", "模组重量");
        headerMap.put("ipRating", "防护等级");
        headerMap.put("brightness", "亮度");
        headerMap.put("contrastRatio", "对比度");
        headerMap.put("grayScale", "灰度等级");
        headerMap.put("refresh", "刷新率");
        headerMap.put("viewing", "可视角度");
        headerMap.put("powerMax", "功耗最大");
        headerMap.put("powerAvg", "功耗平均");
        headerMap.put("ledType", "LED类型");
        headerMap.put("drivingType", "驱动方式");
        headerMap.put("psu", "电源");
        headerMap.put("carryHigh", "标准带载 220V");
        headerMap.put("carryLower", "标准带载 110V");
        headerMap.put("receivedCard", "接收卡");
    	
        ProductQuery query=new ProductQuery();
        query.setPartNo(partNo);
        query.setParentSeries(parentSeries);
        query.setProductType(productType);
        query.setCertificationStr(certificationStr);
        query.setColor(color);
        query.setStatus(status);
    	final Page<SkimProductDto> requestPage = new Page<>(1,Integer.MAX_VALUE);
    	
    	query.setLang(user.getLanguage());
    	LOGGER.debug("message {}", query);
    	this.productService.selectSkimProduct(requestPage, query);
    	List<SkimProductDto> data=requestPage.getRecords();
    	Map<String,String> convertMap=Maps.newHashMap();
    	convertMap.put("0", "X");
		convertMap.put("1", "√");
    	List<SkimProductDto> list=data.stream().map(x->{
    		x.setCalibration(convertMap.get(x.getCalibration()));
    		x.setIntelligent(convertMap.get(x.getIntelligent()));
    		x.setStack(convertMap.get(x.getStack()));
    		x.setFront(convertMap.get(x.getFront()));
    		return x;
    	}).collect(Collectors.toList());
    	if(CollectionUtils.isNotEmpty(list)) {
    		WebUtils.exportExcelObj(resp, list, "产品一览表-"+LocalDate.now()+".xlsx",headerMap);
    	}
    }
}

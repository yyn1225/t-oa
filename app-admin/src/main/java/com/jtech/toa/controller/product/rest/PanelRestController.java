package com.jtech.toa.controller.product.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.core.util.WebUtils;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.model.dto.products.ProductExportDto;
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
            @Valid @RequestBody DataTablesInput dataTablesInput, int area
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        final Page<ProductDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        ProductQuery query = dataTablesInput
                .getParams()
                .toJavaObject(ProductQuery.class);
        //获取登录用户的语言
        query.setLang(shiroUser.getDeptName());
        LOGGER.debug("message {}", query);
        this.productService.selectProductListByPage(requestPage, query, area);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
    
    /**
     * 模组列表
     *
     * @return 模组列表列表
     */
    @GetMapping("/export")
    public void export(String partNo,String series,String configuration,
    		String certification,String status,int area,HttpServletResponse resp
    		) {
       
        Map<String, String> headerMap =new HashMap<>();
        headerMap.put("parentSeriesName", "系列");
        headerMap.put("productSeries", "产品");
        headerMap.put("partNo", "物料号");
        headerMap.put("productConfiguration", "配置");
        headerMap.put("certification", "认证");
        headerMap.put("featured", "主推");
        headerMap.put("productType", "产品类型");
        headerMap.put("productStatus", "状态");
    	
        ProductQuery query=new ProductQuery();
        query.setPartNo(partNo);
        query.setConfiguration(configuration);
        query.setCertification(certification);
        query.setStatus(status);
        if(StringUtils.isNotBlank(series)) {
        	String[] arr=series.split(",");
        	List<Integer> seriesList=new LinkedList<>();
        	for(String a:arr) {
        		seriesList.add(Integer.valueOf(a));
        	}
        	query.setSeries(seriesList);
        }
    	final Page<ProductDto> requestPage = new Page<>(1,Integer.MAX_VALUE);
    	
    	//获取shiroUser得到登录的语言
    	final Subject subject = SecurityUtils.getSubject();
    	final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
    	query.setLang(shiroUser.getDeptName());
    	LOGGER.debug("message {}", query);
    	 this.productService.selectProductListByPage(requestPage, query, area);
    	List<ProductDto> data=requestPage.getRecords();
    	final BeanCopier copier = BeanCopier.create(ProductDto.class, ProductExportDto.class, false);
    	if(CollectionUtils.isNotEmpty(data)) {
	    	List<ProductExportDto> list=new LinkedList<>();
	        data.forEach(x->{
	        	ProductExportDto productExportDto=new ProductExportDto();
	        	copier.copy(x, productExportDto, null);
	        	list.add(productExportDto);
	        });
    		WebUtils.exportExcelObj(resp, list, "屏体维护OR物料配件-"+LocalDate.now()+".xlsx",headerMap);
    	}
    }
}

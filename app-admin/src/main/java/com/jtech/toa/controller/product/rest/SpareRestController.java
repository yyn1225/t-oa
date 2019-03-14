package com.jtech.toa.controller.product.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
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
import com.jtech.toa.model.dto.products.SpareDto;
import com.jtech.toa.model.dto.products.SpareExportDto;
import com.jtech.toa.model.query.SpareQuery;
import com.jtech.toa.service.product.ISpareService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/spare/rest")
public class SpareRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpareRestController.class);

    private final ISpareService spareService;

    @Autowired
    public SpareRestController(ISpareService spareService) {
        this.spareService = spareService;
    }

    /**
     * 备件列表
     *
     * @return 备件列表
     */
    @PostMapping("/list")
    public DataTablesOutput<SpareDto> userList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        final Page<SpareDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        SpareQuery query = dataTablesInput
                .getParams()
                .toJavaObject(SpareQuery.class);
        //获取登录用户的语言种类
        query.setLang(shiroUser.getDeptName());
        LOGGER.debug("message {}", query);
        this.spareService.selectSpareListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
    

    /**
     * 模组列表
     *
     * @return 模组列表列表
     */
    @GetMapping("/export")
    public void export(String material,String type,HttpServletResponse resp
    		) {
       
        Map<String, String> headerMap =new HashMap<>();
        headerMap.put("material", "物物料号");
        headerMap.put("type", "备件类型");
        headerMap.put("model", "型号/名称");
        headerMap.put("unit", "单位");
        headerMap.put("classify", "物料分类");
        headerMap.put("description", "物料描述");
    	
        SpareQuery query=new SpareQuery();
        query.setMaterial(material);
        query.setType(type);
    	final Page<SpareDto> requestPage = new Page<>(1,Integer.MAX_VALUE);
    	
    	//获取shiroUser得到登录的语言
    	final Subject subject = SecurityUtils.getSubject();
    	final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
    	query.setLang(shiroUser.getDeptName());
    	LOGGER.debug("message {}", query);
    	this.spareService.selectSpareListByPage(requestPage, query);
    	List<SpareDto> data=requestPage.getRecords();
    	final BeanCopier copier = BeanCopier.create(SpareDto.class, SpareExportDto.class, false);
    	
    	if(CollectionUtils.isNotEmpty(data)) {
	    	List<SpareExportDto> list=new LinkedList<>();
	        for(SpareDto x:data) {
	        	SpareExportDto spareExportDto=new SpareExportDto();
	        	copier.copy(x, spareExportDto, null);
	        	list.add(spareExportDto);
	        }
    		WebUtils.exportExcelObj(resp, list, "备件维护-"+LocalDate.now()+".xlsx",headerMap);
    	}
    }
}

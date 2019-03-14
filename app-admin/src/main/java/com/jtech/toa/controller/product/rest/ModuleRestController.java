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
import com.jtech.toa.model.dto.products.ModuleDto;
import com.jtech.toa.model.dto.products.ModuleExportDto;
import com.jtech.toa.model.query.ModuleQuery;
import com.jtech.toa.service.product.IModuleService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/module/rest")
public class ModuleRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRestController.class);

    private final IModuleService moduleService;

    @Autowired
    public ModuleRestController(IModuleService moduleService) {
        this.moduleService = moduleService;
    }
    /**
     * 模组列表
     *
     * @return 模组列表列表
     */
    @PostMapping("/list")
    public DataTablesOutput<ModuleDto> userList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<ModuleDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        ModuleQuery query = dataTablesInput
                .getParams()
                .toJavaObject(ModuleQuery.class);
        //获取shiroUser得到登录的语言
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        query.setLang(shiroUser.getDeptName());
        LOGGER.debug("message {}", query);
        this.moduleService.selectModuleListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
    
    /**
     * 模组列表
     *
     * @return 模组列表列表
     */
    @GetMapping("/export")
    public void export(String scnNo,String configuration,HttpServletResponse resp
    		) {
       
        Map<String, String> headerMap =new HashMap<>();
        headerMap.put("scnNo", "物料号");
        headerMap.put("height", "高度(mm)");
        headerMap.put("width", "宽度(mm)");
        headerMap.put("transverse", "横向分辨率");
        headerMap.put("portrait", "纵向分辨率");
        headerMap.put("pitch", "间距(mm)");
        headerMap.put("density", "密度");
        headerMap.put("lamp", "LED灯数量");
        headerMap.put("configuration", "LED灯类型");
        headerMap.put("weight", "单个重量(KG)");
    	
    	ModuleQuery moduleQuery=new ModuleQuery();
    	moduleQuery.setConfiguration(configuration);
    	moduleQuery.setScnNo(scnNo);
    	final Page<ModuleDto> requestPage = new Page<>(1,Integer.MAX_VALUE);
    	
    	//获取shiroUser得到登录的语言
    	final Subject subject = SecurityUtils.getSubject();
    	final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
    	moduleQuery.setLang(shiroUser.getDeptName());
    	LOGGER.debug("message {}", moduleQuery);
    	this.moduleService.selectModuleListByPage(requestPage, moduleQuery);
    	List<ModuleDto> data=requestPage.getRecords();
    	final BeanCopier copier = BeanCopier.create(ModuleDto.class, ModuleExportDto.class, false);
    	if(CollectionUtils.isNotEmpty(data)) {
	    	List<ModuleExportDto> list=new LinkedList<>();
	        for(ModuleDto x:data) {
	        	ModuleExportDto moduleExportDto=new ModuleExportDto();
	        	copier.copy(x, moduleExportDto, null);
	        	list.add(moduleExportDto);
	        }
    		WebUtils.exportExcelObj(resp, list, "模组维护-"+LocalDate.now()+".xlsx",headerMap);
    	}
    }
}

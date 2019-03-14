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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.core.util.WebUtils;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.model.dto.products.SeriesExportDto;
import com.jtech.toa.model.query.SeriesQuery;
import com.jtech.toa.service.product.ISeriesService;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/series/rest")
public class SeriesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesRestController.class);
    private final ISeriesService seriesService;

    @Autowired
    public SeriesRestController(ISeriesService seriesService) {
        this.seriesService = seriesService;
    }

    /**
     * 系列列表
     *
     * @return 产品列表
     */
    @PostMapping("/list")
    public DataTablesOutput<SeriesDto> seriesList(
            @Valid @RequestBody DataTablesInput dataTablesInput) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final Page<SeriesDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        SeriesQuery query = null;
        if (dataTablesInput.hasParasm()) {
            query = dataTablesInput
                    .getParams()
                    .toJavaObject(SeriesQuery.class);

            LOGGER.debug("message {}", query);
        }
        seriesService.findSeriesByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    /**
     * 系列列表
     *
     * @return 产品图片列表
     */
    @PostMapping("/img/list")
    public DataTablesOutput<SeriesDto> seriesImgList(
            @Valid @RequestBody DataTablesInput dataTablesInput) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final Page<SeriesDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        SeriesQuery query = null;
        if (dataTablesInput.hasParasm()) {
            query = dataTablesInput
                    .getParams()
                    .toJavaObject(SeriesQuery.class);

            LOGGER.debug("message {}", query);
        }
        seriesService.selectSeriesImgListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }


    @PostMapping("/edit")
    public ResponseEntity editStatus(int id, int status) {
        Series series = seriesService.selectById(id);
        if (series != null) {
            series.setStatus(status);
            return ResponseEntity.ok(series.updateById());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                .setMessage("alert.message.systemError").createErrorModel());
    }
    
    
    /**
     * 模组列表
     *
     * @return 模组列表列表
     */
    @GetMapping("/export")
    public void export(boolean flag,String allName,HttpServletResponse resp
    		) {
       
        Map<String, String> headerMap =new HashMap<>();
        headerMap.put("pName", "系列");
        headerMap.put("text", "产品");
        headerMap.put("status", "状态");
    	
        SeriesQuery query=new SeriesQuery();
        query.setFlag(flag);
        query.setAllName(allName);
    	final Page<SeriesDto> requestPage = new Page<>(1,Integer.MAX_VALUE);
    	
    	//获取shiroUser得到登录的语言
    	final Subject subject = SecurityUtils.getSubject();
    	final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
    	query.setLang(shiroUser.getDeptName());
    	LOGGER.debug("message {}", query);
    	seriesService.selectSeriesImgListByPage(requestPage, query);
    	List<SeriesDto> data=requestPage.getRecords();
    	final BeanCopier copier = BeanCopier.create(SeriesDto.class, SeriesExportDto.class, false);
    	if(CollectionUtils.isNotEmpty(data)) {
	    	List<SeriesExportDto> list=new LinkedList<>();
	        for(SeriesDto x:data) {
	        	SeriesExportDto seriesExportDto=new SeriesExportDto();
	        	copier.copy(x, seriesExportDto, null);
	        	list.add(seriesExportDto);
	        }
    		WebUtils.exportExcelObj(resp, list, "产品管理-"+LocalDate.now()+".xlsx",headerMap);
    	}
    }
}

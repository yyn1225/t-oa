package com.jtech.toa.controller.product.rest;

import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.core.util.WebUtils;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.ProductBoxLang;
import com.jtech.toa.model.dto.products.BoxDto;
import com.jtech.toa.model.dto.products.BoxExportDto;
import com.jtech.toa.model.query.BoxQuery;
import com.jtech.toa.service.impl.product.BoxServiceImpl;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IProductBoxLangService;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/box/rest")
public class BoxRestController {

    private final IBoxService boxService;
    private final IProductBoxLangService productBoxLangService;

    @Autowired
    public BoxRestController (IBoxService boxService, IProductBoxLangService productBoxLangService){
        this.boxService = boxService;
        this.productBoxLangService = productBoxLangService;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(BoxServiceImpl.class);

    /**
     * 箱体信息
     *
     * @return 产品信息 箱体信息
     */
    @PostMapping("/list")
    public DataTablesOutput<BoxDto> boxList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        final Page<BoxDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        BoxQuery query = dataTablesInput
                .getParams()
                .toJavaObject(BoxQuery.class);
        query.setLang(shiroUser.getDeptName());
        LOGGER.debug("message {}", query);
        this.boxService.selectBoxListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    /**
     * 模组新增,编辑
     *
     * @return 页面提示
     */
    @PostMapping("/save")
    public ResponseEntity save(BoxDto boxDto, String lang) {
        List<ProductBoxLang> boxLangs = JSONArray.parseArray(lang, ProductBoxLang.class);
        Optional<Box> boxByScnNo = boxService.findByScnNo(boxDto.getScnNo());
        for (ProductBoxLang boxLang : boxLangs) {
            if ("zh".equals(boxLang.getLang())) {
                boxDto.setBoxType(boxLang.getType());
            }
        }
        if (boxDto.getId() <= 0) {
            if(boxByScnNo.isPresent()){
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = boxService.addBox(boxDto, boxLangs);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        } else {
            List<ProductBoxLang> boxLangList = productBoxLangService.selectList(new EntityWrapper<ProductBoxLang>().
                    eq("box", boxDto.getId()));
            Map<String,ProductBoxLang> map = Maps.newHashMap();
            for (ProductBoxLang productBoxLang : boxLangList) {
                map.put(productBoxLang.getLang(), productBoxLang);
            }
            List<ProductBoxLang> updateList = Lists.newArrayList();
            List<ProductBoxLang> newList = Lists.newArrayList();

            for(ProductBoxLang lan:boxLangs) {
                if(map.containsKey(lan.getLang())) {
                    lan.setId(map.get(lan.getLang()).getId());
                    lan.setUpdateTime(new Date());
                    updateList.add(lan);
                }else{
                    lan.setCreateTime(new Date());
                    newList.add(lan);
                }
            }
            if (boxByScnNo.isPresent() && !((boxDto.getId() == boxByScnNo.get().getId()))) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = boxService.updateBox(boxDto, updateList, newList);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        }
        return ResponseEntity.ok(boxDto);
    }

    /**
     * 模组列表
     *
     * @return 模组列表列表
     */
    @GetMapping("/export")
    public void export(String scnNo,String modual,HttpServletResponse resp
    		) {
       
        Map<String, String> headerMap =new HashMap<>();
        headerMap.put("scnNo", "物料号");
        headerMap.put("modual", "使用模组");
        headerMap.put("transverseCount", "横向数量");
        headerMap.put("portraitCount", "纵向数量");
        
        headerMap.put("weight", "单个重量(KG)");
        headerMap.put("transversePix", "横向分辨率");
        headerMap.put("portraitPix", "纵向分辨率");
        headerMap.put("height", "高度");
        headerMap.put("width", "宽度");
        headerMap.put("width", "宽度");
        headerMap.put("thickness", "厚度");
        headerMap.put("boxType", "箱体材质");
    	
        BoxQuery query=new BoxQuery();
        query.setModual(modual);
        query.setScnNo(scnNo);
    	final Page<BoxDto> requestPage = new Page<>(1,Integer.MAX_VALUE);
    	
    	//获取shiroUser得到登录的语言
    	final Subject subject = SecurityUtils.getSubject();
    	final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
    	query.setLang(shiroUser.getDeptName());
    	LOGGER.debug("message {}", query);
    	this.boxService.selectBoxListByPage(requestPage, query);
    	List<BoxDto> data=requestPage.getRecords();
    	
    	final BeanCopier copier = BeanCopier.create(BoxDto.class, BoxExportDto.class, false);
    	if(CollectionUtils.isNotEmpty(data)) {
	    	List<BoxExportDto> list=new LinkedList<>();
	        data.forEach(x->{
	        	BoxExportDto boxExportDto=new BoxExportDto();
	        	copier.copy(x, boxExportDto, null);
	        	list.add(boxExportDto);
	        });
    		WebUtils.exportExcelObj(resp, list, "箱体维护-"+LocalDate.now()+".xlsx",headerMap);
    	}
    }
}

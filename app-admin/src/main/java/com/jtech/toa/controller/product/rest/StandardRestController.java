package com.jtech.toa.controller.product.rest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.StandardQuery;
import com.jtech.toa.service.impl.product.BoxServiceImpl;
import com.jtech.toa.service.product.IStandardService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/standard/rest")
public class StandardRestController {

    private final IStandardService standardService;

    @Autowired
    public StandardRestController(IStandardService standardService) {
        this.standardService = standardService;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(BoxServiceImpl.class);

    /**
     * 备件产品关联信息table展示
     *
     * @return 备件产品关联信息
     */
    @PostMapping("/list")
    public DataTablesOutput<StandardDto> standardList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        final Page<StandardDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        StandardQuery query = dataTablesInput
                .getParams()
                .toJavaObject(StandardQuery.class);
        query.setLang(shiroUser.getDeptName());
        LOGGER.debug("message {}", query);
        this.standardService.selectStandardListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @GetMapping("/delete")
    public ResponseEntity standardDelete(@RequestParam("standardId") int standardId
    ) {
        boolean isOk = standardService.deleteStandard(standardId);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/save")
    public ResponseEntity save(String sids,
                               int pid,
                               int tid) {
        List<String> lists = JSON.parseArray(sids, String.class);
        for(String sid :lists){
            boolean ok =  standardService.addStandard(sid, pid, tid);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        }
//        for(StandardDto s: standardDtos){
//            standardService.updateStandard(s);
//        }
        return ResponseEntity.ok(String.valueOf(ProductCode.SUCCESS_CODE.getCode()));
    }
}

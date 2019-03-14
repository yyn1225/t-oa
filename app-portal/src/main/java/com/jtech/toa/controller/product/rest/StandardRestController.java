package com.jtech.toa.controller.product.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.StandardQuery;
import com.jtech.toa.service.product.IStandardService;
import com.jtech.toa.service.impl.product.BoxServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public StandardRestController(IStandardService standardService){
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
            @Valid @RequestBody DataTablesInput dataTablesInput, @RequestUser RequestSubject user
            ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final Page<StandardDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        StandardQuery query = dataTablesInput
                .getParams()
                .toJavaObject(StandardQuery.class);
        query.setLang(user.getLanguage());
        LOGGER.debug("message {}", query);
        this.standardService.selectStandardListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }








}

/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.controller.product.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.StandardQuery;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISeriesStandardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/series/count")
public class SeriesCountRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesCountRestController.class);
    private final ISeriesService seriesService;
    private final ISeriesStandardService seriesStandardService;

    @Autowired
    public SeriesCountRestController(ISeriesService seriesService, ISeriesStandardService seriesStandardService) {
        this.seriesService = seriesService;
        this.seriesStandardService = seriesStandardService;
    }



    /**
     * 系列列表
     *
     * @return 产品列表
     */
    @PostMapping("/series/list")
    public DataTablesOutput<StandardDto> seriesAndspareList(
            @Valid @RequestBody DataTablesInput dataTablesInput) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<StandardDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        StandardQuery query = dataTablesInput
                .getParams()
                .toJavaObject(StandardQuery.class);
        LOGGER.debug("message {}", query);
        this.seriesStandardService.selectStandardListByPage(requestPage, query);
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/save")
    public ResponseEntity save(Integer seriesId, String standardIds, Integer type) {
        boolean isOk = seriesStandardService.addStandard(standardIds, seriesId, type);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/year/save")
    public ResponseEntity yearSave(String yearsJsonStr, Integer standardId, Integer auto) {
        boolean isOk = seriesStandardService.saveByStandard(standardId, yearsJsonStr, auto);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/delete")
    public ResponseEntity standardDelete(@RequestParam("standardId") int standardId
    ) {
        boolean isOk = seriesStandardService.deleteById(standardId);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}

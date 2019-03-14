package com.jtech.toa.controller.price.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.model.dto.prices.AreaPricesDetailDto;
import com.jtech.toa.model.query.AreaPricesDetailQuery;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.service.prices.IPricesDetailsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/price/rest")
public class PriceRestController {
    private IPricesDetailsService pricesDetailsService;

    @Autowired
    public PriceRestController(IPricesDetailsService pricesDetailsService) {
        this.pricesDetailsService = pricesDetailsService;
    }

    /**
     * 表格数据加载
     */
    @PostMapping("/datagrid")
    public DataTablesOutput<AreaPricesDetailDto> priceList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<AreaPricesDetailDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        AreaPricesDetailQuery query = dataTablesInput
                .getParams()
                .toJavaObject(AreaPricesDetailQuery.class);
        //当选择了区域时，才进行查询
        if (query.getArea() != null) {
            this.pricesDetailsService.findAreaPriceByPage(requestPage, query);
            if (CollectionUtils.isNotEmpty(requestPage.getRecords())) {
                for (AreaPricesDetailDto areaPricesDetail : requestPage.getRecords()) {
                    areaPricesDetail.setUnit(new SapRate().getKeyCode().get(areaPricesDetail.getUnit()));
                }
            }
        }
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }
}

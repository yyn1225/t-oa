package com.jtech.toa.controller.seriesproduct.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.model.dto.files.FileDto;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.FileQuery;
import com.jtech.toa.model.query.StandardQuery;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISeriesStandardService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.product.ISpecialService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/sp/rest")
public class SeriesProductRestController {

    private final ISeriesStandardService seriesStandardService;
    private final ISpareService spareService;
    private final IParamsService paramsService;
    private final IProductService productService;
    private final IBoxService boxService;
    private final IPriceService priceService;
    private final ISpecialService specialService;
    private final IConfigurationService configurationService;
    private final ISeriesService seriesService;
    private final ICertificationService certificationService;
    private final IDictService dictService;
    private final IFileService fileService;


    @Autowired
    public SeriesProductRestController(ISeriesStandardService seriesStandardService, ISeriesService seriesService,
                                   ISpareService spareService,
                                   IFileService fileService, IParamsService paramsService,
                                   IProductService productService, IBoxService boxService,
                                   IPriceService priceService, ISpecialService specialService,
                                   IConfigurationService configurationService,
                                   ICertificationService certificationService, IDictService dictService) {
        this.seriesStandardService = seriesStandardService;
        this.seriesService = seriesService;
        this.spareService = spareService;
        this.paramsService = paramsService;
        this.productService = productService;
        this.boxService = boxService;
        this.priceService = priceService;
        this.specialService = specialService;
        this.configurationService = configurationService;
        this.certificationService = certificationService;
        this.dictService = dictService;
        this.fileService= fileService;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesProductRestController.class);

    @PostMapping("/file")
    public DataTablesOutput<FileDto> datagrid(@Valid @RequestBody DataTablesInput dataTablesInput,
                                              @RequestUser RequestSubject user) {
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();

        final Page<FileDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
     if (dataTablesInput.hasParasm()) {
         //走查询方法
        FileQuery fileQuery = dataTablesInput
                .getParams()
                .toJavaObject(FileQuery.class);
        fileQuery.setUserId((long)user.getId());

        fileService.selectBySecurity(requestPage, fileQuery);

    } else {
        FileQuery fileQuery = new FileQuery();
        fileQuery.setUserId((long)user.getId());
        fileService.selectBySecurity(requestPage, fileQuery);
    }
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    /**
     * 系列列表
     *
     * @return 产品列表
     */
    @PostMapping("/spare")
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

}

package com.jtech.toa.controller.file.rest;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.model.dto.files.FileDto;
import com.jtech.toa.model.query.AdminFileQuery;
import com.jtech.toa.model.query.SeriesQuery;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.xiaoleilu.hutool.util.CollectionUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api/series")
public class FileSeriesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSeriesRestController.class);

    private final IFileService fileService;
    private final ISeriesService seriesService;

    @Autowired
    public FileSeriesRestController(IFileService fileService, ISeriesService seriesService) {
        this.fileService = fileService;
        this.seriesService = seriesService;
    }

    @PostMapping("/datagrid")
    public DataTablesOutput<FileDto> datagrid(@Valid @RequestBody DataTablesInput dataTablesInput) {
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();

        final Page<FileDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
//        if (dataTablesInput.hasParasm()) {
            // 走查询方法
        AdminFileQuery fileQuery = dataTablesInput
                    .getParams()
                    .toJavaObject(AdminFileQuery.class);

            LOGGER.debug("message {}", fileQuery);
            fileService.selectBySeriesAndPage(requestPage, fileQuery);

//        }
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    /**
     * 返回产品系列树
     *
     * @return 产品系列树
     */
    @GetMapping("/tree")
    public ResponseEntity series(String name) {
        final String lang = "zh";
        final SeriesQuery query = new SeriesQuery();
        query.setLang("zh");
        query.setName(name);
        final List<ZTreeVO<TreeDataVO>> treeVOS = seriesService.findAllToTree(query);

        List<ZTreeVO<TreeDataVO>> parents = Lists.newArrayList();

        if (CollectionUtil.isEmpty(treeVOS)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("未找到系列").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        parents.addAll(treeVOS);
        return ResponseEntity.ok(parents);
    }
}

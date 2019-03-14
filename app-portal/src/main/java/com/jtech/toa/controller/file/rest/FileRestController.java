package com.jtech.toa.controller.file.rest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.collect.Lists;
import com.jtech.marble.StringPool;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.file.FileMarketDetail;
import com.jtech.toa.entity.file.FilePackage;
import com.jtech.toa.model.dto.files.FileDto;
import com.jtech.toa.model.query.FileMarketDetailQuery;
import com.jtech.toa.model.query.FileQuery;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.service.file.IFileMarketDetailService;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/file/rest")
public class FileRestController {
    private final IFileService fileService;
    private final IFilePackageService filePackageService;
    private final IFileMarketDetailService fileMarketDetailService;

    @Autowired
    public FileRestController(IFileService fileService, IFilePackageService filePackageService,IFileMarketDetailService fileMarketDetailService) {
        this.fileService = fileService;
        this.filePackageService = filePackageService;
        this.fileMarketDetailService = fileMarketDetailService;
    }

    @PostMapping("/datagrid")
    public DataTablesOutput<FileDto> datagrid(@Valid @RequestBody DataTablesInput dataTablesInput, @RequestUser RequestSubject user) {
        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<FileDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        FileQuery fileQuery;
        if (dataTablesInput.hasParasm()) {
            // 走查询方法
            fileQuery = dataTablesInput
                    .getParams()
                    .toJavaObject(FileQuery.class);

        } else {
            fileQuery = new FileQuery();
        }
        fileQuery.setUserId((long)user.getId());
        fileQuery.setLang(user.getLanguage());
        fileService.selectBySecurity(requestPage, fileQuery);
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/page/data")
    public ResponseEntity pageData(@RequestUser RequestSubject user,
                                   int pageLength,
                                   int pageSize,
                                   @RequestParam(required = false) String q){
        FileQuery fileQuery = JSON.parseObject(q,FileQuery.class);
        if(fileQuery == null){
            fileQuery = new FileQuery();
        }
        final Page<FileDto> requestPage = new Page<>(
                pageLength,
                pageSize,
                StringPool.EMPTY
        );
        fileQuery.setUserId((long)user.getId());
        fileQuery.setLang(user.getLanguage());
        fileService.selectBySecurity(requestPage, fileQuery);
        return ResponseEntity.ok(requestPage.getRecords());
    }

    @GetMapping("/tree")
    public ResponseEntity tree(@RequestParam(required = false) String q) {
        final List<ZTreeVO<TreeDataVO>> treeVOS = filePackageService.findAllToTree(q);
        List<ZTreeVO<TreeDataVO>> parents = Lists.newArrayList();

        if (CollectionUtil.isEmpty(treeVOS)) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("未找到文件夹").createErrorModel();
            return ResponseEntity.badRequest().body(errorModel);
        }
        parents.addAll(treeVOS);
        return ResponseEntity.ok(parents);
    }

    @GetMapping("/scene/data")
    public ResponseEntity sceneData(){
        List<FilePackage> filePackages = filePackageService.sceneData();
        return ResponseEntity.ok(filePackages);
    }

    @GetMapping("/market/details")
    public ResponseEntity fileMarketDetail(@RequestUser RequestSubject user){
        FileMarketDetailQuery query = new FileMarketDetailQuery();
        query.setLang(user.getLanguage());
        List<FileMarketDetail> fileMarketDetails = fileMarketDetailService.selectSpareListAll(query);
        return ResponseEntity.ok(fileMarketDetails);
    }

}

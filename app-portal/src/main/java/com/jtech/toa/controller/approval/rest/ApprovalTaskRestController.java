package com.jtech.toa.controller.approval.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

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

import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.model.dto.offer.OfferListDto;
import com.jtech.toa.model.query.OfferQuery;
import com.jtech.toa.service.approval.IApprovalTaskService;
import com.jtech.toa.service.offer.IOfferService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/approval/rest")
public class ApprovalTaskRestController {

    private final IOfferService offerService;
    private final IApprovalTaskService approvalTaskService;

    @Autowired
    public ApprovalTaskRestController(IOfferService offerService, IApprovalTaskService approvalTaskService) {
        this.offerService = offerService;
        this.approvalTaskService = approvalTaskService;
    }

    @PostMapping("/list")
    public DataTablesOutput<OfferListDto> userList(
            @Valid @RequestBody DataTablesInput dataTablesInput, @RequestUser RequestSubject user,
            Integer status
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<OfferListDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        OfferQuery query = dataTablesInput.getParams().toJavaObject(OfferQuery.class);
        query.setOptUser(user.getId());
        if (status != null) {
            query.setStatus(status);
        }
        this.offerService.selectApprovalList(query, requestPage);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/launch")
    public DataTablesOutput<OfferListDto> offerList(
            @Valid @RequestBody DataTablesInput dataTablesInput, @RequestUser RequestSubject user
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<OfferListDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        OfferQuery query = dataTablesInput
                .getParams()
                .toJavaObject(OfferQuery.class);
        query.setUserId(user.getId());
        this.offerService.selectLaunchList(query, requestPage);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @GetMapping("/retract")
    public ResponseEntity retract(Long id) {
        boolean ok = approvalTaskService.updateRetract(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/examine")
    public ResponseEntity examine(Long approvalId, Integer state, String opinion) {
        approvalTaskService.upTaskStatus(approvalId,state,opinion);
        return ResponseEntity.ok().build();
    }
}

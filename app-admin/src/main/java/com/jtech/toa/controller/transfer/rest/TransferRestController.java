package com.jtech.toa.controller.transfer.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.offer.OfferTransfer;
import com.jtech.toa.model.dto.offer.OfferTransferDto;
import com.jtech.toa.service.offer.IOfferTransferService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/transfer/rest")
public class TransferRestController {
    private final IOfferTransferService offerTransferService;

    @Autowired
    public TransferRestController(IOfferTransferService offerTransferService) {
        this.offerTransferService = offerTransferService;
    }

    /**
     * 物流补充信息
     *
     * @return 物流补充信息
     */
    @PostMapping("/list")
    public DataTablesOutput<OfferTransferDto> offerTransferList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<OfferTransferDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        this.offerTransferService.selectByPage(requestPage);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/save")
    public ResponseEntity save(Long transferId, BigDecimal cost) {
        OfferTransfer offerTransfer = new OfferTransfer();
        offerTransfer.setId(transferId);
        offerTransfer.setCost(cost);
        boolean ok = offerTransferService.updateById(offerTransfer);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("系统内部错误").createErrorModel());
        }
        return ResponseEntity.ok().build();
    }
}

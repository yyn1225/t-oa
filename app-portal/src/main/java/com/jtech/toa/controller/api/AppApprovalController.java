package com.jtech.toa.controller.api;

import com.google.common.collect.Lists;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.jtech.marble.error.ErrorModel;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.sys.ApprovalTaskDto;
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
@RequestMapping("/api/approval")
public class AppApprovalController {
    private IApprovalTaskService approvalTaskService;
    private IOfferService offerService;

    @Autowired
    public AppApprovalController(IApprovalTaskService approvalTaskService, IOfferService offerService) {
        this.approvalTaskService = approvalTaskService;
        this.offerService = offerService;
    }

    /**
     * 查看列表
     * @param lastId 最后一条数据id
     * @param status 状态 1-未审，2-已审，3-撤回
     * @param user 登录用户，指审批人
     */
    @GetMapping("/list")
    private ResponseEntity approvalList(
            @RequestParam(value = "lastId", required = false) Long lastId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "name", required = false) String name,
            @RequestUser RequestSubject user) {
        List<ApprovalTaskDto> approvalTaskList = approvalTaskService.selectApprovalList(lastId, status, user.getId(),name);
        if (!CollectionUtils.isEmpty(approvalTaskList)) {
            return ResponseEntity.ok(approvalTaskList);
        }
        return ResponseEntity.ok(Lists.newArrayList());
    }

    /**
     * 通过报价单状态查询列表,我发起的使用这条查询,查询所有状态
     * @param lastId 最后一条数据id
     * @param status 状态 1-未审，2-通过，3-打回
     * @param user 登录用户，指发起报价单的人
     */
    @GetMapping("/my")
    private ResponseEntity myApproval(
            @RequestParam(value = "lastId", required = false) Long lastId,
            @RequestParam(value = "status", required = false) String status,
            @RequestUser RequestSubject user) {
        List<ApprovalTaskDto> approvalTaskList = approvalTaskService.selectMyApproval(lastId, status, user.getId());
        if (!CollectionUtils.isEmpty(approvalTaskList)) {
            return ResponseEntity.ok(approvalTaskList);
        }
        return null;
    }

    /**
     * 查看一条审批的报价单记录
     * @param id 报价单id
     */
    @GetMapping("/get")
    private ResponseEntity getApproval(@RequestParam(value = "lastId") long id) {
        MyOfferDto myOffer = offerService.selectOfferById(id);
        return ResponseEntity.ok(myOffer);
    }

    /**
     * 审批
     * @param state 1-同意 2-打回
     * @param approvalId 审批数据id
     * @param opinion 审批意见
     */
    @PostMapping("/edit")
    private ResponseEntity editApproval(@RequestParam(value = "state")Integer state,
                                        @RequestParam(value = "approvalId")Long approvalId,
                                        @RequestParam(value = "opinion")String opinion) {

            boolean ok = approvalTaskService.updateExamine(approvalId, state, opinion);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage("系统出错").createErrorModel());
            }
            return ResponseEntity.ok().build();
    }
    /**
     * 撤回报价单
     * @param approvalId 审批表主键
     */
    @GetMapping("/retract")
    public ResponseEntity retract(Long approvalId) {
        boolean ok = approvalTaskService.updateRetract(approvalId);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("系统出错").createErrorModel());
        }
        return ResponseEntity.ok().build();
    }
}

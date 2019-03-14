package com.jtech.toa.service.approval;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.toa.entity.approval.ApprovalTask;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.model.dto.sys.ApprovalTaskDto;
import com.jtech.toa.model.vo.OfferVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-11-29
 */
public interface IApprovalTaskService extends IService<ApprovalTask> {
    /**
     * 撤回更新方法
     * @param id 主键
     * @return 布尔值
     */
    boolean updateRetract(Long id);

    /**
     * 审批报价单
     * @param approvalId 主键
     * @param status 状态
     * @param opinion 意见
     * @return 布尔值
     */
    boolean updateExamine(long approvalId, Integer status, String opinion);

    /**
     * 判断某个报价单是否需要审批
     * @param offerVo
     * @param area
     * @param level
     * @return
     */
    public boolean needApproval(OfferVo offerVo, int area, Level level);

    /**
     * 对报价单创建审批信息
     * @param offer
     * @param area
     * @return
     */
    public boolean saveApproval(Offer offer, int area);

    /**
     * 通过最后一位id和状态查询列表
     * @param lastId 最后一条数据的主键
     * @param status 审批状态
     * @param userId 审批人
     * @return 审批列表
     */
    List<ApprovalTaskDto> selectApprovalList(Long lastId, String status, int userId,String name);

    /**
     * 通过最后一位id和状态查询订单列表
     * @param lastId 最后一条数据的主键
     * @param status 订单状态
     * @param userId 审批人
     * @return 审批列表
     */
    List<ApprovalTaskDto> selectMyApproval(Long lastId, String status, int userId);

    /**
     * 审批订单
     * @return
     */
    boolean upTaskStatus(Long approvalId, Integer state, String opinion);
}

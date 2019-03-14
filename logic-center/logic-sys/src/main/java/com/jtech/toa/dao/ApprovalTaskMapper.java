package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.approval.ApprovalTask;
import com.jtech.toa.model.dto.sys.ApprovalTaskDto;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-11-29
 */
public interface ApprovalTaskMapper extends BaseMapper<ApprovalTask> {

    /**
     * 通过最后一位id和状态查询列表
     * @param lastId 最后一条数据的主键
     * @param status 审批状态
     * @param userId 审批人
     * @return 审批列表
     */
    List<ApprovalTaskDto> selectApprovalList(@Param("lastId") Long lastId, @Param("status") String status, @Param("userId") int userId,@Param("name") String name);

    /**
     * 通过最后一位id和查询订单列表
     * @param lastId 最后一条数据的主键
     * @param status 订单状态
     * @param userId 审批人
     * @return 审批列表
     */
    List<ApprovalTaskDto> selectMyApproval(@Param("lastId") Long lastId,@Param("status") String status, @Param("userId") int userId);
}
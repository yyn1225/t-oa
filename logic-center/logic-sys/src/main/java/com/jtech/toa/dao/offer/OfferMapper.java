/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.dao.offer;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.model.dto.offer.AppOfferDto;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferListDto;
import com.jtech.toa.model.query.OfferQuery;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
public interface OfferMapper extends BaseMapper<Offer> {

    /**
     * 根据分页信息获取数据（总监专用查询）
     * @param requestPage 分页参数
     * @param query 查询参数
     * @return 列表数据
     */
    List<OfferListDto> selectListByManager(Page<OfferListDto> requestPage, @Param("query") OfferQuery query);

    /**
     * 根据分页信息获取数据
     * @param requestPage 分页参数
     * @param query 查询参数
     * @return 列表数据
     */
    List<OfferListDto> selectListByPage(Page<OfferListDto> requestPage, @Param("query") OfferQuery query);

    /**
     * 根据分页信息获取数据
     * @param requestPage 分页参数
     * @param query 查询参数
     * @return 列表数据
     */
    List<OfferListDto> selectPreferenceListByPage(Page<OfferListDto> requestPage, @Param("query") OfferQuery query);

    /**
     * 查询除了喜好之外所有我的报价单
     * @param query 查询对象
     * @return 列表数据
     */
    List<OfferListDto> selectOfferList(@Param("query") OfferQuery query);

    /**
     * 查询4条最新报价记录
     * @return 报价列表
     */
    List<MyOfferDto> selectMyOfferList(int userId);

    /**
     * 通过id查询报价单系列号
     * @param offerId 报价单id
     * @return 报价单实体类
     */
    MyOfferDto selectOfferById(@Param("offerId") Long offerId);

    /**
     * 通过id查询报价单
     * @param offerId 报价单id
     * @param lang 语言
     * @return 报价单实体类
     */
    MyOfferDto findOfferById(@Param("offerId") Long offerId, @Param("lang") String lang);

    /**
     * 查询审批流程中的报价单
     * @param query  查询条件对象
     * @param requestPage 分页对象
     * @return 报价单列表
     */
    List<OfferListDto> selectApprovalList(@Param("query") OfferQuery query, Page<OfferListDto> requestPage);

    /**
     * 查询审批流程中的报价单
     * @param query  查询条件对象
     * @param requestPage 分页对象
     * @return 报价单列表
     */
    List<OfferListDto> selectLaunchList(@Param("query") OfferQuery query, Page<OfferListDto> requestPage);

    /**
     * 查询4条报价记录
     * @param userId 当前登录人id
     * @return 审批报价记录
     */
    List<MyOfferDto> selectMyApprovalList(@Param("userId") Integer userId);

    /**
     * 获取报价单
     * @param userId
     * @return
     */
    List<AppOfferDto> findApiOfferListByPage(@Param("userId") Integer userId,
                                             @Param("lastId")long lastId,
                                             @Param("pageSize")int pageSize,
                                             @Param("status")Integer status);
    /**
     * 根据报价单Id获取数据
     * @param ids
     * @return
     */
    List<AppOfferDto> findApiOfferListByIds(@Param("ids")List<Long> ids, @Param("lang")String lang);

    /**
     * @param userId
     * @param lastId
     * @return
     */
    List<MyOfferDto> getMyOfferList(@Param("userId") int userId, @Param("lastId") long lastId, @Param("status") int status);

    /**
     * 分页查询app我的喜好
     * @param query 查询对象
     * @param lastId 最后一条数据的id
     * @return 报价单列表
     */
    List<OfferListDto> selectAppPreferenceListByPage(@Param("query") OfferQuery query, @Param("lastId") long lastId);
}
/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.offer;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.io.File;
import java.util.List;

import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.model.dto.offer.AppOfferDto;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferListDto;
import com.jtech.toa.model.query.OfferQuery;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.user.entity.Department;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
public interface IOfferService extends IService<Offer> {
	/**
	 * 保存报价单信息
	 * @param userId 用户id
	 * @param offerJson 报价单json字符串
	 * @param offer 报价单对象
	 * @param draftFlag 草稿标记
     * @return
     */
	Offer save(int userId, String offerJson, OfferVo offer, boolean draftFlag);

//	/**
//	 * 编辑报价单信息
//	 * @param offer 报价单详情
//	 * @param offerId 报价单id
//	 * @param trans 是否转交物流部
//	 * @param draftFlag 草稿标记
//	 * @param area 用户区域
//	 * @return 布尔
//	 */
//	boolean edit(OfferVo offer,boolean trans, int offerId, boolean draftFlag, int area);

	/**
	 * 通过订单主键获取订单的详情信息
	 * @param offer 报价单id
	 * @param lang 语言
	 * @return 报价单对象
	 */
	OfferVo getOfferDetails(long offer, String lang);

	/**
	 * 根据分页信息获取数据（总监专用查询）
	 * @param requestPage 分页参数
	 * @param query 查询参数
	 */
	void selectListByManager(Page<OfferListDto> requestPage, OfferQuery query);

	/**
	 * 根据分页信息获取数据
	 * @param requestPage 分页参数
	 * @param query 查询参数
	 */
	void selectListByPage(Page<OfferListDto> requestPage, OfferQuery query);

	/**
	 * 根据分页信息获取数据
	 * @param requestPage 分页参数
	 * @param query 查询参数
	 * @return 列表数据
	 */
	void selectPreferenceListByPage(Page<OfferListDto> requestPage, OfferQuery query);

	/**
	 * 查询除了喜好之外所有我的报价单
	 * @param query 查询对象
	 * @return 列表数据
	 */
	List<OfferListDto> selectOfferList(OfferQuery query);

	/**
	 * 查询4条最新报价记录
	 * @return 报价列表
	 */
	List<MyOfferDto> selectMyOfferList(int userId);

	List<MyOfferDto> getMyOfferList(int userId, long lastId,int status);

	/**
	 * 通过id查询报价单系列号
	 * @param offerId 报价单id
	 * @return 报价单实体类
	 */
	MyOfferDto selectOfferById(Long offerId);

	/**
	 * 通过id查询报价单
	 * @param offerId 报价单id
	 * @param lang 语言
	 * @return 报价单实体类
	 */
	MyOfferDto findOfferById(Long offerId, String lang);

	/**
	 * 查询审批流程中的报价单
	 * @param query  查询条件对象
	 * @param requestPage 分页对象
	 */
	void selectApprovalList(OfferQuery query, Page<OfferListDto> requestPage);

	/**
	 * 查询审批流程中的报价单
	 * @param query  查询条件对象
	 * @param requestPage 分页对象
	 */
	void selectLaunchList(OfferQuery query, Page<OfferListDto> requestPage);

	/**
	 * 查询4条报价记录
	 * @param userId 当前登录人id
	 * @return 审批报价记录
	 */
	List<MyOfferDto> selectMyApprovalList(Integer userId);

	/**
	 * 获取 报价单
	 * @param userId
	 * @param lastId
	 * @return
	 */
	List<AppOfferDto> findApiOfferListByPage(int userId, long lastId,int pageSize, Integer status, String lang);

	List<AppOfferDto> findApiOfferListByIds(List<Long> ids, String lang);

	/**
	 * 分页查询app我的喜好
	 * @param query 查询对象
	 * @param lastId 最后一条数据的id
	 * @return 报价单列表
	 */
	List<OfferListDto> selectAppPreferenceListByPage(OfferQuery query, long lastId);

	File createExcelFile(OfferVo offerVo,Department department,String lang,String filepath);

	/**
	 * 通过订单主键获取订单的详情信息
	 * @param offer 报价单id
	 * @param lang 语言
	 * @return 报价单对象
	 */
	OfferVo getOfferDetailsExport(long offer, String lang);
}

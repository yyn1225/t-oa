/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.dao.offer;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.offer.OfferTransfer;
import com.jtech.toa.model.dto.offer.OfferTransferDto;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-12-10
 */
public interface OfferTransferMapper extends BaseMapper<OfferTransfer> {

    /**
     * 分页查询物流补充列表
     * @param requestPage 分页对象
     * @return 物流补充列表
     */
    List<OfferTransferDto> selectByPage(Page<OfferTransferDto> requestPage);

    /**
     * 通过主键查询一条记录
     * @param id 物流补充表主键
     * @return 一条补充记录
     */
    OfferTransferDto selectOneById(@Param("id") long id);
}
/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.dao.offer;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jtech.toa.entity.offer.OfferService;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.model.dto.offer.OfferServiceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
public interface OfferServiceMapper extends BaseMapper<OfferService> {

    List<OfferServiceDto> selectListById(@Param("id") long id ,@Param("lang") String lang);
}
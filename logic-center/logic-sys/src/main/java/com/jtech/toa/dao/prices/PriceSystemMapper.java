/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.dao.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.PriceSystem;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.model.dto.prices.PriceSystemDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-12-24
 */
public interface PriceSystemMapper extends BaseMapper<PriceSystem> {

    List<PriceSystemDto> findListByPage(@Param("page") Page<PriceSystemDto> page, @Param("area") int area);

    List<PriceSystemDto> findListByUserPage(@Param("page") Page<PriceSystemDto> page, @Param("user") int user);

    PriceSystem getSystemByArea(@Param("area") int area, @Param("date") Date date);
}
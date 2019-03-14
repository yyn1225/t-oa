package com.jtech.toa.dao;

import com.jtech.toa.entity.system.SysExchange;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-12-05
 */
public interface SysExchangeMapper extends BaseMapper<SysExchange> {
    public List<SysExchange> getLastExchange();
}
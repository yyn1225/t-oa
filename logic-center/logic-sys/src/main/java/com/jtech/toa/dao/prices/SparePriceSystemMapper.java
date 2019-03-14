package com.jtech.toa.dao.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.PriceSystem;
import com.jtech.toa.entity.prices.SparePriceSystem;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.model.dto.prices.PriceSystemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2018-03-22
 */
public interface SparePriceSystemMapper extends BaseMapper<SparePriceSystem> {

    /**
     * <p>查询所有价格体系</p>
     * @param page 分页对象
     * @return 价格体系列表
     */
    List<PriceSystemDto> findListByPage(@Param("page") Page<PriceSystemDto> page);
}
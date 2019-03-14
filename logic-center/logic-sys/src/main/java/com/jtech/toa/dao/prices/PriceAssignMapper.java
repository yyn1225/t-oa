package com.jtech.toa.dao.prices;

import com.jtech.toa.entity.prices.PriceAssign;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.model.dto.prices.PriceAssignDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-12-25
 */
public interface PriceAssignMapper extends BaseMapper<PriceAssign> {
    public List<PriceAssignDto> findAssignList(@Param("systems") int systems);
}
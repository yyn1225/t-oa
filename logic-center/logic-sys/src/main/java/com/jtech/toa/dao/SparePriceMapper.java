package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.product.SparePrice;
import com.jtech.toa.model.dto.prices.AppSparePrice;
import com.jtech.toa.model.dto.products.SparePriceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface SparePriceMapper extends BaseMapper<SparePrice> {

    /**
     * 根据备件以及地区查找价格
     * @param spareId 备件id
     * @return 备件价格
     */
    List<SparePriceDto> selectAreaPrice(@Param("spareId") int spareId);

    List<AppSparePrice> findTop100List(@Param("area") int area,@Param("lastId") int lastId);


    List<AppSparePrice> findAllSpareList(@Param("area") int area,@Param("lastId") int lastId);
}

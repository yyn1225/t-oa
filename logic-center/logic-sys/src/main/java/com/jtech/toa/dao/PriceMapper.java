package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.model.dto.prices.AppProductPrice;
import com.jtech.toa.model.dto.products.PanelPriceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface PriceMapper extends BaseMapper<Price> {

    /**
     * 根据产品和地区查询价格
     * @param product 产品物料
     * @param area 地区
     * @return 价格
     */
    Price selectByProductAndArea(@Param("product") int product, @Param("area") int area);

    /**
     * 查询所有地区的产品成本价
     * @param productId 产品id
     * @return 价格集合
     */
    List<PanelPriceDto> selectAreaPrice(@Param("productId")int productId);

    List<AppProductPrice> findAppProductPrices(@Param("area") int area, @Param("lastId") int lastId);

    List<AppProductPrice> findAppAllProductPrices(@Param("userId") int userId, @Param("lastId") int lastId);
}

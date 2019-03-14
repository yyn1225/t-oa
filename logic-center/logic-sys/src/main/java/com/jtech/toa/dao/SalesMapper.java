package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.product.Sales;
import org.apache.ibatis.annotations.Param;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface SalesMapper extends BaseMapper<Sales> {
    /**
     * 根据产品以及地区查询销售区域配置
     * @param product 产品物料号
     * @param area 地区
     * @return 销售区域
     */
    Sales selectSalesByArea(@Param("product") int product, @Param("area") int area);
}

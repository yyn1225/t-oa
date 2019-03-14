package com.jtech.toa.service.product;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.product.Sales;
import org.apache.ibatis.annotations.Param;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ISalesService extends IService<Sales> {

    /**
     * 根据产品以及地区查询销售区域配置
     * @param product 产品物料号
     * @param area 地区
     * @return 销售区域
     */
    Sales selectSalesByArea(int product, int area);

    /**
     * 新增销售区域配置
     * @param product 产品物料号
     * @param area 地区
     * @return 布尔值
     */
    boolean saveSales(int product, int area, int status);
}

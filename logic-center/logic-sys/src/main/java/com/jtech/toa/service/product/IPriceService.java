package com.jtech.toa.service.product;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.model.dto.prices.AppProductPrice;
import com.jtech.toa.model.dto.products.PanelPriceDto;
import com.jtech.toa.model.dto.prices.SavePriceDto;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface IPriceService extends IService<Price> {

    /**
     * 根据产品和地区查询价格
     * @param product 产品物料
     * @param area 地区
     * @return 价格
     */
    Price selectByProductAndArea(int product, int area);

    /**
     * 保存价格信息
     * @param savePriceDto 保存价格信息数据传输对象
     * @return 布尔值
     */
    boolean savePriceNotZero(SavePriceDto savePriceDto);

    /**
     * 保存价格信息
     * @param savePriceDto 保存价格信息数据传输对象
     * @return 布尔值
     */
    boolean savePriceZero(SavePriceDto savePriceDto);

    /**
     * 编辑价格信息
     * @param savePriceDto 保存价格信息数据传输对象
     * @return 布尔值
     */
    boolean updatePrice(SavePriceDto savePriceDto);

    /**
     * 查询所有地区的产品成本价
     * @param productId 产品id
     * @return 价格集合
     */
    List<PanelPriceDto> selectAreaPrice(int productId);

    List<AppProductPrice> findAppProductPrices(int area, int lastId);

    List<AppProductPrice> findAppAllProductPrices(int userId, int lastId);
}

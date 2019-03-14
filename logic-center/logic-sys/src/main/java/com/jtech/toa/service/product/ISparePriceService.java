package com.jtech.toa.service.product;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.product.SparePrice;
import com.jtech.toa.model.dto.prices.AppSparePrice;
import com.jtech.toa.model.dto.products.SpareDto;
import com.jtech.toa.model.dto.products.SparePriceDto;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ISparePriceService extends IService<SparePrice> {

    /**
     * 根据备件以及地区查找价格
     * @param spareId 备件id
     * @return 备件价格
     */
    List<SparePriceDto> selectAreaPrice(int spareId);

    /**
     * 新增备件价格
     * @param spareDto 保存价格信息数据传输对象
     * @param spare 备件id
     * @return 布尔值
     */
    boolean savePrice(SpareDto spareDto, int spare);

    List<AppSparePrice> findTop100List(int area, int lastId);

    /**
     * 获取所有配件价格
     */
    List<AppSparePrice> findAllSpareList(int area, int lastId);
}

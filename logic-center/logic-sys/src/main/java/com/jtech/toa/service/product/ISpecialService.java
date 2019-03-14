package com.jtech.toa.service.product;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.product.Special;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ISpecialService extends IService<Special> {

    /**
     * 查询所有特殊要求数据
     * @return 特殊要求数据
     */
    List<Special> selectSpecialList();

    /**
     * 查询某个系列下的产品对应的所有的特殊配置信息
     * @param series
     * @return
     */
    List<Special> selectListBySeries(int series);
}

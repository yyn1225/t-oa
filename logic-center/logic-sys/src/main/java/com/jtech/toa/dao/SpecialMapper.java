package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.product.Special;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface SpecialMapper extends BaseMapper<Special> {

    /**
     * 查询所有特殊要求数据
     * @return 特殊要求数据
     */
    List<Special> selectSpecialList();

   List<Special> selectListBySeries(int series);
}

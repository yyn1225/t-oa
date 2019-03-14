package com.jtech.toa.dao.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.SparePriceAssign;
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
 * @since 2018-03-22
 */
public interface SparePriceAssignMapper extends BaseMapper<SparePriceAssign> {

    /**
     * <p>
     *  分页查询备件价格应用区域
     * </p>
     *@param systems 价格体系
     *@param requestPage 分页对象
     *@return  应用区域列表
     */
    List<PriceAssignDto> findAssignList(Page<PriceAssignDto> requestPage, @Param("systems") int systems);

    /**
     * 删除备件价格应用区域
     *
     * @Param systems 价格体系
     * @return boolean
     */

    boolean deleteBySystemId(@Param("systems") Integer systems);
}
package com.jtech.toa.service.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.SparePriceAssign;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.model.dto.prices.PriceAssignDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2018-03-22
 */
public interface ISparePriceAssignService extends IService<SparePriceAssign> {
    /**
     * <p>
     *  分页查询备件价格应用区域
     * </p>
     *@param systems 价格体系
     *@param requestPage 分页对象
     */
    void findAssignList(Page<PriceAssignDto> requestPage, int systems);

    /**
     * 删除备件价格应用区域
     *
     * @Param systems 价格体系
     * @return boolean
     */
    boolean deleteBySystemId(Integer systems);
}

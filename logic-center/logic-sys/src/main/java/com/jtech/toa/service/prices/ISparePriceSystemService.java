package com.jtech.toa.service.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.SparePriceDetails;
import com.jtech.toa.entity.prices.SparePriceSystem;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.model.dto.prices.PriceSystemDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2018-03-22
 */
public interface ISparePriceSystemService extends IService<SparePriceSystem> {
    /**
     * <p>查询所有价格体系</p>
     * @param page 分页对象
     */
    void findListByPage(Page<PriceSystemDto> page);

    /**
     * <p>查询所有价格体系</p>
     * @param id 价格体系主键
     * @param sparePricesDetailsList 价格明细集合
     * @return 布尔值
     * @exception Exception 删除失败抛出异常
     */
    boolean deleteSparePriceSystems(int id, List<SparePriceDetails> sparePricesDetailsList) throws Exception;

    /**
     * <p>查询所有价格体系</p>
     * @param priceSystem 价格体系对象
     * @param sparePricesDetailsList 价格明细集合
     * @param userId 用户id
     * @param area 用户区域
     * @return 布尔值
     */
    boolean savePrice(List<SparePriceDetails> sparePricesDetailsList, SparePriceSystem priceSystem,int userId, int area);

    /**
     * <p>查询所有价格体系</p>
     * @param priceSystem 价格体系对象
     * @param sparePricesDetailsList 价格明细集合
     * @param userId 用户id
     * @param area 用户区域
     * @param id 价格体系id
     * @return 布尔值
     */
    boolean editPrice(List<SparePriceDetails> sparePricesDetailsList, SparePriceSystem priceSystem, int userId, int area,int id);
    }

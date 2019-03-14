/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.prices.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.PriceSystem;
import com.jtech.toa.dao.prices.PriceSystemMapper;
import com.jtech.toa.entity.prices.PricesDetails;
import com.jtech.toa.model.dto.prices.PriceSystemDto;
import com.jtech.toa.service.prices.IPriceSystemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.service.prices.IPricesDetailsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-12-24
 */
@Service
public class PriceSystemServiceImpl extends ServiceImpl<PriceSystemMapper, PriceSystem> implements IPriceSystemService {

    private final IPricesDetailsService pricesDetailsService;

    @Autowired
    public PriceSystemServiceImpl(IPricesDetailsService pricesDetailsService) {
        this.pricesDetailsService = pricesDetailsService;
    }

    @Override
    public List<PriceSystemDto> findPageList(Page<PriceSystemDto> page, int area) {
        return baseMapper.findListByPage(page,area);
    }

    @Override
    public List<PriceSystemDto> findPageListByUser(Page<PriceSystemDto> page, int user) {
        return baseMapper.findListByUserPage(page,user);
    }

    @Override
    public PriceSystem getSystemByArea(int area, Date date) {
        return baseMapper.getSystemByArea(area, date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePriceSystems(int id, List<PricesDetails> pricesDetailsList) throws Exception {
        boolean ok = true;
        if (CollectionUtils.isNotEmpty(pricesDetailsList)) {
            for (PricesDetails pricesDetails : pricesDetailsList) {
                ok = pricesDetailsService.deleteById(pricesDetails.getId());
            }
        }
        if (!ok) {
            throw new Exception("删除价格明细失败");
        }
        PriceSystem priceSystem = selectById(id);
        if (priceSystem == null) {
            throw new Exception("操作异常");
        }
        ok = deleteById(id);
        return ok;
    }
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.prices.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Strings;
import com.jtech.toa.entity.prices.PriceSystem;
import com.jtech.toa.entity.prices.PricesDetails;
import com.jtech.toa.dao.prices.PricesDetailsMapper;
import com.jtech.toa.model.dto.prices.AreaPricesDetailDto;
import com.jtech.toa.model.dto.prices.PricesDetailDto;
import com.jtech.toa.model.dto.products.SparePriceDetailsDto;
import com.jtech.toa.model.query.AreaPricesDetailQuery;
import com.jtech.toa.service.prices.IPricesDetailsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
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
public class PricesDetailsServiceImpl extends ServiceImpl<PricesDetailsMapper, PricesDetails> implements IPricesDetailsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePrice(List<PricesDetails> pricesDetailsList, PriceSystem priceSystem,int userId, int area) {
        boolean ok = true;
        //新增类别
        priceSystem.setCreater(userId);
        priceSystem.setCreateTime(new Date());
        priceSystem.setArea(area);
        ok = priceSystem.insert();
        if (CollectionUtils.isNotEmpty(pricesDetailsList)) {
            for (PricesDetails pricesDetails : pricesDetailsList) {
//                PricesDetails prices = selectOne(new EntityWrapper<PricesDetails>().
//                        eq("panel", pricesDetails.getPanel()).
//                        eq("systems", pricesDetails.getSystems()));
//                if (prices == null) {
                    pricesDetails.setSystems(priceSystem.getId());
                    ok = insert(pricesDetails);
//                } else {
//                    prices.setPanel(pricesDetails.getPanel());
//                    prices.setPrice(pricesDetails.getPrice());
//                    prices.setModual(pricesDetails.getModual());
//                    ok = updateById(prices);
//                }
            }
        }
        return ok;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editPrice(PriceSystem priceSystem, String details)throws Exception {
        boolean ok = priceSystem.updateById();
        if (!ok) {
            throw new Exception("更新价格体系失败");
        }
        if (!Strings.isNullOrEmpty(details)) {
            List<PricesDetails> pricesDetailsList = JSON.parseArray(details, PricesDetails.class);
            for (PricesDetails pricesDetails : pricesDetailsList) {
                if (pricesDetails.getId() == 0) {
                    ok = insert(pricesDetails);
                } else {
                    ok = updateById(pricesDetails);
                }
            }
        }
        return ok;
    }

    @Override
    public PricesDetails getDetailsByPanelAndArea(int panel, int area) {
        return baseMapper.getByPanelAndArea(panel,area, DateTime.now().toDate());
    }

    @Override
    public List<PricesDetailDto> getDetailList(int systemId) {
        List<PricesDetailDto> list = baseMapper.getListBySystemsId(systemId);
        return list;
    }

    @Override
    public  List<SparePriceDetailsDto> getListBySparePriceSystemsId(int systemId){
        List<SparePriceDetailsDto> list = baseMapper.getListBySparePriceSystemsId(systemId);
        return list;
    }

    @Override
    public void findAreaPriceByPage(Page<AreaPricesDetailDto> requestPage, AreaPricesDetailQuery query) {
        List<AreaPricesDetailDto> areaPricesDetailList = baseMapper.findAreaPriceByPage(requestPage, query);
        requestPage.setRecords(areaPricesDetailList);
    }
}

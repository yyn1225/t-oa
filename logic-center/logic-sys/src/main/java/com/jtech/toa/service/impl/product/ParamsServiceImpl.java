/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.product;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import com.jtech.toa.dao.ParamsMapper;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.ProductParamsLang;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IProductParamsLangService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class ParamsServiceImpl extends ServiceImpl<ParamsMapper, Params> implements IParamsService {

    private final IProductParamsLangService productParamsLangService;

    @Autowired
    public ParamsServiceImpl(IProductParamsLangService productParamsLangService) {
        this.productParamsLangService = productParamsLangService;
    }

    @Override
    public Params selectParamsByProductId(int product) {
        return baseMapper.selectParamsByProductId(product);
    }

    @Override
    public List<Params> selectParamsList() {
        return baseMapper.selectParamsList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAndLang(Params params, List<ProductParamsLang> updateList, List<ProductParamsLang> newList) {
        boolean isOk;
        params.setUpdateTime(new Date());
        isOk = updateById(params);
        if (updateList.size() > 0) {
            for (ProductParamsLang paramsLang : updateList) {
                paramsLang.setParams(params.getId());
                isOk = productParamsLangService.updateById(paramsLang);
            }
        }
        if (newList.size() > 0) {
            for (ProductParamsLang paramsLang : newList) {
                paramsLang.setParams(params.getId());
                isOk = productParamsLangService.insert(paramsLang);
            }
        }
        return isOk;
    }

    @Override
    public Params selectByIdAndLang(Integer id, String lang) {
        return baseMapper.selectByIdAndLang(id, lang);
    }

    @Override
    public List<Params> findTop50List(int lastId) {
        return baseMapper.selectTop50List(lastId);
    }

    @Override
    public List<Params> findAllWithoutParamsLang() {
        return baseMapper.findAllWithoutParamsLang();
    }

    @Override
    public List<Params> findAllWithoutParamsControlLang() {
        return baseMapper.findAllWithoutParamsControlLang();
    }

    @Override
    public List<Params> findAllWithoutParamsFixModualLang() {
        return baseMapper.findAllWithoutParamsFixModualLang();
    }

    @Override
    public List<Params> findAllWithoutParamsFixPsuLang() {
        return baseMapper.findAllWithoutParamsFixPsuLang();
    }
}

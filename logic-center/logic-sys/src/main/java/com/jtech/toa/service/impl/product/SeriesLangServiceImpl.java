/*
<<<<<<< HEAD:t-oa/logic-center/logic-sys/src/java/com/jtech/toa/service/impl/SeriesLangServiceImpl.java
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.service.impl.product;

import com.jtech.toa.entity.product.SeriesLang;
import com.jtech.toa.dao.SeriesLangMapper;
import com.jtech.toa.service.product.ISeriesLangService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
@Service
public class SeriesLangServiceImpl extends ServiceImpl<SeriesLangMapper, SeriesLang> implements ISeriesLangService {

    @Override
    public List<SeriesLang> findByType(int app) {
        return baseMapper.selectByType(app);
    }
}

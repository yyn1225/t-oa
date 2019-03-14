/*
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



package com.jtech.toa.service.product;

import com.jtech.toa.entity.product.SeriesLang;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
public interface ISeriesLangService extends IService<SeriesLang> {

    /**
     * 根据类型查询到系列的语言
     * @param app 类型
     * @return 系列语言的集合
     */
    List<SeriesLang> findByType(int app);
}

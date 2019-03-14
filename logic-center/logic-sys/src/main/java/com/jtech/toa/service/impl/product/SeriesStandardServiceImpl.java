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

package com.jtech.toa.service.impl.product;


import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.SeriesStandardMapper;
import com.jtech.toa.entity.product.SeriesStandard;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.dto.products.YearFormDto;
import com.jtech.toa.model.query.StandardQuery;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISeriesStandardService;
import com.jtech.toa.service.product.ISpareService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-28
 */
@Service
public class SeriesStandardServiceImpl extends ServiceImpl<SeriesStandardMapper, SeriesStandard> implements ISeriesStandardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesStandardServiceImpl.class);

    private final ISeriesService seriesService;
    private final ISpareService spareService;

    @Autowired
    public SeriesStandardServiceImpl(ISeriesService seriesService, ISpareService spareService) {
        this.seriesService = seriesService;
        this.spareService = spareService;
    }


    @Override
    public void selectStandardListByPage(Page<StandardDto> requestPage, StandardQuery query) {
        List<StandardDto> spares = baseMapper.selectStandardListByPage(requestPage, query);
        requestPage.setRecords(spares);
    }

    @Override
    public boolean addStandard(String standardIds, Integer seriesId, Integer type) {
        boolean isOk = true;
        if (!Strings.isNullOrEmpty(standardIds)
                && seriesId > 0
                && type > 0) {
            String[] ids = standardIds.split(StringPool.COMMA);
            SeriesStandard standard;
            for (String id : ids) {
                standard = new SeriesStandard();
                standard.setSeries(seriesId);
                standard.setSpare(Ints.tryParse(id));
                standard.setType(type);
                isOk = insert(standard);
                if (!isOk) {
                    throw new DaoException("保存备件关联信息失败");
                }
            }
        }
        return isOk;
    }

    @Override
    public boolean saveByStandard(Integer standardId, String yearsJsonStr, Integer auto) {
        SeriesStandard standard = selectById(standardId);
        standard.setStandard(auto);
        setYear(yearsJsonStr, standard);
        boolean isOk = updateById(standard);
        if (!isOk) {
            throw new DaoException("更新备件关联信息失败！");
        }
        return isOk;
    }

    private SeriesStandard findBySeriesIdAndSpareId(Integer seriesId, Integer spareId) {
        return baseMapper.selectBySeriesIdAndSpareId(seriesId, spareId);
    }

    void setYear(String yearsJsonStr, SeriesStandard standard) {
        List<YearFormDto> yearFormDtos = JSON.parseArray(yearsJsonStr, YearFormDto.class);
        Map<Integer, YearFormDto> yearFormDtoMap = Maps.newHashMap();
        for (YearFormDto yearFormDto : yearFormDtos) {
            yearFormDtoMap.put(yearFormDto.getYear(), yearFormDto);
        }
        //2年的
        YearFormDto year2 = yearFormDtoMap.get(2);
        if (year2 != null) {
            standard.setCountsTwo(year2.getCounts());
            standard.setCalTypeTwo(year2.getUnitType());
            standard.setSpelTwo(year2.getSpel());
        }
        //3年的
        YearFormDto year3 = yearFormDtoMap.get(3);
        if (year3 != null) {
            standard.setCountsThree(year3.getCounts());
            standard.setCalTypeThree(year3.getUnitType());
            standard.setSpelThree(year3.getSpel());
        }
        //4年的
        YearFormDto year4 = yearFormDtoMap.get(4);
        if (year4 != null) {
            standard.setCountsFour(year4.getCounts());
            standard.setCalTypeFour(year4.getUnitType());
            standard.setSpelFour(year4.getSpel());
        }
        //5年的
        YearFormDto year5 = yearFormDtoMap.get(5);
        if (year2 != null) {
            standard.setCountsFive(year5.getCounts());
            standard.setCalTypeFive(year5.getUnitType());
            standard.setSpelFive(year5.getSpel());
        }
    }
}

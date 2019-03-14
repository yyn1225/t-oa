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

package com.jtech.toa.service.impl.system;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.jtech.toa.constants.ExhibitionType;
import com.jtech.toa.dao.ExhibitionMapper;
import com.jtech.toa.entity.system.Exhibition;
import com.jtech.toa.model.dto.sys.ExhibitionAppDto;
import com.jtech.toa.service.system.IExhibitionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
@Service
public class ExhibitionServiceImpl extends ServiceImpl<ExhibitionMapper, Exhibition> implements IExhibitionService {

    @Override
    public List<ExhibitionAppDto> findForApp() {
        return baseMapper.selectByType(ExhibitionType.APP_TYPE);
    }

    @Override
    public List<ExhibitionAppDto> findForPc() {
        return baseMapper.selectByType(ExhibitionType.PC_TYPE);
    }

    @Override
    public void selectFourList(Page<Exhibition> requestPage) {
        List<Exhibition> exhibitionList =  baseMapper.selectFourList(requestPage);
        requestPage.setRecords(exhibitionList);
    }
}

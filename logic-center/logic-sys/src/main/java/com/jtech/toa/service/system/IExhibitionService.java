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

package com.jtech.toa.service.system;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.system.Exhibition;
import com.jtech.toa.model.dto.sys.ExhibitionAppDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
public interface IExhibitionService extends IService<Exhibition> {

    /**
     * 查询APP的轮播图
     * @return 轮播图的
     */
    List<ExhibitionAppDto> findForApp();

    /**
     * 查询web端的轮播图
     * @return 轮播图的
     */
    List<ExhibitionAppDto> findForPc();

    /**
     * 查询轮播图列表
     * @param requestPage 分页参数
     * @return 轮播图列表
     */
    void selectFourList(Page<Exhibition> requestPage);
}

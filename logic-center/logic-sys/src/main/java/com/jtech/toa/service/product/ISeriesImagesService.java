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

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.product.SeriesImages;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-28
 */
public interface ISeriesImagesService extends IService<SeriesImages> {

    /**
     * 查询系列的展示图
     * @param seriesId 系列的id
     * @return 展示图的集合
     */
    List<SeriesImages> findBySeriesId(Integer seriesId);

    /**
     * 更新图片的信息
     * @param seriesId 系列的id
     * @param attIds 附件的id
     * @return 是否成功
     */
    boolean updateImageBySeriesIdAndAttIds(Integer seriesId, String attIds);
}

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

package com.jtech.toa.service.file;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.file.FileSeries;
import com.jtech.toa.model.dto.files.AppFileSeries;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-26
 */
public interface IFileSeriesService extends IService<FileSeries> {

    /**
     * 保存文件的系列信息
     * @param seriesIds 系列的ids
     * @param fileId 文件的id
     * @return 是否成功
     */
    boolean save(String seriesIds, Integer fileId);

    /**
     * 根据fileId删除文件关联信息
     * @param id 文件的id
     * @return 是否成功
     */
    boolean removeByFile(int id);

    /**
     * 根据文件id查询关联的系列的id
     * @param id 文件的id
     * @return 系列的id集合
     */
    List<Integer> findSeriesIdsByFileId(Integer id);

    List<AppFileSeries> selectAppFileSeries(int lastId);
}

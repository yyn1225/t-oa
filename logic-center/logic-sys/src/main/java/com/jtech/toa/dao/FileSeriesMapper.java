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

package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.file.FileSeries;

import com.jtech.toa.model.dto.files.AppFileSeries;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-26
 */
public interface FileSeriesMapper extends BaseMapper<FileSeries> {

    int deleteByFileId(@Param("fileId") int id);

    /**
     * 根据文件id查询关联的系列的id
     * @param id 文件的id
     * @return 系列的id集合
     */
    List<Integer> selectSeriesIdsByFileId(@Param("fileId") Integer id);

    List<AppFileSeries> selectAppFileSeries(int lastId);
}
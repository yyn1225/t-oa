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
import com.jtech.toa.entity.file.FilePackageRelation;

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
public interface FilePackageRelationMapper extends BaseMapper<FilePackageRelation> {

    /**
     * 删除文件夹的关联
     * @param fileId 文件的id
     * @return
     */
    int deleteByFile(@Param("fileId") int fileId);

    /**
     * 根据文件的id查询文件夹的id集合
     * @param id 文件的id
     * @return 文件夹的id集合
     */
    List<Integer> selectPackageIdsByFileId(@Param("fileId") Integer id);

    int deleteByFileAndPackage(@Param("fileId") Integer id, @Param("packageId") Integer packageId);
}
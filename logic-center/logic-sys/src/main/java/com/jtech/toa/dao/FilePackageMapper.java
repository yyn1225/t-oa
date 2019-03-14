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
import com.jtech.toa.entity.file.FilePackage;
import com.jtech.toa.model.dto.files.AppFilePackages;
import com.jtech.toa.model.dto.files.AppPackages;
import com.jtech.toa.model.dto.files.FilePackageDto;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
public interface FilePackageMapper extends BaseMapper<FilePackage> {

    /**
     * 查询所有的文件的信息
     * @return
     */
    List<FilePackage> selectAll();

    /**
     * 根据名称查询文件夹，名称是空则返回全部
     * @param name 文件夹名称
     * @return 文件夹集合
     */
    List<FilePackage> selectByName(@Param("name") String name);


    List<AppPackages> findApiAllFilePackage(@Param("lang") String lang);

    /**
     * 根据创建时间和更新时间查询列表
     * @param lastTime 同步时间
     * @return 文件夹列表
     */
    List<FilePackageDto> selectAppList(@Param("lastTime") Date lastTime);

    /**
     * 根据创建时间和更新时间查询更新列表
     * @param lastTime 同步时间
     * @return 文件夹列表
     */
    List<FilePackageDto> selectAppUpdateList(@Param("lastTime")Date lastTime);

    List<AppFilePackages> findFilePackages(int lastId);
}
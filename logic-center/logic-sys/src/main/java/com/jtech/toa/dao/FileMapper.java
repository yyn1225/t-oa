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
import com.baomidou.mybatisplus.plugins.Page;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.file.File;
import com.jtech.toa.model.dto.files.FileDto;
import com.jtech.toa.model.query.AdminFileQuery;
import com.jtech.toa.model.query.FileQuery;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
public interface FileMapper extends BaseMapper<File> {

    /**
     * 更新文件的文件夹，把之前是packageId的文件更新到parentId的文件夹中
     * @param oldPackageId 文件夹的id
     * @param newPackageId 更新的文件夹id
     * @return 是否成功
     */
    int updateNewPackageIdByOldPackageId(@Param("oldPackage") Integer oldPackageId,@Param("newPackage") Integer newPackageId);

    /**
     * 分页查询数据库信息
     * @param requestPage 分页对象
     * @param fileQuery 查询对象
     * @return 文件对象集合
     */
    List<FileDto> searchByPagination(Page<FileDto> requestPage,@Param("query") AdminFileQuery fileQuery);

    /**
     * 分页查询数据库信息
     * @param requestPage 分页对象
     * @param query 查询对象
     * @return 文件对象集合
     */
    List<FileDto> selectBySeriesAndPage(Page<FileDto> requestPage, @Param("query") AdminFileQuery query);

    /**
     * 通过用户id获取用户的
     * @param user
     * @return
     */
    List<File> findFileListByUser(int user);

    /**
     * 通过用户id获取用户的前10条数据列表
     * @param user
     * @return
     */
    List<File> findTenFileListByUser(@Param("user")int user,@Param("type")int type);

    /**
     * 获取10条最新文件（用户可见的数据列表）
     * @param user 用户id
     * @param lastId
     * @return
     */
    List<File> findTenFileListByUserAndPage(@Param("user")int user,@Param("last")int lastId,@Param("query") FileQuery query);

    /**
     * 根据文件Id获取文件信息
     * @param ids
     * @return
     */
    List<File> findTenFileListByIds(@Param("ids")List<Long> ids,@Param("userId") int userId,
                                    @Param("lang") String lang);

    /**
     * 分页通过权限查询数据库信息
     * @param requestPage 分页对象
     * @param query 查询对象
     * @return 文件对象集合
     */
    List<FileDto> selectBySecurity(Page<FileDto> requestPage, @Param("query") FileQuery query);

    /**
     * 分页获取文件清单，专用于App端
     * @param userId
     * @param lastId
     * @return
     */
    List<File> findPageFileListByUser(@Param("userId") int userId,@Param("lastId") int lastId);

    /**
     * 根据用户找出用户的文件
     * @param userId 用户id
     * @return
     */
    List<File> getAllFileByUserForApp(@Param("userId") int userId);
}
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
import com.jtech.toa.entity.file.FilePackageRelation;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-26
 */
public interface IFilePackageRelationService extends IService<FilePackageRelation> {

    /**
     * 删除文件夹的关联
     * @param fileId 文件的id
     * @return
     */
    boolean removeByFile(int fileId);

    /**
     * 插入文件夹关联数据
     * @param packageIds 文件夹的ids
     * @param fileId 文件的id
     * @return 是否成功
     */
    boolean save(String packageIds, int fileId);

    /**
     * 根据文件的id查询文件夹的id集合
     * @param id 文件的id
     * @return 文件夹的id集合
     */
    List<Integer> findPackageIdsByFileId(Integer id);

    /**
     * 根据文件和文件夹删除关联关系
     * @param id 文件的id
     * @param packageId 文件夹的id
     * @return 是否成功
     */
    boolean removeByFileAndPackage(Integer id, Integer packageId);
}

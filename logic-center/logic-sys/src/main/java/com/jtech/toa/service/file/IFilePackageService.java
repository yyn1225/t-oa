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
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.entity.file.FilePackage;
import com.jtech.toa.model.dto.files.AppFilePackages;
import com.jtech.toa.model.dto.files.AppPackages;
import com.jtech.toa.model.dto.files.FilePackageDto;
import com.jtech.toa.model.dto.files.FolderNestableDto;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
public interface IFilePackageService extends IService<FilePackage> {
    /**
     * 获取系统存储文件 附件的目录地址
     *
     * @return 附件目录
     */
    String getAppStorePath();


    /**
     * 递归产生nestable数据
     * @return nestable数据的集合
     */
    List<FolderNestableDto> genderForNestable();

    /**
     * 更新整个文件夹的父级
     * @param folderNestableDtos 文件夹的结果信息
     * @return
     */
    boolean updateStruct(List<FolderNestableDto> folderNestableDtos);

    /**
     * 根据id删除文件夹
     * 1.需要把子文件夹放到这个文件夹的父级
     * 2.需要把这个文件夹的文件放到文件夹的父级
     * @param id 删除的文件夹的id
     * @return 是否成功
     */
    boolean removeById(Integer id);


    /**
     * 查询文件夹的树
     * @param name 文件夹名称
     * @return 树结构的集合
     */
    List<ZTreeVO<com.jtech.toa.user.model.vo.TreeDataVO>> findAllToTree(String name);

    /**
     * 查询应用场景
     */
    List<FilePackage> sceneData();

    List<AppPackages> findApiAllFilePackage(String lang);

    List<AppFilePackages> findFilePakages(int lastId);

    /**
     * 根据创建时间和更新时间查询列表
     * @param lastTime 同步时间
     * @return 文件夹列表
     */
    List<FilePackageDto> selectAppList(Date lastTime);

    /**
     * 根据创建时间和更新时间查询更新列表
     * @param lastTime 同步时间
     * @return 文件夹列表
     */
    List<FilePackageDto> selectAppUpdateList(Date lastTime);
}

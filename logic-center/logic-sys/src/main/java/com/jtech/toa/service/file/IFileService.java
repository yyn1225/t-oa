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

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.toa.entity.file.File;
import com.jtech.toa.model.dto.files.FileDto;
import com.jtech.toa.model.query.AdminFileQuery;
import com.jtech.toa.model.query.FileQuery;

/**
 * <p> 服务类 </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
public interface IFileService extends IService<File> {

    /**
     * 获取系统存储文件 附件的目录地址
     *
     * @return 附件目录
     */
    String getAppStorePath();


    /**
     * 更新文件的文件夹，把之前是packageId的文件更新到parentId的文件夹中
     *
     * @param packageId 文件夹的id
     * @param parentId  更新的文件夹id
     * @return 是否成功
     */
    boolean updateParentByPackeId(Integer packageId, Integer parentId);

    /**
     * 分页查询
     *
     * @param requestPage 分页信息
     * @param fileQuery   文件查询对象
     */
    void searchByPagination(Page<FileDto> requestPage, AdminFileQuery fileQuery);

    String genDatePath();

    String medialUrl(String relativeURL);

    /**
     * 保存文件信息
     *
     * @param ids       attachment的id
     * @param packageIds 文件夹的ids
     * @param seriesIds 系列的ids
     * @param fileType 文件的类型
     * @param securitys 查看类型
     * @param market 产品细分
     * @return 是否成功
     */
    boolean saveFile(String ids, String packageIds,String seriesIds,Integer fileType,Integer securitys,Integer market);

    /**
     * 保存视频信息
     *
     * @param packageIds 文件夹的ids
     * @param seriesIds 系列的ids
     * @param file 文件对象
     * @param fileType 文件类型
     * @param fileList 文件对象集合
     * @return 是否成功
     */
    boolean saveVideoFile(String packageIds,String seriesIds,Integer fileType, File file, List<File> fileList);

    /**
     * 更新文件信息，通过attachmentId来更新
     * @param file 文件信息对象
     * @param packageIds 文件夹的ids
     * @param seriesIds 系列的ids
     * @param fileType 文件的类型
     * @return 是否成功
     */
    boolean updateVideo(File file,String packageIds,String seriesIds,Integer
            fileType);

    /**
     * 更新文件信息，通过attachmentId来更新
     * @param id 文件的id
     * @param attId attachmentId
     * @param packageIds 文件夹的ids
     * @param seriesIds 系列的ids
     * @param fileType 文件的类型
     * @param securitys 查看类型
     * @param market 产品细分
     * @return 是否成功
     */
    boolean updateInfoByIdAndAttId(int id, int attId,String packageIds,String seriesIds,Integer
            fileType,Integer securitys,Integer market);
    /**
     * 更新文件信息，通过attachmentId来更新
     * @param list 文件的id集合
     * @param packageIds 文件夹的ids
     * @param seriesIds 系列的ids
     * @param fileType 文件的类型
     * @param securitys 查看类型
     * @param market 产品细分
     * @return 是否成功
     */
    boolean updateInfoByIds(List<String> list,String packageIds, String seriesIds, Integer fileType,Integer securitys,Integer market);
    /**
     * 更新文件的文件夹信息
     * @param id 文件的id
     * @param packageId 文件夹的id
     * @return 是否成功
     */
    boolean updatePackageById(Integer id, Integer packageId,Integer oldPackageId);

    /**
     * 删除文件
     * @param ids 文件的id 集合
     * @return 是否成功
     */
    boolean removeByIds(List<Integer> ids);

    /**
     * 删除文件
     * @param id 文件的id
     * @return 是否成功
     */
    boolean removeById(Integer id);

    /**
     * 分页查询数据库信息
     * @param requestPage 分页对象
     * @param fileQuery 查询对象
     */
    void selectBySeriesAndPage(Page<FileDto> requestPage, AdminFileQuery fileQuery);

    /**
     * 通过用户列表查找文件列表
     * @param userId
     * @return
     */
    List<File> findFileListByUser(int userId);

    List<File> findPageFileListByUser(int userId,int lastId);

    List<File> findTenFileListByUser(int userId,int type);

    List<File> findTenFileListByUser(int userId,int lastId,FileQuery query);

    List<File> findTenFileListByIds(List<Long> ids,int userId,String lang);

    /**
     * 分页通过权限查询数据库信息
     * @param requestPage 分页对象
     * @param query 查询对象
     */
    void selectBySecurity(Page<FileDto> requestPage, FileQuery query);


    /**
     * 根据用户找出用户的文件
     * @param userId 用户id
     * @return
     */
    List<File> getAllFileByUserForApp(int userId);
}

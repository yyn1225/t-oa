package com.jtech.toa.service.file;


import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.file.FileSecurityRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-11-11
 */
public interface IFileSecurityRoleService extends IService<FileSecurityRole> {

    /**
     * 根据文件查询所有对应角色
     * @param fileId 文件id
     * @return 角色集合
     */
    List<FileSecurityRole> securityRoleList(int fileId);

    /**
     * 删除文件下所有关联数据
     * @param fileId 文件id
     * @return 布尔值
     */
    boolean deleteByFile(Long fileId);

    /**
     * 新增文件权限
     * @param fileSecurityRole 角色文件权限对象
     * @return 影响的行数
     */
    boolean insertSecurity(FileSecurityRole fileSecurityRole);

    /**
     * 根据文件和角色查询权限
     * @param fileId  文件id
     * @param roleId 角色id
     * @return 权限
     */
    FileSecurityRole selectByRoleAndFile(int fileId, int roleId);

    /**
     * 通过文件id和权限查询列表
     * @param file 文件id
     * @param security 权限
     * @return 文件权限列表
     */
    List<FileSecurityRole> selectByRoleAndSecurity(int file, int security);

    /**
     * 检查用户是否有权限查看文件
     * @param file
     * @param user
     * @return
     */
    boolean checkFileRoleReadAble(int file,int user);

    /**
     * 检查某个用户是否有权限下载文件
     * @param file
     * @param user
     * @return
     */
    boolean checkFileDownloadAble(int file,int user);

    /**
     * 删除文件集合下所有关联数据
     * @param fileIds 文件id集合
     * @return 布尔值
     */
    boolean deleteByFileIds(List<Long> fileIds);
}

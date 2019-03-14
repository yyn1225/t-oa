package com.jtech.toa.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.file.FileSecurityRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-11-11
 */
public interface FileSecurityRoleMapper extends BaseMapper<FileSecurityRole> {

    /**
     * 根据文件查询所有对应角色
     * @param fileId 文件id
     * @return 角色集合
     */
    List<FileSecurityRole> securityRoleList(@Param("fileId")int fileId);

    /**
     * 删除文件下所有关联数据
     * @param fileId 文件id
     * @return 影响的行数
     */
    int deleteByFile(@Param("fileId")Long fileId);

    /**
     * 新增文件权限
     * @param fileSecurityRole 角色文件权限对象
     * @return 影响的行数
     */
    int insertSecurity(FileSecurityRole fileSecurityRole);

    /**
     * 根据文件和角色查询权限
     * @param fileId  文件id
     * @param roleId 角色id
     * @return 权限
     */
    FileSecurityRole selectByRoleAndFile(@Param("fileId")int fileId, @Param("roleId")int roleId);

    /**
     * 通过文件id和权限查询列表
     * @param file 文件id
     * @param security 权限
     * @return 文件权限列表
     */
    List<FileSecurityRole> selectByRoleAndSecurity(@Param("file") int file,@Param("security") int security);

    /**
     * 检查用户的文件权限类型
     * @param file
     * @param user
     * @return
     */
    Integer checkFileSecurity(@Param("file") int file,@Param("user") int user);

    /**
     * 删除文件集合下角色权限关联数据
     */
    int deleteByFileIds(@Param("fileIds") List<Long> fileIds);
}
package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.file.FileSecurityUser;
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
public interface FileSecurityUserMapper extends BaseMapper<FileSecurityUser> {

    /**
     * 根据文件Id查询所有用户权限
     * @param fileId 文件id
     * @return 用户权限集合
     */
    List<FileSecurityUser> selectSecurityUser(@Param("fileId")int fileId);

    /**
     * 删除文件下所有关联数据
     * @param fileId 文件id
     * @return 影响的行数
     */
    int deleteByFile(@Param("fileId")Long fileId);

    /**
     * 新增文件权限
     * @param fileSecurityUser 用户文件权限对象
     * @return 影响的行数
     */
    int insertSecurity(FileSecurityUser fileSecurityUser);

    /**
     * 根据文件和用户查询权限
     * @param fileId  文件id
     * @param userId 用户id
     * @return 权限
     */
    FileSecurityUser selectByUserAndFile(@Param("fileId")int fileId, @Param("userId")int userId);
}
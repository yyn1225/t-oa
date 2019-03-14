package com.jtech.toa.service.file;


import com.baomidou.mybatisplus.service.IService;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.entity.file.FileSecurityUser;
import com.jtech.toa.user.model.vo.TreeDataVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-11-11
 */
public interface IFileSecurityUserService extends IService<FileSecurityUser> {

    /**
     * 地区用户查询
     * @return 树的集合
     */
    List<ZTreeVO<TreeDataVO>> findAllToTree();

    /**
     * 根据文件Id查询所有用户权限
     * @param fileId 文件id
     * @return 用户权限集合
     */
    List<FileSecurityUser> selectSecurityUser(int fileId);

    /**
     * 删除文件下所有关联数据
     * @param fileId 文件id
     * @return 布尔值
     */
    boolean deleteByFile(Long fileId);

    /**
     * 新增文件权限
     * @param fileSecurityUser 用户文件权限对象
     * @return 布尔值
     */
    boolean insertSecurity(FileSecurityUser fileSecurityUser);

    /**
     * 根据文件和用户查询权限
     * @param fileId  文件id
     * @param userId 用户id
     * @return 权限
     */
    FileSecurityUser selectByUserAndFile(int fileId, int userId);
}

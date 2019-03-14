package com.jtech.toa.service.impl.file;


import com.jtech.toa.dao.FileSecurityRoleMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.file.FileSecurityRole;
import com.jtech.toa.service.file.IFileSecurityRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-11-11
 */
@Service
public class FileSecurityRoleServiceImpl extends ServiceImpl<FileSecurityRoleMapper, FileSecurityRole> implements IFileSecurityRoleService {

    @Override
    public List<FileSecurityRole> securityRoleList(int fileId) {
        return baseMapper.securityRoleList(fileId);
    }

    @Override
    public boolean deleteByFile(Long fileId) {
        return baseMapper.deleteByFile(fileId) > 0;
    }

    @Override
    public boolean insertSecurity(FileSecurityRole fileSecurityRole) {
        return baseMapper.insertSecurity(fileSecurityRole) == 1;
    }

    @Override
    public FileSecurityRole selectByRoleAndFile(int fileId, int roleId) {
        return baseMapper.selectByRoleAndFile(fileId, roleId);
    }

    @Override
    public List<FileSecurityRole> selectByRoleAndSecurity(int file, int security) {
        return baseMapper.selectByRoleAndSecurity(file, security);
    }

    @Override
    public boolean checkFileRoleReadAble(int file, int user) {
        Integer security = baseMapper.checkFileSecurity(file, user);
        return security != null && security == 2;
    }


    @Override
    public boolean checkFileDownloadAble(int file, int user) {
        Integer security = baseMapper.checkFileSecurity(file,user);
        return security>=1;
    }

    @Override
    public boolean deleteByFileIds(List<Long> fileIds) {
        return baseMapper.deleteByFileIds(fileIds) > 0;
    }
}

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

package com.jtech.toa.service.impl.file;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.FilePackageRelationMapper;
import com.jtech.toa.entity.file.FilePackageRelation;
import com.jtech.toa.service.file.IFilePackageRelationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-26
 */
@Service
public class FilePackageRelationServiceImpl extends ServiceImpl<FilePackageRelationMapper, FilePackageRelation> implements IFilePackageRelationService {

    @Override
    public boolean removeByFile(int fileId) {
        return baseMapper.deleteByFile(fileId) >= 0;
    }

    @Transactional
    @Override
    public boolean save(String packageIds, int fileId) {
        boolean isOk = true;
        if (!Strings.isNullOrEmpty(packageIds)) {
            String[] pids = packageIds.split(StringPool.COMMA);
            for (String pid : pids) {
                FilePackageRelation filePackageRelation = new FilePackageRelation();
                filePackageRelation.setFiles(fileId);
                filePackageRelation.setPackages(Ints.tryParse(pid));
                isOk = insert(filePackageRelation);
                if (!isOk) {
                    throw new DaoException("保存文件夹关联信息失败");
                }
            }
        }
        return isOk;
    }

    @Override
    public List<Integer> findPackageIdsByFileId(Integer id) {
        return baseMapper.selectPackageIdsByFileId(id);
    }

    @Override
    public boolean removeByFileAndPackage(Integer id, Integer packageId) {
        return baseMapper.deleteByFileAndPackage(id, packageId) >= 0;
    }
}

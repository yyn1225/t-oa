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
import com.jtech.toa.dao.FileSeriesMapper;
import com.jtech.toa.entity.file.FileSeries;
import com.jtech.toa.model.dto.files.AppFileSeries;
import com.jtech.toa.service.file.IFileSeriesService;

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
public class FileSeriesServiceImpl extends ServiceImpl<FileSeriesMapper, FileSeries> implements IFileSeriesService {

    @Transactional
    @Override
    public boolean save(String seriesIds, Integer fileId) {
        boolean isOk = true;
        if (!Strings.isNullOrEmpty(seriesIds)) {
            String[] sids = seriesIds.split(StringPool.COMMA);
            for (String pid : sids) {
                FileSeries filePackageRelation = new FileSeries();
                filePackageRelation.setFiles(fileId);
                filePackageRelation.setSeries(Ints.tryParse(pid));
                isOk = insert(filePackageRelation);
                if (!isOk) {
                    throw new DaoException("保存文件和系列关联信息失败");
                }
            }
        }
        return isOk;
    }

    @Override
    public boolean removeByFile(int id) {
        return baseMapper.deleteByFileId(id) >= 0;
    }

    @Override
    public List<Integer> findSeriesIdsByFileId(Integer id) {
        return baseMapper.selectSeriesIdsByFileId(id);
    }

    @Override
    public List<AppFileSeries> selectAppFileSeries(int lastId) {
        return baseMapper.selectAppFileSeries(lastId);
    }
}

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

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.util.CollectionUtil;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.dao.FilePackageMapper;
import com.jtech.toa.entity.file.FilePackage;
import com.jtech.toa.model.dto.files.AppFilePackages;
import com.jtech.toa.model.dto.files.AppPackages;
import com.jtech.toa.model.dto.files.FilePackageDto;
import com.jtech.toa.model.dto.files.FolderNestableDto;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.user.model.vo.TreeDataVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
@Service
public class FilePackageServiceImpl extends ServiceImpl<FilePackageMapper, FilePackage> implements IFilePackageService {
    private final String filePath;
    private final IFileService fileService;

    @Autowired
    public FilePackageServiceImpl(OaProperties properties, IFileService fileService) {
        this.filePath = properties.getMediaPath();
        this.fileService = fileService;
    }

    @Override
    public String getAppStorePath() {
        return filePath;
    }

    @Override
    public List<FolderNestableDto> genderForNestable() {
        List<FilePackage> filePackages = baseMapper.selectAll();
        if (CollectionUtils.isEmpty(filePackages)) {
            return Collections.EMPTY_LIST;
        }
        List<FolderNestableDto> folderNestableDtos = Lists.newArrayList();
        FolderNestableDto folderNestableDto;
        for (FilePackage filePackage : filePackages) {
            folderNestableDto = new FolderNestableDto();
            folderNestableDto.setId(filePackage.getId());
            folderNestableDto.setName(filePackage.getName());
            folderNestableDto.setIcon(filePackage.getIcon());
            folderNestableDto.setParentId(filePackage.getParent());
            folderNestableDtos.add(folderNestableDto);
        }
        List<FolderNestableDto> result = buildByRecursive(folderNestableDtos);
        return result;
    }

    @Override
    public boolean updateStruct(List<FolderNestableDto> folderNestableDtos) {
        if (CollectionUtils.isNotEmpty(folderNestableDtos)) {
            List<FolderNestableDto> result = build(folderNestableDtos);
            List<FilePackage> filePackages = Lists.newArrayList();
            FilePackage filePackage;
            for (FolderNestableDto folderNestableDto : result) {
                filePackage = new FilePackage();
                filePackage.setId(folderNestableDto.getId());
                filePackage.setParent(folderNestableDto.getParentId());
                filePackages.add(filePackage);
            }
            boolean isOk = updateBatchById(filePackages, 100);
            if (!isOk) {
                throw new DaoException("更新文件夹结构失败!");
            } else {
                return true;
            }
        }
        return true;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        FilePackage filePackage = selectById(id);
        final int parentId = filePackage.getParent();
        boolean isOk = fileService.updateParentByPackeId(id, parentId);
        if (!isOk) {
            throw new DaoException("更新文件的文件夹失败");
        }
        isOk = baseMapper.deleteById(id) > 0;
        if (!isOk) {
            throw new DaoException("删除文件夹失败");
        }
        return isOk;
    }

    @Override
    public List<ZTreeVO<TreeDataVO>> findAllToTree(String name) {
        return generateOrgTree(name);
    }

    @Override
    public List<FilePackage> sceneData(){
        return this.baseMapper.selectByName(StringPool.EMPTY);
    }

    @Override
    public List<AppPackages> findApiAllFilePackage(String lang){
        final List<AppPackages> treeNodes = this.baseMapper.findApiAllFilePackage(lang);
        buildPackageTree(treeNodes);
        return treeNodes;
    }

    private void buildPackageTree(List<AppPackages> treeNodes) {
        final List<AppPackages> remove = Lists.newArrayList();
        final Map<Integer,List<AppPackages>> map = Maps.newHashMap();
        for (AppPackages treeNode : treeNodes) {
            if(null != treeNode.getParent() && treeNode.getParent() != 0){
                remove.add(treeNode);
                if(map.get(treeNode.getParent()) == null){
                    map.put(treeNode.getParent(),Lists.newArrayList(treeNode));
                }else{
                    map.get(treeNode.getParent()).add(treeNode);
                }
            }
        }
        treeNodes.removeAll(remove);
        for (AppPackages treeNode : treeNodes) {
            if(map.get(treeNode.getId()) != null){
                treeNode.setChildren(JSON.toJSONString(map.get(treeNode.getId())));
            }
        }
    }

    @Override
    public List<AppFilePackages> findFilePakages(int lastId) {
        return baseMapper.findFilePackages(lastId);
    }

    private List<ZTreeVO<TreeDataVO>> generateOrgTree(String name) {
        final List<FilePackage> filePackages = this.baseMapper.selectByName(name);
        if (CollectionUtil.isEmpty(filePackages)) {
            return Collections.emptyList();
        }
        return listToZtree(filePackages);
    }

    private List<ZTreeVO<TreeDataVO>> listToZtree(List<FilePackage> filePackages) {
        final int organizationSize = filePackages.size();
        final List<ZTreeVO<TreeDataVO>> zTreeVOList = Lists
                .newArrayListWithCapacity(organizationSize);
        for (FilePackage organization : filePackages) {
            final int parentId = MoreObjects.firstNonNull(organization.getParent(), 0);
            TreeDataVO treeDataVO = new TreeDataVO();
            treeDataVO.setId(organization.getId());
            treeDataVO.setName(organization.getName());
            treeDataVO.setParentId(parentId);
            final ZTreeVO<TreeDataVO> zTreeVO = ZTreeVO.<TreeDataVO>builder()
                    .data(treeDataVO)
                    .id("00" + organization.getId())
                    .pid(parentId == 0 ? StringPool.ZERO : "00" + parentId)
                    .name(organization.getName())
                    .build();
            zTreeVOList.add(zTreeVO);
        }
        return zTreeVOList;
    }

    /**
     * 递归把树状list转成普通的list，对象中的parentId有值
     *
     * @param treeNodes 需要处理的树状list
     * @return 普通的list，对象中的parentId有值的对象集合
     */
    static List<FolderNestableDto> build(List<FolderNestableDto> treeNodes) {
        List<FolderNestableDto> trees = Lists.newArrayList();
        for (FolderNestableDto treeNode : treeNodes) {
            trees.add(treeNode);
            recursive(trees, treeNode);
        }
        return trees;
    }

    /**
     * 递归把树状list转成普通的list，对象中的parentId有值
     *
     * @param result   执行的结果
     * @param treeNode 单个要处理的节点
     */
    static void recursive(List<FolderNestableDto> result, FolderNestableDto treeNode) {
        if (CollectionUtils.isNotEmpty(treeNode.getChildren())) {
            int pid = treeNode.getId();
            for (FolderNestableDto treeNode1 : treeNode.getChildren()) {
                treeNode1.setParentId(pid);
                result.add(treeNode1);
                recursive(result, treeNode1);
            }
        }
    }

    /**
     * 使用递归方法建树
     */
    static List<FolderNestableDto> buildByRecursive(List<FolderNestableDto> treeNodes) {
        List<FolderNestableDto> trees = Lists.newArrayList();
        for (FolderNestableDto treeNode : treeNodes) {
            if (0 == treeNode.getParentId()) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     */
    static FolderNestableDto findChildren(FolderNestableDto treeNode, List<FolderNestableDto> treeNodes) {
        for (FolderNestableDto it : treeNodes) {
            if (treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<FolderNestableDto>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    @Override
    public List<FilePackageDto> selectAppList(Date lastTime) {
        return baseMapper.selectAppList(lastTime);
    }

    @Override
    public List<FilePackageDto> selectAppUpdateList(Date lastTime) {
        return baseMapper.selectAppUpdateList(lastTime);
    }
}

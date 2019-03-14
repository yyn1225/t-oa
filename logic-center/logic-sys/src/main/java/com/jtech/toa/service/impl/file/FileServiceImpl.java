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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.util.DateUtil;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.core.util.FileTypeUtils;
import com.jtech.toa.dao.FileMapper;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.file.FilePackageRelation;
import com.jtech.toa.entity.system.Attachment;
import com.jtech.toa.exception.LocalFileNotFoundException;
import com.jtech.toa.model.dto.files.FileDto;
import com.jtech.toa.model.query.AdminFileQuery;
import com.jtech.toa.model.query.FileQuery;
import com.jtech.toa.service.file.IFilePackageRelationService;
import com.jtech.toa.service.file.IFileSeriesService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.file.ILogFileService;
import com.jtech.toa.service.system.IAttachmentService;

import static com.jtech.marble.StringPool.DASH;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-20
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {
    private final String filePath;
    private final String mediaUrl;
    private final IAttachmentService attachmentService;
    private final ILogFileService logFileService;
    private final IFilePackageRelationService filePackageRelationService;
    private final IFileSeriesService fileSeriesService;

    public FileServiceImpl(OaProperties properties,
                           IAttachmentService attachmentService,
                           ILogFileService logFileService,
                           IFilePackageRelationService filePackageRelationService,
                           IFileSeriesService fileSeriesService) {
        this.filePath = properties.getMediaPath();
        this.mediaUrl = properties.getMediaUrl();
        this.attachmentService = attachmentService;
        this.logFileService = logFileService;
        this.filePackageRelationService = filePackageRelationService;
        this.fileSeriesService = fileSeriesService;
    }

    @Override
    public String getAppStorePath() {
        return filePath;
    }

    @Override
    @Transactional
    public boolean updateParentByPackeId(Integer oldPackageId, Integer newPackageId) {
        return baseMapper.updateNewPackageIdByOldPackageId(oldPackageId, newPackageId) >= 0;
    }

    @Override
    public void searchByPagination(Page<FileDto> requestPage, AdminFileQuery fileQuery) {
        List<FileDto> fileDtos = baseMapper.searchByPagination(requestPage,
                fileQuery);
        for (FileDto fileDto : fileDtos) {
            if (!Strings.isNullOrEmpty(fileDto.getUrl())) {
                fileDto.setUrl(attachmentService.medialUrl(fileDto.getUrl()));
            }
        }
        requestPage.setRecords(fileDtos);
    }

    @Override
    public String genDatePath() {
        final String yyyyMMdd = DateUtil.yyyyMMddDash(new Date());
        return filePath + StringUtils.replace(yyyyMMdd, DASH, java.io.File.separator);
    }


    @Override
    public String medialUrl(String relativeURL) {
        if (Strings.isNullOrEmpty(relativeURL)) {
            throw new LocalFileNotFoundException("Could not read file: " + relativeURL);
        }
        String accessUrl = StringUtils.replace(relativeURL, java.io.File.separator, StringPool.SLASH);
        if(accessUrl.toLowerCase().startsWith("http")){
            return accessUrl;
        }
        return this.mediaUrl + accessUrl;
    }

    @Override
    public boolean saveFile(String ids, String packageIds, String seriesIds, Integer fileType,Integer securitys, Integer markete) {
        String[] idArray = ids.split(StringPool.COMMA);
        List<String> stringList = Lists.newArrayList();
        for (String s : idArray) {
            if (!Strings.isNullOrEmpty(s)) {
                stringList.add(s);
            }
        }
        List<Attachment> attachments = attachmentService.selectBatchIds(stringList);
        boolean isOk = false;
        for (Attachment attachment : attachments) {
            File file = new File();
            file.setName(attachment.getFileName());
            file.setPath(attachment.getFilePath());
            String fileTypeStr = attachment.getFileType().toLowerCase();
            file.setExtend(fileTypeStr);
            file.setSize(attachment.getFileSize().longValue());
            file.setUrl(attachment.getFileUrl());
            file.setPackages(0);
            file.setType(FileTypeUtils.fileTypeToInt(fileTypeStr));
            file.setUploader(attachment.getUploader());
            file.setUploadTime(attachment.getUploadTime());
            file.setCategory(fileType);
            file.setSecuritys(securitys);
            file.setMarket(markete);
            isOk = insert(file);
            if (!isOk) {
                throw new DaoException("保存文件信息失败");
            }
            isOk = filePackageRelationService.save(packageIds, file.getId());
            if (!isOk) {
                throw new DaoException("保存文件夹关联信息失败");
            }
            isOk = fileSeriesService.save(seriesIds, file.getId());
            if (!isOk) {
                throw new DaoException("保存文件和系列关联信息失败");
            }
            isOk = logFileService.saveLog(file);
            if (!isOk) {
                throw new DaoException("保存文件信息失败");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveVideoFile(String packageIds, String seriesIds,Integer fileType, File file, List<File> fileList) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        boolean isOk = false;
        for (File f : fileList) {
            f.setSecuritys(file.getSecuritys());
            f.setMarket(file.getMarket());
            f.setExtend("1".equals(file.getExtend()) ? "video": "link");
            f.setPath(f.getUrl());
            f.setSize((long) 0);
            f.setPackages(0);
            f.setType("video".equals(f.getExtend()) ? 888 : 999);
            f.setUploader((int) shiroUser.getId());
            f.setUploadTime(new Date());
            f.setCategory(fileType);
            isOk = insert(f);
            if (!isOk) {
                throw new DaoException("保存文件信息失败");
            }
            isOk = filePackageRelationService.save(packageIds, f.getId());
            if (!isOk) {
                throw new DaoException("保存文件夹关联信息失败");
            }
            isOk = fileSeriesService.save(seriesIds, f.getId());
            if (!isOk) {
                throw new DaoException("保存文件和系列关联信息失败");
            }
            isOk = logFileService.saveLog(f);
            if (!isOk) {
                throw new DaoException("保存文件信息失败");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateVideo(File file, String packageIds, String seriesIds, Integer fileType) {
        boolean isOk = true;
        file.setCategory(fileType);
        file.setUpdateTime(new Date());
        file.setPath(file.getUrl());
        isOk = updateById(file);
        if (!isOk) {
            throw new DaoException("保存文件失败");
        }
        isOk = filePackageRelationService.removeByFile(file.getId());
        if (!isOk) {
            throw new DaoException("删除文件夹关联信息失败");
        }
        isOk = filePackageRelationService.save(packageIds, file.getId());
        if (!isOk) {
            throw new DaoException("保存文件夹关联信息失败");
        }
        isOk = fileSeriesService.removeByFile(file.getId());
        if (!isOk) {
            throw new DaoException("删除系列关联信息失败");
        }
        isOk = fileSeriesService.save(seriesIds, file.getId());
        if (!isOk) {
            throw new DaoException("保存系列关联信息失败");
        }
        return isOk;
    }

    @Transactional
    @Override
    public boolean updateInfoByIdAndAttId(int id,
                                          int attId,
                                          String packageIds,
                                          String seriesIds,
                                          Integer fileType,
                                          Integer securitys,
                                          Integer markete) {
        Attachment attachment = attachmentService.selectById(attId);

        File file = baseMapper.selectById(id);
        if (file == null) {
            throw new DaoException("文件未找到");
        }
        boolean isOk = logFileService.saveLog(file);
        if (!isOk) {
            throw new DaoException("保存日志失败");
        }
        if (attachment != null) {
            file.setName(attachment.getFileName());
            file.setPath(attachment.getFilePath());
            String fileTypeStr = attachment.getFileType().toLowerCase();
            file.setExtend(fileTypeStr);
            file.setType(FileTypeUtils.fileTypeToInt(fileTypeStr));
            file.setSize(attachment.getFileSize().longValue());
            file.setUrl(attachment.getFileUrl());
            file.setSecuritys(securitys);
            file.setMarket(markete);
        }
        file.setCategory(fileType);
        isOk = updateById(file);
        if (!isOk) {
            throw new DaoException("保存文件失败");
        }
        isOk = filePackageRelationService.removeByFile(id);
        if (!isOk) {
            throw new DaoException("删除文件夹关联信息失败");
        }
        isOk = filePackageRelationService.save(packageIds, file.getId());
        if (!isOk) {
            throw new DaoException("保存文件夹关联信息失败");
        }
        isOk = fileSeriesService.removeByFile(id);
        if (!isOk) {
            throw new DaoException("删除系列关联信息失败");
        }
        isOk = fileSeriesService.save(seriesIds, file.getId());
        if (!isOk) {
            throw new DaoException("保存系列关联信息失败");
        }
        return isOk;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateInfoByIds(List<String> list, String packageIds, String seriesIds, Integer fileType, Integer securitys, Integer markete) {
        File file ;
        boolean isOk = false;
        Integer id;
        for (String strId : list) {
            id = Integer.valueOf(strId.trim());
            file = baseMapper.selectById(id);

            if (file == null) {
                throw new DaoException("文件未找到");
            }
            isOk = logFileService.saveLog(file);
            if (!isOk) {
                throw new DaoException("保存日志失败");
            }
            file.setSecuritys(securitys);
            file.setMarket(markete);
            file.setCategory(fileType);
            isOk = updateById(file);
            if (!isOk) {
                throw new DaoException("保存文件失败");
            }
            isOk = filePackageRelationService.removeByFile(id);
            if (!isOk) {
                throw new DaoException("删除文件夹关联信息失败");
            }
            isOk = filePackageRelationService.save(packageIds, file.getId());
            if (!isOk) {
                throw new DaoException("保存文件夹关联信息失败");
            }
            isOk = fileSeriesService.removeByFile(id);
            if (!isOk) {
                throw new DaoException("删除系列关联信息失败");
            }
            isOk = fileSeriesService.save(seriesIds, file.getId());
            if (!isOk) {
                throw new DaoException("保存系列关联信息失败");
            }
        }
        return isOk;
    }

    @Transactional
    @Override
    public boolean updatePackageById(Integer id, Integer packageId, Integer oldPackageId) {
        FilePackageRelation filePackageRelation = new FilePackageRelation();
        filePackageRelation.setPackages(packageId);
        filePackageRelation.setFiles(id);
        boolean isOk;
        isOk = filePackageRelationService.removeByFileAndPackage(id, oldPackageId);
        if (!isOk) {
            throw new DaoException("保存系列关联信息失败");
        }
        isOk = filePackageRelationService.save(packageId + "", id);
        if (!isOk) {
            throw new DaoException("保存系列关联信息失败");
        }
        return isOk;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        File file = selectById(id);
        boolean isOk = logFileService.saveLog(file);
        if (!isOk) {
            throw new DaoException("保存日志失败");
        }
        isOk = deleteById(id);
        if (!isOk) {
            throw new DaoException("删除文件失败");
        }
        return isOk;
    }

    @Transactional
    @Override
    public boolean removeByIds(List<Integer> idList) {
        File file;
        boolean isOk = false;
        for (Integer id : idList) {
            file = selectById(id);
            isOk = logFileService.saveLog(file);
            if (!isOk) {
                throw new DaoException("保存日志失败");
            }
            isOk = deleteById(id);
            if (!isOk) {
                throw new DaoException("删除文件失败");
            }
        }
        return isOk;
    }

    @Override
    public void selectBySeriesAndPage(Page<FileDto> requestPage, AdminFileQuery fileQuery) {
        List<FileDto> fileDtoList = baseMapper.selectBySeriesAndPage(requestPage, fileQuery);
        for (FileDto fileDto : fileDtoList) {
            if (!Strings.isNullOrEmpty(fileDto.getUrl())) {
                fileDto.setUrl(attachmentService.medialUrl(fileDto.getUrl()));
            }
        }
        requestPage.setRecords(fileDtoList);
    }

    /**
     * 通过用户列表查找文件列表
     */
    @Override
    public List<File> findFileListByUser(int userId) {
        List<File> fileList = baseMapper.findFileListByUser(userId);
        return fileList;
    }

    @Override
    public List<File> findPageFileListByUser(int userId, int lastId) {
        List<File> fileList = baseMapper.findPageFileListByUser(userId,lastId);
        return fileList;
    }

    /**
     * 通过用户列表查找文件列表
     */
    @Override
    public List<File> findTenFileListByUser(int userId,int type) {
        List<File> fileList = baseMapper.findTenFileListByUser(userId,type);
        return fileList;
    }

    @Override
    public List<File> findTenFileListByUser(int userId, int lastId, FileQuery query) {
        List<File> fileList = baseMapper.findTenFileListByUserAndPage(userId,lastId,query);
        List<File> reFileList = new ArrayList<>();
        //查询有重复数据，此处过滤重复数据
        Map<Integer,File> fileMap = Maps.newHashMap();
        for(File file: fileList){
            if(fileMap.get(file.getId()) != null){
                continue;
            }else{
                fileMap.put(file.getId(),file);
                reFileList.add(file);
            }
        }

        for(File file: reFileList){
            if (!Strings.isNullOrEmpty(file.getUrl())) {
                file.setUrl(attachmentService.medialUrl(file.getUrl()));
            }
        }
        return reFileList;
    }
    @Override
    public List<File> findTenFileListByIds(List<Long> ids,int userId,String lang) {
        List<File> fileList = baseMapper.findTenFileListByIds(ids, userId,lang);
        for(File file: fileList){
            if (!Strings.isNullOrEmpty(file.getUrl())) {
                file.setUrl(attachmentService.medialUrl(file.getUrl()));
            }
        }
        return fileList;
    }

    @Override
    public void selectBySecurity(Page<FileDto> requestPage, FileQuery fileQuery) {
        List<FileDto> fileDtos = baseMapper.selectBySecurity(requestPage,
                fileQuery);
        for (FileDto fileDto : fileDtos) {
            if (!Strings.isNullOrEmpty(fileDto.getUrl())) {
                fileDto.setUrl(attachmentService.medialUrl(fileDto.getUrl()));
            }
        }
        requestPage.setRecords(fileDtos);
    }

    @Override
    public List<File> getAllFileByUserForApp(int userId) {
        List<File> fileList = baseMapper.getAllFileByUserForApp(userId);
        for(File file: fileList){
            if (!Strings.isNullOrEmpty(file.getUrl())) {
                file.setUrl(attachmentService.medialUrl(file.getUrl()));
            }
        }
        return fileList;
    }
}

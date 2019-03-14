package com.jtech.toa.service.impl.experience;

import com.google.common.base.Strings;
import com.google.common.io.Files;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.entity.experience.ExperienceComment;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.io.FileUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.dao.ExperienceShareMapper;
import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.entity.experience.ExperienceShare;
import com.jtech.toa.model.app.AppShare;
import com.jtech.toa.model.app.AppShareImage;
import com.jtech.toa.model.dto.experience.ExperienceCommentDto;
import com.jtech.toa.model.dto.experience.ExperienceDto;
import com.jtech.toa.model.query.ExperienceQuery;
import com.jtech.toa.service.experience.IExperienceCommentService;
import com.jtech.toa.service.experience.IExperienceImageService;
import com.jtech.toa.service.experience.IExperienceShareService;

import static com.jtech.marble.StringPool.EMPTY;

/**
 * <p>  经验分享 服务实现类 </p>
 *
 * @author wang
 * @since 2017-11-29
 */
@Service
public class ExperienceShareServiceImpl extends
    ServiceImpl<ExperienceShareMapper, ExperienceShare> implements
    IExperienceShareService {

  private final String filePath;
  private final String mediaUrl;
  @Autowired
  IExperienceImageService experienceImageService;

  @Autowired
  IExperienceCommentService experienceCommentService;

  public ExperienceShareServiceImpl(OaProperties oaProperties) {
    this.filePath = oaProperties.getMediaPath();
    this.mediaUrl = oaProperties.getMediaUrl();
  }

  @Override
  public void selectExperienceList(ExperienceQuery query, Page<ExperienceDto> requestPage, int id) {

    List<ExperienceDto> experienceDtoList = baseMapper.selectExperienceList(query, requestPage);
    experienceDtoList.forEach(i->{
      if (i.getCreateUser().equals(id)){
        i.setIsAllow("1");
      }else {
        i.setIsAllow("2");
      }
    });
    requestPage.setRecords(experienceDtoList);

  }


  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public ExperienceImage saveFileOverrid(File file,
      String uploldFileName,
      int userId) {

    String fileType = Files.getFileExtension(file.getAbsolutePath());
    // 存储数据
    return saveAttachmentOverrid(file, fileType, uploldFileName, userId);


  }

  @Transactional
  ExperienceImage saveAttachmentOverrid(
      File file,
      String fileType,
      String uploadFileName,
      int uploader
  ) {

    final String absolutePath = file.getAbsolutePath();
    String relativePath = StringUtils.replace(absolutePath, filePath, EMPTY);

    final Long fileSize = FileUtil.size(file);
    final ExperienceImage experienceImage = new ExperienceImage();
    experienceImage.setCommentId(0L);
    experienceImage.setShareId(0L);
    experienceImage.setFileName(uploadFileName);
    experienceImage.setCreateUser(uploader);
    experienceImage.setFileSize(fileSize.intValue());
    experienceImage.setFileType(fileType);
    experienceImage.setFilePath(relativePath);
    experienceImage.setCreateTime(DateTime.now());
    experienceImage.setUrl(relativePath);

    boolean ret = this.experienceImageService.insert(experienceImage);
    if (ret) {
      experienceImage.setUrl(medialUrl(experienceImage.getUrl()));
      return experienceImage;
    } else {
      throw new DaoException("图片上传失败");
    }
  }

  @Override
  public String medialUrl(String relativeURL) {
    if (Strings.isNullOrEmpty(relativeURL)) {
      return relativeURL;
    }
    if (relativeURL.toLowerCase().startsWith("http://") || relativeURL.toLowerCase()
        .startsWith("https://")) {
      return relativeURL;
    }
    String accessUrl = StringUtils.replace(relativeURL, File.separator, StringPool.SLASH);
    return this.mediaUrl + accessUrl;
  }

  @Override
  public List<AppShare> getShareListForApp(int currentUserId, String productName,
      String shareUserName, String title, int type, long id) {
    return this.baseMapper.getShareListForApp(currentUserId, productName,
        shareUserName, title, type, id);
  }

  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public boolean saveForApp(int currentUserId, AppShare appShare) {
    ExperienceShare share = new ExperienceShare();
    share.setSeriesId(Integer.parseInt(appShare.getProductId()));
    share.setTitle(appShare.getTitle());
    share.setContent(appShare.getContent());
    share.setStatus(1);
    share.setCreateUser(currentUserId);
    share.setCreateTime(new Date());

    this.baseMapper.insert(share);
    if (!appShare.getImageList().isEmpty()) {
      experienceImageService.saveImageForApp(appShare.getImageList());
      for (AppShareImage image : appShare.getImageList()) {
        ExperienceImage experienceImage = new ExperienceImage();
        experienceImage.setShareId(share.getId());
        experienceImage.setUrl(image.getUrl());
        experienceImage.setCreateUser(currentUserId);
        experienceImage.setCreateTime(new Date());
        experienceImageService.insert(experienceImage);
      }
    }
    return true;
  }

  @Override
  public AppShare getByIdForApp(long id,String lang) {
    return this.baseMapper.getByIdForApp(id,lang);
  }


  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public void saveOrUpdateShare(ExperienceDto experienceDto, int userId) {
    List<Long> images = null;
    ExperienceShare experienceShare = experienceDto.toExperienceShare();
    experienceShare.setCreateUser(userId);
    experienceShare.setCreateTime(new Date());
    boolean retBool = this.insertOrUpdate(experienceShare);

    if (retBool && experienceDto.getImages() != null && !("[]").equals(experienceDto.getImages())) {
      images = JSONArray.parseArray(experienceDto.getImages(), Long.class);
      experienceImageService.updateImageShareId(experienceShare.getId(), images);
    }
  }

  @Override
  public ExperienceDto selectDtoById(Long id, String lang, int userId) {
    ExperienceDto experienceDto = this.baseMapper.selectDtoById(id,lang);
    Long parentId = 0L;
    List<ExperienceCommentDto> experienceCommentList = experienceCommentService
        .selectByShareId(id, parentId,lang,userId);
    experienceCommentList.forEach(experienceCommentDto -> {
      if (experienceCommentDto.getCreateId().equals(userId)){
        experienceCommentDto.setIsAllow("1");
      }else {
        experienceCommentDto.setIsAllow("2");
      }
    });
    if (experienceCommentList.size() >0) {
      experienceDto.setCommentDtoList(experienceCommentList);
    }
    return experienceDto;
  }

  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public void deleteByShareId(String id) {
    Map<String,Object> map = new HashMap<>(1);
    map.put("id",Long.valueOf(id));
    List<ExperienceComment> experienceCommentDtoList =  experienceCommentService.selectByMap(map);
    this.baseMapper.deleteById(Long.valueOf(id));
    experienceCommentDtoList.forEach(experienceComment -> {
      experienceCommentService.deleteByCommentId(experienceComment.getId().toString());
    });
  }
}

package com.jtech.toa.service.impl.experience;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.jtech.toa.model.dto.experience.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jtech.toa.dao.ExperienceCommentMapper;
import com.jtech.toa.entity.experience.ExperienceComment;
import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.model.app.AppShareComment;
import com.jtech.toa.model.app.AppShareImage;
import com.jtech.toa.model.app.AppShareReply;
import com.jtech.toa.model.dto.experience.ExperienceCommentDto;
import com.jtech.toa.service.experience.IExperienceCommentService;
import com.jtech.toa.service.experience.IExperienceImageService;

/**
 * <p>  经验分享 服务实现类 </p>
 *
 * @author wang
 * @since 2017-11-29
 */
@Service
public class ExperienceCommentServiceImpl extends
    ServiceImpl<ExperienceCommentMapper, ExperienceComment> implements
    IExperienceCommentService {

  @Autowired
  private IExperienceImageService experienceImageService;

  @Override
  public List<AppShareComment> getByShareIdForApp(long shareId,String lang) {
    return this.baseMapper.getByShareIdForApp(shareId,lang);
  }

  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public boolean saveForApp(int currentUserId, AppShareComment shareComment) {
    ExperienceComment experienceComment = new ExperienceComment();
    experienceComment.setShareId(Long.parseLong(shareComment.getShareId()));
    experienceComment.setParentId(0L);
    experienceComment.setContent(shareComment.getContent());
    experienceComment.setStatus(1);
    experienceComment.setCreateUser(currentUserId);
    experienceComment.setCreateTime(new Date());
    this.baseMapper.insert(experienceComment);
    if (!shareComment.getImageList().isEmpty()) {
      experienceImageService.saveImageForApp(shareComment.getImageList());
      for (AppShareImage image : shareComment.getImageList()) {
        ExperienceImage experienceImage = new ExperienceImage();
        experienceImage.setShareId(Long.parseLong(shareComment.getShareId()));
        experienceImage.setCommentId(experienceComment.getId());
        experienceImage.setUrl(image.getUrl());
        experienceImage.setCreateUser(currentUserId);
        experienceImage.setCreateTime(new Date());
        experienceImageService.insert(experienceImage);
      }
    }
    return true;
  }

  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public boolean saveReplyForApp(int currentUserId, AppShareReply appShareReply) {
    ExperienceComment experienceComment = new ExperienceComment();
    experienceComment.setShareId(Long.parseLong(appShareReply.getShareId()));
    experienceComment.setParentId(Long.parseLong(appShareReply.getCommentId()));
    experienceComment.setContent(appShareReply.getContent());
    experienceComment.setStatus(1);
    experienceComment.setCreateUser(currentUserId);
    experienceComment.setCreateTime(new Date());
    this.baseMapper.insert(experienceComment);
    return true;
  }

  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public boolean saveOrUpdateComment(ExperienceCommentDto experienceCommentDto, int userId) {
    List<Long> images;
    ExperienceComment experienceComment = new ExperienceComment();
    experienceComment.setShareId(experienceCommentDto.getShareId());
    experienceComment.setParentId(experienceCommentDto.getParentId());
    experienceComment.setContent(experienceCommentDto.getContent());
    experienceComment.setStatus(1);
    experienceComment.setCreateUser(userId);
    experienceComment.setCreateTime(new Date());
    boolean rtnBool = this.baseMapper.insert(experienceComment) == 1;
    if (rtnBool && experienceCommentDto.getImages() != null && !("[]")
        .equals(experienceCommentDto.getImages()) && !("")
        .equals(experienceCommentDto.getImages())) {
      images = JSONArray.parseArray(experienceCommentDto.getImages(), Long.class);
      experienceImageService.updateImageCommentId(experienceComment.getId(), images);
    }
    return true;
  }

  @Override
  public List<ExperienceCommentDto> selectByShareId(Long shareId, Long parentId, String lang, int userId) {
    List<ExperienceCommentDto> experienceCommentDtoList = this.baseMapper
        .selectListByShareId(shareId,parentId, lang);
    for (ExperienceCommentDto experienceCommentDto : experienceCommentDtoList) {
      experienceCommentDto
          .setListImages(experienceImageService.getImagesByCommentId(experienceCommentDto.getId()));
      Map<String, Object> map = new HashMap<>(1);
      //回复
      List<CommentDto> experienceCommentList = this.baseMapper.selectListByMap(experienceCommentDto.getId(),lang);
      experienceCommentList.forEach(commentDto -> {
        if (commentDto.getCreateId().equals(userId)){
          commentDto.setIsAllow("1");
        }else {
          commentDto.setIsAllow("2");
        }
      });
      experienceCommentDto.setExperienceCommentList(experienceCommentList);
    }
    return experienceCommentDtoList;
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public void deleteByCommentId(String id) {
    this.baseMapper.deleteById(Long.valueOf(id));
    Map<String, Object> experienceComment = new HashMap<>(1);
    experienceComment.put("parent_id", Long.valueOf(id));
    this.baseMapper.deleteByMap(experienceComment);
    experienceImageService.deleteByCommentId(Long.valueOf(id));
  }

  @Override
  @Transactional(readOnly = true,rollbackFor = Exception.class)
  public void deleteByReplyId(String id) {
    this.baseMapper.deleteById(Long.valueOf(id));
  }
}

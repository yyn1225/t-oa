package com.jtech.toa.service.experience;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.model.app.AppShareImage;
import java.util.Map;

/**
 * <p> 经验分享 接口 服务类 </p>
 *
 * @author wang
 * @since 2017-11-29
 */
public interface IExperienceImageService extends IService<ExperienceImage> {

  /**
   * 取出经验分享的图片
   *
   * @param shareId 分享id
   */
  List<AppShareImage> getByShareIdForApp(long shareId);

  /**
   * 保存 分享 图片
   * @param shareId
   * @param seriesId
   * @return
   */
  Integer updateImageShareId(Long shareId, List<Long> seriesId);

  /**
   * 保存  评论 图片
   * @param commentId
   * @param seriesId
   * @return
   */
  Integer updateImageCommentId(Long commentId, List<Long> seriesId);

  void deleteImage(Long id);

  /**
   * 获取 分享 图片集合
   *
   * @param shareId 分享id
   */
  List<ExperienceImage> getImageListById(long shareId);

  /**
   * 获取 评论 图片 集合
   * @param commentId
   * @return
   */
  List<ExperienceImage> getImagesByCommentId(long commentId);


  /**
   * app api 保存经验分享
   * @param appShareImageList
   * @return
   */
  boolean saveImageForApp(List<AppShareImage> appShareImageList);

  /**
   * 删除评论图片
   * @param id
   */
  void deleteByCommentId(Long id);
}

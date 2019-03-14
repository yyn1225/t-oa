package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.model.app.AppShareImage;

/**
 * <p> Mapper 接口 </p>
 *
 * @author wang
 * @since 2018-08-03
 */
public interface ExperienceImageMapper extends BaseMapper<ExperienceImage> {

  /**
   * 取出经验分享的图片
   *
   * @param shareId 分享id
   */
  List<AppShareImage> getByShareIdForApp(@Param("shareId") long shareId);

  /**
   * 分享 图片
   */
  Integer updateImageShareId(@Param("shareId") Long shareId, @Param("ids") List<Long> ids);

  /**
   * 评论 图片
   */
  Integer updateImageCommentId(@Param("commentId") Long commentId, @Param("ids") List<Long> ids);
}

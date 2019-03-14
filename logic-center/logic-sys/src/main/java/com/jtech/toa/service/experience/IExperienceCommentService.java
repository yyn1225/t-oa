package com.jtech.toa.service.experience;

import com.baomidou.mybatisplus.service.IService;

import com.jtech.toa.model.dto.experience.ExperienceCommentDto;
import java.util.List;

import com.jtech.toa.entity.experience.ExperienceComment;
import com.jtech.toa.model.app.AppShareComment;
import com.jtech.toa.model.app.AppShareReply;

/**
 * <p> 经验分享 接口 服务类 </p>
 *
 * @author wang
 * @since 2017-11-29
 */
public interface IExperienceCommentService extends IService<ExperienceComment> {


  /**
   * app api 获取经验分享评论
   *
   * @param shareId 经验分享id
   */
  List<AppShareComment> getByShareIdForApp(long shareId,String lang);

  /**
   * app api 保存评论信息
   *
   * @param currentUserId 当前用户id
   * @param shareComment 评论信息
   */
  boolean saveForApp(int currentUserId, AppShareComment shareComment);

  /**
   * app api 保存回复信息
   *
   * @param currentUserId 当前用户id
   * @param appShareReply 回复信息
   */
  boolean saveReplyForApp(int currentUserId, AppShareReply appShareReply);

  /**
   * 评论
   */
  boolean saveOrUpdateComment(ExperienceCommentDto experienceCommentDto, int userId);

  /**
   * 获取评论
   */
  List<ExperienceCommentDto> selectByShareId(Long id, Long parentId, String lang, int userId);

  /**
   * 删除评论
   * @param id
   */
  void deleteByCommentId(String id);

  /**
   * 删除回复
   * @param id
   */
  void deleteByReplyId(String id);
}

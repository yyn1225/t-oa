package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.jtech.toa.model.dto.experience.CommentDto;
import com.jtech.toa.model.dto.experience.ExperienceCommentDto;
import java.util.List;

import com.jtech.toa.entity.experience.ExperienceComment;
import com.jtech.toa.model.app.AppShareComment;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2018-08-03
 */
public interface ExperienceCommentMapper extends BaseMapper<ExperienceComment> {

    /**
     * app api 获取经验分享评论
     * @param shareId 经验分享id
     * @return
     */
    List<AppShareComment> getByShareIdForApp( @Param("shareId") long shareId , @Param("lang")  String lang);


  /**
   *  获取 评论 或者 回复
   * @param shareId
   * @param parentId
   * @return
   */
    List<ExperienceCommentDto> selectListByShareId(@Param("shareId") Long shareId,@Param("parentId") Long parentId,@Param("lang") String lang);

    List<CommentDto> selectListByMap(@Param("parentId") Long parentId ,@Param("lang") String lang);
}
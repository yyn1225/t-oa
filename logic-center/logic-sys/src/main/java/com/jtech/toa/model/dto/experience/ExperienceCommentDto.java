package com.jtech.toa.model.dto.experience;

import com.jtech.toa.entity.experience.ExperienceImage;
import java.util.List;
import lombok.Data;

/**
 * <p></p>
 *
 * @author wang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ExperienceCommentDto {

  private Long id;
  private Long shareId;
  private Long parentId;
  private Integer status;
  private String content;
  private String createUser;
  private Integer createId;
  private String createTime;
  private String modifyUser;
  private String modifyTime;
  /**
   *   1 可操作  2 ，不可操作
   */
  private String isAllow;
  private String images;

  private List<ExperienceImage> listImages;

  //回复
  private List<CommentDto> experienceCommentList;

}

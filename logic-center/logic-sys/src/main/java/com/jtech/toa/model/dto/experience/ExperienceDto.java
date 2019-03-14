package com.jtech.toa.model.dto.experience;

import com.jtech.toa.entity.experience.ExperienceShare;
import java.util.Date;
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
public class ExperienceDto {

  private Long id;
  private Integer seriesId;
  private Integer seriesLine;
  private String title;
  private String productName;
  private String content;
  private Integer status;
  private Integer createUser;
  private String createUserName;
  private Date createTime;
  private Integer modifyUser;
  private Date modifyTime;

  /**
   *   1 可操作  2 ，不可操作
   */
  private String isAllow;

  private List<ExperienceCommentDto> commentDtoList;

  private String images;

  public  ExperienceShare toExperienceShare(){
    ExperienceShare experienceShare = new ExperienceShare();
    experienceShare.setSeriesId(this.seriesId);
    experienceShare.setTitle(this.title);
    experienceShare.setContent(this.content);
    experienceShare.setStatus(1);
    experienceShare.setId(this.id);
    return experienceShare;
  }
}

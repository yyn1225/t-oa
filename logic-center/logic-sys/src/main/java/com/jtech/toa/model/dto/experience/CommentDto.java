package com.jtech.toa.model.dto.experience;

import lombok.Data;

/**
 * <p></p>
 *
 * @author wang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class CommentDto {

  private Long id;
  private Long shareId;
  private Long parentId;
  private Integer status;
  private String content;
  private String createUser;
  private Integer createId;
  private String parentUser;
  private String createTime;
  private String modifyUser;
  private String modifyTime;
  private String images;
  /**
   *   1 可操作  2 ，不可操作
   */
  private String isAllow;


}

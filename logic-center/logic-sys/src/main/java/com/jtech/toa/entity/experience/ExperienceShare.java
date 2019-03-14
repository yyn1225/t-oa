package com.jtech.toa.entity.experience;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jtech.toa.entity.product.Params;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * <p></p>
 *
 * @author wang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("absen_experience_share")
public class ExperienceShare extends Model<ExperienceShare> {

  private static final long serialVersionUID = 1L;
  @TableField("id")
  private Long id;
  @TableField("series_id")
  private Integer seriesId;
  @TableField("title")
  private String title;
  @TableField("content")
  private String content;
  @TableField("status")
  private Integer status;
  @TableField("create_user")
  private Integer createUser;
  @TableField("create_time")
  private Date createTime;
  @TableField("modify_user")
  private Integer modifyUser;
  @TableField("modify_time")
  private Date modifyTime;


  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}

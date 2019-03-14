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
 * @author  wang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("absen_experience_image")
public class ExperienceImage extends Model<ExperienceImage> {

  private static final long serialVersionUID = 1L;

  @TableField("id")
  private Long id;
  @TableField("share_id")
  private Long shareId;
  @TableField("comment_id")
  private Long commentId;
  @TableField("url")
  private String url;
  @TableField("create_user")
  private Integer createUser;
  @TableField("create_time")
  private Date createTime;
  @TableField("file_name")
  private String fileName;
  @TableField("file_type")
  private String fileType;
  @TableField("file_path")
  private String filePath;
  @TableField("file_size")
  private Integer fileSize;


  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}

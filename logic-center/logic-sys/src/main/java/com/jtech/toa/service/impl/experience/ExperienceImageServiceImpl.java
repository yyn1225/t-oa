package com.jtech.toa.service.impl.experience;

import static com.jtech.marble.StringPool.DASH;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.jtech.marble.StringPool;
import com.jtech.marble.util.DateUtil;
import com.jtech.marble.util.IdUtil;
import com.jtech.marble.util.io.FilePathUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.dao.ExperienceImageMapper;
import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.model.app.AppShareImage;
import com.jtech.toa.service.experience.IExperienceImageService;
import java.io.File;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>  经验分享 服务实现类 </p>
 *
 * @author wang
 * @since 2017-11-29
 */
@Service
public class ExperienceImageServiceImpl extends
    ServiceImpl<ExperienceImageMapper, ExperienceImage> implements IExperienceImageService {

  @Override
  public List<AppShareImage> getByShareIdForApp(long shareId) {
    return this.baseMapper.getByShareIdForApp(shareId);
  }

  private final String filePath;

  public ExperienceImageServiceImpl(OaProperties oaProperties) {
    this.filePath = oaProperties.getMediaPath();
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public Integer updateImageShareId(Long shareId, List<Long> seriesId) {
    return this.baseMapper.updateImageShareId(shareId, seriesId);
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public Integer updateImageCommentId(Long commentId, List<Long> seriesId) {
    return this.baseMapper.updateImageCommentId(commentId, seriesId);
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public void deleteImage(Long id) {

    ExperienceImage experienceImage = this.baseMapper.selectById(id);

    File file = new File(filePath + experienceImage.getUrl());
    if (file.exists()) {
      file.delete();
    }
    deleteById(id);
  }

  @Override
  public List<ExperienceImage> getImageListById(long shareId) {
    Map<String, Object> map = new HashMap<>(1);
    map.put("share_id", shareId);
    return this.baseMapper.selectByMap(map);
  }

  @Override
  public List<ExperienceImage> getImagesByCommentId(long commentId) {
    Map<String, Object> map = new HashMap<>(1);
    map.put("comment_id", commentId);
    List<ExperienceImage> listImages = this.baseMapper.selectByMap(map);
    for (ExperienceImage e : listImages) {
      e.setUrl("OTA/" + e.getUrl());
    }
    return listImages;
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public boolean saveImageForApp(List<AppShareImage> appShareImageList) {

    for (AppShareImage appShareImage : appShareImageList) {
      // 对字节数组字符串进行Base64解码并生成图片
      if (appShareImage.getUrl() != null && !("").equals(appShareImage.getUrl())) {
        try {
          String initialStr = appShareImage.getUrl();
          int position = initialStr.indexOf(";");
          String suffix = initialStr.substring(11,position);
          // Base64解码  需要替换这些数据 否则生成的图片是损坏的
          String uri = appShareImage.getUrl().replace("data:image/"+suffix+";base64,", "");
          byte[] decoderBytes = Base64.decodeBase64(uri);
          for (int i = 0; i < decoderBytes.length; ++i) {
            if (decoderBytes[i] < 0) {// 调整异常数据
              decoderBytes[i] += 256;
            }
          }
          String pictureName = IdUtil.uuid() + StringPool.DOT + suffix;
          String yyyyMMdd = DateUtil.yyyyMMddDash(new Date());
          String saveUri =
              StringUtils.replace(yyyyMMdd, DASH, File.separator) + File.separator + pictureName;
          String datePath = filePath + StringUtils.replace(yyyyMMdd, DASH, File.separator);

          appShareImage.setUrl(saveUri);
          String pathname = FilePathUtil.contact(datePath, pictureName);
          File file = new File(pathname);
          if (!file.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            file.getParentFile().mkdir();
            try {
              //创建文件
              file.createNewFile();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          // 生成新的图片
          OutputStream out = new FileOutputStream(file);
          out.write(decoderBytes);
          out.flush();
          out.close();
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    }
    return true;
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public void deleteByCommentId(Long commentId) {
    Map<String, Object> map = new HashMap<>(1);
    map.put("comment_id", commentId);
    List<ExperienceImage> listImages = this.baseMapper.selectByMap(map);
    for (ExperienceImage experienceImage : listImages) {
      File file = new File(filePath + experienceImage.getUrl());
      if (file.exists()) {
        file.delete();
      }
    }
  }
}

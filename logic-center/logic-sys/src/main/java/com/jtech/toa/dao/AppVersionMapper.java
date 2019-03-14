package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.AppVersion;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2018-04-09
 */
public interface AppVersionMapper extends BaseMapper<AppVersion> {

    List<AppVersion> findNewVersionByType(@Param("type") int type);

}
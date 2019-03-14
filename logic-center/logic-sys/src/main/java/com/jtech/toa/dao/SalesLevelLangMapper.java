package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.customer.LevelLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
public interface SalesLevelLangMapper extends BaseMapper<LevelLang> {

    List<LevelLang> selectByLevelId(@Param("levelId") int levelId);

    int deleteByLevelId(@Param("levelId") int levelId);
}

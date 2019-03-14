package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.system.Language;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *     语种 mapper 接口
 * </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
public interface LanguageMapper extends BaseMapper<Language> {

    /**
     * @return 语言集合
     */
    List<Language> selectLanguageList();

    /**
     * 根据code查询语言的信息
     * @param lang 语言的code
     * @return 语言的对象
     */
    Language selectByCode(@Param("lang") String lang);
}

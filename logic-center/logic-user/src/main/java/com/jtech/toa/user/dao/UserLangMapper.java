/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.user.dao;

import com.jtech.toa.user.entity.UserLang;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.user.model.dto.UserLangDto;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
public interface UserLangMapper extends BaseMapper<UserLang> {

    /**
     * 通过用户id查询语言信息
     *
     * @param userId 用户的id
     * @return 语言信息的集合
     */
    List<UserLangDto> selectByUserId(int userId);

    /**
     * 通过用户id和语言来查询符合的语言信息
     * @param lang 语言的code
     * @param userId 用户的id
     * @return 符合的语言code集合
     */
    String selectInLang(@Param("lang") String lang,@Param("userId") int userId);

    /**
     * 更新
     * @param userLang
     * @return
     */
    int updateByUserIdAndLang(@Param("userLang") UserLang userLang);

    /**
     * 更新
     * @param userLang
     * @return
     */
    int insertUserId(@Param("userLang") UserLang userLang);
}
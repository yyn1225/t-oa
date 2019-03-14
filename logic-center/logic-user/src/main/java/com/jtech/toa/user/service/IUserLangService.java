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

package com.jtech.toa.user.service;

import com.jtech.toa.user.entity.UserLang;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.user.model.dto.UserLangDto;

import java.util.List;

/**
 * <p> 服务类 </p>
 *
 * @author jtech
 * @since 2017-10-19
 */
public interface IUserLangService extends IService<UserLang> {

    /**
     * 通过用户id查询语言信息
     *
     * @param userId 用户的id
     * @return 语言信息的集合
     */
    List<UserLangDto> findByUserId(int userId);

    /**
     * 更新用户的语言信息
     * @param userId 用户的id
     * @param nameLang 语言
     * @param lang 语言code
     * @return 是否成功
     */
    boolean updateNameLangByUserId(int userId,String nameLang,String lang);
}

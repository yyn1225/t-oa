/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-$today.yea
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.SecurityUserDto;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.model.dto.UserSelect2Dto;
import com.jtech.toa.user.model.query.UserQuery;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jtech tao.ding
 * @since 2017-07-10
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据名称或手机号查询信息
     * @param username 名称或手机号
     * @return 用户信息
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据手机号查询信息
     * @param phone 手机
     * @return 用户信息
     */
    User selectByPhone(@Param("phone") String phone);

    /**
     * 根据分页信息获取用户数据
     * @Param requestPage 分页参数
     * @param query 分页及查询参数
     * @param lang 用户默认语言
     * @return 用户数据
     */
    List<UserDto> selectUserListByPage(Page<UserDto> requestPage, @Param("query") UserQuery query, @Param("lang") String lang,@Param("listIds") List<Integer> list);

    /**
     * 获取全部用户，下拉框
     * @param lang 语言
     * @return 数据的集合
     */
    List<UserSelect2Dto> selectAllForSelect2(@Param("lang") String lang);

    /**
     * 根据用户id查询给app的信息
     * @param userId 用户id
     * @return APP用户DTO的集合
     */
    UserAppDto selectForAppByUserId(int userId);

    List<UserDto> selectAllUser();

    int deleteUser(@Param("id")int id);
    int enableUser(@Param("id")int id);

    /**
     * 查询所有用户
     * @return 用户集合
     */
    List<SecurityUserDto> selectUserList();

    List<User> getListByArea(int area);

    /**
     * 根据角色获取用户
     * @param role 角色
     * @return 角色
     */
    List<User> findUsersByRole(@Param("role") int role);

    /**
     * 根据分页信息获取用户数据
     * @Param requestPage 分页参数
     * @param query 分页及查询参数
     * @param lang 用户默认语言
     * @return 用户数据
     */
    List<UserDto> selectUserAssistantListByPage(Page<UserDto> requestPage, @Param("query") UserQuery query, @Param("lang") String lang);

    /**
     * 当assistant 为空时更新字段
     * @param user
     */
     int updateUserAssistant(@Param("user") User user);

     List<UserDto> selectUserByIds(@Param("ids")List<Integer> ids) ;
}
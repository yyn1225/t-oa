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

package com.jtech.toa.user.service;

import com.google.common.base.Optional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.SecurityUserDto;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.model.dto.UserSelect2Dto;
import com.jtech.toa.user.model.query.UserQuery;

import java.util.List;

/**
 * <p> 用户表 服务类 </p>
 *
 * @author jtech
 * @since 2017-07-10
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名称或手机号获取用户信息
     *
     * @param username 用户名称或手机号，一般为登录帐号
     * @return 用户信息
     */
    Optional<User> findByUsername(String username);

    /**
     * 根据用户手机，获取用户信息
     *
     * @param phone 用户手机
     * @return 用户信息
     */
    Optional<User> findByPhone(String phone);

    /**
     * 通过用户id查询用户信息
     *
     * @param userId 用户id
     */
    Optional<User> findByUserId(int userId);

    /**
     * 添加用户
     */

    boolean addUser(int loginUserId, UserDto userDto, String deptIds);

    /**
     * 更新用户
     */
    boolean updateUser(int loginUserId, UserDto userDto, String deptIds);

    /**
     * 更新用户冻结状态
     *
     * @param loginUserId 登录用户id
     * @param userId      用户id
     * @param status      用户要改变成的状态
     * @return 更新状态
     */
    boolean updateStatus(int loginUserId, int userId, int status);

    /**
     * 重置密码
     */
    boolean updatePassword(int loginUserId, UserDto userDto);

    /**
     * 根据分页信息获取用户数据
     *
     * @param query 分页及查询参数
     */
    void selectUserListByPage(Page<UserDto> requestPage, UserQuery query);

    /**
     * 根据语言获取用户的信息
     * @param lang 语言的信息
     * @return 用户的集合
     */
    List<UserSelect2Dto> findAllForSelect2(String lang);

    /**
     * 根据用户id查询给app的信息
     * @param userId 用户id
     * @return APP用户DTO的集合
     */
    UserAppDto findForAppByUserId(int userId);
    List<UserDto> selectAllUser();

    boolean deleteUser(int id);
    boolean enableUser(int id);

    /**
     * 查询所有用户
     * @return 用户集合
     */
    List<SecurityUserDto> selectUserList();

    /**
     * 获取某个部门下的所有用户
     * @param area
     * @return
     */
    List<User> selectUserListByArea(int area);

    List<User> findUsersByRole(int role);

    /**
     * 根据分页信息获取销售助理用户数据
     * @param query 分页及查询参数
     * @return 用户数据
     */
    public void selectUserAssistantListByPage(Page<UserDto> requestPage, UserQuery query);

    /**
     * 查询所有用户 通过用户id集合
     * @param  ids
     * @return 用户集合
     */
    List<UserDto> selectUserListByIds(List<Integer> ids);

}

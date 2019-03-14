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

package com.jtech.toa.user.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.util.PasswordUtil;
import com.jtech.toa.user.dao.UserMapper;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.DepartmentUser;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.SecurityUserDto;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.model.dto.UserSelect2Dto;
import com.jtech.toa.user.model.query.UserQuery;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IDepartmentUserService;
import com.jtech.toa.user.service.IUserService;
import com.xiaoleilu.hutool.crypto.SecureUtil;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-07-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IDepartmentService departmentService;
    private final IDepartmentUserService departmentUserService;

    @Autowired
    public UserServiceImpl(IDepartmentService departmentService,
                           IDepartmentUserService departmentUserService) {
        this.departmentService = departmentService;
        this.departmentUserService = departmentUserService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        if (Strings.isNullOrEmpty(username)) {
            return Optional.absent();
        }
        User user = baseMapper.selectByUsername(username);
        if (user == null) {
            LOGGER.warn("用户名称{} 查询用户不存在！", username);
            return Optional.absent();
        }
        return Optional.of(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByPhone(String phone) {
        if (Strings.isNullOrEmpty(phone)) {
            return Optional.absent();
        }
        User user = baseMapper.selectByPhone(phone);
        if (user == null) {
            return Optional.absent();
        }
        return Optional.of(user);
    }

    /**
     * 查询用户的信息根据用户的id
     *
     * @param userId 用户id
     */
    @Override
    public Optional<User> findByUserId(int userId) {
        User user = baseMapper.selectById(userId);
        if (user == null) {
            return Optional.absent();
        }
        return Optional.of(user);
    }

    /**
     * 添加用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(int loginUserId, UserDto userDto, String deptIds) {
        deptIds= "["+deptIds+"]";
        List<Integer> oldList = JSON.parseArray(deptIds, Integer.class);
        boolean ok = true;
        List<Integer> deptIdList = checkUser(oldList);
        Department department = departmentService.selectDeptByIds(deptIdList);
        //如果当前选择的区域等级最高为3
        if (department.getLevel() == 3) {
            department.setId(department.getParent());
        }
        userDto.setAreaId(department.getId());
        User user = userDto.toUser();
        final String md5password = SecureUtil.md5("123456");
        final byte[] randomSaltBytes = PasswordUtil.generateSalt();
        final String password = PasswordUtil.password(randomSaltBytes, md5password);
        final String randomSalt = PasswordUtil.salt(randomSaltBytes);
        user.setPassword(password);
        user.setSalt(randomSalt);
        user.setCreater(loginUserId);
        user.setCreateTime(new Date());
        user.setStatus(User.Status.Valid);
        user.setDeleteFlag(User.DeleteFlag.NO);
        user.setDeleteAble(User.DeleteAble.YES);
        ok =  baseMapper.insert(user) == 1;
        ok =  departmentUserService.deleteByUser(user.getId());
        if (CollectionUtils.isNotEmpty(deptIdList)) {
            for (Integer departmentId : deptIdList) {
                DepartmentUser departmentUser = new DepartmentUser();
                departmentUser.setDepartment(departmentId);
                departmentUser.setUsers(user.getId());
                ok = departmentUserService.insert(departmentUser);
            }
        }
        return ok;
    }

    /**
     * 更新用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(int loginUserId, UserDto userDto, String deptIds) {
        String[] str  = deptIds.split(",");
        List<Integer> oldList = Lists.newArrayList();
        for (String s : str) {
            oldList.add(Integer.parseInt(s));
        }
        List<Integer> deptIdList = checkUser(oldList);
        boolean ok = true;
        Department department = departmentService.selectDeptByIds(deptIdList);
        //如果当前选择的区域等级最高为3
        if (department.getLevel() == 3) {
            department.setId(department.getParent());
        }
        userDto.setAreaId(department.getId());
        User user = userDto.toUser();
//        user.setu(DateUtil.unixTime());
//        user.setUpdater(loginUserId);
        if (user.getAssistant() == null){
            ok = baseMapper.updateUserAssistant(user)==1;
        }
        ok =  baseMapper.updateById(user) == 1;
        ok =  departmentUserService.deleteByUser(user.getId());
        if (CollectionUtils.isNotEmpty(deptIdList)) {
            for (Integer departmentId : deptIdList) {
                DepartmentUser departmentUser = new DepartmentUser();
                departmentUser.setDepartment(departmentId);
                departmentUser.setUsers(user.getId());
                ok = departmentUserService.insert(departmentUser);
            }
        }
        return ok;
    }

    /**
     * 重置密码
     */
    @Override
    public boolean updatePassword(int loginUserId, UserDto userDto) {
        User user = new User();
        final byte[] randomSaltBytes = PasswordUtil.generateSalt();
        final String userPass = PasswordUtil.password(randomSaltBytes, userDto.getPassword());
        final String randomSalt = PasswordUtil.salt(randomSaltBytes);
        user.setPassword(userPass);
        user.setSalt(randomSalt);
        user.setId(userDto.getId());
//        user.setUpdateTime(DateUtil.unixTime());
//        user.setUpdater(loginUserId);
        return baseMapper.updateById(user) == 1;
    }

    private List<Integer> checkUser(List<Integer> deptIdList) {
        List<Integer> newList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(deptIdList)) {
            for (Integer deptId : deptIdList) {
                if (0 == deptId) {
                    Department department = departmentService.selectOne(new EntityWrapper<Department>().eq("level", 1));
                    if (department != null) {
                        newList.add(department.getId());
                    }
                }else {
                    newList.add(deptId);
                }
            }
            return newList;
        }
        return Lists.newArrayList();
    }

    /**
     * 更新用户冻结状态
     *
     * @param loginUserId 登录用户id
     * @param userId      用户id
     * @param status      用户要改变成的状态
     * @return 更新状态
     */
    @Override
    public boolean updateStatus(int loginUserId, int userId, int status) {
        User user = new User();
        user.setId(userId);
        user.setStatus(status);
        return baseMapper.updateById(user) == 1;
    }

    /**
     * 根据分页信息获取用户数据
     * @param query 分页及查询参数
     * @return 用户数据
     */
    @Override
    public void selectUserListByPage(Page<UserDto> requestPage, UserQuery query){

        List<Integer> list = null;
        if(!"".equals(query.getAreaIds()) && query.getAreaIds() != null ){
            list = JSON.parseArray(query.getAreaIds(), Integer.class);
        }
        //TODO 需要通过当前登录人选择的语言查询数据
        List<UserDto> userDtos = baseMapper.selectUserListByPage(requestPage, query, "" ,list);
        requestPage.setRecords(userDtos);
    }

    @Override
    public List<UserSelect2Dto> findAllForSelect2(String lang) {
        return baseMapper.selectAllForSelect2(lang);
    }

    @Override
    public UserAppDto findForAppByUserId(int userId) {
        return baseMapper.selectForAppByUserId(userId);
    }

    @Override
    public List<UserDto> selectAllUser() {
        return baseMapper.selectAllUser();
    }

    @Override
    public boolean deleteUser(int id) {
        return baseMapper.deleteUser(id)==1;
    }

    @Override
    public boolean enableUser(int id) {
        return baseMapper.enableUser(id)==1;
    }

    @Override
    public List<SecurityUserDto> selectUserList() {
        return baseMapper.selectUserList();
    }

    @Override
    public List<User> selectUserListByArea(int area) {
        return baseMapper.getListByArea(area);
    }

    @Override
    public List<User> findUsersByRole(int role){
        return baseMapper.findUsersByRole(role);
    }

    /**
     * 根据分页信息获取销售助理用户数据
     * @param query 分页及查询参数
     * @return 用户数据
     */
    @Override
    public void selectUserAssistantListByPage(Page<UserDto> requestPage, UserQuery query){
        //TODO 需要通过当前登录人选择的语言查询数据
        List<UserDto> userDtos = baseMapper.selectUserAssistantListByPage(requestPage, query, "");
        requestPage.setRecords(userDtos);
    }

    @Override
    public List<UserDto> selectUserListByIds(List<Integer> ids){

        List<UserDto> userDtos = baseMapper.selectUserByIds(ids);
        return  userDtos;
    }

}

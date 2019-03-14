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

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import com.jtech.toa.user.dao.UserTypeMapper;
import com.jtech.toa.user.entity.UserType;
import com.jtech.toa.user.service.IUserTypeService;

import java.util.List;

/**
 * <p>
 * 用户类型表 服务实现类
 * </p>
 *
 * @author jtech tao.ding
 * @since 2017-07-10
 */
@Service
public class UserTypeServiceImpl extends ServiceImpl<UserTypeMapper, UserType> implements IUserTypeService {

    /**
     * 下拉框查询所有用户类型
     * @return
     */
    @Override
    public List<UserType> selectAll(){
        return baseMapper.selectAll();
    }
}

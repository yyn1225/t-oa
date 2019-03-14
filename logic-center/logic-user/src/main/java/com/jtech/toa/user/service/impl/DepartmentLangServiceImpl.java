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

package com.jtech.toa.user.service.impl;

import com.google.common.base.Strings;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.user.dao.DepartmentLangMapper;
import com.jtech.toa.user.entity.DepartmentLang;
import com.jtech.toa.user.service.IDepartmentLangService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
@Service
public class DepartmentLangServiceImpl extends ServiceImpl<DepartmentLangMapper, DepartmentLang> implements IDepartmentLangService {

    @Override
    public boolean removeByDeparmentId(int deparmentId) {
        return baseMapper.deleteByDeparmentId(deparmentId) >= 0;
    }

    @Transactional
    @Override
    public boolean insertDepartmentLang(String langVal, int departmentId) {
        boolean isOk = true;
        if (!Strings.isNullOrEmpty(langVal)) {
            isOk = removeByDeparmentId(departmentId);
            if (!isOk) {
                throw new DaoException("删除语言信息失败!");
            }
            JSONObject jsonObject = JSON.parseObject(langVal);
            DepartmentLang departmentLang;
            for (String s : jsonObject.keySet()) {
                departmentLang = new DepartmentLang();
                departmentLang.setLang(s);
                departmentLang.setNameLang(jsonObject.getString(s));
                departmentLang.setDepartment(departmentId);
                isOk = insert(departmentLang);
                if (!isOk) {
                    throw new DaoException("插入语言信息失败!");
                }
            }
        }
        return isOk;
    }

    @Override
    public List<DepartmentLang> findByDepartmentId(Integer id) {
        return baseMapper.selectByDepartmentId(id);
    }
}

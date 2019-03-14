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

package com.jtech.toa.service.impl.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.RoleLangMapper;
import com.jtech.toa.entity.system.RoleLang;
import com.jtech.toa.service.system.IRoleLangService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
@Service
public class RoleLangServiceImpl extends ServiceImpl<RoleLangMapper, RoleLang> implements IRoleLangService {

    @Override
    public List<RoleLang> selectByRoleId(int roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public boolean deleteByRoleId(int roleId) {
        return baseMapper.deleteByRoleId(roleId) > 0;
    }

    @Override
    @Transactional
    public boolean insertRoleLang(String langVal, int roleId) {
        boolean isOk = true;
        if (!Strings.isNullOrEmpty(langVal)) {
            isOk = deleteByRoleId(roleId);
//            if (!isOk) {
//                throw new DaoException("删除语言信息失败!");
//            }
            JSONObject jsonObject = JSON.parseObject(langVal);
            RoleLang roleLang;
            for (String s : jsonObject.keySet()) {
                roleLang = new RoleLang();
                roleLang.setLanguage(s);
                roleLang.setNameLang(jsonObject.getString(s));
                roleLang.setRole(roleId);
                isOk = insert(roleLang);
                if (!isOk) {
                    throw new DaoException("插入语言信息失败!");
                }
            }
        }
        return isOk;
    }
}

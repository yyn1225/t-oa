/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.system;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.LanguageMapper;
import com.jtech.toa.entity.system.Language;
import com.jtech.toa.service.system.ILanguageService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     语言 服务实现类
 * </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class LanguageServiceImpl extends ServiceImpl<LanguageMapper, Language> implements ILanguageService{

    @Override
    public List<Language> selectLanguageList() {
        return baseMapper.selectLanguageList();
    }

    @Override
    public Language findByCode(String lang) {
        return baseMapper.selectByCode(lang);
    }
}

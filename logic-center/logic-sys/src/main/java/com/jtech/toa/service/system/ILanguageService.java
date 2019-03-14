/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.system.Language;

import java.util.List;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
public interface ILanguageService extends IService<Language> {

    List<Language> selectLanguageList();

    /**
     * 根据code查询语言的信息
     * @param lang 语言的code
     * @return 语言的对象
     */
    Language findByCode(String lang);
}

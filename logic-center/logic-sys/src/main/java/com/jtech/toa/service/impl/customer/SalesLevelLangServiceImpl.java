package com.jtech.toa.service.impl.customer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.SalesLevelLangMapper;
import com.jtech.toa.entity.customer.LevelLang;
import com.jtech.toa.service.customer.ISalesLevelLangService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class SalesLevelLangServiceImpl extends ServiceImpl<SalesLevelLangMapper, LevelLang> implements ISalesLevelLangService {

    @Override
    public List<LevelLang> selectByLevelId(int levelId) {
        return baseMapper.selectByLevelId(levelId);
    }

}

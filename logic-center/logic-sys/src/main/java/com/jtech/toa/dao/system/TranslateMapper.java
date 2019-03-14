package com.jtech.toa.dao.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.jtech.toa.entity.system.Translate;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2018-03-12
 */
public interface TranslateMapper extends BaseMapper<Translate> {

    /**
     * 得到翻译表所有的数据
     * @return List<Translate>
     */
    public List<Translate> getForList();
}
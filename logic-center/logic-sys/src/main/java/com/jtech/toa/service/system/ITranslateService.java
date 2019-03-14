package com.jtech.toa.service.system;

import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

import com.jtech.toa.entity.system.Translate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2018-03-12
 */
public interface ITranslateService extends IService<Translate> {

	Map<String,String> getForList();
}

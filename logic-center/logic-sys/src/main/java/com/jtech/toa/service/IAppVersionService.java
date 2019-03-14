package com.jtech.toa.service;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.AppVersion;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2018-04-09
 */
public interface IAppVersionService extends IService<AppVersion> {

    AppVersion findNewVersionByType(int softwareType);
	
}

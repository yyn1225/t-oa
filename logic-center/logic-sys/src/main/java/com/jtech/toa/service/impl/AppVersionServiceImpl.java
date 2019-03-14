package com.jtech.toa.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.AppVersionMapper;
import com.jtech.toa.entity.AppVersion;
import com.jtech.toa.service.IAppVersionService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2018-04-09
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements IAppVersionService {


	public AppVersion findNewVersionByType(int type){
        List<AppVersion> softwareVersions = baseMapper.findNewVersionByType(type);
        if(softwareVersions == null || softwareVersions.size() == 0){
            return null;
        }else{
            return softwareVersions.get(0);
        }
    }
}

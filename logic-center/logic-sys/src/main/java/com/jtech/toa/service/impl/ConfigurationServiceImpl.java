package com.jtech.toa.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.ConfigurationMapper;
import com.jtech.toa.entity.basic.Configuration;
import com.jtech.toa.model.dto.products.AppConfiguration;
import com.jtech.toa.service.IConfigurationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class ConfigurationServiceImpl extends ServiceImpl<ConfigurationMapper, Configuration> implements IConfigurationService {

    @Override
    public List<Configuration> selectConfigurationList(String lang) {
        return baseMapper.selectConfigurationList(lang);
    }

    @Override
    public List<Configuration> selectBySeries(int series, String lang){
        return baseMapper.selectBySeries(series, lang);
    }

    @Override
    public List<AppConfiguration> findAppList() {
        return baseMapper.findAppList();
    }

    @Override
    public Configuration selectByIdAndLang(int id, String lang) {
        return baseMapper.selectByIdAndLang(id, lang);
    }
}

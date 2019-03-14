package com.jtech.toa.service;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.basic.Configuration;
import com.jtech.toa.model.dto.products.AppConfiguration;

import java.util.List;

/**
 * <p>产品配置信息业务逻辑层接口</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
public interface IConfigurationService extends IService<Configuration> {
    /**
     * 产品配置信息列表查询
     * @return 产品信息列表
     */
    List<Configuration> selectConfigurationList(String lang);

    /**
     * 通过系列找到系列下的所有产品配置信息列表
     * @param series
     * @param lang 语言
     * @return
     */
    List<Configuration> selectBySeries(int series, String lang);

    List<AppConfiguration> findAppList();

    /**
     * 通过产品主键查询一条数据
     * @param id 主键
     * @param lang 语言
     * @return 一条配置信息
     */
    Configuration selectByIdAndLang(int id, String lang);
}

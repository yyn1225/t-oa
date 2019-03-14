package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.basic.Configuration;
import com.jtech.toa.model.dto.products.AppConfiguration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ConfigurationMapper extends BaseMapper<Configuration> {

    /**
     * 产品配置信息列表查询
     * @return 产品信息列表
     */
    List<Configuration> selectConfigurationList(@Param("lang") String lang);

    /**
     * 通过产品的某个系列，获取对应的系列下产品的所有配置信息
     * @param series
     * @param lang 语言
     * @return
     */
    List<Configuration> selectBySeries(@Param("series") int series,@Param("lang") String lang);

    List<AppConfiguration> findAppList();

    /**
     * 通过产品主键查询一条数据
     * @param id 主键
     * @param lang 语言
     * @return 一条配置信息
     */
    Configuration selectByIdAndLang(@Param("id")int id, @Param("lang") String lang);
}

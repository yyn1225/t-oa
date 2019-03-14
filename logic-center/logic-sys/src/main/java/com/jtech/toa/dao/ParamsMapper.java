package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.product.Params;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ParamsMapper extends BaseMapper<Params> {

    /**
     * 产品阐述信息查询
     * @param product 产品id
     * @return 产品参数信息
     */
    Params selectParamsByProductId(@Param("product") int product);

    List<Params> selectParamsList();

    /**
     * 根据产品id和语言查询一条记录
     * @param id 产品id
     * @param lang 语言
     * @return 参数
     */
    Params selectByIdAndLang(@Param("id")Integer id, @Param("lang") String lang);

    List<Params> selectTop50List(int lastId);

    List<Params> findAllWithoutParamsLang();

    /**
     * 取出没有翻译数据的参数控制
     * @return
     */
    List<Params> findAllWithoutParamsControlLang();

    /**
     * 取出没有翻译数据的参数fixModual
     * @return
     */
    List<Params> findAllWithoutParamsFixModualLang();

    /**
     * 取出没有翻译数据的参数fixPsu
     * @return
     */
    List<Params> findAllWithoutParamsFixPsuLang();
}

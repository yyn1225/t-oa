/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.product;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.ProductParamsLang;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface IParamsService extends IService<Params> {

    /**
     * 产品阐述信息查询
     * @param product 产品id
     * @return 产品参数信息
     */
    Params selectParamsByProductId(int product);

    List<Params> selectParamsList();

    boolean updateAndLang(Params params, List<ProductParamsLang> updateList, List<ProductParamsLang> newList);

    /**
     * 根据产品id和语言查询一条记录
     * @param id 产品id
     * @param lang 语言
     * @return 参数
     */
    Params selectByIdAndLang(Integer id, String lang);

    List<Params> findTop50List(int lastId);

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

/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.service.product;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.product.Standard;
import com.jtech.toa.model.dto.imports.StandardImportDto;
import com.jtech.toa.model.dto.products.AppSpareProduct;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.StandardQuery;

import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
public interface IStandardService extends IService<Standard> {
    /**
     * 添加备件产品关系
     * @return */

    boolean addStandard(List<Integer> trs, int pid, int tid, List<StandardDto> standardDtos);


    /**
     * 删除备件产品关系
     * @return
     */
    boolean deleteStandard(Integer standardId);

    /**
     * 根据分页信息获取箱体数据
     * @param query 分页及查询参数
     */
    void selectStandardListByPage(Page<StandardDto> requestPage, StandardQuery query);


    /**
     * 批量添加产品备件关联
     * @param spareIds 备件的id字符串
     * @param productId 产品的id
     * @param type 类型
     * @return 是否成功
     */
    boolean addStandard(String spareIds, Integer productId, Integer type);

    /**
     * 保存年数量设置
     * @param standardId 备件关联表的id
     * @param auto 是否自动
     * @param yearsJsonStr 年设置的JSON字符串
     * @return 是否成功
     */
    boolean saveByStandard(Integer standardId, String yearsJsonStr, Integer auto);

    /**
     * 导入系列备件维护
     * @param list 导入的集合对象
     * @param type 类型 1-标配 2-选配 3-免费
     * @return map集合
     */
    Map<Integer, String> importStandard(List<StandardImportDto> list, Integer type);

    List<Standard> findTop100List(int lastId);

    List<AppSpareProduct> findAllStandardList(String lang, int lastId);
}

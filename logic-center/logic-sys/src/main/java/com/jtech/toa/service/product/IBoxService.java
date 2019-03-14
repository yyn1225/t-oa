/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.product;

import com.google.common.base.Optional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.ProductBoxLang;
import com.jtech.toa.model.dto.products.AppBoxs;
import com.jtech.toa.model.dto.products.BoxDto;
import com.jtech.toa.model.dto.products.BoxImportDto;
import com.jtech.toa.model.query.BoxQuery;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
public interface IBoxService extends IService<Box> {
    /**
     * 添加箱体信息
     * @param boxDto
     * @return */

    boolean addBox( BoxDto boxDto, List<ProductBoxLang> boxLangs);

    /**
     * 更新箱体信息
     * @param boxDto
     * @return
     */
    boolean updateBox( BoxDto boxDto, List<ProductBoxLang> updateList, List<ProductBoxLang> newList);

    /**
     * 根据分页信息获取箱体数据
     * @param query 分页及查询参数
     */
    void selectBoxListByPage(Page<BoxDto> requestPage, BoxQuery query);

    /**
     * 根据分页信息获取箱体数据
     * @return 箱体数据
     */
    List<BoxDto> selectBoxList();

    /**
     * 根据料号获取箱体信息
     *
     * @param scnNo 料号 
     * @return 箱体信息
     */
    Optional<Box> findByScnNo(String scnNo);
//
//    /**
//     * 根据料号和id获取箱体信息
//     *用来判断除自己之外是否重复
//     * @param scnNo  id 料号 ID
//     * @return 箱体信息
//     */
//    Box findByScnNoAndIdNotEq(String scnNo,int id);

    /**
     * 根据id查找模组信息
     * @param id
     * @return
     */
    Box findBoxById(int id);

    /**
     * 根据excel 获取的箱体DTO 转换成箱体数据导入
     * @param boxDtoList 箱体数据传输对象集合
     * @return map集合
     */
    Map<Integer, String> importBox(List<BoxImportDto> boxDtoList);

    /**
     * 通过id以及语言查询一条记录
     * @param id 主键
     * @param lang 语言
     * @return 箱体
     */
    Box selectByIdAndLang(Integer id, String lang);

    List<AppBoxs> findAppBoxs(int lastId);

    List<Box> findAllWithoutBoxLang();

    List<Box> selectByProductsAndLang(List<Product> productList, String lang);
}

/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.product;

import com.google.common.base.Optional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

import com.jtech.toa.entity.product.ProductSpareLang;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.model.dto.imports.SpareImportDto;
import com.jtech.toa.model.dto.prices.AppSparePrice;
import com.jtech.toa.model.dto.products.AppSpares;
import com.jtech.toa.model.dto.products.ProductSpareDto;
import com.jtech.toa.model.dto.products.SpareDto;
import com.jtech.toa.model.query.SpareQuery;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @since 2017-10-13
 */
public interface ISpareService extends IService<Spare> {
    /**
     * 通过产品id获取对应的产品
     * @param product
     * @param standard
     * @return
     */
    List<ProductSpareDto> findSparesByProduct(int product,int series, int type , int standard,int area, String lang);

    /**
     * 通过产品id查询配件详情
     * @param product 产品id集合
     * @param series 系列
     * @param type 配件类型
     * @param standard 是否自动带出
     * @param area 区域
     * @param lang 语言
     * @return 产品配件详情列表
     */
    List<ProductSpareDto> findSparesByProductWithClassify(int product,int series, int type , int standard,int area, String lang);

    /**
     * 通过产品id集合以及配件类型查询配件详情
     * @param productId 产品id
     * @param series 系列
     * @param type 配件类型
     * @param standard 是否自动带出
     * @param area 区域
     * @param lang 语言
     * @return 产品配件详情列表
     */
    List<ProductSpareDto> findSparesByProductIdsWithClassify(Integer productId, int series, int type, int standard, int area, String lang);

    /**
     * 查询所有通用配件及价格
     * @param area 区域
     * @return 产品配件详情列表
     */
    List<ProductSpareDto> selectCommonSpares(Integer area);



    /**
     * 根据分页信息获取备件数据
     * @param query 分页及查询参数
     */
    void selectSpareListByPage(Page<SpareDto> requestPage, SpareQuery query);

    /**
     * 添加备件
     *
     * @param spareDto 备件数据传输对象
     * @param spareLangs 备件语言集合
     * @return 布尔值
     */

    boolean addSpare(SpareDto spareDto, List<ProductSpareLang> spareLangs);

    /**
     * 更新备件
     *
     * @param spareDto 备件数据传输对象
     * @param updateList 更新的备件语言集合
     * @param newList 新增的备件语言集合
     * @return 布尔值
     */

    boolean updateSpare(SpareDto spareDto, List<ProductSpareLang> updateList, List<ProductSpareLang> newList);

    List<Spare> selectSpareList();

    /**
     * 根据物料号获取备件信息
     *
     * @param material 物料号
     * @return 用户信息
     */
    Optional<Spare> selectSpareByMaterial(String material);

    /**
     * 根据物料号查询备件的id
     * @param spareNo 物料号
     * @return 备件的id
     */
    Integer findIdByNo(String spareNo);

    /**
     * 通过主键和语言查询一条记录
     * @param id 主键
     * @param  lang 语言
     * @return 备件
     */
    Spare selectByIdAndLang(Integer id, String lang);

    List<Spare> findSpareWithoutLang();

    List<AppSpares> findAppSpares(int lastId);

    List<AppSpares> findAllAppSpares(String lang, int lastId);

    List<AppSparePrice> findAppSparesPrice(int lastId, String lang, int area);

    /**
     *
     * 找出不在语音表的备件记录
     * @return
     */
    List<Spare> findAllWithoutSpareLang();

    /**
     * 获取配件、选配、免费
     */
    Map<String, Object> offerSpares(String lang,
                                    int product,
                                    int series,
                                    String moneyType,
                                    int area);

    /**
     * 通用配件导入方法
     * @param list 配件导入数据集合
     * @return 错误提示的map集合
     */
    Map<Integer, String> importSpare(List<SpareImportDto> list);

    /**
     *
     * 找出不在语音表的备件品牌记录
     * @return
     */
    List<Spare> findAllWithoutSpareBrandLang();
    /**
     *
     * 找出不在语音表的备件型号记录
     * @return
     */
    List<Spare> findAllWithoutSpareModelLang();
    /**
     *
     * 找出不在语音表的备件类型记录
     * @return
     */
    List<Spare> findAllWithoutSpareTypeLang();
}

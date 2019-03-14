/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.product;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.google.common.base.Optional;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.ProductLang;
import com.jtech.toa.entity.product.ProductParamsLang;
import com.jtech.toa.model.dto.products.AppProduct;
import com.jtech.toa.model.dto.products.AppProductDto;
import com.jtech.toa.model.dto.products.BoxParamsDto;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.model.dto.products.ProductImportDto;
import com.jtech.toa.model.dto.products.SkimProductDto;
import com.jtech.toa.model.query.ProductQuery;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface IProductService extends IService<Product> {

    /**
     * 根据分页信息获取产品数据
     * @param query 分页及查询参数
     * @param area 地区
     */
    void selectProductListByPage(Page<ProductDto> requestPage, ProductQuery query, int area);
    /**
     * 查询商品一览表
     * @param requestPage
     * @param query
     */
    void selectSkimProduct(Page<SkimProductDto> requestPage, ProductQuery query);
    /**
     * 获取产品数据
     * @return 产品数据
     */
    List<Product> selectProductList();

    /**
     * 添加产品
     *
     *@param loginUserId 登录用户id
     *@param productDto 产品数据传输对象
     *@return 布尔值
     */
    boolean addProduct(int loginUserId, ProductDto productDto);

    /**
     * 更新产品
     *
     * @param loginUserId 登录用户id
     * @param productDto 产品数据传输对象
     * @param updateList 更新的产品语言集合
     * @param newList 新增的产品语言集合
     * @return 布尔值
     */
    boolean updateProduct(int loginUserId, ProductDto productDto, List<ProductLang> updateList,
                          List<ProductLang> newList);

    /**
     * 通过产品序列查找产品列表
     * @param series
     * @return
     */
    List<ProductDto> findProductListBySeries(int series,int area);

    /**
     * 通过指定参数查询屏体信息
     * @param params
     *          series 系列号
     *          cert 认证信息
     *          config 配置信息
     *          state 状态信息
     * @return
     */
    List<ProductDto> findProductListByParams(Map<String,Object> params);

    Optional<Product> selectProductByBox(Integer box);

    /**
     * 通过id查找产品信息
     * @param id
     * @return
     */
    Product findProductById(int id);

    /**
     * 产品导入方法
     * @param list  产品导入数据传输对象集合
     * @return map集合
     */
    Map<Integer, String> importProduct(List<ProductImportDto> list);

    /**
     * 根据系列查询产品的ids
     * @param seriesIds 系列号的id集合
     * @return 产品的id集合
     */
    List<Integer> findIdBySeriesIds(List<Integer> seriesIds);

    ProductDto selectProductForOffer(int pid);

    /**
     * 添加产品参数
     *
     * @param loginUserId 登录用户id
     * @param productDto 产品数据传输对象
     * @param params 产品参数
     * @param productLangs 屏体语言集合
     * @param paramsLangs 参数语言集合
     * @return 布尔值
     */
    boolean addProductAndParams(int loginUserId, ProductDto productDto, Params params,
                                List<ProductLang> productLangs, List<ProductParamsLang> paramsLangs);

    /**
     * 根据主键和语言查询一条记录
     * @param id 主键
     * @param lang 语言
     * @return 产品
     */
    Product selectByIdAndLang(Integer id, String lang);

    /**
     * 通过id查询一条记录
     * @param id 主键
     * @return 产品记录
     */
    ProductDto selectSeriesById(int id);

    /**
     * 查询没有语言的列表
     * @return 产品列表
     */
    List<Product> findWithoutLang();

    List<AppProduct> selectTop50List(int lastId);

    List<AppProductDto> selectProductAllList(String lang, int lastId);

    List<Product> findAllWithoutProductLang();

    /**
     * 通过系列查询产品
     * @param series 产品系列
     * @param lang 语言
     * @param area 区域
     * @param panelIdList 已经选择的屏体id列表
     * @return 产品列表
     */
    List<Product> selectProductBySeries(int series, int area, String lang, List<Integer> panelIdList);

    List<Product> selectByPanelsAndLang(List<OfferPanels> panelsParent, String lang);

    /**
     * 通过产品id查询一条记录（箱体，参数）
     * @param panel 屏体id
     * @param lang 语言
     * @return 箱体参数一条记录
     */
    BoxParamsDto findBoxAndParamsById(int panel, String lang);
}

package com.jtech.toa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.model.dto.products.AppProduct;
import com.jtech.toa.model.dto.products.AppProductDto;
import com.jtech.toa.model.dto.products.BoxParamsDto;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.model.dto.products.SkimProductDto;
import com.jtech.toa.model.query.ProductQuery;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 根据分页信息获取产品数据
     * @param query 分页及查询参数
     * @return 产品数据
     */
    List<ProductDto> selectProductListByPage(Page<ProductDto> requestPage, @Param("query") ProductQuery query, @Param("area") int area);
    
    /**
     * 分页获取产品一览表
     * @param requestPage
     * @param query
     * @return
     */
    List<SkimProductDto> selectSkimProduct(Page<SkimProductDto> requestPage, @Param("query") ProductQuery query);

    /**
     * 根据分页信息获取产品数据
     * @return 产品数据
     */
    List<Product> selectProductList();

    /**
     * 通过序列号查找对应的产品信息
     * @param series
     * @return
     */
    List<ProductDto> findProductListBySeries(@Param("series")int series,@Param("area")int area);

    /**
     * 通过多个查询条件，查询对应的物料信息
     * @param series
     * @param area
     * @param cert
     * @param config
     * @param status
     * @param lang
     * @return
     */
    List<ProductDto> findProductListByParams(@Param("series")int series,
                                             @Param("area")int area,
                                             @Param("cert") List<String> cert,
                                             @Param("config")int config,
                                             @Param("state")int status,
                                             @Param("lang")String lang);

    Product selectProductByBox(@Param("box") Integer box);

    /**
     * 通过id主键查找产品信息
     * @param id
     * @return
     */
    Product findProductById(int id);

    /**
     * 根据系列查询产品的ids
     * @param seriesIds 系列号的id集合
     * @return 产品的id集合
     */
    List<Integer> selectIdBySeriesIds(@Param("seriesIds") List<Integer> seriesIds);

    ProductDto selectProductForOffer(@Param("pid")int pid);

    /**
     * 根据主键和语言查询一条记录
     * @param id 主键
     * @param lang 语言
     * @return 产品
     */
    Product selectByIdAndLang(@Param("id") Integer id, @Param("lang") String lang);

    /**
     * 通过id查询一条记录
     * @param id 主键
     * @return 产品记录
     */
    ProductDto selectSeriesById(@Param("id") int id);

    /**
     * 查询没有语言的列表
     * @return 产品列表
     */
    List<Product> findWithoutLang();

    List<AppProduct> selectTop50List(int lastId);


    List<AppProductDto> selectProductAllList(@Param("lang") String lang, @Param("lastId") int lastId);

    List<Product> findAllWithoutProductLang();

    /**
     * 通过系列查询产品
     * @param series 产品系列
     * @param lang 语言
     * @param area 区域
     * @param panelIdList 已选择的屏体id列表
     * @return 产品列表
     */
    List<Product> selectProductBySeries(@Param("series") int series, @Param("area") int area, @Param("lang") String lang, @Param("panelIdList") List<Integer> panelIdList);

    List<Product> selectByPanelsAndLang(List<OfferPanels> panelsParent, String lang);

    /**
     * 通过产品id查询一条记录（箱体，参数）
     * @param panel 屏体id
     * @param lang 语言
     * @return 箱体参数一条记录
     */
    BoxParamsDto findBoxAndParamsById(@Param("id") int panel, @Param("lang") String lang);
}

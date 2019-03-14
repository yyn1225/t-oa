package com.jtech.toa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.product.Spare;
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
public interface SpareMapper extends BaseMapper<Spare> {
    List<Spare> selectByProduct(@Param("product") int product, @Param("series") int series,
                                          @Param("type") int type, @Param("standard") int standard,
                                          @Param("area")int area, @Param("lang") String lang);

    /**
     * 根据分页信息获取备件数据
     * @param query 分页及查询参数
     * @return 模组数据
     */
    List<Spare> selectSpareListByPage(Page<SpareDto> requestPage, @Param("query") SpareQuery query);

    List<Spare> selectSpareList();

    Spare selectSpareByMaterial(@Param("material") String material);

    /**
     * 根据物料号查询备件的id
     * @param spareNo 物料号
     * @return 备件的id
     */
    Integer selectIdByNo(@Param("no") String spareNo);

    /**
     * 通过主键和语言查询一条记录
     * @param id 主键
     * @param  lang 语言
     * @return 备件
     */
    Spare selectByIdAndLang(@Param("id")Integer id, @Param("lang")String lang);

    List<Spare> findSparesWithoutLangs();

    List<ProductSpareDto> selectByProductWithClassify(@Param("product") int product, @Param("series") int series,
                                                      @Param("type") int type, @Param("standard") int standard,
                                                      @Param("area")int area, @Param("lang") String lang);

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
    List<ProductSpareDto> findSparesByProductIdsWithClassify(@Param("productId") Integer productId, @Param("series") int series,
                                                      @Param("type") int type, @Param("standard") int standard,
                                                      @Param("area")int area, @Param("lang") String lang);

    /**
     * 查询所有通用配件及价格
     * @param area 区域
     * @return 产品配件详情列表
     */
    List<ProductSpareDto> selectCommonSpares(@Param("area") Integer area);

    List<AppSpares> findAppSpares(int lastId);

    List<AppSpares> findAllAppSpares(@Param("lang")String lang,@Param("lastId") int lastId);

    List<AppSparePrice> findAppSparesPrice(@Param("lastId") int lastId, @Param("lang") String
            lang,@Param("area") int area);

    List<Spare> findAllWithoutSpareLang();

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

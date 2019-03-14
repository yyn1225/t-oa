package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.jtech.toa.entity.product.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.product.Box;
import com.jtech.toa.model.dto.products.AppBoxs;
import com.jtech.toa.model.dto.products.BoxDto;
import com.jtech.toa.model.query.BoxQuery;

/**
 * <p>
 *     产品信息 箱体 mapper 接口
 * </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
public interface BoxMapper extends BaseMapper<Box> {

    /**
     * 根据分页信息获取用户数据
     * @param query 分页及查询参数
     * @return 用户数据
     */
    List<BoxDto> selectBoxListByPage(Page<BoxDto> requestPage, @Param("query") BoxQuery query);

    /**
     * 根据分页信息获取箱体数据
     * @return 箱体数据
     */
    List<BoxDto> selectBoxList();


    /**
     * 根据箱体料号查询信息
     * @param scnNo 料号
     * @return 箱体信息
     */
    Box selectByScnNo(@Param("scnNo") String scnNo);

    /**
     * 通过id以及语言查询一条记录
     * @param id 主键
     * @param lang 语言
     * @return 箱体
     */
    Box selectByIdAndLang(@Param("id") Integer id, @Param("lang") String lang);

    List<AppBoxs> findAppBoxs(int lastId);

    List<Box> findAllWithoutBoxLang();

    List<Box> selectByProductsAndLang(@Param("productList") List<Product> productList, String lang);
}

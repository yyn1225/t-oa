package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.product.Standard;
import com.jtech.toa.model.dto.products.AppSpareProduct;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.query.StandardQuery;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *     语种 mapper 接口
 * </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
public interface StandardMapper extends BaseMapper<Standard> {
    /**
     * 根据分页信息获取备件产品关系数据
     * @param query 分页及查询参数
     * @return 备件产品关系数据
     */
    List<StandardDto> selectStandardListByPage(Page<StandardDto> requestPage, @Param("query") StandardQuery query);

    /**
     * 删除关系
     */
    int  deleteStandard( @Param("pid")int pid, @Param("sid")int sid,@Param("typeNum")int typeNum);

    List<Standard> findTop100List(int lastId);

    List<AppSpareProduct> findAllStandardList(@Param("lastId") int lastId);
}

package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.model.query.SalesLevelQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface SalesLevelMapper extends BaseMapper<Level> {

    /**
     * 根据分页信息获取产品数据
     * @param requestPage 分页
     * @param query 分页及查询参数
     * @return 产品数据
     */
    List<Level> selectLevelListByPage(Page<Level> requestPage, @Param("query") SalesLevelQuery query);

    /**
     * 根据code查询等级信息，排除当前的id
     * @param code 编码信息
     * @param id 需要排除的id
     * @return 等级对象
     */
    Level findByCodeAndIdNotEq(@Param("code") String code,@Param("id") int id);

    /**
     * 根据code查询等级信息
     * @param code 编码
     * @return 等级对象
     */
    Level findByCode(@Param("code") String code);

    Level selectByIdAndLang(@Param("levelId") int levelId, @Param("lang") String lang);

    Level findByCustomerId(@Param("customer") int customer);
}

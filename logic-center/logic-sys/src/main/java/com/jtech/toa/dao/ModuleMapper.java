package com.jtech.toa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.model.dto.products.AppModules;
import com.jtech.toa.model.dto.products.ModuleDto;
import com.jtech.toa.model.query.ModuleQuery;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @since 2017-10-12
 */
public interface ModuleMapper extends BaseMapper<Module> {

    /**
     * 根据分页信息获取模组数据
     * @param query 分页及查询参数
     * @return 模组数据
     */
    List<Module> selectModuleListByPage(Page<ModuleDto> requestPage, @Param("query") ModuleQuery query);
    List<Module> selectModuleScnNoList();

    Module selectModuleByScnNo(@Param("scnNo") String scnNo);

    /**
     * 通过语言和主键查询一条数据
     * @param id 主键
     * @param lang 语言
     * @return 模组对象
     */
    Module selectByIdAndLang(@Param("id") Integer id, @Param("lang") String lang);

    List<AppModules> selectTop50List(int lastId);

    List<Module> findAllWithoutModuleLang();
}

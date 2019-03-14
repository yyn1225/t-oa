/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.product;

import com.google.common.base.Optional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.ProductModualLang;
import com.jtech.toa.model.dto.products.AppModules;
import com.jtech.toa.model.dto.products.ModuleDto;
import com.jtech.toa.model.dto.products.ModuleImportDto;
import com.jtech.toa.model.query.ModuleQuery;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @since 2017-10-13
 */
public interface IModuleService extends IService<Module> {

    /**
     * 添加模组
     *
     * @param moduleDto 模组数据传输对象
     * @param modualLangs 语言集合
     * @return 布尔值
     */

    boolean addModule(ModuleDto moduleDto, List<ProductModualLang> modualLangs);

    /**
     * 更新模组
     *
     * @param moduleDto 模组数据传输对象
     * @param updateList 更新集合
     * @param newList 新增集合
     * @return 布尔值
     */

    boolean updateModule(ModuleDto moduleDto, List<ProductModualLang> updateList, List<ProductModualLang> newList);


    /**
     * 根据分页信息获取模组数据
     * @param query 分页及查询参数
     */

    void selectModuleListByPage(Page<ModuleDto> requestPage, ModuleQuery query);
    List<Module> selectModuleScoNoList();

    /**
     * 根据物料号获取模组信息
     *
     * @param scnNo 物料号
     * @return 用户信息
     */
    Optional<Module> selectModuleByScnNo(String scnNo);

    int getIdByScnNo(String scnNo);

    /**
     * 通过语言和主键查询一条数据
     * @param id 主键
     * @param lang 语言
     * @return 模组对象
     */
    Module selectByIdAndLang(Integer id, String lang);

    List<AppModules> selectTop50List(int lastId);

    List<Module> findAllWithoutModuleLang();

    /**
     * 模组的数据导入方法
     * @param list 模组导入dto集合
     * @return 错误提示map集合
     */
    Map<Integer, String> importModule(List<ModuleImportDto> list);
}

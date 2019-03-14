/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.product;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.jtech.toa.dao.ModuleMapper;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.ProductModualLang;
import com.jtech.toa.model.dto.products.AppModules;
import com.jtech.toa.model.dto.products.ModuleDto;
import com.jtech.toa.model.dto.products.ModuleExportDto;
import com.jtech.toa.model.dto.products.ModuleImportDto;
import com.jtech.toa.model.query.ModuleQuery;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IProductModualLangService;
import com.jtech.toa.util.ReplaceUtil;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @since 2017-10-13
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {

    private final IProductModualLangService productModualLangService;

    @Autowired
    public ModuleServiceImpl(IProductModualLangService productModualLangService) {
        this.productModualLangService = productModualLangService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleServiceImpl.class);
    /**
     * 添加模组
     *
     * @param moduleDto 模组数据传输对象
     * @return 布尔值
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addModule(ModuleDto moduleDto, List<ProductModualLang> productModualLangs) {
        boolean isOk;
        Module module = moduleDto.toModule();
        module.setCreateTime(new Date());
        isOk = insert(module);
        for (ProductModualLang productModualLang : productModualLangs) {
            productModualLang.setCreateTime(new Date());
            productModualLang.setModual(module.getId());
            productModualLangService.insert(productModualLang);
        }
        return isOk;
    }

    /**
     * 更新模组
     *
     * @param moduleDto 模组数据传输对象
     * @return 布尔值
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateModule(ModuleDto moduleDto, List<ProductModualLang> updateList, List<ProductModualLang> newList) {
        boolean isOk;
        Module module = moduleDto.toModule();
        module.setUpdateTime(new Date());
        isOk = updateById(module);
        if (updateList.size() > 0) {
            for (ProductModualLang productModualLang : updateList) {
                productModualLang.setModual(module.getId());
                isOk = productModualLangService.updateById(productModualLang);
            }
        }
        if (newList.size() > 0) {
            for (ProductModualLang productModualLang : newList) {
                productModualLang.setModual(module.getId());
                isOk = productModualLangService.insert(productModualLang);
            }
        }
        return isOk;
    }

    /**
     * 根据分页信息获取模组数据
     * @param query 分页及查询参数
     */

    @Override
    public void selectModuleListByPage(Page<ModuleDto> requestPage, ModuleQuery query) {
        List<Module> moduleDtos = baseMapper.selectModuleListByPage(requestPage, query);
        final BeanCopier copier = BeanCopier.create(Module.class, ModuleDto.class, false);
        List<ModuleDto> list=new LinkedList<>();
        if(CollectionUtils.isNotEmpty(moduleDtos)) {
        	for(Module x:moduleDtos) {
        		ModuleDto moduleDto=new ModuleDto();
        		copier.copy(x, moduleDto, null);
        		list.add(moduleDto);
        	}
        }
        requestPage.setRecords(list);
    }

    @Override
    public List<Module> selectModuleScoNoList() {
        return baseMapper.selectModuleScnNoList();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Module> selectModuleByScnNo(String scnNo) {
        if (Strings.isNullOrEmpty(scnNo)) {
            return Optional.absent();
        }
        Module module = baseMapper.selectModuleByScnNo(scnNo);
        if (module == null) {
            LOGGER.warn("物料号{} 查询模组不存在！", scnNo);
            return Optional.absent();
        }
        return Optional.of(module);
    }

    @Override
    public int getIdByScnNo(String scnNo) {
         Module module = baseMapper.selectModuleByScnNo(scnNo);
        if(null == module){
            return 0;
        }
        return module.getId();
    }

    @Override
    public Module selectByIdAndLang(Integer id, String lang) {
        return baseMapper.selectByIdAndLang(id, lang);
    }

    @Override
    public List<AppModules> selectTop50List(int lastId) {
        return baseMapper.selectTop50List(lastId);
    }

    @Override
    public List<Module> findAllWithoutModuleLang() {
        return baseMapper.findAllWithoutModuleLang();
    }

    @Override
    public Map<Integer, String> importModule(List<ModuleImportDto> list) {
        Map<Integer, String> map = Maps.newHashMap();
        //定义flag记录成功次数
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            Module module = new Module();
            if (StringUtils.isEmpty(list.get(i).getScnNo())) {
                //考虑excel去除表头，计数从1开始，所以行数以i + 2表示。模组物料号为空提示
                map.put(i + 2, "物料号不能为空");
                continue;
            }
            Optional<Module> moduleOptional = selectModuleByScnNo(ReplaceUtil.replaceStr(list.get(i).getScnNo()));
            if (moduleOptional.isPresent()) {
                //物料号重复提示
                map.put(i + 2, "系统中已存在相同物料号");
                continue;
            }
            module.setScnNo(ReplaceUtil.replaceStr(list.get(i).getScnNo()));

            try {
                module.setWidth(ReplaceUtil.replaceDecimal(list.get(i).getWidth()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "宽度必须为数值");
                continue;
            }

            try {
                module.setHeight(ReplaceUtil.replaceDecimal(list.get(i).getHeight()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "高度必须为数值");
                continue;
            }

            try {
                module.setTransverse(ReplaceUtil.replaceInteger(list.get(i).getTransverse()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "横向分辨率必须为整数");
                continue;
            }

            try {
                module.setPortrait(ReplaceUtil.replaceInteger(list.get(i).getPortrait()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "纵向分辨率必须为整数");
                continue;
            }

            try {
                module.setPitch(ReplaceUtil.replaceDecimal(list.get(i).getPitch()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "点间距必须为数值");
                continue;
            }

            try {
                module.setDensity(ReplaceUtil.replaceInteger(list.get(i).getDensity()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "密度必须为整数");
                continue;
            }

            module.setConfiguration(ReplaceUtil.replaceStr(list.get(i).getConfiguration()));

            try {
                module.setWeight(ReplaceUtil.replaceDecimal(list.get(i).getWeight()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "重量必须为数值");
                continue;
            }
            module.setDescription(ReplaceUtil.replaceStr(list.get(i).getDescription()));
            module.setRemark(ReplaceUtil.replaceStr(list.get(i).getRemark()));
            module.setCreateTime(new Date());
            if (module.getTransverse() != null && module.getPortrait() != null) {
                module.setLamp(module.getTransverse()*module.getPortrait());
            }
            boolean ok = false;
            try {
                ok = module.insert();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!ok) {
                map.put(i + 2, "此行出错，请检查数据是否过长");
                continue;
            }
            flag += 1;
        }
        if (map.size() > 0) {
            map.put(list.size() + 3, String.valueOf(flag));
        }
        return map;
    }
}

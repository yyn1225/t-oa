/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.product;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.jtech.toa.entity.product.Product;
import com.jtech.toa.model.dto.products.BoxImportDto;
import com.jtech.toa.util.ReplaceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.dao.BoxMapper;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.ProductBoxLang;
import com.jtech.toa.model.dto.products.AppBoxs;
import com.jtech.toa.model.dto.products.BoxDto;
import com.jtech.toa.model.query.BoxQuery;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IProductBoxLangService;

/**
 * <p>
 * 箱体信息表 服务实现类
 * </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class BoxServiceImpl extends ServiceImpl<BoxMapper, Box> implements IBoxService {


    private final IModuleService moduleService;
    private final IProductBoxLangService productBoxLangService;

    @Autowired
    public BoxServiceImpl(IModuleService moduleService, IProductBoxLangService productBoxLangService) {
        this.moduleService = moduleService;
        this.productBoxLangService = productBoxLangService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBox(BoxDto boxDto, List<ProductBoxLang> boxLangs) {
        boolean isOk;
        Box box = boxDto.toBox();
        box.setCreateTime(new Date());
        isOk =  insert(box);
        for (ProductBoxLang boxLang : boxLangs) {
            boxLang.setCreateTime(new Date());
            boxLang.setBox(box.getId());
            isOk = productBoxLangService.insert(boxLang);
        }
        return isOk;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBox(BoxDto boxDto, List<ProductBoxLang> updateList, List<ProductBoxLang> newList) {
        boolean isOk;
        Box box = boxDto.toBox();
        box.setUpdateTime(new Date());
        isOk = updateById(box);
        if (updateList.size() > 0) {
            for (ProductBoxLang productBoxLang : updateList) {
                productBoxLang.setBox(box.getId());
                isOk = productBoxLangService.updateById(productBoxLang);
            }
        }
        if (newList.size() > 0) {
            for (ProductBoxLang productBoxLang : newList) {
                productBoxLang.setBox(box.getId());
                isOk = productBoxLangService.insert(productBoxLang);
            }
        }
        return isOk;
    }

    @Override
    public void selectBoxListByPage(Page<BoxDto> requestPage, BoxQuery query) {
        List<BoxDto> boxDtos = baseMapper.selectBoxListByPage(requestPage, query);
        requestPage.setRecords(boxDtos);
    }

    @Override
    public List<BoxDto> selectBoxList() {
        return baseMapper.selectBoxList();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Optional<Box> findByScnNo(String scnNo) {
        if (Strings.isNullOrEmpty(scnNo)) {
            return Optional.absent();
        }
        Box box = baseMapper.selectByScnNo(scnNo);
        if (box == null) {
            LOGGER.warn("箱体料号{} 查询箱体不存在", scnNo);
            return Optional.absent();
        }
        return Optional.of(box);
    }
    /**
     * 根据id查找模组信息
     */
    @Override
    public Box findBoxById(int id) {
        return baseMapper.selectById(id);
    }

    /**
     * 箱体信息excel导入 模组字符串转换成相应id
     * 插入数据库
     *
     * @param boxDtoList 箱体数据传输对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<Integer, String> importBox(List<BoxImportDto> boxDtoList) {
        Map<Integer, String> map = Maps.newHashMap();
        //定义flag记录成功数
        int flag = 0;
        //map 是用来减少请求数据库次数的,已经存在的nodualId直接从map中取
        //没有的话就塞进map中存起来
        for (int i = 0; i < boxDtoList.size(); i++) {
            if (StringUtils.isEmpty(ReplaceUtil.replaceStr(boxDtoList.get(i).getScnNo()))) {
                map.put(i + 2, "箱体料号不能为空");
                continue;
            }
            if (findByScnNo(boxDtoList.get(i).getScnNo()).isPresent()) {
                map.put(i + 2, "系统已存在相同的箱体料号");
                continue;
            }
            if (Strings.isNullOrEmpty(boxDtoList.get(i).getModualNo())) {
                map.put(i + 2, "模组料号不能为空");
                continue;
            }
            String moduleNo = boxDtoList.get(i).getModualNo().replace("，",",");
            String[] moduleNoChar = moduleNo.split(",");
            Integer moduleId = moduleService.getIdByScnNo(moduleNoChar[0]);

            Integer moduleId2 = 0;
            if (moduleNoChar.length > 1) {
             moduleId2 = moduleService.getIdByScnNo(moduleNoChar[1]);
            }
            if (moduleId == 0 && moduleId2 == 0) {
                map.put(i + 2, "无效的模组料号");
                continue;
            }
            Box box = new Box();
            if (moduleId == 0) {
                box.setModual(moduleId2);
            }
            if (moduleId != 0) {
                box.setModual(moduleId);
                box.setModual2(moduleId2);
            }
            box.setScnNo(ReplaceUtil.replaceStr(boxDtoList.get(i).getScnNo()));
            try {
                box.setTransverseCount(ReplaceUtil.replaceInteger(boxDtoList.get(i).getTransverseCount()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "横向模组数量必须为整数");
            }
            try {
                box.setPortraitCount(ReplaceUtil.replaceInteger(boxDtoList.get(i).getPortraitCount()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "纵向模组数量必须为整数");
            }
            try {
                box.setWeight(ReplaceUtil.replaceDecimal(boxDtoList.get(i).getWeight()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "单个重量必须为数值");
            }
            try {
                box.setTransversePix(ReplaceUtil.replaceInteger(boxDtoList.get(i).getTransversePix()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "横向分辨率必须为整数");
            }
            try {
                box.setPortraitPix(ReplaceUtil.replaceInteger(boxDtoList.get(i).getPortraitPix()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "纵向分辨率必须为整数");
            }
            try {
                box.setWidth(ReplaceUtil.replaceDecimal(boxDtoList.get(i).getWidth()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "宽度必须为数值");
            }
            try {
                box.setHeight(ReplaceUtil.replaceDecimal(boxDtoList.get(i).getHeight()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "高度必须为数值");
            }
            try {
                box.setThickness(ReplaceUtil.replaceDecimal(boxDtoList.get(i).getThickness()));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(i + 2, "厚度必须为数值");
            }
            box.setBoxType(ReplaceUtil.replaceStr(boxDtoList.get(i).getBoxType()));
            box.setCreateTime(new Date());
            boolean ok = false;
            try {
                ok = insert(box);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!ok) {
                map.put(i + 2, "系统出错，请检查是否数据过长");
                continue;
            }
            flag += 1;
        }
        if (map.size() > 0) {
            map.put(boxDtoList.size() + 3, String.valueOf(flag));
        }
        return map;
    }

    @Override
    public Box selectByIdAndLang(Integer id, String lang) {
        return baseMapper.selectByIdAndLang(id, lang);
    }

    @Override
    public List<AppBoxs> findAppBoxs(int lastId) {
        return baseMapper.findAppBoxs(lastId);
    }

    @Override
    public List<Box> findAllWithoutBoxLang() {
        return baseMapper.findAllWithoutBoxLang();
    }

    @Override
    public List<Box> selectByProductsAndLang(List<Product> productList, String lang) {
        return baseMapper.selectByProductsAndLang(productList, lang);
    }
}

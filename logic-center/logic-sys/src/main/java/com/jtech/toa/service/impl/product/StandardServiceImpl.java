package com.jtech.toa.service.impl.product;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.StandardMapper;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.entity.product.Standard;
import com.jtech.toa.model.dto.imports.StandardImportDto;
import com.jtech.toa.model.dto.products.AppSpareProduct;
import com.jtech.toa.model.dto.products.StandardDto;
import com.jtech.toa.model.dto.products.YearFormDto;
import com.jtech.toa.model.query.StandardQuery;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.product.IStandardService;
import com.jtech.toa.user.service.impl.UserServiceImpl;

import com.jtech.toa.util.ReplaceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> 备件产品关联表 服务实现类 </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class StandardServiceImpl extends ServiceImpl<StandardMapper, Standard> implements IStandardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final List<String> YES = Lists.newArrayList("Y","YES","是","√","TRUE","T");

    private final IProductService productService;
    private final ISpareService spareService;

    @Autowired
    public StandardServiceImpl(IProductService productService, ISpareService spareService) {
        this.productService = productService;
        this.spareService = spareService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addStandard(List<Integer> trs, int pid, int tid, List<StandardDto> standardDtos) {

        Standard standard = new Standard();
        List<Integer> ids = Lists.newArrayList();
        for (Integer s : trs) {
            standard.setProduct(pid);
            standard.setSpare(s);
            standard.setType(tid);
            boolean ok = baseMapper.insert(standard) == 1;
            if (!ok) {
                LOGGER.warn("新增失败{} 添加新关系失败", standard);
                return false;
            }
            ids.add(standard.getId());
        }
        return true;
    }


    @Override
    public boolean deleteStandard(Integer standardId) {
        boolean isOk;
        isOk = baseMapper.deleteById(standardId) > 0;
        if (!isOk) {
            throw new DaoException("删除备件关系失败！");
        }
        return true;
    }

    @Override
    public void selectStandardListByPage(Page<StandardDto> requestPage, StandardQuery query) {
        List<StandardDto> spares = baseMapper.selectStandardListByPage(requestPage, query);
        requestPage.setRecords(spares);
    }

    @Override
    public boolean addStandard(String spareIds, Integer productId, Integer type) {
        boolean isOk = true;
        if (!Strings.isNullOrEmpty(spareIds)
                && productId > 0
                && type > 0) {
            String[] ids = spareIds.split(StringPool.COMMA);
            Standard standard;
            for (String id : ids) {
                standard = new Standard();
                standard.setProduct(productId);
                standard.setSpare(Ints.tryParse(id));
                standard.setType(type);
                isOk = insert(standard);
                if (!isOk) {
                    throw new DaoException("保存备件关联信息失败");
                }
            }
        }
        return true;
    }

    @Transactional(rollbackFor = DaoException.class)
    @Override
    public boolean saveByStandard(Integer standardId, String yearsJsonStr, Integer auto) {
        Standard standard = selectById(standardId);
        standard.setStandard(auto);
        setYear(yearsJsonStr, standard);
        boolean isOk = updateById(standard);
        if (!isOk) {
            throw new DaoException("更新备件关联信息失败！");
        }
        return true;
    }

    void setYear(String yearsJsonStr, Standard standard) {
        List<YearFormDto> yearFormDtos = JSON.parseArray(yearsJsonStr, YearFormDto.class);
        Map<Integer, YearFormDto> yearFormDtoMap = Maps.newHashMap();
        for (YearFormDto yearFormDto : yearFormDtos) {
            yearFormDtoMap.put(yearFormDto.getYear(), yearFormDto);
        }
        //2年的
        YearFormDto year2 = yearFormDtoMap.get(2);
        if (year2 != null) {
            standard.setCountsTwo(year2.getCounts());
            standard.setCalTypeTwo(year2.getUnitType());
            standard.setSpelTwo(year2.getSpel());
        }
        //3年的
        YearFormDto year3 = yearFormDtoMap.get(3);
        if (year3 != null) {
            standard.setCountsThree(year3.getCounts());
            standard.setCalTypeThree(year3.getUnitType());
            standard.setSpelThree(year3.getSpel());
        }
        //4年的
        YearFormDto year4 = yearFormDtoMap.get(4);
        if (year4 != null) {
            standard.setCountsFour(year4.getCounts());
            standard.setCalTypeFour(year4.getUnitType());
            standard.setSpelFour(year4.getSpel());
        }
        //5年的
        YearFormDto year5 = yearFormDtoMap.get(5);
        if (year2 != null) {
            standard.setCountsFive(year5.getCounts());
            standard.setCalTypeFive(year5.getUnitType());
            standard.setSpelFive(year5.getSpel());
        }
    }

    @Override
    public Map<Integer, String> importStandard(List<StandardImportDto> list, Integer type) {
        Map<Integer, String> map = Maps.newHashMap();
        int flag = 0;
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Integer unitType;
                //{id: 1, text: "每单"}, {id: 2, text: "每平米"}, {id: 3, text: "每个箱体"}, {id: 4,text: "每个模组"}, {id: 5, text: "自定义"}
                String calType = ReplaceUtil.replaceStr(list.get(i).getCalType());
                if (StringUtils.equals(calType, "每单")) {
                    unitType = 1;
                } else if (StringUtils.equals(calType, "每平米")) {
                    unitType = 2;
                } else if (StringUtils.equals(calType, "每个箱体")) {
                    unitType = 3;
                } else if (StringUtils.equals(calType, "每个模组")) {
                    unitType = 4;
                } else if (StringUtils.equals(calType, "表达式")) {
                    unitType = 5;
                }else {
                    unitType = null;
                }
                //找到产品的id
                if (Strings.isNullOrEmpty(ReplaceUtil.replaceStr(list.get(i).getPartNo()))) {
                    LOGGER.info("产品料号为空");
                    map.put(i + 2, "产品料号为空");
                    continue;
                }
                if (StringUtils.isEmpty(ReplaceUtil.replaceStr(list.get(i).getSpareNo()))) {
                    LOGGER.info("备件料号为空");
                    map.put(i + 2, "备件料号为空");
                    continue;
                }
                Product product = productService.selectOne(new EntityWrapper<Product>().eq("part_no", list.get(i).getPartNo()));
                if (product == null) {
                    map.put(i + 2, "无效的产品料号");
                    continue;
                }
                //找到备件的id
                Integer spareId = spareService.findIdByNo(ReplaceUtil.replaceStr(list.get(i).getSpareNo()));
                // 如果没有改备件则新增一条
                if (spareId == null) {
                    LOGGER.info("无效的备件料号");
                    Spare spare = new Spare();
                    spare.setMaterial(ReplaceUtil.replaceStr(list.get(i).getSpareNo()));
                    spare.setType(ReplaceUtil.replaceStr(list.get(i).getType()));
                    spare.setModel(ReplaceUtil.replaceStr(list.get(i).getModel()));
                    spare.setUnit("PC");
                    spare.setDescription(ReplaceUtil.replaceStr(list.get(i).getDescription()));
                    spare.setClassify(Spare.Category.SPARE);
                    spare.setCreateTime(new Date());
                    try {
                        spare.insert();
                    } catch (Exception e) {
                        e.printStackTrace();
                        map.put(i + 2, "此行出错，请检查数据是否过长");
                        continue;
                    }
                    spareId = spare.getId();
                }
                boolean isOk = false;
                Standard standard = selectOne(new EntityWrapper<Standard>().eq("product", product.getId()).eq("spare", spareId).eq("type", type));
                if (standard == null) {
                    standard  = new Standard();
                }
                standard.setType(type);
                standard.setProduct(product.getId());
                standard.setSpare(spareId);
                standard.setStandard((!StringUtils.isEmpty(list.get(i).getSuggest()) && YES.contains(list.get(i).getSuggest().toUpperCase())) ? 1 : 0);
                if (unitType != null) {
                    //2年
                    standard.setCalTypeTwo(unitType);
                    BigDecimal year2Count = list.get(i).getTwoYearsCount();
                    if (year2Count != null) {
                        standard.setCountsTwo(year2Count);
                    }
                    //3年
                    standard.setCalTypeThree(unitType);
                    BigDecimal year3Count = list.get(i).getThreeYearsCount();
                    if (year3Count != null) {
                        standard.setCountsThree(year3Count);
                    }
                    //4年
                    standard.setCalTypeFour(unitType);
                    BigDecimal year4Count = list.get(i).getFourYearsCount();
                    if (year4Count != null) {
                        standard.setCountsFour(year4Count);
                    }
                    //5年
                    standard.setCalTypeFive(unitType);
                    BigDecimal year5Count = list.get(i).getFiveYearsCount();
                    if (year5Count != null) {
                        standard.setCountsFive(year5Count);
                    }
                    if (unitType == 5) {
                        if (!Strings.isNullOrEmpty(list.get(i).getExpression())) {
                            if (list.get(i).getTwoYearsCount() != null) {
                                standard.setSpelTwo(list.get(i).getExpression().substring(0, list.get(i).getExpression().length() - 1) + "*" + list.get(i).getTwoYearsCount() + ")");
                            }
                            if (list.get(i).getThreeYearsCount() != null) {
                                standard.setSpelThree(list.get(i).getExpression().substring(0, list.get(i).getExpression().length() - 1) + "*" + list.get(i).getThreeYearsCount() + ")");
                            }
                            if (list.get(i).getFourYearsCount() != null) {
                                standard.setSpelFour(list.get(i).getExpression().substring(0, list.get(i).getExpression().length() - 1) + "*" + list.get(i).getFourYearsCount() + ")");
                            }
                            if (list.get(i).getFiveYearsCount() != null) {
                                standard.setSpelFive(list.get(i).getExpression().substring(0, list.get(i).getExpression().length() - 1) + "*" + list.get(i).getFiveYearsCount() + ")");
                            }
                        }
                    }
                }
                try {
                    isOk = standard.insertOrUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!isOk) {
                    map.put(i + 2, "此行出错，请检查数据是否过长");
                    continue;
                }
                flag +=1;
            }
            if (map.size() > 0) {
                map.put(list.size() + 3, String.valueOf(flag));
            }
        }
        return map;
    }

    @Override
    public List<Standard> findTop100List(int lastId) {
        return baseMapper.findTop100List(lastId);
    }
    @Override
    public List<AppSpareProduct> findAllStandardList(String lang, int lastId) {
        return baseMapper.findAllStandardList(lastId);
    }
}

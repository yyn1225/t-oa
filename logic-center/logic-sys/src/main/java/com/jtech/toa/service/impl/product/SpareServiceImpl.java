package com.jtech.toa.service.impl.product;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.SpareMapper;
import com.jtech.toa.entity.prices.PricesDetails;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.ProductSpareLang;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.model.dto.imports.SpareImportDto;
import com.jtech.toa.model.dto.prices.AppSparePrice;
import com.jtech.toa.model.dto.products.AppSpares;
import com.jtech.toa.model.dto.products.ModuleDto;
import com.jtech.toa.model.dto.products.ProductSpareDto;
import com.jtech.toa.model.dto.products.SpareDto;
import com.jtech.toa.model.query.SpareQuery;
import com.jtech.toa.service.prices.IPricesDetailsService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.IProductSpareLangService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.util.ReplaceUtil;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @since 2017-10-13
 */
@Service
public class SpareServiceImpl extends ServiceImpl<SpareMapper, Spare> implements ISpareService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleServiceImpl.class);

    private final IProductSpareLangService productSpareLangService;
    private final IPricesDetailsService pricesDetailsService;
    private final ISysExchangeService iSysExchangeService;
    private final IBoxService boxService;
    private final IModuleService moduleService;
    private final IProductService productService;


    @Autowired
    public SpareServiceImpl(IProductSpareLangService productSpareLangService,
                            IPricesDetailsService pricesDetailsService,
                            ISysExchangeService iSysExchangeService,
                            IBoxService boxService,
                            IModuleService moduleService,
                            IProductService productService) {
        this.productSpareLangService = productSpareLangService;
        this.iSysExchangeService = iSysExchangeService;
        this.boxService = boxService;
        this.pricesDetailsService = pricesDetailsService;
        this.moduleService = moduleService;
        this.productService = productService;
    }

    @Override
    public List<ProductSpareDto> findSparesByProduct(int product, int series, int type, int standard,int area,String lang) {
        if (0 == product) {
            return Lists.newArrayList();
        }
        List<Spare> list=baseMapper.selectByProduct(product, series, type, standard, area, lang);
        
        final BeanCopier copier = BeanCopier.create(Spare.class, ProductSpareDto.class, false);
        List<ProductSpareDto> data=new LinkedList<>();
        if(CollectionUtils.isNotEmpty(list)) {
        	for(Spare x:list) {
        		ProductSpareDto productSpareDto=new ProductSpareDto();
        		copier.copy(x, productSpareDto, null);
        		data.add(productSpareDto);
        	}
        }
        return data;
    }

    @Override
    public List<ProductSpareDto> findSparesByProductWithClassify(int product, int series, int type, int standard,int area,String lang) {
        if (0 == product) {
            return Lists.newArrayList();
        }
        return baseMapper.selectByProductWithClassify(product, series, type, standard, area, lang);
    }

    @Override
    public List<ProductSpareDto> findSparesByProductIdsWithClassify(Integer productId, int series, int type, int standard, int area, String lang) {
        return baseMapper.findSparesByProductIdsWithClassify(productId, series, type, standard, area, lang);
    }

    @Override
    public List<ProductSpareDto> selectCommonSpares(Integer area) {
        return baseMapper.selectCommonSpares(area);
    }

    /**
     * 根据分页信息获取备件数据
     * @param query 分页及查询参数
     */
    @Override
    public void selectSpareListByPage(Page<SpareDto> requestPage, SpareQuery query) {
        List<Spare> spareDtos = baseMapper.selectSpareListByPage(requestPage, query);
        
        final BeanCopier copier = BeanCopier.create(Spare.class, SpareDto.class, false);
        List<SpareDto> list=new LinkedList<>();
        if(CollectionUtils.isNotEmpty(spareDtos)) {
        	for(Spare x:spareDtos) {
        		SpareDto spareDto=new SpareDto();
        		copier.copy(x, spareDto, null);
        		list.add(spareDto);
        	}
        }
        requestPage.setRecords(list);
    }

    /**
     * 添加备件
     *
     * @param spareDto 备件数据传输对象
     * @return 布尔值
     */

    @Override
    @Transactional(readOnly = true, rollbackFor = DaoException.class)
    public boolean addSpare(SpareDto spareDto, List<ProductSpareLang> spareLangs) {
        boolean ok;
        Spare spare = spareDto.toSpare();
        spare.setCreateTime(new Date());
        ok =  baseMapper.insert(spare) == 1;
//        ok = sparePriceService.savePrice(spareDto, spare.getId());
        for (ProductSpareLang spareLang : spareLangs) {
            spareLang.setSpare(spare.getId());
            spareLang.setCreateTime(new Date());
            ok = productSpareLangService.insert(spareLang);
        }
        return ok;
    }

    /**
     * 更新备件
     *
     * @param spareDto 备件数据传输对象
     * @return 布尔值
     */

    @Override
    @Transactional(readOnly = true, rollbackFor = DaoException.class)
    public boolean updateSpare(SpareDto spareDto, List<ProductSpareLang> updateList, List<ProductSpareLang> newList) {
        boolean ok;
        Spare spare = spareDto.toSpare();
        spare.setUpdateTime(new Date());
        ok = updateById(spare);
//        ok = sparePriceService.savePrice(spareDto, spare.getId());
        if (updateList.size() > 0) {
            for (ProductSpareLang productSpareLang : updateList) {
                productSpareLang.setSpare(spare.getId());
                productSpareLangService.updateById(productSpareLang);
            }
        }
        if (newList.size() > 0) {
            for (ProductSpareLang productSpareLang : newList) {
                productSpareLang.setSpare(spare.getId());
                productSpareLangService.insert(productSpareLang);
            }
        }
        return ok;
    }

    @Override
    public List<Spare> selectSpareList() {
        return baseMapper.selectSpareList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Spare> selectSpareByMaterial(String material) {

        if (Strings.isNullOrEmpty(material)) {
            return Optional.absent();
        }
        Spare spare = baseMapper.selectSpareByMaterial(material);
        if (spare == null) {
            LOGGER.warn("物料号{} 查询备件不存在！", material);
            return Optional.absent();
        }
        return Optional.of(spare);
    }

    @Override
    public Integer findIdByNo(String spareNo) {
        return baseMapper.selectIdByNo(spareNo);
    }

    @Override
    public Spare selectByIdAndLang(Integer id, String lang) {
        return baseMapper.selectByIdAndLang(id, lang);
    }

    @Override
    public List<Spare> findSpareWithoutLang() {
        return baseMapper.findSparesWithoutLangs();
    }

    @Override
    public List<AppSpares> findAppSpares(int lastId) {
        return baseMapper.findAppSpares(lastId);
    }

    @Override
    public List<AppSpares> findAllAppSpares(String lang,int lastId) {
        return baseMapper.findAllAppSpares(lang, lastId);
    }

    @Override
    public List<AppSparePrice> findAppSparesPrice(int lastId, String lang, int area) {
        return baseMapper.findAppSparesPrice(lastId, lang, area);
    }

    @Override
    public List<Spare> findAllWithoutSpareLang() {
        return baseMapper.findAllWithoutSpareLang();
    }

    @Override
    public Map<String, Object> offerSpares(String lang,
                                           int product,
                                           int series,
                                           String moneyType,
                                           int area){
        Map<String, Object> map = Maps.newHashMap();

        //标配
        List<ProductSpareDto> sparesList = this.findPricesDetailsByType(lang,product,series,1,
                moneyType,area);
        //选配
        List<ProductSpareDto> chooseSparesList = this.findPricesDetailsByType(lang,product,series,2,
                moneyType,area);
        //免费
        List<ProductSpareDto> freeSparesList = this.findPricesDetailsByType(lang,product,series,3,
                moneyType,area);

        map.put("spares", sparesList);
        map.put("choose", chooseSparesList);
        map.put("free", freeSparesList);

        return map;

    }

    /**
     * 根据Type获取配件 数据
     */
    private List<ProductSpareDto> findPricesDetailsByType(String lang,
                                                          int product,
                                                          int series,
                                                          int type,
                                                          String moneyType,
                                                          int area){
        List<ProductSpareDto> list = this.findSparesByProductWithClassify(product, series, type, -1,
                area, lang);//-1代表不对是否默认带出进行过滤
        Module moduleLeft = null;
        Module moduleRight = null;
        PricesDetails pricesDetails = pricesDetailsService.getDetailsByPanelAndArea(product, area);
        Product pd = productService.selectById(product);
        if (null != pd) {
            Box box = boxService.selectById(pd.getBox());
            if (null != box && 0 != box.getModual()) {
                moduleLeft = moduleService.selectById(box.getModual());
            }
            if(null!=box && 0!=box.getModual2()){
                moduleRight = moduleService.selectById(box.getModual2());
            }
        }
        List<SysExchange> exchanges = iSysExchangeService.getLastExchange();
        Map<String,BigDecimal> rateMap = Maps.newHashMap();
        for(SysExchange rate:exchanges){
            rateMap.put(rate.getCode(),rate.getRmb());
        }

        for (ProductSpareDto spare:list) {
            if (null !=pricesDetails && ((null != moduleLeft && spare.getMaterial().equals(moduleLeft.getScnNo())) //左模组的物料号和备件物料号一致
                    || (null != moduleRight && spare.getMaterial().equals(moduleRight.getScnNo())))) { //右模组的物料号和备件物料号一致
                spare.setSalePrice(iSysExchangeService.calcRate(pricesDetails.getModual(),pricesDetails.getUnit(),moneyType,rateMap));
            }else{
                spare.setSalePrice(iSysExchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),moneyType,rateMap));
            }

            spare.setCostPrice(iSysExchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),moneyType,rateMap));
        }
        return list;
    }

    @Override
    public Map<Integer, String> importSpare(List<SpareImportDto> list) {
        Map<Integer, String> map = Maps.newHashMap();
        //定义flag记录成功次数
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.isEmpty(list.get(i).getMaterial())) {
                map.put(i + 2, "物料号不能为空");
                continue;
            }
            Integer spareId = findIdByNo(ReplaceUtil.replaceStr(list.get(i).getMaterial()));
            if (spareId != null) {
                map.put(i + 2, "系统已存在相同物料号");
                continue;
            }
            Spare spare = new Spare();
            spare.setMaterial(ReplaceUtil.replaceStr(list.get(i).getMaterial()));
            spare.setBrand(ReplaceUtil.replaceStr(list.get(i).getBrand()));
            spare.setModel(ReplaceUtil.replaceStr(list.get(i).getModel()));
            spare.setDescription(ReplaceUtil.replaceStr(list.get(i).getDescription()));
            spare.setUnit(ReplaceUtil.replaceStr(list.get(i).getUnit()));
            spare.setRemark(ReplaceUtil.replaceStr(list.get(i).getRemark()));
            spare.setType(ReplaceUtil.replaceStr(list.get(i).getType()));
            spare.setClassify(Spare.Category.PARTS);
            spare.setCreateTime(new Date());
            boolean ok = false;
            try {
                ok = spare.insert();
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

    @Override
    public List<Spare> findAllWithoutSpareBrandLang() {
        return baseMapper.findAllWithoutSpareBrandLang();
    }

    @Override
    public List<Spare> findAllWithoutSpareModelLang() {
        return baseMapper.findAllWithoutSpareModelLang();
    }

    @Override
    public List<Spare> findAllWithoutSpareTypeLang() {
        return baseMapper.findAllWithoutSpareTypeLang();
    }
}

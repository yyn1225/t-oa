/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.product;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.dao.ProductMapper;
import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.entity.basic.Configuration;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.ProductLang;
import com.jtech.toa.entity.product.ProductParamsLang;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.model.dto.products.*;
import com.jtech.toa.model.query.ProductQuery;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductLangService;
import com.jtech.toa.service.product.IProductParamsLangService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;

import com.jtech.toa.util.ReplaceUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleServiceImpl.class);
    private static final List<String> yes = Lists.newArrayList("Y","YES","是","√","TRUE","T");

    private final IPriceService priceService;
    private final ISeriesService seriesService;
    private final IConfigurationService configurationService;
    private final ICertificationService certificationService;
    private final IDictService dictService;
    private final IParamsService paramsService;
    private final IBoxService boxService;
    private final IProductLangService productLangService;
    private final IProductParamsLangService productParamsLangService;

    @Autowired
    public ProductServiceImpl(IPriceService priceService, ISeriesService seriesService,
                              IConfigurationService configurationService, ICertificationService certificationService,
                              IDictService dictService, IParamsService paramsService, IBoxService boxService,
                              IProductLangService productLangService, IProductParamsLangService productParamsLangService) {
        this.priceService = priceService;
        this.seriesService = seriesService;
        this.configurationService = configurationService;
        this.certificationService = certificationService;
        this.dictService = dictService;
        this.paramsService = paramsService;
        this.boxService = boxService;
        this.productLangService = productLangService;
        this.productParamsLangService = productParamsLangService;
    }

    /**
     * 根据分页信息获取产品数据
     *
     * @param query 分页及查询参数
     */
    @Override
    public void selectProductListByPage(Page<ProductDto> requestPage, ProductQuery query, int area) {
        List<ProductDto> productDtos = baseMapper.selectProductListByPage(requestPage, query, area);
        requestPage.setRecords(productDtos);
    }
    /**
     * 分页获取产品一览表
     */
    @Override
    public void selectSkimProduct(Page<SkimProductDto> requestPage, ProductQuery query) {
    	List<SkimProductDto> productDtos = baseMapper.selectSkimProduct(requestPage, query);
    	requestPage.setRecords(productDtos);
    }

    /**
     * 根据分页信息获取产品数据
     *
     * @return 产品数据
     */
    @Override
    public List<Product> selectProductList() {
        return baseMapper.selectProductList();
    }

    @Override
    public boolean addProduct(int loginUserId, ProductDto productDto) {
        Product product = productDto.toProduct();
        product.setCreater(loginUserId);
        product.setCreateTime(new Date());
        return baseMapper.insert(product) == 1;

//        Price price = savePriceDto.toPrice();
//        price.setArea(0);
//        price.setProduct(product.getId());
//        return priceService.insert(price);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean updateProduct(int loginUserId, ProductDto productDto, List<ProductLang> updateList,
                                 List<ProductLang> newList) {
        Product product = productDto.toProduct();
        product.setUpdater(loginUserId);
        product.setUpdateTime(new Date());
        boolean isOk;
        isOk = updateById(product);
//        if (savePriceDto.getPriceId() == null) {
//            Price price = savePriceDto.toPrice();
//            price.setArea(0);
//            price.setProduct(product.getId());
//            isOk =  priceService.insert(price);
//        }else {
//            isOk = priceService.updatePrice(savePriceDto);
//        }
        if (updateList.size() > 0) {
            for (ProductLang productLang : updateList) {
                productLang.setProduct(product.getId());
                isOk = productLangService.updateById(productLang);
            }
        }
        if (newList.size() > 0) {
            for (ProductLang productLang : newList) {
                productLang.setProduct(product.getId());
                isOk = productLangService.insert(productLang);
            }
        }
        return isOk;
    }

    /**
     * 通过产品序列查找产品列表
     */
    @Override
    public List<ProductDto> findProductListBySeries(int series, int area) {
        return baseMapper.findProductListBySeries(series, area);
    }

    /**
     * 通过指定参数查询屏体信息
     *
     * @param params 参数map集合
     */
    @Override
    public List<ProductDto> findProductListByParams(Map<String, Object> params) {
//        Map<String, Object> params = Maps.newHashMap();
//        for (String s : param.keySet()) {
//            if (param.get(s) == null) {
//                params.put(s, StringPool.EMPTY);
//            }else {
//                params.put(s, params.get(s));
//            }
//        }
        String seriesStr = params.get("series").toString();
        String areaStr = params.get("area").toString();
        String configStr = params.get("config").toString();
        String stateStr = params.get("state").toString();
        String lang = params.get("lang").toString();
        int area = StringUtils.isEmpty(areaStr) ? 0 : Integer.valueOf(areaStr);
        int config = StringUtils.isEmpty(configStr) ? 0 : Integer.valueOf(configStr);
        int state = StringUtils.isEmpty(stateStr) ? 0 : Integer.valueOf(stateStr);
        int series = StringUtils.isEmpty(seriesStr) ? 0 : Integer.valueOf(seriesStr);
        List<String> certList = Lists.newArrayList();
        String certs = params.get("cert").toString();
        if (!StringUtils.isEmpty(certs)) {
            certList = Lists.newArrayList(certs.split(","));
            certList.forEach(cert -> { cert = "Basics".equals(cert) ? "" : cert; });
        }
        return baseMapper.findProductListByParams(series, area, certList, config, state, lang);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> selectProductByBox(Integer box) {
        if (box == null || box == 0) {
            return Optional.absent();
        }
        Product product = baseMapper.selectProductByBox(box);
        if (product == null) {
            LOGGER.warn("物料号{} 查询产品不存在！", box);
            return Optional.absent();
        }
        return Optional.of(product);
    }

    /**
     * 通过id查找产品信息
     */
    @Override
    public Product findProductById(int id) {
        return baseMapper.findProductById(id);
    }

    @Override
    public Map<Integer, String> importProduct(List<ProductImportDto> list) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        boolean ok = false;
        int flag = 0;
        Map<Integer, String> messageMap = Maps.newHashMap();
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.isEmpty(ReplaceUtil.replaceStr(list.get(i).getBox()))) {
                messageMap.put(i + 2, "箱体料号不能为空");
                continue;
            }
            Series series;
            if (StringUtils.isEmpty(ReplaceUtil.replaceStr(list.get(i).getSeriesParent())) ||
                    StringUtils.isEmpty(ReplaceUtil.replaceStr(list.get(i).getSeries()))) {
                messageMap.put(i + 2, "系列和产品不能为空");
                continue;
            }else {
                int line;
                if ("户内".equals(ReplaceUtil.replaceStr(list.get(i).getLine()))) {
                    line = 1;
                }else if ("户外".equals(ReplaceUtil.replaceStr(list.get(i).getLine()))) {
                    line = 2;
                }else {
                    line = 3;
                }
                series = seriesService.selectOne(
                        new EntityWrapper<Series>().eq("name", list.get(i).getSeries()));
                Series parent = seriesService.selectOne(
                        new EntityWrapper<Series>().eq("name", list.get(i).getSeriesParent()));
                if (parent == null) {
                    parent = new Series();
                    parent.setStatus(1);
                    parent.setLine(line);
                    parent.setParent(0);
                    parent.setName(ReplaceUtil.replaceStr(list.get(i).getSeriesParent()));
                    parent.setCreateTime(new Date());
                    parent.setCreater((int) shiroUser.getId());
                    parent.insert();
                }
                if (series == null) {
                    series = new Series();
                    series.setStatus(1);
                    series.setLine(line);
                    series.setParent(parent.getId());
                    series.setName(ReplaceUtil.replaceStr(list.get(i).getSeries()));
                    series.setCreateTime(new Date());
                    series.setCreater((int) shiroUser.getId());
                    series.insert();
                }
            }
            Configuration configuration = configurationService.selectOne(
                    new EntityWrapper<Configuration>().eq("remark", ReplaceUtil.replaceStr(list.get(i).getConfiguration())));
            Dict type = dictService.selectOne(new EntityWrapper<Dict>().
                    eq("name", list.get(i).getType()).eq("category", "product_type"));
            Dict status = dictService.selectOne(new EntityWrapper<Dict>().
                    eq("name", list.get(i).getStatus()).eq("category", "product_status"));
            Box box = boxService.selectOne(new EntityWrapper<Box>().eq("scn_no", list.get(i).getBox()));

            Product product = new Product();
            if (box == null) {
                messageMap.put(i + 2, "箱体料号无效");
                continue;
            }
            Product productIsExist = selectOne(new EntityWrapper<Product>().eq("part_no", list.get(i).getBox()));
            if (productIsExist != null) {
                messageMap.put(i + 2, "该箱体料号已被使用");
                continue;
            }
            product.setPartNo(list.get(i).getBox());
            product.setBox(box.getId());
            product.setSeries(series.getId());
            product.setColor(ReplaceUtil.replaceStr(list.get(i).getColor()));
            product.setState(ReplaceUtil.replaceStr(list.get(i).getState()));
            product.setCreater((int) shiroUser.getId());
            product.setCreateTime(new Date());
            if (configuration != null) {
                product.setConfiguration(configuration.getId());
            }else {
                product.setConfiguration(configurationService.selectOne(
                        new EntityWrapper<Configuration>().eq("remark", "标准版")).getId());
            }

            String cert = ReplaceUtil.replaceStr(list.get(i).getCertification());
            if (cert != null) {
                product.setCertification(cert.replace("+",","));
            }

            if (type != null) {
                product.setType(type.getCode());
            }else {
                //类型默认标准
                product.setType("1");
            }

            if (status != null) {
                product.setStatus(status.getCode());
            }else {
                //默认在售
                product.setStatus("1");
            }

            if (!StringUtils.isEmpty(list.get(i).getFeatured()) && yes.contains(list.get(i).getFeatured().toUpperCase())){
                product.setFeatured(1);
            }else{
                product.setFeatured(0);
            }
            Params params = new Params();
            params.setCalibration((!StringUtils.isEmpty(list.get(i).getCalibration()) && yes.contains(list.get(i).getCalibration().toUpperCase())) ? 1 : 0);
            params.setFront((!StringUtils.isEmpty(list.get(i).getFront()) && yes.contains(list.get(i).getFront().toUpperCase())) ? 1 : 0);
            params.setStack((!StringUtils.isEmpty(list.get(i).getStack()) && yes.contains(list.get(i).getStack().toUpperCase())) ? 1 : 0);
            params.setIntelligent((!StringUtils.isEmpty(list.get(i).getIntelligent()) && yes.contains(list.get(i).getIntelligent().toUpperCase())) ? 1 : 0);
            params.setControl(ReplaceUtil.replaceStr(list.get(i).getControl()));
            try {
                params.setRigging(ReplaceUtil.replaceInteger(list.get(i).getRigging()));
            } catch (Exception e) {
                e.printStackTrace();
                messageMap.put(i + 2, "吊装数量必须为整数");
                continue;
            }
            params.setFixModual(ReplaceUtil.replaceStr(list.get(i).getFixModual()));
            params.setFixPsu(ReplaceUtil.replaceStr(list.get(i).getFixPsu()));
            params.setIpRating(ReplaceUtil.replaceStr(list.get(i).getIpRating()));
            params.setBrightness(ReplaceUtil.replaceStr(list.get(i).getBrightness()));
            params.setContrastRatio(ReplaceUtil.replaceStr(list.get(i).getContrastRatio()));
            params.setGrayScale(ReplaceUtil.replaceStr(list.get(i).getGrayScale()));
            params.setRefresh(ReplaceUtil.replaceStr(list.get(i).getRefresh()));
            params.setViewing(ReplaceUtil.replaceStr(list.get(i).getViewing()));
            try {
                params.setPowerMax(ReplaceUtil.replaceInteger(list.get(i).getPowerMax()));
            } catch (Exception e) {
                e.printStackTrace();
                messageMap.put(i + 2, "最大功率必须为整数");
                continue;
            }
            try {
                params.setPowerAvg(ReplaceUtil.replaceInteger(list.get(i).getPowerAvg()));
            } catch (Exception e) {
                e.printStackTrace();
                messageMap.put(i + 2, "平均功率必须为整数");
                continue;
            }
            params.setDrivingIc(ReplaceUtil.replaceStr(list.get(i).getDrivingIc()));
            params.setDrivingType(ReplaceUtil.replaceStr(list.get(i).getDrivingType()));
            params.setPsu(ReplaceUtil.replaceStr(list.get(i).getPsu()));
            try {
                params.setPsuPower(ReplaceUtil.replaceInteger(list.get(i).getPsuPower()));
            } catch (Exception e) {
                e.printStackTrace();
                messageMap.put(i + 2, "电源原功率必须为整数");
                continue;
            }
            try {
                params.setPsuCount(ReplaceUtil.replaceInteger(list.get(i).getPsuCount()));
            } catch (Exception e) {
                e.printStackTrace();
                messageMap.put(i + 2, "电源数量必须为整数");
                continue;
            }
            try {
                params.setStandardCarryLower(ReplaceUtil.replaceInteger(list.get(i).getStandardCarryLower()));
            } catch (Exception e) {
                e.printStackTrace();
                messageMap.put(i + 2, "110V带载方式必须为整数");
                continue;
            }
            try {
                params.setStandardCarryHigh(ReplaceUtil.replaceInteger(list.get(i).getStandardCarryHigh()));
            } catch (Exception e) {
                e.printStackTrace();
                messageMap.put(i + 2, "220V带载方式必须为整数");
                continue;
            }
            params.setCreateTime(new Date());

            try {
                insert(product);
                params.setProduct(product.getId());
                paramsService.insert(params);
            } catch (RuntimeException e) {
                e.printStackTrace();
                deleteById(product.getId());
                messageMap.put(i + 2, "此行出错，请检查数据格式是否有误");
                continue;
            }
            flag += 1;
        }
        if (messageMap.size() > 0) {
            messageMap.put(list.size() + 3, String.valueOf(flag));
        }

        return messageMap;
    }

    @Override
    public List<Integer> findIdBySeriesIds(List<Integer> seriesIds) {
        if (CollectionUtils.isEmpty(seriesIds)) {
            return Collections.EMPTY_LIST;
        }
        return baseMapper.selectIdBySeriesIds(seriesIds);
    }

    @Override
    public ProductDto selectProductForOffer(int pid) {
        return baseMapper.selectProductForOffer(pid);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public boolean addProductAndParams(int loginUserId, ProductDto productDto, Params params,
                                       List<ProductLang> productLangs, List<ProductParamsLang> paramsLangs) {
        boolean ok;
        Product product = productDto.toProduct();
        product.setCreater(loginUserId);
        product.setCreateTime(new Date());
        ok = baseMapper.insert(product) == 1;

//        Price price = savePriceDto.toPrice();
//        price.setArea(0);
//        price.setProduct(product.getId());
//        ok = priceService.insert(price);

        params.setProduct(product.getId());
        params.setCreateTime(new Date());
        ok = paramsService.insert(params);

        for (ProductLang productLang : productLangs) {
            productLang.setCreateTime(new Date());
            productLang.setProduct(product.getId());
            ok = productLangService.insert(productLang);
        }
        for (ProductParamsLang paramsLang : paramsLangs) {
            paramsLang.setCreateTime(new Date());
            paramsLang.setParams(params.getId());
            ok = productParamsLangService.insert(paramsLang);
        }
        return ok;
    }

    @Override
    public Product selectByIdAndLang(Integer id, String lang) {
        return baseMapper.selectByIdAndLang(id ,lang);
    }

    @Override
    public ProductDto selectSeriesById(int id) {
        return baseMapper.selectSeriesById(id);
    }

    @Override
    public List<Product> findWithoutLang() {
        return baseMapper.findWithoutLang();
    }

    @Override
    public List<AppProduct> selectTop50List(int lastId) {
        return baseMapper.selectTop50List(lastId);
    }

    @Override
    public List<AppProductDto> selectProductAllList(String lang, int lastId) {
        return baseMapper.selectProductAllList(lang, lastId);
    }
    @Override
    public List<Product> findAllWithoutProductLang() {
        return baseMapper.findAllWithoutProductLang();
    }

    @Override
    public List<Product> selectProductBySeries(int series, int area, String lang, List<Integer> panelIdList) {
        return baseMapper.selectProductBySeries(series, area, lang, panelIdList);
    }

    @Override
    public List<Product> selectByPanelsAndLang(List<OfferPanels> panelsParent, String lang) {
        return baseMapper.selectByPanelsAndLang(panelsParent ,lang);
    }

    @Override
    public BoxParamsDto findBoxAndParamsById(int panel, String lang) {
        return baseMapper.findBoxAndParamsById(panel, lang);
    }
}

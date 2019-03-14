/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.offer;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xiaoleilu.hutool.util.CollectionUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.entity.basic.Configuration;
import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.entity.offer.OfferPreference;
import com.jtech.toa.entity.prices.PriceSystem;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.SeriesImages;
import com.jtech.toa.entity.system.RoleUser;
import com.jtech.toa.model.dto.offer.OfferListDto;
import com.jtech.toa.model.dto.products.BoxParamsDto;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.model.query.OfferQuery;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.offer.IOfferPreferenceService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.prices.IPriceSystemService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesImagesService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.system.IAttachmentService;
import com.jtech.toa.service.system.IRoleUserService;
import com.jtech.toa.sync.syncinterface.Crm2Interface;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.DepartmentUser;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IDepartmentUserService;
import com.jtech.toa.user.service.IUserService;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@RestController
public class RestOfferController {
    private final ISeriesImagesService imagesService;
    private final ISpareService spareService;
    private final IProductService productService;
    private final IConfigurationService configurationService;
    private final ICertificationService certificationService;
    private final IUserService userService;
    private final IOfferService offerService;
    private final IAttachmentService attachmentService;
    private final IPriceSystemService priceSystemService;
    private final ISeriesService seriesService;
    private final IOfferPreferenceService offerPreferenceService;
    private final IDepartmentService departmentService;
    private final IDictService dictService;
    private final IRoleUserService roleUserService;
    private final IDepartmentUserService departmentUserService;
    private final IBoxService boxService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RestOfferController.class);

    @Value("${oa.connect-oa-crm-interface}")
    private boolean connectOaCrmInterface;

    public RestOfferController(ISeriesImagesService imagesService,
                               ISpareService spareService,
                               IProductService productService,
                               IConfigurationService configurationService,
                               ICertificationService certificationService,
                               IUserService userService,
                               IOfferService offerService,
                               IAttachmentService attachmentService,
                               IPriceSystemService priceSystemService,
                               ISeriesService seriesService,
                               IOfferPreferenceService offerPreferenceService,
                               IDepartmentService departmentService,
                               IDictService dictService, IRoleUserService roleUserService, 
                               IDepartmentUserService departmentUserService,
                               IBoxService boxService) {
        this.imagesService = imagesService;
        this.spareService = spareService;
        this.productService = productService;
        this.configurationService = configurationService;
        this.certificationService = certificationService;
        this.userService = userService;
        this.offerService = offerService;
        this.attachmentService = attachmentService;
        this.priceSystemService = priceSystemService;
        this.seriesService = seriesService;
        this.offerPreferenceService = offerPreferenceService;
        this.departmentService = departmentService;
        this.dictService = dictService;
        this.roleUserService = roleUserService;
        this.departmentUserService = departmentUserService;
        this.boxService = boxService;
    }

    /**
     * 通过系列id和区域获取产品物料号
     * @param id 系列
     * @param area 区域
     * @param user 当前用户
     * @return 产品数据
     */
    @GetMapping("/offer/product/get")
    public ResponseEntity getProductData(
            @RequestParam(value = "id") Integer id, @RequestParam(value = "area") Integer area, @RequestUser RequestSubject user) {
        if (id != null && id > 0) {
            List<Product> productList = productService.selectProductBySeries(id, area, user.getLanguage(), null);
            if(CollectionUtils.isNotEmpty(productList)) {
            	Integer box=productList.get(0).getBox();
            	Box tmp = boxService.selectByIdAndLang(box, user.getLanguage());
            	if(tmp!=null) {
            		productList.get(0).setTransverseCount(tmp.getTransverseCount());
            		productList.get(0).setPortraitCount(tmp.getPortraitCount());
            	}
            }
            return ResponseEntity.ok(productList);
        }
        return null;
    }

    /**
     * 通过产品线获取时间数据
     * @param line 产品线
     * @return 产品数据
     */
    @GetMapping("/offer/line/get")
    public ResponseEntity getLineData(@RequestParam(value = "line") Integer line, Integer area) {
        if (line != null && line > 0) {
            List<SeriesDto> seriesList = seriesService.findSeriesList(line, area);
            return ResponseEntity.ok(seriesList);
        }
        return null;
    }

    /**
     * 通过产品线获取所有产品数据
     * @param line 产品线
     * @return 产品数据
     */
    @GetMapping("/offer/line/getall")
    public ResponseEntity getLineAllData(@RequestParam(value = "line") Integer line ) {
        if (line != null && line > 0) {
            List<SeriesDto> seriesList = seriesService.findSeriesList(line);
            return ResponseEntity.ok(seriesList);
        }
        return null;
    }

    /**
     * 获取拼屏数据
     * @param id 系列
     * @param area 区域
     * @param user 当前用户
     * @return 产品数据
     */
    @GetMapping("/offer/split/get")
    public ResponseEntity getSplitData(@RequestParam(value = "id") Integer id,
                                       @RequestParam(value = "area") Integer area,
                                       @RequestUser RequestSubject user,
                                       @RequestParam(value = "panelIds")String panelIds) {
        if (id != null && id > 0) {
            List<Integer> panelIdList = Lists.newArrayList();
            if (!StringUtils.isEmpty(panelIds)) {
                String[] panelStr = panelIds.split(",");
                for (String s : panelStr) {
                    panelIdList.add(Integer.parseInt(s));
                }
            }
            List<Product> productList = productService.selectProductBySeries(id, area, user.getLanguage(), panelIdList);

            return ResponseEntity.ok(productList);
        }
        return null;
    }

    /**
     * 根据产品的序列，获取产品的配置信息、认证信息、图片、产品列表、要求信息等
     * @param series
     * @return
     */
    @GetMapping("/offer/series/products")
    public ResponseEntity getDataConfigs(
            @RequestParam(name = "series",required = true) int series,
            @RequestUser RequestSubject user
    ){
        Map<String,Object> map = Maps.newHashMap();
        Optional<User> userInfo = userService.findByUserId(user.getId());
        if(!userInfo.isPresent()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        List<SeriesImages> images = imagesService.findBySeriesId(series);
        for (SeriesImages image : images) {
            if(!StringUtils.isEmpty(image.getUrlThum())){
                image.setUrlThum(attachmentService.medialUrl(image.getUrlThum()));
            }
        }
        List<ProductDto> productList = productService.findProductListBySeries(series, userInfo.get().getArea());
        List<Configuration> configurations = configurationService.selectBySeries(series, user.getLanguage());
        List<Certification> certifications = certificationService.selectCertificationListBySeries(series);
        Series productSeries = seriesService.selectById(series);
        Map<String,String> status = Maps.newHashMap();
        for(ProductDto product:productList){
            if(!StringUtils.isEmpty(product.getStatus())){
                status.put(product.getStatus(),null);
            }
        }
        map.put("cert",certifications);
        map.put("config",configurations);
        map.put("special",status.keySet());
        map.put("images",images);
        map.put("products",productList);
        map.put("productSeries",productSeries);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/offer/area/unit")
    public ResponseEntity getAreaMoneyType(int area){
        PriceSystem system = priceSystemService.getSystemByArea(area, DateTime.now().toDate());
        if(null==system){
            return ResponseEntity.ok("");
        }
        return ResponseEntity.ok(system.getUnit());
    }

    /**
     * 报价单的保存功能实现
     * @param user 当前登录用户信息
     * @offer 报价单详情
     * @return
     */
    @PostMapping("/offer/submit")
    public ResponseEntity submitOffer(@RequestUser RequestSubject user,String offer,boolean draftFlag){
        try{
            OfferVo vo = JSON.parseObject(offer,OfferVo.class);
            Offer saveOffer=offerService.save(user.getId(), offer, vo, draftFlag);
            if( saveOffer != null && saveOffer.getId() >0){
                Map<String,Object> map = Maps.newHashMap();
                map.put("id",saveOffer.getId());
                map.put("status",vo.getOffer().getStatus());
                //此处另起线程，调用zoho crm接口，同步新增报价数据
                if(connectOaCrmInterface){
                    //草稿箱的报价单不提交crm
                    if(!draftFlag) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Crm2Interface.createOffer(saveOffer, vo, dictService,
                                        userService, user,productService,seriesService);
                            }
                        });
                        t.start();
                    }
                }
                return ResponseEntity.ok(map);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR).createErrorModel());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.DATA_IN_WRONG_FORMAT).createErrorModel());
        }

        return ResponseEntity.ok(-1);
    }

    @GetMapping("/offer/delete")
    public ResponseEntity delete(long id) {
        Offer offer = offerService.selectById(id);
        boolean ok = true;
        if (Offer.DeleteFlag.No.equals(offer.getDeleteFlag())) {
            offer.setDeleteFlag(Offer.DeleteFlag.Yes);
            ok = offerService.updateById(offer);
            if(ok){
                //此处另起线程，调用zoho crm接口，同步删除crm报价数据
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Crm2Interface.deleteOffer(offer.getNum());
                    }
                });
                t.start();
            }
        }
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/preference/delete")
    public ResponseEntity deletePreference(long id) {
        boolean ok = offerPreferenceService.deleteById(id);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/offer/rest/list")
    public DataTablesOutput<OfferListDto> userList(
            @Valid @RequestBody DataTablesInput dataTablesInput, @RequestUser RequestSubject user
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<OfferListDto> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );
        OfferQuery query = dataTablesInput
                .getParams()
                .toJavaObject(OfferQuery.class);
        query.setUserId(user.getId());
        if (query.getPreferenceFlag() == null) {
            List<RoleUser> roleUserList = roleUserService.findByUserId(user.getId());
            boolean manager = false;
            for (RoleUser roleUser : roleUserList) {
                //目前14代表总监，目前没有角色编码，暂时先这么处理
                if (roleUser.getRole() == 14) {
                    //表示目前用户属于销售助理
                    manager = true;
                }
            }
            //如果是总监,查询此人所有报价区域，查询出所有包含此报价区域的报价单
            if (manager) {
                List<DepartmentUser> departmentUserList = departmentUserService.selectList(new EntityWrapper<DepartmentUser>().eq("users", user.getId()));
                List<Integer> areaList = Lists.newArrayList();
                for (DepartmentUser departmentUser : departmentUserList) {
                    areaList.add(departmentUser.getDepartment());
                }
                query.setAreaList(areaList);
                this.offerService.selectListByManager(requestPage, query);
            }else {
                this.offerService.selectListByPage(requestPage, query);
            }
            List<OfferListDto> offerList = requestPage.getRecords();
            if (CollectionUtil.isNotEmpty(offerList)) {
                for (OfferListDto offer : offerList) {
                    offer.setQcodeUrl(attachmentService.medialUrl(offer.getNum() + ".png"));
                }
            }
        }else {
            if (query.getPreferenceFlag()) {
                this.offerService.selectPreferenceListByPage(requestPage, query);
            }else {
                this.offerService.selectListByPage(requestPage, query);
            }
        }
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/offer/preference/add")
    public ResponseEntity preferenceAdd(String preferences, @RequestUser RequestSubject user) {
        boolean ok = true;
        List<OfferPreference> preferenceList = JSON.parseArray(preferences, OfferPreference.class);
        for (OfferPreference preference : preferenceList) {
            preference.setCreater(user.getId());
            preference.setCreateTime(new Date());
            ok = offerPreferenceService.insert(preference);
        }
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR).createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/offer/preference/edit")
    public ResponseEntity preferenceEdit(String preferenceJson) {
        boolean ok = true;
        List<OfferPreference> preferenceList = JSON.parseArray(preferenceJson, OfferPreference.class);
        for (OfferPreference preference : preferenceList) {
            ok = preference.updateById();
        }
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR).createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/offer/rest/spares")
    public ResponseEntity offerSpares(@RequestUser RequestSubject user, int product, int series, String moneyType, int area){
        Department department = departmentService.selectById(area);
        Map<String, Object> map = spareService.offerSpares(user.getLanguage(),product,series, moneyType, department.getParent());
        return ResponseEntity.ok(map);
    }

    @GetMapping("/offer/rest/get/params")
    public ResponseEntity getParamsByPanel(int panel, @RequestUser RequestSubject user) {
        BoxParamsDto boxParams = productService.findBoxAndParamsById(panel, user.getLanguage());
        if (boxParams != null) {
            return ResponseEntity.ok(boxParams);
        }
        return null;
    }

    /**
     * 通过用户id查询对应的报价区域集合
     */
    @GetMapping("/offer/area/get")
    public ResponseEntity getSalesArea(int userId, @RequestUser RequestSubject user) {
        return ResponseEntity.ok(departmentService.selectDepartmentByUser(userId, user.getLanguage()));
    }
}

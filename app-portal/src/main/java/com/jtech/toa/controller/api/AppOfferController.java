/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.api;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtech.marble.StringPool;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.controller.BaseController;
import com.jtech.toa.entity.mail.MailFile;
import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.offer.OfferPreference;
import com.jtech.toa.entity.offer.OfferService;
import com.jtech.toa.entity.offer.OfferSpareSelfdefine;
import com.jtech.toa.entity.offer.OfferSpares;
import com.jtech.toa.entity.offer.OfferTransfer;
import com.jtech.toa.entity.offer.TransportPackage;
import com.jtech.toa.entity.prices.PricesDetails;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Standard;
import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.dto.DeleteData;
import com.jtech.toa.model.dto.InsertData;
import com.jtech.toa.model.dto.offer.AppOfferDto;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.model.dto.products.ProductSpareDto;
import com.jtech.toa.model.query.OfferQuery;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.model.vo.PanelVo;
import com.jtech.toa.service.ExportExcel;
import com.jtech.toa.service.MailService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.offer.IOfferPanelsService;
import com.jtech.toa.service.offer.IOfferPreferenceService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.offer.IOfferServiceService;
import com.jtech.toa.service.offer.IOfferSpareSelfdefineService;
import com.jtech.toa.service.offer.IOfferSparesService;
import com.jtech.toa.service.offer.IOfferTransferService;
import com.jtech.toa.service.offer.ITransportPackageService;
import com.jtech.toa.service.prices.IPricesDetailsService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.sync.syncinterface.Crm2Interface;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.model.dto.AppTemplate;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

/**
 * app offer api
 * @author
 */
@RestController
@RequestMapping("/api/offer")
public class AppOfferController extends BaseController {
    @Value("${template.exportExcel}")
    private String excelPath;
    @Value("${template.export}")
    private String filepath;
    @Value("${spring.mail.username}")
    private String senderMail;
    @Value("${oa.connect-oa-crm-interface}")
    private boolean connectOaCrmInterface;
    @Autowired
    IPriceService priceService;
    @Autowired
    ISysExchangeService exchangeService;
    @Autowired
    IDictService dictService;
    @Autowired
    IProductService productService;
    @Autowired
    IParamsService paramsService;
    @Autowired
    IBoxService boxService;
    @Autowired
    IModuleService moduleService;
    @Autowired
    ISpareService spareService;
    @Autowired
    IOfferService offerService;
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IOfferSparesService offerSparesService;
    @Autowired
    IOfferPanelsService offerPanelsService;
    @Autowired
    IOfferSpareSelfdefineService offerSpareSelfdefineService;
    @Autowired
    IOfferServiceService offerServiceService;
    @Autowired
    IOfferTransferService offerTransferService;
    @Autowired
    ITransportPackageService transportPackageService;
    @Autowired
    IPricesDetailsService pricesDetailsService;
    @Autowired
    ISeriesService seriesService;
    @Autowired
    IOfferPreferenceService offerPreferenceService;
    @Autowired
    IUserService userService;
    @Autowired
    private  MailService mailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppOfferController.class);

    /**
     * 报价单的保存功能实现
     * @param user 当前登录用户信息
     * @offer 报价单详情
     * @return
     */
    @PostMapping("/submit")
    public ResponseEntity submitOffer(@RequestUser RequestSubject user,String offer,boolean draftFlag){
        try{
            LOGGER.info("offer="+offer);
            OfferVo vo = JSON.parseObject(offer,OfferVo.class);
            Offer saveOffer  =offerService.save(user.getId(), offer, vo, draftFlag);
            if(saveOffer != null && saveOffer.getId() > 0){
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
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR).setMessage("系统出错!").createErrorModel());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.DATA_IN_WRONG_FORMAT).setMessage("提交数据格式有误!").createErrorModel());
        }

        return ResponseEntity.ok(-1);
    }

    @GetMapping("/panel/price")
    public ResponseEntity getPanelPrice(@RequestUser RequestSubject user,int product,String money){
        List<SysExchange> exchanges = exchangeService.getLastExchange();
        Map<String,BigDecimal> rateMap = Maps.newHashMap();
        for(SysExchange rate:exchanges){
            rateMap.put(rate.getCode(),rate.getRmb());
        }
        Price price = priceService.selectByProductAndArea(product, user.getArea());
        price.setSalePrice(exchangeService.calcRate(price.getSalePrice(),price.getUnit(),money,rateMap));
        price.setPrice(exchangeService.calcRate(price.getPrice(),price.getUnit(),money,rateMap));
        return ResponseEntity.ok(price);
    }

    @GetMapping("/create/params")
    public ResponseEntity getParams(@RequestUser RequestSubject user){
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        Map<String,Object> map = Maps.newHashMap();
        map.put("tradeCompany", dictService.selectDictByCategory("trade_company",user.getLanguage()));
        map.put("payment", dictService.selectDictByCategory("payment_mode",user.getLanguage()));
        map.put("shipping", dictService.selectDictByCategory("shipping",user.getLanguage()));
        map.put("rates",exchangeService.getLastExchange());
        map.put("department", departmentService.selectDepartmentByUser(user.getId(),shiroUser.getDeptName()));
        return ResponseEntity.ok(map);
    }

    @GetMapping("/create/panels")
    public ResponseEntity getPanelList(@RequestUser RequestSubject user,String params){
        Map<String, Object> paramMaps = JSONObject.parseObject(params, new TypeReference<Map<String, Object>>(){});
        paramMaps.put("area", user.getArea());
        paramMaps.put("lang", user.getLanguage());
        List<ProductDto> productList = productService.findProductListByParams(paramMaps);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/create/panel/detail")
    public ResponseEntity getPanelDetails(@RequestUser RequestSubject user,int productId,String money,@RequestParam(required = false,defaultValue = "0") int area){
        Product product = productService.findProductById(productId);
        Params params = paramsService.selectParamsByProductId(productId);
        Box box = boxService.findBoxById(product.getBox());
        if(area == 0){
            area = user.getArea();
        }

        Price price = priceService.selectByProductAndArea(productId,area);

        PricesDetails pricesDetails = pricesDetailsService.getDetailsByPanelAndArea(productId,area);

        Map<String,BigDecimal> rateMap = Maps.newHashMap();
        List<SysExchange> exchanges = exchangeService.getLastExchange();

        for(SysExchange rate:exchanges){
            rateMap.put(rate.getCode(),rate.getRmb());
        }

        if(null!=price){
            price.setPrice(exchangeService.calcRate(price.getPrice(),price.getUnit(),money,rateMap));
        }else{
            price = new Price();
            price.setPrice(BigDecimal.ZERO);
        }
        price.setUnit(money);

        if(null!=pricesDetails){
            price.setSalePrice(exchangeService.calcRate(pricesDetails.getPrice(),pricesDetails.getUnit(),money,rateMap));
        }

        Module module = moduleService.selectById(box.getModual());

        List<ProductSpareDto> standards = spareService.findSparesByProduct(productId, product.getSeries(), Standard.Type.Standard, Standard.StandardType.All, area, user.getLanguage());
        List<ProductSpareDto> optionals = spareService.findSparesByProduct(productId, product.getSeries(), Standard.Type.Optional, Standard.StandardType.All, area, user.getLanguage());
        List<ProductSpareDto> frees = spareService.findSparesByProduct(productId, product.getSeries(), Standard.Type.Free, Standard.StandardType.All, area, user.getLanguage());

        for (ProductSpareDto spare:standards){
            spare.setSalePrice(exchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),money,rateMap));
            spare.setCostPrice(exchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),money,rateMap));
        }

        for (ProductSpareDto spare:optionals){
            spare.setSalePrice(exchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),money,rateMap));
            spare.setCostPrice(exchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),money,rateMap));
        }

        for (ProductSpareDto spare:frees){
            spare.setSalePrice(exchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),money,rateMap));
            spare.setCostPrice(exchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),money,rateMap));
        }

        Map<String,Object> map = Maps.newHashMap();
        map.put("product",product);
        map.put("params",params);
        map.put("box",box);
        map.put("price",price);
        map.put("module",module);
        map.put("standards", standards);
        map.put("optionals", optionals);
        map.put("frees", frees);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/list/my")
    public ResponseEntity getMyOfferList(@RequestUser RequestSubject user,
             @RequestParam(value = "lastId", required = false,defaultValue = "0") long lastId,
             @RequestParam(value = "status",required = false,defaultValue = "-1") int status){
        return ResponseEntity.ok(offerService.getMyOfferList(user.getId(),lastId,status));
    }

    @GetMapping("/detail")
    public ResponseEntity getOfferDetail(@RequestUser RequestSubject user,long offerId){
//        OfferPrices offerPrices = offerPricesService.selectByOffer(offerId);
        List<PanelVo> panelVoList = Lists.newArrayList();
        List<OfferPanelsDto> offerPanels = offerPanelsService.selectListByOffer(offerId, user.getLanguage());
//        OfferBasic basic = basicService.selectOne(new EntityWrapper<OfferBasic>().eq("offer",offerId));
        MyOfferDto offer = offerService.findOfferById(offerId, user.getLanguage());
        for (OfferPanelsDto offerPanel : offerPanels) {
            List<OfferSpares> standardList = offerSparesService.selectList(new EntityWrapper<OfferSpares>().eq("offer", offerId).eq("type", 1).eq("panel", offerPanel.getId()));
            List<OfferSpares> spareList = offerSparesService.selectList(new EntityWrapper<OfferSpares>().eq("offer", offerId).eq("type", 2).eq("panel", offerPanel.getId()));
            List<OfferSpares> freeList = offerSparesService.selectList(new EntityWrapper<OfferSpares>().eq("offer", offerId).eq("type", 3).eq("panel", offerPanel.getId()));
            List<OfferSpareSelfdefine> selfStandList = offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("type", 1).eq("panel", offerPanel.getId()));
            List<OfferSpareSelfdefine> selfSpareList = offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("type", 2).eq("panel", offerPanel.getId()));
            List<OfferSpareSelfdefine> selfFreeList = offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("type", 3).eq("panel", offerPanel.getId()));
            PanelVo panelVo = new PanelVo();
            panelVo.setPanels(new OfferPanels());
            panelVo.setPanelsDto(offerPanel);
            panelVo.setStandardList(standardList);
            panelVo.setSpareList(spareList);
            panelVo.setFreeList(freeList);
            panelVo.setSelfStandList(selfStandList);
            panelVo.setSelfSpareList(selfSpareList);
            panelVo.setSelfFreeList(selfFreeList);
            panelVoList.add(panelVo);
        }
        List<OfferService> serviceList = offerServiceService.selectList(new EntityWrapper<OfferService>().eq("offer", offerId));
        OfferTransfer offerTransfer = offerTransferService.selectOne(new EntityWrapper<OfferTransfer>().eq("orders", offerId));
        OfferVo offerVo = offerService.getOfferDetails(offerId, user.getLanguage());
        offerVo.setPanelList(panelVoList);
        offerVo.setServiceList(serviceList);
        offerVo.setMyOffer(offer);

        Map<String,Object> resultList = Maps.newHashMap();
//        resultList.put("offerPrice", offerPrices == null? new OfferPrices():offerPrices);
        List<TransportPackage> packageList = transportPackageService.selectList(new EntityWrapper<TransportPackage>().eq("offer", offerId));
        BigDecimal bigDecimal = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(packageList)) {
            for (TransportPackage transportPackage : packageList) {
                bigDecimal = bigDecimal.add(transportPackage.getPrice().multiply(new BigDecimal(transportPackage.getNumber())));
            }
        }

        Map<String, Object> paramsMap = Maps.newHashMap();
//        paramsMap.put("area", basic.getArea());
        paramsMap.put("series", offer.getSeries());
        paramsMap.put("money", offer.getMoneyUnit());
//        paramsMap.put("config", basic.getConfiguration() != null ? basic.getConfiguration(): "");
//        paramsMap.put("state", basic.getStatus() != null ? basic.getStatus():"");
//        paramsMap.put("cert", basic.getCert() != null ? basic.getCert():"");
        paramsMap.put("lang", user.getLanguage());
        List<ProductDto> productList = productService.findProductListByParams(paramsMap);

        resultList.put("productList",productList);
        resultList.put("offerVo",offerVo);
        resultList.put("transfer", offerTransfer);
        resultList.put("packageList", packageList);
        resultList.put("packageTotal", bigDecimal.add(null==offerTransfer?BigDecimal.ZERO:offerTransfer.getCost()));
        if (offerVo.getMyOffer().getMoneyUnit() != null) {
            resultList.put("moneyCode",offerVo.getMyOffer().getMoneyUnit());
            resultList.put("moneyUnit", new SapRate().getKeyCode().get(offerVo.getMyOffer().getMoneyUnit()));
        }else{
            resultList.put("moneyCode","CNY");
            resultList.put("moneyUnit","￥");
        }

//        resultList.put("basics",basic);
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/find/offer")
    public ResponseEntity getOfferDetailById(long offerId){
        final Offer offer = offerService.selectById(offerId);
        return ResponseEntity.ok(offer);
    }

    @GetMapping("/spare/list")
    public ResponseEntity getSpareList(@RequestUser RequestSubject user,int productId,int series,String money,int area){
        List<ProductSpareDto> standards = spareService.findSparesByProduct(productId, series, Standard.Type.Standard, Standard.StandardType.All, area, user.getLanguage());
        List<ProductSpareDto> optionals = spareService.findSparesByProduct(productId, series, Standard.Type.Optional, Standard.StandardType.All, area, user.getLanguage());
        List<ProductSpareDto> frees = spareService.findSparesByProduct(productId, series, Standard.Type.Free, Standard.StandardType.All, area, user.getLanguage());
        Map<String,BigDecimal> rateMap = Maps.newHashMap();
        List<SysExchange> exchanges = exchangeService.getLastExchange();

        for(SysExchange rate:exchanges){
            rateMap.put(rate.getCode(),rate.getRmb());
        }

        for (ProductSpareDto spare:standards){
            spare.setSalePrice(exchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),money,rateMap));
            spare.setCostPrice(exchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),money,rateMap));
        }

        for (ProductSpareDto spare:optionals){
            spare.setSalePrice(exchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),money,rateMap));
            spare.setCostPrice(exchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),money,rateMap));
        }

        for (ProductSpareDto spare:frees){
            spare.setSalePrice(exchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),money,rateMap));
            spare.setCostPrice(exchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),money,rateMap));
        }

        Map<String,Object> map = Maps.newHashMap();
        map.put("standards", standards);
        map.put("optionals", optionals);
        map.put("frees", frees);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/list")
    public ResponseEntity getOfferList(@RequestUser RequestSubject user, long lastId,
                                       @RequestParam(value = "status",required = false, defaultValue = "0") Integer status,
                                       @RequestParam(required = false, defaultValue = "20") int pageSize){

        LOGGER.info("Get offer list,lastId:"+lastId+",status:"+status+",pageSize:"+pageSize);
        if(status == -1){
            status = null;
        }
        List<AppOfferDto> offers = offerService.findApiOfferListByPage(user.getId(), lastId,
                pageSize,status,user.getLanguage());

        return ResponseEntity.ok(offers);
    }

    @GetMapping("/tmps")
    public ResponseEntity getTemplates(@RequestUser RequestSubject user){
        DeleteData deleteData = new DeleteData(AppConstant.Templates);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("dataDelete",deleteData);
        jsonArray.add(jsonObject);

        List<AppTemplate> templates = Lists.newArrayList();
        List<OfferPreference> list =offerPreferenceService.selectList(new EntityWrapper<OfferPreference>().eq("creater",user.getId()));
        list.forEach(x->{
            AppTemplate template = new AppTemplate();
            template.setId(x.getId());
            template.setName(x.getName());
            template.setOffer(x.getOffer());
            templates.add(template);
        });
        InsertData<AppTemplate> insertData = new InsertData<>(AppConstant.Templates,templates,2);

        JSONObject insert=new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/preference/list")
    public ResponseEntity getPreferenceList(@RequestUser RequestSubject user,
                                            @RequestParam(value="last",defaultValue = "0",required = false) long last,
                                            @RequestParam(required = false)String q) {
        OfferQuery query;
        if (StringUtils.isNotEmpty(q)) {
            query = JSON.parseObject(q, OfferQuery.class);
        }else {
            query = new OfferQuery();
        }
        query.setUserId(user.getId());
        return ResponseEntity.ok(offerService.selectAppPreferenceListByPage(query, last));
    }


    @GetMapping("/create/excel")
    public ResponseEntity createExcel(
            @RequestUser RequestSubject user,long offerId, String language){
        final OfferVo offerVo = offerService.getOfferDetails(offerId, language);
        final MyOfferDto myOffer = offerService.findOfferById(offerId, language);
        final User userMsg = userService.selectById(offerVo.getOffer().getCreater());
        final Department department = departmentService.selectParentById(offerVo.getOffer().getArea());
        final List<OfferPanelsDto> offerPanelsList = offerPanelsService.selectListByOffer(offerId, language);
        final ExportExcel exportExcel = new ExportExcel();
        final Map fileInfo = exportExcel.findExcelFileInfo(offerVo, myOffer, department,
                offerPanelsList, userMsg, language, excelPath,filepath);
        return ResponseEntity.ok(fileInfo);
    }

    @PostMapping("/remove/{offerId}")
    public ResponseEntity removeOffer(@PathVariable(value = "offerId", required = false) long offerId){
        final Offer offer = offerService.selectById(offerId);
        offer.setDeleteFlag("1");
        offerService.updateById(offer);
        return ResponseEntity.ok(offerId);
    }

    @PostMapping("/finish/{offerId}")
    public ResponseEntity finishOffer(@PathVariable(value = "offerId", required = false) long offerId){
        final Offer offer = offerService.selectById(offerId);
        offer.setStatus(Offer.Status.Finish);
        offerService.updateById(offer);
        return ResponseEntity.ok(offerId);
    }

    @GetMapping("/find/{orderNum}")
    public ResponseEntity findByofferId(@PathVariable(value = "orderNum", required = false) String orderNum){
        final Offer offer = offerService.selectOne(new EntityWrapper<Offer>().eq("num", orderNum));
        return ResponseEntity.ok(offer);
    }

    @GetMapping("/download/{offerId}/{filename}/{language}")
    public void createExcel(
            @RequestUser RequestSubject user,
            HttpServletResponse response,
            @PathVariable(value = "filename", required = false) String filename,
            @PathVariable(value = "offerId", required = false) long offerId,
            @PathVariable(value = "language", required = false) String language){
        LOGGER.info("Download filename:"+filename);
//        final OfferVo offerVo = offerService.getOfferDetails(offerId, language);
//        final MyOfferDto myOffer = offerService.findOfferById(offerId, language);
//        final User userMsg = userService.selectById(offerVo.getOffer().getCreater());
//        final Department department = departmentService.selectParentById(offerVo.getOffer().getArea());
//        final List<OfferPanelsDto> offerPanelsList = offerPanelsService.selectListByOffer(offerId, language);
//        final ExportExcel exportEcel = new ExportExcel();
//        exportEcel.findExcelFileInfo(offerVo, myOffer, department,
//                offerPanelsList, userMsg, language, excelPath,filepath);
//        String languageType = "zh".equals(language) ? "(CN)" : "(EN)";
        File file  = new File(filepath+filename);
        LOGGER.debug("Download file.getPath()="+file.getPath());
        try {
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 报价单发送邮件  语言(zh:中文;en:英文)
     * @param offerId 报价单id
     * @param receiverMail 接收人
     * @param subject 主题
     * @param type 类型（1：中文excel；2：英文excel；3：中文pdf；4：英文pdf）
     * @return
     */
    @GetMapping("/sendMail")
    public ResponseEntity sendMail(@RequestParam("offerId") String offerId,
                                   @RequestParam("receiverMail") String receiverMail,
                                   @RequestParam("subject") String subject,
                                   @RequestParam("type") String type,
                                   HttpServletRequest request){
        LOGGER.info("app offer send mail,offerId:"+offerId+
                ",receiverMail:"+receiverMail+",subject:"+subject+",type:"+type);
        if(!Strings.isNullOrEmpty(offerId)&&!Strings.isNullOrEmpty(receiverMail)
                &&!Strings.isNullOrEmpty(subject)&&!Strings.isNullOrEmpty(type)){
            String language="";
            //发送1:中文excel,2:英文excel
            if("1".equals(type)||"2".equals(type)){
                if("1".equals(type)) {
                    language = "zh";
                }
                if("2".equals(type)){
                    language = "en";
                }
                String fileName = this.createOfferExcel(Long.parseLong(offerId), language, filepath);
                try {
                    Map<String, Object> model = new HashMap<>(4);
                    List<MailFile> mailFiles = Lists.newArrayList();
                    MailFile mailFile = new MailFile();
                    mailFile.setSendMail(senderMail);
                    mailFile.setAcceptMail(receiverMail);
                    mailFile.setMailSubject(subject);
                    mailFiles.add(mailFile);

                    // 发送邮件
                    model.put("mailFiles", mailFiles);
                    model.put("filePath", fileName);
                    model.put("path", filepath);
                    model.put("deployServerPath", mailService.getDeployServerPath(request));
                    mailService.sendHtmlMailUsingFreeMarkerOffer(senderMail, receiverMail, subject, model);
                    File file = new File(fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    return ResponseEntity.ok("send success.");
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.ok("send fail:"+e.getMessage());
                }
            }
            //发送3:中文pdf,4:英文pdf
            if("3".equals(type) || "4".equals(type)){
                if("3".equals(type)) {
                    language = "zh";
                }
                if("4".equals(type)){
                    language = "en";
                }

                List<String> fileNames = this.createOfferExcelPdf(Long.parseLong(offerId), language, filepath);
                //设置输出文件
//                String pdfTmplPath = fileName.replace("xlsx", "pdf");
//                File pdfTmpl = new File(pdfTmplPath);
//                if (pdfTmpl.exists()) {
//                    pdfTmpl.delete();
//                }
                String pdfFileName = this.createOfferPdf(fileNames);

                try {
                    Map<String, Object> model = new HashMap<>(4);

                    List<MailFile> mailFiles = Lists.newArrayList();

                    MailFile mailFile = new MailFile();

                    mailFile.setSendMail(senderMail);
                    mailFile.setAcceptMail(receiverMail);
                    mailFile.setMailSubject(subject);
                    mailFiles.add(mailFile);

                    // 发送邮件
                    model.put("mailFiles", mailFiles);
                    model.put("filePath", pdfFileName);
                    model.put("path", filepath);
                    model.put("deployServerPath", mailService.getDeployServerPath(request));
                    mailService.sendHtmlMailUsingFreeMarkerOffer(senderMail, receiverMail,subject, model);
                    File file = new File(pdfFileName);
                    for (String fileName:fileNames) {
                        File fileExcel = new File(fileName);
                        if (file.exists()) {
                            file.delete();
                        }
                        if (fileExcel.exists()) {
                            fileExcel.delete();
                        }
                    }
                    return ResponseEntity.ok("send success.");
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.ok("send fail:"+e.getMessage());
                }
            }
        }else{
           return ResponseEntity.ok("send fail,missing parameter.");
        }
        return ResponseEntity.ok(StringPool.EMPTY);
    }

    /**
     * 报价单下载  语言(zh:中文;en:英文)
     * @param offerId 报价单id
     * @param type 类型（1：中文excel；2：英文excel；3：中文pdf；4：英文pdf）
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity download(@RequestParam("offerId") String offerId,
                                   @RequestParam("type") String type){
        LOGGER.info("app offer download,offerId:"+offerId+",type="+type);
        if(!Strings.isNullOrEmpty(offerId)&&!Strings.isNullOrEmpty(type)){
            String language="";
            //下载1:中文excel,2:英文excel
            if("1".equals(type)||"2".equals(type)) {
                if ("1".equals(type)) {
                    language = "zh";
                }
                if ("2".equals(type)) {
                    language = "en";
                }
                String fileName = this.createOfferExcel(Long.parseLong(offerId), language, filepath);
                HashMap<Object, Object> hashMap = Maps.newHashMap();
                File file = new File(fileName);
                final long sizeOf = FileUtils.sizeOf(file);
                hashMap.put("size",sizeOf);
                hashMap.put("name", file.getName());
                return ResponseEntity.ok(hashMap);
            }
            //下载3:中文pdf,4:英文pdf
            if("3".equals(type) || "4".equals(type)) {
                if ("3".equals(type)) {
                    language = "zh";
                }
                if ("4".equals(type)) {
                    language = "en";
                }
                List<String> fileNames = this.createOfferExcelPdf(Long.parseLong(offerId), language, filepath);
                String pdfFileName = this.createOfferPdf(fileNames);
                HashMap<Object, Object> hashMap = Maps.newHashMap();
                File file = new File(pdfFileName);
                final long sizeOf = FileUtils.sizeOf(file);
                hashMap.put("size",sizeOf);
                hashMap.put("name", file.getName());
                return ResponseEntity.ok(hashMap);
            }
        }else{
            return ResponseEntity.ok("download fail,missing parameter.");
        }
        return ResponseEntity.ok("");
    }

}

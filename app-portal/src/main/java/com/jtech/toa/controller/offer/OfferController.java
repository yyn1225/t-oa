package com.jtech.toa.controller.offer;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xiaoleilu.hutool.util.CollectionUtil;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.entity.offer.OfferPanels;
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
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.Standard;
import com.jtech.toa.entity.system.RoleUser;
import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferListDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.dto.products.PanelDetails;
import com.jtech.toa.model.dto.products.ProductSpareDto;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.model.dto.products.SpareDetails;
import com.jtech.toa.model.query.OfferQuery;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.model.vo.PanelVo;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.offer.IOfferPanelsService;
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
import com.jtech.toa.service.system.IRoleUserService;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

/**
 * <p>报价单相关信息 </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Controller
public class OfferController {
    private final ISpareService spareService;
    private final ISeriesService seriesService;
    private final IBoxService boxService;
    private final IParamsService paramsService;
    private final IModuleService moduleService;
    private final IPriceService priceService;
    private final ICustomerService customerService;
    private final IOfferSparesService offerSparesService;
    private final IProductService productService;
    private final IOfferService offerService;
    private final IOfferPanelsService offerPanelsService;
    private final IOfferSpareSelfdefineService offerSpareSelfdefineService;
    private final IOfferServiceService offerServiceService;
    private final IDictService dictService;
    private final IOfferTransferService offerTransferService;
    private final ISysExchangeService iSysExchangeService;
    private final IDepartmentService departmentService;
    private final IPricesDetailsService pricesDetailsService;
    private final ITransportPackageService transportPackageService;
    private final IRoleUserService roleUserService;
    private final IUserService userService;

    @Autowired
    public OfferController(ISpareService spareService,
                           ISeriesService seriesService,
                           IBoxService boxService,
                           IParamsService paramsService,
                           IModuleService moduleService,
                           IPriceService priceService,
                           ICustomerService customerService,
                           IOfferSparesService offerSparesService,
                           IProductService productService,
                           IOfferService offerService,
                           IOfferPanelsService offerPanelsService,
                           IOfferSpareSelfdefineService offerSpareSelfdefineService,
                           IOfferServiceService offerServiceService,
                           ISysExchangeService iSysExchangeService,
                           IDictService dictService,
                           IOfferTransferService offerTransferService,
                           IPricesDetailsService pricesDetailsService,
                           IDepartmentService departmentService,
                           ITransportPackageService transportPackageService, IRoleUserService roleUserService, IUserService userService) {
        this.spareService = spareService;
        this.seriesService = seriesService;
        this.boxService = boxService;
        this.paramsService = paramsService;
        this.moduleService = moduleService;
        this.priceService = priceService;
        this.customerService = customerService;
        this.offerSparesService = offerSparesService;
        this.productService = productService;
        this.offerService = offerService;
        this.offerPanelsService = offerPanelsService;
        this.offerSpareSelfdefineService = offerSpareSelfdefineService;
        this.offerServiceService = offerServiceService;
        this.dictService = dictService;
        this.offerTransferService = offerTransferService;
        this.iSysExchangeService = iSysExchangeService;
        this.departmentService = departmentService;
        this.pricesDetailsService = pricesDetailsService;
        this.transportPackageService = transportPackageService;
        this.roleUserService = roleUserService;
        this.userService = userService;
    }

    /**
     * 通过屏体的id，查找该屏体对应的备件信息列表
     *
     * @param model spring
     * @return
     */
    @GetMapping("/offer/spares")
    public String spareParts(@RequestUser RequestSubject user, String productIds, int series, int type, String moneyType, Model model, int area) {
        Department department = departmentService.selectById(area);
        //-1代表不对是否默认带出进行过滤

        String[] productIdStr = productIds.split(",");

        Module moduleLeft = null;
        Module moduleRight = null;
        PricesDetails pricesDetails = pricesDetailsService.getDetailsByPanelAndArea(Integer.parseInt(productIdStr[0]), area);
        Product pd = productService.selectById(Integer.parseInt(productIdStr[0]));
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

        List<List<ProductSpareDto>> totalList = Lists.newArrayList();
        for (String productId: productIdStr) {
            List<ProductSpareDto> list = spareService.findSparesByProductIdsWithClassify(Integer.parseInt(productId), series, type, -1, department.getParent(), user.getLanguage());
            for (ProductSpareDto spare: list) {
                //左模组的物料号和备件物料号一致
                if (null !=pricesDetails && ((null != moduleLeft && spare.getMaterial().equals(moduleLeft.getScnNo()))
                        //右模组的物料号和备件物料号一致
                        || (null != moduleRight && spare.getMaterial().equals(moduleRight.getScnNo())))) {
                    spare.setSalePrice(iSysExchangeService.calcRate(pricesDetails.getModual(),pricesDetails.getUnit(),moneyType,rateMap));
                }else{
                    spare.setSalePrice(iSysExchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),moneyType,rateMap));
                }

                spare.setCostPrice(iSysExchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),moneyType,rateMap));
            }
            totalList.add(list);
        }

        //通用配件存入
        List<ProductSpareDto> commonList = spareService.selectCommonSpares(area);
        for (ProductSpareDto spare: commonList) {
            //左模组的物料号和备件物料号一致
            if (null !=pricesDetails && ((null != moduleLeft && spare.getMaterial().equals(moduleLeft.getScnNo()))
                    //右模组的物料号和备件物料号一致
                    || (null != moduleRight && spare.getMaterial().equals(moduleRight.getScnNo())))) {
                spare.setSalePrice(iSysExchangeService.calcRate(pricesDetails.getModual(),pricesDetails.getUnit(),moneyType,rateMap));
            }else{
                spare.setSalePrice(iSysExchangeService.calcRate(spare.getSalePrice(),spare.getMoneyUnit(),moneyType,rateMap));
            }

            spare.setCostPrice(iSysExchangeService.calcRate(spare.getCostPrice(),spare.getMoneyUnit(),moneyType,rateMap));
        }

        totalList.add(commonList);

        model.addAttribute("list", totalList);
        return "offer/spare";
    }

    @GetMapping("/offer/list")
    public String index(Model model) {
        model.addAttribute("series", seriesService.findSeriesList());
        return "offer/my/list";
    }
    /**
     * 报价单详情查看
     * @param offerId 报价单主键
     * @param user 登录用户
     */
    @GetMapping("/offer/item")
    public String item(Long offerId, Model model, @RequestUser RequestSubject user) {
        List<PanelVo> panelVoList = Lists.newArrayList();
        List<OfferPanelsDto> offerPanels = offerPanelsService.selectListByOffer(offerId, user.getLanguage());
        MyOfferDto offer = offerService.findOfferById(offerId, user.getLanguage());
        for (OfferPanelsDto offerPanel : offerPanels) {
            Params params = paramsService.selectParamsByProductId(offerPanel.getPanel());
            Product product = productService.selectByIdAndLang(offerPanel.getPanel(), user.getLanguage());
            List<SpareDetails> standardList = offerSparesService.getSpareListByOffer(offerId,offerPanel.getId(), OfferSpares.Type.Standard, user.getLanguage());
            List<SpareDetails> spareList = offerSparesService.getSpareListByOffer(offerId,offerPanel.getId(), OfferSpares.Type.Spare, user.getLanguage());
            List<SpareDetails> freeList = offerSparesService.getSpareListByOffer(offerId,offerPanel.getId(), OfferSpares.Type.Free, user.getLanguage());
            List<OfferSpareSelfdefine> selfStandList = offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("type", 1).eq("panel", offerPanel.getId()));
            List<OfferSpareSelfdefine> selfSpareList = offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("type", 2).eq("panel", offerPanel.getId()));
            List<OfferSpareSelfdefine> selfFreeList = offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("type", 3).eq("panel", offerPanel.getId()));
            Box box = boxService.selectByIdAndLang(product.getBox(), user.getLanguage());
            Module module = moduleService.selectByIdAndLang(box.getModual(), user.getLanguage());
            PanelVo panelVo = new PanelVo();
            panelVo.setPanelsDto(offerPanel);
            panelVo.setParams(params);
            panelVo.setModule(module);
            panelVo.setBox(box);
            panelVo.setStandardDetailList(standardList);
            panelVo.setSpareDetailList(spareList);
            panelVo.setFreeDetailList(freeList);
            panelVo.setSelfStandList(selfStandList);
            panelVo.setSelfSpareList(selfSpareList);
            panelVo.setSelfFreeList(selfFreeList);
            panelVoList.add(panelVo);
        }
        List<OfferService> serviceList = offerServiceService.selectList(new EntityWrapper<OfferService>().eq("offer", offerId));
        OfferTransfer offerTransfer = offerTransferService.selectOne(new EntityWrapper<OfferTransfer>().eq("orders", offerId));
        OfferVo offerVo = new OfferVo();
        offerVo.setPanelList(panelVoList);
        offerVo.setServiceList(serviceList);
        offerVo.setMyOffer(offer);
        List<TransportPackage> packageList = transportPackageService.selectList(new EntityWrapper<TransportPackage>().eq("offer", offerId));
        BigDecimal bigDecimal = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(packageList)) {
            for (TransportPackage transportPackage : packageList) {
                bigDecimal = bigDecimal.add(transportPackage.getPrice());
            }
        }
        model.addAttribute("offerVo",offerVo);
        model.addAttribute("offerId", offerId);
        model.addAttribute("transfer", offerTransfer);
        model.addAttribute("packageList", packageList);
        model.addAttribute("packageTotal", bigDecimal.add(offerTransfer.getCost()));
        if (offerVo.getMyOffer().getMoneyUnit() != null) {
            model.addAttribute("moneyUnit", new SapRate().getKeyCode().get(offerVo.getMyOffer().getMoneyUnit()));
        }
        return "offer/my/item";
    }


    /**
     * 邮件发送编辑页
     *
     * @param fileIds 需要发送的文件ids
     * @param model
     * @return
     */
    @GetMapping("/offer/mail")
    public String edit(String fileIds,String language, Model model) {
        model.addAttribute("fileIds", fileIds);
        model.addAttribute("language", language);
        return "offer/my/mail";
    }


    /**
     * 获取所有销售员的方法
     * @param user 当前用户
     */
    private List<User> getSalesList(RequestSubject user) {
        List<RoleUser> roleUserList = roleUserService.findByUserId(user.getId());
        boolean assistant = false;
        for (RoleUser roleUser : roleUserList) {
            //目前18代表销售助理，目前没有角色编码，暂时先这么处理
            if (roleUser.getRole() == 18) {
                //表示目前用户属于销售助理
                assistant = true;
            }
        }

        List<User> userList = Lists.newArrayList();
        //如果当前用户为销售助理，则查询出此销售助理对应负责的销售
        if (assistant) {
            userList = userService.selectList(new EntityWrapper<User>().eq("assistant", user.getId()));
        }else {
            User u = new User();
            u.setId(user.getId());
            u.setName(user.getUserName());
            u.setPhone(user.getPhone());
            u.setArea(user.getArea());
            userList.add(u);
        }

        return userList;
    }

    /**
     * 新建报价单
     * @param user 登录用户
     */
    @GetMapping("/offer/create")
    public String createStep1(@RequestUser RequestSubject user, Model model) {
        //查询所有的产品系列
        List<SeriesDto> seriesList = seriesService.findSeriesList();
        List<Customer> customerList = customerService.findMyCustomer(user.getId());

        model.addAttribute("customers", customerList);
        model.addAttribute("seriesList", seriesList);
        model.addAttribute("department", departmentService.selectDepartmentByUser(user.getId(),user.getLanguage()));
        model.addAttribute("tradeCompany", dictService.selectDictByCategory("trade_company",user.getLanguage()));
        model.addAttribute("tradeAddress", dictService.selectDictByCategory("trade_address",user.getLanguage()));
        model.addAttribute("shipping", dictService.selectDictByCategory("shipping",user.getLanguage()));
        model.addAttribute("rates",iSysExchangeService.getLastExchange());
        model.addAttribute("user", getSalesList(user));
        List<Dict> tradeDict = dictService.selectDictByCategory("terms_of_trade",user.getLanguage());
        model.addAttribute("tradeDict", tradeDict);
        model.addAttribute("tag", UUID.randomUUID().toString().replace("-", ""));
        model.addAttribute("serviceList", dictService.selectDictByCategory("offer_service",user.getLanguage()));
        return "offer/offer";
    }

    /**
     * 报价单详情编辑
     * @param offerId 报价单主键
     * @param user 登录用户
     */
    @GetMapping("/offer/create/edit")
    public String edit(Model model,long offerId, @RequestUser RequestSubject user) {
        OfferVo offerVo = offerService.getOfferDetails(offerId, user.getLanguage());

        for (PanelDetails panelDetails : offerVo.getPanelDetailList()) {
            try {
                //查询某个屏第一个拼屏的系列
                Series series = seriesService.selectById(panelDetails.getOfferPanels().getChildPanels().get(0).getSeries());

                for (OfferPanels childPanels : panelDetails.getOfferPanels().getChildPanels()) {
                    childPanels.setSeriesName(series.getName());
                    childPanels.setLine(series.getLine());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Customer> customerList = customerService.findMyCustomer(user.getId());

        BigDecimal bigDecimal = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(offerVo.getTransport())) {
            for (TransportPackage transportPackage : offerVo.getTransport()) {
                bigDecimal = bigDecimal.add(transportPackage.getPrice());
            }
        }
        if (offerVo.getTransfer() != null && offerVo.getTransfer().getCost() != null) {
            model.addAttribute("transferPrice", bigDecimal.add(offerVo.getTransfer().getCost()));
        }
        model.addAttribute("customers", customerList);
        model.addAttribute("department", departmentService.selectDepartmentByUser(user.getId(),user.getLanguage()));
        model.addAttribute("tradeCompany", dictService.selectDictByCategory("trade_company",user.getLanguage()));
        model.addAttribute("tradeAddress", dictService.selectDictByCategory("trade_address",user.getLanguage()));
        model.addAttribute("shipping", dictService.selectDictByCategory("shipping",user.getLanguage()));
        model.addAttribute("rates",iSysExchangeService.getLastExchange());
        model.addAttribute("user", getSalesList(user));
        model.addAttribute("money", offerVo.getOffer().getRate());
        model.addAttribute("offerVo", offerVo);
        List<Dict> tradeDict = dictService.selectDictByCategory("terms_of_trade",user.getLanguage());
        model.addAttribute("tradeDict", tradeDict);
        model.addAttribute("tag", UUID.randomUUID().toString().replace("-", ""));
        model.addAttribute("serviceList", dictService.selectDictByCategory("offer_service",user.getLanguage()));
        return "offer/newEdit";
    }

    /**
     * 报价单复制报价
     * @param offerId 报价单主键
     * @param user 登录用户
     */
    @GetMapping("/offer/create/copy")
    public String copy(Model model,long offerId, @RequestUser RequestSubject user) {
        OfferVo offerVo = offerService.getOfferDetails(offerId, user.getLanguage());

        for (PanelDetails panelDetails : offerVo.getPanelDetailList()) {
            try {
                //查询某个屏第一个拼屏的系列
                Series series = seriesService.selectById(panelDetails.getOfferPanels().getChildPanels().get(0).getSeries());

                for (OfferPanels childPanels : panelDetails.getOfferPanels().getChildPanels()) {
                    childPanels.setSeriesName(series.getName());
                    childPanels.setLine(series.getLine());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Customer> customerList = customerService.findMyCustomer(user.getId());

        BigDecimal bigDecimal = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(offerVo.getTransport())) {
            for (TransportPackage transportPackage : offerVo.getTransport()) {
                bigDecimal = bigDecimal.add(transportPackage.getPrice());
            }
        }
        if (offerVo.getTransfer() != null && offerVo.getTransfer().getCost() != null) {
            model.addAttribute("transferPrice", bigDecimal.add(offerVo.getTransfer().getCost()));
        }
        model.addAttribute("customers", customerList);
        model.addAttribute("department", departmentService.selectDepartmentByUser(user.getId(),user.getLanguage()));
        model.addAttribute("tradeCompany", dictService.selectDictByCategory("trade_company",user.getLanguage()));
        model.addAttribute("tradeAddress", dictService.selectDictByCategory("trade_address",user.getLanguage()));
        model.addAttribute("shipping", dictService.selectDictByCategory("shipping",user.getLanguage()));
        model.addAttribute("rates",iSysExchangeService.getLastExchange());
        model.addAttribute("user", getSalesList(user));
        model.addAttribute("money", offerVo.getOffer().getRate());
        model.addAttribute("offerVo", offerVo);
        List<Dict> tradeDict = dictService.selectDictByCategory("terms_of_trade",user.getLanguage());
        model.addAttribute("tradeDict", tradeDict);
        model.addAttribute("tag", UUID.randomUUID().toString().replace("-", ""));
        model.addAttribute("serviceList", dictService.selectDictByCategory("offer_service",user.getLanguage()));
        return "offer/copy";
    }


    /**
     * 下拉选择屏带出参数的方法
     * @param product 屏体物料号
     * @param series 系列
     * @param money 货币类型
     * @param tag 标识，表示某一个tab页面
     * @param requestArea 报价区域
     * @param fieldsetIndex 当前屏的filedset下标
     **/
    @GetMapping("/offer/create/panel/params")
    public String panelParamDetails(int product, int series, String money, String tag, Model model,
                                    @RequestUser RequestSubject user,int requestArea, int fieldsetIndex) {
        Params params = paramsService.selectParamsByProductId(product);
        Product p = productService.selectByIdAndLang(product, user.getLanguage());
        model.addAttribute("param", params);
        model.addAttribute("panel", p);

        Module moduleLeft = null;
        Module moduleRight = null;
        if (null != p) {
            Box box = boxService.selectByIdAndLang(p.getBox(), user.getLanguage());
            model.addAttribute("box",box);
            if (null != box && 0 != box.getModual()) {
                moduleLeft = moduleService.selectByIdAndLang(box.getModual(), user.getLanguage());
                model.addAttribute("modual", moduleLeft);
            }
            if (null != box && 0 != box.getModual2()) {
                moduleRight = moduleService.selectByIdAndLang(box.getModual2(), user.getLanguage());
            }
        }

        //获取此价格信息是为了得到成本价
        Price price = priceService.selectByProductAndArea(product, requestArea);

        PricesDetails pricesDetails = pricesDetailsService.getDetailsByPanelAndArea(product,requestArea);

        Department department = departmentService.selectById(requestArea);

        List<SysExchange> exchanges = iSysExchangeService.getLastExchange();
        Map<String,BigDecimal> rateMap = Maps.newHashMap();
        for(SysExchange rate:exchanges){
            rateMap.put(rate.getCode(),rate.getRmb());
        }
        if(price==null){
            price=new Price();
            price.setUnit(money);
            price.setPrice(BigDecimal.ZERO);
        }else{
            price.setPrice(iSysExchangeService.calcRate(price.getPrice(),price.getUnit(),money,rateMap));
            price.setUnit(money);
        }

        if(null!=pricesDetails){
            price.setSalePrice(iSysExchangeService.calcRate(pricesDetails.getPrice(),pricesDetails.getUnit(),money,rateMap));
        }

        model.addAttribute("price", price);
        model.addAttribute("tag", tag);

        List<ProductSpareDto> standards = spareService.findSparesByProductWithClassify(product, series, Standard.Type.Standard, Standard.StandardType.Yes, department.getParent(), user.getLanguage());
        List<ProductSpareDto> optionals = spareService.findSparesByProductWithClassify(product, series, Standard.Type.Optional, Standard.StandardType.Yes, department.getParent(), user.getLanguage());
        List<ProductSpareDto> frees = spareService.findSparesByProductWithClassify(product, series, Standard.Type.Free, Standard.StandardType.Yes, department.getParent(), user.getLanguage());

        List<ProductSpareDto> spareDtoList = Lists.newArrayList();
        spareDtoList.addAll(standards);
        spareDtoList.addAll(optionals);
        spareDtoList.addAll(frees);

        for (ProductSpareDto spare : spareDtoList) {
            spare.setCostPrice(iSysExchangeService.calcRate(spare.getCostPrice(), spare.getMoneyUnit(), money, rateMap));
            //左模组的物料号和备件物料号一致                                                                           //右模组的物料号和备件物料号一致
            if (null !=pricesDetails && ((null != moduleLeft && spare.getMaterial().equals(moduleLeft.getScnNo())) || (null != moduleRight && spare.getMaterial().equals(moduleRight.getScnNo())))) {
                spare.setSalePrice(iSysExchangeService.calcRate(pricesDetails.getModual(), pricesDetails.getUnit(), money, rateMap));
            } else {
                spare.setSalePrice(iSysExchangeService.calcRate(spare.getSalePrice(), spare.getMoneyUnit(), money, rateMap));
            }
        }

        model.addAttribute("standards", standards);
        model.addAttribute("optionals", optionals);
        model.addAttribute("frees", frees);
        model.addAttribute("money",new SapRate().getKeyCode().get(money));
        model.addAttribute("fieldsetIndex", fieldsetIndex);

        return "offer/items/params";
    }

    /**
     * 我的喜好列表
     */
    @GetMapping("/offer/preference")
    public String preference(Model model) {
        model.addAttribute("series", seriesService.findSeriesList());
        return "offer/preference/list";
    }

    /**
     * 我的喜好选择报价单页面
     */
    @GetMapping("/offer/preference/detail")
    public String preferenceDetail(Model model, @RequestUser RequestSubject user) {
        OfferQuery query = new OfferQuery();
        query.setUserId(user.getId());
        List<OfferListDto> offerList = offerService.selectOfferList(query);
        if (CollectionUtil.isNotEmpty(offerList)) {
            for (OfferListDto offerListDto : offerList) {
                if (!Strings.isNullOrEmpty(offerListDto.getMoneyUnit())) {
                    offerListDto.setMoneyUnit(new SapRate().getKeyCode().get(offerListDto.getMoneyUnit()));
                }
            }
        }
        model.addAttribute("offerList", offerList);
        return "offer/preference/detail";
    }
}

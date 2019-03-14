package com.jtech.toa.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.constants.DictCategory;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.app.AppCustomer;
import com.jtech.toa.model.app.AppDict;
import com.jtech.toa.model.app.AppFile;
import com.jtech.toa.model.app.dto.AddDbDataDto;
import com.jtech.toa.model.app.dto.TableInfoDto;
import com.jtech.toa.model.dto.files.AppFileMarket;
import com.jtech.toa.model.dto.files.AppFileSeries;
import com.jtech.toa.model.dto.files.AppPackages;
import com.jtech.toa.model.dto.offer.AppOfferDto;
import com.jtech.toa.model.dto.prices.AppProductPrice;
import com.jtech.toa.model.dto.prices.AppSparePrice;
import com.jtech.toa.model.dto.products.AppProduct;
import com.jtech.toa.model.dto.products.AppProductDto;
import com.jtech.toa.model.dto.products.AppSeries;
import com.jtech.toa.model.dto.products.AppSpareProduct;
import com.jtech.toa.model.dto.products.AppSpares;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.file.IFileMarketDetailService;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.service.file.IFileSeriesService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.product.IStandardService;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.DepartmentUser;
import com.jtech.toa.user.model.dto.AppAreas;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IDepartmentUserService;

/**
 * <p>
 *     初始化APP端 数据库
 * </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
public class AppInitDbService {
    private final ICustomerService customerService;
    private final IDictService dictService;
    private final ISysExchangeService exchangeService;
    private final IProductService productService;
    private final ISpareService spareService;
    private final IPriceService priceService;
    private final IStandardService standardService;
    private final IDepartmentService departmentService;
    private final ISeriesService seriesService;
    private final IFileSeriesService fileSeriesService;
    private final IFilePackageService filePackageService;
    private final IFileMarketDetailService fileMarketDetailService;
    private final IOfferService offerService;
    private final IFileService fileService;
    private final IDepartmentUserService departmentUserService;

    public AppInitDbService(ICustomerService customerService,
                            IDictService dictService,
                            ISysExchangeService exchangeService,
                            IProductService productService,
                            ISpareService spareService,
                            IPriceService priceService,
                            IStandardService standardService,
                            IDepartmentService departmentService,
                            ISeriesService seriesService,
                            IFileSeriesService fileSeriesService,
                            IFilePackageService filePackageService,
                            IFileMarketDetailService fileMarketDetailService,
                            IOfferService offerService,
                            IFileService fileService,
                            IDepartmentUserService departmentUserService){
        this.customerService = customerService;
        this.dictService = dictService;
        this.exchangeService = exchangeService;
        this.productService = productService;
        this.spareService = spareService;
        this.priceService = priceService;
        this.standardService = standardService;
        this.departmentService = departmentService;
        this.seriesService = seriesService;
        this.fileSeriesService = fileSeriesService;
        this.filePackageService = filePackageService;
        this.fileMarketDetailService = fileMarketDetailService;
        this.offerService = offerService;
        this.fileService = fileService;
        this.departmentUserService = departmentUserService;
    }


    /**
     * 全量更新
     */
    public List<Object> createSQLiteDb(RequestSubject user){
        List<Object> list = Lists.newArrayList();

        Map customers = this.findCustomer(user, 0);
        Map dicts = this.findDict(user, 0);
        Map rates = this.findRate();
        Map products = this.findProduct(user, 0);
        Map prices = this.findProductPrice(user, 0);
        Map sparePrice = this.findSparePrice(user, 0);
        Map spare = this.findSpare(user, 0);
        Map spareProduct = this.findSpareProduct(user, 0);
        Map userArea = this.findUserArea(user);
        Map series = this.findSeries();
        Map fileSeries = this.findFileSeries(0);
        Map filesPackages = this.findFilesPackages(user);
        Map marketList = this.findApiAppMarketList();
        Map fileList = this.findApiAppFileList(user);

        list.add(customers);
        list.add(dicts);
        list.add(rates);
        list.add(products);
        list.add(prices);
        list.add(sparePrice);
        list.add(spare);
        list.add(spareProduct);
        list.add(userArea);
        list.add(series);
        list.add(fileSeries);
        list.add(filesPackages);
        list.add(marketList);
        list.add(fileList);

        return list;
    }

    /**
     * 增量
     *
     * 思路：
     * 1.把数据转换成键值对形式 {Key:修改时间}
     * 2.循环对比时间，循环判断是否服务端已删除
     * 3.收集已更改 数据信息, 生成对应APP需要执行的sql
     * 4.组装数据，返回数据到App端
     */
    public Map<String,Object> addSQLiteDb(RequestSubject user,AddDbDataDto addDbDataDto){
        List<AppOfferDto> offers = Lists.newArrayList();
        List<Long> deleteOfferIds = Lists.newArrayList();
//        List<File> files = Lists.newArrayList();
//        List<Integer> deleteFileIds = Lists.newArrayList();

        final Map<String, List<TableInfoDto>> addDbDataDtoTable = addDbDataDto.getTable();
        Map<String,Map<Long,Date>> tableMap = Maps.newHashMap();
        for (String key : addDbDataDtoTable.keySet()) {
            tableMap.put(key, Maps.newHashMap());
            final Map<Long, Date> map = tableMap.get(key);
            final List<TableInfoDto> dtoList = addDbDataDtoTable.get(key);
            for (TableInfoDto infoDto : dtoList) {
                if(infoDto.getId() != null && infoDto.getModifyTime() != null){
                    map.put(infoDto.getId(),infoDto.getModifyTime());
                }
            }
        }
        if(tableMap.get("app_offer") != null && tableMap.get("app_offer").keySet().size() > 0){
            final Map<Long, Date> appOffer = tableMap.get("app_offer");

            final Set<Long> app_offer = tableMap.get("app_offer").keySet();
            final Long[] desc = new Long[app_offer.size()];
            final Long[] integers = app_offer.toArray(desc);
            final List<Long> list = Arrays.asList(integers);

            final List<AppOfferDto> offerList = offerService.findApiOfferListByIds(list, user.getLanguage());

            //判断长度是否一致
            if(offerList.size() == app_offer.size()){
                for (AppOfferDto offer : offerList) {
                    if(offer.getModifyTime() != null
                            && offer.getModifyTime().compareTo(appOffer.get(offer.getId())) > 0){
                        offers.add(offer);
                        deleteOfferIds.add(offer.getId());
                    }
                }
            }else {
                for (AppOfferDto offer : offerList) {
                    if(appOffer.get(offer.getId()) == null){
                        deleteOfferIds.add(offer.getId());
                    }else{
                        if(offer.getModifyTime() != null && offer
                                .getModifyTime().compareTo(appOffer.get(offer.getId())) > 0){
                            deleteOfferIds.add(offer.getId());
                            offers.add(offer);
                        }
                    }
                }
            }
        }
//        else if(tableMap.get("app_files") != null && tableMap.get("app_files").keySet().size() > 0){
//            final Map<Long, Date> app_files = tableMap.get("app_files");
//            final Set<Long> appFiles = tableMap.get("app_files").keySet();
//            final Long[] desc = new Long[appFiles.size()];
//            final Long[] integers = appFiles.toArray(desc);
//            final List<Long> list = Arrays.asList(integers);
//            final List<File> offerList = fileService.findTenFileListByIds(list,user.getId(),user
//                    .getLanguage());
//            //判断长度是否一致
//            if(offerList.size() == appFiles.size()){
//                for (File file : offerList) {
//                    if(file.getUpdateTime() != null && file.getUpdateTime().compareTo(app_files.get(file.getId())) > 0){
//                        files.add(file);
//                        deleteFileIds.add(file.getId());
//                    }
//                }
//            }else {
//                for (File file : offerList) {
//                    if(app_files.get(file.getId()) == null){
//                        deleteFileIds.add(file.getId());
//                    }else{
//                        if(file.getUpdateTime() != null && file.getUpdateTime().compareTo(app_files
//                                .get(file.getId())) > 0){
//                            files.add(file);
//                            deleteFileIds.add(file.getId());
//                        }
//                    }
//                }
//            }
//        }
        //初始化数据
        Map<String,Object> resMap = Maps.newHashMap();
        List<Object> list = createSQLiteDb(user);

        Map<String,Object> offerMap = Maps.newHashMap();
        offerMap.put("tableName", AppConstant.Offer);
        offerMap.put("dataList", offers);
//        Map<String,Object> fileMap = Maps.newHashMap();
//        fileMap.put("tableName", AppConstant.Files);
//        fileMap.put("dataList", files);
        list.add(offerMap);
//        list.add(fileMap);

        List<String> sqlList = Lists.newArrayList();
//        if(deleteFileIds.size() > 0){
//            sqlList.add("delete from app_files where id in ("+ StringUtils.join(deleteFileIds
//                    .toArray(),",") +")");
//        }
        if(deleteOfferIds.size() > 0){
            sqlList.add("delete from app_offer where id in ("+ StringUtils.join
                    (deleteOfferIds.toArray(),",") +")");
        }

        final ArrayList<String> emptyTables = Lists.newArrayList(
                AppConstant.Customer,
                AppConstant.Dict,
                AppConstant.Rate,
                AppConstant.Product,
                AppConstant.ProductPrice,
                AppConstant.SparePrice,
                AppConstant.Spare,
                AppConstant.SpareProduct,
                AppConstant.Series,
                AppConstant.Area,
                AppConstant.FileSeries,
                AppConstant.FilesPackages,
                AppConstant.FileMarket,
                AppConstant.Files);

        resMap.put("emptyTable", emptyTables);
        resMap.put("sql", sqlList);
        resMap.put("initTable", list);
        return resMap;
    }


    /**
     * 获取客户
     */
    private Map<String, Object> findCustomer(RequestSubject user, int lastId){
        List<Customer> customerList = customerService.findMyCustomerAllPage(user.getId(),lastId);

        Map<String,Object> map = Maps.newHashMap();
        List<AppCustomer> customers = Lists.newArrayList();

        for (Customer customer : customerList) {
            AppCustomer appCustomer = new AppCustomer(customer);
            appCustomer.setSyncState(2);
            customers.add(appCustomer);
        }

        map.put("tableName", AppConstant.Customer);
        map.put("dataList", customers);

        return map;
    }


    /**
     * 获取字典
     *
     * 字典类型编码，建议涵盖：
     * 贸易条款，付款方式，发运地址，贸易公司，
     * 这几个类型定义延续WEB后台代码，APP这里代码直接调用
     */
    private Map<String, Object> findDict(RequestSubject user, int lastId){
        List<Dict> dictTermsOfTrade = this.dictService.
                selectDictByCategory(DictCategory.termsOfTrade,user.getLanguage());
        List<Dict> payment = this.dictService.
                selectDictByCategory(DictCategory.paymentMode,user.getLanguage());
        List<Dict> dictTradeAddress = this.dictService.
                selectDictByCategory(DictCategory.tradeAddress,user.getLanguage());
        List<Dict> dictTradeCompany = this.dictService.
                selectDictByCategory(DictCategory.tradeCompany,user.getLanguage());
        List<Dict> productStatus = this.dictService.
                selectDictByCategory(DictCategory.productStatus,user.getLanguage());
        //服务类型
        List<Dict> serviceType = this.dictService.
                selectDictByCategory(DictCategory.serviceType,user.getLanguage());
        dictTermsOfTrade.addAll(payment);
        dictTermsOfTrade.addAll(dictTradeAddress);
        dictTermsOfTrade.addAll(dictTradeCompany);
        dictTermsOfTrade.addAll(productStatus);
        dictTermsOfTrade.addAll(serviceType);

        Map<String,Object> map = Maps.newHashMap();
        List<AppDict> dicts = Lists.newArrayList();

        for (Dict dict : dictTermsOfTrade) {
            dicts.add(new AppDict(dict));
        }
        map.put("tableName", AppConstant.Dict);
        map.put("dataList", dicts);
        return map;
    }

    /**
     * 汇率
     */
    private Map<String, Object> findRate(){
        List<SysExchange> rates = exchangeService.getLastExchange();
        List<Map<String,Object>> maps = Lists.newArrayList();

        rates.forEach(x->{
            Map<String,Object> map = Maps.newHashMap();
            map.put("id", x.getId());
            map.put("code",x.getCode());
            map.put("rate",x.getRmb());
            map.put("symbol",x.getCurrency());
            map.put("symbol2", null); //母币种
            maps.add(map);
        });

        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.Rate);
        map.put("dataList", maps);
        return map;
    }

    /**
     * 产品
     */
    private Map<String, Object> findProduct(RequestSubject user,int lastId){
        List<AppProductDto> list = productService.selectProductAllList(user.getLanguage(),lastId);
        List<AppProduct> products = Lists.newArrayList();
        for(AppProductDto appProductDto : list){
            AppProduct appProduct = appProductDto.toProduct();
            appProduct.setSyncState(2);
            products.add(appProductDto.toProduct());
        }

        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.Product);
        map.put("dataList", products);

        return map;
    }

    /**
     * 配件价格
     */
    private Map<String, Object> findSparePrice(RequestSubject user, int lastId){
        List<DepartmentUser> departmentUserList = departmentUserService.selectList(
                new EntityWrapper<DepartmentUser>().eq("users", user.getId()));
        List<AppSparePrice> sparePrices;
        if (CollectionUtils.isNotEmpty(departmentUserList)) {
            //从最后一个报价区域开始寻找，如果等级为1，则为总部，不取价格，等级为2取改区域价格，等级为3取父区域
            Department department = departmentService.selectById(departmentUserList.get(departmentUserList.size() - 1).getDepartment());
            if (department.getLevel() == 1) {
                sparePrices = Lists.newArrayList();
            }else if (department.getLevel() == 2) {
                sparePrices = spareService.findAppSparesPrice(lastId, user.getLanguage(),department.getId());
            }else {
                sparePrices = spareService.findAppSparesPrice(lastId, user.getLanguage(),department.getParent());
            }
        }else {
            sparePrices = Lists.newArrayList();
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.SparePrice);
        map.put("dataList", sparePrices);
        return map;
    }

    /**
     * 屏体价格
     */
    private Map<String, Object> findProductPrice(RequestSubject user, int lastId){
        List<AppProductPrice> productPrices = priceService.findAppAllProductPrices(user.getId(),
                lastId);

        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.ProductPrice);
        map.put("dataList", productPrices);
        return map;
    }

    /**
     * 配件
     */
    private Map<String, Object> findSpare(RequestSubject user, int lastId){
        List<AppSpares> spares = spareService.findAllAppSpares(user.getLanguage(),lastId);

        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.Spare);
        map.put("dataList", spares);
        return map;
    }

    /**
     * 配件产品关联关系
     */
    private Map<String, Object> findSpareProduct(RequestSubject user, int lastId){
        List<AppSpareProduct> spareProducts = standardService.findAllStandardList(user.getLanguage(),lastId);

        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.SpareProduct);
        map.put("dataList", spareProducts);
        return map;
    }


    /**
     * 区域
     */
    private Map<String, Object> findUserArea(RequestSubject user){
        List<AppAreas> appAreas = departmentService.selectAppArea(user.getId());

        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.Area);
        map.put("dataList", appAreas);
        return map;
    }

    /**
     * 系列
     */
    private Map<String,Object> findSeries(){
        List<AppSeries> list = seriesService.findApiSeriesList();
        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.Series);
        map.put("dataList", list);
        return map;
    }

    /**
     * 文件系列关联表
     */
    private Map<String, Object> findFileSeries(int lastId){
        final List<AppFileSeries> fileSeries = fileSeriesService.selectAppFileSeries(lastId);
        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.FileSeries);
        map.put("dataList", fileSeries);
        return map;
    }

    /**
     * 手机端文件场景表
     */
    private Map<String,Object> findFilesPackages(RequestSubject user){
        final List<AppPackages> apiAllFilePackage = filePackageService.findApiAllFilePackage(user.getLanguage());
        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.Packages);
        map.put("dataList", apiAllFilePackage);
        return map;
    }

    /**
     * 文件细分应用市场
     */
    private Map<String,Object> findApiAppMarketList(){
        final List<AppFileMarket> apiAppMarketList = fileMarketDetailService.findApiAppMarketList();
        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.FileMarket);
        map.put("dataList", apiAppMarketList);
        return map;
    }

    /**
     * 文件
     * @param user
     * @return
     */
    private Map<String,Object> findApiAppFileList(RequestSubject user){
        List<File> fileList = fileService.getAllFileByUserForApp(user.getId());
        List<AppFile> appFileList = new ArrayList<>();
        if(!fileList.isEmpty()){
            for(File file:fileList){
                AppFile appFile = new AppFile(file);
                appFile.setLocal(file.getUrl());
                appFileList.add(appFile);
            }
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("tableName", AppConstant.Files);
        map.put("dataList", appFileList);
        return map;
    }



}

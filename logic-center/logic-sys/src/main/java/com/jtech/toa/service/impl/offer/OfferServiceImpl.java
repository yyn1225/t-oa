/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.offer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.util.CollectionUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.dao.offer.OfferMapper;
import com.jtech.toa.entity.approval.ApprovalTask;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.entity.offer.Formula;
import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.entity.offer.OfferFormula;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.offer.OfferService;
import com.jtech.toa.entity.offer.OfferSpareSelfdefine;
import com.jtech.toa.entity.offer.OfferSpares;
import com.jtech.toa.entity.offer.OfferTransfer;
import com.jtech.toa.entity.offer.TransportPackage;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.system.ApprovalConfig;
import com.jtech.toa.model.dto.offer.AppOfferDto;
import com.jtech.toa.model.dto.offer.AppOfferProductDto;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferListDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.dto.products.PanelDetails;
import com.jtech.toa.model.dto.products.SpareDetails;
import com.jtech.toa.model.query.OfferQuery;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.model.vo.PanelVo;
import com.jtech.toa.service.approval.IApprovalTaskService;
import com.jtech.toa.service.customer.ISalesLevelService;
import com.jtech.toa.service.offer.IFormulaService;
import com.jtech.toa.service.offer.IOfferPanelsService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.offer.IOfferServiceService;
import com.jtech.toa.service.offer.IOfferSpareSelfdefineService;
import com.jtech.toa.service.offer.IOfferSparesService;
import com.jtech.toa.service.offer.IOfferTransferService;
import com.jtech.toa.service.offer.ITransportPackageService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.system.IApprovalConfigService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.service.IDepartmentService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-11-20
 */
@Service
public class OfferServiceImpl extends ServiceImpl<OfferMapper, Offer> implements IOfferService {

    private final IOfferServiceService serviceService;
    private final IOfferSparesService sparesService;
    private final IOfferPanelsService panelsService;
    private final IOfferSpareSelfdefineService selfdefineService;
    private final IApprovalTaskService taskService;
    private final ISalesLevelService levelService;
    private final IBoxService boxService;
    private final IModuleService moduleService;
    private final IParamsService paramsService;
    private final IProductService productService;
    private final IOfferSpareSelfdefineService offerSpareSelfdefineService;
    private final ITransportPackageService transportPackageService;
    private final IOfferSparesService offerSparesService;
    private final IDepartmentService departmentService;
    private final IApprovalConfigService approvalConfigService;
    private final IOfferTransferService offerTransferService;
    private final ITransportPackageService packageService;
    private final IOfferTransferService transferService;
    private final String path;
    private final IFormulaService formulaService;

    @Autowired
    public OfferServiceImpl(
            IOfferServiceService serviceService,
            IOfferSparesService sparesService,
            IOfferPanelsService panelsService,
            IOfferSpareSelfdefineService selfdefineService,
            IApprovalTaskService taskService,
            ISalesLevelService levelService,
            IModuleService moduleService,
            IParamsService paramsService,
            IBoxService boxService,
            IProductService productService,
            IOfferSpareSelfdefineService offerSpareSelfdefineService,
            ITransportPackageService transportPackageService,
            IOfferSparesService offerSparesService,
            IDepartmentService departmentService,
            IApprovalConfigService approvalConfigService,
            IOfferTransferService offerTransferService,
            ITransportPackageService packageService,
            IOfferTransferService transferService,
            OaProperties oaProperties, IFormulaService formulaService) {
        this.serviceService = serviceService;
        this.sparesService = sparesService;
        this.panelsService = panelsService;
        this.selfdefineService = selfdefineService;
        this.taskService = taskService;
        this.levelService = levelService;
        this.boxService = boxService;
        this.moduleService = moduleService;
        this.paramsService = paramsService;
        this.productService = productService;
        this.offerSpareSelfdefineService = offerSpareSelfdefineService;
        this.transportPackageService = transportPackageService;
        this.offerSparesService = offerSparesService;
        this.departmentService = departmentService;
        this.approvalConfigService = approvalConfigService;
        this.offerTransferService = offerTransferService;
        this.packageService = packageService;
        this.transferService = transferService;
        this.path = oaProperties.getMediaPath();
        this.formulaService = formulaService;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Offer save(int userId,String offerJson, OfferVo offerVo, boolean draftFlag) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        Offer offer = offerVo.getOffer();
        //查询报价区域对应的大区
        Department department = departmentService.selectParentById(offer.getArea());
        if (offer.getId() == null) {
            offer.setCreateTime(date);
            offer.setCreater(userId);
            offer.setVersion(format.format(date));
            offer.setDeleteFlag(Offer.DeleteFlag.No);
            offer.setNum(department.getCode() + "-" + format2.format(date) + (int)((Math.random()*9+1)*100000));
        } else {
            offer.setUpdateTime(date);
            offer.setUpdater(userId);
            //编辑删除关联表,重新创建
            panelsService.delete(new EntityWrapper<OfferPanels>().eq("offer", offer.getId()));
            offerSparesService.delete(new EntityWrapper<OfferSpares>().eq("offer", offer.getId()));
            offerSpareSelfdefineService.delete(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offer.getId()));
            serviceService.delete(new EntityWrapper<OfferService>().eq("offer", offer.getId()));
            transportPackageService.delete(new EntityWrapper<TransportPackage>().eq("offer", offer.getId()));
            formulaService.delete(new EntityWrapper<Formula>().eq("offer", offer.getId()));
        }
        offer.setOfferJson(offerJson);
        boolean needApproval;
        if (!draftFlag) {
            Level level;
            if(null!=offer.getCustomer()){
                level = levelService.findByCustomerId(offer.getCustomer());
            }else{
                level=null;
            }
            if(null==level){
                level = new Level();
                level.setDiscount(new BigDecimal(100));
            }
            needApproval = taskService.needApproval(offerVo, offer.getArea(), level);

            if(needApproval){
                offer.setStatus(Offer.Status.Submited);
            }else{
                offer.setStatus(Offer.Status.Approved);
            }
        }else {
            needApproval = false;
            offer.setStatus(Offer.Status.Draft);
        }
        //报价单基础信息
        if(!offer.insertOrUpdate()) {
            throw new RuntimeException();
        }
        if(needApproval){
            List<ApprovalTask> approvalTasks = taskService.selectList(new EntityWrapper<ApprovalTask>()
                    .eq("orders", offer.getId()));
            if(approvalTasks != null && approvalTasks.size() > 0){
                taskService.deleteBatchIds(approvalTasks);
            }
            if(!createdApprovaler(offer,offer.getArea())){
                throw new RuntimeException();
            }
        }

        //保存屏体相关信息
        for(PanelVo panelVo:offerVo.getPanelList()){
            panelVo.setOffer(offer.getId());
            OfferPanels panel = panelVo.getPanels();
            panel.setOffer(offer.getId());
            if(!panelsService.insert(panel)){
                throw new RuntimeException();
            }

            //保存子屏
            for (OfferPanels childPanels : panel.getChildPanels()) {
                childPanels.setOffer(offer.getId());
                childPanels.setSplitScreenParent(panel.getId());
                if(!panelsService.insert(childPanels)){
                    throw new RuntimeException();
                }
            }

            List<OfferSpares> sparesList = Lists.newArrayList();
            panelVo.getStandardList().forEach(spare->{
                spare.setOffer(offer.getId());
                spare.setPanel(panel.getId());
                spare.setType(OfferSpares.Type.Standard);
                sparesList.add(spare);
            });
            panelVo.getFreeList().forEach(spare->{
                spare.setOffer(offer.getId());
                spare.setPanel(panel.getId());
                spare.setType(OfferSpares.Type.Free);
                sparesList.add(spare);
            });
            panelVo.getSpareList().forEach(spare->{
                spare.setOffer(offer.getId());
                spare.setPanel(panel.getId());
                spare.setType(OfferSpares.Type.Spare);
                sparesList.add(spare);
            });

            //保存配件和公式
            for(OfferSpares spare:sparesList){
                if(!sparesService.insert(spare)){
                    throw new RuntimeException();
                }

                if (CollectionUtil.isNotEmpty(spare.getFormula())) {
                    for (OfferFormula offerFormula : spare.getFormula()) {
                        Formula formula = offerFormula.buildFormula();
                        formula.setSpare(spare.getId());
                        formula.setOffer(offer.getId());
                        if (!formula.insert()) {
                            throw new RuntimeException();
                        }
                    }
                }
            }

            List<OfferSpareSelfdefine> selfdefineList = Lists.newArrayList();
            panelVo.getSelfStandList().forEach(spare->{
                spare.setOffers(offer.getId());
                spare.setPanel(panel.getId());
                spare.setType(OfferSpares.Type.Standard);
                selfdefineList.add(spare);
            });
            panelVo.getSelfFreeList().forEach(spare->{
                spare.setOffers(offer.getId());
                spare.setPanel(panel.getId());
                spare.setType(OfferSpares.Type.Free);
                selfdefineList.add(spare);
            });
            panelVo.getSelfSpareList().forEach(spare->{
                spare.setOffers(offer.getId());
                spare.setPanel(panel.getId());
                spare.setType(OfferSpares.Type.Spare);
                selfdefineList.add(spare);
            });
            for(OfferSpareSelfdefine spare:selfdefineList){
                if(!selfdefineService.insert(spare)){
                    throw new RuntimeException();
                }
            }
        }

        for(OfferService service:offerVo.getServiceList()){
            service.setOffer(offer.getId());
            if(!serviceService.insert(service)){
                throw new RuntimeException();
            }
        }

        OfferTransfer transfer = offerVo.getTransfer();
        transfer.setOrders(offer.getId());
        transfer.setStatus(OfferTransfer.Status.Confirmed);
        transfer.setSubmitTime(date);
        transfer.setSubmiter(offer.getCreater());
        if(!transfer.insertOrUpdate()){
            throw new RuntimeException();
        }
        List<TransportPackage> packages = offerVo.getTransport();
        for(TransportPackage pack:packages){
            pack.setOffer(offer.getId());
            pack.setTransfer(transfer.getId());
            if(!pack.insert()){
                throw new RuntimeException();
            }
        }
        //判断二维码是否存在，不存在则创建二维码
        File file = new File(path + offer.getNum() + ".png");
        if (!file.exists()) {
            String fileName = offer.getNum() + ".png";
            String content = offer.getNum();
            // 图像宽度
            int width = 300;
            // 图像高度
            int height = 300;
            // 图像类型
            String imgFormat = "png";
            Map<EncodeHintType, Object> hints = Maps.newHashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //设置白边间距，数字越大越高
            hints.put(EncodeHintType.MARGIN, 1);
            //设置容错率，越高二维码越复杂，识别度越高
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            try {
                // 生成矩阵
                BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                Path filePath = FileSystems.getDefault().getPath(path, fileName);
                // 输出图像
                MatrixToImageWriter.writeToPath(bitMatrix, imgFormat, filePath);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("输出成功.");
        }

        return offer;
    }

    /**
     * 创建创建审批信息
     * @param offer 审批单数据
     * @param area 地区
     * @return 是否插入成功
     */
    private boolean createdApprovaler(Offer offer,int area){
        Date date = new Date();
        ApprovalConfig approvalConfig = approvalConfigService.selectOne(new
                EntityWrapper<ApprovalConfig>().eq("area",area));
        if(approvalConfig == null){
            return true;
        }
//        List<User> userList = userService.findUsersByRole(approvalConfig.getAuditor());
//        for(User user : userList){
            ApprovalTask task = new ApprovalTask();
            if (offer.getCreater() != null) {
                task.setCreater(offer.getCreater());
            }else {
                task.setCreater(offer.getUpdater());
            }
            task.setOptUser(approvalConfig.getAuditor());
            task.setCreateTime(date);
            task.setOrders(offer.getId());
            task.setOptStatus(ApprovalTask.Status.WAIT);
            taskService.insert(task);
//        }
        return true;
    }

    @Override
    public OfferVo getOfferDetails(long offerId, String lang) {
        List<PanelDetails> panelDetails = Lists.newArrayList();
        List<OfferPanels> panels = panelsService.selectList(new EntityWrapper<OfferPanels>().eq("offer",offerId));
        List<OfferPanels> newPanels = Lists.newArrayList();
        //遍历所有的屏体，找出父屏体
        for (OfferPanels newPanel : panels) {
            if (newPanel.getSplitScreenParent() == null) {
                newPanels.add(newPanel);

            }
        }
        for(OfferPanels panel: newPanels) {
            //构建屏体父子关系
            List<OfferPanels> newChildPanels = Lists.newArrayList();
            for (OfferPanels offerPanels : panels) {
                if (panel.getId().equals(offerPanels.getSplitScreenParent())) {
                    Product product  = productService.selectByIdAndLang(offerPanels.getPanel(), lang);
                    Box box = boxService.selectByIdAndLang(product.getBox(), lang);
                    Params params = paramsService.selectParamsByProductId(offerPanels.getPanel());
                    Module module = moduleService.selectByIdAndLang(box.getModual(), lang);
                    offerPanels.setProduct(product);
                    offerPanels.setBox(box);
                    offerPanels.setParams(params);
                    offerPanels.setModule(module);
                    newChildPanels.add(offerPanels);

                }
            }
            panel.setChildPanels(newChildPanels);

            PanelDetails vo = new PanelDetails();
//            Product product  = productService.selectByIdAndLang(panel.getPanel(), lang);
//            Box box = boxService.selectByIdAndLang(product.getBox(), lang);
            vo.setOfferPanels(panel);
//            vo.setProduct(product);
//            vo.setBox(box);
//            vo.setParams(paramsService.selectParamsByProductId(panel.getPanel()));
//            vo.setModule(moduleService.selectByIdAndLang(box.getModual(), lang));
            List<OfferFormula> formulaList = formulaService.selectFormulaByOffer(offerId);
            //查询标配
            List<SpareDetails> standardList = sparesService.getSpareListByOffer(offerId,panel.getId(), OfferSpares.Type.Standard, lang);
            //查询选配
            List<SpareDetails> spareList = sparesService.getSpareListByOffer(offerId,panel.getId(), OfferSpares.Type.Spare, lang);
            //查询免费
            List<SpareDetails> freeList = sparesService.getSpareListByOffer(offerId,panel.getId(), OfferSpares.Type.Free, lang);

            //构建标配公式
            for (SpareDetails standard : standardList) {
                List<OfferFormula> stadardFormulas = Lists.newArrayList();
                for (OfferFormula formula : formulaList) {
                    if(formula.getOfferSpare().equals(standard.getOfferSpareId())) {
                        stadardFormulas.add(formula);
                    }
                }
                standard.setFormula(stadardFormulas);
                standard.setFormulaJSON(JSON.toJSONString(stadardFormulas));
            }
            //构建选配公式
            for (SpareDetails spare : spareList) {
                List<OfferFormula> spareFormulas = Lists.newArrayList();
                for (OfferFormula formula : formulaList) {
                    if(formula.getOfferSpare().equals(spare.getOfferSpareId())) {
                        spareFormulas.add(formula);
                    }
                }
                spare.setFormula(spareFormulas);
                spare.setFormulaJSON(JSON.toJSONString(spareFormulas));
            }
//            //构建免费公式
            for (SpareDetails free : freeList) {
                List<OfferFormula> freeFormulas = Lists.newArrayList();
                for (OfferFormula formula : formulaList) {
                    if(formula.getOfferSpare().equals(free.getOfferSpareId())) {
                        freeFormulas.add(formula);
                    }
                }
                free.setFormula(freeFormulas);
                free.setFormulaJSON(JSON.toJSONString(freeFormulas));
            }

            vo.setStandardDetailList(standardList);
            vo.setSpareDetailList(spareList);
            vo.setFreeDetailList(freeList);
            //查询所有自定义配件
            vo.setSelfStandardList(offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("panel",panel.getId()).eq("type", OfferSpares.Type.Standard)));
            vo.setSelfSpareList(offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("panel",panel.getId()).eq("type", OfferSpares.Type.Spare)));
            vo.setSelfFreeList(offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("panel",panel.getId()).eq("type", OfferSpares.Type.Free)));
            panelDetails.add(vo);
        }
        OfferVo offerVo=new OfferVo();
        Offer offer = baseMapper.selectById(offerId);
        offerVo.setServiceList(serviceService.selectList(new EntityWrapper<OfferService>().eq("offer",offerId)));
        offerVo.setTransfer(transferService.selectOne(new EntityWrapper<OfferTransfer>().eq("orders",offerId)));
        offerVo.setTransport(packageService.selectList(new EntityWrapper<TransportPackage>().eq("offer",offerId)));
        offerVo.setOffer(offer);
        offerVo.setPanelDetailList(panelDetails);
        return offerVo;
    }

    @Override
    public void selectListByManager(Page<OfferListDto> requestPage, OfferQuery query) {
        List<OfferListDto> offerListDtoList = baseMapper.selectListByManager(requestPage, query);
        requestPage.setRecords(offerListDtoList);
    }

    @Override
    public void selectListByPage(Page<OfferListDto> requestPage, OfferQuery query) {
        List<OfferListDto> offerListDtoList = baseMapper.selectListByPage(requestPage, query);
        requestPage.setRecords(offerListDtoList);
    }

    @Override
    public void selectPreferenceListByPage(Page<OfferListDto> requestPage, OfferQuery query) {
        List<OfferListDto> offerListDtoList = baseMapper.selectPreferenceListByPage(requestPage, query);
        requestPage.setRecords(offerListDtoList);
    }

    @Override
    public List<OfferListDto> selectOfferList(OfferQuery query) {
        return baseMapper.selectOfferList(query);
    }

    @Override
    public List<MyOfferDto> selectMyOfferList(int userId) {
        return baseMapper.selectMyOfferList(userId);
    }

    @Override
    public List<MyOfferDto> getMyOfferList(int userId, long lastId,int status) {
        SapRate rate= new SapRate();
        List<MyOfferDto> offerDtos = baseMapper.getMyOfferList(userId,lastId,status);
        for(MyOfferDto dto:offerDtos){
            dto.setMoneyType(rate.getKeyCode().get(dto.getMoneyUnit()));
        }
        return offerDtos;
    }

    @Override
    public MyOfferDto selectOfferById(Long offerId) {
        return baseMapper.selectOfferById(offerId);
    }

    @Override
    public MyOfferDto findOfferById(Long offerId, String lang) {
        MyOfferDto myOfferDto = baseMapper.findOfferById(offerId, lang);
        if(myOfferDto != null){
            myOfferDto.setSizeUnit(StringUtils.trim(myOfferDto.getSizeUnit()));
        }
        return myOfferDto;
    }

    @Override
    public void selectApprovalList(OfferQuery query, Page<OfferListDto> requestPage) {
        List<OfferListDto> myOfferDtoList = baseMapper.selectApprovalList(query, requestPage);
        requestPage.setRecords(myOfferDtoList);
    }

    @Override
    public void selectLaunchList(OfferQuery query, Page<OfferListDto> requestPage) {
        List<OfferListDto> offerListDtoList = baseMapper.selectLaunchList(query, requestPage);
        requestPage.setRecords(offerListDtoList);
    }

    @Override
    public List<MyOfferDto> selectMyApprovalList(Integer userId) {
        return baseMapper.selectMyApprovalList(userId);
    }

    @Override
    public List<AppOfferDto> findApiOfferListByPage(int userId,
                                                    long lastId,
                                                    int pageSize,
                                                    Integer status,
                                                    String lang) {
        return baseMapper.findApiOfferListByPage(userId,lastId,pageSize,status);
    }
    @Override
    public List<AppOfferDto> findApiOfferListByIds(List<Long> ids, String lang) {
        return baseMapper.findApiOfferListByIds(ids, lang);
    }

    /**
     * 把相关字段 放到Offerr详情字段
     */
    private void offerHandle(List<AppOfferDto> offerDtos, String lang){
        List<Long> ids = Lists.newArrayList();
        Map<Long, Map<String,List<Object>>> offerKeyMap = Maps.newHashMap();
        for(AppOfferDto offerDto : offerDtos){
            ids.add(offerDto.getId());
            offerKeyMap.put(offerDto.getId(),Maps.newHashMap());
        }
        final List<AppOfferProductDto> appOfferProductDtos = this.panelsService.selectApiListByOfferData(ids, lang);
        appOfferProductDtos.forEach(pro -> {
            if(null == offerKeyMap.get(pro.getOffer()).get("panel")){
                offerKeyMap.get(pro.getOffer()).put("panel",Lists.newArrayList(pro));
            }else {
                offerKeyMap.get(pro.getOffer()).get("panel").add(pro);
            }
        });
        final List<OfferService> services = serviceService.selectList(new EntityWrapper<OfferService>().in("offer", ids));
        services.forEach(s -> {
            if(null == offerKeyMap.get(s.getOffer()).get("service")){
                offerKeyMap.get(s.getOffer()).put("service",Lists.newArrayList(s));
            }else {
                offerKeyMap.get(s.getOffer()).get("service").add(s);
            }
        });
        final List<OfferSpareSelfdefine> selfdefines = offerSpareSelfdefineService.selectList(new
                EntityWrapper<OfferSpareSelfdefine>().in("offers", ids));
        selfdefines.forEach(s -> {
            if(null == offerKeyMap.get(s.getOffers()).get("selfdefine")){
                offerKeyMap.get(s.getOffers()).put("selfdefine",Lists.newArrayList(s));
            }else {
                offerKeyMap.get(s.getOffers()).get("selfdefine").add(s);
            }
        });
        final List<OfferSpares> spares = offerSparesService.selectList(new EntityWrapper<OfferSpares>().in("offer", ids));
        spares.forEach(s -> {
            if(null == offerKeyMap.get(s.getOffer()).get("spare")){
                offerKeyMap.get(s.getOffer()).put("spare",Lists.newArrayList(s));
            }else {
                offerKeyMap.get(s.getOffer()).get("spare").add(s);
            }
        });
        final List<OfferTransfer> translates = offerTransferService.selectList(new
                EntityWrapper<OfferTransfer>().in("orders", ids));
        translates.forEach(s -> {
            if(null == offerKeyMap.get(s.getOrders()).get("translates")){
                offerKeyMap.get(s.getOrders()).put("translates",Lists.newArrayList(s));
            }else {
                offerKeyMap.get(s.getOrders()).get("translates").add(s);
            }
        });
        final List<TransportPackage> transportPackages = transportPackageService.selectList(new EntityWrapper<TransportPackage>().in("offer", ids));
        transportPackages.forEach(s -> {
            if(null == offerKeyMap.get(s.getOffer()).get("package")){
                offerKeyMap.get(s.getOffer()).put("package",Lists.newArrayList(s));
            }else {
                offerKeyMap.get(s.getOffer()).get("package").add(s);
            }
        });
        for(AppOfferDto dto : offerDtos){
            dto.setDetails(offerKeyMap.get(dto.getId()));
            final Map<String, List<Object>> stringListMap = offerKeyMap.get(dto.getId());
            stringListMap.computeIfAbsent("product", k -> Lists.newArrayList());
            stringListMap.computeIfAbsent("service", k -> Lists.newArrayList());
            stringListMap.computeIfAbsent("selfdefine", k -> Lists.newArrayList());
            stringListMap.computeIfAbsent("spare", k -> Lists.newArrayList());
            stringListMap.computeIfAbsent("translates", k -> Lists.newArrayList());
            stringListMap.computeIfAbsent("package", k -> Lists.newArrayList());
        }
    }

    @Override
    public List<OfferListDto> selectAppPreferenceListByPage(OfferQuery query, long lastId) {
        return baseMapper.selectAppPreferenceListByPage(query, lastId);
    }

    @Override
    public File createExcelFile(OfferVo offerVo,Department department,String lang,String filepath){
        List<String> msg;
        File file;
        if ("CHN".equals(department.getCode())) {
            file = new File(filepath + "CHN.xlsx");
            msg = Lists.newArrayList();
        }else {
            if ("en".equals(lang)) {
                file = new File(filepath + "en.xlsx");
                msg = Lists.newArrayList("Quotation of Absen-A2712 Display", "Screen Area Dimension", "Unit Price", "free",
                        "Recommended Spare parts --Optional", "Service", "Transport", "Total", "Remarks:", "1. Quotation valid time: 30 days from quotation date.",
                        "2. Packing: ", "T/T 30% down payment, T/T 70% balance before delivery", "T/T 0% down payment, T/T 100% balance before delivery",
                        "3. Payment method: ", "4. Production lead time:7 days from receiving down payment.",
                        "5. Quality Warranty Period: " + offerVo.getOffer().getGuarantee() + " years from delivery date.");
            } else {
                file = new File(filepath + "zh.xlsx");
                msg = Lists.newArrayList("艾比森-A2712 显示屏报价", "屏体尺寸", "单价", "免费", "备件 --可选", "服务", "物流", "总计", "备注：",
                        "1. 报价有效期：30日内有效。", "2. 包装：", "预付30%定金, 70%余款需在交货前付清。", "无需预付定金，款项需在交货前付清。", "3. 支付方式：",
                        "4. 开始生产日期：收到首付款7日内。", "5. 质保期: 交货期起" + offerVo.getOffer().getGuarantee() + "年。");
            }
        }
        return file;
    }

    @Override
    public OfferVo getOfferDetailsExport(long offerId, String lang) {
        List<PanelDetails> panelDetails = Lists.newArrayList();
        //屏体 主数据
        List<OfferPanels> panels = panelsService.selectList(new EntityWrapper<OfferPanels>().eq("offer",offerId).isNull("split_screen_parent").isNull("panel"));
//        List<Product> productList = Lists.newArrayList();
//        List<Box> boxList =  Lists.newArrayList();
        for(OfferPanels panel:panels){

            PanelDetails vo = new PanelDetails();
//            List<OfferPanels> panelsParent = panelsService.selectList(
//                new EntityWrapper<OfferPanels>().eq("offer", offerId)
//                    .eq("split_screen_parent", panel.getId()));

            List<OfferPanelsDto> offerPanelsDtos = panelsService
                .selectDtoList(offerId, panel.getId());
            if (offerPanelsDtos.size() > 0) {
                for (OfferPanelsDto o : offerPanelsDtos) {
                    Product productPanel = productService.selectByIdAndLang(o.getPanel(), lang);
                    Box boxProduct = boxService.selectByIdAndLang(productPanel.getBox(), lang);
                    o.setParams(paramsService.selectByIdAndLang(o.getPanel(), lang));
                    o.setProduct(productPanel);
                    o.setBox(boxProduct);
                }
            }
            Product product  = productService.selectByIdAndLang(offerPanelsDtos.get(0).getPanel(), lang);
            Box box = boxService.selectByIdAndLang(product.getBox(), lang);
            vo.setOfferPanels(panel);
            vo.setProduct(product);
            vo.setBox(box);
            vo.setSplicingPanelsDto(offerPanelsDtos);
            vo.setOfferPanels(panel);
            vo.setParams(paramsService.selectByIdAndLang(offerPanelsDtos.get(0).getPanel(), lang));
            vo.setModule(moduleService.selectByIdAndLang(box.getModual(), lang));
            vo.setStandardDetailList(sparesService.getSpareListByOffer(offerId,panel.getId(), OfferSpares.Type.Standard, lang));
            vo.setSpareDetailList(sparesService.getSpareListByOffer(offerId,panel.getId(), OfferSpares.Type.Spare, lang));
            vo.setFreeDetailList(sparesService.getSpareListByOffer(offerId,panel.getId(), OfferSpares.Type.Free, lang));
            vo.setSelfStandardList(offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("panel",panel.getId()).eq("type", OfferSpares.Type.Standard)));
            vo.setSelfSpareList(offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("panel",panel.getId()).eq("type", OfferSpares.Type.Spare)));
            vo.setSelfFreeList(offerSpareSelfdefineService.selectList(new EntityWrapper<OfferSpareSelfdefine>().eq("offers", offerId).eq("panel",panel.getId()).eq("type", OfferSpares.Type.Free)));
            panelDetails.add(vo);
        }
        OfferVo offerVo=new OfferVo();
        Offer offer = baseMapper.selectById(offerId);
        offerVo.setServiceListDto(serviceService.selectListById(offerId,lang));
        offerVo.setTransfer(transferService.selectOne(new EntityWrapper<OfferTransfer>().eq("orders",offerId)));
        offerVo.setTransport(packageService.selectList(new EntityWrapper<TransportPackage>().eq("offer",offerId)));
        offerVo.setOffer(offer);
        offerVo.setPanelDetailList(panelDetails);
        return offerVo;
    }
}

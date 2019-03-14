package com.jtech.toa.service.impl.approval;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.SqlUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import com.jtech.toa.dao.ApprovalTaskMapper;
import com.jtech.toa.entity.approval.ApprovalTask;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.offer.OfferService;
import com.jtech.toa.entity.offer.OfferSpares;
import com.jtech.toa.entity.system.ApprovalConfig;
import com.jtech.toa.model.dto.sys.ApprovalTaskDto;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.model.vo.PanelVo;
import com.jtech.toa.service.approval.IApprovalTaskService;
import com.jtech.toa.service.system.IApprovalConfigService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.service.IDepartmentService;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-11-29
 */
@Service
public class ApprovalTaskServiceImpl extends ServiceImpl<ApprovalTaskMapper, ApprovalTask> implements IApprovalTaskService {
    @Autowired
    IApprovalConfigService configService;

    @Autowired
    IDepartmentService departmentService;

    @Autowired
    IApprovalTaskService approvalTaskService;

    private final static double areaChange = 0.092903;
    private final static double lengthChange = 0.3048;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRetract(Long id) {
        boolean ok = true;
        ApprovalTask approvalTask = selectById(id);
        if (ApprovalTask.Status.RETRACT.equals(approvalTask.getOptStatus())) {
            return false;
        }
        approvalTask.setOptStatus(ApprovalTask.Status.RETRACT);
        ok = updateById(approvalTask);
        Offer offer = new Offer();
        offer.setId(approvalTask.getOrders().longValue());
        offer.setStatus(Offer.Status.Withdraw);
        ok = offer.updateById();
        return ok;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateExamine(long approvalId, Integer status, String opinion) {
        boolean ok = true;
        ApprovalTask approvalTask = selectById(approvalId);
        if (ApprovalTask.Status.ALREADY.equals(approvalTask.getOptStatus())) {
            return false;
        }
        approvalTask.setOptStatus(ApprovalTask.Status.ALREADY);
        approvalTask.setOptResult(status);
        approvalTask.setOpinion(opinion);
        ok = updateById(approvalTask);
        Offer offer = new Offer();
        offer.setId(approvalTask.getOrders().longValue());
        if (ApprovalTask.Result.Agree.equals(status)) {
            offer.setStatus(Offer.Status.Approved);
        }
        if (ApprovalTask.Result.DisAgree.equals(status)) {
            offer.setStatus(Offer.Status.Rejected);
        }
        ok = offer.updateById();
        return ok;
    }

    @Override
    public boolean needApproval(OfferVo offerVo, int area, Level level) {
        ApprovalConfig config = configService.selectOne(new EntityWrapper<ApprovalConfig>().eq("area", area));
        if (null == config || config.getApproval().equals(0)) { //没有配置或者配置不需要审批，直接跳过
            return false;
        }
        int condition = config.getCondition();
        switch (condition) {
            case 1:
                return true;
            case 2:
                return checkCostPrice(offerVo);
            case 3:
                return checkCustomerDiscount(offerVo, level);
            case 4:
                return checkSuggestPrice(offerVo);
            default:
                return false;
        }
    }

    /**
     * 检查报价单的成本价是否比总报价高。如果是，说明报价过低，需要审批
     */
    private boolean checkCostPrice(OfferVo offerVo) {
        List<PanelVo> panelList = offerVo.getPanelList();
        double areaSize = offerVo.getOffer().getSizeUnit().equals("1") ? 1 : areaChange;
        double totalPrice = 0;
        for (PanelVo panelVo : panelList) {
            OfferPanels panel = panelVo.getPanels();
            double area = panel.getHorizontal().multiply(panel.getLongitudinal()).doubleValue() * areaSize;
            totalPrice += panel.getCostPrice().doubleValue() * area;  //屏体成本价

            List<OfferSpares> spares = panelVo.getStandardList();
            if (null == spares) {
                spares = Lists.newArrayList();
            }
            spares.addAll(panelVo.getSpareList()); //选配和标配可以放在一起计算
            for (OfferSpares spare : spares) {
                int real = spare.getCountReal();
                double cost = spare.getPriceCost().doubleValue();
                totalPrice += real * cost;
            }

            for (OfferSpares spare : panelVo.getFreeList()) { //免费需要将指导数量去掉，计算多出来的价格
                int real = spare.getCountReal() - spare.getCountGuid();
                if (real <= 0) {
                    continue;
                }
                double cost = spare.getPriceCost().doubleValue();
                totalPrice += real * cost;
            }
        }

        List<OfferService> services = offerVo.getServiceList();
        for (OfferService service : services) {
            totalPrice += service.getCounts() * service.getPrice().doubleValue();
        }
        //成本价 > 报价单销售总价，则说明价格报低了，需要审批
        return totalPrice >= offerVo.getOffer().getTotalPrice().doubleValue();
    }

    /**
     * 检查报价单的客户折扣后金额是否比报价单的总价高
     */
    private boolean checkCustomerDiscount(OfferVo offerVo, Level level) {
        double totalPrice = getTotalSuggestPrice(offerVo);
        double discount = level.getDiscount().doubleValue();
        //建议销售价格*客户折扣 > 报价单销售总价，则说明价格报低了，需要审批
        return (totalPrice * discount / 100) > offerVo.getOffer().getTotalPrice().doubleValue();
    }

    /**
     * 判断建议销售价是否比报价高
     */
    private boolean checkSuggestPrice(OfferVo offerVo) {
        double totalPrice = getTotalSuggestPrice(offerVo);
        //建议销售价格*客户折扣 > 报价单销售总价，则说明价格报低了，需要审批
        return totalPrice > offerVo.getOffer().getTotalPrice().doubleValue();
    }

    /**
     * 获取报价单中所有的屏体、备件、服务费用的建议销售价格合计
     */
    private double getTotalSuggestPrice(OfferVo offerVo) {
        List<PanelVo> panelList = offerVo.getPanelList();
        double areaSize = offerVo.getOffer().getSizeUnit().equals("1") ? 1 : areaChange;
        double totalPrice = 0;
        for (PanelVo panelVo : panelList) {
            OfferPanels panel = panelVo.getPanels();
            double area = panel.getHorizontal().multiply(panel.getLongitudinal()).doubleValue() * areaSize;
            totalPrice += panel.getSuggPrice().doubleValue() * area;  //屏体成本价

            List<OfferSpares> spares = panelVo.getStandardList();
            if (null == spares) {
                spares = Lists.newArrayList();
            }
            spares.addAll(panelVo.getSpareList()); //选配和标配可以放在一起计算
            for (OfferSpares spare : spares) {
                int real = spare.getCountReal();
                double cost = spare.getPriceGuide().doubleValue();
                totalPrice += real * cost;
            }

            for (OfferSpares spare : panelVo.getFreeList()) { //免费需要将指导数量去掉，计算多出来的价格
                int real = spare.getCountReal() - spare.getCountGuid();
                if (real <= 0) {
                    continue;
                }
                double cost = spare.getPriceGuide().doubleValue();
                totalPrice += real * cost;
            }
        }

        List<OfferService> services = offerVo.getServiceList();
        for (OfferService service : services) {
            totalPrice += service.getCounts() * service.getPrice().doubleValue();
        }
        return totalPrice;
    }

    @Override
    @Transactional
    public boolean saveApproval(Offer offer,int area){
        ApprovalTask task = new ApprovalTask();
        task.setCreater(offer.getCreater());
        task.setCreateTime(new Date());
        task.setOrders(offer.getId());
        task.setOptStatus(ApprovalTask.Status.WAIT);
        Department department=departmentService.selectById(area);
        if(null!=department){
            task.setOptUser(department.getLeader());
        }
        if(!task.insert()){
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public List<ApprovalTaskDto> selectApprovalList(Long lastId, String status, int userId,String name) {
        if(StringUtils.isNotEmpty(name)){
            name = SqlUtils.concatLike(name, SqlLike.DEFAULT);
        }
        return baseMapper.selectApprovalList(lastId, status, userId,name);
    }

    @Override
    public List<ApprovalTaskDto> selectMyApproval(Long lastId, String status, int userId) {
        return baseMapper.selectMyApproval(lastId, status, userId);
    }

    @Override
    @Transactional
    public boolean upTaskStatus(Long approvalId, Integer state, String opinion){
        ApprovalTask approvalTask1 = approvalTaskService.selectById(approvalId);
        List<ApprovalTask> approvalTasks = approvalTaskService.selectList(new
                EntityWrapper<ApprovalTask>().eq("orders", approvalTask1.getOrders()));
        for(ApprovalTask task: approvalTasks){
            task.setOptStatus(ApprovalTask.Status.ALREADY);
            if(task.getId() != null && task.getId().compareTo(approvalId) == 0){
                task.setOptResult(state);
                task.setOpinion(opinion);
            }
        }
        return approvalTaskService.updateBatchById(approvalTasks);
    }
}

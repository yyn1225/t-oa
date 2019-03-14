package com.jtech.toa.service.impl.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.SparePriceDetails;
import com.jtech.toa.entity.prices.SparePriceSystem;
import com.jtech.toa.dao.prices.SparePriceSystemMapper;
import com.jtech.toa.model.dto.prices.PriceSystemDto;
import com.jtech.toa.service.prices.ISparePriceAssignService;
import com.jtech.toa.service.prices.ISparePriceDetailsService;
import com.jtech.toa.service.prices.ISparePriceSystemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2018-03-22
 */
@Service
public class SparePriceSystemServiceImpl extends ServiceImpl<SparePriceSystemMapper, SparePriceSystem> implements ISparePriceSystemService {

    private final ISparePriceDetailsService sparePriceDetailsService;
    private final ISparePriceAssignService sparePriceAssignService;
    @Autowired
    public SparePriceSystemServiceImpl(ISparePriceDetailsService sparePriceDetailsService,
                                       ISparePriceAssignService sparePriceAssignService) {
        this.sparePriceDetailsService = sparePriceDetailsService;
        this.sparePriceAssignService  = sparePriceAssignService;
    }

    @Override
    public void findListByPage(Page<PriceSystemDto> page) {
        List<PriceSystemDto> priceSystemList = baseMapper.findListByPage(page);
        page.setRecords(priceSystemList);
    }

    @Override
    public boolean deleteSparePriceSystems(int id, List<SparePriceDetails> sparePricesDetailsList) throws Exception {
        boolean ok = true;
        if (CollectionUtils.isNotEmpty(sparePricesDetailsList)) {
            for (SparePriceDetails pricesDetails : sparePricesDetailsList) {
                ok = sparePriceDetailsService.deleteById(pricesDetails.getId());
            }
        }
        if (!ok) {
            throw new Exception("删除价格明细失败");
        }

         ok = sparePriceAssignService.deleteBySystemId(id);
        SparePriceSystem priceSystem = selectById(id);
        if (priceSystem == null) {
            throw new Exception("操作异常");
        }
        ok = deleteById(id);
        return ok;
    }

    @Override
    public boolean editPrice(List<SparePriceDetails> sparePricesDetailsList, SparePriceSystem priceSystem, int userId, int area,int id) {
        boolean ok = false;
        if (CollectionUtils.isNotEmpty(sparePricesDetailsList)) {
            for (SparePriceDetails pricesDetails : sparePricesDetailsList) {
                if (pricesDetails.getSystems() == null){
                    pricesDetails.setSystems(priceSystem.getId());
                    pricesDetails.setUnit(priceSystem.getUnit());
                    ok = sparePriceDetailsService.insert(pricesDetails);
                }else {
                    ok = sparePriceDetailsService.updateById(pricesDetails);
                }
            }
        }
        return ok;
    }
   @Override
    public boolean savePrice(List<SparePriceDetails> sparePricesDetailsList, SparePriceSystem priceSystem, int userId, int area) {
        boolean ok ;
        //新增类别
        priceSystem.setCreater(userId);
        priceSystem.setCreateTime(new Date());
        priceSystem.setArea(area);
        ok = priceSystem.insert();
        if (CollectionUtils.isNotEmpty(sparePricesDetailsList)) {
            for (SparePriceDetails pricesDetails : sparePricesDetailsList) {
                pricesDetails.setSystems(priceSystem.getId());
                ok = sparePriceDetailsService.insert(pricesDetails);
            }
        }
        return ok;
    }
}

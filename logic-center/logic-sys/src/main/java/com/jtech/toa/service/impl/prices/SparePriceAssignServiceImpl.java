package com.jtech.toa.service.impl.prices;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.prices.SparePriceAssign;
import com.jtech.toa.dao.prices.SparePriceAssignMapper;
import com.jtech.toa.model.dto.prices.PriceAssignDto;
import com.jtech.toa.service.prices.ISparePriceAssignService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class SparePriceAssignServiceImpl extends ServiceImpl<SparePriceAssignMapper, SparePriceAssign> implements ISparePriceAssignService {

    @Override
    public void findAssignList(Page<PriceAssignDto> requestPage, int systems) {
        List<PriceAssignDto> priceAssignList = baseMapper.findAssignList(requestPage, systems);
        requestPage.setRecords(priceAssignList);
    }

    @Override
    public boolean deleteBySystemId(Integer systems){
       return  baseMapper.deleteBySystemId(systems);
    }
}

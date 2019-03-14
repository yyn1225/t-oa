package com.jtech.toa.service.impl.product;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.SparePriceMapper;
import com.jtech.toa.entity.product.SparePrice;
import com.jtech.toa.model.dto.prices.AppSparePrice;
import com.jtech.toa.model.dto.products.SpareDto;
import com.jtech.toa.model.dto.products.SparePriceDto;
import com.jtech.toa.service.product.ISparePriceService;
import com.jtech.toa.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class SparePriceServiceImpl extends ServiceImpl<SparePriceMapper, SparePrice> implements ISparePriceService {

    private final IUserService userService;

    @Autowired
    public SparePriceServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<SparePriceDto> selectAreaPrice(int spareId) {
        return baseMapper.selectAreaPrice(spareId);
    }

    @Override
    public boolean savePrice(SpareDto spareDto, int spare) {
        SparePrice sparePrice = spareDto.toSparePrice();
        if (spareDto.getPriceId() == null) {
            sparePrice.setArea(0);
            sparePrice.setSpare(spare);
            return insert(sparePrice);
        }
        return updateById(sparePrice);
    }

    @Override
    public List<AppSparePrice> findTop100List(int area, int lastId) {
        return baseMapper.findTop100List(area,lastId);
    }

    @Override
    public List<AppSparePrice> findAllSpareList(int area, int lastId) {
        return baseMapper.findAllSpareList(area,lastId);
    }
}

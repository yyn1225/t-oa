package com.jtech.toa.service.impl.product;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.dao.PriceMapper;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.model.dto.prices.AppProductPrice;
import com.jtech.toa.model.dto.products.PanelPriceDto;
import com.jtech.toa.model.dto.prices.SavePriceDto;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class PriceServiceImpl extends ServiceImpl<PriceMapper, Price> implements IPriceService {

    private final IUserService userService;

    @Autowired
    public PriceServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Price selectByProductAndArea(int product, int area) {
        return baseMapper.selectByProductAndArea(product, area);
    }

    @Override
    public boolean savePriceNotZero(SavePriceDto savePriceDto) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());

        Price price = selectByProductAndArea(savePriceDto.getProduct(), userAppDto.getArea());
        if (price == null) {
            Price priceTwo = savePriceDto.toPriceTwo();
            priceTwo.setArea(userAppDto.getArea());
            return baseMapper.insert(priceTwo) == 1;
        }else {
            Price priceTwo = savePriceDto.toPriceTwo();
            priceTwo.setId(price.getId());
            return updateById(priceTwo);
        }
    }

    @Override
    public boolean savePriceZero(SavePriceDto savePriceDto) {
        Price price = selectByProductAndArea(savePriceDto.getProduct(), 0);
        if (price == null) {
            Price priceTwo = savePriceDto.toPrice();
            priceTwo.setArea(0);
            return baseMapper.insert(priceTwo) == 1;
        }else {
            Price priceTwo = savePriceDto.toPrice();
            priceTwo.setId(price.getId());
            return updateById(priceTwo);
        }
    }

    @Override
    public boolean updatePrice(SavePriceDto savePriceDto) {
        Price price = savePriceDto.toPrice();
        return updateById(price);
    }

    @Override
    public List<PanelPriceDto> selectAreaPrice(int productId) {
        return baseMapper.selectAreaPrice(productId);
    }

    @Override
    public List<AppProductPrice> findAppProductPrices(int area, int lastId) {
        return baseMapper.findAppProductPrices(area,lastId);
    }
    @Override
    public List<AppProductPrice> findAppAllProductPrices(int userId, int lastId) {
        return baseMapper.findAppAllProductPrices(userId,lastId);
    }
}

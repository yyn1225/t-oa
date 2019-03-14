package com.jtech.toa.service.impl.product;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.SalesMapper;
import com.jtech.toa.entity.product.Sales;
import com.jtech.toa.service.product.ISalesService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements ISalesService {
    @Override
    public Sales selectSalesByArea(int product, int area) {
        return baseMapper.selectSalesByArea(product, area);
    }

    @Override
    public boolean saveSales(int product, int area, int status) {
        Sales sales = selectSalesByArea(product, area);
        Sales sale = new Sales();
        if (sales == null) {
            sale.setProduct(product);
            sale.setArea(area);
            //新增状态为1-下架 ，状态 已售
            sale.setStatus(status);
            return baseMapper.insert(sale) == 1;
        }
        sale.setStatus(status);
        sale.setId(sales.getId());
        return baseMapper.updateById(sale) == 1;
    }
}

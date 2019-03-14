package com.jtech.toa.service.impl.system;

import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.dao.SysExchangeMapper;
import com.jtech.toa.service.system.ISysExchangeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-12-05
 */
@Service
public class SysExchangeServiceImpl extends ServiceImpl<SysExchangeMapper, SysExchange> implements ISysExchangeService {

    @Override
    public List<SysExchange> getLastExchange() {
        return baseMapper.getLastExchange();
    }

    @Override
    public BigDecimal calcRate(BigDecimal money, String current, String target, Map<String, BigDecimal> rateMap) {
        if (null == money) {
            return BigDecimal.ZERO;
        }
        if (target.equals(current)) {
            return money;
        }
        BigDecimal currentRate = rateMap.get(current);
        if (null == currentRate) {
            currentRate = BigDecimal.ONE;
        }
        BigDecimal targetRate = rateMap.get(target);
        if (null == targetRate) {
            targetRate = BigDecimal.ONE;
        }

        //原货币单位*人民币利率=人民币数量； 人民币数量/目标利率=目标货币金额

        return new BigDecimal(money.doubleValue()*currentRate.doubleValue()/targetRate.doubleValue());
    }
}

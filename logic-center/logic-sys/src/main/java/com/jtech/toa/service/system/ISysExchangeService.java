package com.jtech.toa.service.system;

import com.jtech.toa.entity.system.SysExchange;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-12-05
 */
public interface ISysExchangeService extends IService<SysExchange> {
	public List<SysExchange> getLastExchange();

	public BigDecimal calcRate(BigDecimal money,String current,String target,Map<String,BigDecimal> rateMap);
}

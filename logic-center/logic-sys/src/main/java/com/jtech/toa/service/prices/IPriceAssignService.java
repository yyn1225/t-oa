package com.jtech.toa.service.prices;

import com.jtech.toa.entity.prices.PriceAssign;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.model.dto.prices.PriceAssignDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-12-25
 */
public interface IPriceAssignService extends IService<PriceAssign> {
	List<PriceAssignDto> getAssignList(int systems);

	boolean saveAssign(PriceAssign assign);
}

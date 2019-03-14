/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.prices.impl;

import com.jtech.toa.entity.prices.PriceAssign;
import com.jtech.toa.dao.prices.PriceAssignMapper;
import com.jtech.toa.model.dto.prices.PriceAssignDto;
import com.jtech.toa.service.prices.IPriceAssignService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-12-25
 */
@Service
public class PriceAssignServiceImpl extends ServiceImpl<PriceAssignMapper, PriceAssign> implements IPriceAssignService {

    @Override
    public List<PriceAssignDto> getAssignList(int systems) {
        return baseMapper.findAssignList(systems);
    }

    @Override
    public boolean saveAssign(PriceAssign assign) {
        return assign.insert();
    }
}

package com.jtech.toa.service.impl.product;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.SpecialMapper;
import com.jtech.toa.entity.product.Special;
import com.jtech.toa.service.product.ISpecialService;
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
public class SpecialServiceImpl extends ServiceImpl<SpecialMapper, Special> implements ISpecialService {

    @Override
    public List<Special> selectSpecialList() {
        return baseMapper.selectSpecialList();
    }

    @Override
    public List<Special> selectListBySeries(int series){
        return baseMapper.selectListBySeries(series);
    }
}

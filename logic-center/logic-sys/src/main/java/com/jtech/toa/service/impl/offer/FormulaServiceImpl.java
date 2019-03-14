package com.jtech.toa.service.impl.offer;

import com.jtech.toa.entity.offer.Formula;
import com.jtech.toa.dao.offer.FormulaMapper;
import com.jtech.toa.entity.offer.OfferFormula;
import com.jtech.toa.service.offer.IFormulaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2018-08-03
 */
@Service
public class FormulaServiceImpl extends ServiceImpl<FormulaMapper, Formula> implements IFormulaService {

    @Override
    public List<OfferFormula> selectFormulaByOffer(Long offer) {
        return baseMapper.selectFormulaByOffer(offer);
    }
}

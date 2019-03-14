package com.jtech.toa.service.offer;

import com.jtech.toa.entity.offer.Formula;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.offer.OfferFormula;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2018-08-03
 */
public interface IFormulaService extends IService<Formula> {

    /**
     * 通过报价配件id集合查询公式集合
     * @param offer 报价单id
     * @return 公式集合
     */
    List<OfferFormula> selectFormulaByOffer(Long offer);
}

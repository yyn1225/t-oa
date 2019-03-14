package com.jtech.toa.dao.offer;

import com.jtech.toa.entity.offer.Formula;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.offer.OfferFormula;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2018-08-03
 */
public interface FormulaMapper extends BaseMapper<Formula> {

    /**
     * 通过报价单id集合查询公式集合
     * @param offer 报价单id
     * @return 公式集合
     */
    List<OfferFormula> selectFormulaByOffer(Long offer);
}
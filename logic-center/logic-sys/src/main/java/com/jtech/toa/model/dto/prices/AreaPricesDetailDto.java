package com.jtech.toa.model.dto.prices;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>区域产品价格明细dto</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class AreaPricesDetailDto {
    private Integer id;
    private String seriesName;
    private String partNo;
    private BigDecimal price;
    private String unit;
    private String remark;
    private Integer area;
    private Date createTime;
    private String systemName;
}

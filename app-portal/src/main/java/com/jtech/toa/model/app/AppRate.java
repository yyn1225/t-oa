package com.jtech.toa.model.app;

import lombok.Data;

/**
 * <p>
 *     汇率
 * </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppRate {
    private int id;
    /**
     * 币种
     */
    private String symbol;
    /**
     * 母币种
     */
    private String symbol_2;
    /**
     * 汇率
     */
    private long rate;
}

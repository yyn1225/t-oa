/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.sap;

import java.math.BigDecimal;
import java.util.Map;

import com.google.common.collect.Maps;
import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class SapRate {
    private Map<String,String> keyCode;

    public SapRate(){
        keyCode = Maps.newHashMap();
        keyCode.put("AUD","A$");//澳元
        keyCode.put("USD","$"); //美元
        keyCode.put("BRL","Gr.$"); //巴西雷亚尔
        keyCode.put("EUR","€"); //欧元
        keyCode.put("GBP","£");//英镑
        keyCode.put("HKD","HK$");//港币
        keyCode.put("RUB","RUB");//卢布
        keyCode.put("CNY","￥");//人民币
        keyCode.put("JPY","JPY￥"); //日元
        keyCode.put("MXN","Mex$"); //墨西哥币
        keyCode.put("CHF","CHF"); //瑞士法郎
        keyCode.put("MOP","MOP$"); //澳门币
    }

    /**
     * 集团
     */
    String mandt;
    /**
     * 汇率类型
     */
    String kurst;
    /**
     * 原货币
     */
    String fcurr;
    /**
     * 转换后的货币
     */
    String tcurr;
    /**
     * 有效开始时间
     */
    String gdatu;
    /**
     * 汇率
     */
    BigDecimal ukurs;
    /**
     * 原货币比率
     */
    BigDecimal ffact;
    /**
     * 转换后的货币比率
     */
    BigDecimal tfact;
    /**
     * 日期
     */
    String edate;

    public String getRateKey(String code){
        return keyCode.get(code);
    }
}

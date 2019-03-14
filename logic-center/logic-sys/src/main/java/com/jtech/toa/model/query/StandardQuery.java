package com.jtech.toa.model.query;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class StandardQuery {
    public static final StandardQuery me = new StandardQuery();
    /**
     * 备件产品关联类型
     */
    int type;
    /**
     * 产品id
     */
    int product;

    /**
     * 系列
     */
    int series;
    /**
     * 语言
     */
    String lang;


}

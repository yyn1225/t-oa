/*
 * Copyright (c)2012-2017 JingTong RDC(Research and Development Centre), Inc. All rights reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of JingTong RDC ,
 *         if any. The intellectual and technical concepts contained herein are proprietary
 *         to JingTong RDC and covered by China and Foreign Patents, patents in process,
 *         and are protected by trade secret or copyright law. Dissemination of this information
 *         or reproduction of this material is strictly forbidden unless prior written permission
 *         is obtained from JingTong RDC.
 */

package com.jtech.toa.core.service;

/**
 * <p> 流水号服务 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public interface ISerialNumberService {

    /**
     * 根据流水号前缀获取当前流水
     *
     * @param prefix 缓存前缀KEY
     * @param digits 流水号位数
     * @return 流水号
     */
    String serialNumber(String prefix, int digits);


    /**
     * 获取每日轮询的当前流水号
     *
     * @param prefix 缓存前缀KEY
     * @param digits 流水号位数
     * @return 流水号
     */
    String dayPolling(String prefix, int digits);
}

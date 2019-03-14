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

package com.jtech.toa.core.service.impl;

import com.google.common.base.Strings;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.jtech.marble.StringPool;
import com.jtech.marble.util.DateUtil;
import com.jtech.toa.core.service.ISerialNumberService;

/**
 * <p> 流水号服务 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class SerialNumberServiceImpl implements ISerialNumberService {


    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public SerialNumberServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String serialNumber(String redisCacheKey, int digits) {
        final BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(redisCacheKey);
        if (Strings.isNullOrEmpty(operations.get())) {
            operations.set(StringPool.ZERO);
        }
        Long increment = redisTemplate.opsForValue().increment(redisCacheKey, 1);
        final String digitsFormat = "%0" + digits + "d";
        final String serialNumber = String.format(digitsFormat, increment);
        if (StringUtils.length(serialNumber) > digits) {
            // 如果超过了指定长度，则重新开始
            operations.set(StringPool.ZERO);
            increment = redisTemplate.opsForValue().increment(redisCacheKey, 1);
            return String.format(digitsFormat, increment);
        }
        return serialNumber;
    }

    @Override
    public String dayPolling(String redisCacheKey, int digits) {

        final String redisKey = redisCacheKey + StringPool.COLON + DateUtil.yyyyMMdd(new Date());
        final BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(redisKey);
        if (Strings.isNullOrEmpty(operations.get())) {
            operations.set(StringPool.ZERO, 1, TimeUnit.DAYS);
        }
        Long increment = redisTemplate.opsForValue().increment(redisCacheKey, 1);
        final String digitsFormat = "%0" + digits + "d";
        final String serialNumber = String.format(digitsFormat, increment);
        if (StringUtils.length(serialNumber) > digits) {
            // 如果超过了指定长度，则重新开始
            operations.set(StringPool.ZERO);
            increment = redisTemplate.opsForValue().increment(redisCacheKey, 1);
            return String.format(digitsFormat, increment);
        }
        return serialNumber;
    }
}

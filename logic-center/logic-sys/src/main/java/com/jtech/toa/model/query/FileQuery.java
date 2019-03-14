/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.jtech.marble.StringPool;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class FileQuery {
    private String name;
    private List<Integer> folderId; //应用场景
    private List<Integer> seriesId;
    private Integer category; //类型
    private List<Integer> fileMarketDetailId; //细分
    private Long userId;
    private String lang;
    private String extend;

    public String getNameLike() {
        if (this.name == null) {
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }
    public List<String> getExtend() {
        if (StringUtils.isEmpty(this.extend)) {
            return new ArrayList<>();
        }else {
            return Arrays.asList(StringUtils.split(this.extend,","));
        }
    }
}

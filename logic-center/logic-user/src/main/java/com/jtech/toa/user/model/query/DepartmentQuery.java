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

package com.jtech.toa.user.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class DepartmentQuery {
    private String name;
    private Integer parentId;

    public String getNameLike() {
        if (this.name == null) {
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }
}

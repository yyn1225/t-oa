/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.app;

import com.jtech.toa.entity.customer.Customer;

import java.util.Date;

import lombok.Data;

@Data
public class AppCustomer {
    private int id;
    /**
     * 客户编码
     */
    private String zohuID;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 销售人员
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUserid;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifyUserid;
    /**
     * 同步状态
     */
    private int syncState;
    /**
     * 同步时间
     */
    private Date syncTime;
    /**
     * 同步人
     */
    private String syncUserid;
    /**
     * 同步异常
     */
    private String syncException;

    public AppCustomer(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
    }
}

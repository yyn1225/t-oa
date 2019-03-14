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

package com.jtech.toa.user.model.dto;

import com.google.common.collect.Lists;
import com.jtech.toa.user.entity.Department;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class DepartmentDto {
    private int id;
    private String name;
    private Integer leader;
    private String code;
    private String remark;
    private Integer parent;
    private int status;
    private String leaderName;
    private int level;
    private String parentName;
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;
    private String text;

    public List<DepartmentDto> children;

    public DepartmentDto() {

    }

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.text = department.getName();
        this.leader = department.getLeader();
        this.code = department.getCode();
        this.remark = department.getRemark();
        this.createTime = department.getCreateTime();
        this.status = department.getStatus();
        this.parent = department.getParent();
        this.level = department.getLevel();
        this.parent = department.getParent();
        this.children = Lists.newArrayList();
    }
}

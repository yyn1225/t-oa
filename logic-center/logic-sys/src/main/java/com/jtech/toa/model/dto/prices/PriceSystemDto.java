/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.prices;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
public class PriceSystemDto {
    private Integer id;
    private String name;
    private String creater;
    private String createName;
    private Date createTime;
    private Integer area;
    private Date startTime;
    private Date endTime;
    private String areaName;
}

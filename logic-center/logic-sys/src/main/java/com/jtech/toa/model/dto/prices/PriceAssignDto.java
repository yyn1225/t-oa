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
public class PriceAssignDto {
    private Integer id;
    private Integer systems;
    private String systemName;
    private Integer area;
    private String areaName;
    private String assignName;
    private Integer assigner;
    private Date assignTime;
}

/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import com.google.common.collect.Lists;

import com.jtech.toa.entity.product.Series;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class SeriesDto implements Comparable<SeriesDto> {
    private Integer id;
    private String text;
    private Integer line;
    private Integer status;
    private Integer creater;
    private Date createTime;
    private String image;
    private String remark;
    private Integer parent;
    private Integer type;
    private String pName;

    public List<SeriesDto> children;

    public SeriesDto() {
    }

    public Series toSeries() {
        Series series = new Series();
        series.setId(this.id);
        series.setName(this.text);
        series.setLine(this.line);
        series.setStatus(this.status);
        series.setCreater(this.creater);
        series.setImage(this.image);
        series.setRemark(this.remark);
        series.setParent(this.parent);
        series.setType(this.type);
        series.setCreateTime(this.createTime);
        return series;
    }

    public SeriesDto(Series series) {
        this.id = series.getId();
        this.text = series.getName();
        this.line = series.getLine();
        this.status = series.getStatus();
        this.creater = series.getCreater();
        this.createTime = series.getCreateTime();
        this.image = series.getImage();
        this.remark = series.getRemark();
        this.parent = series.getParent();
        this.type = series.getType();
        this.children = Lists.newArrayList();
    }

    @Override
    public int compareTo(SeriesDto o) {
        return this.getText().compareTo(o.getText());
    }
}

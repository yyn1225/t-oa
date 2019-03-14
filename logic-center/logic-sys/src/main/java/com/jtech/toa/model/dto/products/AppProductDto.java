package com.jtech.toa.model.dto.products;

import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppProductDto {
    private Integer id;
    private Integer box;
    private String configuration;
    private String certification;
    private String partNo;
    private String state;
    private String series;
    private int seriesId;
    private Integer featured;
    private Date updateTime;
    private Integer updater;
    private Date executionTime;
    private String type;
    private Integer status;
    private String color;
    private Integer creater;
    private Date createTime;
    private Integer request;


    private Integer boxId;
    private String boxScnNo;
    private int boxModual;
    private int boxModual2;
    private String modualScnNo;
    private String modualScnNo2;
    private Integer boxTransverseCount;
    private Integer boxPortraitCount;
    private BigDecimal boxWeight;
    private Integer boxTransversePix;
    private Integer boxPortraitPix;
    private BigDecimal boxHeight;
    private BigDecimal boxWidth;
    private BigDecimal boxThickness;
    private String boxBoxType;
    private Date boxCreateTime;
    private Date boxUpdateTime;


    private Integer paramsId;
    private Integer paramsProduct;
    private String paramsControl;
    private Integer paramsCalibration;
    private Integer paramsIntelligent;
    private Integer paramsRigging;
    private Integer paramsStack;
    private Integer paramsFront;
    private String paramsFixModual;
    private String paramsFixPsu;
    private String paramsIpRating;
    private String paramsBrightness;
    private String paramsContrastRatio;
    private String paramsGrayScale;
    private String paramsRefresh;
    private String paramsViewing;
    private Integer paramsPowerMax;
    private Integer paramsPowerAvg;
    private String paramsDrivingIc;
    private String paramsDrivingType;
    private String paramsPsu;
    private Integer paramsPsuPower;
    private Integer paramsPsuCount;
    private Integer paramsStandardCarryLower;
    private Integer paramsStandardCarryHigh;
    private Date paramsCreateTime;
    private Date paramsUpdateTime;

    public AppProduct toProduct(){
        AppProduct product = new AppProduct();
        product.setId(this.getId());
        product.setCertification(this.getCertification());
        product.setConfiguration(this.getConfiguration());
        product.setColor(this.getColor());
//        product.setCreater(this.getCreater());
//        product.setCreateTime(this.getCreateTime());
//        product.setExecutionTime(this.getExecutionTime());
//        product.setFeatured(this.getFeatured());
        product.setPartNo(this.getPartNo());
//        product.setRequest(this.getRequest());
        product.setSeries(this.getSeries());
        product.setSeriesId(this.getSeriesId());
        product.setStatus(this.getStatus());
        product.setRemark(this.getState());
//        product.setType(this.getType());
//        product.setUpdater(this.getUpdater());
//        product.setUpdateTime(this.getUpdateTime());

        Map<String,Object> activeparams = Maps.newHashMap();
        Map<String,Object> techparams = Maps.newHashMap();

        activeparams.put("id",this.getBoxId());
        activeparams.put("scnNo",this.getBoxScnNo());
        activeparams.put("modual",this.getBoxModual());
        activeparams.put("modual2",this.getBoxModual2());
        activeparams.put("modualScnNo",this.getModualScnNo());
        activeparams.put("modualScnNo2",this.getModualScnNo2());
        activeparams.put("transverseCount",this.getBoxTransverseCount());
        activeparams.put("portraitCount",this.getBoxPortraitCount());
        activeparams.put("weight",this.getBoxWeight());
        activeparams.put("transversePix",this.getBoxTransversePix());
        activeparams.put("portraitPix",this.getBoxPortraitPix());
        activeparams.put("height",this.getBoxHeight());
        activeparams.put("width",this.getBoxWidth());
        activeparams.put("thickness",this.getBoxThickness());
        activeparams.put("boxType",this.getBoxBoxType());
        activeparams.put("createTime",this.getBoxCreateTime());
        activeparams.put("updateTime",this.getBoxUpdateTime());

        techparams.put("id", this.getParamsId());
        techparams.put("product", this.getParamsProduct());
        techparams.put("control", this.getParamsControl());
        techparams.put("calibration", this.getParamsCalibration());
        techparams.put("intelligent", this.getParamsIntelligent());
        techparams.put("rigging", this.getParamsRigging());
        techparams.put("stack", this.getParamsStack());
        techparams.put("front", this.getParamsFront());
        techparams.put("fixModual", this.getParamsFixModual());
        techparams.put("fixPsu", this.getParamsFixPsu());
        techparams.put("ipRating", this.getParamsIpRating());
        techparams.put("brightness", this.getParamsBrightness());
        techparams.put("contrastRatio", this.getParamsContrastRatio());
        techparams.put("grayScale", this.getParamsGrayScale());
        techparams.put("refresh", this.getParamsRefresh());
        techparams.put("viewing", this.getParamsViewing());
        techparams.put("powerMax", this.getParamsPowerMax());
        techparams.put("powerAvg", this.getParamsPowerAvg());
        techparams.put("drivingIc", this.getParamsDrivingIc());
        techparams.put("drivingType", this.getParamsDrivingType());
        techparams.put("psu", this.getParamsPsu());
        techparams.put("psuPower", this.getParamsPsuPower());
        techparams.put("psuCount", this.getParamsPsuCount());
        techparams.put("standardCarryLower", this.getParamsStandardCarryLower());
        techparams.put("standardCarryHigh", this.getParamsStandardCarryHigh());
        techparams.put("createTime", this.getParamsCreateTime());
        techparams.put("updateTime", this.getParamsUpdateTime());

        Map<String,Object> box = Maps.newHashMap();
        box.put("table", "app_product");
        box.put("activeparams", activeparams);
        box.put("techparams", techparams);

        product.setBox(JSONObject.toJSONString(box));
        return product;
    }
}

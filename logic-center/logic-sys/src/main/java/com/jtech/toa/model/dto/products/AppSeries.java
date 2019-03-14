package com.jtech.toa.model.dto.products;

import com.jtech.marble.StringPool;
import com.jtech.toa.entity.product.Series;

import java.util.Date;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppSeries {
    private Integer id;
    private String text;
    private Integer line;
    private Integer status;
    private String image;
    private String remark;
    private Integer parent;
    private Integer type;
    private String pname;

    public String children;

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


    public AppSeries(Series series){
        this.id = series.getId();
        this.text = series.getName();
        this.line = series.getLine();
        this.status = series.getStatus();
        this.createUserid = series.getCreater() + "";
        this.createTime = series.getCreateTime();
        this.image = series.getImage();
        this.remark = series.getRemark();
        this.parent = series.getParent();
        //这里使用了一个临时字段type去存储主推
        this.type = series.getFeatured();
        this.children = StringPool.EMPTY;
    }
}

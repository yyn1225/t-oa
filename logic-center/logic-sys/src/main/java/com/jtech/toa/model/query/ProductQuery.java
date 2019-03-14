package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;
import lombok.Data;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ProductQuery {
    public static final ProductQuery me = new ProductQuery();

    /**
     * 物料号
     */
    String partNo;

    /**
     * 系列
     */
    List<Integer> series;

    /**
     * 配置
     */
    String configuration;

    /**
     * 认证
     */
    String certification;

    /**
     * 认证Like
     */
    String certificationStr;

    /**
     * 状态
     */
    String status;

    /**
     * 主推
     */
    String featured;

    /**
     * 产品Id
     */
    Integer productId;

    /**
     * 产品类型
     */
    String productType;

    /**
     * 系列号(上级)
     */
    Integer parentSeries;

    /**
     * 颜色
     */
    String color;

    /**
     * 语言
     */
    String lang;

    public String getCustomerPartNoLike(){
        if(this.partNo == null){
            this.partNo = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(partNo, SqlLike.DEFAULT);
    }

    public String getColorLike(){
        if(this.color == null){
            this.color = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(color, SqlLike.DEFAULT);
    }

    public String getCertificationStrLike(){
        if(this.certificationStr == null){
            this.certificationStr = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(certificationStr, SqlLike.DEFAULT);
    }
}

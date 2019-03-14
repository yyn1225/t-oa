package com.jtech.toa.entity.product;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@TableName("absen_product_sales")
public class Sales extends Model<Sales> {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer product;
    private Integer area;
    private Integer status;
    @TableField("discount_type")
    private Integer discountType;
    private String unit;
    private Integer suggest;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getSuggest() {
        return suggest;
    }

    public void setSuggest(Integer suggest) {
        this.suggest = suggest;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

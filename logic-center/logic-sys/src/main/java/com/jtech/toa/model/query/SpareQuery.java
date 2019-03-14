package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;
import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class SpareQuery {

    public static final SpareQuery me = new SpareQuery();

    /**
     * 料号或其他唯一标识码
     */
    String material;

    /**
     * 配件类型
     */
    String type;

    /**
     * 品牌
     */
    String brand;

    /**
     * 品牌
     */
    String lang;

    public String getCustomerMaterialLike(){
        if(this.material == null){
            this.material = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(material, SqlLike.DEFAULT);
    }

    public String getCustomerTypeLike(){
        if(this.type == null){
            this.type = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(type, SqlLike.DEFAULT);
    }

    public String getCustomerBrandLike(){
        if(this.brand == null){
            this.brand = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(brand, SqlLike.DEFAULT);
    }
}

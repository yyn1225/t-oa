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
public class SalesLevelQuery {
    public static final SalesLevelQuery ME = new SalesLevelQuery();

    /**
     * 语言
     */
    String lang;

    /**
     * 名称
     */
    String name;

    /**
     * 编号
     */
    String code;

    /**
     * 地区
     */
    Integer area;

    public String getCustomerNameLike(){
        if(this.name == null){
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }

    public String getCustomerCodeLike(){
        if(this.code == null){
            this.code = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(code, SqlLike.DEFAULT);
    }
}

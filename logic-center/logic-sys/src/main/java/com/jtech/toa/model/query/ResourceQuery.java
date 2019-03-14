package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ResourceQuery {
    public static final ResourceQuery me = new ResourceQuery();
    /**
     * 菜单名称
     */
    String name;
    /**
     * 菜单编码
     */
    String code;
    /**
     * 语言
     */
    String lang;
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

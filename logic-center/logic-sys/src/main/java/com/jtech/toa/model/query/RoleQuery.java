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
public class RoleQuery {
    public static final RoleQuery ME = new RoleQuery();

    /**
     * 状态
     */
    int status;

    /**
     * 语言
     */
    String language;

    /**
     * 名称
     */
    String name;

    public String getCustomerNameLike(){
        if(this.name == null){
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }
}

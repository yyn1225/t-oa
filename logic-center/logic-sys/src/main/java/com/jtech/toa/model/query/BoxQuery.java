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
public class BoxQuery {
    public static final BoxQuery me = new BoxQuery();
    /**
     * 箱体料号
     */
    String scnNo;

    /**
     * 模组
     */
    String modual;

    /**
     * 箱体料号
     */
    String lang;

    public String getCustomerScnNoLike(){
        if(this.scnNo == null){
            this.scnNo = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(scnNo, SqlLike.DEFAULT);
    }
}

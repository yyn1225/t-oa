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
public class ModuleQuery {

    public static final ModuleQuery me = new ModuleQuery();

    /**
     * 物料号或其他唯一标识码
     */
    String scnNo;

    /**
     * LED灯类型
     */
    String configuration;
    /**
     * 语言类型
     */
    String lang;

    public String getCustomerScnNoLike(){
        if(this.scnNo == null){
            this.scnNo = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(scnNo, SqlLike.DEFAULT);
    }

    public String getCustomerConfigurationLike(){
        if(this.configuration == null){
            this.configuration = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(configuration, SqlLike.DEFAULT);
    }
}

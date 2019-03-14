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
public class SeriesQuery {
    private String lang;
    private String name;
    private boolean flag;
    private String allName;

    public String getNameLike() {
        if (this.name == null) {
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }

    public String getAllNameLike() {
        if (this.allName == null) {
            this.allName = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(allName, SqlLike.DEFAULT);
    }
}

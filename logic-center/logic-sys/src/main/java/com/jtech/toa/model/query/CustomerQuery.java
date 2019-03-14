package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;

import lombok.Data;

import java.util.Date;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class CustomerQuery {
    public static final CustomerQuery me = new CustomerQuery();

    int userId;

    String name;
    //等级
    int rating;
    //所属大区
    int area;

    /**
     * 行业类型
     */
    String type;
    /**
     * 城市
     */
    String location;

    Date startTime;

    Date endTime;

    public String getCustomerNameLike() {
        if (this.name == null) {
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }

    public String getTypeLike() {
        if (this.type == null) {
            this.type = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(type, SqlLike.DEFAULT);
    }

    public String getLocationLike() {
        if (this.location == null) {
            this.location = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(location, SqlLike.DEFAULT);
    }


}

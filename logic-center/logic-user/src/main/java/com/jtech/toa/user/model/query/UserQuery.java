package com.jtech.toa.user.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;

import lombok.Data;

import java.util.List;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class UserQuery {

    public static final UserQuery me = new UserQuery();
    /**
     * 用户名称
     */
    String name;
    /**
     * 用户工号
     */
    String workno;
    /**
     * 用户手机号码
     */
    String phone;
    /**
     * 用户区域
     */
    List<Integer> area;


    /**
     * 报价区域
     */
    String areaIds;

    public String getCustomerNameLike() {
        if (this.name == null) {
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }

    public String getCustomerWorknoLike() {
        if (this.workno == null) {
            this.workno = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(workno, SqlLike.DEFAULT);
    }

    public String getCustomerPhoneLike() {
        if (this.phone == null) {
            this.phone = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(phone, SqlLike.DEFAULT);
    }
}

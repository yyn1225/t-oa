package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;
import lombok.Data;

import java.util.List;

/**
 * <p>区域价格查询对象query</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AreaPricesDetailQuery {
    /**
     * 产品系列list条件
     */
    private List<Integer> seriesList;
    /**
     * 产品物料号
     */
    private String partNo;
    /**
     * 二级区域
     */
    private Integer area;

    public String getPartNoLike() {
        if (this.partNo == null) {
            this.partNo = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(partNo, SqlLike.DEFAULT);
    }
}

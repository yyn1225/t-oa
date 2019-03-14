package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.jtech.marble.StringPool;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AdminFileQuery {
    private String name;
    private Integer folderId;
    private Integer seriesId;
    private Integer category;
    private Integer fileMarketDetailId;
    private Long userId;
    private String lang;
    private List<String> extend;

    public String getNameLike() {
        if (this.name == null) {
            this.name = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(name, SqlLike.DEFAULT);
    }

    public void setExtend(String extend){
        if (extend.trim() != null && extend.trim() != ""&&extend.length()>0){
            this.extend = Arrays.asList(extend.split(","));
        }else{
            this.extend =null;
        }

    }
}

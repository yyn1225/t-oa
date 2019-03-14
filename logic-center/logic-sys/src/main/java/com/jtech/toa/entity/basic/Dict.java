package com.jtech.toa.entity.basic;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@TableName("absen_basic_dict")
public class Dict extends Model<Dict> {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String category;
    private String code;
    private String name;
    private Integer parent;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

package com.jtech.toa.model.app;

import java.util.Date;

import lombok.Data;

import com.jtech.toa.entity.basic.Dict;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppDict {
    private int id;
    /**
     * 字典类型编码，建议涵盖：
     *      贸易条款，
     *      付款方式，
     *      发运地址，
     *      贸易公司，
     *      服务类型，
     * 这几个类型定义延续WEB后台代码，APP这里代码直接调用
     */
    private String category;
    /**
     * 字典编码
     */
    private String code;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 上级字典
     */
    private int parent;


    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUserid;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifyUserid;

    public AppDict(Dict dict){
        this.id = dict.getId();
        this.category = dict.getCategory();
        this.code = dict.getCode();
        this.name = dict.getName();
        this.parent = dict.getParent();
    }
}

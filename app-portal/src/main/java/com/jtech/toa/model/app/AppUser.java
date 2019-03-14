package com.jtech.toa.model.app;

import java.util.Date;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AppUser {
    private int id;
    /**
     * 目前是用的艾比森工号
     */
    private String User_id;
    /**
     * 姓名
     */
    private String Name;
    /**
     * 验证
     */
    private String token;
    /**
     * 用户角色
     */
    private String User_role;
    /**
     * 基本信息
     */
    private String Basics;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 创建人
     */
    private String create_userid;
    /**
     * 修改时间
     */
    private Date modify_time;
    /**
     * 修改人
     */
    private String modify_userid;
    /**
     * 同步状态
     */
    int sync_state;
    /**
     * 同步时间
     */
    private Date sync_time;
    /**
     * 同步人
     */
    private String sync_userid;
    /**
     * 同步异常
     */
    private String sync_exception;

}

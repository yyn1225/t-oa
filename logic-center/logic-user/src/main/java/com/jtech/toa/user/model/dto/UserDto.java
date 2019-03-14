package com.jtech.toa.user.model.dto;

import com.jtech.toa.user.entity.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author jtech tao.ding
 * @since 2017-07-11
 */
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -3818453501089787141L;

    private Integer id;
    private String phone;
    private String username;
    private String loginName;
    private String avatar;
    private Integer status;
    private String salt;
    private Integer deleteFlag;
    private String password;
    private String workNo;
    private String language;
    private String languageDefault;
    private String areaName;
    private int area;
    private Integer areaId;
    private String email;
    private Date createTime;
    private Integer creater;
    private Integer deleteAble;
    private List<Integer> departmentIds;
    private Integer assistant;
    private String assistantName;
    private String departmentName;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.username.trim());
        user.setPhone(this.phone);
        user.setLoginName(this.loginName);
        user.setEmail(this.email);
        user.setArea(this.areaId);
        user.setWorkNo(this.workNo);
        user.setLanguage(this.language);
        user.setLanguageDefault(this.languageDefault);
        user.setAvatar(this.avatar);
        user.setCreateTime(this.createTime);
        user.setAssistant(this.assistant);
        return user;
    }

}

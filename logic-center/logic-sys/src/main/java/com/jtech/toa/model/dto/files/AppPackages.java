package com.jtech.toa.model.dto.files;

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
public class AppPackages {
    private Integer id;
    private String names;
    private Integer parent;
    private String icon;
    private Date createTime;
    private Date modifyTime;
    private Integer createUserid;
    private Integer modifyUserid;
    private String fullPath;

    private String children;
}

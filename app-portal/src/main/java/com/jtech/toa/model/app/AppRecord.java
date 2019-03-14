package com.jtech.toa.model.app;

import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class AppRecord {
    private String code;
    private Date syncTime;
}

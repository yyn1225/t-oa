package com.jtech.toa.user.model;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class UserToken implements Serializable {

    private static final long serialVersionUID = 7563498567107593672L;

    private int userId;
    private String name;

    private String token;


}

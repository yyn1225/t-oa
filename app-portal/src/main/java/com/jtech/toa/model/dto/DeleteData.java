/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Data
public class DeleteData {
    private int sort;
    private String table;
    private String key;
    private String value;
    private String type;
    private String vtype;

    public DeleteData(String table) {
        this.table = table;
        this.sort = 1;
        this.key = "id";
        this.vtype = Vtype.Single;
        this.type = Type.NotEq;
        this.value = "0";
    }

    public DeleteData(){}

    public interface Type {
        public static final String Eq = "=";
        public static final String NotEq = "!=";
        public static final String In = "in";
        public static final String NotIn = "not in";
        public static final String Like = "like";
        public static final String NotLike = "not like";
        public static final String Lt = "<";
        public static final String Gt = ">";
        public static final String LtOrEq = "<=";
        public static final String GtOrEq = ">=";
    }

    public interface Vtype {
        public static final String Single = "1";
        public static final String Array = "2";
    }
}

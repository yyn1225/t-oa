/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.files;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class FilePackageDto {
    private int id;
    private String name;
    private int parentId;
    private String parentName;
}

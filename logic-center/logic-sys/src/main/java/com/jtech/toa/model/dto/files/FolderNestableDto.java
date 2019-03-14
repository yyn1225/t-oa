/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.files;

import java.util.List;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class FolderNestableDto {
    private int id;
    private String name;
    private String icon;
    private int parentId;
    private List<FolderNestableDto> children;
}

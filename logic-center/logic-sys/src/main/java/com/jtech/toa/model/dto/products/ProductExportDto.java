/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.products;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class ProductExportDto  {

    private String parentSeriesName;
    private String productSeries;
    private String partNo;
    private String productConfiguration;
    private String certification;
    private Integer featured;
    private String productType;
    private String productStatus;
}

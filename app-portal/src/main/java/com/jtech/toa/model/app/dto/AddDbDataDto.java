package com.jtech.toa.model.app.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class AddDbDataDto {
    private Map<String,List<TableInfoDto>> table;
}

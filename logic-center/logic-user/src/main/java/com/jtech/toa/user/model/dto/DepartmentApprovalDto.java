package com.jtech.toa.user.model.dto;

import lombok.Data;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class DepartmentApprovalDto {
    private Integer departmentId;
    private String name;
    private Integer level;
    private Integer leader;
    private String leaderName;
    private Integer id;
    private Integer area;
    private Integer approval;
    private Integer condition;
    private Integer auditor;
    private Integer export;
    private String approvalStr;
}

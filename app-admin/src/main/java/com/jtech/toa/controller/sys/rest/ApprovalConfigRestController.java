package com.jtech.toa.controller.sys.rest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jtech.toa.entity.system.ApprovalConfig;
import com.jtech.toa.service.system.IApprovalConfigService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.model.dto.DepartmentApprovalDto;

import com.jtech.toa.user.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/approval/config/rest")
public class ApprovalConfigRestController {
    private IApprovalConfigService approvalConfigService;
    private IDepartmentService departmentService;

    @Autowired
    public ApprovalConfigRestController(IApprovalConfigService approvalConfigService, IDepartmentService departmentService){
        this.approvalConfigService = approvalConfigService;
        this.departmentService = departmentService;
    }

    /**
     * 审批配置的保存方法
     * @param departmentApprovalDto 部门审批DTO
     */
    @PostMapping("/save")
    public ResponseEntity save(DepartmentApprovalDto departmentApprovalDto) {
        List<Department> departmentList = departmentService.selectList(new EntityWrapper<Department>().eq("parent", departmentApprovalDto.getArea()));
        Department dept = departmentService.selectById(departmentApprovalDto.getArea());
        departmentList.add(dept);
        for (Department department : departmentList) {
            ApprovalConfig approvalConfig;
            approvalConfig = approvalConfigService.selectOne(new EntityWrapper<ApprovalConfig>().eq("area", department.getId()));
            if (approvalConfig == null) {
                approvalConfig = new ApprovalConfig();
            }
            approvalConfig.setArea(department.getId());
            approvalConfig.setAuditor(department.getLeader());
            if (departmentApprovalDto.getApprovalStr() == null) {
                approvalConfig.setApproval(0);
            }else {
                approvalConfig.setApproval(1);
            }
            approvalConfig.setCondition(departmentApprovalDto.getCondition());
            approvalConfig.setExport(departmentApprovalDto.getExport());
            approvalConfig.insertOrUpdate();
        }
        return ResponseEntity.ok().build();
    }
}

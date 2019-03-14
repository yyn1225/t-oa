/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.user.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.google.common.base.Optional;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.model.dto.AppAreas;
import com.jtech.toa.user.model.dto.DepartmentApprovalDto;
import com.jtech.toa.user.model.dto.DepartmentDto;
import com.jtech.toa.user.model.dto.SecurityUserDto;
import com.jtech.toa.user.model.query.DepartmentQuery;
import com.jtech.toa.user.model.vo.TreeDataVO;

import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 组织结构树查询
     * @param q 组织机构的名称
     * @param lang 语言ID
     * @return 树的集合
     */
    List<ZTreeVO<TreeDataVO>> findAllToTree(String q, String lang);

    /**
     * 分页查询部门信息
     * @param requestPage 分页对象
     * @param organizationQuery 查询对象
     */
    void searchByPagination(Page<DepartmentDto> requestPage, DepartmentQuery
            organizationQuery,String lang);

    /**
     * 分页获取部门信息
     * @param requestPage 分页对象
     * @param orders 排序对象
     */
    void findByPagination(Page<DepartmentDto> requestPage, List<Sort.Order> orders,String lang);

    /**
     * 查询大区
     * @return 部门集合
     */
    List<Department> selectDepartmentList();


    /**
     * 根据语言查询全部的组织
     * @param lang 语言
     * @return 组织集合
     */
    List<Department> findAll(String lang);

    /**
     * 根据code查询组织信息
     * @param code 编码
     * @return 组织对象
     */
    Optional<Department> findByCode(String code);

    /**
     * 根据code查询组织信息，排除当前的id
     * @param code 编码信息
     * @param id 需要排除的id
     * @return 组织对象
     */
    Department findByCodeAndIdNotEq(String code, int id);

    /**
     * 更新部门信息
     * @param department 部门对象
     * @param langVal 语言json
     * @return 是否成功
     */
    boolean updateDepartment(Department department, String langVal);

    /**
     * 添加部门信息
     * @param department 部门对象
     * @param langVal 语言json
     * @return 是否成功
     */
    boolean plusDepartment(Department department, String langVal);

    /**
     * 删除组织
     * @param id 组织的id
     */
    boolean deleteByDepartmentId(int id);

    /**
     * 查询组织的子集
     * @param id 父级id
     * @return 组织的集合
     */
    List<Department> findByParentId(int id);

    /**
     * 通过id查询部门信息
     * @param id 组织id
     * @return 部门数据传输对象
     */
    DepartmentDto selectOneById(int id);

    /**
     * 通过查询所有存在用户的部门信息
     * @return 部门权限集合
     */
    List<SecurityUserDto> selectListByArea();

    /**
     * 通过某个用户的id查询用户所在大区的对应部门信息
     * @param userId
     * @return
     */
    Department findUserAreaDepartment(int userId);

    /**
     * 查询所有审批设置的部门列表
     * @return 列表
     */
    List<DepartmentApprovalDto> selectApprovalList();

    /**
     * 通过用户查询所有部门
     * @param userId 用户id
     * @return 部门集合
     */
    List<Department> selectDepartmentByUser(int userId , String lang);

    /**
     * 通过id集合查询第一条数据
     * @param deptIdList 部门id集合
     * @return 一条数据
     */
    Department selectDeptByIds(List<Integer> deptIdList);

    /**
     * 通过用户查询对应地区
     * @param user 用户主键
     * @return 地区集合
     */
    List<Department> selectDataByUser(int user);

    /**
     * 查询一级部门以及二级部门
     * @return 部门列表
     */
    List<DepartmentDto> selectChildList();

    /**
     * 通过子部门查询一级父部门
     * @param id 子部门id
     * @return Department对象
     */
    Department selectParentById(int id);

    List<AppAreas> selectAppArea(int userId);
}

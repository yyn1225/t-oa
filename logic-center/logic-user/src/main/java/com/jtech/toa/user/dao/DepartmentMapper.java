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

package com.jtech.toa.user.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.user.entity.Department;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.user.model.dto.AppAreas;
import com.jtech.toa.user.model.dto.DepartmentApprovalDto;
import com.jtech.toa.user.model.dto.DepartmentDto;
import com.jtech.toa.user.model.dto.SecurityUserDto;
import com.jtech.toa.user.model.query.DepartmentQuery;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * <p> Mapper 接口 </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询全部的组织机构信息
     *
     * @param lang 语言ID
     * @return 组织机构集合
     */
    List<Department> findAll(@Param("lang") String lang);

    /**
     * 组织机构树按照名字查询
     *
     * @param likeName 名字
     * @param lang     语言ID
     * @return 组织机构的集合
     */
    List<Department> selectByLikeName(@Param("likeName") String likeName,
                                      @Param("lang") String lang);

    /**
     * 查询部门信息
     *
     * @param requestPage       分页对象
     * @param organizationQuery 查询对象
     * @return 部门dto集合
     */
    List<DepartmentDto> searchByPagination(Page<DepartmentDto> requestPage, @Param("query")
            DepartmentQuery organizationQuery, @Param("lang") String lang);

    /**
     * 分页获取部门信息
     *
     * @param requestPage 分页对象
     * @param orders      排序对象
     * @return 部门dto集合
     */
    List<DepartmentDto> selectByPagination(Page<DepartmentDto> requestPage, List<Sort.Order> orders, @Param("lang") String lang);

    /**
     * 查询大区的
     *
     * @return 部门集合
     */
    List<Department> selectTopDepartmentList();


    /**
     * 根据code查询组织信息
     *
     * @param code 编码
     * @return 组织对象
     */
    Department selectByCode(@Param("code") String code);


    /**
     * 更具code查询组织信息，排除当前的id
     *
     * @param code 编码信息
     * @param id   需要排除的id
     * @return 组织对象
     */
    Department selectByCodeAndIdNotEq(@Param("code") String code, @Param("id") int id);

    /**
     * 删除组织信息
     * @param id 组织的id
     * @return 影响的行数
     */
    int remove(@Param("id") int id);

    /**
     * 查询部门的子部门
     * @param id 组织的id
     * @return 部门的集合
     */
    List<Department> selectByParentId(@Param("parentId") int id);

    /**
     * 通过id查询部门信息
     * @param id 组织id
     * @return 部门数据传输对象
     */
    DepartmentDto selectOneById(@Param("id") int id);

    /**
     * 通过查询所有存在用户的部门信息
     * @return 部门权限集合
     */
    List<SecurityUserDto> selectListByArea();

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
    List<Department> selectDepartmentByUser(@Param("userId") int userId,@Param("lang") String lang);

    /**
     * 通过id集合查询第一条数据
     * @param deptIdList
     * @return 一条数据
     */
    Department selectDeptByIds(@Param("deptIdList") List<Integer> deptIdList);

    /**
     * 通过用户查询对应地区
     * @param user 用户主键
     * @return 地区集合
     */
    List<Department> selectDataByUser(@Param("user") int user);

    /**
     * 查询一级部门以及二级部门
     * @return 部门列表
     */
    List<Department> selectChildList();

    /**
     * 通过子部门查询一级父部门
     * @param id 子部门id
     * @return Department对象
     */
    Department selectParentById(@Param("id") int id);

    List<AppAreas> selectAppArea(int userId);
}
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

package com.jtech.toa.user.service.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.google.common.collect.Maps;
import com.jtech.marble.StringPool;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.util.CollectionUtil;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.user.dao.DepartmentMapper;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.model.dto.AppAreas;
import com.jtech.toa.user.model.dto.DepartmentApprovalDto;
import com.jtech.toa.user.model.dto.DepartmentDto;
import com.jtech.toa.user.model.dto.SecurityUserDto;
import com.jtech.toa.user.model.query.DepartmentQuery;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.jtech.toa.user.service.IDepartmentLangService;
import com.jtech.toa.user.service.IDepartmentService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    private final IDepartmentLangService departmentLangService;

    @Autowired
    public DepartmentServiceImpl(IDepartmentLangService departmentLangService) {
        this.departmentLangService = departmentLangService;
    }

    @Override
    public List<ZTreeVO<TreeDataVO>> findAllToTree(String likeName, String lang) {
        if (Strings.isNullOrEmpty(likeName)) {
            return generateOrgTree(lang);
        } else {
            return generateOrgTreeByName(likeName, lang);
        }
    }

    @Override
    public void searchByPagination(Page<DepartmentDto> requestPage, DepartmentQuery
            organizationQuery, String lang) {
        List<DepartmentDto> organizationDtos = baseMapper.searchByPagination(requestPage,
                organizationQuery, lang);
        requestPage.setRecords(organizationDtos);
    }

    @Override
    public void findByPagination(Page<DepartmentDto> requestPage, List<Sort.Order> orders, String lang) {
        List<DepartmentDto> organizationDtos = baseMapper.selectByPagination(requestPage, orders,lang);
        requestPage.setRecords(organizationDtos);
    }

    @Override
    public List<Department> selectDepartmentList() {
        return baseMapper.selectTopDepartmentList();
    }

    @Override
    public List<Department> findAll(String lang) {
        return baseMapper.findAll(lang);
    }

    @Override
    public Optional<Department> findByCode(String code) {
        if (Strings.isNullOrEmpty(code)) {
            return Optional.absent();
        }
        Department department = baseMapper.selectByCode(code);
        if (department == null) {
            return Optional.absent();
        }
        return Optional.of(department);
    }

    @Override
    public Department findByCodeAndIdNotEq(String code, int id) {
        return baseMapper.selectByCodeAndIdNotEq(code, id);
    }

    @Transactional
    @Override
    public boolean updateDepartment(Department department, String langVal) {
        Department parentDept = baseMapper.selectById(department.getParent());
        if (parentDept == null) {
            department.setLevel(1);
            department.setParent(0);
        }else {
            department.setLevel(parentDept.getLevel() + 1);
        }
        boolean isOk = updateById(department);
        if (!isOk) {
            throw new DaoException("更新组织失败");
        }
        isOk = departmentLangService.insertDepartmentLang(langVal, department.getId());
        return isOk;
    }

    @Override
    public boolean plusDepartment(Department department, String langVal) {
        Department parentDept = baseMapper.selectById(department.getParent());
        if (parentDept == null) {
            department.setLevel(1);
            department.setParent(0);
        }else {
            department.setLevel(parentDept.getLevel() + 1);
        }
        department.setStatus(Department.Status.Valid);
        boolean isOk = insert(department);
        if (!isOk) {
            throw new DaoException("更新组织失败");
        }
        isOk = departmentLangService.insertDepartmentLang(langVal, department.getId());
        return isOk;
    }

    @Override
    public boolean deleteByDepartmentId(int id) {
        boolean isOk = baseMapper.remove(id) > 0;
        if (!isOk) {
            throw new DaoException("删除组织失败");
        }
        isOk = departmentLangService.removeByDeparmentId(id);
        if (!isOk) {
            throw new DaoException("删除组织语言失败");
        }
        return isOk;
    }

    @Override
    public List<Department> findByParentId(int id) {
        return baseMapper.selectByParentId(id);
    }

    private List<ZTreeVO<TreeDataVO>> generateOrgTree(String lang) {
        final List<Department> organizations = findAll(lang);
        if (CollectionUtil.isEmpty(organizations)) {
            return Collections.emptyList();
        }
        return listToZtree(organizations);
    }

    private List<ZTreeVO<TreeDataVO>> generateOrgTreeByName(String name, String lang) {
        final String likeName = SqlUtils.concatLike(name, SqlLike.DEFAULT);
        List<Department> organizations = baseMapper.selectByLikeName(likeName, lang);
        if (CollectionUtil.isEmpty(organizations)) {
            return Collections.emptyList();
        }
        return listToZtree(organizations);
    }

    private static List<ZTreeVO<TreeDataVO>> listToZtree(List<Department> organizations) {
        final int organizationSize = organizations.size();
        final List<ZTreeVO<TreeDataVO>> zTreeVOList = Lists
                .newArrayListWithCapacity(organizationSize);
        for (Department organization : organizations) {
            final int parentId = MoreObjects.firstNonNull(organization.getParent(), 0);
            TreeDataVO treeDataVO = new TreeDataVO();
            treeDataVO.setId(organization.getId());
            treeDataVO.setCode(organization.getCode());
            treeDataVO.setName(organization.getName());
            treeDataVO.setParentId(parentId);
            final ZTreeVO<TreeDataVO> zTreeVO = ZTreeVO.<TreeDataVO>builder()
                    .data(treeDataVO)
                    .id("00" + organization.getId())
                    .pid(parentId == 0 ? StringPool.ZERO : "00" + parentId)
                    .name(organization.getName())
                    .build();
            zTreeVOList.add(zTreeVO);
        }
        return zTreeVOList;
    }
    @Override
    public DepartmentDto selectOneById(int id) {
        return baseMapper.selectOneById(id);
    }

    @Override
    public List<SecurityUserDto> selectListByArea() {
        return baseMapper.selectListByArea();
    }

    /**
     * 通过某个用户的id查询用户所在大区的对应部门信息
     */
    @Override
    public Department findUserAreaDepartment(int userId) {
        return baseMapper.findUserAreaDepartment(userId);
    }

    @Override
    public List<DepartmentApprovalDto> selectApprovalList() {
        return baseMapper.selectApprovalList();
    }

    @Override
    public List<Department> selectDepartmentByUser(int userId ,String lang) {
        return baseMapper.selectDepartmentByUser(userId  , lang);
    }

    @Override
    public Department selectDeptByIds(List<Integer> deptIdList) {
        return baseMapper.selectDeptByIds(deptIdList);
    }

    @Override
    public List<Department> selectDataByUser(int user) {
        return baseMapper.selectDataByUser(user);
    }

    @Override
    public List<DepartmentDto> selectChildList() {
        List<Department> lists = baseMapper.selectChildList();
        Map<Integer, DepartmentDto> map = Maps.newHashMap();
        for (Department department : lists) {
            DepartmentDto dto = new DepartmentDto(department);
            map.put(dto.getId(), dto);
        }
        List<DepartmentDto> result = Lists.newArrayList();
        for (DepartmentDto dto : map.values()) {
            if (3 == dto.getLevel()) {
                map.get(dto.getParent()).getChildren().add(dto);
            } else {
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public Department selectParentById(int id) {
        return baseMapper.selectParentById(id);
    }

    @Override
    public List<AppAreas> selectAppArea(int userId) {
        return baseMapper.selectAppArea(userId);
    }
}

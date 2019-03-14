package com.jtech.toa.service.impl.file;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.jtech.marble.StringPool;
import com.jtech.marble.util.CollectionUtil;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.dao.FileSecurityUserMapper;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.entity.file.FileSecurityUser;
import com.jtech.toa.service.file.IFileSecurityUserService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.model.dto.SecurityUserDto;
import com.jtech.toa.user.model.vo.TreeDataVO;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-11-11
 */
@Service
public class FileSecurityUserServiceImpl extends ServiceImpl<FileSecurityUserMapper, FileSecurityUser> implements IFileSecurityUserService {
	private final IDepartmentService departmentService;
	private final IUserService userService;

	@Autowired
    public FileSecurityUserServiceImpl(IDepartmentService departmentService, IUserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @Override
    public List<ZTreeVO<TreeDataVO>> findAllToTree() {
        List<SecurityUserDto> securityUserDtoList = userService.selectUserList();
        //等级为1的是总部
        Department department = departmentService.selectOne(new EntityWrapper<Department>().eq("level", 1));
        //遍历所有用户，查找area为0的用户，为总部，再从部门中查找总部，替换parent为部门总部数据的id
        for (SecurityUserDto securityUserDto : securityUserDtoList) {
            if (securityUserDto.getParent() == 0) {
                securityUserDto.setParent(department.getId());
            }
        }
        List<SecurityUserDto> securityDptList = departmentService.selectListByArea();
        securityDptList.addAll(securityUserDtoList);
        if (CollectionUtil.isEmpty(securityDptList)) {
            return Collections.emptyList();
        }
        return listToZtree(securityDptList);
    }

    private static List<ZTreeVO<TreeDataVO>> listToZtree(List<SecurityUserDto> securityDptList) {
        final int securitySize = securityDptList.size();
        final List<ZTreeVO<TreeDataVO>> zTreeVOList = Lists
                .newArrayListWithCapacity(securitySize);
        for (SecurityUserDto security : securityDptList) {
            final int parentId = MoreObjects.firstNonNull(security.getParent(), 0);
            TreeDataVO treeDataVO = new TreeDataVO();
            treeDataVO.setId(security.getId());
            treeDataVO.setName(security.getName());
            treeDataVO.setParentId(parentId);
            final ZTreeVO<TreeDataVO> zTreeVO = ZTreeVO.<TreeDataVO>builder()
                    .data(treeDataVO)
                    .id("00" + security.getId())
                    .pid(parentId == 0 ? StringPool.ZERO : "00" + parentId)
                    .name(security.getName())
                    .build();
            zTreeVOList.add(zTreeVO);
        }
        return zTreeVOList;
    }

    @Override
    public List<FileSecurityUser> selectSecurityUser(int fileId) {
        return baseMapper.selectSecurityUser(fileId);
    }

    @Override
    public boolean deleteByFile(Long fileId) {
        return baseMapper.deleteByFile(fileId) > 0;
    }

    @Override
    public boolean insertSecurity(FileSecurityUser fileSecurityUser) {
        return baseMapper.insertSecurity(fileSecurityUser) == 1;
    }

    @Override
    public FileSecurityUser selectByUserAndFile(int fileId, int userId) {
        return baseMapper.selectByUserAndFile(fileId, userId);
    }
}

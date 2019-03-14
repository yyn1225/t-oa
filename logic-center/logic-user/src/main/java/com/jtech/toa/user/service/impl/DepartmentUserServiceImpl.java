package com.jtech.toa.user.service.impl;

import com.jtech.toa.user.entity.DepartmentUser;
import com.jtech.toa.user.dao.DepartmentUserMapper;
import com.jtech.toa.user.service.IDepartmentUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
@Service
public class DepartmentUserServiceImpl extends ServiceImpl<DepartmentUserMapper, DepartmentUser> implements IDepartmentUserService {

    @Override
    public boolean deleteByUser(int user) {
        return baseMapper.deleteByUser(user) > 0;
    }
}

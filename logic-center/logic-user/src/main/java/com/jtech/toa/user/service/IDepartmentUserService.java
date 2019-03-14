package com.jtech.toa.user.service;

import com.jtech.toa.user.entity.DepartmentUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
public interface IDepartmentUserService extends IService<DepartmentUser> {
    /**
     * 通过id删除一条记录
     * @param user 用户主键
     * @return 整形
     */
    boolean deleteByUser(int user);
}

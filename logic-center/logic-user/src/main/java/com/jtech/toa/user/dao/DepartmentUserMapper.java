package com.jtech.toa.user.dao;

import com.jtech.toa.user.entity.DepartmentUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-17
 */
public interface DepartmentUserMapper extends BaseMapper<DepartmentUser> {
    /**
     * 通过id删除一条记录
     * @param user 用户主键
     * @return 整形
     */
    int deleteByUser(@Param("user") int user);
}
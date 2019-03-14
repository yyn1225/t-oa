package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.customer.CustomerUser;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
public interface CustomerUserMapper extends BaseMapper<CustomerUser> {
CustomerUser getUserId(@Param("customer")int customer);
int saveCustomerUser(CustomerUser customerUser);
int deleteByCustomerUser(CustomerUser customerUser);
}
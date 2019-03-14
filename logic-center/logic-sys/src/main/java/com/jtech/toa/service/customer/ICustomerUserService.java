package com.jtech.toa.service.customer;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.customer.CustomerUser;

/**
 * <p> 服务类 </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
public interface ICustomerUserService extends IService<CustomerUser> {
    CustomerUser getUserId(int customer);

    boolean saveCustomerUser(CustomerUser customerUser);

    boolean deleteCustomerUser(CustomerUser customerUser);

    /**
     * 保存客户的用户分配人
     *
     * @param customerId 客户的id
     * @param userId     用户的id
     */
    boolean saveCustomerUser(Integer customerId, Integer userId);
}

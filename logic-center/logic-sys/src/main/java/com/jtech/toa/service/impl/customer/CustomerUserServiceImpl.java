package com.jtech.toa.service.impl.customer;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.CustomerUserMapper;
import com.jtech.toa.entity.customer.CustomerUser;
import com.jtech.toa.service.customer.ICustomerUserService;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
@Service
public class CustomerUserServiceImpl extends ServiceImpl<CustomerUserMapper, CustomerUser> implements ICustomerUserService {

    @Override
    public CustomerUser getUserId(int customer) {
        return baseMapper.getUserId(customer);
    }

    @Override
    public boolean saveCustomerUser(CustomerUser customerUser) {
        return baseMapper.saveCustomerUser(customerUser) == 1;
    }

    @Override
    public boolean deleteCustomerUser(CustomerUser customerUser) {
        return baseMapper.deleteByCustomerUser(customerUser) == 1;
    }


    public boolean saveCustomerUser(Integer customerId, Integer userId) {
        CustomerUser customerUser = new CustomerUser();
        customerUser.setCustomer(customerId);
        customerUser.setUsers(userId);
        customerUser.setAssigner(userId);
        customerUser.setAssignTime(DateTime.now().toDate());
        return insert(customerUser);
    }
}

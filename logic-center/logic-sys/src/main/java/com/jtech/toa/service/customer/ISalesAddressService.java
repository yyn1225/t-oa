package com.jtech.toa.service.customer;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.entity.customer.SalesAddress;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
public interface ISalesAddressService extends IService<SalesAddress> {


    /**
     * 修改地址
     * @param billAddress 收获地址
     * @param shipAddress 账单地址
     * @param customer 客户信息
     * @return 是否成功
     */
    boolean updateAddress(String billAddress, String shipAddress, Customer customer);

    /**
     * 新增客户的地址
     * @param billAddress 收获地址
     * @param shipAddress 账单地址
     * @param customer 客户信息
     * @return 是否成功
     */
    boolean saveAddress(String billAddress,String shipAddress,Customer customer);
}

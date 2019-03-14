package com.jtech.toa.service.impl.customer;

import com.google.common.base.Strings;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.SalesAddressMapper;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.entity.customer.SalesAddress;
import com.jtech.toa.service.customer.ISalesAddressService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
@Service
public class SalesAddressServiceImpl extends ServiceImpl<SalesAddressMapper, SalesAddress> implements ISalesAddressService {

    @Transactional
    @Override
    public boolean updateAddress(String billAddress, String shipAddress, Customer customer) {
        boolean isOk = true;
        if (Strings.isNullOrEmpty(shipAddress)) {
            if (customer.getShipping() != null && customer.getShipping() > 0) {
                isOk = deleteById(customer.getShipping());
            }
            customer.setShipping(0);
        } else {
            SalesAddress address = new SalesAddress();
            address.setDetails(shipAddress);
            if (customer.getShipping() != null && customer.getShipping() > 0) {
                int id = customer.getShipping();
                address.setId(id);
                isOk = updateById(address);
            } else {
                isOk = insert(address);
                customer.setShipping(address.getId());
            }
        }
        if (Strings.isNullOrEmpty(billAddress)) {
            if (customer.getBilling() != null && customer.getBilling() > 0) {
                isOk = deleteById(customer.getBilling());
            }
            customer.setBilling(0);
        } else {
            SalesAddress address = new SalesAddress();
            address.setDetails(billAddress);
            if (customer.getBilling() != null && customer.getBilling() > 0) {
                int id = customer.getBilling();
                address.setId(id);
                isOk = updateById(address);
            } else {
                isOk = insert(address);
                customer.setBilling(address.getId());
            }

        }
        return isOk;
    }

    @Transactional
    @Override
    public boolean saveAddress(String billAddress, String shipAddress, Customer customer) {
        boolean isOk = true;
        if (Strings.isNullOrEmpty(shipAddress)) {
            customer.setBilling(0);
        } else {
            SalesAddress billAddressObj = new SalesAddress();
            billAddressObj.setDetails(shipAddress);
            isOk = insert(billAddressObj);
            customer.setShipping(billAddressObj.getId());
        }
        if (Strings.isNullOrEmpty(billAddress)) {
            customer.setBilling(0);
        } else {
            SalesAddress billAddressObj = new SalesAddress();
            billAddressObj.setDetails(billAddress);
            isOk = insert(billAddressObj);
            customer.setBilling(billAddressObj.getId());
        }
        return isOk;
    }


}

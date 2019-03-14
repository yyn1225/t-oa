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

package com.jtech.toa.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.model.dto.sys.CustomerDto;
import com.jtech.toa.model.query.CustomerQuery;

import java.util.List;
import java.util.Map;

/**
 * <p> 服务类 </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
public interface ICustomerService extends IService<Customer> {

    /**
     * 根据分页信息获取客户数据
     *
     * @param query 分页及查询参数
     * @return 客户数据
     */
    void selectCustomerListByPage(Page<CustomerDto> requestPage, CustomerQuery query);

    List<CustomerDto> selectCustomerLevel(int area , String lang);

    boolean deleteByCustomer(int customer);

    /**
     * 查询列表给前台使用
     *
     * @param requestPage   分页对象
     * @param customerQuery 查询对象
     */
    void findCustomerListByPageForProtal(Page<CustomerDto> requestPage, CustomerQuery customerQuery,int userId,int area);

    /**
     * 获取客户，根据最后一条Id分页
     * @param customerQuery 查询参数
     * @param userId 当前用户Id
     * @param area 地区
     * @param lastId 最后一条信息Id
     * @return 客户数据
     */
    List<CustomerDto> findCustomerListByLastIdForProtal(CustomerQuery customerQuery,int userId,int area,int lastId);

    /**
     * 更新客户的信息，包括账单接受地址和收货地址
     *
     * @param customer    客户的对象
     * @param shipAddress 账单接受地址
     * @param billAddress 收货地址
     * @return 是否成功
     */
    boolean updateInfo(Customer customer, String shipAddress, String billAddress);

    /**
     * 前台新增客户信息，包括账单接受地址和收获地址
     * @param customer 客户的信息
     * @param shipAddress 账单接受地址
     * @param billAddress 收获地址
     * @param userId 用户的id
     * @return 是否成功
     */
    boolean saveInfoForProtal(Customer customer, String shipAddress, String billAddress, int userId);

    /**
     * 后台新增客户信息，包括账单接受地址和收获地址
     * @param customer 客户的信息
     * @param shipAddress 账单接受地址
     * @param billAddress 收获地址
     * @return
     */
    boolean saveInfoForAdmin(Customer customer, String shipAddress, String billAddress);

    /**
     * 查找我的客户列表
     * @return
     */
    List<Customer> findMyCustomer(int user);

    /**
     * 根据参数查找我的客户并且
     * @param user 关联用户Id
     * @return
     */
    List<Customer> findMyAndAutosuggestCustomer(CustomerQuery query, int user);

    /**
     * 通过时间查询客户列表
     * @return 客户列表
     */
    List<List<Customer>> selectByTime(int userId);

    /**
     * 查找所有没有指定分配人的客户信息
     * @return
     */
    List<Customer> findUnsignedList();

    /**
     * 将客户数据转换成Mao数组
     * @param customers
     * @return
     */
    List<Map<String,Object>> customers2Maps(List<Customer> customers);

    List<Customer> findMyCustomerPage(int id, int lastId);

    List<Customer> findMyCustomerAllPage(int id, int lastId);
}

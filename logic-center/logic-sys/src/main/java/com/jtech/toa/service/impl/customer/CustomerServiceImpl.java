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

package com.jtech.toa.service.impl.customer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.exception.DaoException;
import com.jtech.toa.dao.CustomerMapper;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.model.dto.sys.CustomerDto;
import com.jtech.toa.model.query.CustomerQuery;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.customer.ICustomerUserService;
import com.jtech.toa.service.customer.ISalesAddressService;
import com.jtech.toa.service.system.IRoleUserService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> 服务实现类 </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    private final IUserService userService;
    private final ISalesAddressService salesAddressService;
    private final IDepartmentService departmentService;
    private final ICustomerUserService customerUserService;
    private final IRoleUserService roleUserService;

    @Autowired
    public CustomerServiceImpl(ISalesAddressService salesAddressService,
                               IDepartmentService departmentService,
                               ICustomerUserService customerUserService,
                               IUserService userService,
                               IRoleUserService roleUserService) {
        this.salesAddressService = salesAddressService;
        this.customerUserService = customerUserService;
        this.departmentService = departmentService;
        this.userService = userService;
        this.roleUserService = roleUserService;
    }


    @Override
    public void selectCustomerListByPage(Page<CustomerDto> requestPage, CustomerQuery query) {
        List<CustomerDto> customerDtos = baseMapper.selectCustomerListByPage(requestPage, query);
        requestPage.setRecords(customerDtos);
    }

    @Override
    public List<CustomerDto> selectCustomerLevel(int area , String lang) {
        return baseMapper.selectCustomerLevel(area  , lang);
    }

    @Override
    public boolean deleteByCustomer(int customer) {
        return baseMapper.deleteByCustomer(customer) == 1;
    }

    @Override
    public List<CustomerDto> findCustomerListByLastIdForProtal(CustomerQuery customerQuery,int userId,int area,int lastId){
        List<Department> departments = this.departmentService.selectDataByUser(userId);
        customerQuery.setUserId(userId);
        if(departments.size() <= 0){
            return Lists.newArrayList();
        }
        int level = departments.get(0).getLevel();
        List<CustomerDto> customerDtos = Lists.newArrayList();
        if(level == 1){
            //总
            customerDtos = baseMapper.selectCustomerListAllByLastId(customerQuery, lastId);
        }else if(level == 2){
            //大区
            customerDtos = baseMapper.selectCustomerListRegionByLastId(customerQuery, lastId,area);
        }else{
            //区域
            List<CustomerDto> responsibleDtos = baseMapper.selectCustomerListResponsibleByLastId(customerQuery, lastId);//负责
            customerDtos.addAll(responsibleDtos);
        }
        return customerDtos;
    }

    @Override
    public void findCustomerListByPageForProtal(Page<CustomerDto> requestPage, CustomerQuery customerQuery,int userId,int area) {
        List<Department> departments = this.departmentService.selectDataByUser(userId);
        if(departments.size() <= 0){
            return;
        }
        int level = departments.get(0).getLevel();
        List<CustomerDto> customerDtos = Lists.newArrayList();
        if(level == 1){
            //总
            customerDtos = baseMapper.selectCustomerListAllByPage(requestPage, customerQuery);
        }else if(level == 2){
            //大区
            customerDtos = baseMapper.selectCustomerListRegionByPage(requestPage, customerQuery,area);
        }else{
            //区域
            List<CustomerDto> responsibleDtos = baseMapper.selectCustomerListResponsibleByPage(requestPage, customerQuery);//负责
            customerDtos.addAll(responsibleDtos);
        }
        requestPage.setRecords(customerDtos);
    }

    @Transactional
    @Override
    public boolean updateInfo(Customer customer, String shipAddress, String billAddress) {
        boolean isOk;
        isOk = salesAddressService.updateAddress(billAddress, shipAddress, customer);
        if (!isOk) {
            throw new DaoException("更新地址信息出错");
        }
        isOk = updateById(customer);
        if (!isOk) {
            throw new DaoException("更新客户信息出错");
        }
        return isOk;
    }

    @Transactional
    @Override
    public boolean saveInfoForProtal(Customer customer, String shipAddress, String billAddress, int userId) {
        boolean isOk;
        isOk = salesAddressService.saveAddress(billAddress, shipAddress, customer);
        if (!isOk) {
            throw new DaoException("添加地址信息出错");
        }
        customer.setStatus(1);
        customer.setDeleteAble(Customer.DeleteAble.YES);
        isOk = insert(customer);
        if (!isOk) {
            throw new DaoException("添加客户信息出错");
        }
        isOk = customerUserService.saveCustomerUser(customer.getId(), userId);
        if (!isOk) {
            throw new DaoException("添加客户分配人信息出错");
        }
        return isOk;
    }

    @Override
    public boolean saveInfoForAdmin(Customer customer, String shipAddress, String billAddress) {
        boolean isOk;
        isOk = salesAddressService.saveAddress(billAddress, shipAddress, customer);
        if (!isOk) {
            throw new DaoException("添加地址信息出错");
        }
        customer.setStatus(1);
        customer.setDeleteAble(Customer.DeleteAble.YES);
        isOk = insert(customer);
        if (!isOk) {
            throw new DaoException("添加客户信息出错");
        }
        return isOk;
    }

    /**
     * 查找我的客户列表
     */
    @Override
    public List<Customer> findMyCustomer(int user) {
        return baseMapper.findMyCustomer(user);
    }

    /**
     * 查找我的客户列表
     * 根据参数查找我的客户并且
     * @param user 关联用户Id
     */
    @Override
    public List<Customer> findMyAndAutosuggestCustomer(CustomerQuery query, int user) {
        return baseMapper.findMyAndAutosuggestCustomer(query,user);
    }

    @Override
    public List<List<Customer>> selectByTime(int userId) {
        //获取一周前的时间
        Calendar week = Calendar.getInstance();
        week.setTime(new Date());
        week.add(Calendar.DATE, - 7);

        //获取一月前时间
        Calendar month = Calendar.getInstance();
        month.setTime(new Date());
        month.add(Calendar.MONTH, -1);

        //获取一年前的时间
        Calendar year = Calendar.getInstance();
        year.setTime(new Date());
        year.add(Calendar.YEAR, - 1);

        //获取三年前的时间(排除一年)
        Calendar threeYears = Calendar.getInstance();
        threeYears.setTime(new Date());
        threeYears.add(Calendar.YEAR, - 3);

        List<List<Customer>> customerList = new ArrayList<>();

        List<Customer> lastWeekCustomer = baseMapper.selectByTime(week.getTime(), new Date(), userId);
        List<Customer> lastMonthCustomer = baseMapper.selectByTime(month.getTime(), week.getTime(), userId);
        List<Customer> lastYearCustomer = baseMapper.selectByTime(year.getTime(), month.getTime(), userId);
        List<Customer> lastThreeYearCustomer = baseMapper.selectByTime(threeYears.getTime(), year.getTime(), userId);

        customerList.add(lastWeekCustomer);
        customerList.add(lastMonthCustomer);
        customerList.add(lastYearCustomer);
        customerList.add(lastThreeYearCustomer);
        return customerList;
    }

    @Override
    public List<Customer> findUnsignedList() {
        return baseMapper.findUnSingedList();
    }

    /**
     * 判断是否存在重复
     */
    private boolean customerDtoExist(List<CustomerDto> dtos,CustomerDto dto){
        for(CustomerDto customerDto: dtos){
            if(customerDto.getId().compareTo(dto.getId()) == 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Map<String,Object>> customers2Maps(List<Customer> customers){
        List<Map<String,Object>> list = Lists.newArrayList();
        for(Customer customer : customers){
            Map<String,Object> map = Maps.newHashMap();
            map.put("id",customer.getId());
            map.put("label",customer.getName());
            map.put("value",customer.getName());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Customer> findMyCustomerPage(int id, int lastId) {
        return baseMapper.findMyCustomerPage(id,lastId);
    }

    @Override
    public List<Customer> findMyCustomerAllPage(int id, int lastId) {
        boolean isAssistant = roleUserService.findUserRoleByIdAndRole(id,"销售助理");//todo 暂时用中文名称比对
        if(isAssistant){//判断是否为助理
            List<User> userList = userService.selectList(new EntityWrapper<User>().eq
                    ("assistant", id));
            List<Integer> ids = Lists.newArrayList();
            for(User user : userList){
                ids.add(user.getId());
            }
            return baseMapper.findMyCustomerOfassistantPage(ids,lastId);
        }else{
            return baseMapper.findMyCustomerPage(id,lastId);
        }
    }
}

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

package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.model.dto.sys.CustomerDto;
import com.jtech.toa.model.query.CustomerQuery;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-25
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 根据分页信息获取客户数据
     * @param query 分页及查询参数
     * @return 客户数据
     */
    List<CustomerDto> selectCustomerListByPage(Page<CustomerDto> requestPage, @Param("query") CustomerQuery query);

    List<CustomerDto> selectCustomerLevel(@Param("area")int area ,@Param("lang") String lang );

    int deleteByCustomer(@Param("customer")int customer);

    /**
     * 查询列表给前台使用
     * @param requestPage 分页对象
     * @param customerQuery 查询对象
     */
    List<CustomerDto> selectCustomerListByPageForProtal(Page<CustomerDto> requestPage,
                                                        @Param("query") CustomerQuery customerQuery);

    /**
     * 总
     * @param customerQuery 查询参数
     * @return 查询到的数据
     */
    List<CustomerDto> selectCustomerListAllByLastId(@Param("query") CustomerQuery customerQuery,@Param("lastId") int lastId);
    /**
     * 大区
     * @param customerQuery 查询参数
     * @param area 地区数据
     * @return 查询到的数据
     */
    List<CustomerDto> selectCustomerListRegionByLastId(@Param("query") CustomerQuery customerQuery,
                                                    @Param("area") int area,
                                                       @Param("lastId") int lastId);
    /**
     * 自己的客户
     * @param customerQuery 查询参数
     * @return 查询到的数据
     */
    List<CustomerDto> selectCustomerListResponsibleByLastId( @Param("query") CustomerQuery customerQuery,
                                                             @Param("lastId") int lastId);

    /**
     * 总
     * @param requestPage 分页数据
     * @param customerQuery 查询参数
     * @return 查询到的数据
     */
    List<CustomerDto> selectCustomerListAllByPage(Page<CustomerDto> requestPage,
                                                  @Param("query") CustomerQuery customerQuery);
    /**
     * 大区
     * @param requestPage 分页数据
     * @param customerQuery 查询参数
     * @param area 地区数据
     * @return 查询到的数据
     */
    List<CustomerDto> selectCustomerListRegionByPage(Page<CustomerDto> requestPage,
                                                     @Param("query") CustomerQuery customerQuery,
                                                     @Param("area") int area);
    /**
     * 自己的客户
     * @param requestPage 分页数据
     * @param customerQuery 查询参数
     * @return 查询到的数据
     */
    List<CustomerDto> selectCustomerListResponsibleByPage(Page<CustomerDto> requestPage, @Param("query") CustomerQuery customerQuery);
    /**
     * 查找我的客户列表
     * @param user
     * @return
     */
    List<Customer> findMyCustomer(int user);

    List<Customer> findMyCustomerPage(@Param("user") int user, @Param("lastId") int lastId);


    List<Customer> findMyCustomerOfassistantPage(@Param("ids") List<Integer> ids, @Param("lastId")
            int lastId);

    /**
     * 根据参数查找我的客户并且
     * @param user 关联用户Id
     * @return
     */
    List<Customer> findMyAndAutosuggestCustomer(@Param("query") CustomerQuery query, @Param("user") int user);

    /**
     * 通过时间查询客户列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 客户列表
     */
    List<Customer> selectByTime(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("userId") int userId);

    /**
     * 查找没有分配的客户信息，并且只查找从CRM同步过来的数据
     * @return
     */
    List<Customer> findUnSingedList();
}
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
import com.jtech.toa.entity.system.Resource;
import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.model.query.ResourceQuery;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface ResourceMapper extends BaseMapper<Resource> {


    List<Resource> selectByUserId(@Param("lang") String lang,@Param("userId") int userId);

    /**
     * 根据编码查询信息
     * @param code 编码
     * @return 编码信息
     */
    Resource selectByCode(@Param("code") String code);
//    /**
//     * 更具code查询菜单信息，排除当前的id
//     *
//     * @param code 编码信息
//     * @param id   需要排除的id
//     * @return 菜单信息
//     */
//    Resource selectByCodeAndIdNotEq(@Param("code") String code,  @Param("id") int id);

    /**
     * 根据分页信息获取菜单数据
     * @Param requestPage 分页参数
     * @param query 分页及查询参数
     * @return 菜单数据
     */
    List<ResouceDto> selectResourceListByPage(Page<ResouceDto> requestPage, @Param("query") ResourceQuery query);

    List<Resource> selectResourceList(@Param("lang") String lang);

    List<Resource> selectResourceByParent(@Param("resource")int resource);

    int deleteResource(@Param("id")int id);

}
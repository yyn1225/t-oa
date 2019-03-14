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

package com.jtech.toa.service.system;

import com.google.common.base.Optional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.system.Resource;
import com.jtech.toa.entity.system.ResourceLang;
import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.model.query.ResourceQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-10-23
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 构建树状list
     * @param lang 当前用户的语言code
     * @param userId 当前用户id
     * @return 树状菜单的集合
     */
    List<ResouceDto> genderForMenu(String lang,int userId);


    /**
     * 添加菜单
     * @param loginResourceId 登录用户id
     * @param resourceDto 资源数据传输对象
     * @param resourceLangs 资源语言集合
     * @return 布尔值
     */
    boolean addResource(int loginResourceId, ResouceDto resourceDto, List<ResourceLang> resourceLangs);

    /**
     * 更新
     * @param resourceDto 资源数据传输对象
     * @param updateList 资源语言更新集合
     * @param newList 资源语言新增集合
     */
    boolean updateResource( ResouceDto resourceDto, List<ResourceLang> updateList, List<ResourceLang> newList);

    /**
     * 根据菜单编码，获取菜单信息
     *
     * @param code 菜单编码
     * @return 菜单信息
     */
    Optional<Resource> findByCode(String code);
//    /**
//     * 根据code查询菜单信息，排除当前的id
//     * @param code 编码信息
//     * @param id 需要排除的id
//     * @return 菜单信息
//     */
//    Resource findByCodeAndIdNotEq(String code,int id);


    /**
     * 查询用户的资源信息
     * @param code 语言的code
     * @param userId 用户的id
     * @return 资源对象的集合
     */
    List<Resource> findByLangAndUserId(String code,int userId);

    /**
     * 根据分页信息获取菜单数据
     *
     * @param query 分页及查询参数
     */
    void selectResourceListByPage(Page<ResouceDto> requestPage, ResourceQuery query);

    List<Resource> selectResourceList(String lang);

    List<Resource> selectResourceByParent(int resource);

    String deleteByResourceId(int resource);
}

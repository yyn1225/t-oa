package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jtech.toa.entity.experience.ExperienceShare;
import com.jtech.toa.model.app.AppShare;
import com.jtech.toa.model.dto.experience.ExperienceDto;
import com.jtech.toa.model.query.ExperienceQuery;
import org.springframework.web.bind.annotation.PostMapping;

;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2018-08-03
 */
public interface ExperienceShareMapper extends BaseMapper<ExperienceShare> {

    /**
     * 查询列表
     * @param query
     * @return 分享列表
     */
    List<ExperienceDto> selectExperienceList(@Param("query")ExperienceQuery query, Page<ExperienceDto> requestPage);

    /**
     * app api 获取经验分享列表信息
     * @param currentUserId 当前用户id
     * @param productName 产品
     * @param shareUserName 分享人
     * @param title 标题
     * @param type 类型（1:我的；2:其他；）
     * @param id 初始为0，加载更多为当前记录的最后一条数据的id，用于分页；
     * @return
     */
    List<AppShare> getShareListForApp(@Param("currentUserId") int currentUserId,
                                      @Param("productName") String productName,
                                      @Param("shareUserName") String shareUserName,
                                      @Param("title") String title,
                                      @Param("type") int type,
                                      @Param("id") long id);

    /**
     * app api 获取经验分享详情
     * @param id 经验分享id
     * @return
     */
    AppShare getByIdForApp(@Param("id") long id, @Param("lang")  String lang);

    /**
     *  web 获取经验分享详情
     * @param id 经验分享id
     * @param lang 语言
     * @return
     */
    ExperienceDto selectDtoById(@Param("id") Long id , @Param("lang")  String lang);
}
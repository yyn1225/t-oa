/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-$today.yea
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.system.Attachment;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jtech tao.ding
 * @since 2017-07-10
 */
public interface AttachmentMapper extends BaseMapper<Attachment> {

    /**
     * 查询附件的信息
     * @param ids ids
     * @return 附件的集合
     */
    List<Attachment> selectByIds(@Param("ids") String[] ids);
}
package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.basic.Certification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface CertificationMapper extends BaseMapper<Certification> {

    /**
     * 查询所有认证信息
     * @return 认证信息列表
     */
    List<Certification> selectCertificationList();

    /**
     * 通过系列id获取该系列下的所有产品对应的认证信息
     * @param series
     * @return
     */
    List<Certification> selectCertificationListBySeries(int series);

    /**
     * 通过编码查询一条认证信息
     * @param code 编码
     * @return 认证信息
     */
    List<Certification> selectByCode(@Param("code") String code);
}

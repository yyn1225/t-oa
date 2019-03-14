package com.jtech.toa.service;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.basic.Certification;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
public interface ICertificationService extends IService<Certification> {
    /**
     * 查询所有认证信息
     * @return 认证信息列表
     */
    List<Certification> selectCertificationList();

    List<Certification> selectCertificationListBySeries(int series);

    /**
     * 通过编码查询一条认证信息
     * @param code 编码
     * @return 认证信息
     */
    List<Certification> selectByCode(String code);
}

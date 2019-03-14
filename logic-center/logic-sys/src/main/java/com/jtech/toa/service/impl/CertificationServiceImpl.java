package com.jtech.toa.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.CertificationMapper;
import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.service.ICertificationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class CertificationServiceImpl extends ServiceImpl<CertificationMapper, Certification> implements ICertificationService {
    @Override
    public List<Certification> selectCertificationList() {
        return baseMapper.selectCertificationList();
    }

    @Override
    public List<Certification> selectCertificationListBySeries(int series) {
        return baseMapper.selectCertificationListBySeries(series);
    }

    @Override
    public List<Certification> selectByCode(String code) {
        return baseMapper.selectByCode(code);
    }
}

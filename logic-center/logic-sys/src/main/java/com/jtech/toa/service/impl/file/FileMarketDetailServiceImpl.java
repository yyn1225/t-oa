package com.jtech.toa.service.impl.file;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.toa.dao.FileMarketDetailMapper;
import com.jtech.toa.entity.file.FileMarketDetail;
import com.jtech.toa.model.dto.files.AppFileMarket;
import com.jtech.toa.model.query.FileMarketDetailQuery;
import com.jtech.toa.service.file.IFileMarketDetailService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2017-12-23
 */
@Service
public class FileMarketDetailServiceImpl extends ServiceImpl<FileMarketDetailMapper, FileMarketDetail> implements IFileMarketDetailService {
    /**
     * 获取全部数据
     * @return 数据
     */
    @Override
    public List<FileMarketDetail> selectSpareListAll(FileMarketDetailQuery query) {
        return baseMapper.selectSpareListAll(query);
    }

    @Override
    public List<AppFileMarket> findAppMarketList() {
        return baseMapper.findAppMarketList();
    }

    @Override
    public List<AppFileMarket> findApiAppMarketList() {
        return baseMapper.findApiAppMarketList();
    }
}

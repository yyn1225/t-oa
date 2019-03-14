package com.jtech.toa.service.file;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.file.FileMarketDetail;
import com.jtech.toa.model.dto.files.AppFileMarket;
import com.jtech.toa.model.query.FileMarketDetailQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtech
 * @since 2017-12-23
 */
public interface IFileMarketDetailService extends IService<FileMarketDetail> {

    /**
     * 获取全部数据
     * @return 数据
     */
    List<FileMarketDetail> selectSpareListAll(FileMarketDetailQuery query);

    List<AppFileMarket> findAppMarketList();

    List<AppFileMarket> findApiAppMarketList();
}

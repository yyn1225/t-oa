package com.jtech.toa.dao;

import com.jtech.toa.entity.file.FileMarketDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.model.dto.files.AppFileMarket;
import com.jtech.toa.model.query.FileMarketDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jtech
 * @since 2017-12-23
 */
public interface FileMarketDetailMapper extends BaseMapper<FileMarketDetail> {

    /**
     * 获取全部数据
     * @return 数据
     */
    List<FileMarketDetail> selectSpareListAll(@Param("query") FileMarketDetailQuery query);

    List<AppFileMarket> findAppMarketList();


    List<AppFileMarket> findApiAppMarketList();
}
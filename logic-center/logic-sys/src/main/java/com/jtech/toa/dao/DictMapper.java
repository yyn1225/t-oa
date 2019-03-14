package com.jtech.toa.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jtech.toa.entity.basic.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface DictMapper extends BaseMapper<Dict> {

    List<Dict> selectDictByCategory(@Param("category") String category,@Param("lang")String lang);

    Dict selectDictByCode(@Param("category") String category, @Param("code") String code,@Param("lang")String lang);
}

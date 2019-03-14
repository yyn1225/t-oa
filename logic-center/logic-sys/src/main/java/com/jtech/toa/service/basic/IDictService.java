package com.jtech.toa.service.basic;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.basic.Dict;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface IDictService extends IService<Dict> {

    List<Dict> selectDictByCategory(String category,String lang);

    Dict selectDictByCode(String category, String code,String lang);

    /**
     * 把字典转换成多个map
     * @param dicts 字典
     * @return 数据
     */
    List<Map<String,Object>> list2ListMap(List<Dict> dicts);
}

package com.jtech.toa.service.impl.basic;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.toa.dao.DictMapper;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.service.basic.IDictService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    public List<Dict> selectDictByCategory(String category,String lang) {
        return baseMapper.selectDictByCategory(category,lang);
    }

    @Override
    public Dict selectDictByCode(String category, String code,String lang) {
        return baseMapper.selectDictByCode(category, code, lang);
    }

    @Override
    public List<Map<String,Object>> list2ListMap(List<Dict> dicts){
        List<Map<String,Object>> list = Lists.newArrayList();
        for(Dict dict: dicts){
            Map<String,Object> map = Maps.newHashMap();
            map.put("id",dict.getCode());
            map.put("value",dict.getName());
            list.add(map);
        }
        return list;
    }
}

package com.jtech.toa.service.impl.system;

import com.google.common.collect.Maps;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jtech.toa.dao.system.TranslateMapper;
import com.jtech.toa.entity.system.Translate;
import com.jtech.toa.service.system.ITranslateService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtech
 * @since 2018-03-12
 */
@Service
public class TranslateServiceImpl extends ServiceImpl<TranslateMapper, Translate> implements ITranslateService {

    @Override
    public Map<String,String> getForList() {
        List<Translate> translateList =  baseMapper.getForList();
        Map<String,String> translateMap = Maps.newHashMap();
        if(!translateList.isEmpty()){
            for(Translate translate:translateList){
                String key = translate.getCategory()+"-"+translate.getChinese();
                if(translateMap.get(key)==null){
                    translateMap.put(key,translate.getTranslate());
                }
            }
        }
        return translateMap;
    }
}

package com.jtech.toa.service.customer;

import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.customer.LevelLang;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ISalesLevelLangService extends IService<LevelLang> {

    List<LevelLang> selectByLevelId(int levelId);

}

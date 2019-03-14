package com.jtech.toa.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.entity.customer.LevelLang;
import com.jtech.toa.model.query.SalesLevelQuery;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public interface ISalesLevelService extends IService<Level> {
    /**
     * 根据分页信息获取产品数据
     * @param requestPage 分页
     * @param query 分页及查询参数
     */
    void selectLevelListByPage(Page<Level> requestPage, SalesLevelQuery query);

    /**
     * 插入等级
     * @param level 等级实体类
     * @param levelLangList 客户等级语言列表
     * @return 布尔值
     */
    boolean insertLevel(Level level, List<LevelLang> levelLangList);

    /**
     * 编辑等级
     * @param level 等级实体类
     * @param updateList 更新的语言列表
     * @param newList 新增的语言列表
     * @return 布尔值
     */
    boolean updateLevel(Level level,List<LevelLang> updateList, List<LevelLang> newList);

    /**
     * 根据code查询等级信息，排除当前的id
     * @param code 编码信息
     * @param id 需要排除的id
     * @return 等级对象
     */
    Level findByCodeAndIdNotEq(String code, int id);

    /**
     * 根据code查询等级信息
     * @param code 编码
     * @return 等级对象
     */
    Level findByCode(String code);

    Level selectByIdAndLang(int levelId, String lang);

    Level findByCustomerId(int customer);
}

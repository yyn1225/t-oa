package com.jtech.toa.service.impl.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.dao.SalesLevelMapper;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.entity.customer.LevelLang;
import com.jtech.toa.model.query.SalesLevelQuery;
import com.jtech.toa.service.customer.ISalesLevelLangService;
import com.jtech.toa.service.customer.ISalesLevelService;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class SalesLevelServiceImpl extends ServiceImpl<SalesLevelMapper, Level> implements ISalesLevelService {

    private IUserService userService;
    private ISalesLevelLangService salesLevelLangService;

    @Autowired
    public SalesLevelServiceImpl(IUserService userService, ISalesLevelLangService salesLevelLangService) {
        this.userService = userService;
        this.salesLevelLangService = salesLevelLangService;
    }

    @Override
    public void selectLevelListByPage(Page<Level> requestPage, SalesLevelQuery query) {
        List<Level> levelList = baseMapper.selectLevelListByPage(requestPage, query);
        requestPage.setRecords(levelList);
    }
    @Override
    public boolean insertLevel(Level level, List<LevelLang> levelLangList) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        BigDecimal bigDecimal = new BigDecimal(100);
        if (level.getDiscount() != null) {
            BigDecimal discount = level.getDiscount().divide(bigDecimal);
            level.setDiscount(discount);
        }
        if (level.getProfit() != null) {
            BigDecimal profit = level.getProfit().divide(bigDecimal);
            level.setProfit(profit);
        }
        level.setOrg(userAppDto.getArea());
        level.setDiscount(level.getDiscount());
        boolean ok = insert(level);
        if (!ok) {
            throw new DaoException("添加等级失败");
        }
        for (LevelLang levelLang : levelLangList) {
            levelLang.setLevel(level.getId());
            ok = salesLevelLangService.insert(levelLang);
        }
        return ok;
    }

    @Override
    public boolean updateLevel(Level level, List<LevelLang> updateList, List<LevelLang> newList) {
        BigDecimal bigDecimal = new BigDecimal(100);
        if (level.getDiscount() != null) {
            BigDecimal discount = level.getDiscount().divide(bigDecimal);
            level.setDiscount(discount);
        }else {
            level.setDiscount(new BigDecimal(0));
        }
        if (level.getProfit() != null) {
            BigDecimal profit = level.getProfit().divide(bigDecimal);
            level.setProfit(profit);
        }else {
            level.setProfit(new BigDecimal(0));
        }
        boolean ok = updateById(level);
        if (!ok) {
            throw new DaoException("更新等级失败");
        }
        if (updateList.size() > 0) {
            for (LevelLang levelLang : updateList) {
                levelLang.setLevel(level.getId());
                salesLevelLangService.updateById(levelLang);
            }
        }
        if (newList.size() > 0) {
            for (LevelLang levelLang : newList) {
                levelLang.setLevel(level.getId());
                salesLevelLangService.insert(levelLang);
            }
        }
        return ok;
    }

    @Override
    public Level findByCodeAndIdNotEq(String code, int id) {
        return baseMapper.findByCodeAndIdNotEq(code, id);
    }

    @Override
    public Level findByCode(String code) {
        return baseMapper.findByCode(code);
    }

    @Override
    public Level selectByIdAndLang(int levelId, String lang) {
        return baseMapper.selectByIdAndLang(levelId, lang);
    }

    @Override
    public Level findByCustomerId(int customer) {
        return baseMapper.findByCustomerId(customer);
    }


}

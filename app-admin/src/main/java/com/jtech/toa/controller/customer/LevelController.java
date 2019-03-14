package com.jtech.toa.controller.customer;

import com.google.common.collect.Maps;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.entity.customer.LevelLang;
import com.jtech.toa.entity.system.Language;
import com.jtech.toa.service.customer.ISalesLevelLangService;
import com.jtech.toa.service.customer.ISalesLevelService;
import com.jtech.toa.service.system.ILanguageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class LevelController {
    private ISalesLevelService salesLevelService;
    private ILanguageService languageService;
    private ISalesLevelLangService salesLevelLangService;

    @Autowired
    public LevelController(ILanguageService languageService, ISalesLevelService salesLevelService,
                           ISalesLevelLangService salesLevelLangService) {
        this.salesLevelService = salesLevelService;
        this.languageService = languageService;
        this.salesLevelLangService = salesLevelLangService;
    }

    @GetMapping("/customer/level")
    public String index() {
        return "customer/level/list";
    }

    @GetMapping("/customer/level/addOrEdit")
    public String addOrEdit(int id, Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        List<Language> languages = languageService.selectLanguageList();
        if (id != 0) {
            List<LevelLang> levelLangs = salesLevelLangService.selectByLevelId(id);
            Map<String, LevelLang> levelLangMap = Maps.newHashMap();
            for (LevelLang levelLang : levelLangs) {
                levelLangMap.put(levelLang.getLang(), levelLang);
            }
            Level level = salesLevelService.selectByIdAndLang(id, shiroUser.getDeptName());
            model.addAttribute("lang", levelLangMap);
            model.addAttribute("level", level);
            model.addAttribute("levelLang", levelLangs);
        }
        model.addAttribute("levelId", id);
        model.addAttribute("languages", languages);
        return "customer/level/addOrEdit";
    }
}

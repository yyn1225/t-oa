package com.jtech.toa.controller.sys;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;
import com.jtech.toa.entity.system.Translate;
import com.jtech.toa.service.system.ITranslateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>获取翻译字段</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/sys/translate")
public class LanguageTranslateController {

    private final ITranslateService translateService;

    @Autowired
    public LanguageTranslateController(ITranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping("/get")
    public ResponseEntity getTranslate(@RequestParam(value = "chinese") String chinese,
                                       @RequestParam(value = "category") String category) {
        Translate translate = null;
        if (StringUtils.isNotEmpty(chinese)) {
            translate = translateService.selectOne(new EntityWrapper<Translate>().eq("chinese",chinese.trim()).eq("category", category));
        }
        if (null != translate) {
            return ResponseEntity.ok(translate.getTranslate());
        }
        else {
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveTranslate(Translate translateObj) {
        ShiroUser shiroUser = ShiroUtil.getUser();
        boolean ok;
        translateObj.setCreater((int) shiroUser.getId());
        translateObj.setCreateTime(new Date());
        ok = translateObj.insert();
        return ResponseEntity.ok(ok);
    }
}

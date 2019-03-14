package com.jtech.toa.controller.product;

import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.ProductBoxLang;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IProductBoxLangService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *     箱体信息  控制层
 * </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class BoxController {

    private final IBoxService boxService;
    private final IModuleService moduleService;
    private final IProductBoxLangService productBoxLangService;

    @Autowired
    public BoxController (IBoxService boxService,IModuleService moduleService, IProductBoxLangService productBoxLangService){
        this.boxService = boxService;
        this.moduleService = moduleService;
        this.productBoxLangService = productBoxLangService;
    }

    @GetMapping(value = "/box")
    public String importIndex(Model model) {
        model.addAttribute("modules", moduleService.selectModuleScoNoList());
        return "/product/box/list";
    }

    @GetMapping(value = "/box/manage")
    public String addOrEdit(@RequestParam("id") int id, Model model){
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        List<Module> modules = moduleService.selectModuleScoNoList();
        if (id == 0) {
            Box box = new Box();
            model.addAttribute("id", id);
            model.addAttribute("box", box);
            model.addAttribute("operate", "新增");
        }else {
            Box box = boxService.selectByIdAndLang(id, shiroUser.getDeptName());
            List<ProductBoxLang> boxLangs = productBoxLangService.selectList(new EntityWrapper<ProductBoxLang>().eq("box", id));
            Map<String, ProductBoxLang> boxLangMap = Maps.newHashMap();
            for (ProductBoxLang boxLang : boxLangs) {
                boxLangMap.put(boxLang.getLang(), boxLang);
            }
            model.addAttribute("boxLang", boxLangMap);
            model.addAttribute("id", id);
            model.addAttribute("box", box);
            model.addAttribute("operate", "编辑");
        }
        model.addAttribute("modules", JSONArray.toJSONString(modules));
        return "product/box/save";
    }


}

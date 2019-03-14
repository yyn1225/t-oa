package com.jtech.toa.controller.product;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.product.Module;
import com.jtech.toa.entity.product.ProductModualLang;
import com.jtech.toa.model.dto.products.ModuleDto;
import com.jtech.toa.service.product.IModuleService;
import com.jtech.toa.service.product.IProductModualLangService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
public class ModuleController {

    private final IModuleService moduleService;
    private final IProductModualLangService productModualLangService;

    @Autowired
    public ModuleController(IModuleService moduleService, IProductModualLangService productModualLangService) {
        this.moduleService = moduleService;
        this.productModualLangService = productModualLangService;
    }

    @GetMapping("/module")
    public String moduleUI() {
        return "product/module/list";
    }

    @GetMapping(value = "/module/manage")
    public String addOrEdit(@RequestParam("id") int id, Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        if (id == 0) {
            model.addAttribute("id", id);
        } else {
            List<ProductModualLang> modualLangs = productModualLangService.selectList(
                    new EntityWrapper<ProductModualLang>().eq("modual", id));
            Map<String, Map<String, String>> langMap = Maps.newHashMap();
            Map<String, String> typeMap = Maps.newHashMap();
            for (ProductModualLang modualLang : modualLangs) {
                typeMap.put(modualLang.getLang(), modualLang.getType());
            }
            langMap.put("type", typeMap);
            Module module = moduleService.selectByIdAndLang(id, shiroUser.getDeptName());
            model.addAttribute("id", id);
            model.addAttribute("langMap", langMap);
            model.addAttribute("module", module);
        }
        return "product/module/addOrEdit";
    }
    /**
     * 模组新增,编辑
     *
     * @return 页面提示
     */
    @ResponseBody
    @PostMapping("/module/save")
    public ResponseEntity save(ModuleDto moduleDto, String lang) {
        List<ProductModualLang> langLists= JSONArray.parseArray(lang, ProductModualLang.class);
        for (ProductModualLang langList : langLists) {
            if ("zh".equals(langList.getLang())) {
                moduleDto.setConfiguration(langList.getType());
            }
        }
        Optional<Module> moduleByScnNo = moduleService.selectModuleByScnNo(moduleDto.getScnNo());
        if (moduleDto.getId() <= 0) {
            if (moduleByScnNo.isPresent()) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = moduleService.addModule(moduleDto, langLists);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        } else {
            List<ProductModualLang> modualLangs = productModualLangService.selectList(
                    new EntityWrapper<ProductModualLang>().eq("modual", moduleDto.getId()));
            Map<String,ProductModualLang> map = Maps.newHashMap();
            for(ProductModualLang modualLang:modualLangs){
                map.put(modualLang.getLang(),modualLang);
            }
            List<ProductModualLang> updateList = Lists.newArrayList();
            List<ProductModualLang> newList = Lists.newArrayList();

            for(ProductModualLang lan:langLists) {
                if(map.containsKey(lan.getLang())) {
                    lan.setId(map.get(lan.getLang()).getId());
                    lan.setUpdateTime(new Date());
                    updateList.add(lan);
                }else{
                    lan.setCreateTime(new Date());
                    newList.add(lan);
                }
            }
            if (moduleByScnNo.isPresent() && !(moduleDto.getId().equals(moduleByScnNo.get().getId()))) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = moduleService.updateModule(moduleDto, updateList, newList);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        }return ResponseEntity.ok(moduleDto);
    }
}

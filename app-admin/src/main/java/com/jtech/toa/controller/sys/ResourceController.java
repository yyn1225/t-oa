package com.jtech.toa.controller.sys;

import com.google.common.collect.Maps;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.system.Resource;
import com.jtech.toa.entity.system.ResourceLang;
import com.jtech.toa.service.system.IResourceLangService;
import com.jtech.toa.service.system.IResourceService;

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
 * <p> </p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class ResourceController {

    private final IResourceService resourceService;
    private final IResourceLangService resourceLangService;

    @Autowired
    public ResourceController (IResourceService resourceService, IResourceLangService resourceLangService){
        this.resourceService = resourceService;
        this.resourceLangService = resourceLangService;
    }

    @GetMapping(value = "/resource")
    public String importIndex() {
        return "/sys/resource/list";
    }

    @GetMapping(value = "/resource/manage")
    public String addOrEdit(@RequestParam("id") int id, Model model){
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        List<Resource> resourceList = resourceService.selectResourceList(shiroUser.getDeptName());
        if (id == 0) {
            Resource resource = new Resource();
            model.addAttribute("id", id);
            model.addAttribute("resource", resource);
            model.addAttribute("resourceList", resourceList);
            model.addAttribute("operate", "新增");
        }else {
            Resource resource = resourceService.selectById(id);
            List<ResourceLang> resourceLangs = resourceLangService.selectByResource(id);
            Map<String, Map<String, String>> langMap = Maps.newHashMap();
            Map<String, String> typeMap = Maps.newHashMap();
            for (ResourceLang resourceLang : resourceLangs) {
                typeMap.put(resourceLang.getLanguage(),resourceLang.getNameLang());
            }
            langMap.put("nameLang", typeMap);
            model.addAttribute("langMap", langMap);
            model.addAttribute("id", id);
            model.addAttribute("resource", resource);
            model.addAttribute("resourceList", resourceList);
            model.addAttribute("operate", "编辑");
        }
        return "sys/resource/addOrEdit";
    }
}

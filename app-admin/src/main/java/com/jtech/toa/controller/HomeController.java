package com.jtech.toa.controller;

import com.google.common.collect.Maps;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.system.Language;
import com.jtech.toa.model.dto.sys.ResouceDto;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.system.ILanguageService;
import com.jtech.toa.service.system.IResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 公共的一些请求 ，比如首页 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
public class HomeController {
    private final IResourceService resourceService;
    private final ILanguageService languageService;
    private final IFileService fileService;
    private final ISeriesService seriesService;

    @Autowired
    public HomeController(IResourceService resourceService, ILanguageService languageService,
                          IFileService fileService,ISeriesService seriesService) {
        this.resourceService = resourceService;
        this.languageService = languageService;
        this.fileService = fileService;
        this.seriesService=seriesService;
    }

    @GetMapping(value = {"/home", "/"})
    public String viewHome(Model model) {
        ShiroUser shiroUser = ShiroUtil.getUser();
        //这里有个特殊约定，我将departName字段存放的数据是默认语言编码
        //谁都不要随便更改这个字段的值！！！
        String lang = shiroUser.getDeptName();
        Long userId = shiroUser.getId();
        List<Language> languages = languageService.selectLanguageList();
        List<ResouceDto> resouceDtos = resourceService.genderForMenu(lang,userId.intValue());
        model.addAttribute("resouces", resouceDtos);
        model.addAttribute("langs", languages);
        Language defaultLang = languageService.findByCode(lang);
        model.addAttribute("defaultLang", defaultLang);
        return "main";
    }

    @GetMapping(value = "dashboard")
    public String dashboard(Model model) {
        ShiroUser shiroUser = ShiroUtil.getUser();
        int userId = (int)shiroUser.getId();
        List<File> fileList = fileService.findFileListByUser(userId);
        Map<String,List<File>> fileMap= Maps.newHashMap();
        for(File file:fileList){
            if(null==file.getCategory()){
                file.setCategory(File.FileCategory.News);
            }
            switch (file.getCategory()){
                case File.FileCategory.News:
                    if(!fileMap.containsKey("news")){
                        fileMap.put("news", new ArrayList<File>());
                    }
                    if(fileMap.get("news").size()<=4){
                        fileMap.get("news").add(file);
                    }
                    break;
                case File.FileCategory.Solutions:
                    if(!fileMap.containsKey("solution")){
                        fileMap.put("solution", new ArrayList<File>());
                    }
                    if(fileMap.get("solution").size()<=4){
                        fileMap.get("solution").add(file);
                    }
                    break;
                case File.FileCategory.Brand:
                    if(!fileMap.containsKey("brand")){
                        fileMap.put("brand", new ArrayList<File>());
                    }
                    if(fileMap.get("brand").size()<=4){
                        fileMap.get("brand").add(file);
                    }
                    break;
                case File.FileCategory.Products:
                    if(!fileMap.containsKey("product")){
                        fileMap.put("product", new ArrayList<File>());
                    }
                    if(fileMap.get("product").size()<=4){
                        fileMap.get("product").add(file);
                    }
                    break;
                default:
                    if(!fileMap.containsKey("news")){
                        fileMap.put("news", new ArrayList<File>());
                    }
                    if(fileMap.get("news").size()<=4){
                        fileMap.get("news").add(file);
                    }
                    break;
            }
        }

        List<SeriesDto> seriesDtos = seriesService.findSeriesList();
        Map<String,List<SeriesDto>> seriesMap = Maps.newHashMap();
        for (SeriesDto dto:seriesDtos){
            switch (dto.getLine()){
                case Series.Line.Indoor:
                    if(!seriesMap.containsKey("indoor")){
                        seriesMap.put("indoor",new ArrayList<SeriesDto>());
                    }
                    seriesMap.get("indoor").add(dto);
                    break;
                case Series.Line.Outdoor:
                    if(!seriesMap.containsKey("outdoor")){
                        seriesMap.put("outdoor",new ArrayList<SeriesDto>());
                    }
                    seriesMap.get("outdoor").add(dto);
                    break;
                case Series.Line.Rental:
                    if(!seriesMap.containsKey("rental")){
                        seriesMap.put("rental",new ArrayList<SeriesDto>());
                    }
                    seriesMap.get("rental").add(dto);
                    break;
            }
        }
        model.addAttribute("files",fileMap);
        model.addAttribute("series",seriesMap);
        return "dashboard";
    }
}

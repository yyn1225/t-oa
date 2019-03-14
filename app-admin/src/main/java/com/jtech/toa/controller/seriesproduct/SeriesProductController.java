package com.jtech.toa.controller.seriesproduct;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.SeriesStandard;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISeriesStandardService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.product.ISpecialService;

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
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class SeriesProductController {
    private final ISeriesStandardService seriesStandardService;
    private final ISpareService spareService;
    private final IFilePackageService filePackageService;
    private final IParamsService paramsService;
    private final IProductService productService;
    private final IBoxService boxService;
    private final IPriceService priceService;
    private final ISpecialService specialService;
    private final IConfigurationService configurationService;
    private final ISeriesService seriesService;
    private final ICertificationService certificationService;
    private final IDictService dictService;

    @Autowired
    public SeriesProductController(ISeriesStandardService seriesStandardService, ISeriesService seriesService,
                                   ISpareService spareService,
                                   IFilePackageService filePackageService, IParamsService paramsService,
                                   IProductService productService, IBoxService boxService,
                                   IPriceService priceService, ISpecialService specialService,
                                   IConfigurationService configurationService,
                                   ICertificationService certificationService, IDictService dictService) {
        this.seriesStandardService = seriesStandardService;
        this.seriesService = seriesService;
        this.spareService = spareService;
        this.filePackageService = filePackageService;
        this.paramsService = paramsService;
        this.productService = productService;
        this.boxService = boxService;
        this.priceService = priceService;
        this.specialService = specialService;
        this.configurationService = configurationService;
        this.certificationService = certificationService;
        this.dictService = dictService;
    }

    @GetMapping(value = "/seriesproduct")
    public String importIndex() {
        return "/seriesproduct/list";
    }

    @GetMapping("/sp/spare/count")
    public String item(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("seriesId", id);
        }
        return "/seriesproduct/count/item";
    }

    @GetMapping("/sp/file")
    public String file(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("fid", id);
        }
        return "/seriesproduct/file/list";
    }

    @GetMapping("/sp/panel")
    public String panel(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("series", id);
        }
        return "/seriesproduct/panel/list";
    }


    @GetMapping("/sp/count/years")
    public String years(Integer standardId, Model model) {
        if (standardId > 0) {
            SeriesStandard standard = seriesStandardService.selectById(standardId);
            if (standard != null) {
                Integer seriesId = standard.getSeries();
                Integer sid = standard.getSpare();
                if (seriesId != null && sid != null) {
                    Series series = seriesService.selectById(seriesId);
                    Spare spare = spareService.selectById(sid);
                    model.addAttribute("years", getYears(standard));
                    model.addAttribute("spare", spare);
                    model.addAttribute("standard", standard);
                    model.addAttribute("series", series);
                }
            }
        }
        return "/seriesproduct/count/years";
    }

    @GetMapping("/sp/product/view")
    public String addUI(@RequestParam("id") int id, Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        Params params = paramsService.selectParamsByProductId(id);
        Product product = productService.selectById(id);
        model.addAttribute("params", params);
        if (params != null) {
            model.addAttribute("box", boxService.selectById(product.getBox()));
        }
        model.addAttribute("product", product);
        model.addAttribute("productId", product.getId());
        model.addAttribute("price", priceService.selectByProductAndArea(product.getId(), 0));
        model.addAttribute("requests", specialService.selectById(product.getRequest()));
        model.addAttribute("configurations", configurationService.selectById(product.getConfiguration()));
        model.addAttribute("seriesName", seriesService.selectById(product.getSeries()));
        List<Certification> certifications = certificationService.selectByCode(product.getCertification());
        if (certifications.size() > 0) {
            model.addAttribute("certificationName", certificationService.selectByCode(product.getCertification()).get(0));
        }
        Dict dicts = dictService.selectDictByCode("product_type", product.getType(), shiroUser.getDeptName());
        if (dicts == null) {
            dicts = new Dict();
        }
        model.addAttribute("productType", dicts);

        Dict dictList = dictService.selectDictByCode("product_status", product.getType(), shiroUser.getDeptName());
        if (dictList == null) {
            dictList = new Dict();
        }
        model.addAttribute("productStatus", dictList);
        return "seriesproduct/panel/add";
    }

    private List<Map<String, Object>> getYears(SeriesStandard standard) {
        List<Map<String, Object>> years = Lists.newArrayList();
        Map<String, Object> year2 = Maps.newHashMap();
        year2.put("year", 2);
        year2.put("counts", standard.getCountsTwo());
        year2.put("unitType", standard.getCalTypeTwo());
        year2.put("spel", standard.getSpelTwo());
        Map<String, Object> year3 = Maps.newHashMap();
        year3.put("year", 3);
        year3.put("counts", standard.getCountsThree());
        year3.put("unitType", standard.getCalTypeThree());
        year3.put("spel", standard.getSpelThree());
        Map<String, Object> year4 = Maps.newHashMap();
        year4.put("year", 4);
        year4.put("counts", standard.getCountsFour());
        year4.put("unitType", standard.getCalTypeFour());
        year4.put("spel", standard.getSpelFour());
        Map<String, Object> year5 = Maps.newHashMap();
        year5.put("year", 5);
        year5.put("counts", standard.getCountsFive());
        year5.put("unitType", standard.getCalTypeFive());
        year5.put("spel", standard.getSpelFive());
        years.add(year2);
        years.add(year3);
        years.add(year4);
        years.add(year5);
        return years;
    }
}

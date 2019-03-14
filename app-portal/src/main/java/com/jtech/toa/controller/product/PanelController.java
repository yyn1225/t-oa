/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.controller.product;

import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.product.Params;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISpecialService;
import com.jtech.toa.service.system.ISysExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Controller
public class PanelController {
    private final IParamsService paramsService;
    private final IProductService productService;
    private final IBoxService boxService;
    private final IPriceService priceService;
    private final ISpecialService specialService;
    private final IConfigurationService configurationService;
    private final ISeriesService seriesService;
    private final ICertificationService certificationService;
    private final IDictService dictService;
    private final ISysExchangeService sysExchangeService;

    @Autowired
    public PanelController(IParamsService paramsService, IProductService productService, IBoxService boxService,
                           IPriceService priceService, ISpecialService specialService,
                           IConfigurationService configurationService, ISeriesService seriesService,
                           ICertificationService certificationService, IDictService dictService,
                           ISysExchangeService sysExchangeService) {
        this.paramsService = paramsService;
        this.productService = productService;
        this.boxService = boxService;
        this.priceService = priceService;
        this.specialService = specialService;
        this.configurationService = configurationService;
        this.seriesService = seriesService;
        this.certificationService = certificationService;
        this.dictService = dictService;
        this.sysExchangeService = sysExchangeService;
    }

    @GetMapping("/product")
    public String panel(Model model,@RequestUser RequestSubject user) {
        List<SeriesDto> seriesList = seriesService.findSeriesList();
        List<Certification> certification = certificationService.selectCertificationList();
        List<Dict> productType = dictService.selectDictByCategory("product_type",user.getLanguage());
        List<Dict> productStatus = dictService.selectDictByCategory("product_status",user.getLanguage());
        model.addAttribute("certification", certification);
        model.addAttribute("seriesList",seriesList);
        model.addAttribute("productType",productType );
        model.addAttribute("productStatus",productStatus );
        return "product/panel/list";
    }
    
    @GetMapping("/skimProduct")
    public String skimProduct(Model model,@RequestUser RequestSubject user) {
    	List<SeriesDto> seriesList = seriesService.findSeriesList();
        List<Certification> certification = certificationService.selectCertificationList();
        List<Dict> productType = dictService.selectDictByCategory("product_type",user.getLanguage());
        List<Dict> productStatus = dictService.selectDictByCategory("product_status",user.getLanguage());
        model.addAttribute("certification", certification);
        model.addAttribute("seriesList",seriesList);
        model.addAttribute("productType",productType );
        model.addAttribute("productStatus",productStatus );
    	return "product/panel/skimList";
    }


    @GetMapping("/product/addUI")
    public String addUI(@RequestParam("id") int id, Model model, @RequestUser RequestSubject user) {
        Params params = paramsService.selectParamsByProductId(id);
        Product product = productService.selectById(id);
        model.addAttribute("params", params);
        model.addAttribute("box", boxService.selectById(product.getBox()));
        Price price = priceService.selectByProductAndArea(product.getId(), user.getArea());
        model.addAttribute("product", product);
        model.addAttribute("productId", product.getId());
        model.addAttribute("price", price);
        if (price != null) {
            model.addAttribute("moneyUnit", new SapRate().getKeyCode().get(price.getUnit()));
        }
        model.addAttribute("requests", specialService.selectById(product.getRequest()));
        model.addAttribute("configurations", configurationService.selectById(product.getConfiguration()));
        model.addAttribute("seriesName", seriesService.selectById(product.getSeries()));
        model.addAttribute("rates", sysExchangeService.getLastExchange());
        List<Certification> certifications = certificationService.selectByCode(product.getCertification());
        if (certifications.size() > 0) {
            model.addAttribute("certificationName", certificationService.selectByCode(product.getCertification()).get(0));
        }
        Dict dicts = dictService.selectDictByCode("product_type", product.getType(),user.getLanguage());
        if (dicts == null) {
            dicts = new Dict();
        }
        model.addAttribute("productType",dicts);

        Dict dictList = dictService.selectDictByCode("product_status", product.getType(),user.getLanguage());
        if (dictList == null) {
            dictList = new Dict();
        }
        model.addAttribute("productStatus", dictList);

        return "product/panel/add";
    }
}

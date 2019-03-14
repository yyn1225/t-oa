package com.jtech.toa.controller.product;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Strings;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.prices.PriceSystem;
import com.jtech.toa.entity.prices.SparePriceSystem;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.entity.product.SparePrice;
import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.model.dto.prices.SavePriceDto;
import com.jtech.toa.model.dto.products.PanelPriceDto;
import com.jtech.toa.model.dto.products.ProductDto;
import com.jtech.toa.model.dto.products.SparePriceDetailsDto;
import com.jtech.toa.model.dto.products.SparePriceDto;
import com.jtech.toa.model.dto.prices.PricesDetailDto;
import com.jtech.toa.service.prices.IPriceSystemService;
import com.jtech.toa.service.prices.IPricesDetailsService;
import com.jtech.toa.service.prices.ISparePriceSystemService;
import com.jtech.toa.service.product.*;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.service.IDepartmentService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class PriceController {
    private final IPriceSystemService priceSystemService;
    private final ISalesService salesService;
    private final ISparePriceService sparePriceService;
    private final ISysExchangeService exchangeService;
    private final ISysExchangeService sysExchangeService;
    private final IProductService productService;
    private final IDepartmentService departmentService;
    private final IPricesDetailsService detailsService;
    private final ISparePriceSystemService sparePriceSystemService;

    @Autowired
    public PriceController(IPricesDetailsService pricesDetailsService, ISalesService salesService,
                           IPriceSystemService priceSystemService, ISparePriceService sparePriceService, ISparePriceSystemService sparePriceSystemService,
                           ISysExchangeService sysExchangeService, IProductService productService, IDepartmentService departmentService, ISparePriceSystemService sparePriceSystemService1) {
        this.priceSystemService = priceSystemService;
        this.salesService = salesService;
        this.sparePriceService = sparePriceService;
        this.exchangeService = sysExchangeService;
        this.sysExchangeService = sysExchangeService;
        this.productService = productService;
        this.departmentService = departmentService;
        this.detailsService = pricesDetailsService;
        this.sparePriceSystemService = sparePriceSystemService1;
    }

    @GetMapping("/panel/price")
    public String list(Model model) {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        List<Department> departmentList = departmentService.selectDataByUser((int)shiroUser.getId());
        boolean limit=true;
        for(Department department:departmentList){
            if(department.getLevel()==2 || department.getLevel()==1){
                limit=false;
                break;
            }
        }
        model.addAttribute("limit",limit);
        return "product/price/panelList";
    }

    @ResponseBody
    @PostMapping("/sales/save")
    public ResponseEntity saveSales(int product, int area, int status) {
        boolean ok = salesService.saveSales(product, area, status);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("alert.message.systemError").createErrorModel());
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/price/page/add")
    public String addNewPricePage(Model model){
        List<SysExchange> exchanges = exchangeService.getLastExchange();
        model.addAttribute("moneys",exchanges);
        return "product/price/add";
    }

    @GetMapping("/price/page/edit")
    public String editPricePage(Model model,int systemId){
        PriceSystem system = priceSystemService.selectById(systemId);
        List<PricesDetailDto> details = detailsService.getDetailList(systemId);
        List<SysExchange> exchanges = exchangeService.getLastExchange();
        model.addAttribute("moneys",exchanges);
        model.addAttribute("systems",system);
        model.addAttribute("details",details);
        return "product/price/edit";
    }

    /**
     * 跳转备件价格体系的方法
     */
    @GetMapping("/spare/price")
    public String importPrice(Model model) {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        List<Department> departmentList = departmentService.selectDataByUser((int)shiroUser.getId());
        boolean limit=true;
        for(Department department:departmentList){
            if(department.getLevel()==1){
                limit=false;
                break;
            }
        }
        model.addAttribute("limit",limit);
        model.addAttribute("department", departmentService.selectChildList());
        return "/product/spare/price/list";
    }

    /**
     * 跳转备件价格体系明细的方法
     */
    @GetMapping("/spare/price/page/add")
    public String addNewSparePricePage(Model model){
        List<SysExchange> exchanges = exchangeService.getLastExchange();
        model.addAttribute("moneys",exchanges);
        return "product/spare/price/add";
    }

    /**
     * 跳转备件价格体系明细的方法
     */
    @GetMapping("/spare/price/page/edit")
    public String editSparePricePage(Model model,int systemId){
        SparePriceSystem system = sparePriceSystemService.selectById(systemId);

        List<SparePriceDetailsDto> details = detailsService.getListBySparePriceSystemsId(systemId);
        List<SysExchange> exchanges = exchangeService.getLastExchange();
        SysExchange exchangesOne = null;
        for (SysExchange s: exchanges) {
            if (s.getCode().equals(system.getUnit())){
                exchangesOne = s;
            }
        }
        model.addAttribute("moneys",exchanges);
        model.addAttribute("systems",system);
        model.addAttribute("details",details);
        model.addAttribute("exchangesOne",exchangesOne);
        return "product/spare/price/edit";
    }
}
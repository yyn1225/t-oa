package com.jtech.toa.controller.product;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.product.ProductSpareLang;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.entity.product.SparePrice;
import com.jtech.toa.model.dto.products.SpareDto;
import com.jtech.toa.service.product.IProductSpareLangService;
import com.jtech.toa.service.product.ISparePriceService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.system.ISysExchangeService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class SpareController {

    private final ISpareService spareService;
    private final ISparePriceService sparePriceService;
    private final IProductSpareLangService productSpareLangService;
    private final ISysExchangeService sysExchangeService;

    @Autowired
    public SpareController(ISpareService spareService, ISparePriceService sparePriceService,
                           IProductSpareLangService productSpareLangService,
                           ISysExchangeService sysExchangeService) {
        this.spareService = spareService;
        this.sparePriceService = sparePriceService;
        this.productSpareLangService = productSpareLangService;
        this.sysExchangeService = sysExchangeService;
    }

    @GetMapping("/spare")
    public String spareUI() {
        return "product/spare/list";
    }

    @GetMapping(value = "/spare/manage")
    public String addOrEdit(@RequestParam("id") int id, Model model) {
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        if (id == 0) {
            model.addAttribute("id", id);
            model.addAttribute("operate", "新增");
        } else {
            Spare spare = spareService.selectByIdAndLang(id, shiroUser.getDeptName());
            List<ProductSpareLang> spareLangs = productSpareLangService.selectList(
                    new EntityWrapper<ProductSpareLang>().eq("spare", id));
            Map<String, ProductSpareLang> spareLangMap = Maps.newHashMap();
            for (ProductSpareLang spareLang : spareLangs) {
                spareLangMap.put(spareLang.getLang(), spareLang);
            }
            model.addAttribute("spareLang", spareLangMap);
            model.addAttribute("id", id);
            model.addAttribute("spare", spare);
            model.addAttribute("operate", "编辑");
            model.addAttribute("price", sparePriceService.selectOne(
                    new EntityWrapper<SparePrice>().eq("spare", id).eq("area", 0)));
        }
        model.addAttribute("rates", sysExchangeService.getLastExchange());
        return "product/spare/addOrEdit";
    }

    /**
     * 备件新增,编辑
     *
     * @return 页面提示
     */
    @ResponseBody
    @PostMapping("/spare/save")
    public ResponseEntity save(String execute, SpareDto spareDto, String lang) {
        List<ProductSpareLang> spareLangs = JSONArray.parseArray(lang, ProductSpareLang.class);
        for (ProductSpareLang spareLang : spareLangs) {
            if ("zh".equals(spareLang.getLang())) {
                spareDto.setBrand(spareLang.getBrandLang());
                spareDto.setModel(spareLang.getModelLang());
                spareDto.setType(spareLang.getTypeLang());
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
            try {
                date = simpleDateFormat.parse(execute);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            spareDto.setExecuteTime(date);
        Optional<Spare> spareByMaterial = spareService.selectSpareByMaterial(spareDto.getMaterial());
        if (spareDto.getId() <= 0) {
            if (spareByMaterial.isPresent()) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = spareService.addSpare(spareDto, spareLangs);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        } else {
            List<ProductSpareLang> spareLangList = productSpareLangService.selectList(
                    new EntityWrapper<ProductSpareLang>().eq("spare", spareDto.getId()));
            Map<String,ProductSpareLang> map = Maps.newHashMap();
            for (ProductSpareLang productSpareLang : spareLangList) {
                map.put(productSpareLang.getLang(), productSpareLang);
            }
            List<ProductSpareLang> updateList = Lists.newArrayList();
            List<ProductSpareLang> newList = Lists.newArrayList();

            for(ProductSpareLang lan : spareLangs) {
                if(map.containsKey(lan.getLang())) {
                    lan.setId(map.get(lan.getLang()).getId());
                    lan.setUpdateTIme(new Date());
                    updateList.add(lan);
                }else{
                    lan.setCreateTime(new Date());
                    newList.add(lan);
                }
            }
            if (spareByMaterial.isPresent() && !(spareDto.getId().equals(spareByMaterial.get().getId()))) {
                return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST)
                        .setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
            }
            boolean ok = spareService.updateSpare(spareDto, updateList, newList);
            if (!ok) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                        .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                        .setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
            }
        }
        return ResponseEntity.ok(spareDto);
    }
}

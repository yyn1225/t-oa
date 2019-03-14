package com.jtech.toa.controller.customer.rest;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.customer.Level;
import com.jtech.toa.entity.customer.LevelLang;
import com.jtech.toa.model.query.SalesLevelQuery;
import com.jtech.toa.service.customer.ISalesLevelLangService;
import com.jtech.toa.service.customer.ISalesLevelService;
import com.jtech.toa.service.impl.customer.SalesLevelServiceImpl;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/customer/level/rest")
public class LevelRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesLevelServiceImpl.class);

    private ISalesLevelService salesLevelService;
    private IUserService userService;
    private ISalesLevelLangService salesLevelLangService;

    @Autowired
    public LevelRestController(ISalesLevelService salesLevelService, IUserService userService,
                               ISalesLevelLangService salesLevelLangService) {
        this.salesLevelService = salesLevelService;
        this.userService = userService;
        this.salesLevelLangService = salesLevelLangService;
    }

    /**
     * 客户等级信息
     *
     * @return 等级信息
     */
    @PostMapping("/list")
    public DataTablesOutput<Level> boxList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        final Page<Level> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        SalesLevelQuery query = dataTablesInput
                .getParams()
                .toJavaObject(SalesLevelQuery.class);

        query.setLang(shiroUser.getDeptName());

        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        query.setArea(userAppDto.getArea());
        LOGGER.debug("message {}", query);
        this.salesLevelService.selectLevelListByPage(requestPage, query);

        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(int id) {
        boolean ok = salesLevelService.deleteById(id);
        if (!ok) {
            return ResponseEntity.badRequest().body(ErrorModel.builder()
                    .setCode(UserCode.DELETE_POSITION_ERROR).setMessage("系统出错").createErrorModel());
        }
        return ResponseEntity.ok(id);
    }
    @PostMapping("/save")
    public ResponseEntity save(Level level, String lang) {
        List<LevelLang> levelLangList = JSONArray.parseArray(lang, LevelLang.class);
        for (LevelLang levelLang : levelLangList) {
            if ("zh".equals(levelLang.getLang())) {
                level.setName(levelLang.getNameLang());
            }
        }
        final Subject subject = SecurityUtils.getSubject();
        final ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        UserAppDto userAppDto = userService.findForAppByUserId((int) shiroUser.getId());
        if (userAppDto.getArea() == 1 || userAppDto.getArea() == 2){
            if (level.getId() > 0) {
                Level levels = salesLevelService.findByCodeAndIdNotEq(level.getCode(), level.getId());
                if (levels != null) {
                    return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST).
                            setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
                }
                //将数据库中的语言与前台获取的语言对比
                List<LevelLang> levelLangs = salesLevelLangService.selectByLevelId(level.getId());
                Map<String,LevelLang> map = Maps.newHashMap();
                for (LevelLang levelLang : levelLangs) {
                    map.put(levelLang.getLang(), levelLang);
                }
                List<LevelLang> updateList = Lists.newArrayList();
                List<LevelLang> newList = Lists.newArrayList();

                for(LevelLang lan : levelLangList) {
                    if(map.containsKey(lan.getLang())) {
                        lan.setId(map.get(lan.getLang()).getId());
                        updateList.add(lan);
                    }else{
                        newList.add(lan);
                    }
                }

                boolean isOk = salesLevelService.updateLevel(level, updateList, newList);
                if (!isOk) {
                    return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.SYSTEM_INSIDE_ERROR).
                    setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
                }
            }else {
                Level levels = salesLevelService.findByCode(level.getCode());
                if (levels != null) {
                    return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.CODE_IS_EXIST).
                            setMessage(String.valueOf(ProductCode.CODE_IS_EXIST.getCode())).createErrorModel());
                }
                boolean isOk = salesLevelService.insertLevel(level, levelLangList);
                if (!isOk) {
                    return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.SYSTEM_INSIDE_ERROR).
                            setMessage(String.valueOf(ProductCode.SYSTEM_INSIDE_ERROR.getCode())).createErrorModel());
                }
            }
        }else {
            return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(ProductCode.NO_PERMISSION).
                    setMessage(String.valueOf(ProductCode.NO_PERMISSION.getCode())).createErrorModel());
        }
        return ResponseEntity.ok(level);
    }

    @GetMapping("code/validate")
    public ResponseEntity validate(int id, String code) {
        Level level;
        Map<String, Object> result = Maps.newHashMap();
        if (id > 0) {
            level = salesLevelService.findByCodeAndIdNotEq(code, id);
        } else {
            level = salesLevelService.findByCode(code);
        }
        if (null == level) {
            result.put("valid", true);
            return ResponseEntity.ok(result);
        }
        result.put("valid", false);
        return ResponseEntity.ok(result);
    }
}

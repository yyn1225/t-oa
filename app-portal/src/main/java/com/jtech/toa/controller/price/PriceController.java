package com.jtech.toa.controller.price;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.service.IDepartmentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/price")
public class PriceController {
    private ISeriesService seriesService;
    private IDepartmentService departmentService;

    @Autowired
    public PriceController(ISeriesService seriesService, IDepartmentService departmentService) {
        this.seriesService = seriesService;
        this.departmentService = departmentService;
    }

    /**
     * 请求区域价格明细页面
     */
    @GetMapping("/list")
    public String list(Model model, @RequestUser RequestSubject user) {
        model.addAttribute("series", seriesService.findSeriesList());
        List<Department> departmentList = departmentService.selectDepartmentByUser(user.getId(),user.getLanguage());
        model.addAttribute("department", departmentService.selectDepartmentByUser(user.getId(),user.getLanguage()));
        if (CollectionUtils.isNotEmpty(departmentList)) {
            model.addAttribute("parent", departmentService.selectParentById(departmentList.get(0).getId()));
        }else {
            model.addAttribute("parent", new Department());
        }
        return "price/list";
    }
}

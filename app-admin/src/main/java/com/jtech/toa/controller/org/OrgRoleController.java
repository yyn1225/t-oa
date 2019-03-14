package com.jtech.toa.controller.org;

import com.alibaba.fastjson.JSONObject;
import com.jtech.marble.error.ErrorModel;
import com.jtech.toa.entity.system.*;
import com.jtech.toa.service.system.*;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.DepartmentLang;
import com.jtech.toa.user.model.dto.UserSelect2Dto;
import com.jtech.toa.user.service.IDepartmentLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class OrgRoleController {
    private final IRoleService roleService;
    private final ILanguageService languageService;
    private final IRoleLangService roleLangService;
    private final IRoleResourceService roleResourceService;
    private final IRoleUserService roleUserService;

    @Autowired
    public OrgRoleController(IRoleService roleService, ILanguageService languageService, IRoleLangService roleLangService,
                             IRoleResourceService roleResourceService, IRoleUserService roleUserService) {
        this.roleService = roleService;
        this.languageService = languageService;
        this.roleLangService = roleLangService;
        this.roleResourceService = roleResourceService;
        this.roleUserService = roleUserService;
    }

    @GetMapping("/role")
    public String index() {
        return "org/role/list";

    }

    @GetMapping("/role/addOrEdit")
    public String addOrEdit(int id, Model model) {
        List<Language> languages = languageService.selectLanguageList();
        if (id != 0) {
            List<RoleLang> roleLangs = roleLangService.selectByRoleId(id);
            Role role = roleService.selectById(id);
            model.addAttribute("role", role);
            model.addAttribute("roleLang", roleLangs);
        }
        model.addAttribute("roleId", id);
        model.addAttribute("languages", languages);
        return "org/role/addOrEdit";
    }

    @GetMapping("/role/delete")
    public ResponseEntity delete(int id) {
        List<RoleResource> roleResources = roleResourceService.findByRoleId(id);
        List<Long> userIds = roleUserService.findByRoleId(id);
        if (roleResources.size() != 0 || userIds.size() != 0) {
            return ResponseEntity.badRequest().body(ErrorModel.builder().setCode(UserCode.REMOVE_ORGANIZATION_POSITION_ERROR)
                    .setMessage("该资源已被使用，无法删除").createErrorModel());
        }
        boolean ok = roleService.deleteRole(id);
        if (!ok) {
            return ResponseEntity.badRequest().body(ErrorModel.builder()
                    .setMessage("服务器内部错误").createErrorModel());
        }
        return ResponseEntity.ok(id);
    }

    @ResponseBody
    @PostMapping("/role/save")
    public ResponseEntity save(Role role, String langVal) {
        if (role.getId() > 0) {
            boolean isOk = roleService.updateRole(role, langVal);
            if (!isOk) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorModel.builder().setMessage("更新失败").createErrorModel());
            }
        } else {
            boolean isOk = roleService.insertRole(role, langVal);
            if (!isOk) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorModel.builder().setMessage("添加失败").createErrorModel());
            }
        }
        return ResponseEntity.ok(role);
    }
}

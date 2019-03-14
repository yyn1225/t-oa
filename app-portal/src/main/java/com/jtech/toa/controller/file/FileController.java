package com.jtech.toa.controller.file;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.file.FileMarketDetail;
import com.jtech.toa.model.query.FileMarketDetailQuery;
import com.jtech.toa.service.file.IFileMarketDetailService;
import com.jtech.toa.user.model.dto.UserAppDto;
import com.jtech.toa.user.service.IUserService;
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
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private final IFileMarketDetailService fileMarketDetailService;

    @Autowired
    private FileController(IFileMarketDetailService fileMarketDetailService){
        this.fileMarketDetailService = fileMarketDetailService;
    }
    @GetMapping("/list")
    public String list(Model model,@RequestUser RequestSubject user) {
        FileMarketDetailQuery query = new FileMarketDetailQuery();
        query.setLang(user.getLanguage());
        List<FileMarketDetail> fileMarketDetails = fileMarketDetailService.selectSpareListAll(query);
        model.addAttribute("fileMarketDetails",fileMarketDetails);
        return "file/list";
    }
}

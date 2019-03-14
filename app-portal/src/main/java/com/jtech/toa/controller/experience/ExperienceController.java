package com.jtech.toa.controller.experience;


import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.model.dto.experience.ExperienceDto;
import com.jtech.toa.service.approval.IApprovalTaskService;
import com.jtech.toa.service.experience.IExperienceImageService;
import com.jtech.toa.service.experience.IExperienceShareService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.user.service.IUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jtech
 * @since 2017-11-29
 */
@Controller
public class ExperienceController {
    private final IUserService userService;
    private final ISeriesService seriesService;
    private final IOfferService offerService;
    private final IApprovalTaskService approvalTaskService;
    private final IExperienceShareService experienceService;
private final IExperienceImageService experienceImageService;
    @Autowired
    public ExperienceController(IUserService userService, ISeriesService seriesService,
        IOfferService offerService,
        IApprovalTaskService approvalTaskService,
        IExperienceShareService experienceService,
        IExperienceImageService experienceImageService) {
        this.userService = userService;
        this.seriesService = seriesService;
        this.offerService = offerService;
        this.approvalTaskService = approvalTaskService;
        this.experienceService = experienceService;
        this.experienceImageService = experienceImageService;
    }

    @GetMapping("/experience/list")
    public String list() {
        return "experience/list";
    }

    @GetMapping("/experience/item")
    public String item(@RequestUser RequestSubject user,Model model) {
        List<ExperienceImage> listImages = new ArrayList<>();
        model.addAttribute("listImages",listImages);
        return "experience/item";
    }


    @GetMapping("/experience/edit")
    public String details(@RequestUser RequestSubject user,Long id,Model model ) {
        ExperienceDto share = experienceService.selectDtoById(id,user.getLanguage(), user.getId());
        List<ExperienceImage> listImages = experienceImageService.getImageListById(id);
        model.addAttribute("share",share);
        model.addAttribute("listImages",listImages);
        return "experience/item";
    }

    @GetMapping("/experience/details")
    public String viewOffer(@RequestUser RequestSubject user,Long id, Model model) {
        ExperienceDto share = experienceService.selectDtoById(id,user.getLanguage(), user.getId());
        List<ExperienceImage> listImages = experienceImageService.getImageListById(id);
        for (ExperienceImage e : listImages) {
            e.setUrl("OTA/" + e.getUrl());
        }
        model.addAttribute("share",share);
        model.addAttribute("listImages",listImages);
        return "experience/details";
    }

    @GetMapping("/experience/shareComment")
    public String shareComment(@RequestUser RequestSubject user,Long id,Model model) {
        ExperienceDto share = experienceService.selectDtoById(id,user.getLanguage(),user.getId());
//        List<ExperienceImage> listImages = experienceImageService.getImageListById(id);
//        for (ExperienceImage e : listImages) {
//            e.setUrl("OTA/" + e.getUrl());
//        }
        model.addAttribute("shareComment",share);
        return "experience/comment";
    }
}

package com.jtech.toa.controller.approval;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import com.jtech.toa.entity.approval.ApprovalTask;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.service.approval.IApprovalTaskService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.user.model.dto.UserDto;
import com.jtech.toa.user.service.IUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jtech
 * @since 2017-11-29
 */
@Controller
public class ApprovalTaskController {
    private final IUserService userService;
    private final ISeriesService seriesService;
    private final IOfferService offerService;
    private final IApprovalTaskService approvalTaskService;

    @Autowired
    public ApprovalTaskController(IUserService userService, ISeriesService seriesService,IOfferService offerService,
                                  IApprovalTaskService approvalTaskService) {
        this.userService = userService;
        this.seriesService = seriesService;
        this.offerService = offerService;
        this.approvalTaskService = approvalTaskService;
    }

    @GetMapping("/approval/wait")
    public String list(Model model) {
        List<UserDto> userList = userService.selectAllUser();
        List<SeriesDto> seriesList = seriesService.findSeriesList();
        model.addAttribute("userList", userList);
        model.addAttribute("series", seriesList);
        return "approval/wait";
    }

    @GetMapping("/approval/already")
    public String alreadyList(Model model) {
        List<UserDto> userList = userService.selectAllUser();
        List<SeriesDto> seriesList = seriesService.findSeriesList();
        model.addAttribute("userList", userList);
        model.addAttribute("series", seriesList);
        return "approval/already";
    }

    @GetMapping("/approval/launch")
    public String launchList(Model model) {
        List<SeriesDto> seriesList = seriesService.findSeriesList();
        model.addAttribute("series", seriesList);
        return "approval/launch";
    }

    @GetMapping("/approval/detail")
    public String detail(Model model, Long offerId, Integer approvalId, Integer state) {
        MyOfferDto myOffer = offerService.selectOfferById(offerId);
        model.addAttribute("offer", myOffer);
        model.addAttribute("approvalId", approvalId);
        model.addAttribute("state", state);
        return "approval/detail";
    }

    @GetMapping("/approval/details")
    public String details(Model model, Long offerId, Integer approvalId) {
        MyOfferDto myOffer = offerService.selectOfferById(offerId);
        ApprovalTask approvalTask = approvalTaskService.selectById(approvalId);
        model.addAttribute("offer", myOffer);
        model.addAttribute("approval", approvalTask);
        return "approval/details";
    }
}

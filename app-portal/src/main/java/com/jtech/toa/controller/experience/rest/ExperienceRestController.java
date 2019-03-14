package com.jtech.toa.controller.experience.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.model.dto.experience.ExperienceCommentDto;
import com.jtech.toa.model.dto.experience.ExperienceDto;
import com.jtech.toa.model.query.ExperienceQuery;
import com.jtech.toa.service.approval.IApprovalTaskService;
import com.jtech.toa.service.experience.IExperienceCommentService;
import com.jtech.toa.service.experience.IExperienceImageService;
import com.jtech.toa.service.experience.IExperienceShareService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.system.IAttachmentService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author chibing.wang
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/experience/rest")
public class ExperienceRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExperienceRestController.class);
  private final ResourceLoader resourceLoader;
  private final IOfferService offerService;
  private final IApprovalTaskService approvalTaskService;
  private final IExperienceShareService experienceShareService;
  private final IExperienceImageService experienceImageService;
  private final IExperienceCommentService experienceCommentService;
  private final IAttachmentService attachmentService;
  private final String path;

  @Autowired
  public ExperienceRestController(ResourceLoader resourceLoader,
      IOfferService offerService,
      IApprovalTaskService approvalTaskService,
      IExperienceShareService experienceShareService,
      IExperienceImageService experienceImageService,
      IExperienceCommentService experienceCommentService,
      IAttachmentService attachmentService, OaProperties oaProperties) {
    this.resourceLoader = resourceLoader;
    this.offerService = offerService;
    this.approvalTaskService = approvalTaskService;
    this.experienceShareService = experienceShareService;
    this.experienceImageService = experienceImageService;
    this.experienceCommentService = experienceCommentService;
    this.attachmentService = attachmentService;
    this.path = oaProperties.getMediaPath();
  }

  @PostMapping("/list")
  public DataTablesOutput<ExperienceDto> userList(
      @Valid @RequestBody DataTablesInput dataTablesInput, @RequestUser RequestSubject user,
      Integer status
  ) {

    DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
    final Pagination pagination = dataTablesPagination.getPagination();
    final List<Sort.Order> orders = dataTablesPagination.getOrders();
    final Page<ExperienceDto> requestPage = new Page<>(
        pagination.getCurrent(),
        pagination.getSize(),
        pagination.getOrderByField()
    );
    ExperienceQuery query = dataTablesInput.getParams().toJavaObject(ExperienceQuery.class);

    if (status != null) {
      query.setStatus(status);
    }
    query.setLang(user.getLanguage());
    this.experienceShareService.selectExperienceList(query, requestPage,user.getId());

    return DataTablesUtils.buildOut(dataTablesInput, requestPage);
  }

  /**
   *  保存 分享
   * @param experienceDto
   * @param requestSubject
   * @return
   */
  @PostMapping("/share/save")
  public ResponseEntity saveShare(ExperienceDto experienceDto,
      @RequestUser RequestSubject requestSubject) {
    int userId = requestSubject.getId();
    LOGGER.info("experience images : " + experienceDto.getImages());
    experienceShareService.saveOrUpdateShare(experienceDto, userId);
    return ResponseEntity.noContent().build();
  }

  /**
   * 保存 评论
   * @param experienceCommentDto
   * @param requestSubject
   * @return
   */
  @PostMapping("/comment/save")
  public ResponseEntity saveComment(ExperienceCommentDto experienceCommentDto,
      @RequestUser RequestSubject requestSubject) {
    int userId = requestSubject.getId();
    LOGGER.info("experience images : " + experienceCommentDto.getImages());
    experienceCommentService.saveOrUpdateComment(experienceCommentDto, userId);
    return ResponseEntity.ok().build();
  }

  /**
   * 删除 评论
   * @param id
   * @param requestSubject
   * @return
   */
  @PostMapping("/commentDelete")
  public ResponseEntity commentDelete(String  id,
      @RequestUser RequestSubject requestSubject) {
    int userId = requestSubject.getId();
    LOGGER.info("experience comment : " +  id);
    experienceCommentService.deleteByCommentId(id);
    return ResponseEntity.ok().build();
  }
  /**
   * 删除 评论
   * @param id
   * @param requestSubject
   * @return
   */
  @PostMapping("/share/delete")
  public ResponseEntity shareDelete(String  id,
      @RequestUser RequestSubject requestSubject) {
    int userId = requestSubject.getId();
    LOGGER.info("experience share : " +  id);
    experienceShareService.deleteByShareId(id);
    return ResponseEntity.ok().build();
  }

  /**
   * 删除 回复
   * @param id
   * @param requestSubject
   * @return
   */
  @PostMapping("/replyDelete")
  public ResponseEntity replyDelete(String  id,
      @RequestUser RequestSubject requestSubject) {

    LOGGER.info("experience reply  : " +  id);
    experienceCommentService.deleteByReplyId(id);
    return ResponseEntity.ok().build();
  }

  /**
   * 保存 回复
   * @param experienceCommentDto
   * @param requestSubject
   * @return
   */
  @PostMapping("/reply/save")
  public ResponseEntity saveReply(ExperienceCommentDto experienceCommentDto,
      @RequestUser RequestSubject requestSubject) {
    int userId = requestSubject.getId();
    LOGGER.info("experience images : " + experienceCommentDto.getImages());
    experienceCommentService.saveOrUpdateComment(experienceCommentDto, userId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/image/delete")
  public ResponseEntity deleteImages(Long id,
      @RequestUser RequestSubject requestSubject) {
    int userId = requestSubject.getId();
    experienceImageService.deleteImage(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/images")
  public List<ExperienceImage> details(@RequestUser RequestSubject user, Long id, Model model) {
    List<ExperienceImage> listImages = experienceImageService.getImageListById(id);
    for (ExperienceImage e : listImages) {
      e.setUrl("OTA/" + e.getUrl());
    }
    return listImages;
  }

}

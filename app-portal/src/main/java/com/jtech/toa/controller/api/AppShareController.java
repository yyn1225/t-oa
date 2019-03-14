/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2018
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.controller.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.constants.AppMessage;
import com.jtech.toa.model.app.AppShare;
import com.jtech.toa.model.app.AppShareComment;
import com.jtech.toa.model.app.AppShareImage;
import com.jtech.toa.model.app.AppShareReply;
import com.jtech.toa.model.app.AppShareSubComment;
import com.jtech.toa.service.experience.IExperienceCommentService;
import com.jtech.toa.service.experience.IExperienceImageService;
import com.jtech.toa.service.experience.IExperienceShareService;

/**
 * <p> </p>
 *
 * @author EE
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/share")
public class AppShareController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppShareController.class);

    @Autowired
    private IExperienceShareService experienceShareService;
    @Autowired
    private IExperienceImageService experienceImageService;
    @Autowired
    private IExperienceCommentService experienceCommentService;

    /**
     * 获取经验分享列表信息
     */
    @GetMapping("/list")
    public ResponseEntity getShareList(@RequestUser RequestSubject user,
                                       @RequestParam("id") long id,
                                       @RequestParam("type") int type, String product,
                                       String shareUser,String title){
        LOGGER.info("Get share list:id="+id+"，type="+type+"," +
                "product="+product+",shareUser="+shareUser+",title="+title);
        AppMessage appMessage = new AppMessage();
        List<AppShare> appShareList =  experienceShareService.getShareListForApp(
                user.getId(),product,shareUser,title,type,id);
        appMessage.setSuccess(true);
        appMessage.setData(appShareList);
        return ResponseEntity.ok(appMessage);
    }

    @PostMapping("/save")
    public ResponseEntity saveShare(@RequestUser RequestSubject user,
                                    @RequestParam("shareInfo") String shareInfo){
        LOGGER.info("save share info,shareInfo="+shareInfo);
        AppShare appShare = JSON.parseObject(shareInfo,AppShare.class);
        boolean bool = experienceShareService.saveForApp(user.getId(),appShare);
        AppMessage appMessage = new AppMessage();
        appMessage.setSuccess(bool);
        appMessage.setData("save success.");
        return ResponseEntity.ok(appMessage);
    }

    @GetMapping("/detail")
    public ResponseEntity getDetails(@RequestUser RequestSubject user,@RequestParam("id") long id){
        LOGGER.info("get share detail,shareId="+id);
        AppMessage appMessage = new AppMessage();

        AppShare appShare = experienceShareService.getByIdForApp(id,user.getLanguage());
        List<AppShareImage> imageAllList = experienceImageService.getByShareIdForApp(id);
        List<AppShareComment> commentAllList = experienceCommentService.getByShareIdForApp(id,user.getLanguage());

        List<AppShareImage> shareImages = Lists.newArrayList();
        Map<String,List<AppShareImage>> commentImagesMap = Maps.newHashMap();
        if(!imageAllList.isEmpty()){
            for(AppShareImage image:imageAllList){
                if("0".equals(image.getCommentId())|| image.getCommentId()== null){
                    shareImages.add(image);
                }else{
                    if(commentImagesMap.get(image.getCommentId())==null){
                        List<AppShareImage> commentShareImage = Lists.newArrayList();
                        commentShareImage.add(image);
                        commentImagesMap.put(image.getCommentId(),commentShareImage);
                    }else{
                        commentImagesMap.get(image.getCommentId()).add(image);
                    }
                }
            }
        }

        List<AppShareComment> commentList = Lists.newArrayList();
        Map<String,List<AppShareSubComment>> commentMap = Maps.newHashMap();
        if(!commentAllList.isEmpty()){
            for(AppShareComment comment:commentAllList){
                if("0".equals(comment.getParentId())){
                    commentList.add(comment);
                }else{
                    if(commentMap.get(comment.getParentId()) == null){
                        List<AppShareSubComment> subCommentList = Lists.newArrayList();
                        AppShareSubComment shareSubComment = new AppShareSubComment();
                        shareSubComment.setId(comment.getId());
                        shareSubComment.setContent(comment.getContent());
                        shareSubComment.setCreateUser(comment.getCreateUser());
                        shareSubComment.setCreateTime(comment.getCreateTime());
                        subCommentList.add(shareSubComment);
                        commentMap.put(comment.getParentId(),subCommentList);
                    }else{
                        AppShareSubComment shareSubComment = new AppShareSubComment();
                        shareSubComment.setId(comment.getId());
                        shareSubComment.setContent(comment.getContent());
                        shareSubComment.setCreateUser(comment.getCreateUser());
                        shareSubComment.setCreateTime(comment.getCreateTime());
                        commentMap.get(comment.getParentId()).add(shareSubComment);
                    }
                }
            }
        }
        appShare.setImageList(shareImages);


        if(!commentList.isEmpty()){
            for(AppShareComment comment:commentList){
                comment.setImageList(commentImagesMap.get(comment.getId()));
                comment.setSubCommentList(commentMap.get(comment.getId()));
            }
        }

        appShare.setCommentList(commentList);
        appMessage.setSuccess(true);
        appMessage.setData(appShare);
        return ResponseEntity.ok(appMessage);
    }

    @PostMapping("/saveComment")
    public ResponseEntity saveComment(@RequestUser RequestSubject user,
                                    @RequestParam("commentInfo") String commentInfo){
        LOGGER.info("save comment info,commentInfo="+commentInfo);
        AppShareComment appShareComment = JSON.parseObject(commentInfo,AppShareComment.class);
        boolean bool = experienceCommentService.saveForApp(user.getId(),appShareComment);
        AppMessage appMessage = new AppMessage();
        appMessage.setSuccess(bool);
        appMessage.setData("save success.");
        return ResponseEntity.ok(appMessage);
    }

    @PostMapping("/saveReply")
    public ResponseEntity saveReply(@RequestUser RequestSubject user,
                                      @RequestParam("replyInfo") String replyInfo){
        LOGGER.info("save reply info,replyInfo="+replyInfo);
        AppShareReply appShareComment = JSON.parseObject(replyInfo,AppShareReply.class);
        boolean bool = experienceCommentService.saveReplyForApp(user.getId(),appShareComment);
        AppMessage appMessage = new AppMessage();
        appMessage.setSuccess(bool);
        appMessage.setData("save success.");
        return ResponseEntity.ok(appMessage);
    }

}

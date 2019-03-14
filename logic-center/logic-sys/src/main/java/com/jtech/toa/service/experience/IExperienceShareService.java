package com.jtech.toa.service.experience;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.io.File;
import java.util.List;

import com.jtech.toa.entity.experience.ExperienceImage;
import com.jtech.toa.entity.experience.ExperienceShare;
import com.jtech.toa.model.app.AppShare;
import com.jtech.toa.model.dto.experience.ExperienceDto;
import com.jtech.toa.model.query.ExperienceQuery;

/**
 * <p>
 *  经验分享 接口 服务类
 * </p>
 *
 * @author wang
 * @since 2017-11-29
 */
public interface IExperienceShareService extends IService<ExperienceShare> {


    /**
     * 通过查询条件查询列表
     * @Param query
     * @return 分享列表
     */
    void selectExperienceList(ExperienceQuery query, Page<ExperienceDto> requestPage, int id);


    /**
     * 普通文件附件保存，如果文件流是一个图片，则会进行图片附件的保存
     *
     * @param file           文件流
     * @param uploldFileName 上传文件名
     * @param userId         当前用户
     * @return 附件信息
     */
    ExperienceImage saveFileOverrid(File file, String uploldFileName, int userId);

    /**
     * 通过相对文件地址访问路径
     *
     * @param relativeURL 相对地址文件
     * @return 访问地址
     */
    String medialUrl(String relativeURL);
    /**
     * app api 获取经验分享列表信息
     * @param currentUserId 当前用户id
     * @param productName 产品
     * @param shareUserName 分享人
     * @param title 标题
     * @param type 类型（1:我的；2:其他；）
     * @param id 初始为0，加载更多为当前记录的最后一条数据的id，用于分页；
     * @return
     */
    List<AppShare> getShareListForApp(int currentUserId, String productName,
                                      String shareUserName, String title,
                                      int type, long id);

    /**
     * app api 保存经验分享
     * @param currentUserId 当前用户id
     * @param appShare 分享信息
     * @return
     */
    boolean saveForApp(int currentUserId,AppShare appShare);
    /**
     * app api 获取经验分享详情
     * @param id 经验分享id
     * @return
     */
    AppShare getByIdForApp(long id,String lang);

    /**
     * 保存 更新 分享
     * @param experienceDto
     * @param userId
     */
    void saveOrUpdateShare(ExperienceDto experienceDto, int userId);

    /**
     * 获取 share
     * @param id
     * @param userId
     * @return
     */
    ExperienceDto selectDtoById(Long id, String lang, int userId);

    /**
     * 删除分享
     * @param id
     */
    void deleteByShareId(String id);
}

package com.jtech.toa.controller.importExcel.rest;

import com.google.common.io.Files;
import com.jtech.marble.StringPool;
import com.jtech.marble.error.BaseErrorCode;
import com.jtech.marble.exception.InternalServerErrorException;
import com.jtech.marble.exception.ParamCheckException;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;
import com.jtech.marble.util.IdUtil;
import com.jtech.marble.util.io.FilePathUtil;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.entity.system.Attachment;
import com.jtech.toa.service.system.IAttachmentService;
import com.xiaoleilu.hutool.io.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/upload")
public class UploadImgController {
    private final IAttachmentService attachmentService;
    private final String path;

    @Autowired
    public UploadImgController(IAttachmentService attachmentService,
                                OaProperties oaProperties) {
        this.attachmentService = attachmentService;
        this.path = oaProperties.getMediaPath();
    }

    /**
     * 普通文件上传请求
     *
     * @param file 上传的文件
     * @return 请求响应体
     */
    @PostMapping("/image")
    public Attachment file(@RequestPart("file") MultipartFile file) {
        if (file == null) {
            throw new ParamCheckException(BaseErrorCode.ERRROR, "请选择文件上传");
        }

        try {
            String fileType = Files.getFileExtension(file.getOriginalFilename());
            final String pictureName = IdUtil.uuid() + StringPool.DOT + fileType;
            final String datePath = attachmentService.genDatePath();
            final String uploadFileName = file.getOriginalFilename();
            final String pathname = FilePathUtil.contact(datePath, pictureName);
            final File saveFile = new File(pathname);
            Files.createParentDirs(saveFile);
            file.transferTo(saveFile);

            //获取文件大小
            long fileSize = saveFile.length();
            //如果文件大小大于100kb
//            if (fileSize > 100*1024) {
//                //计算需要压缩至100kb的压缩大小比例
//                float scale = (float) 100*1024/fileSize;
//                Thumbnails.of(saveFile.getPath()).scale(1f).outputQuality(scale).toFile(saveFile.getParent() + "\\" + FileUtil.mainName(saveFile) + "-2." + fileType);
//            }
            if (fileSize < 100* 1024) {
            }
            if (fileSize >= (100 * 1024) && fileSize < (500 * 1024)) {
                //计算需要压缩至100kb的压缩大小比例
//                double scale = (double) 100 * 1024 / fileSize;
                Thumbnails.of(saveFile.getPath()).scale(0.5f).toFile(saveFile.getParent() + "\\" + FileUtil.mainName(saveFile) + "-2");
            }
//            if (fileSize >= (500 * 1024) && fileSize < (1000 * 1024)) {
//                //计算需要压缩至100kb的压缩大小比例
////                double scale = (double) 100 * 1024 / fileSize;
//                Thumbnails.of(new FileInputStream(saveFile.getPath())).scale(0.4f).toFile(dest.substring(0, dest.lastIndexOf(".")) + "-2");
//            }
            if ((fileSize >= 500 * 1024 && fileSize < 3000 * 1024)) {
                Thumbnails.of(saveFile.getPath()).scale(0.2f).toFile(saveFile.getParent() + "\\" + FileUtil.mainName(saveFile) + "-2");
            }
            if (fileSize >= (3000 * 1024)) {
                Thumbnails.of(saveFile.getPath()).scale(0.1f).toFile(saveFile.getParent() + "\\" + FileUtil.mainName(saveFile) + "-2");
            }
            final ShiroUser shiroUser = ShiroUtil.getUser();
            return attachmentService.saveFile(saveFile, uploadFileName, (int) shiroUser.getId());
        } catch (IOException e) {
            throw new InternalServerErrorException("文件上传失败");
        }
    }
}

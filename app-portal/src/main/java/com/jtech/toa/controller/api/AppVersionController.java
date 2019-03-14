package com.jtech.toa.controller.api;

import com.jtech.marble.StringPool;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.entity.AppVersion;
import com.jtech.toa.service.IAppVersionService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/version")
public class AppVersionController {
    private final String mediaUrl;
    private final IAppVersionService appVersionService;
    public AppVersionController(OaProperties properties,IAppVersionService appVersionService){
        this.mediaUrl = properties.getMediaUrl();
        this.appVersionService = appVersionService;
    }

    /**
     * 根据平台返回对应的版本信息
     *
     * @param type 客户端类型 1-iOS 2-Android 3-PC
     * @param version 客户端当前版本
     * @return 版本信息
     */
    @GetMapping("/check/version")
    public ResponseEntity checkVersion(int type, int version) {
        AppVersion appVersion = new AppVersion(false, false, 0, StringUtils.EMPTY, version);

        if (type <= 0) {
            return ResponseEntity.ok(appVersion);
        }
        appVersion = this.appVersionService.findNewVersionByType(type);
        if (appVersion != null) {
//            appVersion.setPath(mediaUrl + appVersion.getPath());
            final int newVersion = appVersion.getVersion();
            final String updateInfo = appVersion.getUpdateInfo();
            final boolean mustUpdate = appVersion.getMustUpdate();

            int isUpdate = newVersion > version ? 1 : 0;
            appVersion.setType(type);
            appVersion.setUpdateInfo(updateInfo);
            appVersion.setVersion(newVersion);
            if (isUpdate == 1) {
                if(mustUpdate){
                    appVersion.setUpdate(true);
                    appVersion.setMustUpdate(true);
                    return ResponseEntity.ok(appVersion);
                }
                appVersion.setUpdate(true);
                appVersion.setMustUpdate(false);
                return ResponseEntity.ok(appVersion);
            } else {
                appVersion.setUpdate(false);
                appVersion.setMustUpdate(false);
                return ResponseEntity.ok(appVersion);
            }
        } else {
            return ResponseEntity.ok(new AppVersion(false, false , type, StringPool.EMPTY, version));
        }

    }
}

/*
 * Copyright © 2015-2018, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.model.dto.files;

import com.jtech.toa.entity.file.File;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 *
 * 这个类之所有存在与这个，
 *
 * 是由于 百度的 WebUploader 上传控件在上传时处理服务端返回信息的时候，
 *
 * 不处理403等一些错误信息
 *
 * </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Data
@Builder
public class FileResponseDto {

    private int code;

    private File file;

    private String message;
}

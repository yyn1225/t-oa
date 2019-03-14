/*
 * Copyright (c)2012-2017 JingTong RDC(Research and Development Centre), Inc. All rights reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of JingTong RDC ,
 *         if any. The intellectual and technical concepts contained herein are proprietary
 *         to JingTong RDC and covered by China and Foreign Patents, patents in process,
 *         and are protected by trade secret or copyright law. Dissemination of this information
 *         or reproduction of this material is strictly forbidden unless prior written permission
 *         is obtained from JingTong RDC.
 */

package com.jtech.toa.util;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import com.jtech.marble.StringPool;
import com.jtech.marble.util.DateUtil;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class DownloadUtil {

    public static void downloadExcel(HttpServletResponse response, String title, Workbook workbook) throws IOException {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String exportTitle = DateUtil.yyyyMMdd(DateTime.now().toDate()) + title + ".xls";
        OutputStream os = null;
        try {
            final String fileName = URLEncoder.encode(exportTitle, StringPool.UTF_8);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(os);
        }
    }
}

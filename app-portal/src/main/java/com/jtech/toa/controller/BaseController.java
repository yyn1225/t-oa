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

package com.jtech.toa.controller;

import com.google.common.base.Strings;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.service.ExportExcel;
import com.jtech.toa.service.offer.IOfferPanelsService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

/**
 * <p> </p>
 *
 * @author EE
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
public class BaseController {

    @Autowired
    private IOfferService offerService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IOfferPanelsService offerPanelsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 创建报价单excel
     *
     * @param offerId  报价单id
     * @param language 语言(zh:中文;en:英文)
     * @param filePath 文件路径
     * @return 文件名称（全路径）
     */
    public String createOfferExcel(long offerId, String language, String filePath) {
        LOGGER.info("create offer excel start,offerId=" + offerId + ",language=" + language + ",filePath=" + filePath);
        final OfferVo offerVo = offerService.getOfferDetailsExport(offerId, language);
        final MyOfferDto myOffer = offerService.findOfferById(offerId, language);
        final User userMsg = userService.selectById(offerVo.getOffer().getCreater());
        final Department department = departmentService.selectParentById(offerVo.getOffer().getArea());
        final List<OfferPanelsDto> offerPanelsList = offerPanelsService.selectListByOffer(offerId, language);
        final ExportExcel exportExcel = new ExportExcel();
        final XSSFWorkbook workbook = exportExcel.createXSSFWorkbook(offerVo, myOffer, department, offerPanelsList,
                language, filePath, userMsg);
        //设置输出文件
        final String fileName = filePath + offerVo.getOffer().getNum() + "(" + language + ")" + ".xlsx";
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("create offer excel end,fileName=" + fileName);
        return fileName;
    }

    /**
     * 创建报价单excel
     *
     * @param offerId  报价单id
     * @param language 语言(zh:中文;en:英文)
     * @param filePath 文件路径
     * @return 文件名称（全路径）
     */
    public List<String> createOfferExcelPdf(long offerId, String language, String filePath) {
        LOGGER.info("create offer excel start,offerId=" + offerId + ",language=" + language + ",filePath=" + filePath);
        final OfferVo offerVo = offerService.getOfferDetailsExport(offerId, language);
        final MyOfferDto myOffer = offerService.findOfferById(offerId, language);
        final User userMsg = userService.selectById(offerVo.getOffer().getCreater());
        final Department department = departmentService.selectParentById(offerVo.getOffer().getArea());
        final List<OfferPanelsDto> offerPanelsList = offerPanelsService.selectListByOffer(offerId, language);
        final ExportExcel exportExcel = new ExportExcel();
        final List<XSSFWorkbook> workbooks = exportExcel.createXSSFWorkbookPdf(offerVo, myOffer, department, offerPanelsList,
                language, filePath, userMsg);


        FileOutputStream fos;
        int count = workbooks.size();
        List<String> list = new ArrayList<>(count);
        try {
            for (int i = 0; i < count; i++) {
                XSSFWorkbook workbook = workbooks.get(i);
                //设置输出文件
                String fileName = filePath + offerVo.getOffer().getNum() + "(" + language + ")_" + i + ".xlsx";
                File file = new File(fileName);
                if (file.exists()) {
                    file.delete();
                }
                fos = new FileOutputStream(fileName);
                workbook.write(fos);
                LOGGER.info("create offer excel end,fileName=" + fileName);
                list.add(fileName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 创建报价单pdf (如果excel中有多个sheet，
     * 在转成pdf的时候会生成多个pdf文件，有多个pdf文件就进行压缩处理)
     *
     * @param fileNames excel 文件名（全路径）
     * @return pdf 文件名（全路径）或 压缩文件名
     */
    public String createOfferPdf(List<String> fileNames) {
        String reFilePath = "";
        for (String fileName : fileNames) {
            LOGGER.info("create offer pdf start,excel file name=" + fileName);
            if (Strings.isNullOrEmpty(fileName)) {
                return "";
            }

            String pdfTmplPath = fileName.substring(0, fileName.lastIndexOf("."));
            ComThread.InitSTA();
            ActiveXComponent app = new ActiveXComponent("Excel.Application");
            app.setProperty("Visible", new Variant(false));
            Object excels = app.getProperty("Workbooks").toDispatch();
            Object excel = Dispatch.invoke(
                    (Dispatch) excels,
                    "Open",
                    Dispatch.Method,
                    new Object[]{fileName, new Variant(false),
                            new Variant(true)}, new int[1]).toDispatch();

            // 获取activate表格
            Dispatch currentSheet = Dispatch.get((Dispatch) excel,
                    "ActiveSheet").toDispatch();
            Dispatch pageSetup = Dispatch.get(currentSheet, "PageSetup")
                    .toDispatch();


            try {
                Dispatch sheets = Dispatch.get((Dispatch) excel, "Sheets")
                        .toDispatch();
                Dispatch sheet = Dispatch.invoke(sheets, "Item",
                        Dispatch.Get, new Object[]{new Integer(1)},
                        new int[1]).toDispatch();
                pageSetup = Dispatch.get(sheet, "PageSetup").toDispatch();
                Dispatch.put(pageSetup, "Orientation", 1);
                File file;
                if (fileNames.size() == 1) {
                    pdfTmplPath = pdfTmplPath.replace("_0", "");
                }
                file = new File(pdfTmplPath + ".pdf");
                if (file.exists()) {
                    file.delete();
                }
                Dispatch.call(sheet, "SaveAs", pdfTmplPath + ".pdf", new Variant(57));

            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                Dispatch.call((Dispatch) excel, "Close", new Variant(false));
                if (app != null) {
                    app.invoke("Quit", new Variant[]{});
                    app = null;
                }
                ComThread.Release();
            }
            LOGGER.info("create offer pdf end,pdf file name=" + reFilePath);

        }
        File[] files = new File[fileNames.size()];
        ZipOutputStream zos = null;


        if (fileNames.size() > 1) {
            String pdfTmplPath = fileNames.get(0).substring(0, fileNames.get(0).lastIndexOf("."));

            for (int i = 0; i < fileNames.size(); i++) {
                files[i] = new File(pdfTmplPath.replace("_0", "_" + i) + ".pdf");
            }
            String pdfTmplZip = pdfTmplPath.replace("_0", "") + ".zip";
            File pdfTmplZipFile = new File(pdfTmplZip);
            if (pdfTmplZipFile.exists()) {
                pdfTmplZipFile.delete();
            }
            try {
                zos = new ZipOutputStream(new FileOutputStream(pdfTmplZip));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //多个pdf，进行压缩
            compress(files, pdfTmplPath, zos);
            reFilePath = pdfTmplZip;
        } else if (fileNames.size() == 1) {
            reFilePath = fileNames.get(0).substring(0, fileNames.get(0).lastIndexOf(".") - 2) + ".pdf";
            files[0] = new File(fileNames.get(0).substring(0, fileNames.get(0).lastIndexOf(".") - 2) + ".pdf");
        }

        if (files.length > 1) {
            for (File f : files) {
                if (f.exists()) {
                    f.delete();
                }
            }
        }
        return reFilePath;
    }

    //压缩文件
    private void compress(File[] f, String baseDir, ZipOutputStream zos) {

        File[] fs = f;
        BufferedInputStream bis = null;
        byte[] bufs = new byte[1024 * 10];
        FileInputStream fis = null;

        try {
            for (int i = 0; i < fs.length; i++) {
                if (fs[i].isFile()) {
                    ZipEntry zipEntry = new ZipEntry(fs[i].getName());
                    zos.putNextEntry(zipEntry);
                    //读取待压缩的文件并写进压缩包里
                    fis = new FileInputStream(fs[i]);
                    bis = new BufferedInputStream(fis, 1024 * 10);
                    int read = 0;
                    while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                        zos.write(bufs, 0, read);
                    }
                    //如果需要删除源文件，则需要执行下面2句
                    fis.close();
                    fs[i].delete();
                } else if (fs[i].isDirectory()) {
                    compress(fs, fs[i] + "/", zos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != zos) {
                    zos.close();
                }
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

package com.jtech.toa.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import com.jtech.toa.entity.offer.TransportPackage;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.dto.offer.OfferServiceDto;
import com.jtech.toa.model.dto.products.PanelDetails;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.service.offer.IOfferPanelsService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
public class ExportExcel {

    @Autowired
    private IOfferPanelsService panelsService;

    public XSSFWorkbook createXSSFWorkbook(OfferVo offerVo,
                                           MyOfferDto myOffer,
                                           Department department,
                                           List<OfferPanelsDto> offerPanelsList,
                                           String lang,
                                           String filepath, User userMsg) {
        XSSFWorkbook workbook;
        InputStream inputStream;
        XSSFSheet sheet;
        try {
            List<String> msg;
            File file;
            if ("en".equals(lang)) {
                file = new File(filepath + "EU&NA&LA&Asia1&Asia2-Quote Template-en.xlsx");
                msg = Lists
                        .newArrayList("Quotation of Absen-A2712 Display", "Panel Area Dimension", "Unit Price",
                                "free",
                                "Recommended Spare parts --Optional", "Service", "Transport", "Total", "Remarks:",
                                "1. Quotation valid time: 30 days from quotation date.",
                                "2. Packing: ", "T/T 30% down payment, T/T 70% balance before delivery",
                                "T/T 0% down payment, T/T 100% balance before delivery",
                                "3. Payment terms: ",
                                "4. leading time:7 days from receiving down payment.",
                                "5. Warranty time: " + offerVo.getOffer().getGuarantee()
                                        + " years from delivery date.");
            } else {
                file = new File(filepath + "CHN-Quote Template-zh.xlsx");
                msg = Lists
                        .newArrayList("艾比森-A2712 显示屏报价", "屏体面积", "单价", "免费", "备件 --可选", "服务", "物流", "总计", "备注：",
                                "1. 报价有效期：30日内有效。", "2. 包装：", "预付30%定金, 70%余款需在交货前付清。", "无需预付定金，款项需在交货前付清。",
                                "3. 支付方式：",
                                "4. 开始生产日期：收到首付款7日内。", "5. 质保期: 交货期起" + offerVo.getOffer().getGuarantee() + "年。");
            }
            inputStream = new FileInputStream(file);
            //读取excel模板
            workbook = new XSSFWorkbook(inputStream);

            //设置(更改)sheet1的名字
            workbook.setSheetName(0, "Screen");
            //读取了模板内第一个sheet内容
            sheet = workbook.getSheetAt(0);
            List<PanelDetails> panelDetailsList = offerVo.getPanelDetailList();

            if (panelDetailsList.size() > 1) {
                for (int i = 1; i < offerVo.getPanelDetailList().size(); i++) {
                    //设置复制的sheet页名：产品-index
                    XSSFSheet copySheet = workbook.createSheet("Screen" + (i + 1));
                    //复制sheet内容,这里的行表示行号，从1开始计数，而poi读取是从0开始，故+1
                    copySheet(sheet, copySheet, sheet.getLastRowNum() + 1);

                }
            }
            if ("en".equals(lang)) {
                templateEUNew2(panelDetailsList, workbook, myOffer, msg, offerVo, sheet,
                        offerPanelsList, filepath, userMsg);
            } else {
                templateZHNew(panelDetailsList, workbook, myOffer, msg, offerVo, sheet,
                        offerPanelsList, filepath, userMsg);
            }

            return workbook;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<XSSFWorkbook> createXSSFWorkbookPdf(OfferVo offerVo,
                                                    MyOfferDto myOffer,
                                                    Department department,
                                                    List<OfferPanelsDto> offerPanelsList,
                                                    String lang,
                                                    String filepath, User userMsg) {
        List<XSSFWorkbook> xssfWorkbookList = new ArrayList<>(offerVo.getPanelDetailList().size());
        XSSFWorkbook workbook;
        InputStream inputStream;
        XSSFSheet sheet;
        try {
            List<String> msg;
            File file;
            if ("enPdf".equals(lang) || "en".equals(lang) || "enpdf".equals(lang)) {
                file = new File(filepath + "EU&NA&LA&Asia1&Asia2-Quote Template-en.xlsx");
                msg = Lists
                        .newArrayList("Quotation of Absen-A2712 Display", "Panel Area Dimension", "Unit Price",
                                "free",
                                "Recommended Spare parts --Optional", "Service", "Transport", "Total", "Remarks:",
                                "1. Quotation valid time: 30 days from quotation date.",
                                "2. Packing: ", "T/T 30% down payment, T/T 70% balance before delivery",
                                "T/T 0% down payment, T/T 100% balance before delivery",
                                "3. Payment terms: ",
                                "4. leading time:7 days from receiving down payment.",
                                "5. Warranty time: " + offerVo.getOffer().getGuarantee()
                                        + " years from delivery date.");
            } else {
                file = new File(filepath + "CHN-Quote Template-zh.xlsx");
                msg = Lists
                        .newArrayList("艾比森-A2712 显示屏报价", "屏体面积", "单价", "免费", "备件 --可选", "服务", "物流", "总计", "备注：",
                                "1. 报价有效期：30日内有效。", "2. 包装：", "预付30%定金, 70%余款需在交货前付清。", "无需预付定金，款项需在交货前付清。",
                                "3. 支付方式：",
                                "4. 开始生产日期：收到首付款7日内。", "5. 质保期: 交货期起" + offerVo.getOffer().getGuarantee() + "年。");
            }
            List<PanelDetails> panelDetailsList = offerVo.getPanelDetailList();
            if (panelDetailsList.size() > 0) {
                for (int i = 0; i < offerVo.getPanelDetailList().size(); i++) {
                    inputStream = new FileInputStream(file);
                    //读取excel模板
                    workbook = new XSSFWorkbook(inputStream);

                    //设置(更改)sheet1的名字
                    workbook.setSheetName(0, "Screen" + (i + 1));
                    //读取了模板内第一个sheet内容
                    sheet = workbook.getSheetAt(0);
                    xssfWorkbookList.add(workbook);

                }
            }
            if ("enPdf".equals(lang) || "en".equals(lang)|| "enpdf".equals(lang)) {
                templateEUPdfNew2(panelDetailsList, xssfWorkbookList, myOffer, msg, offerVo,
                        offerPanelsList, filepath, userMsg);
            } else {
                templateZHPdfNew(panelDetailsList, xssfWorkbookList, myOffer, msg, offerVo,
                        offerPanelsList, filepath, userMsg);
            }

            return xssfWorkbookList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map findExcelFileInfo(OfferVo offerVo,
                                 MyOfferDto myOffer,
                                 Department department,
                                 List<OfferPanelsDto> offerPanelsList,
                                 User userMsg,
                                 String lang,
                                 String excelPath, String filepath) {
        String languageType = "zh".equals(lang) ? "(CN)" : "(EN)";
        String fileUrl = excelPath + offerVo.getOffer().getNum() + languageType + ".xlsx";
        final HashMap<Object, Object> hashMap = Maps.newHashMap();

        File file = new File(fileUrl);
        try {
            Files.createParentDirs(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            XSSFWorkbook workbook = createXSSFWorkbook(offerVo, myOffer, department, offerPanelsList,
                    lang, filepath, userMsg);
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream(fileUrl);
                workbook.write(fileOut);
                workbook.close();
                fileOut.close();
            } catch (IOException e) {
                if (fileOut != null) {
                    try {
                        fileOut.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            }
        }
        final long sizeOf = FileUtils.sizeOf(file);
        hashMap.put("size", sizeOf);
        hashMap.put("name", file.getName());
        return hashMap;
    }


    /**
     * 创建单元格
     *
     * @param row    行
     * @param number 需要创建的单元格数量
     * @return 单元格集合
     */
    private List<XSSFCell> createRowAndCell(XSSFRow row, int number, XSSFCellStyle style) {
        List<XSSFCell> cellList = Lists.newArrayList();
        //遍历数量number，创建相依数量的单元格
        for (int i = 0; i < number; i++) {
            //创建单元格
            XSSFCell cell = row.createCell(i);
            //为单元格设置样式
            cell.setCellStyle(style);
            cellList.add(cell);
        }
        return cellList;
    }

    /**
     * 设置标题 颜色
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle titleStyleColor(XSSFWorkbook workbook) {
        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //单元格背景色
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(245, 131, 32)));
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        return style;
    }

    /**
     * 数值格式
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle styleDataFormat(XSSFWorkbook workbook) {

        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        // 此处设置数据格式
        XSSFDataFormat df = workbook.createDataFormat();

        //单元格背景色
        //创建字体 等线 10
        XSSFFont font = workbook.createFont();

        font.setFontName("等线");
        font.setFontHeightInPoints((short) 9);
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //设置右对齐
        style.setAlignment(HorizontalAlignment.RIGHT);
        //保留两位小数点
        style.setDataFormat(2);

        return style;
    }

    /**
     * 数值格式
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle styleDataFormatZero(XSSFWorkbook workbook) {

        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //单元格背景色
        //创建字体 等线 10
        XSSFFont font = workbook.createFont();

        font.setFontName("等线");
        font.setFontHeightInPoints((short) 9);
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置右对齐
        style.setAlignment(HorizontalAlignment.RIGHT);
        //保留两位小数点
        style.setDataFormat(0);

        return style;
    }

    /**
     * 数值格式
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle styleDataZeroBold(XSSFWorkbook workbook) {

        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        // 此处设置数据格式
        XSSFDataFormat df = workbook.createDataFormat();

        //单元格背景色
        //创建字体 等线 10
        XSSFFont font = workbook.createFont();

        font.setFontName("等线");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //设置左对齐
        style.setAlignment(HorizontalAlignment.LEFT);
        //保留两位小数点
        style.setDataFormat(0);

        return style;
    }

    /**
     * 其他区域标题及小标题样式创建
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle titleStyleMain(XSSFWorkbook workbook) {
        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //单元格背景色
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(250, 250, 250)));
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        //创建字体 等线 12
        XSSFFont font = workbook.createFont();

        font.setFontName("等线");
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        //设置边框
        XSSFDataFormat df = workbook.createDataFormat(); // 此处设置数据格式
        style.setDataFormat(2);//保留两位小数点
        return style;
    }


    /**
     * 其他区域标题及小标题样式创建
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle titleStyle(XSSFWorkbook workbook) {
        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //单元格背景色
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(116, 115, 115)));
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        //创建字体 等线 12
        XSSFFont font = workbook.createFont();

        font.setFontName("等线");
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(new XSSFColor(new java.awt.Color(250, 250, 250)));
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 样式对象 
        XSSFDataFormat df = workbook.createDataFormat(); // 此处设置数据格式
        style.setDataFormat(2);//保留两位小数点
        //设置边框
//    style.setBorderBottom(BorderStyle.THIN);
//    style.setBorderLeft(BorderStyle.THIN);
//    style.setBorderRight(BorderStyle.THIN);
//    style.setBorderTop(BorderStyle.THIN);
        return style;
    }

    /**
     * 其他区域标题及小标题样式创建
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle totaltyle(XSSFWorkbook workbook, String local) {
        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //单元格背景色
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(116, 115, 115)));
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        //创建字体 等线 12
        XSSFFont font = workbook.createFont();

        font.setFontName("等线");
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(new XSSFColor(new java.awt.Color(250, 250, 250)));
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        if ("left".equals(local)) {
            style.setAlignment(CellStyle.ALIGN_LEFT);
        } else if ("right".equals(local)) {
            style.setAlignment(CellStyle.ALIGN_RIGHT);
        } else {
            style.setAlignment(CellStyle.ALIGN_CENTER);
        }

        // 样式对象 
        XSSFDataFormat df = workbook.createDataFormat(); // 此处设置数据格式
        style.setDataFormat(2);//保留两位小数点
        //设置边框
//    style.setBorderBottom(BorderStyle.THIN);
//    style.setBorderLeft(BorderStyle.THIN);
//    style.setBorderRight(BorderStyle.THIN);
//    style.setBorderTop(BorderStyle.THIN);
        return style;
    }

    /**
     * 文本内容样式创建
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle bodyStyle(XSSFWorkbook workbook, String fontFamily, int fontSize) {
        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //创建字体 等线 12
        XSSFFont font = workbook.createFont();
        font.setFontName(fontFamily);
        font.setFontHeightInPoints((short) fontSize);
        style.setFont(font);
        //设置左对齐
        style.setAlignment(HorizontalAlignment.LEFT);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        style.setWrapText(true);
        return style;
    }

    /**
     * 其他区域备注区域样式创建
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle remarkStyle(XSSFWorkbook workbook) {
        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //创建字体 等线 12
        XSSFFont font = workbook.createFont();
        font.setFontName("等线");
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 合并单元格并给单元格加上边框
     *
     * @param sheet 工作表
     * @param index 表示起始行和终止行，因为模板几乎没有纵向合并，所以起始行和终止行默认同一行
     * @param first 起始列
     * @param last  终止列
     */
    private void collapse(XSSFSheet sheet, int index, int first, int last) {
        //指明合并的单元格并添加边框,目前模板主要是横向合并
        CellRangeAddress cra = new CellRangeAddress(index, index, first, last);

        sheet.addMergedRegion(cra);
    }

    /**
     * 合并单元格
     *
     * @param sheet 工作表
     * @param index 表示起始行和终止行，因为模板几乎没有纵向合并，所以起始行和终止行默认同一行
     * @param first 起始列
     * @param last  终止列
     */
    private void collapseNone(XSSFSheet sheet, int index, int first, int last) {
        //指明合并的单元格并添加边框,目前模板主要是横向合并
        CellRangeAddress cra = new CellRangeAddress(index, index, first, last);
        sheet.addMergedRegion(cra);
    }

    /**
     * 合并单元格
     *
     * @param sheet 工作表
     * @param start 起始行
     * @param end   终止行
     * @param first 起始列
     * @param last  终止列
     */
    private void collapseAuto(XSSFSheet sheet, int start, int end, int first, int last) {
        //指明合并的单元格并添加边框,目前模板主要是横向合并
        CellRangeAddress cra = new CellRangeAddress(start, end, first, last);
        sheet.addMergedRegion(cra);
    }

    /**
     * 复制sheet的方法
     *
     * @param sheetFrom 源sheet
     * @param sheetTo   目标sheet
     * @param rowNum    复制的行数
     */
    private static XSSFSheet copySheet(XSSFSheet sheetFrom, XSSFSheet sheetTo, int rowNum) {

        // 初期化
        CellRangeAddress region;
        XSSFRow rowFrom;
        XSSFRow rowTo;
        XSSFCell cellFrom;
        XSSFCell cellTo;
        //合并单元格
        for (int i = 0; i < sheetFrom.getNumMergedRegions(); i++) {
            region = sheetFrom.getMergedRegion(i);
            if ((region.getFirstColumn() >= sheetFrom.getFirstRowNum())
                    && (region.getLastRow() <= sheetFrom.getLastRowNum())) {
                sheetTo.addMergedRegion(region);
            }
        }
        sheetTo.getPrintSetup().setPaperSize(
                XSSFPrintSetup.A4_PAPERSIZE);

        //单元格的复制
        for (int intRow = sheetFrom.getFirstRowNum(); intRow < rowNum; intRow++) {
            rowFrom = sheetFrom.getRow(intRow);
            rowTo = sheetTo.createRow(intRow);
            if (null == rowFrom) {
                continue;
            }
            rowTo.setHeight(rowFrom.getHeight());
            for (int intCol = 0; intCol < rowFrom.getLastCellNum(); intCol++) {

                //单元格宽度的复制
                sheetTo.setDefaultColumnStyle(intCol, sheetFrom.getColumnStyle(intCol));
                sheetTo.setColumnWidth(intCol, sheetFrom.getColumnWidth(intCol));
                cellFrom = rowFrom.getCell(intCol);
                cellTo = rowTo.createCell(intCol);
                if (null == cellFrom) {
                    continue;
                }
                //单元格类型复制
                cellTo.setCellStyle(cellFrom.getCellStyle());
                cellTo.setCellType(cellFrom.getCellType());

                //内容的复制，判断单元格类型，设置相应的值
                int type = cellFrom.getCellType();
                if (type == 0) {
                    cellTo.setCellValue(cellFrom.getNumericCellValue());
                }
                if (type == 1) {
                    cellTo.setCellValue(cellFrom.getStringCellValue());
                }
                if (type == 2) {
                    cellTo.setCellFormula(cellFrom.getCellFormula());
                }

            }
        }

        //设置是否有网格线 false-无
        sheetTo.setDisplayGridlines(false);
        //Excel比例设定
        sheetTo.setZoom(100, 100);

        //
        return sheetTo;
    }

    /**
     * 重新计算公式的方法
     *
     * @param workbook 工作簿
     */
    private void reCalculating(XSSFWorkbook workbook) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            XSSFSheet sheet = workbook.getSheetAt(sheetNum);
            for (org.apache.poi.ss.usermodel.Row r : sheet) {
                for (Cell c : r) {
                    if (c.getCellType() == CELL_TYPE_FORMULA) {
                        evaluator.evaluateFormulaCell(c);
                    }
                }
            }
        }
    }


    /**
     * 中国区域模板读写方法
     */
    private void templateZHNew(List<PanelDetails> panelDetailsList, XSSFWorkbook workbook,
                               MyOfferDto myOffer, List<String> msg, OfferVo offerVo, XSSFSheet
                                       sheet, List<OfferPanelsDto> offerPanelsList,
                               String filepath, User userMsg) {
        // 样式对象 
        XSSFCellStyle styleColor = workbook.createCellStyle();
        //单元格背景色
        styleColor.setFillForegroundColor(new XSSFColor(new java.awt.Color(245, 131, 32)));
        styleColor.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < panelDetailsList.size(); i++) {

            //总重量
            BigDecimal weight = new BigDecimal(0);
            //总面积
            BigDecimal area = new BigDecimal(0);
            // 箱体 宽数
            int wNum = 0;
            // 箱体 高数
            int lNum = 0;
            //平均功耗
            BigDecimal avgPower = new BigDecimal(0);

            BigDecimal maxPower = new BigDecimal(0);
            //读取sheet
            XSSFSheet bodySheet = workbook.getSheetAt(i);

            //插入图片
            if (i > 0) {
                String fileName = filepath + "absen0.png";

                byte[] bytes = new byte[0];
                try {
                    InputStream is = new FileInputStream(fileName);
                    bytes = IOUtils.toByteArray(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);

                CreationHelper helper = workbook.getCreationHelper();
                Drawing drawing = bodySheet.createDrawingPatriarch();
                ClientAnchor anchor = helper.createClientAnchor();
                anchor.setCol1(4);
                anchor.setRow1(5);
                Picture pict = drawing.createPicture(anchor, pictureIdx);
                pict.resize();
            }
            //生成单元格样式
            XSSFCellStyle style = titleStyle(workbook);

            XSSFCellStyle styleRise = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise = workbook.createFont();
            fontRise.setFontName("等线");
            fontRise.setFontHeightInPoints((short) 9);
            fontRise.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleRise.setFont(fontRise);
            //设置垂直居中
            styleRise.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRise.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            //
            XSSFRow row2 = bodySheet.createRow(2);
            //设置行高
            row2.setHeightInPoints(20);
            //创建单元格
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            List<XSSFCell> cells2 = createRowAndCell(row2, 7, bodyStyle(workbook, "等线", 9));
            row2.getCell(0).setCellValue("Date:" + sdf.format(offerVo.offer.getCreateTime()));
            row2.getCell(0).setCellStyle(styleRise);
            XSSFCellStyle styleRise1 = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise1 = workbook.createFont();
            fontRise1.setFontName("等线");
            fontRise1.setFontHeightInPoints((short) 9);
            fontRise1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleRise1.setFont(fontRise1);
            //设置垂直居中
            styleRise1.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRise1.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
            row2.getCell(6).setCellValue("单号:" + offerVo.getOffer().getNum());
            row2.getCell(6).setCellStyle(styleRise1);
            //
            XSSFRow row5_1 = bodySheet.createRow(3);
            //设置行高
            row5_1.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells5_1 = createRowAndCell(row5_1, 7, bodyStyle(workbook, "等线", 9));
            row5_1.getCell(0).setCellValue("To: " + myOffer.getCustomerName());
            row5_1.getCell(0).setCellStyle(styleRise);


            //
            XSSFRow row5_2 = bodySheet.createRow(4);
            //设置行高
            row5_2.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells5_2 = createRowAndCell(row5_2, 7, bodyStyle(workbook, "等线", 9));
            row5_2.getCell(0).setCellValue("ATTN: ");
            row5_2.getCell(0).setCellStyle(styleRise);
            //
            XSSFRow row5_3 = bodySheet.createRow(5);
            //设置行高
            row5_3.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_3, 7, bodyStyle(workbook, "等线", 9));
            row5_3.getCell(0).setCellValue("From: " + userMsg.getName());
            row5_3.getCell(0).setCellStyle(styleRise);

            int quotation = 21;
            XSSFRow row1 = bodySheet.createRow(quotation);
            XSSFRow row1_1 = bodySheet.createRow(quotation + 1);
            XSSFRow row1_2 = bodySheet.createRow(quotation + 2);
            XSSFRow row1_3 = bodySheet.createRow(quotation + 3);
            //设置行高
            row1.setHeightInPoints(20);
            // 样式对象 
            XSSFCellStyle styleQuotation = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontQuotation = workbook.createFont();
            fontQuotation.setFontName("等线");
            fontQuotation.setFontHeightInPoints((short) 9);
            fontQuotation.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleQuotation.setFont(fontQuotation);
            //设置垂直居中
            styleQuotation.setVerticalAlignment(VerticalAlignment.CENTER);
            styleQuotation.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            styleQuotation.setWrapText(true);
            //创建单元格
            createRowAndCell(row1, 7, bodyStyle(workbook, "等线", 9));
            createRowAndCell(row1_1, 7, bodyStyle(workbook, "等线", 9));
            createRowAndCell(row1_2, 7, bodyStyle(workbook, "等线", 9));
            createRowAndCell(row1_3, 7, bodyStyle(workbook, "等线", 9));
            collapseAuto(bodySheet, quotation, quotation + 3, 0, 3);
            row1.getCell(0).setCellValue(
                    "Quotation of  Absen-" + offerPanelsList.get(i).getSeriesName() + " LED Display");
            row1.getCell(0).setCellStyle(styleQuotation);


            int first = 53;
            XSSFRow row5_4 = bodySheet.createRow(first);
            //设置行高
            row5_4.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_4, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first, 0, 6);
            row5_4.getCell(0).setCellValue("技术参数:");
            row5_4.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row5_5 = bodySheet.createRow(first + 1);
            //设置行高
            row5_5.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_5, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first + 1, 1, 6);
            row5_5.getCell(0).setCellStyle(styleColor);

            row5_5.getCell(1).setCellValue("模组参数");
            row5_5.getCell(1).setCellStyle(style);


            int firstRow = first + 2;

            XSSFRow row6 = bodySheet.createRow(firstRow);
            //设置行高
            row6.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row6, firstRow);
            row6.getCell(0).setCellValue("模组尺寸 (w x h) (mm)");

            //尺寸信息
            if (panelDetailsList.get(i).getModule().getWidth() != null
                    && panelDetailsList.get(i).getModule().getHeight() != null) {
                row6.getCell(2).setCellValue(
                        panelDetailsList.get(i).getModule().getWidth().intValue() + " X " + panelDetailsList
                                .get(i).getModule().getHeight().intValue());
            }

            row6.getCell(3).setCellValue("分辨率 (w x h)");
            //分辨率
            if (panelDetailsList.get(i).getModule().getTransverse() != null
                    && panelDetailsList.get(i).getModule().getPortrait() != null) {
                row6.getCell(5).setCellValue(
                        panelDetailsList.get(i).getModule().getTransverse().intValue() + " X "
                                + panelDetailsList.get(i).getModule().getPortrait().intValue());
            }

            //第8行设置值，模组的点间距 和 像素点（点数/㎡）
            XSSFRow row7 = bodySheet.createRow(firstRow + 1);
            //设置行高
            row7.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row7, (firstRow + 1));
            row7.getCell(0).setCellValue("间距(mm)");
            //模组的点间距
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getPitch().doubleValue());
            }
            row7.getCell(3).setCellValue("像素点（点数/㎡）");
            //像素点（点数/㎡）
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(5)
                        .setCellValue(new BigDecimal(1000000).divide(panelDetailsList.get(i).getModule().getPitch().multiply(panelDetailsList.get(i).getModule().getPitch()),0,BigDecimal.ROUND_HALF_UP).toString());
            }

            //第9行设置值，箱体的配置信息
            XSSFRow row8 = bodySheet.createRow(firstRow + 2);
            //设置行高
            row8.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row8, (firstRow + 2));
            row8.getCell(0).setCellValue("LED 灯");
            row8.getCell(3).setCellValue("像素组成");
            row8.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());

            List<Box> boxList = Lists.newArrayList();
            if (panelDetailsList.get(i).getSplicingPanelsDto().size() > 0) {
                for (OfferPanelsDto offerPanels : panelDetailsList.get(i).getSplicingPanelsDto()) {
                    boxList.add(offerPanels.getBox());
                }
            }

            //拼屏数量
            int splicingBoxs = boxList.size();

            for (int j = 0; j < splicingBoxs; j++) {
                //第10行设置值，箱体的尺寸 模组个数
                XSSFRow row9 = bodySheet.createRow(firstRow + 3 + 6 * j);
                //设置行高
                row9.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(row9, 7, bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, firstRow + 3 + 6 * j, 1, 6);
                if (splicingBoxs == 1) {
                    row9.getCell(1).setCellValue("标准单元箱体");
                } else {
                    row9.getCell(1).setCellValue("标准单元箱体" + (j + 1));
                }
                row9.getCell(0).setCellStyle(styleColor);
                row9.getCell(1).setCellStyle(style);

                //第11行设置值，箱体的尺寸 模组个数
                XSSFRow row10 = bodySheet.createRow(firstRow + 4 + 6 * j);
                setRowAndCell(workbook, bodySheet, row10, (firstRow + 4 + 6 * j));
                row10.getCell(0).setCellValue("尺寸(w x h x d) (mm)");
                row10.getCell(3).setCellValue("模组个数 ");
                //箱体的尺寸
                if (boxList.get(j).getWidth() != null && boxList.get(j).getHeight() != null
                        && boxList.get(j).getThickness() != null) {
                    row10.getCell(2).setCellValue(
                            boxList.get(j).getWidth().intValue() + " x " + boxList.get(j).getHeight().intValue()
                                    + " x " + boxList.get(j).getThickness().intValue());
                }
                //模组个数
                if (boxList.get(j).getWidth() != null) {
                    row10.getCell(5).setCellValue(
                            boxList.get(j).getTransverseCount().intValue() * boxList.get(j).getPortraitCount()
                                    .intValue());
                }

                //第12行设置值，分辨率 (w x h)	 实像素点数
                XSSFRow row11 = bodySheet.createRow(firstRow + 5 + 6 * j);
                setRowAndCell(workbook, bodySheet, row11, (firstRow + 5 + 6 * j));
                row11.getCell(0).setCellValue("分辨率 (w x h)");
                row11.getCell(3).setCellValue("实像素点数");
                //分辨率
                if (boxList.get(j).getTransversePix() != null && boxList.get(j).getPortraitPix() != null) {
                    row11.getCell(2).setCellValue(
                            boxList.get(j).getTransversePix().intValue() + " x " + boxList.get(j).getPortraitPix()
                                    .intValue());
                }
                //实像素点数
                if (boxList.get(j).getWidth() != null) {
                    row11.getCell(5).setCellValue(
                            boxList.get(j).getTransversePix().intValue() * boxList.get(j).getPortraitPix()
                                    .intValue());
                }

                //第13行设置值，箱体的重量 材质
                XSSFRow row12 = bodySheet.createRow(firstRow + 6 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12, (firstRow + 6 + 6 * j));
                row12.getCell(0).setCellValue("重量 (kg/个)");
                row12.getCell(3).setCellValue("材质");
                //箱体的重量
                if (boxList.get(j).getWeight() != null) {
                    row12.getCell(2).setCellValue(boxList.get(j).getWeight().doubleValue());
                    row12.getCell(2).setCellStyle(styleDataFormat(workbook));
                }
                //材质
                if (boxList.get(j).getBoxType() != null) {
                	row12.getCell(5).setCellStyle(bodyStyle(workbook, "等线", 9));
                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
                }

                //第13行设置值，
                XSSFRow row12_1 = bodySheet.createRow(firstRow + 7 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12_1, (firstRow + 7 + 6 * j));
                row12_1.getCell(0).setCellValue("证书");

                //Certificate
                row12_1.getCell(2).setCellValue("CE");
                row12_1.getCell(3).setCellValue("结构");
                //Mechanics
                row12_1.getCell(5).setCellValue("Rental, Rear maintenance");

                //第14行设置值， 单个屏的功率  标准平均功率（Kw ） 标准最大功率（Kw）
                //标准平均功率
                XSSFRow row13 = bodySheet.createRow(firstRow + 8 + 6 * j);
                setRowAndCell(workbook, bodySheet, row13, (firstRow + 8 + 6 * j));
                row13.getCell(0).setCellValue("标准平均功率（kw/㎡ ）");
                row13.getCell(3).setCellValue("标准最大功率（kw/㎡）");
                if (panelDetailsList.get(i).getParams().getPowerAvg() != null) {
                    row13.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getPowerAvg());
                }
                //标准最大功率
                if (panelDetailsList.get(i).getParams().getPowerMax() != null) {
                    row13.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getPowerMax());
                }
            }

            //拼接箱体 占用行数
            int panelNum = splicingBoxs * 6;

            XSSFRow row15 = bodySheet.createRow(firstRow + 3 + panelNum);
            //设置行高
            row15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 3 + panelNum, 0, 6);

            row15.getCell(0).setCellValue("显示屏数据");
            row15.getCell(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow row16 = bodySheet.createRow(firstRow + 4 + panelNum);
            //设置行高
            row16.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "等线", 9));

            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 4 + panelNum, 3, 4);
            collapse(bodySheet, firstRow + 4 + panelNum, 5, 6);
            row16.getCell(1).setCellValue("项目");
            row16.getCell(2).setCellValue("宽 ");
            row16.getCell(3).setCellValue("高");
            row16.getCell(5).setCellValue("合计");

            row16.getCell(0).setCellStyle(styleColor);
            row16.getCell(1).setCellStyle(style);
            row16.getCell(2).setCellStyle(style);
            row16.getCell(3).setCellStyle(style);
            row16.getCell(5).setCellStyle(style);

            int splicingPanelsSize = panelDetailsList.get(i).getSplicingPanelsDto().size();

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第17行设置值，屏体数量
                XSSFRow row17_panel = bodySheet.createRow(firstRow + 5 + panelNum + j);
                setRowAndCell3(workbook, bodySheet, row17_panel, (firstRow + 5 + panelNum + j));
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);
                row17_panel.getCell(0).setCellValue("屏体数量" + str + "(pcs)");
                row17_panel.getCell(0).setCellStyle(styleDataZeroBold(workbook));

                row17_panel.getCell(2).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
                row17_panel.getCell(2).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(3).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
                row17_panel.getCell(3).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(5)
                        .setCellFormula("C" + (firstRow + 6 + panelNum + j) + "*D" + (firstRow + 6 + panelNum + j));
                row17_panel.getCell(5).setCellStyle(styleDataZeroBold(workbook));

                weight = weight.add(new BigDecimal(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
                                .get(i).getSplicingPanelsDto().get(j).getLcount())
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
            }
            int boxNum = panelNum + splicingPanelsSize;

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第18行设置 显示分辨率 (点)
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);
                XSSFRow row18_new = bodySheet.createRow(firstRow + 5 + boxNum + j);
                setRowAndCell3(workbook, bodySheet, row18_new, (firstRow + 5 + boxNum + j));
                if (offerVo.getOffer().getSizeUnit() == 2) {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(ft)");
                } else {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(sqm)");
                }
                row18_new.getCell(2).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());

                row18_new.getCell(2).setCellStyle(styleDataFormat(workbook));
                row18_new.getCell(3).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());

                row18_new.getCell(3).setCellStyle(styleDataFormat(workbook));
                //计算面积
                if (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                        && panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null) {
                    row18_new.getCell(5).setCellFormula("C" + (firstRow + 6 + boxNum + j) + "*D" + (firstRow + 6 + boxNum + j));
                    row18_new.getCell(5).setCellStyle(styleDataFormat(workbook));
                    area = area.add(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                            .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()));

                }

                avgPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerAvg()))
                        .add(avgPower);
                maxPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerMax()))
                        .add(maxPower);
            }
            int splicingPanels = boxNum + splicingPanelsSize - 2;
            //第19行设置值，
            XSSFRow row19 = bodySheet.createRow(firstRow + 7 + splicingPanels);
            setRowAndCell3(workbook, bodySheet, row19, (firstRow + 7 + splicingPanels));
            row19.getCell(0).setCellValue("显示分辨率 (点)");
            row19.getCell(2)
                    .setCellValue(panelDetailsList.get(i).getModule().getTransverse().intValue() * wNum);
            row19.getCell(2)
                    .setCellStyle(styleDataFormat(workbook));
            row19.getCell(3)
                    .setCellValue(panelDetailsList.get(i).getModule().getPortrait().intValue() * lNum);
            row19.getCell(3)
                    .setCellStyle(styleDataFormat(workbook));
            row19.getCell(5).setCellFormula("C" + (firstRow + 8 + splicingPanels) + "*D" + (firstRow + 8 + splicingPanels));
            row19.getCell(5).setCellStyle(styleDataFormat(workbook));

            //第20行设置值，显示屏重量(kg)
            XSSFRow row20 = bodySheet.createRow(firstRow + 8 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 8 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 8 + splicingPanels, 2, 6);
            row20.getCell(0).setCellValue("显示屏重量(kg)");
            row20.getCell(2).setCellValue(weight.toString());
            row20.getCell(2).setCellStyle(styleDataFormat(workbook));

            //第21行设置值，标准平均功率（Kw ）	 标准最大功率（Kw）
            XSSFRow row21 = bodySheet.createRow(firstRow + 9 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 9 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 3, 4);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 5, 6);
            //标准平均功率（Kw ）
            row21.getCell(0).setCellValue("标准平均功率（Kw ）");
            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            row21.getCell(2).setCellStyle(styleDataFormat(workbook));
            row21.getCell(5).setCellStyle(styleDataFormat(workbook));
            //标准最大功率（Kw）
            row21.getCell(3).setCellValue("标准最大功率（Kw）");
            row21.getCell(5).setCellValue(maxPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

            //第22行设置值，亮度
            XSSFRow row22_21 = bodySheet.createRow(firstRow + 10 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22_21, (firstRow + 10 + splicingPanels), 12);
            
            row22_21.getCell(0).setCellStyle(styleColor);
            row22_21.getCell(1).setCellValue("参数");
            row22_21.getCell(2).setCellValue("参数值");
            row22_21.getCell(1).setCellStyle(style);
            row22_21.getCell(2).setCellStyle(style);
            //参数修改1
            row22_21.getCell(3).setCellValue("参数");
            row22_21.getCell(5).setCellValue("参数值");
            row22_21.getCell(3).setCellStyle(style);
            row22_21.getCell(5).setCellStyle(style);

/*            //第22行设置值，亮度
            XSSFRow row22 = bodySheet.createRow(firstRow + 11 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22, (firstRow + 11 + splicingPanels), 10);
            row22.getCell(1).setCellValue("亮度 ");
            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
            		panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");
            
            //第23行设置值，视角
            XSSFRow row23 = bodySheet.createRow(firstRow + 12 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row23, (firstRow + 12 + splicingPanels), 10);
            row23.getCell(1).setCellValue("可视角度");
            row23.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getViewing());
            
            //第24行设置值，最小可视距离
            XSSFRow row24 = bodySheet.createRow(firstRow + 13 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row24, (firstRow + 13 + splicingPanels), 10);
            row24.getCell(1).setCellValue("推荐最小观看距离");
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
            	row24.getCell(2)
            	.setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
            }
            //第28行设置值，灰度
            XSSFRow row25 = bodySheet.createRow(firstRow + 14 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row25, (firstRow + 14 + splicingPanels), 10);
            row25.getCell(1).setCellValue("灰度等级");
            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());
            
            //第28行设置值，色温
            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 10);
            row26.getCell(1).setCellValue("色温");
            row26.getCell(2).setCellValue("6500K");
            
            //第29行设置值，刷新率
            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 10);
            row27.getCell(1).setCellValue("刷新频率");
            row27.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
            		panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");
            
            //第34行设置值,对比度
            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 10);
            row28.getCell(1).setCellValue("对比度");
            row28.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());
            
            //第34行设置值,
            XSSFRow row29 = bodySheet.createRow(firstRow + 18 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row29, (firstRow + 18 + splicingPanels), 10);
            row29.getCell(1).setCellValue("工作电压");
            row29.getCell(2).setCellValue("50 or 60 Hertz");
            
            //第34行设置值,工作电压
            XSSFRow row29_1 = bodySheet.createRow(firstRow + 19 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row29_1, (firstRow + 19 + splicingPanels), 10);
            row29_1.getCell(1).setCellValue("工作电压");
            row29_1.getCell(2).setCellValue("110~240 Volt");
            
            //第34行设置值,盲点率
            XSSFRow row30 = bodySheet.createRow(firstRow + 20 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row30, (firstRow + 20 + splicingPanels), 10);
            row30.getCell(1).setCellValue("盲点率");
            row30.getCell(2).setCellValue("＜1/10000");
            
            //第34行设置值,使用寿命（50%亮度）
            XSSFRow row31 = bodySheet.createRow(firstRow + 21 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row31, (firstRow + 21 + splicingPanels), 10);
            row31.getCell(1).setCellValue("使用寿命（50%亮度）");
            row31.getCell(2).setCellValue("100000 hours");
            
            //第34行设置值，防护等级
            XSSFRow row32 = bodySheet.createRow(firstRow + 22 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row32, (firstRow + 22 + splicingPanels), 10);
            row32.getCell(1).setCellValue("防护等级（正面/背面）");
            row32.getCell(2).setCellValue("Front IP65,  Rear IP54");
            //第34行设置值，工作环境温度
            XSSFRow row33 = bodySheet.createRow(firstRow + 23 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row33, (firstRow + 23 + splicingPanels), 10);
            row33.getCell(1).setCellValue("工作环境温度");
            row33.getCell(2).setCellValue("  ﹣10 ～﹢40 ℃");
            
            //第34行设置值，工作环境湿度
            XSSFRow row35 = bodySheet.createRow(firstRow + 24 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row35, (firstRow + 24 + splicingPanels), 10);
            row35.getCell(1).setCellValue("工作环境湿度");
            row35.getCell(2).setCellValue(" 10％ ～ 90％");
            
            //第34行设置值，控制距离
            XSSFRow row37 = bodySheet.createRow(firstRow + 25 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row37, (firstRow + 25 + splicingPanels), 10);
            row37.getCell(1).setCellValue("控制距离");
            row37.getCell(2).setCellValue("CAT5 cable:＜100 m; Single mode fiber:＜10 km");
            //第34行设置值，操作系统平台
            XSSFRow row38 = bodySheet.createRow(firstRow + 26 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row38, (firstRow + 26 + splicingPanels), 10);
            row38.getCell(1).setCellValue("操作系统平台");
            row38.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");
 			*/            
            //第22行设置值，亮度,视角
            XSSFRow row22 = bodySheet.createRow(firstRow + 11 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22, (firstRow + 11 + splicingPanels), 10);
            row22.getCell(1).setCellValue("亮度 ");
            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
                    panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");
            row22.getCell(3).setCellValue("可视角度");
            row22.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getViewing());

            XSSFRow row23 = bodySheet.createRow(firstRow + 12 + splicingPanels);
            XSSFRow row24 = bodySheet.createRow(firstRow + 13 + splicingPanels);
            XSSFRow row25 = bodySheet.createRow(firstRow + 14 + splicingPanels);
            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
            XSSFRow row29 = bodySheet.createRow(firstRow + 18 + splicingPanels);
            XSSFRow row30 = bodySheet.createRow(firstRow + 19 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row23, (firstRow + 12 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row24, (firstRow + 13 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row25, (firstRow + 14 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 9);
            setRowAndCell2(workbook, bodySheet, row29, (firstRow + 18 + splicingPanels), 9);
            setRowAndCell2(workbook, bodySheet, row30, (firstRow + 19 + splicingPanels), 9);
            
            //第23行设置值:最小可视距离,灰度
            row23.getCell(1).setCellValue("推荐最小观看距离");
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
            	row23.getCell(2)
                        .setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
            }
            row23.getCell(3).setCellValue("灰度等级");
            row23.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());

            //第24行设置值:色温,刷新率
            row24.getCell(1).setCellValue("色温");
            row24.getCell(2).setCellValue("6500K");
            row24.getCell(3).setCellValue("刷新频率");
            row24.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");

            //第25行设置值,对比度,工作赫兹
            row25.getCell(1).setCellValue("对比度");
            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());
            row25.getCell(3).setCellValue("赫兹");
            row25.getCell(5).setCellValue("50 or 60 Hertz");

            //第26行设置值:工作电压,盲点率
            row26.getCell(1).setCellValue("工作电压");
            row26.getCell(2).setCellValue("110~240 Volt");
            row26.getCell(3).setCellValue("盲点率");
            row26.getCell(5).setCellValue("＜1/10000");

            //第27行设置值:使用寿命（50%亮度）,防护等级
            row27.getCell(1).setCellValue("使用寿命（50%亮度）");
            row27.getCell(2).setCellValue("100000 hours");
            row27.getCell(3).setCellValue("防护等级（正面/背面）");
            row27.getCell(5).setCellValue("Front IP65,  Rear IP54");
            
            //第28行设置值:工作环境温度,工作环境湿度
            row28.getCell(1).setCellValue("工作环境温度");
            row28.getCell(2).setCellValue("  ﹣10 ～﹢40 ℃");
            row28.getCell(3).setCellValue("工作环境湿度");
            row28.getCell(5).setCellValue(" 10％ ～ 90％");

            //第34行设置值:控制距离,操作系统平台
            row29.getCell(1).setCellValue("控制距离");
            row29.getCell(2).setCellValue("CAT5 cable:＜100 m; Single mode fiber:＜10 km");
            row30.getCell(1).setCellValue("操作系统平台");
            row30.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");

            //将firstRow减7;
            firstRow=firstRow-7;
            //第43行设置值，屏体价格
            XSSFRow row39 = bodySheet.createRow(firstRow + 27 + splicingPanels);
            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 27 + splicingPanels, 0, 6);
            row39.getCell(0).setCellValue("工程报价表");
            row39.getCell(0).setCellStyle(titleStyleMain(workbook));

            //第43行设置值，屏体价格
            XSSFRow row40 = bodySheet.createRow(firstRow + 28 + splicingPanels);
            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "等线", 9));
            //第43行设置值，屏体价格
            XSSFRow row41 = bodySheet.createRow(firstRow + 29 + splicingPanels);
            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            row41.getCell(5).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(5).setCellStyle(style);
            row41.getCell(6).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(6).setCellStyle(style);
            row41.getCell(0).setCellStyle(styleColor);
            //设定合并单元格的区域

            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 1, 1);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 2, 2);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 3, 4);
//            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 5, 5);
            row40.getCell(1).setCellValue("屏体部分");
            row40.getCell(2).setCellValue("规格或型号");
            row40.getCell(3).setCellValue("数量");
            row40.getCell(5).setCellValue("单价");
            row40.getCell(6).setCellValue("小计");

            row40.getCell(0).setCellStyle(styleColor);
            row40.getCell(1).setCellStyle(style);
            row40.getCell(2).setCellStyle(style);
            row40.getCell(3).setCellStyle(style);
            row40.getCell(5).setCellStyle(style);
            row40.getCell(6).setCellStyle(style);

            //第43行设置值，屏体价格
            XSSFRow row43 = bodySheet.createRow(firstRow + 30 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 30 + splicingPanels, 0, 1);
            row43.getCell(0).setCellValue("LED 显示屏");
            row43.getCell(2).setCellValue(offerPanelsList.get(i).getSeriesName());
            
            row43.getCell(3).setCellValue(String.valueOf(area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP)));

            row43.getCell(4).setCellValue("sqm");
            BigDecimal panelPrice = panelDetailsList.get(i).getOfferPanels().getPrice() == null ? new BigDecimal(0)
                    : panelDetailsList.get(i).getOfferPanels().getPrice();
            System.out.println("计算总价：" + area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(panelPrice));

            row43.getCell(5).setCellValue(String.valueOf(panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
            //将价格设置为右对齐格式
            row43.getCell(5).setCellStyle(styleDataFormat(workbook));
            
            row43.getCell(6).setCellFormula("D" + (firstRow + 31 + splicingPanels) + "*F" + (firstRow + 31 + splicingPanels));
            row43.getCell(6).setCellStyle(styleDataFormat(workbook));
            //第45行设置值，屏体价格
//            XSSFRow row44 = bodySheet.createRow(firstRow + 31 + splicingPanels);
//            //设置行高
//            row44.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "等线", 9));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
//            row44.getCell(0).setCellValue("接收卡");
//            row44.getCell(2).setCellValue("Nova A8S");
//            row44.getCell(3).setCellValue("1.00 ");
//            row44.getCell(4).setCellValue("pc");

            //第45行设置值，屏体价格
            XSSFRow row45 = bodySheet.createRow(firstRow + 31 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
            row45.getCell(0).setCellValue("LED播放软件");
            row45.getCell(2).setCellValue("NOVA STUDIO");
            row45.getCell(3).setCellValue("1");
            row45.getCell(4).setCellValue("pc");
            row45.getCell(5).setCellValue("免费");
            row45.getCell(6).setCellValue("免费");

            //
            XSSFRow row46_46 = bodySheet.createRow(firstRow + 32 + splicingPanels);
            //创建单元格
            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 32 + splicingPanels, 1, 6);
            cells46.get(1).setCellValue("外围设备");
            cells46.get(1).setCellStyle(style);
            cells46.get(0).setCellStyle(styleColor);

            //获取各种备件集合的长度
            int standardLength = 0;
            int selfStandardLength = 0;
            int spareLength = 0;
            int selfSpareLength = 0;
            int freeLength = 0;
            int selfFreeLength = 0;
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                standardLength = panelDetailsList.get(i).getStandardDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                selfStandardLength = panelDetailsList.get(i).getSelfStandardList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                spareLength = panelDetailsList.get(i).getSpareDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                selfSpareLength = panelDetailsList.get(i).getSelfSpareList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                freeLength = panelDetailsList.get(i).getFreeDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                selfFreeLength = panelDetailsList.get(i).getSelfFreeList().size();
            }
            int totalLength = standardLength + selfStandardLength + spareLength + selfSpareLength;
            int index = firstRow + 33 + splicingPanels;
            //标配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getStandardDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j, 0, 1);
                    cells.get(0)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getRealCount());
                    cells.get(4)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getStandardDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (index + j + 1) + "*F" + (index + j + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义标配备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfStandardList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfStandardList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + j + 1 + standardLength) + "*F" + (index + j + 1 + standardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            //选配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSpareDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength + selfStandardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength + selfStandardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSpareDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellFormula("D" + (index + j + 1 + standardLength + selfStandardLength) +
                            "*F" + (index + j + 1 + standardLength + selfStandardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义选配信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfSpareList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength - selfSpareLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfSpareList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellFormula(
                            "D" + (index + j + totalLength - selfSpareLength + 1) + "*F" + (
                                    index + j + totalLength - selfSpareLength + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

//            XSSFRow transferRowTitleFree = bodySheet.createRow(index + totalLength);
//            transferRowTitleFree.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells1_free = createRowAndCell(transferRowTitleFree, 7,
//                    bodyStyle(workbook, "等线", 9));
//            //设定合并单元格的区域
//            collapse(bodySheet, index + totalLength, 0, 6);
//
//            cells1_free.get(0).setCellValue("其它费用");
//            cells1_free.get(0).setCellStyle(titleStyleMain(workbook));

            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList()) || CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                XSSFRow transferRowTitleFree = bodySheet.createRow(index + totalLength);
                transferRowTitleFree.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_free = createRowAndCell(transferRowTitleFree, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLength, 1, 6);
                cells1_free.get(0).setCellStyle(styleColor);
                cells1_free.get(1).setCellValue("免费配件");
                cells1_free.get(1).setCellStyle(style);
            }

            //免费备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getFreeDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + 1);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength + 1, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }
            //自定义免费备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfFreeList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + freeLength + 1);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength + freeLength + 1, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }

            int totalLengthCount = totalLength + freeLength + selfFreeLength + 1;

            List<TransportPackage> transportPackageList = offerVo.getTransport();
            List<OfferServiceDto> serviceList = offerVo.getServiceListDto();

            boolean showPackageTitle = true;
            if(transportPackageList.size() == 0 && serviceList.size() == 0) {
                if (offerVo.getTransfer() != null) {
                    if (offerVo.getTransfer().getCost() == null || offerVo.getTransfer().getCost().intValue() == 0) {
                        showPackageTitle = false;
                    }
                }else {
                    showPackageTitle = false;
                }
            }
            if (showPackageTitle) {

                XSSFRow transferRowTitle = bodySheet.createRow(index + totalLengthCount);
                transferRowTitle.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_1 = createRowAndCell(transferRowTitle, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLengthCount, 1, 6);
                cells1_1.get(1).setCellValue("包装");
                cells1_1.get(1).setCellStyle(style);
                cells1_1.get(0).setCellStyle(styleColor);
                totalLengthCount++;
            }

            //服务列表长度
            int serviceLength = 0;
            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
                serviceLength = offerVo.getServiceListDto().size();
                //服务费用
                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLengthCount);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLengthCount, 0, 1);
                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
                                    .getServiceDiscount().doubleValue() / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + 1 + j + totalLengthCount) + "*F" + (index + 1 + j + totalLengthCount));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            int totalService = index + totalLengthCount + serviceLength;

            if (transportPackageList.size() > 0) {
                //运输费用
                for (int j = 0; j < transportPackageList.size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(j + totalService);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, j + totalService, 0, 1);
                    cells.get(0).setCellValue(transportPackageList.get(j).getPackages());
                    cells.get(3).setCellValue(transportPackageList.get(j).getNumber());
                    cells.get(4).setCellValue("");
                    cells.get(6).setCellType(CellType.NUMERIC);
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellValue(String.valueOf(transportPackageList.get(j).getPriceUnit().setScale(2, BigDecimal.ROUND_HALF_UP)));
                    
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (j + totalService + 1) + "*F" + (j + totalService + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            if (offerVo.getTransfer() != null) {
                if (offerVo.getTransfer().getCost() != null && offerVo.getTransfer().getCost().intValue() != 0) {
                    //创建行
                    XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size());
                    //设置行高
                    autoRowTransfer.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, totalService + transportPackageList.size(), 0, 1);
                    cells.get(0).setCellValue("发货日期");
                    cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "0" : String.valueOf(offerVo.getTransfer().getCost().setScale(2, BigDecimal.ROUND_HALF_UP)));
                    cells.get(3).setCellStyle(styleDataFormat(workbook));
                    cells.get(4).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(4).setCellValue("");
                    cells.get(5).setCellValue(1);
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 1) + "*F" + (totalService + transportPackageList.size() + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));

                    totalService++;
                }
            }

            XSSFRow transferRow6 = bodySheet.createRow(totalService + transportPackageList.size());
            transferRow6.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells6_1 = createRowAndCell(transferRow6, 7, bodyStyle(workbook, "等线", 9));
            transferRow6.setHeightInPoints(20);
            collapse(bodySheet, totalService + transportPackageList.size(), 0, 4);
            collapse(bodySheet, totalService + transportPackageList.size(), 5, 6);
            XSSFRichTextString val6 = new XSSFRichTextString(
                    "(" + (StringUtils.isEmpty(offerVo.getTransfer().getTrade()) == true ? "" : offerVo.getTransfer().getTrade()) + "  深圳  " + myOffer.getMoneyUnit() + ") 总计:");
            transferRow6.getCell(0).setCellValue(val6);
//            transferRow6.getCell(5).setCellFormula(
//                    "SUM(G" + (42 + splicingPanels) + ":G" + (totalService + transportPackageList.size() + 1) + ")");
            transferRow6.getCell(5).setCellValue(offerVo.offer.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
            transferRow6.getCell(0).setCellStyle(style);
            transferRow6.getCell(5).setCellStyle(totaltyle(workbook, "right"));

            int totalSize = totalService + transportPackageList.size();

            // 样式对象 
            XSSFCellStyle style2 = workbook.createCellStyle();
            //设置垂直居中
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            Font font = workbook.createFont();
            font.setFontName("等线");
            font.setFontHeightInPoints((short) 10);
            style2.setFont(font);

            int remarkIndex = 1;

            if(offerVo.getOffer().getRemark() != null) {
                XSSFRow transferRow6_r = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_r.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_r, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex, 0, 6);
                transferRow6_r.getCell(0).setCellValue(offerVo.getOffer().getRemark());
                transferRow6_r.getCell(0).setCellStyle(style2);

                remarkIndex++;
            }

            //优惠价格
            if (offerVo.getOffer().getSpecialPrice() != null) {
                XSSFRow transferRow6_1 = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_1.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_1, 7, bodyStyle(workbook, "等线", 9));
                transferRow6_1.setHeightInPoints(20);
                collapse(bodySheet, totalSize + remarkIndex, 0, 4);
                collapse(bodySheet, totalSize + remarkIndex, 5, 6);
                String remark="优惠价格";
                if(StringUtils.isNotEmpty(offerVo.getOffer().getSpecialPriceRemark())) {
                	remark=offerVo.getOffer().getSpecialPriceRemark();
                }
                transferRow6_1.getCell(0).setCellValue(remark);
                transferRow6_1.getCell(0).setCellStyle(totaltyle(workbook, "left"));
                transferRow6_1.getCell(5).setCellValue(offerVo.getOffer().getSpecialPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
                transferRow6_1.getCell(5).setCellStyle(totaltyle(workbook, "right"));
                XSSFRow transferRow6_2 = bodySheet.createRow(totalSize + remarkIndex + 1);
                transferRow6_2.setHeightInPoints(20);
               /* //创建单元格
                createRowAndCell(transferRow6_2, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex + 1, 0, 6);
                transferRow6_2.getCell(0).setCellValue(offerVo.getOffer().getSpecialPriceRemark());
                transferRow6_2.getCell(0).setCellStyle(style2);*/
            }

            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 6);
            transferRow7.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells7_1 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "等线", 9));
            transferRow7.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 4, 0, 6);


            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
            transferRow8.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells8_1 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "等线", 9));
            transferRow8.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 5, 0, 6);
            XSSFRichTextString val8 = new XSSFRichTextString("备注:");
            transferRow8.getCell(0).setCellValue(val8);
            transferRow8.getCell(0).setCellStyle(style2);

            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
            transferRow9.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "等线", 9));
            transferRow9.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 6, 0, 6);
            XSSFRichTextString val9 = new XSSFRichTextString("1.报价有效期：此报价30天内有效");
            transferRow9.getCell(0).setCellValue(val9);
            transferRow9.getCell(0).setCellStyle(style2);

            XSSFRow transferRow10 = bodySheet.createRow(totalSize + 7);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells10 = createRowAndCell(transferRow10, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 7, 0, 6);
            StringBuffer packageStr = new StringBuffer();
            transportPackageList.forEach(item -> {
                packageStr.append(item.getPackages()).append(",");
            });
            transferRow10.getCell(0).setCellValue("2.包装方式：" + (transportPackageList.size() > 0 ? packageStr : ""));
            transferRow10.getCell(0).setCellStyle(style2);

            XSSFRow transferRow11 = bodySheet.createRow(totalSize + 8);
            transferRow11.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells11 = createRowAndCell(transferRow11, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 8, 0, 6);
            cells11.get(0).setCellValue("3.付款方式: " + (myOffer.getPayment() == null ? "" : myOffer.getPayment()));
            cells11.get(0).setCellStyle(style2);

            XSSFRow transferRow12 = bodySheet.createRow(totalSize + 9);
            transferRow12.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells12 = createRowAndCell(transferRow12, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 9, 0, 6);
            transferRow12.getCell(0).setCellValue("4.交货周期：收到预付款" + myOffer.getWaitingDate() + "个自然日（不包含中国法定节假日）");
            transferRow12.getCell(0).setCellStyle(style2);

            XSSFRow transferRow13 = bodySheet.createRow(totalSize + 10);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells13 = createRowAndCell(transferRow13, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 10, 0, 6);
            transferRow13.getCell(0).setCellValue("5.质保期限：发货之日起，屏体质保两年，备件质保一年");
            transferRow13.getCell(0).setCellStyle(style2);

            XSSFRow transferRow14 = bodySheet.createRow(totalSize + 11);
            transferRow14.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells14 = createRowAndCell(transferRow14, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 11, 0, 6);
            cells14.get(0).setCellValue(
                    "6.培训服务：免费提供惠州工厂/德国公司7天的在线培训");
            cells14.get(0).setCellStyle(style2);

            XSSFRow transferRow15 = bodySheet.createRow(totalSize + 12);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15R = createRowAndCell(transferRow15, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 12, 0, 6);
            cells15R.get(0).setCellValue(
                    "7.安装指导和售后维保服务可选");
            cells15R.get(0).setCellStyle(style2);
//        	removeRow(bodySheet,87);//删除第80行到第89行，然后使下方单元格上移  
        }
        //重新计算excel里面的公式
        reCalculating(workbook);
    }

    /**
     * 20180919 新增版本
     * 其他区域模板读写方法
     */
    private void templateEUNew2(List<PanelDetails> panelDetailsList, XSSFWorkbook workbook,
                                MyOfferDto myOffer, List<String> msg, OfferVo offerVo, XSSFSheet
                                        sheet, List<OfferPanelsDto> offerPanelsList,
                                String filepath, User userMsg) {

// 样式对象 
        XSSFCellStyle styleColor = workbook.createCellStyle();
        //单元格背景色
        styleColor.setFillForegroundColor(new XSSFColor(new java.awt.Color(245, 131, 32)));
        styleColor.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        for (int i = 0; i < panelDetailsList.size(); i++) {

            //总重量
            BigDecimal weight = new BigDecimal(0);
            //总面积
            BigDecimal area = new BigDecimal(0);
            // 箱体 宽数
            int wNum = 0;
            // 箱体 高数
            int lNum = 0;
            //平均功耗
            BigDecimal avgPower = new BigDecimal(0);

            BigDecimal maxPower = new BigDecimal(0);
            //读取sheet
            XSSFSheet bodySheet = workbook.getSheetAt(i);


            //生成单元格样式
            XSSFCellStyle style = titleStyle(workbook);


            XSSFCellStyle styleRise = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise = workbook.createFont();
            fontRise.setFontName("等线");
            fontRise.setFontHeightInPoints((short) 12);
            fontRise.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleRise.setFont(fontRise);
            //设置垂直居中
            styleRise.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRise.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);

            XSSFRow row1 = bodySheet.createRow(1);
            createRowAndCell(row1, 7, bodyStyle(workbook, "等线", 9));

            //设置行高
            row1.setHeightInPoints(20);
            row1.getCell(0).setCellValue(
                    "Quotation of  Absen-" + offerPanelsList.get(i).getSeriesName() + " LED Display");
            row1.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row2 = bodySheet.createRow(2);
            //设置行高
            row2.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row2, 7, bodyStyle(workbook, "等线", 9));
            XSSFCellStyle styleRise1 = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise1 = workbook.createFont();
            fontRise1.setFontName("等线");
            fontRise1.setFontHeightInPoints((short) 12);
            styleRise1.setFont(fontRise1);
            //设置垂直居中
            styleRise1.setVerticalAlignment(VerticalAlignment.CENTER);
//            styleRise1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            row2.getCell(0).setCellValue("Quotation Number:" + offerVo.getOffer().getNum());
            row2.getCell(0).setCellStyle(styleRise1);


            int first = 4;
            XSSFRow row5_4 = bodySheet.createRow(first);
            //设置行高
            row5_4.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_4, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first, 0, 6);
            row5_4.getCell(0).setCellValue("Screen Configuation");
            row5_4.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row5_5 = bodySheet.createRow(first + 1);
            //设置行高
            row5_5.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_5, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first + 1, 1, 6);

            row5_5.getCell(0).setCellStyle(styleColor);
            row5_5.getCell(1).setCellValue("Module");
            row5_5.getCell(1).setCellStyle(style);

            int firstRow = first + 2;

            XSSFRow row6 = bodySheet.createRow(firstRow);
            //设置行高
            row6.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row6, firstRow);
            row6.getCell(0).setCellValue("Dimensions (w x h) (mm)");

            //尺寸信息
            if (panelDetailsList.get(i).getModule().getWidth() != null
                    && panelDetailsList.get(i).getModule().getHeight() != null) {
                row6.getCell(2).setCellValue(
                        panelDetailsList.get(i).getModule().getWidth().intValue() + " X " + panelDetailsList
                                .get(i).getModule().getHeight().intValue());
            }

            row6.getCell(3).setCellValue("Resolution (w x h)");
            //分辨率
            if (panelDetailsList.get(i).getModule().getTransverse() != null
                    && panelDetailsList.get(i).getModule().getPortrait() != null) {
                row6.getCell(5).setCellValue(
                        panelDetailsList.get(i).getModule().getTransverse().intValue() + " X "
                                + panelDetailsList.get(i).getModule().getPortrait().intValue());
            }

            //第8行设置值，模组的点间距 和 像素点（点数/㎡）
            XSSFRow row7 = bodySheet.createRow(firstRow + 1);
            //设置行高
            row7.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row7, (firstRow + 1));
            row7.getCell(0).setCellValue("Pixel pitch (mm)");
            //模组的点间距
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getPitch().doubleValue());
            }
            row7.getCell(3).setCellValue("Pixel Density (pixels/m2)");
            //像素点（点数/㎡）
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(5)
                        .setCellValue(new BigDecimal(1000000).divide(panelDetailsList.get(i).getModule().getPitch().multiply(panelDetailsList.get(i).getModule().getPitch()),0,BigDecimal.ROUND_HALF_UP).toString());
            }

            //第9行设置值，箱体的配置信息
            XSSFRow row8 = bodySheet.createRow(firstRow + 2);
            //设置行高
            row8.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row8, (firstRow + 2));
            row8.getCell(0).setCellValue("LED Lamp");
            row8.getCell(3).setCellValue("Pixel Configuration");
            row8.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());

            List<Box> boxList = Lists.newArrayList();
            if (panelDetailsList.get(i).getSplicingPanelsDto().size() > 0) {
                for (OfferPanelsDto offerPanels : panelDetailsList.get(i).getSplicingPanelsDto()) {
                    boxList.add(offerPanels.getBox());
                }
            }

            //拼屏数量
            int splicingBoxs = boxList.size();

            for (int j = 0; j < splicingBoxs; j++) {
                //第10行设置值，箱体的尺寸 模组个数
                XSSFRow row9 = bodySheet.createRow(firstRow + 3 + 6 * j);
                //设置行高
                row9.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(row9, 7, bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, firstRow + 3 + 6 * j, 1, 6);
                if (splicingBoxs == 1) {
                    row9.getCell(1).setCellValue("Standard Panel");
                } else {
                    row9.getCell(1).setCellValue("Standard Panel" + (j + 1));
                }
                row9.getCell(0).setCellStyle(styleColor);
                row9.getCell(1).setCellStyle(style);

                //第11行设置值，箱体的尺寸 模组个数
                XSSFRow row10 = bodySheet.createRow(firstRow + 4 + 6 * j);
                setRowAndCell(workbook, bodySheet, row10, (firstRow + 4 + 6 * j));
                row10.getCell(0).setCellValue("Dimensions(w x h x d)(mm)");
                row10.getCell(3).setCellValue("Module Quantity ");
                //箱体的尺寸
                if (boxList.get(j).getWidth() != null && boxList.get(j).getHeight() != null
                        && boxList.get(j).getThickness() != null) {
                    row10.getCell(2).setCellValue(
                            boxList.get(j).getWidth().intValue() + " x " + boxList.get(j).getHeight().intValue()
                                    + " x " + boxList.get(j).getThickness().intValue());
                }
                //模组个数
                if (boxList.get(j).getWidth() != null) {
                    row10.getCell(5).setCellValue(
                            boxList.get(j).getTransverseCount().intValue() * boxList.get(j).getPortraitCount()
                                    .intValue());
                }

                //第12行设置值，分辨率 (w x h)	 实像素点数
                XSSFRow row11 = bodySheet.createRow(firstRow + 5 + 6 * j);
                setRowAndCell(workbook, bodySheet, row11, (firstRow + 5 + 6 * j));
                row11.getCell(0).setCellValue("Physical Resolution (w x h)");
                row11.getCell(3).setCellValue("Physical Pixels (total)");
                //分辨率
                if (boxList.get(j).getTransversePix() != null && boxList.get(j).getPortraitPix() != null) {
                    row11.getCell(2).setCellValue(
                            boxList.get(j).getTransversePix().intValue() + " x " + boxList.get(j).getPortraitPix()
                                    .intValue());
                }
                //实像素点数
                if (boxList.get(j).getWidth() != null) {
                    row11.getCell(5).setCellValue(
                            boxList.get(j).getTransversePix().intValue() * boxList.get(j).getPortraitPix()
                                    .intValue());
                }

                //第13行设置值，箱体的重量 材质
                XSSFRow row12 = bodySheet.createRow(firstRow + 6 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12, (firstRow + 6 + 6 * j));
                row12.getCell(0).setCellValue("Weight/Panel (kg)");
                row12.getCell(3).setCellValue("Material");
                //箱体的重量
                if (boxList.get(j).getWeight() != null) {
                    row12.getCell(2).setCellValue(boxList.get(j).getWeight().doubleValue());
                }
                //材质
                if (boxList.get(j).getBoxType() != null) {
                	row12.getCell(5).setCellStyle(bodyStyle(workbook, "等线", 9));
                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
                }

                //第13行设置值，
                XSSFRow row12_1 = bodySheet.createRow(firstRow + 7 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12_1, (firstRow + 7 + 6 * j));
                row12_1.getCell(0).setCellValue("Certificate");

                //Certificate
                row12_1.getCell(2).setCellValue("CE");
                row12_1.getCell(3).setCellValue("Mechanics");
                //Mechanics
                row12_1.getCell(5).setCellValue("Rental, Rear maintenance");

                //第14行设置值， 单个屏的功率  标准平均功率（Kw ） 标准最大功率（Kw）
                //标准平均功率
                XSSFRow row13 = bodySheet.createRow(firstRow + 8 + 6 * j);
                setRowAndCell(workbook, bodySheet, row13, (firstRow + 8 + 6 * j));
                row13.getCell(0).setCellValue("Average Power/sqm(watts)");
                row13.getCell(3).setCellValue("Max Power/sqm(watts)");
                if (panelDetailsList.get(i).getParams().getPowerAvg() != null) {
                    row13.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getPowerAvg());
                }
                //标准最大功率
                if (panelDetailsList.get(i).getParams().getPowerMax() != null) {
                    row13.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getPowerMax());
                }
            }

            //拼接箱体 占用行数
            int panelNum = splicingBoxs * 6;

            XSSFRow row15 = bodySheet.createRow(firstRow + 3 + panelNum);
            //设置行高
            row15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 3 + panelNum, 0, 6);
            row15.getCell(0).setCellValue("Display Data");
            row15.getCell(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow row16 = bodySheet.createRow(firstRow + 4 + panelNum);
            //设置行高
            row16.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "等线", 9));

            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 4 + panelNum, 1, 1);
            collapse(bodySheet, firstRow + 4 + panelNum, 3, 4);
            collapse(bodySheet, firstRow + 4 + panelNum, 5, 6);
            row16.getCell(1).setCellValue("ITEM");
            row16.getCell(2).setCellValue("Width ");
            row16.getCell(3).setCellValue("Height");
            row16.getCell(5).setCellValue("Total");

            row16.getCell(0).setCellStyle(styleColor);
            row16.getCell(1).setCellStyle(style);
            row16.getCell(2).setCellStyle(style);
            row16.getCell(3).setCellStyle(style);
            row16.getCell(5).setCellStyle(style);

            int splicingPanelsSize = panelDetailsList.get(i).getSplicingPanelsDto().size();

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第17行设置值，屏体数量
                XSSFRow row17_panel = bodySheet.createRow(firstRow + 5 + panelNum + j);
                setRowAndCell3(workbook, bodySheet, row17_panel, (firstRow + 5 + panelNum + j));
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);
                row17_panel.getCell(0).setCellValue("Panel Quantity" + str + "(pcs)");
                row17_panel.getCell(0).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(2).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
                row17_panel.getCell(2).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(3).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
                row17_panel.getCell(3).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(5)
                        .setCellFormula("C" + (firstRow + 6 + panelNum + j) + "*D" + (firstRow + 6 + panelNum + j));
                row17_panel.getCell(5)
                        .setCellStyle(styleDataZeroBold(workbook));

                weight = weight.add(new BigDecimal(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
                                .get(i).getSplicingPanelsDto().get(j).getLcount())
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
            }
            int boxNum = panelNum + splicingPanelsSize;

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第18行设置 显示分辨率 (点)
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);
                XSSFRow row18_new = bodySheet.createRow(firstRow + 5 + boxNum + j);
                setRowAndCell3(workbook, bodySheet, row18_new, (firstRow + 5 + boxNum + j));
                if (offerVo.getOffer().getSizeUnit() == 2) {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(ft)");
                } else {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(sqm)");
                }
                row18_new.getCell(2).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());
                row18_new.getCell(2).setCellStyle(styleDataFormat(workbook));
                row18_new.getCell(3).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());
                row18_new.getCell(3).setCellStyle(styleDataFormat(workbook));
                //计算面积
                if (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                        && panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null) {
                    row18_new.getCell(5).setCellFormula("C" + (firstRow + 6 + boxNum + j) + "*D" + (firstRow + 6 + boxNum + j));
                    row18_new.getCell(5).setCellStyle(styleDataFormat(workbook));
                    area = area.add(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                            .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()));

                }

                avgPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerAvg()))
                        .add(avgPower);
                maxPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerMax()))
                        .add(maxPower);
            }
            int splicingPanels = boxNum + splicingPanelsSize - 2;
            //第19行设置值，
            XSSFRow row19 = bodySheet.createRow(firstRow + 7 + splicingPanels);
            setRowAndCell3(workbook, bodySheet, row19, (firstRow + 7 + splicingPanels));
            row19.getCell(0).setCellValue("Display Resolution(dots)");
            row19.getCell(2)
                    .setCellValue(panelDetailsList.get(i).getModule().getTransverse().intValue() * wNum);
            row19.getCell(2)
                    .setCellStyle(styleDataFormatZero(workbook));
            row19.getCell(3)
                    .setCellValue(panelDetailsList.get(i).getModule().getPortrait().intValue() * lNum);
            row19.getCell(3)
                    .setCellStyle(styleDataFormatZero(workbook));
            row19.getCell(5).setCellFormula("C" + (firstRow + 8 + splicingPanels) + "*D" + (firstRow + 8 + splicingPanels));
            row19.getCell(5).setCellStyle(styleDataFormatZero(workbook));

            //第20行设置值，显示屏重量(kg)
            XSSFRow row20 = bodySheet.createRow(firstRow + 8 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 8 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 8 + splicingPanels, 2, 6);
            row20.getCell(0).setCellValue("Total Net Weight (kgs)");
            row20.getCell(2).setCellValue(weight.toString());
            row20.getCell(2).setCellStyle(styleDataFormat(workbook));

            //第21行设置值，标准平均功率（Kw ）	 标准最大功率（Kw）
            XSSFRow row21 = bodySheet.createRow(firstRow + 9 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 9 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 3, 4);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 5, 6);
            //标准平均功率（Kw ）
            row21.getCell(0).setCellValue("Total Average Power(watts)");
            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            row21.getCell(2).setCellStyle(styleDataFormat(workbook));
            row21.getCell(5).setCellStyle(styleDataFormat(workbook));
            //标准最大功率（Kw）
            row21.getCell(3).setCellValue("Total Max Power(watts)");
            row21.getCell(5).setCellValue(maxPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            
            //第22行设置值，亮度
            XSSFRow row22_21 = bodySheet.createRow(firstRow + 10 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22_21, (firstRow + 10 + splicingPanels), 12);
            
            row22_21.getCell(0).setCellStyle(styleColor);
            row22_21.getCell(1).setCellValue("Parameter");
            row22_21.getCell(2).setCellValue("Value");
            row22_21.getCell(1).setCellStyle(style);
            row22_21.getCell(2).setCellStyle(style);
            //参数修改1
            row22_21.getCell(3).setCellValue("Parameter");
            row22_21.getCell(5).setCellValue("Value");
            row22_21.getCell(3).setCellStyle(style);
            row22_21.getCell(5).setCellStyle(style);
            /*
            //第22行设置值，亮度
            XSSFRow row22 = bodySheet.createRow(firstRow + 11 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22, (firstRow + 11 + splicingPanels), 10);
            row22.getCell(1).setCellValue("Brightness ");
            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
                    panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");

            //第23行设置值，视角
            XSSFRow row23 = bodySheet.createRow(firstRow + 12 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row23, (firstRow + 12 + splicingPanels), 10);
            row23.getCell(1).setCellValue("Viewing Angle ");
            row23.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getViewing());

            //第24行设置值，最小可视距离
            XSSFRow row24 = bodySheet.createRow(firstRow + 13 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row24, (firstRow + 13 + splicingPanels), 10);
            row24.getCell(1).setCellValue("Minimum Viewing Distance");
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row24.getCell(2)
                        .setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
            }
            //第28行设置值，灰度
            XSSFRow row25 = bodySheet.createRow(firstRow + 14 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row25, (firstRow + 14 + splicingPanels), 10);
            row25.getCell(1).setCellValue("Gray scale");
            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());

            //第28行设置值，色温
            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 10);
            row26.getCell(1).setCellValue("color temperature ");
            row26.getCell(2).setCellValue("6500K");

            //第29行设置值，刷新率
            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 10);
            row27.getCell(1).setCellValue("Refresh rate");
            row27.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");

            //第34行设置值,对比度
            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 10);
            row28.getCell(1).setCellValue("Contrast ratio");
            row28.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());

            //第34行设置值,
            XSSFRow row29 = bodySheet.createRow(firstRow + 18 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row29, (firstRow + 18 + splicingPanels), 10);
            row29.getCell(1).setCellValue("Input power frequency");
            row29.getCell(2).setCellValue("50 or 60 Hertz");

            //第34行设置值,工作电压
            XSSFRow row29_1 = bodySheet.createRow(firstRow + 19 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row29_1, (firstRow + 19 + splicingPanels), 10);
            row29_1.getCell(1).setCellValue("Input Voltage");
            row29_1.getCell(2).setCellValue("110~240 Volt");

            //第34行设置值,盲点率
            XSSFRow row30 = bodySheet.createRow(firstRow + 20 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row30, (firstRow + 20 + splicingPanels), 10);
            row30.getCell(1).setCellValue("PPM");
            row30.getCell(2).setCellValue("＜1/10000");

            //第34行设置值,使用寿命（50%亮度）
            XSSFRow row31 = bodySheet.createRow(firstRow + 21 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row31, (firstRow + 21 + splicingPanels), 10);
            row31.getCell(1).setCellValue("Lifetime");
            row31.getCell(2).setCellValue("100000 hours");

            //第34行设置值，防护等级
            XSSFRow row32 = bodySheet.createRow(firstRow + 22 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row32, (firstRow + 22 + splicingPanels), 10);
            row32.getCell(1).setCellValue("IP RATING");
            row32.getCell(2).setCellValue("Front IP65,  Rear IP54");
            //第34行设置值，工作环境温度
            XSSFRow row33 = bodySheet.createRow(firstRow + 23 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row33, (firstRow + 23 + splicingPanels), 10);
            row33.getCell(1).setCellValue("Operating temperature");
            row33.getCell(2).setCellValue(" ﹣10 ～﹢40 ℃");

            //第34行设置值，工作环境湿度
            XSSFRow row35 = bodySheet.createRow(firstRow + 24 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row35, (firstRow + 24 + splicingPanels), 10);
            row35.getCell(1).setCellValue("Operating humidity ");
            row35.getCell(2).setCellValue(" 10％ ～ 90％");

            //第34行设置值，控制距离
            XSSFRow row37 = bodySheet.createRow(firstRow + 25 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row37, (firstRow + 25 + splicingPanels), 10);
            row37.getCell(1).setCellValue("Control distance");
            row37.getCell(2).setCellValue("CAT5 cable:＜100 m; Single mode fiber:＜10 km");
            //第34行设置值，操作系统平台
            XSSFRow row38 = bodySheet.createRow(firstRow + 26 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row38, (firstRow + 26 + splicingPanels), 10);
            row38.getCell(1).setCellValue("Signal input format");
            row38.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");*/

            //第22行设置值，亮度,视角
            XSSFRow row22 = bodySheet.createRow(firstRow + 11 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22, (firstRow + 11 + splicingPanels), 10);
            row22.getCell(1).setCellValue("Brightness ");
            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
                    panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");
            row22.getCell(3).setCellValue("Viewing Angle");
            row22.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getViewing());

            XSSFRow row23 = bodySheet.createRow(firstRow + 12 + splicingPanels);
            XSSFRow row24 = bodySheet.createRow(firstRow + 13 + splicingPanels);
            XSSFRow row25 = bodySheet.createRow(firstRow + 14 + splicingPanels);
            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
            XSSFRow row29 = bodySheet.createRow(firstRow + 18 + splicingPanels);
            XSSFRow row30 = bodySheet.createRow(firstRow + 19 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row23, (firstRow + 12 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row24, (firstRow + 13 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row25, (firstRow + 14 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 9);
            setRowAndCell2(workbook, bodySheet, row29, (firstRow + 18 + splicingPanels), 9);
            setRowAndCell2(workbook, bodySheet, row30, (firstRow + 19 + splicingPanels), 9);
            
            //第23行设置值:最小可视距离,灰度
            row23.getCell(1).setCellValue("Minimum Viewing Distance");
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
            	row23.getCell(2)
                        .setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
            }
            row23.getCell(3).setCellValue("Gray scale");
            row23.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());

            //第24行设置值:色温,刷新率
            row24.getCell(1).setCellValue("color temperature");
            row24.getCell(2).setCellValue("6500K");
            row24.getCell(3).setCellValue("Refresh rate");
            row24.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");

            //第25行设置值,对比度,工作赫兹
            row25.getCell(1).setCellValue("Contrast ratio");
            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());
            row25.getCell(3).setCellValue("Input power frequency");
            row25.getCell(5).setCellValue("50 or 60 Hertz");

            //第26行设置值:工作电压,盲点率
            row26.getCell(1).setCellValue("Input Voltage");
            row26.getCell(2).setCellValue("110~240 Volt");
            row26.getCell(3).setCellValue("PPM");
            row26.getCell(5).setCellValue("＜1/10000");

            //第27行设置值:使用寿命（50%亮度）,防护等级
            row27.getCell(1).setCellValue("Lifetime");
            row27.getCell(2).setCellValue("100000 hours");
            row27.getCell(3).setCellValue("IP RATING");
            row27.getCell(5).setCellValue("Front IP65,  Rear IP54");
            
            //第28行设置值:工作环境温度,工作环境湿度
            row28.getCell(1).setCellValue("Operating temperature");
            row28.getCell(2).setCellValue("  ﹣10 ～﹢40 ℃");
            row28.getCell(3).setCellValue("Operating humidity");
            row28.getCell(5).setCellValue(" 10％ ～ 90％");

            //第34行设置值:控制距离,操作系统平台
            row29.getCell(1).setCellValue("Control distance");
            row29.getCell(2).setCellValue("CAT5 cable:＜100 m; Single mode fiber:＜10 km");
            row30.getCell(1).setCellValue("Signal input format");
            row30.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");

            //将firstRow减7;
            firstRow=firstRow-7;
            
            //第43行设置值，屏体价格
            XSSFRow row39 = bodySheet.createRow(firstRow + 27 + splicingPanels);
            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 27 + splicingPanels, 0, 6);
            row39.getCell(0).setCellValue("QUOTATION");
            row39.getCell(0).setCellStyle(titleStyleMain(workbook));

            //第43行设置值，屏体价格
            XSSFRow row40 = bodySheet.createRow(firstRow + 28 + splicingPanels);
            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "等线", 9));
            //第43行设置值，屏体价格
            XSSFRow row41 = bodySheet.createRow(firstRow + 29 + splicingPanels);
            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            row41.getCell(5).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(5).setCellStyle(style);
            row41.getCell(6).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(6).setCellStyle(style);
            row41.getCell(0).setCellStyle(styleColor);
            //设定合并单元格的区域

            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 1, 1);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 2, 2);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 3, 4);
//            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 5, 5);
            row40.getCell(0).setCellStyle(styleColor);
            row40.getCell(1).setCellValue("Item No.");
            row40.getCell(2).setCellValue("Model No.");
            row40.getCell(3).setCellValue("Qty.");
            row40.getCell(5).setCellValue("Unit Price");
            row40.getCell(6).setCellValue("Sub Total");

            row40.getCell(1).setCellStyle(style);
            row40.getCell(2).setCellStyle(style);
            row40.getCell(3).setCellStyle(style);
            row40.getCell(5).setCellStyle(style);
            row40.getCell(6).setCellStyle(style);

            //第43行设置值，屏体价格
            XSSFRow row43 = bodySheet.createRow(firstRow + 30 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 30 + splicingPanels, 0, 1);
            row43.getCell(0).setCellValue("LED screen");
            row43.getCell(2).setCellValue(offerPanelsList.get(i).getSeriesName());


            row43.getCell(3).setCellValue(String.valueOf(area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP)));
            row43.getCell(3).setCellStyle(styleDataFormat(workbook));
            row43.getCell(4).setCellValue("sqm");
            BigDecimal panelPrice = panelDetailsList.get(i).getOfferPanels().getPrice() == null ? new BigDecimal(0)
                    : panelDetailsList.get(i).getOfferPanels().getPrice();
//            System.out.println("计算总价：" + area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(panelPrice));

            row43.getCell(5).setCellValue(String.valueOf(panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
            row43.getCell(5).setCellStyle(styleDataFormat(workbook));
            row43.getCell(6).setCellFormula("D" + (firstRow + 31 + splicingPanels) + "*F" + (firstRow + 31 + splicingPanels));
            row43.getCell(6).setCellStyle(styleDataFormat(workbook));
            //第45行设置值，屏体价格
//            XSSFRow row44 = bodySheet.createRow(firstRow + 31 + splicingPanels);
//            //设置行高
//            row44.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "等线", 9));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
//            row44.getCell(0).setCellValue("Receiving Card");
//            row44.getCell(2).setCellValue("Nova A8S");
//            row44.getCell(3).setCellValue("1.00 ");
//            row44.getCell(4).setCellValue("pc");

            //第45行设置值，屏体价格
            XSSFRow row45 = bodySheet.createRow(firstRow + 31 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
            row45.getCell(0).setCellValue("LED Software");
            row45.getCell(2).setCellValue("NOVA STUDIO");
            row45.getCell(3).setCellValue("1");
            row45.getCell(4).setCellValue("pc");
            row45.getCell(5).setCellValue("Free");
            row45.getCell(6).setCellValue("Free");


            //
            XSSFRow row46_46 = bodySheet.createRow(firstRow + 32 + splicingPanels);
            //创建单元格
            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 32 + splicingPanels, 1, 6);
            cells46.get(0).setCellStyle(styleColor);
            cells46.get(1).setCellValue("Recommended spare parts");
            cells46.get(1).setCellStyle(style);

            //获取各种备件集合的长度
            int standardLength = 0;
            int selfStandardLength = 0;
            int spareLength = 0;
            int selfSpareLength = 0;
            int freeLength = 0;
            int selfFreeLength = 0;
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                standardLength = panelDetailsList.get(i).getStandardDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                selfStandardLength = panelDetailsList.get(i).getSelfStandardList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                spareLength = panelDetailsList.get(i).getSpareDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                selfSpareLength = panelDetailsList.get(i).getSelfSpareList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                freeLength = panelDetailsList.get(i).getFreeDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                selfFreeLength = panelDetailsList.get(i).getSelfFreeList().size();
            }
            int totalLength = standardLength + selfStandardLength + spareLength + selfSpareLength;
            int index = firstRow + 33 + splicingPanels;
            //标配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getStandardDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j, 0, 1);
                    cells.get(0)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getRealCount());
                    cells.get(4)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getStandardDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (index + j + 1) + "*F" + (index + j + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义标配备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfStandardList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfStandardList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + j + 1 + standardLength) + "*F" + (index + j + 1 + standardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            //选配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSpareDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength + selfStandardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength + selfStandardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSpareDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(6).setCellFormula("D" + (index + j + 1 + standardLength + selfStandardLength) +
                            "*F" + (index + j + 1 + standardLength + selfStandardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义选配信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfSpareList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength - selfSpareLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfSpareList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(6).setCellFormula(
                            "D" + (index + j + totalLength - selfSpareLength + 1) + "*F" + (
                                    index + j + totalLength - selfSpareLength + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList()) || CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                XSSFRow transferRowTitleFree = bodySheet.createRow(index + totalLength);
                transferRowTitleFree.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_free = createRowAndCell(transferRowTitleFree, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLength, 1, 6);
                cells1_free.get(0).setCellStyle(styleColor);
                cells1_free.get(1).setCellValue("Spare parts--free");
                cells1_free.get(1).setCellStyle(style);
                index = index + 1;
            }

            //免费备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getFreeDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }
            //自定义免费备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfFreeList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + freeLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + totalLength + j + freeLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }

            int totalLengthCount = totalLength + freeLength + selfFreeLength;

            List<TransportPackage> transportPackageList = offerVo.getTransport();
            List<OfferServiceDto> serviceList = offerVo.getServiceListDto();

            boolean showPackageTitle = true;
            if(transportPackageList.size() == 0 && serviceList.size() == 0) {
                if (offerVo.getTransfer() != null) {
                    if (offerVo.getTransfer().getCost() == null || offerVo.getTransfer().getCost().intValue() == 0) {
                        showPackageTitle = false;
                    }
                }else {
                    showPackageTitle = false;
                }
            }
            if (showPackageTitle) {

                XSSFRow transferRowTitle = bodySheet.createRow(index + totalLengthCount);
                transferRowTitle.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_1 = createRowAndCell(transferRowTitle, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLengthCount, 1, 6);
                cells1_1.get(0).setCellStyle(styleColor);
                cells1_1.get(1).setCellValue("Package");
                cells1_1.get(1).setCellStyle(style);
                index = index + 1;
            }

            //服务列表长度
            int serviceLength = 0;
            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
                serviceLength = offerVo.getServiceListDto().size();
                //服务费用
                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLengthCount);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLengthCount, 0, 1);
                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellValue(format(
                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
                                    .getServiceDiscount().doubleValue() / 100));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + 1 + j + totalLengthCount) + "*F" + (index + 1 + j + totalLengthCount));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            int totalService = index + totalLengthCount + serviceLength;

            if (transportPackageList.size() > 0) {
                //运输费用
                for (int j = 0; j < transportPackageList.size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(j + totalService);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, j + totalService, 0, 1);
                    cells.get(0).setCellValue(transportPackageList.get(j).getPackages());
                    cells.get(3).setCellValue(transportPackageList.get(j).getNumber());
                    cells.get(4).setCellValue("");
                    cells.get(5).setCellValue(String.valueOf(transportPackageList.get(j).getPriceUnit().setScale(2, BigDecimal.ROUND_HALF_UP)));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (j + totalService + 1) + "*F" + (j + totalService + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            if (offerVo.getTransfer() != null) {
                if (offerVo.getTransfer().getCost() != null && offerVo.getTransfer().getCost().intValue() != 0) {
                    //创建行
                    XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size());
                    //设置行高
                    autoRowTransfer.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, totalService + transportPackageList.size(), 0, 1);
                    cells.get(0).setCellValue("EST.Shipping");
                    cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "0" : String.valueOf(offerVo.getTransfer().getCost().setScale(2, BigDecimal.ROUND_HALF_UP)));
                    cells.get(3).setCellStyle(styleDataFormat(workbook));
                    cells.get(4).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(4).setCellValue("0");
                    cells.get(5).setCellValue(1);
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 1) + "*F" + (totalService + transportPackageList.size() + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                    totalService = totalService + 1;
                }
            }

            XSSFRow transferRow6 = bodySheet.createRow(totalService + transportPackageList.size());
            transferRow6.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(transferRow6, 7, bodyStyle(workbook, "等线", 9));
            transferRow6.setHeightInPoints(20);
            collapse(bodySheet, totalService + transportPackageList.size(), 0, 4);
            collapse(bodySheet, totalService + transportPackageList.size(), 5, 6);
            XSSFRichTextString val6 = new XSSFRichTextString(
                    "(" + (StringUtils.isEmpty(offerVo.getTransfer().getTrade()) == true ? "" : offerVo.getTransfer().getTrade()) + "  Shenzhen  " + myOffer.getMoneyUnit() + ") Total:");
            transferRow6.getCell(0).setCellValue(val6);

//            transferRow6.getCell(5).setCellFormula(
//                    "SUM(G" + (20 + splicingPanels) + ":G" + (totalService + transportPackageList.size() + 1) + ")");

            transferRow6.getCell(5).setCellValue(offerVo.offer.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
            transferRow6.getCell(0).setCellStyle(style);
            transferRow6.getCell(5).setCellStyle(totaltyle(workbook, "right"));


            int totalSize = totalService + transportPackageList.size();

            // 样式对象 
            XSSFCellStyle style2 = workbook.createCellStyle();
            //设置垂直居中
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            Font font = workbook.createFont();
            font.setFontName("等线");
            font.setFontHeightInPoints((short) 10);
            style2.setFont(font);


            int remarkIndex = 1;

            if(offerVo.getOffer().getRemark() != null) {
                XSSFRow transferRow6_r = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_r.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_r, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex, 0, 6);
                transferRow6_r.getCell(0).setCellValue(offerVo.getOffer().getRemark());
                transferRow6_r.getCell(0).setCellStyle(style2);

                remarkIndex++;
            }

            //优惠价格
            if (offerVo.getOffer().getSpecialPrice() != null) {
                XSSFRow transferRow6_1 = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_1.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_1, 7, bodyStyle(workbook, "等线", 9));
                transferRow6_1.setHeightInPoints(20);
                collapse(bodySheet, totalSize + remarkIndex, 0, 4);
                collapse(bodySheet, totalSize + remarkIndex, 5, 6);
                String remark="优惠价格";
                if(StringUtils.isNotEmpty(offerVo.getOffer().getSpecialPriceRemark())) {
                	remark=offerVo.getOffer().getSpecialPriceRemark();
                }
                transferRow6_1.getCell(0).setCellValue(remark);
                
                transferRow6_1.getCell(0).setCellStyle(totaltyle(workbook, "left"));
                transferRow6_1.getCell(5).setCellValue(offerVo.getOffer().getSpecialPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
                transferRow6_1.getCell(5).setCellStyle(totaltyle(workbook, "right"));
                XSSFRow transferRow6_2 = bodySheet.createRow(totalSize + remarkIndex + 1);
                transferRow6_2.setHeightInPoints(20);
                /*//创建单元格
                createRowAndCell(transferRow6_2, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex + 1, 0, 6);
                transferRow6_2.getCell(0).setCellValue(offerVo.getOffer().getSpecialPriceRemark());
                transferRow6_2.getCell(0).setCellStyle(style2);*/
            }
            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 6);
            transferRow7.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells7_1 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "等线", 9));
            transferRow7.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 4, 0, 6);


            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
            transferRow8.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells8_1 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "等线", 9));
            transferRow8.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 5, 0, 6);
            XSSFRichTextString val8 = new XSSFRichTextString("Remarks:");
            transferRow8.getCell(0).setCellValue(val8);
            transferRow8.getCell(0).setCellStyle(style2);

            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
            transferRow9.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "等线", 9));
            transferRow9.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 6, 0, 6);
            XSSFRichTextString val9 = new XSSFRichTextString(
                    "1. Quotation valid time: 30 days from quotation date");
            transferRow9.getCell(0).setCellValue(val9);
            transferRow9.getCell(0).setCellStyle(style2);

            XSSFRow transferRow10 = bodySheet.createRow(totalSize + 7);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells10 = createRowAndCell(transferRow10, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 7, 0, 6);
            StringBuffer packageStr = new StringBuffer();
            transportPackageList.forEach(item -> {
                packageStr.append(item.getPackages()).append(",");
            });

            transferRow10.getCell(0).setCellValue("2. Packing:  " + (transportPackageList.size() > 0 ? packageStr : " "));
            transferRow10.getCell(0).setCellStyle(style2);

            XSSFRow transferRow11 = bodySheet.createRow(totalSize + 8);
            transferRow11.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells11 = createRowAndCell(transferRow11, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 8, 0, 6);
            cells11.get(0).setCellValue("3. Payment terms: "+(myOffer.getPayment() == null ? "": myOffer.getPayment() ));
            style2.setWrapText(true);
            cells11.get(0).setCellStyle(style2);

            XSSFRow transferRow12 = bodySheet.createRow(totalSize + 9);
            transferRow12.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells12 = createRowAndCell(transferRow12, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 9, 0, 6);
            transferRow12.getCell(0).setCellValue("4. leading time: " + myOffer.getWaitingDate() + " days.");
            transferRow12.getCell(0).setCellStyle(style2);

            XSSFRow transferRow13 = bodySheet.createRow(totalSize + 10);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells13 = createRowAndCell(transferRow13, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 10, 0, 6);
            transferRow13.getCell(0).setCellValue("5. Warranty time: "+ offerVo.getOffer().getGuarantee()+" years from delivery date  ");
            transferRow13.getCell(0).setCellStyle(style2);

            XSSFRow transferRow14 = bodySheet.createRow(totalSize + 11);
            transferRow14.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells14 = createRowAndCell(transferRow14, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 11, 0, 6);
            cells14.get(0).setCellValue(
                    "6. Free trainning: 7 days on-site complete technical trainning in our Shenzhen factory");
            cells14.get(0).setCellStyle(style2);

            XSSFRow transferRow15 = bodySheet.createRow(totalSize + 12);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15R = createRowAndCell(transferRow15, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 12, 0, 6);
            cells15R.get(0).setCellValue(
                    "7. Installation Guidance and after-sales premium service package: optional ");
            cells15R.get(0).setCellStyle(style2);

//            //插入图片
//            XSSFRow transferRow16 = bodySheet.createRow(totalSize + 13);
//            transferRow15.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow16, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            XSSFRow transferRow17 = bodySheet.createRow(totalSize + 14);
//            transferRow17.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow17, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            XSSFRow transferRow18 = bodySheet.createRow(totalSize + 15);
//            transferRow15.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow18, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            XSSFRow transferRow19 = bodySheet.createRow(totalSize + 16);
//            transferRow15.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow19, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            String fileName = filepath + "absen.png";
//
//            byte[] bytes = new byte[0];
//            try {
//                InputStream is = new FileInputStream(fileName);
//                bytes = IOUtils.toByteArray(is);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
//
//            CreationHelper helper = workbook.getCreationHelper();
//            Drawing drawing = bodySheet.createDrawingPatriarch();
//            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short) 0, totalSize + 13, (short) 2, totalSize + 16);
//
//
//            Picture pict = drawing.createPicture(anchor, pictureIdx);
//            pict.resize();


            XSSFRow transferRow21 = bodySheet.createRow(totalSize + 17);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells21R = createRowAndCell(transferRow21, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 17, 0, 6);
            cells21R.get(0).setCellValue(
                    "Shenzhen Absen Optoelectronic Co., LTD.");
            cells21R.get(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow transferRow22 = bodySheet.createRow(totalSize + 18);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells22R = createRowAndCell(transferRow22, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 18, 0, 6);
            cells22R.get(0).setCellValue(
                    "Add: 18-20F building 3A, Cloud Park, Bantian, Longgang District, Shenzhen 518129, P.R. China");
            cells22R.get(0).setCellStyle(style2);

            XSSFRow transferRow23 = bodySheet.createRow(totalSize + 19);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells23R = createRowAndCell(transferRow23, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 19, 0, 6);
            cells23R.get(0).setCellValue(
                    "E-mail: absen@absen.com; Http:// www.absen.com");
            cells23R.get(0).setCellStyle(style2);
            XSSFRow transferRow24 = bodySheet.createRow(totalSize + 20);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells24R = createRowAndCell(transferRow24, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 20, 0, 6);
            cells24R.get(0).setCellValue(
                    "Tel: + 86-755-89747399  Fax: + 86-755-89747599");
            cells24R.get(0).setCellStyle(style2);
        }
        //重新计算excel里面的公式
        reCalculating(workbook);
    }

    /**
     * 中国区域模板读写方法
     */
    private void templateZHPdfNew(List<PanelDetails> panelDetailsList, List<XSSFWorkbook> workbooks,
                                  MyOfferDto myOffer, List<String> msg, OfferVo offerVo, List<OfferPanelsDto> offerPanelsList,
                                  String filepath, User userMsg) {


        for (int i = 0; i < workbooks.size(); i++) {
            XSSFWorkbook workbook = workbooks.get(i);
            // 样式对象 
            XSSFCellStyle styleColor = workbook.createCellStyle();
            //单元格背景色
            styleColor.setFillForegroundColor(new XSSFColor(new java.awt.Color(245, 131, 32)));
            styleColor.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            //总重量
            BigDecimal weight = new BigDecimal(0);
            //总面积
            BigDecimal area = new BigDecimal(0);
            // 箱体 宽数
            int wNum = 0;
            // 箱体 高数
            int lNum = 0;
            //平均功耗
            BigDecimal avgPower = new BigDecimal(0);

            BigDecimal maxPower = new BigDecimal(0);
            //读取sheet
            XSSFSheet bodySheet = workbook.getSheetAt(0);

            //生成单元格样式
            XSSFCellStyle style = titleStyle(workbook);

            XSSFCellStyle styleRise = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise = workbook.createFont();
            fontRise.setFontName("等线");
            fontRise.setFontHeightInPoints((short) 12);
            fontRise.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleRise.setFont(fontRise);
            //设置垂直居中
            styleRise.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRise.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            //
            XSSFRow row2 = bodySheet.createRow(2);
            //设置行高
            row2.setHeightInPoints(20);
            //创建单元格
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            List<XSSFCell> cells2 = createRowAndCell(row2, 7, bodyStyle(workbook, "等线", 9));
            row2.getCell(0).setCellValue("Date:" + sdf.format(offerVo.offer.getCreateTime()));
            row2.getCell(0).setCellStyle(styleRise);
            XSSFCellStyle styleRise1 = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise1 = workbook.createFont();
            fontRise1.setFontName("等线");
            fontRise1.setFontHeightInPoints((short) 12);
            fontRise1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleRise1.setFont(fontRise1);
            //设置垂直居中
            styleRise1.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRise1.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
            row2.getCell(6).setCellValue("单号:" + offerVo.getOffer().getNum());
            row2.getCell(6).setCellStyle(styleRise1);
            //
            XSSFRow row5_1 = bodySheet.createRow(3);
            //设置行高
            row5_1.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells5_1 = createRowAndCell(row5_1, 7, bodyStyle(workbook, "等线", 9));
            row5_1.getCell(0).setCellValue("To: " + myOffer.getCustomerName());
            row5_1.getCell(0).setCellStyle(styleRise);


            //
            XSSFRow row5_2 = bodySheet.createRow(4);
            //设置行高
            row5_2.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells5_2 = createRowAndCell(row5_2, 7, bodyStyle(workbook, "等线", 9));
            row5_2.getCell(0).setCellValue("ATTN: ");
            row5_2.getCell(0).setCellStyle(styleRise);
            //
            XSSFRow row5_3 = bodySheet.createRow(5);
            //设置行高
            row5_3.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_3, 7, bodyStyle(workbook, "等线", 9));
            row5_3.getCell(0).setCellValue("From: " + userMsg.getName());
            row5_3.getCell(0).setCellStyle(styleRise);

            int quotation = 21;
            XSSFRow row1 = bodySheet.createRow(quotation);
            XSSFRow row1_1 = bodySheet.createRow(quotation + 1);
            XSSFRow row1_2 = bodySheet.createRow(quotation + 2);
            XSSFRow row1_3 = bodySheet.createRow(quotation + 3);
            //设置行高
            row1.setHeightInPoints(20);
            // 样式对象 
            XSSFCellStyle styleQuotation = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontQuotation = workbook.createFont();
            fontQuotation.setFontName("等线");
            fontQuotation.setFontHeightInPoints((short) 20);
            fontQuotation.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleQuotation.setFont(fontQuotation);
            //设置垂直居中
            styleQuotation.setVerticalAlignment(VerticalAlignment.CENTER);
            styleQuotation.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            styleQuotation.setWrapText(true);
            //创建单元格
            createRowAndCell(row1, 7, bodyStyle(workbook, "等线", 9));
            createRowAndCell(row1_1, 7, bodyStyle(workbook, "等线", 9));
            createRowAndCell(row1_2, 7, bodyStyle(workbook, "等线", 9));
            createRowAndCell(row1_3, 7, bodyStyle(workbook, "等线", 9));
            collapseAuto(bodySheet, quotation, quotation + 3, 0, 3);
            row1.getCell(0).setCellValue(
                    "Quotation of  Absen-" + offerPanelsList.get(i).getSeriesName() + " LED Display");
            row1.getCell(0).setCellStyle(styleQuotation);


            int first = 53;
            XSSFRow row5_4 = bodySheet.createRow(first);
            //设置行高
            row5_4.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_4, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first, 0, 6);
            row5_4.getCell(0).setCellValue("技术参数:");
            row5_4.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row5_5 = bodySheet.createRow(first + 1);
            //设置行高
            row5_5.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_5, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first + 1, 1, 6);
            row5_5.getCell(0).setCellStyle(styleColor);

            row5_5.getCell(1).setCellValue("模组参数");
            row5_5.getCell(1).setCellStyle(style);


            int firstRow = first + 2;

            XSSFRow row6 = bodySheet.createRow(firstRow);
            //设置行高
            row6.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row6, firstRow);
            row6.getCell(0).setCellValue("模组尺寸 (w x h) (mm)");

            //尺寸信息
            if (panelDetailsList.get(i).getModule().getWidth() != null
                    && panelDetailsList.get(i).getModule().getHeight() != null) {
                row6.getCell(2).setCellValue(
                        panelDetailsList.get(i).getModule().getWidth().intValue() + " X " + panelDetailsList
                                .get(i).getModule().getHeight().intValue());
            }

            row6.getCell(3).setCellValue("分辨率 (w x h)");
            //分辨率
            if (panelDetailsList.get(i).getModule().getTransverse() != null
                    && panelDetailsList.get(i).getModule().getPortrait() != null) {
                row6.getCell(5).setCellValue(
                        panelDetailsList.get(i).getModule().getTransverse().intValue() + " X "
                                + panelDetailsList.get(i).getModule().getPortrait().intValue());
            }

            //第8行设置值，模组的点间距 和 像素点（点数/㎡）
            XSSFRow row7 = bodySheet.createRow(firstRow + 1);
            //设置行高
            row7.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row7, (firstRow + 1));
            row7.getCell(0).setCellValue("间距(mm)");
            //模组的点间距
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getPitch().doubleValue());
            }
            row7.getCell(3).setCellValue("像素点（点数/㎡）");
            //像素点（点数/㎡）
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(5)
                        .setCellValue(new BigDecimal(1000000).divide(panelDetailsList.get(i).getModule().getPitch().multiply(panelDetailsList.get(i).getModule().getPitch()),0,BigDecimal.ROUND_HALF_UP).toString());
            }

            //第9行设置值，箱体的配置信息
            XSSFRow row8 = bodySheet.createRow(firstRow + 2);
            //设置行高
            row8.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row8, (firstRow + 2));
            row8.getCell(0).setCellValue("LED 灯");
            row8.getCell(3).setCellValue("像素组成");
            row8.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());

            List<Box> boxList = Lists.newArrayList();
            if (panelDetailsList.get(i).getSplicingPanelsDto().size() > 0) {
                for (OfferPanelsDto offerPanels : panelDetailsList.get(i).getSplicingPanelsDto()) {
                    boxList.add(offerPanels.getBox());
                }
            }

            //拼屏数量
            int splicingBoxs = boxList.size();

            for (int j = 0; j < splicingBoxs; j++) {
                //第10行设置值，箱体的尺寸 模组个数
                XSSFRow row9 = bodySheet.createRow(firstRow + 3 + 6 * j);
                //设置行高
                row9.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(row9, 7, bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, firstRow + 3 + 6 * j, 1, 6);
                if (splicingBoxs == 1) {
                    row9.getCell(1).setCellValue("标准单元箱体");
                } else {
                    row9.getCell(1).setCellValue("标准单元箱体" + (j + 1));
                }
                row9.getCell(0).setCellStyle(styleColor);
                row9.getCell(1).setCellStyle(style);

                //第11行设置值，箱体的尺寸 模组个数
                XSSFRow row10 = bodySheet.createRow(firstRow + 4 + 6 * j);
                setRowAndCell(workbook, bodySheet, row10, (firstRow + 4 + 6 * j));
                row10.getCell(0).setCellValue("尺寸(w x h x d) (mm)");
                row10.getCell(3).setCellValue("模组个数 ");
                //箱体的尺寸
                if (boxList.get(j).getWidth() != null && boxList.get(j).getHeight() != null
                        && boxList.get(j).getThickness() != null) {
                    row10.getCell(2).setCellValue(
                            boxList.get(j).getWidth().intValue() + " x " + boxList.get(j).getHeight().intValue()
                                    + " x " + boxList.get(j).getThickness().intValue());
                }
                //模组个数
                if (boxList.get(j).getWidth() != null) {
                    row10.getCell(5).setCellValue(
                            boxList.get(j).getTransverseCount().intValue() * boxList.get(j).getPortraitCount()
                                    .intValue());
                }

                //第12行设置值，分辨率 (w x h)	 实像素点数
                XSSFRow row11 = bodySheet.createRow(firstRow + 5 + 6 * j);
                setRowAndCell(workbook, bodySheet, row11, (firstRow + 5 + 6 * j));
                row11.getCell(0).setCellValue("分辨率 (w x h)");
                row11.getCell(3).setCellValue("实像素点数");
                //分辨率
                if (boxList.get(j).getTransversePix() != null && boxList.get(j).getPortraitPix() != null) {
                    row11.getCell(2).setCellValue(
                            boxList.get(j).getTransversePix().intValue() + " x " + boxList.get(j).getPortraitPix()
                                    .intValue());
                }
                //实像素点数
                if (boxList.get(j).getWidth() != null) {
                    row11.getCell(5).setCellValue(
                            boxList.get(j).getTransversePix().intValue() * boxList.get(j).getPortraitPix()
                                    .intValue());
                }

                //第13行设置值，箱体的重量 材质
                XSSFRow row12 = bodySheet.createRow(firstRow + 6 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12, (firstRow + 6 + 6 * j));
                row12.getCell(0).setCellValue("重量 (kg/个)");
                row12.getCell(3).setCellValue("材质");
                //箱体的重量
                if (boxList.get(j).getWeight() != null) {
                    row12.getCell(2).setCellValue(boxList.get(j).getWeight().doubleValue());
                    row12.getCell(2).setCellStyle(styleDataFormat(workbook));
                }
                //材质
                if (boxList.get(j).getBoxType() != null) {
                	row12.getCell(5).setCellStyle(bodyStyle(workbook, "等线", 9));
                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
                }

                //第13行设置值，
                XSSFRow row12_1 = bodySheet.createRow(firstRow + 7 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12_1, (firstRow + 7 + 6 * j));
                row12_1.getCell(0).setCellValue("证书");

                //Certificate
                row12_1.getCell(2).setCellValue("CE");
                row12_1.getCell(3).setCellValue("结构");
                //Mechanics
                row12_1.getCell(5).setCellValue("Rental, Rear maintenance");

                //第14行设置值， 单个屏的功率  标准平均功率（Kw ） 标准最大功率（Kw）
                //标准平均功率
                XSSFRow row13 = bodySheet.createRow(firstRow + 8 + 6 * j);
                setRowAndCell(workbook, bodySheet, row13, (firstRow + 8 + 6 * j));
                row13.getCell(0).setCellValue("标准平均功率（kw/㎡ ）");
                row13.getCell(3).setCellValue("标准最大功率（kw/㎡）");
                if (panelDetailsList.get(i).getParams().getPowerAvg() != null) {
                    row13.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getPowerAvg());
                }
                //标准最大功率
                if (panelDetailsList.get(i).getParams().getPowerMax() != null) {
                    row13.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getPowerMax());
                }
            }

            //拼接箱体 占用行数
            int panelNum = splicingBoxs * 6;

            XSSFRow row15 = bodySheet.createRow(firstRow + 3 + panelNum);
            //设置行高
            row15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 3 + panelNum, 0, 6);

            row15.getCell(0).setCellValue("显示屏数据");
            row15.getCell(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow row16 = bodySheet.createRow(firstRow + 4 + panelNum);
            //设置行高
            row16.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "等线", 9));

            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 4 + panelNum, 3, 4);
            collapse(bodySheet, firstRow + 4 + panelNum, 5, 6);
            row16.getCell(1).setCellValue("项目");
            row16.getCell(2).setCellValue("宽 ");
            row16.getCell(3).setCellValue("高");
            row16.getCell(5).setCellValue("合计");

            row16.getCell(0).setCellStyle(styleColor);
            row16.getCell(1).setCellStyle(style);
            row16.getCell(2).setCellStyle(style);
            row16.getCell(3).setCellStyle(style);
            row16.getCell(5).setCellStyle(style);

            int splicingPanelsSize = panelDetailsList.get(i).getSplicingPanelsDto().size();

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第17行设置值，屏体数量
                XSSFRow row17_panel = bodySheet.createRow(firstRow + 5 + panelNum + j);
                setRowAndCell3(workbook, bodySheet, row17_panel, (firstRow + 5 + panelNum + j));
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);

                row17_panel.getCell(0).setCellValue("屏体数量" + str + "(pcs)");
                row17_panel.getCell(0).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(2).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
                row17_panel.getCell(2).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(3).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
                row17_panel.getCell(3).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(5)
                        .setCellFormula("C" + (firstRow + 6 + panelNum + j) + "*D" + (firstRow + 6 + panelNum + j));
                row17_panel.getCell(5).setCellStyle(styleDataZeroBold(workbook));

                weight = weight.add(new BigDecimal(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
                                .get(i).getSplicingPanelsDto().get(j).getLcount())
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
            }
            int boxNum = panelNum + splicingPanelsSize;

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第18行设置 显示分辨率 (点)
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);
                XSSFRow row18_new = bodySheet.createRow(firstRow + 5 + boxNum + j);
                setRowAndCell3(workbook, bodySheet, row18_new, (firstRow + 5 + boxNum + j));
                if (offerVo.getOffer().getSizeUnit() == 2) {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(ft)");
                } else {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(sqm)");
                }
                row18_new.getCell(2).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());

                row18_new.getCell(2).setCellStyle(styleDataFormat(workbook));
                row18_new.getCell(3).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());

                row18_new.getCell(3).setCellStyle(styleDataFormat(workbook));
                //计算面积
                if (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                        && panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null) {
                    row18_new.getCell(5).setCellFormula("C" + (firstRow + 6 + boxNum + j) + "*D" + (firstRow + 6 + boxNum + j));
                    row18_new.getCell(5).setCellStyle(styleDataFormat(workbook));
                    area = area.add(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                            .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()));

                }

                avgPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerAvg()))
                        .add(avgPower);
                maxPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerMax()))
                        .add(maxPower);
            }
            int splicingPanels = boxNum + splicingPanelsSize - 2;
            //第19行设置值，
            XSSFRow row19 = bodySheet.createRow(firstRow + 7 + splicingPanels);
            setRowAndCell3(workbook, bodySheet, row19, (firstRow + 7 + splicingPanels));
            row19.getCell(0).setCellValue("显示分辨率 (点)");
            row19.getCell(2)
                    .setCellValue(panelDetailsList.get(i).getModule().getTransverse().intValue() * wNum);
            row19.getCell(2)
                    .setCellStyle(styleDataFormatZero(workbook));
            row19.getCell(3)
                    .setCellValue(panelDetailsList.get(i).getModule().getPortrait().intValue() * lNum);
            row19.getCell(3)
                    .setCellStyle(styleDataFormatZero(workbook));
            row19.getCell(5).setCellFormula("C" + (firstRow + 8 + splicingPanels) + "*D" + (firstRow + 8 + splicingPanels));
            row19.getCell(5).setCellStyle(styleDataFormatZero(workbook));

            //第20行设置值，显示屏重量(kg)
            XSSFRow row20 = bodySheet.createRow(firstRow + 8 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 8 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 8 + splicingPanels, 2, 6);
            row20.getCell(0).setCellValue("显示屏重量(kg)");
            row20.getCell(2).setCellValue(weight.toString());
            row20.getCell(2).setCellStyle(styleDataFormat(workbook));

            //第21行设置值，标准平均功率（Kw ）	 标准最大功率（Kw）
            XSSFRow row21 = bodySheet.createRow(firstRow + 9 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 9 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 3, 4);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 5, 6);
            //标准平均功率（Kw ）
            row21.getCell(0).setCellValue("标准平均功率（Kw ）");
            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            row21.getCell(2).setCellStyle(styleDataFormat(workbook));
            row21.getCell(5).setCellStyle(styleDataFormat(workbook));
            //标准最大功率（Kw）
            row21.getCell(3).setCellValue("标准最大功率（Kw）");
            row21.getCell(5).setCellValue(maxPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

            //第22行设置值，亮度
            XSSFRow row22_21 = bodySheet.createRow(firstRow + 10 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22_21, (firstRow + 10 + splicingPanels), 12);
            row22_21.getCell(0).setCellStyle(styleColor);
            row22_21.getCell(1).setCellValue("参数");
            row22_21.getCell(2).setCellValue("参数值");
            row22_21.getCell(1).setCellStyle(style);
            row22_21.getCell(2).setCellStyle(style);

            //第22行设置值，亮度
            XSSFRow row22 = bodySheet.createRow(firstRow + 11 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22, (firstRow + 11 + splicingPanels), 10);
            row22.getCell(1).setCellValue("亮度 ");
            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
                    panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");

            //第23行设置值，视角
            XSSFRow row23 = bodySheet.createRow(firstRow + 12 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row23, (firstRow + 12 + splicingPanels), 10);
            row23.getCell(1).setCellValue("可视角度");
            row23.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getViewing());

            //第24行设置值，最小可视距离
            XSSFRow row24 = bodySheet.createRow(firstRow + 13 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row24, (firstRow + 13 + splicingPanels), 10);
            row24.getCell(1).setCellValue("推荐最小观看距离");
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row24.getCell(2)
                        .setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
            }
            //第28行设置值，灰度
            XSSFRow row25 = bodySheet.createRow(firstRow + 14 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row25, (firstRow + 14 + splicingPanels), 10);
            row25.getCell(1).setCellValue("灰度等级");
            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());

            //第28行设置值，色温
            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 10);
            row26.getCell(1).setCellValue("色温");
            row26.getCell(2).setCellValue("6500K");

            //第29行设置值，刷新率
            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 10);
            row27.getCell(1).setCellValue("刷新频率");
            row27.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");

            //第34行设置值,对比度
            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 10);
            row28.getCell(1).setCellValue("对比度");
            row28.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());

            //第34行设置值,
            XSSFRow row29 = bodySheet.createRow(firstRow + 18 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row29, (firstRow + 18 + splicingPanels), 10);
            row29.getCell(1).setCellValue("工作电压");
            row29.getCell(2).setCellValue("50 or 60 Hertz");

            //第34行设置值,工作电压
            XSSFRow row29_1 = bodySheet.createRow(firstRow + 19 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row29_1, (firstRow + 19 + splicingPanels), 10);
            row29_1.getCell(1).setCellValue("工作电压");
            row29_1.getCell(2).setCellValue("110~240 Volt");

            //第34行设置值,盲点率
            XSSFRow row30 = bodySheet.createRow(firstRow + 20 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row30, (firstRow + 20 + splicingPanels), 10);
            row30.getCell(1).setCellValue("盲点率");
            row30.getCell(2).setCellValue("＜1/10000");

            //第34行设置值,使用寿命（50%亮度）
            XSSFRow row31 = bodySheet.createRow(firstRow + 21 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row31, (firstRow + 21 + splicingPanels), 10);
            row31.getCell(1).setCellValue("使用寿命（50%亮度）");
            row31.getCell(2).setCellValue("100000 hours");

            //第34行设置值，防护等级
            XSSFRow row32 = bodySheet.createRow(firstRow + 22 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row32, (firstRow + 22 + splicingPanels), 10);
            row32.getCell(1).setCellValue("防护等级（正面/背面）");
            row32.getCell(2).setCellValue("Front IP65,  Rear IP54");
            //第34行设置值，工作环境温度
            XSSFRow row33 = bodySheet.createRow(firstRow + 23 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row33, (firstRow + 23 + splicingPanels), 10);
            row33.getCell(1).setCellValue("工作环境温度");
            row33.getCell(2).setCellValue("  ﹣10 ～﹢40 ℃");

            //第34行设置值，工作环境湿度
            XSSFRow row35 = bodySheet.createRow(firstRow + 24 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row35, (firstRow + 24 + splicingPanels), 10);
            row35.getCell(1).setCellValue("工作环境湿度");
            row35.getCell(2).setCellValue(" 10％ ～ 90％");

            //第34行设置值，控制距离
            XSSFRow row37 = bodySheet.createRow(firstRow + 25 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row37, (firstRow + 25 + splicingPanels), 10);
            row37.getCell(1).setCellValue("控制距离");
            row37.getCell(2).setCellValue("CAT5 cable:＜100 m; Single mode fiber:＜10 km");
            //第34行设置值，操作系统平台
            XSSFRow row38 = bodySheet.createRow(firstRow + 26 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row38, (firstRow + 26 + splicingPanels), 10);
            row38.getCell(1).setCellValue("操作系统平台");
            row38.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");

            //第43行设置值，屏体价格
            XSSFRow row39 = bodySheet.createRow(firstRow + 27 + splicingPanels);
            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 27 + splicingPanels, 0, 6);
            row39.getCell(0).setCellValue("工程报价表");
            row39.getCell(0).setCellStyle(titleStyleMain(workbook));

            //第43行设置值，屏体价格
            XSSFRow row40 = bodySheet.createRow(firstRow + 28 + splicingPanels);
            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "等线", 9));
            //第43行设置值，屏体价格
            XSSFRow row41 = bodySheet.createRow(firstRow + 29 + splicingPanels);
            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            row41.getCell(5).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(5).setCellStyle(style);
            row41.getCell(6).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(6).setCellStyle(style);
            row41.getCell(0).setCellStyle(styleColor);
            //设定合并单元格的区域

            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 1, 1);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 2, 2);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 3, 4);
//            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 5, 5);
            row40.getCell(1).setCellValue("屏体部分");
            row40.getCell(2).setCellValue("规格或型号");
            row40.getCell(3).setCellValue("数量");
            row40.getCell(5).setCellValue("单价");
            row40.getCell(6).setCellValue("小计");

            row40.getCell(0).setCellStyle(styleColor);
            row40.getCell(1).setCellStyle(style);
            row40.getCell(2).setCellStyle(style);
            row40.getCell(3).setCellStyle(style);
            row40.getCell(5).setCellStyle(style);
            row40.getCell(6).setCellStyle(style);

            //第43行设置值，屏体价格
            XSSFRow row43 = bodySheet.createRow(firstRow + 30 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 30 + splicingPanels, 0, 1);
            row43.getCell(0).setCellValue("LED 显示屏");
            row43.getCell(2).setCellValue(offerPanelsList.get(i).getSeriesName());


            row43.getCell(3).setCellValue(String.valueOf(area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP)));

            row43.getCell(4).setCellValue("sqm");
            BigDecimal panelPrice = panelDetailsList.get(i).getOfferPanels().getPrice() == null ? new BigDecimal(0)
                    : panelDetailsList.get(i).getOfferPanels().getPrice();
            System.out.println("计算总价：" + area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(panelPrice));

            row43.getCell(5).setCellValue(String.valueOf(panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));

            row43.getCell(6).setCellFormula("D" + (firstRow + 31 + splicingPanels) + "*F" + (firstRow + 31 + splicingPanels));
            row43.getCell(6).setCellStyle(styleDataFormat(workbook));
            //第45行设置值，屏体价格
//            XSSFRow row44 = bodySheet.createRow(firstRow + 31 + splicingPanels);
//            //设置行高
//            row44.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "等线", 9));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
//            row44.getCell(0).setCellValue("接收卡");
//            row44.getCell(2).setCellValue("Nova A8S");
//            row44.getCell(3).setCellValue("1.00 ");
//            row44.getCell(4).setCellValue("pc");

            //第45行设置值，屏体价格
            XSSFRow row45 = bodySheet.createRow(firstRow + 31 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
            row45.getCell(0).setCellValue("LED播放软件");
            row45.getCell(2).setCellValue("NOVA STUDIO");
            row45.getCell(3).setCellValue("1");
            row45.getCell(4).setCellValue("pc");
            row45.getCell(5).setCellValue("免费");
            row45.getCell(6).setCellValue("免费");

            //
            XSSFRow row46_46 = bodySheet.createRow(firstRow + 32 + splicingPanels);
            //创建单元格
            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 32 + splicingPanels, 1, 6);
            cells46.get(1).setCellValue("外围设备");
            cells46.get(1).setCellStyle(style);
            cells46.get(0).setCellStyle(styleColor);

            //获取各种备件集合的长度
            int standardLength = 0;
            int selfStandardLength = 0;
            int spareLength = 0;
            int selfSpareLength = 0;
            int freeLength = 0;
            int selfFreeLength = 0;
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                standardLength = panelDetailsList.get(i).getStandardDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                selfStandardLength = panelDetailsList.get(i).getSelfStandardList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                spareLength = panelDetailsList.get(i).getSpareDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                selfSpareLength = panelDetailsList.get(i).getSelfSpareList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                freeLength = panelDetailsList.get(i).getFreeDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                selfFreeLength = panelDetailsList.get(i).getSelfFreeList().size();
            }
            int totalLength = standardLength + selfStandardLength + spareLength + selfSpareLength;
            int index = firstRow + 33 + splicingPanels;
            //标配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getStandardDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j, 0, 1);
                    cells.get(0)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getRealCount());
                    cells.get(4)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getStandardDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (index + j + 1) + "*F" + (index + j + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义标配备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfStandardList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfStandardList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + j + 1 + standardLength) + "*F" + (index + j + 1 + standardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            //选配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSpareDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength + selfStandardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength + selfStandardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSpareDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellFormula("D" + (index + j + 1 + standardLength + selfStandardLength) +
                            "*F" + (index + j + 1 + standardLength + selfStandardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义选配信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfSpareList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength - selfSpareLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfSpareList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellFormula(
                            "D" + (index + j + totalLength - selfSpareLength + 1) + "*F" + (
                                    index + j + totalLength - selfSpareLength + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

//            XSSFRow transferRowTitleFree = bodySheet.createRow(index + totalLength);
//            transferRowTitleFree.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells1_free = createRowAndCell(transferRowTitleFree, 7,
//                    bodyStyle(workbook, "等线", 9));
//            //设定合并单元格的区域
//            collapse(bodySheet, index + totalLength, 0, 6);
//
//            cells1_free.get(0).setCellValue("其它费用");
//            cells1_free.get(0).setCellStyle(titleStyleMain(workbook));

            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList()) || CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                XSSFRow transferRowTitleFree = bodySheet.createRow(index + totalLength);
                transferRowTitleFree.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_free = createRowAndCell(transferRowTitleFree, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLength, 1, 6);
                cells1_free.get(0).setCellStyle(styleColor);
                cells1_free.get(1).setCellValue("免费配件");
                cells1_free.get(1).setCellStyle(style);
            }

            //免费备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getFreeDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + 1);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength + 1, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }
            //自定义免费备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfFreeList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + freeLength + 1);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength + freeLength + 1, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }

            int totalLengthCount = totalLength + freeLength + selfFreeLength + 1;

            List<TransportPackage> transportPackageList = offerVo.getTransport();
            List<OfferServiceDto> serviceList = offerVo.getServiceListDto();

            boolean showPackageTitle = true;
            if(transportPackageList.size() == 0 && serviceList.size() == 0) {
                if (offerVo.getTransfer() != null) {
                    if (offerVo.getTransfer().getCost() == null || offerVo.getTransfer().getCost().intValue() == 0) {
                        showPackageTitle = false;
                    }
                }else {
                    showPackageTitle = false;
                }
            }

            if (showPackageTitle) {
                XSSFRow transferRowTitle = bodySheet.createRow(index + totalLengthCount);
                transferRowTitle.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_1 = createRowAndCell(transferRowTitle, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLengthCount, 1, 6);
                cells1_1.get(1).setCellValue("包装");
                cells1_1.get(1).setCellStyle(style);
                cells1_1.get(0).setCellStyle(styleColor);
                totalLengthCount++;
            }

            //服务列表长度
            int serviceLength = 0;
            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
                serviceLength = offerVo.getServiceListDto().size();
                //服务费用
                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLengthCount);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLengthCount, 0, 1);
                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
                                    .getServiceDiscount().doubleValue() / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + 1 + j + totalLengthCount) + "*F" + (index + 1 + j + totalLengthCount));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            int totalService = index + totalLengthCount + serviceLength;

            if (transportPackageList.size() > 0) {
                //运输费用
                for (int j = 0; j < transportPackageList.size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(j + totalService);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, j + totalService, 0, 1);
                    cells.get(0).setCellValue(transportPackageList.get(j).getPackages());
                    cells.get(3).setCellValue(transportPackageList.get(j).getNumber());
                    cells.get(4).setCellValue("");
                    cells.get(5).setCellValue(String.valueOf(transportPackageList.get(j).getPriceUnit()));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (j + totalService + 1) + "*F" + (j + totalService + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            if (offerVo.getTransfer() != null) {
                if (offerVo.getTransfer().getCost() != null && offerVo.getTransfer().getCost().intValue() != 0) {
                    //创建行
                    XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size());
                    //设置行高
                    autoRowTransfer.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, totalService + transportPackageList.size(), 0, 1);
                    cells.get(0).setCellValue("发货日期");
                    cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "0" : String.valueOf(offerVo.getTransfer().getCost().setScale(2, BigDecimal.ROUND_HALF_UP)));
                    cells.get(3).setCellStyle(styleDataFormat(workbook));
                    cells.get(4).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(4).setCellValue("");
                    cells.get(5).setCellValue(1);
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 1) + "*F" + (totalService + transportPackageList.size() + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));

                    totalService = totalService + 1;
                }
            }

            XSSFRow transferRow6 = bodySheet.createRow(totalService + transportPackageList.size());
            transferRow6.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells6_1 = createRowAndCell(transferRow6, 7, bodyStyle(workbook, "等线", 9));
            transferRow6.setHeightInPoints(20);
            collapse(bodySheet, totalService + transportPackageList.size(), 0, 4);
            collapse(bodySheet, totalService + transportPackageList.size(), 5, 6);
            XSSFRichTextString val6 = new XSSFRichTextString(
                    "(" + (StringUtils.isEmpty(offerVo.getTransfer().getTrade()) == true ? "" : offerVo.getTransfer().getTrade()) + "  深圳  " + myOffer.getMoneyUnit() + ") 总计:");
            transferRow6.getCell(0).setCellValue(val6);
           /* transferRow6.getCell(5).setCellFormula(
                    "SUM(G" + (42 + splicingPanels) + ":G" + (totalService + transportPackageList.size() + 1) + ")");*/
            transferRow6.getCell(5).setCellValue(offerVo.offer.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
            transferRow6.getCell(0).setCellStyle(style);
            transferRow6.getCell(5).setCellStyle(totaltyle(workbook, "right"));

            int totalSize = totalService + transportPackageList.size();

            // 样式对象 
            XSSFCellStyle style2 = workbook.createCellStyle();
            //设置垂直居中
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            Font font = workbook.createFont();
            font.setFontName("等线");
            font.setFontHeightInPoints((short) 10);
            style2.setFont(font);


            int remarkIndex = 1;

            if(offerVo.getOffer().getRemark() != null) {
                XSSFRow transferRow6_r = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_r.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_r, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex, 0, 6);
                transferRow6_r.getCell(0).setCellValue(offerVo.getOffer().getRemark());
                transferRow6_r.getCell(0).setCellStyle(style2);

                remarkIndex++;
            }

            //优惠价格
            if (offerVo.getOffer().getSpecialPrice() != null) {
                XSSFRow transferRow6_1 = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_1.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_1, 7, bodyStyle(workbook, "等线", 9));
                transferRow6_1.setHeightInPoints(20);
                collapse(bodySheet, totalSize + remarkIndex, 0, 4);
                collapse(bodySheet, totalSize + remarkIndex, 5, 6);
                
                String remark="优惠价格";
                if(StringUtils.isNotEmpty(offerVo.getOffer().getSpecialPriceRemark())) {
                	remark=offerVo.getOffer().getSpecialPriceRemark();
                }
                transferRow6_1.getCell(0).setCellValue(remark);
                transferRow6_1.getCell(0).setCellStyle(totaltyle(workbook, "left"));
                transferRow6_1.getCell(5).setCellValue(offerVo.getOffer().getSpecialPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
                transferRow6_1.getCell(5).setCellStyle(totaltyle(workbook, "right"));
                XSSFRow transferRow6_2 = bodySheet.createRow(totalSize + remarkIndex + 1);
                transferRow6_2.setHeightInPoints(20);
                /*//创建单元格
                createRowAndCell(transferRow6_2, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex + 1, 0, 6);
                transferRow6_2.getCell(0).setCellValue(offerVo.getOffer().getSpecialPriceRemark());
                transferRow6_2.getCell(0).setCellStyle(style2);*/
            }

            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 6);
            transferRow7.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells7_1 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "等线", 9));
            transferRow7.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 4, 0, 6);


            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
            transferRow8.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells8_1 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "等线", 9));
            transferRow8.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 5, 0, 6);
            XSSFRichTextString val8 = new XSSFRichTextString("备注:");
            transferRow8.getCell(0).setCellValue(val8);
            transferRow8.getCell(0).setCellStyle(style2);

            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
            transferRow9.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "等线", 9));
            transferRow9.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 6, 0, 6);
            XSSFRichTextString val9 = new XSSFRichTextString("1.报价有效期：此报价30天内有效");
            transferRow9.getCell(0).setCellValue(val9);
            transferRow9.getCell(0).setCellStyle(style2);

            XSSFRow transferRow10 = bodySheet.createRow(totalSize + 7);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells10 = createRowAndCell(transferRow10, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 7, 0, 6);
            StringBuffer packageStr = new StringBuffer();
            transportPackageList.forEach(item -> {
                packageStr.append(item.getPackages()).append(",");
            });
            transferRow10.getCell(0).setCellValue("2.包装方式：" + (transportPackageList.size() > 0 ? packageStr : ""));
            transferRow10.getCell(0).setCellStyle(style2);

            XSSFRow transferRow11 = bodySheet.createRow(totalSize + 8);
            transferRow11.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells11 = createRowAndCell(transferRow11, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 8, 0, 6);
            cells11.get(0).setCellValue("3.付款方式: " + (myOffer.getPayment() == null ? "" : myOffer.getPayment()));
            cells11.get(0).setCellStyle(style2);

            XSSFRow transferRow12 = bodySheet.createRow(totalSize + 9);
            transferRow12.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells12 = createRowAndCell(transferRow12, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 9, 0, 6);
            transferRow12.getCell(0).setCellValue("4.交货周期：收到预付款" + myOffer.getWaitingDate() + "个自然日（不包含中国法定节假日）");
            transferRow12.getCell(0).setCellStyle(style2);

            XSSFRow transferRow13 = bodySheet.createRow(totalSize + 10);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells13 = createRowAndCell(transferRow13, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 10, 0, 6);
            transferRow13.getCell(0).setCellValue("5.质保期限：发货之日起，屏体质保两年，备件质保一年");
            transferRow13.getCell(0).setCellStyle(style2);

            XSSFRow transferRow14 = bodySheet.createRow(totalSize + 11);
            transferRow14.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells14 = createRowAndCell(transferRow14, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 11, 0, 6);
            cells14.get(0).setCellValue(
                    "6.培训服务：免费提供惠州工厂/德国公司7天的在线培训");
            cells14.get(0).setCellStyle(style2);

            XSSFRow transferRow15 = bodySheet.createRow(totalSize + 12);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15R = createRowAndCell(transferRow15, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 12, 0, 6);
            cells15R.get(0).setCellValue(
                    "7.安装指导和售后维保服务可选");
            cells15R.get(0).setCellStyle(style2);

            //重新计算excel里面的公式
            reCalculating(workbook);
        }

    }

    /**
     * 20180919 新增版本
     * 其他区域模板读写方法
     */
    private void templateEUPdfNew2(List<PanelDetails> panelDetailsList, List<XSSFWorkbook> workbooks,
                                   MyOfferDto myOffer, List<String> msg, OfferVo offerVo, List<OfferPanelsDto> offerPanelsList,
                                   String filepath, User userMsg) {


        for (int i = 0; i < workbooks.size(); i++) {
            XSSFWorkbook workbook = workbooks.get(i);
            // 样式对象 
            XSSFCellStyle styleColor = workbook.createCellStyle();
            //单元格背景色
            styleColor.setFillForegroundColor(new XSSFColor(new java.awt.Color(245, 131, 32)));
            styleColor.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            //总重量
            BigDecimal weight = new BigDecimal(0);
            //总面积
            BigDecimal area = new BigDecimal(0);
            // 箱体 宽数
            int wNum = 0;
            // 箱体 高数
            int lNum = 0;
            //平均功耗
            BigDecimal avgPower = new BigDecimal(0);

            BigDecimal maxPower = new BigDecimal(0);
            //读取sheet
            XSSFSheet bodySheet = workbook.getSheetAt(0);


            //生成单元格样式
            XSSFCellStyle style = titleStyle(workbook);


            XSSFCellStyle styleRise = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise = workbook.createFont();
            fontRise.setFontName("等线");
            fontRise.setFontHeightInPoints((short) 12);
            fontRise.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleRise.setFont(fontRise);
            //设置垂直居中
            styleRise.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRise.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);

            XSSFRow row1 = bodySheet.createRow(1);
            createRowAndCell(row1, 7, bodyStyle(workbook, "等线", 9));

            //设置行高
            row1.setHeightInPoints(20);
            row1.getCell(0).setCellValue(
                    "Quotation of  Absen-" + offerPanelsList.get(i).getSeriesName() + " LED Display");
            row1.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row2 = bodySheet.createRow(2);
            //设置行高
            row2.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row2, 7, bodyStyle(workbook, "等线", 9));
            XSSFCellStyle styleRise1 = workbook.createCellStyle();
            //创建字体 等线
            XSSFFont fontRise1 = workbook.createFont();
            fontRise1.setFontName("等线");
            fontRise1.setFontHeightInPoints((short) 12);
            styleRise1.setFont(fontRise1);
            //设置垂直居中
            styleRise1.setVerticalAlignment(VerticalAlignment.CENTER);
//            styleRise1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            row2.getCell(0).setCellValue("Quotation Number:" + offerVo.getOffer().getNum());
            row2.getCell(0).setCellStyle(styleRise1);


            int first = 4;
            XSSFRow row5_4 = bodySheet.createRow(first);
            //设置行高
            row5_4.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_4, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first, 0, 6);
            row5_4.getCell(0).setCellValue("Screen Configuation");
            row5_4.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row5_5 = bodySheet.createRow(first + 1);
            //设置行高
            row5_5.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_5, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, first + 1, 1, 6);

            row5_5.getCell(0).setCellStyle(styleColor);
            row5_5.getCell(1).setCellValue("Module");
            row5_5.getCell(1).setCellStyle(style);

            int firstRow = first + 2;

            XSSFRow row6 = bodySheet.createRow(firstRow);
            //设置行高
            row6.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row6, firstRow);
            row6.getCell(0).setCellValue("Dimensions (w x h) (mm)");

            //尺寸信息
            if (panelDetailsList.get(i).getModule().getWidth() != null
                    && panelDetailsList.get(i).getModule().getHeight() != null) {
                row6.getCell(2).setCellValue(
                        panelDetailsList.get(i).getModule().getWidth().intValue() + " X " + panelDetailsList
                                .get(i).getModule().getHeight().intValue());
            }

            row6.getCell(3).setCellValue("Resolution (w x h)");
            //分辨率
            if (panelDetailsList.get(i).getModule().getTransverse() != null
                    && panelDetailsList.get(i).getModule().getPortrait() != null) {
                row6.getCell(5).setCellValue(
                        panelDetailsList.get(i).getModule().getTransverse().intValue() + " X "
                                + panelDetailsList.get(i).getModule().getPortrait().intValue());
            }

            //第8行设置值，模组的点间距 和 像素点（点数/㎡）
            XSSFRow row7 = bodySheet.createRow(firstRow + 1);
            //设置行高
            row7.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row7, (firstRow + 1));
            row7.getCell(0).setCellValue("Pixel pitch (mm)");
            //模组的点间距
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getPitch().doubleValue());
            }
            row7.getCell(3).setCellValue("Pixel Density (pixels/m2)");
            //像素点（点数/㎡）
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
                row7.getCell(5)
                        .setCellValue(new BigDecimal(1000000).divide(panelDetailsList.get(i).getModule().getPitch().multiply(panelDetailsList.get(i).getModule().getPitch()),0,BigDecimal.ROUND_HALF_UP).toString());
            }

            //第9行设置值，箱体的配置信息
            XSSFRow row8 = bodySheet.createRow(firstRow + 2);
            //设置行高
            row8.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row8, (firstRow + 2));
            row8.getCell(0).setCellValue("LED Lamp");
            row8.getCell(3).setCellValue("Pixel Configuration");
            row8.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());

            List<Box> boxList = Lists.newArrayList();
            if (panelDetailsList.get(i).getSplicingPanelsDto().size() > 0) {
                for (OfferPanelsDto offerPanels : panelDetailsList.get(i).getSplicingPanelsDto()) {
                    boxList.add(offerPanels.getBox());
                }
            }

            //拼屏数量
            int splicingBoxs = boxList.size();

            for (int j = 0; j < splicingBoxs; j++) {
                //第10行设置值，箱体的尺寸 模组个数
                XSSFRow row9 = bodySheet.createRow(firstRow + 3 + 6 * j);
                //设置行高
                row9.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(row9, 7, bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, firstRow + 3 + 6 * j, 1, 6);
                if (splicingBoxs == 1) {
                    row9.getCell(1).setCellValue("Standard Panel");
                } else {
                    row9.getCell(1).setCellValue("Standard Panel" + (j + 1));
                }
                row9.getCell(0).setCellStyle(styleColor);
                row9.getCell(1).setCellStyle(style);

                //第11行设置值，箱体的尺寸 模组个数
                XSSFRow row10 = bodySheet.createRow(firstRow + 4 + 6 * j);
                setRowAndCell(workbook, bodySheet, row10, (firstRow + 4 + 6 * j));
                row10.getCell(0).setCellValue("Dimensions(w x h x d)(mm)");
                row10.getCell(3).setCellValue("Module Quantity ");
                //箱体的尺寸
                if (boxList.get(j).getWidth() != null && boxList.get(j).getHeight() != null
                        && boxList.get(j).getThickness() != null) {
                    row10.getCell(2).setCellValue(
                            boxList.get(j).getWidth().intValue() + " x " + boxList.get(j).getHeight().intValue()
                                    + " x " + boxList.get(j).getThickness().intValue());
                }
                //模组个数
                if (boxList.get(j).getWidth() != null) {
                    row10.getCell(5).setCellValue(
                            boxList.get(j).getTransverseCount().intValue() * boxList.get(j).getPortraitCount()
                                    .intValue());
                }

                //第12行设置值，分辨率 (w x h)	 实像素点数
                XSSFRow row11 = bodySheet.createRow(firstRow + 5 + 6 * j);
                setRowAndCell(workbook, bodySheet, row11, (firstRow + 5 + 6 * j));
                row11.getCell(0).setCellValue("Physical Resolution (w x h)");
                row11.getCell(3).setCellValue("Physical Pixels (total)");
                //分辨率
                if (boxList.get(j).getTransversePix() != null && boxList.get(j).getPortraitPix() != null) {
                    row11.getCell(2).setCellValue(
                            boxList.get(j).getTransversePix().intValue() + " x " + boxList.get(j).getPortraitPix()
                                    .intValue());
                }
                //实像素点数
                if (boxList.get(j).getWidth() != null) {
                    row11.getCell(5).setCellValue(
                            boxList.get(j).getTransversePix().intValue() * boxList.get(j).getPortraitPix()
                                    .intValue());
                }

                //第13行设置值，箱体的重量 材质
                XSSFRow row12 = bodySheet.createRow(firstRow + 6 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12, (firstRow + 6 + 6 * j));
                row12.getCell(0).setCellValue("Weight/Panel (kg)");
                row12.getCell(3).setCellValue("Material");
                //箱体的重量
                if (boxList.get(j).getWeight() != null) {
                    row12.getCell(2).setCellValue(boxList.get(j).getWeight().doubleValue());
                }
                //材质
                if (boxList.get(j).getBoxType() != null) {
                	row12.getCell(5).setCellStyle(bodyStyle(workbook, "等线", 9));
                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
                }

                //第13行设置值，
                XSSFRow row12_1 = bodySheet.createRow(firstRow + 7 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12_1, (firstRow + 7 + 6 * j));
                row12_1.getCell(0).setCellValue("Certificate");

                //Certificate
                row12_1.getCell(2).setCellValue("CE");
                row12_1.getCell(3).setCellValue("Mechanics");
                //Mechanics
                row12_1.getCell(5).setCellValue("Rental, Rear maintenance");

                //第14行设置值， 单个屏的功率  标准平均功率（Kw ） 标准最大功率（Kw）
                //标准平均功率
                XSSFRow row13 = bodySheet.createRow(firstRow + 8 + 6 * j);
                setRowAndCell(workbook, bodySheet, row13, (firstRow + 8 + 6 * j));
                row13.getCell(0).setCellValue("Average Power/sqm(watts)");
                row13.getCell(3).setCellValue("Max Power/sqm(watts)");
                if (panelDetailsList.get(i).getParams().getPowerAvg() != null) {
                    row13.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getPowerAvg());
                }
                //标准最大功率
                if (panelDetailsList.get(i).getParams().getPowerMax() != null) {
                    row13.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getPowerMax());
                }
            }

            //拼接箱体 占用行数
            int panelNum = splicingBoxs * 6;

            XSSFRow row15 = bodySheet.createRow(firstRow + 3 + panelNum);
            //设置行高
            row15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 3 + panelNum, 0, 6);
            row15.getCell(0).setCellValue("Display Data");
            row15.getCell(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow row16 = bodySheet.createRow(firstRow + 4 + panelNum);
            //设置行高
            row16.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "等线", 9));

            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 4 + panelNum, 1, 1);
            collapse(bodySheet, firstRow + 4 + panelNum, 3, 4);
            collapse(bodySheet, firstRow + 4 + panelNum, 5, 6);
            row16.getCell(1).setCellValue("ITEM");
            row16.getCell(2).setCellValue("Width ");
            row16.getCell(3).setCellValue("Height");
            row16.getCell(5).setCellValue("Total");

            row16.getCell(0).setCellStyle(styleColor);
            row16.getCell(1).setCellStyle(style);
            row16.getCell(2).setCellStyle(style);
            row16.getCell(3).setCellStyle(style);
            row16.getCell(5).setCellStyle(style);

            int splicingPanelsSize = panelDetailsList.get(i).getSplicingPanelsDto().size();

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第17行设置值，屏体数量
                XSSFRow row17_panel = bodySheet.createRow(firstRow + 5 + panelNum + j);
                setRowAndCell3(workbook, bodySheet, row17_panel, (firstRow + 5 + panelNum + j));
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);
                row17_panel.getCell(0).setCellValue("Panel Quantity" + str + "(pcs)");
                row17_panel.getCell(0).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(2).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
                row17_panel.getCell(2).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(3).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
                row17_panel.getCell(3).setCellStyle(styleDataZeroBold(workbook));
                row17_panel.getCell(5)
                        .setCellFormula("C" + (firstRow + 6 + panelNum + j) + "*D" + (firstRow + 6 + panelNum + j));
                row17_panel.getCell(5)
                        .setCellStyle(styleDataZeroBold(workbook));

                weight = weight.add(new BigDecimal(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
                                .get(i).getSplicingPanelsDto().get(j).getLcount())
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
            }
            int boxNum = panelNum + splicingPanelsSize;

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第18行设置 显示分辨率 (点)
                String str = splicingPanelsSize == 1 ? "" : "" + (j + 1);
                XSSFRow row18_new = bodySheet.createRow(firstRow + 5 + boxNum + j);
                setRowAndCell3(workbook, bodySheet, row18_new, (firstRow + 5 + boxNum + j));
                if (offerVo.getOffer().getSizeUnit() == 2) {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(ft)");
                } else {
                    row18_new.getCell(0).setCellValue(msg.get(1) + str + "(sqm)");
                }
                row18_new.getCell(2).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());
                row18_new.getCell(2).setCellStyle(styleDataFormat(workbook));
                row18_new.getCell(3).setCellValue(
                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal().setScale(2, BigDecimal.ROUND_HALF_UP)
                                : new BigDecimal(0)).toString());
                row18_new.getCell(3).setCellStyle(styleDataFormat(workbook));
                //计算面积
                if (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
                        && panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null) {
                    row18_new.getCell(5).setCellFormula("C" + (firstRow + 6 + boxNum + j) + "*D" + (firstRow + 6 + boxNum + j));
                    row18_new.getCell(5).setCellStyle(styleDataFormat(workbook));
                    area = area.add(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                            .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()));

                }

                avgPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerAvg()))
                        .add(avgPower);
                maxPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
                        .multiply(new BigDecimal(
                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerMax()))
                        .add(maxPower);
            }
            int splicingPanels = boxNum + splicingPanelsSize - 2;
            //第19行设置值，
            XSSFRow row19 = bodySheet.createRow(firstRow + 7 + splicingPanels);
            setRowAndCell3(workbook, bodySheet, row19, (firstRow + 7 + splicingPanels));
            row19.getCell(0).setCellValue("Display Resolution(dots)");
            row19.getCell(2)
                    .setCellValue(panelDetailsList.get(i).getModule().getTransverse().intValue() * wNum);
            row19.getCell(2)
                    .setCellStyle(styleDataFormatZero(workbook));
            row19.getCell(3)
                    .setCellValue(panelDetailsList.get(i).getModule().getPortrait().intValue() * lNum);
            row19.getCell(3)
                    .setCellStyle(styleDataFormatZero(workbook));
            row19.getCell(5).setCellFormula("C" + (firstRow + 8 + splicingPanels) + "*D" + (firstRow + 8 + splicingPanels));
            row19.getCell(5).setCellStyle(styleDataFormatZero(workbook));

            //第20行设置值，显示屏重量(kg)
            XSSFRow row20 = bodySheet.createRow(firstRow + 8 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 8 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 8 + splicingPanels, 2, 6);
            row20.getCell(0).setCellValue("Total Net Weight (kgs)");
            row20.getCell(2).setCellValue(weight.toString());
            row20.getCell(2).setCellStyle(styleDataFormat(workbook));

            //第21行设置值，标准平均功率（Kw ）	 标准最大功率（Kw）
            XSSFRow row21 = bodySheet.createRow(firstRow + 9 + splicingPanels);
            //设置行高
            row20.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 9 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 3, 4);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 5, 6);
            //标准平均功率（Kw ）
            row21.getCell(0).setCellValue("Total Average Power(watts)");
            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            row21.getCell(2).setCellStyle(styleDataFormat(workbook));
            row21.getCell(5).setCellStyle(styleDataFormat(workbook));
            //标准最大功率（Kw）
            row21.getCell(3).setCellValue("Total Max Power(watts)");
            row21.getCell(5).setCellValue(maxPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());


            //第22行设置值，亮度
            XSSFRow row22_21 = bodySheet.createRow(firstRow + 10 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22_21, (firstRow + 10 + splicingPanels), 12);
            
            row22_21.getCell(0).setCellStyle(styleColor);
            row22_21.getCell(1).setCellValue("Parameter");
            row22_21.getCell(2).setCellValue("Value");
            row22_21.getCell(1).setCellStyle(style);
            row22_21.getCell(2).setCellStyle(style);
            //参数修改1
            row22_21.getCell(3).setCellValue("Parameter");
            row22_21.getCell(5).setCellValue("Value");
            row22_21.getCell(3).setCellStyle(style);
            row22_21.getCell(5).setCellStyle(style);

            //第22行设置值，亮度,视角
            XSSFRow row22 = bodySheet.createRow(firstRow + 11 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row22, (firstRow + 11 + splicingPanels), 10);
            row22.getCell(1).setCellValue("Brightness ");
            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
                    panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");
            row22.getCell(3).setCellValue("Viewing Angle");
            row22.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getViewing());

            XSSFRow row23 = bodySheet.createRow(firstRow + 12 + splicingPanels);
            XSSFRow row24 = bodySheet.createRow(firstRow + 13 + splicingPanels);
            XSSFRow row25 = bodySheet.createRow(firstRow + 14 + splicingPanels);
            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
            XSSFRow row29 = bodySheet.createRow(firstRow + 18 + splicingPanels);
            XSSFRow row30 = bodySheet.createRow(firstRow + 19 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row23, (firstRow + 12 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row24, (firstRow + 13 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row25, (firstRow + 14 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 9);
            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 9);
            setRowAndCell2(workbook, bodySheet, row29, (firstRow + 18 + splicingPanels), 9);
            setRowAndCell2(workbook, bodySheet, row30, (firstRow + 19 + splicingPanels), 9);
            
            //第23行设置值:最小可视距离,灰度
            row23.getCell(1).setCellValue("Minimum Viewing Distance");
            if (panelDetailsList.get(i).getModule().getPitch() != null) {
            	row23.getCell(2)
                        .setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
            }
            row23.getCell(3).setCellValue("Gray scale");
            row23.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());

            //第24行设置值:色温,刷新率
            row24.getCell(1).setCellValue("color temperature");
            row24.getCell(2).setCellValue("6500K");
            row24.getCell(3).setCellValue("Refresh rate");
            row24.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");

            //第25行设置值,对比度,工作赫兹
            row25.getCell(1).setCellValue("Contrast ratio");
            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());
            row25.getCell(3).setCellValue("Input power frequency");
            row25.getCell(5).setCellValue("50 or 60 Hertz");

            //第26行设置值:工作电压,盲点率
            row26.getCell(1).setCellValue("Input Voltage");
            row26.getCell(2).setCellValue("110~240 Volt");
            row26.getCell(3).setCellValue("PPM");
            row26.getCell(5).setCellValue("＜1/10000");

            //第27行设置值:使用寿命（50%亮度）,防护等级
            row27.getCell(1).setCellValue("Lifetime");
            row27.getCell(2).setCellValue("100000 hours");
            row27.getCell(3).setCellValue("IP RATING");
            row27.getCell(5).setCellValue("Front IP65,  Rear IP54");
            
            //第28行设置值:工作环境温度,工作环境湿度
            row28.getCell(1).setCellValue("Operating temperature");
            row28.getCell(2).setCellValue("  ﹣10 ～﹢40 ℃");
            row28.getCell(3).setCellValue("Operating humidity");
            row28.getCell(5).setCellValue(" 10％ ～ 90％");

            //第34行设置值:控制距离,操作系统平台
            row29.getCell(1).setCellValue("Control distance");
            row29.getCell(2).setCellValue("CAT5 cable:＜100 m; Single mode fiber:＜10 km");
            row30.getCell(1).setCellValue("Signal input format");
            row30.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");

            //将firstRow减7;
            firstRow=firstRow-7;

            //第43行设置值，屏体价格
            XSSFRow row39 = bodySheet.createRow(firstRow + 27 + splicingPanels);
            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 27 + splicingPanels, 0, 6);
            row39.getCell(0).setCellValue("QUOTATION");
            row39.getCell(0).setCellStyle(titleStyleMain(workbook));

            //第43行设置值，屏体价格
            XSSFRow row40 = bodySheet.createRow(firstRow + 28 + splicingPanels);
            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "等线", 9));
            //第43行设置值，屏体价格
            XSSFRow row41 = bodySheet.createRow(firstRow + 29 + splicingPanels);
            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            row41.getCell(5).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(5).setCellStyle(style);
            row41.getCell(6).setCellValue("(" + myOffer.getMoneyUnit() + ")");
            row41.getCell(6).setCellStyle(style);
            row41.getCell(0).setCellStyle(styleColor);
            //设定合并单元格的区域

            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 1, 1);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 2, 2);
            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 3, 4);
//            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 5, 5);
            row40.getCell(0).setCellStyle(styleColor);
            row40.getCell(1).setCellValue("Item No.");
            row40.getCell(2).setCellValue("Model No.");
            row40.getCell(3).setCellValue("Qty.");
            row40.getCell(5).setCellValue("Unit Price");
            row40.getCell(6).setCellValue("Sub Total");

            row40.getCell(1).setCellStyle(style);
            row40.getCell(2).setCellStyle(style);
            row40.getCell(3).setCellStyle(style);
            row40.getCell(5).setCellStyle(style);
            row40.getCell(6).setCellStyle(style);

            //第43行设置值，屏体价格
            XSSFRow row43 = bodySheet.createRow(firstRow + 30 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 30 + splicingPanels, 0, 1);
            row43.getCell(0).setCellValue("LED screen");
            row43.getCell(2).setCellValue(offerPanelsList.get(i).getSeriesName());


            row43.getCell(3).setCellValue(String.valueOf(area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP)));
            row43.getCell(3).setCellStyle(styleDataFormat(workbook));
            row43.getCell(4).setCellValue("sqm");
            BigDecimal panelPrice = panelDetailsList.get(i).getOfferPanels().getPrice() == null ? new BigDecimal(0)
                    : panelDetailsList.get(i).getOfferPanels().getPrice();
//            System.out.println("计算总价：" + area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(panelPrice));

            row43.getCell(5).setCellValue(String.valueOf(panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
            row43.getCell(5).setCellStyle(styleDataFormat(workbook));
            row43.getCell(6).setCellFormula("D" + (firstRow + 31 + splicingPanels) + "*F" + (firstRow + 31 + splicingPanels));
            row43.getCell(6).setCellStyle(styleDataFormat(workbook));
            //第45行设置值，屏体价格
//            XSSFRow row44 = bodySheet.createRow(firstRow + 31 + splicingPanels);
//            //设置行高
//            row44.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "等线", 9));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
//            row44.getCell(0).setCellValue("Receiving Card");
//            row44.getCell(2).setCellValue("Nova A8S");
//            row44.getCell(3).setCellValue("1.00 ");
//            row44.getCell(4).setCellValue("pc");

            //第45行设置值，屏体价格
            XSSFRow row45 = bodySheet.createRow(firstRow + 31 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
            row45.getCell(0).setCellValue("LED Software");
            row45.getCell(2).setCellValue("NOVA STUDIO");
            row45.getCell(3).setCellValue("1");
            row45.getCell(4).setCellValue("pc");
            row45.getCell(5).setCellValue("Free");
            row45.getCell(6).setCellValue("Free");

            //
            XSSFRow row46_46 = bodySheet.createRow(firstRow + 33 + splicingPanels);
            //创建单元格
            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "等线", 9));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 33 + splicingPanels, 1, 6);
            cells46.get(0).setCellStyle(styleColor);
            cells46.get(1).setCellValue("Recommended spare parts");
            cells46.get(1).setCellStyle(style);

            //获取各种备件集合的长度
            int standardLength = 0;
            int selfStandardLength = 0;
            int spareLength = 0;
            int selfSpareLength = 0;
            int freeLength = 0;
            int selfFreeLength = 0;
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                standardLength = panelDetailsList.get(i).getStandardDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                selfStandardLength = panelDetailsList.get(i).getSelfStandardList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                spareLength = panelDetailsList.get(i).getSpareDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                selfSpareLength = panelDetailsList.get(i).getSelfSpareList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                freeLength = panelDetailsList.get(i).getFreeDetailList().size();
            }
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                selfFreeLength = panelDetailsList.get(i).getSelfFreeList().size();
            }
            int totalLength = standardLength + selfStandardLength + spareLength + selfSpareLength;
            int index = firstRow + 34 + splicingPanels;
            //标配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getStandardDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j, 0, 1);
                    cells.get(0)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getRealCount());
                    cells.get(4)
                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getUnit());
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getStandardDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (index + j + 1) + "*F" + (index + j + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义标配备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfStandardList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfStandardList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + j + 1 + standardLength) + "*F" + (index + j + 1 + standardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            //选配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSpareDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength + selfStandardLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength + selfStandardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSpareDetailList().get(j).getRealPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellFormula("D" + (index + j + 1 + standardLength + selfStandardLength) +
                            "*F" + (index + j + 1 + standardLength + selfStandardLength));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            //自定义选配信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfSpareList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength - selfSpareLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(format(
                            panelDetailsList.get(i).getSelfSpareList().get(j).getPrice().doubleValue()
                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
                                    / 100));
                    cells.get(6).setCellFormula(
                            "D" + (index + j + totalLength - selfSpareLength + 1) + "*F" + (
                                    index + j + totalLength - selfSpareLength + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList()) || CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                XSSFRow transferRowTitleFree = bodySheet.createRow(index + totalLength);
                transferRowTitleFree.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_free = createRowAndCell(transferRowTitleFree, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLength, 1, 6);
                cells1_free.get(0).setCellStyle(styleColor);
                cells1_free.get(1).setCellValue("Spare parts--free");
                cells1_free.get(1).setCellStyle(style);
                index = index + 1;
            }

            //免费备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {

                for (int j = 0; j < panelDetailsList.get(i).getFreeDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getType());
                    cells.get(2).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getModel());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getRealCount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getUnit());
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }
            //自定义免费备件信息插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
                for (int j = 0; j < panelDetailsList.get(i).getSelfFreeList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + freeLength);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength + freeLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getBrand());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
                    cells.get(4).setCellValue("PC");
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }

            int totalLengthCount = totalLength + freeLength + selfFreeLength;

            List<TransportPackage> transportPackageList = offerVo.getTransport();
            List<OfferServiceDto> serviceList = offerVo.getServiceListDto();

            boolean showPackageTitle = true;
            if(transportPackageList.size() == 0 && serviceList.size() == 0) {
                if (offerVo.getTransfer() != null) {
                    if (offerVo.getTransfer().getCost() == null || offerVo.getTransfer().getCost().intValue() == 0) {
                        showPackageTitle = false;
                    }
                }else {
                    showPackageTitle = false;
                }
            }
            if (showPackageTitle) {

                XSSFRow transferRowTitle = bodySheet.createRow(index + totalLengthCount);
                transferRowTitle.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells1_1 = createRowAndCell(transferRowTitle, 7,
                        bodyStyle(workbook, "等线", 9));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLengthCount, 1, 6);
                cells1_1.get(0).setCellStyle(styleColor);
                cells1_1.get(1).setCellValue("Package");
                cells1_1.get(1).setCellStyle(style);
                index = index + 1;
            }

            //服务列表长度
            int serviceLength = 0;
            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
                serviceLength = offerVo.getServiceListDto().size();
                //服务费用
                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLengthCount);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLengthCount, 0, 1);
                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
                                    .getServiceDiscount().doubleValue() / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + 1 + j + totalLengthCount) + "*F" + (index + 1 + j + totalLengthCount));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            int totalService = index + totalLengthCount + serviceLength;

            if (transportPackageList.size() > 0) {
                //运输费用
                for (int j = 0; j < transportPackageList.size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(j + totalService);
                    //设置行高
//                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, j + totalService, 0, 1);
                    cells.get(0).setCellValue(transportPackageList.get(j).getPackages());
                    cells.get(3).setCellValue(transportPackageList.get(j).getNumber());
                    cells.get(4).setCellValue("");
                    cells.get(5).setCellValue(String.valueOf(transportPackageList.get(j).getPriceUnit().setScale(2, BigDecimal.ROUND_HALF_UP)));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (j + 1 + totalService) + "*F" + (j + 1 + totalService));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }
            if (offerVo.getTransfer() != null) {
                if (offerVo.getTransfer().getCost() != null && offerVo.getTransfer().getCost().intValue() != 0) {
                    //创建行
                    XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size());
                    //设置行高
                    autoRowTransfer.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "等线", 9));
                    //设定合并单元格的区域
                    collapse(bodySheet, totalService + transportPackageList.size(), 0, 1);
                    cells.get(0).setCellValue("EST.Shipping");
                    cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "0" : String.valueOf(offerVo.getTransfer().getCost().setScale(2, BigDecimal.ROUND_HALF_UP)));
                    cells.get(3).setCellStyle(styleDataFormat(workbook));
                    cells.get(4).setCellValue("0");
                    cells.get(4).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(5).setCellValue(1);
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 1) + "*F" + (totalService + transportPackageList.size() + 1));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                    totalService = totalService + 1;
                }
            }

            XSSFRow transferRow6 = bodySheet.createRow(totalService + transportPackageList.size());
            transferRow6.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells6_1 = createRowAndCell(transferRow6, 7, bodyStyle(workbook, "等线", 9));
            transferRow6.setHeightInPoints(20);
            collapse(bodySheet, totalService + transportPackageList.size(), 0, 4);
            collapse(bodySheet, totalService + transportPackageList.size(), 5, 6);
            XSSFRichTextString val6 = new XSSFRichTextString(
                    "(" + (StringUtils.isEmpty(offerVo.getTransfer().getTrade()) == true ? "" : offerVo.getTransfer().getTrade()) + "  Shenzhen  " + myOffer.getMoneyUnit() + ") Total:");
            transferRow6.getCell(0).setCellValue(val6); 
            /*transferRow6.getCell(5).setCellFormula(
                    "SUM(G" + (20 + splicingPanels) + ":G" + (totalService + transportPackageList.size() + 1) + ")");*/
            transferRow6.getCell(5).setCellValue(offerVo.offer.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
            transferRow6.getCell(0).setCellStyle(style);
            transferRow6.getCell(5).setCellStyle(totaltyle(workbook, "right"));


            int totalSize = totalService + transportPackageList.size();

            // 样式对象 
            XSSFCellStyle style2 = workbook.createCellStyle();
            //设置垂直居中
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            Font font = workbook.createFont();
            font.setFontName("等线");
            font.setFontHeightInPoints((short) 10);
            style2.setFont(font);


            int remarkIndex = 1;

            if(offerVo.getOffer().getRemark() != null) {
                XSSFRow transferRow6_r = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_r.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_r, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex, 0, 6);
                transferRow6_r.getCell(0).setCellValue(offerVo.getOffer().getRemark());
                transferRow6_r.getCell(0).setCellStyle(style2);

                remarkIndex++;
            }

            //优惠价格
            if (offerVo.getOffer().getSpecialPrice() != null) {
                XSSFRow transferRow6_1 = bodySheet.createRow(totalSize + remarkIndex);
                transferRow6_1.setHeightInPoints(20);
                //创建单元格
                createRowAndCell(transferRow6_1, 7, bodyStyle(workbook, "等线", 9));
                transferRow6_1.setHeightInPoints(20);
                collapse(bodySheet, totalSize + remarkIndex, 0, 4);
                collapse(bodySheet, totalSize + remarkIndex, 5, 6);
                
                String remark="优惠价格";
                if(StringUtils.isNotEmpty(offerVo.getOffer().getSpecialPriceRemark())) {
                	remark=offerVo.getOffer().getSpecialPriceRemark();
                }
                transferRow6_1.getCell(0).setCellValue(remark);
                transferRow6_1.getCell(0).setCellStyle(totaltyle(workbook, "left"));
                transferRow6_1.getCell(5).setCellValue(offerVo.getOffer().getSpecialPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+" "+myOffer.getMoneyUnit());
                transferRow6_1.getCell(5).setCellStyle(totaltyle(workbook, "right"));
                XSSFRow transferRow6_2 = bodySheet.createRow(totalSize + remarkIndex + 1);
                transferRow6_2.setHeightInPoints(20);
                /*//创建单元格
                createRowAndCell(transferRow6_2, 7, bodyStyle(workbook, "等线", 9));
                collapse(bodySheet, totalSize + remarkIndex + 1, 0, 6);
                transferRow6_2.getCell(0).setCellValue(offerVo.getOffer().getSpecialPriceRemark());
                transferRow6_2.getCell(0).setCellStyle(style2);*/
            }

            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 6);
            transferRow7.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells7_1 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "等线", 9));
            transferRow7.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 4, 0, 6);

            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
            transferRow8.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells8_1 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "等线", 9));
            transferRow8.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 5, 0, 6);
            XSSFRichTextString val8 = new XSSFRichTextString("Remarks:");
            transferRow8.getCell(0).setCellValue(val8);
            transferRow8.getCell(0).setCellStyle(style2);

            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
            transferRow9.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "等线", 9));
            transferRow9.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 6, 0, 6);
            XSSFRichTextString val9 = new XSSFRichTextString(
                    "1. Quotation valid time: 30 days from quotation date");
            transferRow9.getCell(0).setCellValue(val9);
            transferRow9.getCell(0).setCellStyle(style2);

            XSSFRow transferRow10 = bodySheet.createRow(totalSize + 7);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells10 = createRowAndCell(transferRow10, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 7, 0, 6);
            StringBuffer packageStr = new StringBuffer();
            transportPackageList.forEach(item -> {
                packageStr.append(item.getPackages()).append(",");
            });

            transferRow10.getCell(0).setCellValue("2. Packing:  " + (transportPackageList.size() > 0 ? packageStr : " "));
            transferRow10.getCell(0).setCellStyle(style2);

            XSSFRow transferRow11 = bodySheet.createRow(totalSize + 8);
            transferRow11.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells11 = createRowAndCell(transferRow11, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 8, 0, 6);
            cells11.get(0).setCellValue("3. Payment terms: "+(myOffer.getPayment() == null ? "" : myOffer.getPayment()));
            style2.setWrapText(true);
            cells11.get(0).setCellStyle(style2);

            XSSFRow transferRow12 = bodySheet.createRow(totalSize + 9);
            transferRow12.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells12 = createRowAndCell(transferRow12, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 9, 0, 6);
            transferRow12.getCell(0).setCellValue("4. leading time: " + myOffer.getWaitingDate() + " days.");
            transferRow12.getCell(0).setCellStyle(style2);

            XSSFRow transferRow13 = bodySheet.createRow(totalSize + 10);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells13 = createRowAndCell(transferRow13, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 10, 0, 6);
            transferRow13.getCell(0).setCellValue("5. Warranty time: "+ offerVo.getOffer().getGuarantee()+" years from delivery date  ");
            transferRow13.getCell(0).setCellStyle(style2);

            XSSFRow transferRow14 = bodySheet.createRow(totalSize + 11);
            transferRow14.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells14 = createRowAndCell(transferRow14, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 11, 0, 6);
            cells14.get(0).setCellValue(
                    "6. Free trainning: 7 days on-site complete technical trainning in our Shenzhen factory");
            cells14.get(0).setCellStyle(style2);

            XSSFRow transferRow15 = bodySheet.createRow(totalSize + 12);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15R = createRowAndCell(transferRow15, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 12, 0, 6);
            cells15R.get(0).setCellValue(
                    "7. Installation Guidance and after-sales premium service package: optional ");
            cells15R.get(0).setCellStyle(style2);

//            //插入图片
//            XSSFRow transferRow16 = bodySheet.createRow(totalSize + 13);
//            transferRow15.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow16, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            XSSFRow transferRow17 = bodySheet.createRow(totalSize + 14);
//            transferRow17.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow17, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            XSSFRow transferRow18 = bodySheet.createRow(totalSize + 15);
//            transferRow15.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow18, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            XSSFRow transferRow19 = bodySheet.createRow(totalSize + 16);
//            transferRow15.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(transferRow19, 7,
//                    bodyStyle(workbook, "等线", 9));
//
//            String fileName = filepath + "absen.png";
//
//            byte[] bytes = new byte[0];
//            try {
//                InputStream is = new FileInputStream(fileName);
//                bytes = IOUtils.toByteArray(is);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
//
//            CreationHelper helper = workbook.getCreationHelper();
//            Drawing drawing = bodySheet.createDrawingPatriarch();
//            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short) 0, totalSize + 13, (short) 2, totalSize + 16);
//
//            Picture pict = drawing.createPicture(anchor, pictureIdx);
//            pict.resize();


            XSSFRow transferRow21 = bodySheet.createRow(totalSize + 17);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells21R = createRowAndCell(transferRow21, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 17, 0, 6);
            cells21R.get(0).setCellValue(
                    "Shenzhen Absen Optoelectronic Co., LTD.");
            cells21R.get(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow transferRow22 = bodySheet.createRow(totalSize + 18);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells22R = createRowAndCell(transferRow22, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 18, 0, 6);
            cells22R.get(0).setCellValue(
                    "Add: 18-20F building 3A, Cloud Park, Bantian, Longgang District, Shenzhen 518129, P.R. China");
            cells22R.get(0).setCellStyle(style2);

            XSSFRow transferRow23 = bodySheet.createRow(totalSize + 19);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells23R = createRowAndCell(transferRow23, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 19, 0, 6);
            cells23R.get(0).setCellValue(
                    "E-mail: absen@absen.com; Http:// www.absen.com");
            cells23R.get(0).setCellStyle(style2);
            XSSFRow transferRow24 = bodySheet.createRow(totalSize + 20);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells24R = createRowAndCell(transferRow24, 7,
                    bodyStyle(workbook, "等线", 9));
            collapse(bodySheet, totalSize + 20, 0, 6);
            cells24R.get(0).setCellValue(
                    "Tel: + 86-755-89747399  Fax: + 86-755-89747599");
            cells24R.get(0).setCellStyle(style2);


            //重新计算excel里面的公式
            reCalculating(workbook);
        }
    }


    /**
     * 格式化数值（保留两位小数）
     */
    public static String format(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    /**
     * 创建并合并 指定的行列 0 1   3 4
     */
    private void setRowAndCell(XSSFWorkbook workbook, XSSFSheet
            sheet, XSSFRow row, int num) {
        //设置行高
        row.setHeightInPoints(20);
        //创建单元格
        List<XSSFCell> cells10 = createRowAndCell(row, 7, bodyStyle(workbook, "等线", 9));
        //设定合并单元格的区域
        collapse(sheet, num, 0, 1);
        collapse(sheet, num, 3, 4);
        collapse(sheet, num, 5, 6);
    }

    private void setRowAndCell1(XSSFWorkbook workbook, XSSFSheet
            sheet, XSSFRow row, int num, int size) {
        //设置行高
        row.setHeightInPoints(20);
        //创建单元格
        List<XSSFCell> cells10 = createRowAndCell(row, 7, bodyStyle(workbook, "等线", size));
        //设定合并单元格的区域
//        collapse(sheet, num, 1, 1);
//        collapse(sheet, num, 2, 6);
        collapse(sheet, num, 3, 4);
        collapse(sheet, num, 5, 6);
//    // 样式对象 
//    XSSFCellStyle style2 = workbook.createCellStyle();
//    //设置边框
//    style2.setBorderBottom(BorderStyle.THIN);
//    style2.setBorderLeft(BorderStyle.THIN);
//    style2.setBorderRight(BorderStyle.THIN);
//    style2.setBorderTop(BorderStyle.THIN);
//    //设置垂直居中
//    style2.setVerticalAlignment(VerticalAlignment.CENTER);
//    style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
//    cells10.get(0).setCellStyle(style2);
//    cells10.get(2).setCellStyle(style2);


    }
    private void setRowAndCell2(XSSFWorkbook workbook, XSSFSheet
            sheet, XSSFRow row, int num, int size) {
        //设置行高
        row.setHeightInPoints(20);
        //创建单元格
        List<XSSFCell> cells10 = createRowAndCell(row, 7, bodyStyle(workbook, "等线", size));
        //设定合并单元格的区域
        collapse(sheet, num, 2, 6);
    }

    private void setRowAndCell3(XSSFWorkbook workbook, XSSFSheet sheet, XSSFRow row, int num) {
        //设置行高
        row.setHeightInPoints(20);
        //创建单元格
        List<XSSFCell> cells10 = createRowAndCell(row, 7, bodyStyle(workbook, "等线", 9));
        //设定合并单元格的区域
        collapse(sheet, num, 0, 1);
        collapse(sheet, num, 3, 4);
        collapse(sheet, num, 5, 6);
    }
    
    public static void removeRow(XSSFSheet  sheet, int rowIndex) {  
        int lastRowNum=sheet.getLastRowNum();  
        if(rowIndex>=0&&rowIndex<lastRowNum) {
        	sheet.shiftRows(rowIndex+1,lastRowNum,-8);//将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行  
        }
        if(rowIndex==lastRowNum){  
        	XSSFRow removingRow=sheet.getRow(rowIndex);  
            if(removingRow!=null)  
                sheet.removeRow(removingRow);  
        }  
    } 

}

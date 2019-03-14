package com.jtech.toa.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.jtech.toa.entity.offer.TransportPackage;
import com.jtech.toa.entity.product.Box;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.dto.products.PanelDetails;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.service.offer.IOfferPanelsService;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
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
public class ExportExcelOld {

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
            if ("en".equals(lang) || "enpdf".equals(lang)) {
                file = new File(filepath + "EU&NA&LA&Asia1&Asia2-Quote Template-en.xlsx");
                msg = Lists
                        .newArrayList("Quotation of Absen-A2712 Display", "Screen Area Dimension", "Unit Price",
                                "free",
                                "Recommended Spare parts --Optional", "Service", "Transport", "Total", "Remarks:",
                                "1. Quotation valid time: 30 days from quotation date.",
                                "2. Packing: ", "T/T 30% down payment, T/T 70% balance before delivery",
                                "T/T 0% down payment, T/T 100% balance before delivery",
                                "3. Payment method: ",
                                "4. Production lead time:7 days from receiving down payment.",
                                "5. Quality Warranty Period: " + offerVo.getOffer().getGuarantee()
                                        + " years from delivery date.");
            } else {
                file = new File(filepath + "CHN-Quote Template-zh.xlsx");
                msg = Lists
                        .newArrayList("艾比森-A2712 显示屏报价", "屏体尺寸", "单价", "免费", "备件 --可选", "服务", "物流", "总计", "备注：",
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
                    workbook = new XSSFWorkbook(inputStream);
                    //设置复制的sheet页名：产品-index
                    XSSFSheet copySheet = workbook.createSheet("Screen" + (i + 1));
                    //复制sheet内容,这里的行表示行号，从1开始计数，而poi读取是从0开始，故+1
                    copySheet(sheet, copySheet, sheet.getLastRowNum() + 1);

                }
            }
            if ("en".equals(lang) || "enPdf".equals(lang) ) {
                templateEUNew2(panelDetailsList, workbook, myOffer, msg, offerVo, sheet,
                        offerPanelsList, filepath, userMsg);
            } else{
                templateZHNew(panelDetailsList, workbook, myOffer, msg, offerVo, sheet,
                        offerPanelsList, filepath, userMsg);
            }

            return workbook;

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
        //创建字体 微软雅黑 10
        XSSFFont font = workbook.createFont();

        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //保留两位小数点
        style.setDataFormat(2);

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
        //创建字体 微软雅黑 12
        XSSFFont font = workbook.createFont();

        font.setFontName("微软雅黑");
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
        //创建字体 微软雅黑 12
        XSSFFont font = workbook.createFont();

        font.setFontName("微软雅黑");
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
     * 文本内容样式创建
     *
     * @param workbook 工作簿
     */
    private XSSFCellStyle bodyStyle(XSSFWorkbook workbook, String fontFamily, int fontSize) {
        // 样式对象 
        XSSFCellStyle style = workbook.createCellStyle();
        //创建字体 微软雅黑 12
        XSSFFont font = workbook.createFont();
        font.setFontName(fontFamily);
        font.setFontHeightInPoints((short) fontSize);
        style.setFont(font);
        //设置水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置边框
//    style.setBorderBottom(BorderStyle.THIN);
//    style.setBorderLeft(BorderStyle.THIN);
//    style.setBorderRight(BorderStyle.THIN);
//    style.setBorderTop(BorderStyle.THIN);
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
        //创建字体 微软雅黑 12
        XSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
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
        // 使用RegionUtil类为合并后的单元格添加边框
//    RegionUtil.setBorderBottom(1, cra, sheet);
//    RegionUtil.setBorderLeft(1, cra, sheet);
//    RegionUtil.setBorderRight(1, cra, sheet);
//    RegionUtil.setBorderTop(1, cra, sheet);
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
//                if (null != cellFrom.getStringCellValue() && !"".equals(cellFrom.getStringCellValue().trim())) {
//                    cellTo.setCellValue(cellFrom.getStringCellValue());
//                }
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
            for (Row r : sheet) {
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
//    private void templateZH(List<PanelDetails> panelDetailsList, XSSFWorkbook workbook,
//                            MyOfferDto myOffer, List<String> msg, OfferVo offerVo, XSSFSheet
//                                    sheet, List<OfferPanelsDto> offerPanelsList,
//                            String filepath) {
//
//        for (int i = 0; i < panelDetailsList.size(); i++) {
//            //总重量
//            BigDecimal weight = new BigDecimal(0);
//            //总面积
//            BigDecimal area = new BigDecimal(0);
//            // 箱体 宽数
//            int wNum = 0;
//            // 箱体 高数
//            int lNum = 0;
//            //平均功耗
//            BigDecimal avgPower = new BigDecimal(0);
//
//            BigDecimal maxPower = new BigDecimal(0);
//            //读取sheet
//            XSSFSheet bodySheet = workbook.getSheetAt(i);
//
//            //第7行设置值，模组的尺寸信息  和 分辨率
//            XSSFRow row6 = bodySheet.getRow(6);
//
//            //插入图片
//            if (i > 0) {
//                String fileName = filepath + "absen.jpeg";
//
//                byte[] bytes = new byte[0];
//                try {
//                    InputStream is = new FileInputStream(fileName);
//                    bytes = IOUtils.toByteArray(is);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
//
//                CreationHelper helper = workbook.getCreationHelper();
//                Drawing drawing = bodySheet.createDrawingPatriarch();
//                ClientAnchor anchor = helper.createClientAnchor();
//                anchor.setCol1(0);
//                anchor.setRow1(0);
//                Picture pict = drawing.createPicture(anchor, pictureIdx);
//                pict.resize();
//            }
//
//            //尺寸信息
//            if (panelDetailsList.get(i).getModule().getWidth() != null
//                    && panelDetailsList.get(i).getModule().getHeight() != null) {
//                row6.getCell(2).setCellValue(
//                        panelDetailsList.get(i).getModule().getWidth().intValue() + "mm X " + panelDetailsList
//                                .get(i).getModule().getHeight().intValue() + "mm");
//            }
//
//            //分辨率
//            if (panelDetailsList.get(i).getModule().getTransverse() != null
//                    && panelDetailsList.get(i).getModule().getPortrait() != null) {
//                row6.getCell(5).setCellValue(
//                        panelDetailsList.get(i).getModule().getTransverse().intValue() + " X "
//                                + panelDetailsList.get(i).getModule().getPortrait().intValue());
//            }
//
//            //第8行设置值，模组的点间距 和 像素点（点数/㎡）
//            XSSFRow row7 = bodySheet.getRow(7);
//            //模组的点间距
//            if (panelDetailsList.get(i).getModule().getPitch() != null) {
//                row7.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getPitch().doubleValue());
//            }
//
//            //像素点（点数/㎡）
//            if (panelDetailsList.get(i).getModule().getDensity() != null) {
//                row7.getCell(5)
//                        .setCellValue(panelDetailsList.get(i).getModule().getDensity().doubleValue() + "点数/㎡ ");
//            }
//
//            //第9行设置值，箱体的配置信息
//            XSSFRow row8 = bodySheet.getRow(8);
//            row8.getCell(5).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());
//
//            List<Box> boxList = Lists.newArrayList();
//            if (panelDetailsList.get(i).getSplicingPanelsDto().size() > 0) {
//                for (OfferPanelsDto offerPanels : panelDetailsList.get(i).getSplicingPanelsDto()) {
//                    boxList.add(offerPanels.getBox());
//                }
//            }
//
//            //拼屏数量
//            int splicingBoxs = boxList.size();
//
//            //生成单元格样式
//            XSSFCellStyle style = titleStyle(workbook);
//            for (int j = 0; j < splicingBoxs; j++) {
//                //第10行设置值，箱体的尺寸 模组个数
//                XSSFRow row9 = bodySheet.createRow(9 + 5 * j);
//                //设置行高
//                row9.setHeightInPoints(20);
//                //创建单元格
//                List<XSSFCell> cells9 = createRowAndCell(row9, 7, bodyStyle(workbook, "微软雅黑", 10));
//                //设定合并单元格的区域
//                collapse(bodySheet, 9 + 5 * j, 0, 6);
//                if (splicingBoxs == 1) {
//                    row9.getCell(0).setCellValue("标准单元箱体");
//                } else {
//                    row9.getCell(0).setCellValue("标准单元箱体" + (j + 1));
//                }
//                row9.getCell(0).setCellStyle(style);
//
//                //第11行设置值，箱体的尺寸 模组个数
//                XSSFRow row10 = bodySheet.createRow(10 + 5 * j);
//                setRowAndCell(workbook, bodySheet, row10, (10 + 5 * j));
//                row10.getCell(0).setCellValue("尺寸(w x h x d) (mm)");
//                row10.getCell(3).setCellValue("模组个数");
//                //箱体的尺寸
//                if (boxList.get(j).getWidth() != null && boxList.get(j).getHeight() != null
//                        && boxList.get(j).getThickness() != null) {
//                    row10.getCell(2).setCellValue(
//                            boxList.get(j).getWidth().intValue() + "mmx" + boxList.get(j).getHeight().intValue()
//                                    + "mmx" + boxList.get(j).getThickness().intValue() + "mm");
//                }
//                //模组个数
//                if (boxList.get(j).getWidth() != null) {
//                    row10.getCell(5).setCellValue(
//                            boxList.get(j).getTransverseCount().intValue() * boxList.get(j).getPortraitCount()
//                                    .intValue());
//                }
//
//                //第12行设置值，分辨率 (w x h)	 实像素点数
//                XSSFRow row11 = bodySheet.createRow(11 + 5 * j);
//                setRowAndCell(workbook, bodySheet, row11, (11 + 5 * j));
//                row11.getCell(0).setCellValue("分辨率 (w x h)");
//                row11.getCell(3).setCellValue("实像素点数");
//                //分辨率
//                if (boxList.get(j).getTransversePix() != null && boxList.get(j).getPortraitPix() != null) {
//                    row11.getCell(2).setCellValue(
//                            boxList.get(j).getTransversePix().intValue() + " x " + boxList.get(j).getPortraitPix()
//                                    .intValue());
//                }
//                //实像素点数
//                if (boxList.get(j).getWidth() != null) {
//                    row11.getCell(5).setCellValue(
//                            boxList.get(j).getTransversePix().intValue() * boxList.get(j).getPortraitPix()
//                                    .intValue());
//                }
//
//                //第13行设置值，箱体的重量 材质
//                XSSFRow row12 = bodySheet.createRow(12 + 5 * j);
//                setRowAndCell(workbook, bodySheet, row12, (12 + 5 * j));
//                row12.getCell(0).setCellValue("重量 (kg/个)");
//                row12.getCell(3).setCellValue("材质");
//                //箱体的重量
//                if (boxList.get(j).getWeight() != null) {
//                    row12.getCell(2).setCellValue(boxList.get(j).getWeight().doubleValue());
//                }
//                //材质
//                if (boxList.get(j).getBoxType() != null) {
//                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
//                }
//
//                //第14行设置值， 单个屏的功率  标准平均功率（Kw ） 标准最大功率（Kw）
//                //标准平均功率
//                XSSFRow row13 = bodySheet.createRow(13 + 5 * j);
//                setRowAndCell(workbook, bodySheet, row13, (13 + 5 * j));
//                row13.getCell(0).setCellValue("标准平均功率（kw/㎡ ）");
//                row13.getCell(3).setCellValue("标准最大功率（kw/㎡）");
//                if (panelDetailsList.get(i).getParams().getPowerAvg() != null) {
//                    row13.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getPowerAvg());
//                }
//                //标准最大功率
//                if (panelDetailsList.get(i).getParams().getPowerMax() != null) {
//                    row13.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getPowerMax());
//                }
//            }
//
//            //拼接箱体 占用行数
//            int panelNum = splicingBoxs * 5;
//
//            XSSFRow row15 = bodySheet.createRow(9 + panelNum);
//
//            //设置行高
//            row15.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 9 + panelNum, 0, 6);
//            row15.getCell(0).setCellValue("显示屏数据");
//            row15.getCell(0).setCellStyle(style);
//            Cell cell = row15.getCell(0);
//            cell.setCellStyle(style);
//
//            XSSFRow row16 = bodySheet.createRow(10 + panelNum);
//            //设置行高
//            row16.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 10 + panelNum, 0, 1);
//            collapse(bodySheet, 10 + panelNum, 3, 4);
//            collapse(bodySheet, 10 + panelNum, 5, 6);
//            row16.getCell(0).setCellValue("项目");
//            row16.getCell(2).setCellValue("宽");
//            row16.getCell(3).setCellValue("高");
//            row16.getCell(5).setCellValue("合计");
//            row16.getCell(0).setCellStyle(style);
//            row16.getCell(2).setCellStyle(style);
//            row16.getCell(3).setCellStyle(style);
//            row16.getCell(5).setCellStyle(style);
//
//            for (int j = 0; j < splicingBoxs; j++) {
//                //第17行设置值，箱体数量
//                XSSFRow row17_panel = bodySheet.createRow(11 + panelNum + j);
//                setRowAndCell3(workbook, bodySheet, row17_panel, (11 + panelNum + j));
//                row17_panel.getCell(0).setCellValue("箱体数量" + (j + 1) + "(pcs)");
//                row17_panel.getCell(2).setCellValue(
//                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
//                row17_panel.getCell(2).setCellStyle(styleDataFormat(workbook));
//
//                row17_panel.getCell(3).setCellValue(
//                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
//                row17_panel.getCell(3).setCellStyle(styleDataFormat(workbook));
//                row17_panel.getCell(5)
//                        .setCellFormula("C" + (12 + panelNum + j) + "*D" + (12 + panelNum + j));
//                row17_panel.getCell(5)
//                        .setCellStyle(styleDataFormat(workbook));
//
//                weight = weight.add(new BigDecimal(
//                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
//                                .get(i).getSplicingPanelsDto().get(j).getLcount())
//                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
//                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
//                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
//            }
//
//            //拼接箱体占用 和 显示屏信息 箱体占用
//            int boxNum = panelNum + splicingBoxs;
//            for (int j = 0; j < splicingBoxs; j++) {
//                //第18行设置 屏体面积(m)
//                XSSFRow row18_new = bodySheet.createRow(11 + boxNum + j);
//                setRowAndCell3(workbook, bodySheet, row18_new, (11 + boxNum + j));
//                if (offerVo.getOffer().getSizeUnit() == 2) {
//                    row18_new.getCell(0).setCellValue(msg.get(1) + "(ft)");
//                } else {
//                    row18_new.getCell(0).setCellValue(msg.get(1) + "(m)");
//                }
//                row18_new.getCell(2).setCellValue(
//                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                                : new BigDecimal(0)).toString());
//                row18_new.getCell(3).setCellValue(
//                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()
//                                : new BigDecimal(0)).toString());
//                //计算面积
//                if (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
//                        && panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null) {
//                    row18_new.getCell(5).setCellFormula("C" + (12 + boxNum + j) + "*D" + (12 + boxNum + j));
//                    area = area.add(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                            .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()));
//
//                }
//
//                avgPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
//                        .multiply(new BigDecimal(
//                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerAvg()))
//                        .add(avgPower);
//                maxPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
//                        .multiply(new BigDecimal(
//                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerMax()))
//                        .add(maxPower);
//            }
//            //拼接箱体占用 和 显示屏信息 箱体占用 及 屏体尺寸
//            int splicingPanels = boxNum + splicingBoxs - 2;
//            //第19行设置值，显示屏重量(kg)
//            XSSFRow row19 = bodySheet.createRow(13 + splicingPanels);
//            setRowAndCell3(workbook, bodySheet, row19, (13 + splicingPanels));
//            row19.getCell(0).setCellValue("显示分辨率 (点)");
//            row19.getCell(2)
//                    .setCellValue(panelDetailsList.get(i).getModule().getTransverse().intValue() * wNum);
//            row19.getCell(3)
//                    .setCellValue(panelDetailsList.get(i).getModule().getPortrait().intValue() * lNum);
//            row19.getCell(5).setCellFormula("C" + (14 + splicingPanels) + "*D" + (14 + splicingPanels));
//
//
//            //第20行设置值，显示屏重量(kg)
//            XSSFRow row20 = bodySheet.createRow(14 + splicingPanels);
//            //设置行高
//            row20.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 14 + splicingPanels, 0, 1);
//            collapse(bodySheet, 14 + splicingPanels, 2, 6);
//            row20.getCell(0).setCellValue("显示屏重量(kg)");
//            row20.getCell(2).setCellValue(weight.toString());
//
//            //第21行设置值，标准平均功率（Kw ）	 标准最大功率（Kw）
//            XSSFRow row21 = bodySheet.createRow(15 + splicingPanels);
//            //设置行高
//            row20.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 15 + splicingPanels, 0, 1);
//            collapse(bodySheet, 15 + splicingPanels, 3, 4);
//            collapse(bodySheet, 15 + splicingPanels, 5, 6);
//            //标准平均功率（Kw ）
//            row21.getCell(0).setCellValue("标准平均功率（Kw ）");
//            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//            //标准最大功率（Kw）
//            row21.getCell(3).setCellValue("标准最大功率（Kw）");
//            row21.getCell(5).setCellValue(maxPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//
//            //第22行设置值，亮度
//            XSSFRow row22_21 = bodySheet.createRow(16 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row22_21, (16 + splicingPanels), 12);
//            row22_21.getCell(0).setCellValue("参数");
//            row22_21.getCell(2).setCellValue("参数值");
//            row22_21.getCell(0).setCellStyle(style);
//            row22_21.getCell(2).setCellStyle(style);
//
//            //第22行设置值，亮度
//            XSSFRow row22 = bodySheet.createRow(17 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row22, (17 + splicingPanels), 10);
//            row22.getCell(0).setCellValue("亮度");
//            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
//                    panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");
//
//            //第23行设置值，视角
//            XSSFRow row23 = bodySheet.createRow(18 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row23, (18 + splicingPanels), 10);
//            row23.getCell(0).setCellValue("可视角度");
//            row23.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getViewing());
//
//            //第24行设置值，最小可视距离
//            XSSFRow row24 = bodySheet.createRow(19 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row24, (19 + splicingPanels), 10);
//            row24.getCell(0).setCellValue("推荐最小观看距离");
//            if (panelDetailsList.get(i).getModule().getPitch() != null) {
//                row24.getCell(2)
//                        .setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
//            }
//            //第28行设置值，灰度
//            XSSFRow row25 = bodySheet.createRow(20 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row25, (20 + splicingPanels), 10);
//            row25.getCell(0).setCellValue("灰度等级");
//            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());
//
//            //第28行设置值，色温
//            XSSFRow row26 = bodySheet.createRow(21 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row26, (21 + splicingPanels), 10);
//            row26.getCell(0).setCellValue("色温");
//            row26.getCell(2).setCellValue("6500K");
//
//            //第29行设置值，刷新率
//            XSSFRow row27 = bodySheet.createRow(22 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row27, (22 + splicingPanels), 10);
//            row27.getCell(0).setCellValue("刷新频率");
//            row27.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
//                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");
//
//            //第34行设置值,对比度
//            XSSFRow row28 = bodySheet.createRow(23 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row28, (23 + splicingPanels), 10);
//            row28.getCell(0).setCellValue("对比度");
//            row28.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());
//
//            //第34行设置值,工作电压
//            XSSFRow row29 = bodySheet.createRow(24 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row29, (24 + splicingPanels), 10);
//            row29.getCell(0).setCellValue("工作电压");
//            row29.getCell(2).setCellValue("110 ～ 240 V ，50 /60 HZ");
//
//            //第34行设置值,盲点率
//            XSSFRow row30 = bodySheet.createRow(25 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row30, (25 + splicingPanels), 10);
//            row30.getCell(0).setCellValue("盲点率");
//            row30.getCell(2).setCellValue("小于万分之一");
//
//            //第34行设置值,使用寿命（50%亮度）
//            XSSFRow row31 = bodySheet.createRow(26 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row31, (26 + splicingPanels), 10);
//            row31.getCell(0).setCellValue("使用寿命（50%亮度）");
//            row31.getCell(2).setCellValue("100000 小时");
//
//            //第34行设置值，防护等级
//            XSSFRow row32 = bodySheet.createRow(27 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row32, (27 + splicingPanels), 10);
//            row32.getCell(0).setCellValue("防护等级（正面/背面）");
//            row32.getCell(2).setCellValue(" IP40/ IP21");
//            //第34行设置值，工作环境温度
//            XSSFRow row33 = bodySheet.createRow(28 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row33, (28 + splicingPanels), 10);
//            row33.getCell(0).setCellValue("工作环境温度");
//            row33.getCell(2).setCellValue("  ﹣10 ～﹢40 ℃");
//            //第34行设置值，储存环境温度
//            XSSFRow row34 = bodySheet.createRow(29 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row34, (29 + splicingPanels), 10);
//            row34.getCell(0).setCellValue("储存环境温度");
//            row34.getCell(2).setCellValue("  ﹣40 ～﹢60 ℃");
//            //第34行设置值，工作环境湿度
//            XSSFRow row35 = bodySheet.createRow(30 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row35, (30 + splicingPanels), 10);
//            row35.getCell(0).setCellValue("工作环境湿度");
//            row35.getCell(2).setCellValue(" 10％ ～ 90％");
//            //第34行设置值，储存环境湿度
//            XSSFRow row36 = bodySheet.createRow(31 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row36, (31 + splicingPanels), 10);
//            row36.getCell(0).setCellValue("储存环境湿度");
//            row36.getCell(2).setCellValue(" 10％ ～ 90％");
//            //第34行设置值，控制距离
//            XSSFRow row37 = bodySheet.createRow(32 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row37, (32 + splicingPanels), 10);
//            row37.getCell(0).setCellValue("控制距离");
//            row37.getCell(2).setCellValue("超五类网线传输距离为100米；单模光纤传输距离可达10公里。");
//            //第34行设置值，操作系统平台
//            XSSFRow row38 = bodySheet.createRow(33 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row38, (33 + splicingPanels), 10);
//            row38.getCell(0).setCellValue("操作系统平台");
//            row38.getCell(2).setCellValue(" Windows (2000/XP/Vista/7)");
//
//            //第43行设置值，屏体价格
//            XSSFRow row39 = bodySheet.createRow(34 + splicingPanels);
//            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 34 + splicingPanels, 0, 6);
//            row39.getCell(0).setCellValue("工程报价表");
//            row39.getCell(0).setCellStyle(style);
//
//            //第43行设置值，屏体价格
//            XSSFRow row40 = bodySheet.createRow(35 + splicingPanels);
//            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //第43行设置值，屏体价格
//            XSSFRow row41 = bodySheet.createRow(36 + splicingPanels);
//            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            row41.getCell(5).setCellValue("（元）");
//            row41.getCell(6).setCellValue("(元)");
//
//            row41.getCell(5).setCellStyle(style);
//            row41.getCell(6).setCellStyle(style);
//            //设定合并单元格的区域
//            collapseAuto(bodySheet, 35 + splicingPanels, 36 + splicingPanels, 0, 1);
//            collapseAuto(bodySheet, 35 + splicingPanels, 36 + splicingPanels, 2, 2);
//            collapseAuto(bodySheet, 35 + splicingPanels, 36 + splicingPanels, 3, 4);
//            row40.getCell(0).setCellValue("屏体部分");
//            row40.getCell(2).setCellValue("规格或型号");
//            row40.getCell(3).setCellValue("数量");
//            row40.getCell(5).setCellValue("单价");
//            row40.getCell(6).setCellValue("小计");
//            row40.getCell(0).setCellStyle(style);
//            row40.getCell(2).setCellStyle(style);
//            row40.getCell(3).setCellStyle(style);
//            row40.getCell(5).setCellStyle(style);
//            row40.getCell(6).setCellStyle(style);
//
//            //第43行设置值，屏体价格
//            XSSFRow row43 = bodySheet.createRow(37 + splicingPanels);
//            //设置行高
//            row43.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 37 + splicingPanels, 0, 1);
//            row43.getCell(0).setCellValue("显示屏");
//            row43.getCell(2).setCellValue(offerPanelsList.get(i).getSeriesName());
//            row43.getCell(3).setCellFormula(String.valueOf(area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP)));
//            row43.getCell(4).setCellValue("平方米");
//
//            BigDecimal panelPrice = panelDetailsList.get(i).getOfferPanels().getPrice() == null ? new BigDecimal(0)
//                    : panelDetailsList.get(i).getOfferPanels().getPrice();
//            System.out.println("计算总价：" + area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP) + " * " + panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP) + " = " + area.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(panelPrice));
//            row43.getCell(5).setCellValue(String.valueOf(panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
//
//            row43.getCell(6).setCellValue(String.valueOf(area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP)));
////      row43.getCell(6).setCellValue(String.valueOf(panelDetailsList.get(i).getOfferPanels().getTotalPrice()));
//            //第45行设置值，屏体价格
//            XSSFRow row44 = bodySheet.createRow(38 + splicingPanels);
//            //设置行高
//            row44.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 38 + splicingPanels, 0, 1);
//            row44.getCell(0).setCellValue("LED播放软件");
//            row44.getCell(2).setCellValue("Absen LED 演播室");
//            row44.getCell(3).setCellValue("1.00 ");
//            row44.getCell(4).setCellValue("套");
//            row44.getCell(5).setCellValue("");
//            row44.getCell(6).setCellValue("");
//
//            //第45行设置值，屏体价格
//            XSSFRow row45 = bodySheet.createRow(39 + splicingPanels);
//            //设置行高
//            row43.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 39 + splicingPanels, 0, 1);
//            collapse(bodySheet, 39 + splicingPanels, 2, 4);
//            collapse(bodySheet, 39 + splicingPanels, 5, 6);
//            row45.getCell(0).setCellValue("合计");
//            row45.getCell(2).setCellValue(offerPanelsList.get(i).getSeriesName());
//            row45.getCell(5)
//                    .setCellFormula("G" + (38 + splicingPanels) + "+G" + (39 + splicingPanels));
//
//            //
//            XSSFRow row46_46 = bodySheet.createRow(40 + splicingPanels);
//            //创建单元格
//            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, 40 + splicingPanels, 0, 6);
//            cells46.get(0).setCellValue("外围设备");
//            cells46.get(0).setCellStyle(style);
//
//            //获取各种备件集合的长度
//            int standardLength = 0;
//            int selfStandardLength = 0;
//            int spareLength = 0;
//            int selfSpareLength = 0;
//            int freeLength = 0;
//            int selfFreeLength = 0;
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
//                standardLength = panelDetailsList.get(i).getStandardDetailList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
//                selfStandardLength = panelDetailsList.get(i).getSelfStandardList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
//                spareLength = panelDetailsList.get(i).getSpareDetailList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
//                selfSpareLength = panelDetailsList.get(i).getSelfSpareList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
//                freeLength = panelDetailsList.get(i).getFreeDetailList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
//                selfFreeLength = panelDetailsList.get(i).getSelfFreeList().size();
//            }
//            int totalLength =
//                    standardLength + selfStandardLength + spareLength + selfSpareLength + freeLength
//                            + selfFreeLength;
//            int index = 41 + splicingPanels;
//            //标配备件信息循环插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getStandardDetailList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j, 0, 1);
//                    cells.get(0)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getModel());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getRealCount());
//                    cells.get(4)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getStandardDetailList().get(j).getRealPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
//                    cells.get(6).setCellFormula("D" + (index + j + 1) + "*F" + (index + j + 1));
//                }
//            }
//            //自定义标配备件信息插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSelfStandardList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + standardLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getSelfStandardList().get(j).getPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
//                    cells.get(6).setCellFormula(
//                            "D" + (index + j + 1 + standardLength) + "*F" + (index + j + 1 + standardLength));
//                }
//            }
//
//            //免费备件信息循环插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getFreeDetailList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength + selfStandardLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + standardLength + selfStandardLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getType());
//                    cells.get(2).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getModel());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getRealCount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getUnit());
//                    cells.get(5).setCellValue(msg.get(3));
//                    cells.get(6).setCellValue(0);
//                }
//            }
//            //自定义免费备件信息插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSelfFreeList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet
//                            .createRow(index + j + standardLength + selfStandardLength + freeLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + standardLength + selfStandardLength + freeLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
//                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getUnit());
//                    cells.get(5).setCellValue(msg.get(3));
//                    cells.get(6).setCellValue(0);
//                }
//            }
//
//            //选配备件信息循环插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSpareDetailList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(
//                            index + j + standardLength + selfStandardLength + freeLength + selfFreeLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet,
//                            index + j + standardLength + selfStandardLength + freeLength + selfFreeLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getType());
//                    cells.get(2).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getModel());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getRealCount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getSpareDetailList().get(j).getRealPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellFormula(
//                            "D" + (index + j + 1 + standardLength + selfStandardLength + freeLength
//                                    + selfFreeLength) +
//                                    "*F" + (index + j + 1 + standardLength + selfStandardLength + freeLength
//                                    + selfFreeLength));
//                }
//            }
//            //自定义选配信息插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSelfSpareList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength - selfSpareLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
//                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getSelfSpareList().get(j).getPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellFormula(
//                            "D" + (index + j + totalLength - selfSpareLength + 1) + "*F" + (
//                                    index + j + totalLength - selfSpareLength + 1));
//                }
//            }
//            XSSFRow transferRow0 = bodySheet.createRow(index + totalLength);
//            transferRow0.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells0 = createRowAndCell(transferRow0, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow0.setHeightInPoints(20);
//            collapse(bodySheet, index + totalLength, 0, 1);
//            collapse(bodySheet, index + totalLength, 5, 6);
//            cells0.get(0).setCellValue("合计");
//            cells0.get(5).setCellFormula("SUM(G" + (index + 1) + ":G" + (index + totalLength) + ")");
//            cells0.get(5).setCellStyle(style);
//            XSSFRow transferRowTitle = bodySheet.createRow(index + totalLength + 1);
//            transferRowTitle.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells1 = createRowAndCell(transferRowTitle, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, index + totalLength + 1, 0, 6);
//            XSSFRichTextString val40 = new XSSFRichTextString("其它费用");
//            cells1.get(0).setCellValue(val40);
//            cells1.get(0).setCellStyle(style);
//
//            //服务列表长度
//            int serviceLength = 0;
//            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
//                serviceLength = offerVo.getServiceListDto().size();
//                //服务费用
//                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + 2 + j + totalLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + 2 + j + totalLength, 0, 1);
//                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
//                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
//                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
//                                    .getServiceDiscount().doubleValue() / 100));
//                    cells.get(6).setCellFormula(
//                            "D" + (index + 3 + j + totalLength) + "*F" + (index + 3 + j + totalLength));
//                }
//            }
//
//            int totalService = index + totalLength + serviceLength;
//            List<TransportPackage> transportPackageList = offerVo.getTransport();
//            if (transportPackageList.size() > 0) {
//                //运输费用
//                for (int j = 0; j < transportPackageList.size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(j + totalService + 2);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, j + totalService + 2, 0, 1);
//                    cells.get(0).setCellValue(transportPackageList.get(j).getPackages());
//                    cells.get(3).setCellValue(transportPackageList.get(j).getNumber());
//                    cells.get(4).setCellValue("");
//                    cells.get(5).setCellValue(String.valueOf(transportPackageList.get(j).getPriceUnit()));
//                    cells.get(6).setCellFormula("D" + (j + totalService + 3) + "*F" + (j + totalService + 3));
//                }
//            }
//            if (offerVo.getTransfer() != null) {
//                //创建行
//                XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size() + 2);
//                //设置行高
//                autoRowTransfer.setHeightInPoints(20);
//                //创建单元格
//                List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "微软雅黑", 10));
//                //设定合并单元格的区域
//                collapse(bodySheet, totalService + transportPackageList.size() + 2, 0, 1);
//                cells.get(0).setCellValue("预计物流费");
//                cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "" : String.valueOf(offerVo.getTransfer().getCost().setScale(2, BigDecimal.ROUND_HALF_UP)));
//                cells.get(4).setCellValue("");
//                cells.get(5).setCellValue(1);
//                cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 3) + "*F" + (totalService + transportPackageList.size() + 3));
//                totalService = totalService + 1;
//            }
//
//
//            XSSFRow transferRow5 = bodySheet.createRow(totalService + transportPackageList.size() + 2);
//            transferRow5.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells5 = createRowAndCell(transferRow5, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow5.setHeightInPoints(20);
//            collapse(bodySheet, totalService + transportPackageList.size() + 2, 0, 1);
//            collapse(bodySheet, totalService + transportPackageList.size() + 2, 5, 6);
//            cells5.get(0).setCellValue("合计");
//            cells5.get(5).setCellFormula(
//                    "SUM(G" + (index + 3 + totalLength) + ":G" + (totalService + 2 + transportPackageList
//                            .size()) + ")");
//            cells5.get(5).setCellStyle(style);
//            XSSFRow transferRow6 = bodySheet.createRow(totalService + 3 + transportPackageList.size());
//            transferRow6.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells6 = createRowAndCell(transferRow6, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow6.setHeightInPoints(20);
//            collapse(bodySheet, totalService + 3 + transportPackageList.size(), 0, 4);
//            collapse(bodySheet, totalService + 3 + transportPackageList.size(), 5, 6);
//            XSSFRichTextString val6 = new XSSFRichTextString("合计");
//            cells6.get(0).setCellValue(val6);
//            cells6.get(5).setCellFormula(
//                    "F" + (index + totalLength + 1) + "+F" + (40 + splicingPanels) + "+F" + (totalService
//                            + transportPackageList.size() + 3));
//            cells6.get(5).setCellStyle(style);
//            int totalSize = totalService + transportPackageList.size();
//
//            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 4);
//            transferRow7.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells7 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow7.setHeightInPoints(20);
//            collapse(bodySheet, totalSize + 4, 0, 6);
//
//            // 样式对象 
//            XSSFCellStyle style2 = workbook.createCellStyle();
//            //设置垂直居中
//            style2.setVerticalAlignment(VerticalAlignment.CENTER);
//            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
//            Font font = workbook.createFont();
//            font.setFontName("微软雅黑");
//            font.setFontHeightInPoints((short) 10);
//            style2.setFont(font);
//            //设置边框
////      style2.setBorderBottom(BorderStyle.THIN);
////      style2.setBorderLeft(BorderStyle.THIN);
////      style2.setBorderRight(BorderStyle.THIN);
////      style2.setBorderTop(BorderStyle.THIN);
//            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
//            transferRow8.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells8 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow8.setHeightInPoints(20);
//            collapse(bodySheet, totalSize + 5, 0, 6);
//
//            cells8.get(0).setCellValue("备注：");
//            cells8.get(0).setCellStyle(style2);
//
//            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
//            transferRow9.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow9.setHeightInPoints(20);
//            collapse(bodySheet, totalSize + 6, 0, 6);
//            cells9.get(0).setCellValue("1.此报价有效时间为: -----天");
//            cells9.get(0).setCellStyle(style2);
//
//            XSSFRow transferRow10 = bodySheet.createRow(totalSize + 7);
//            transferRow10.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells10 = createRowAndCell(transferRow10, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            collapse(bodySheet, totalSize + 7, 0, 6);
//            cells10.get(0).setCellValue("2.生产周期：收到30%预付款次日起，交货期为" + (myOffer.getWaitingDate()) + "天；");
//            cells10.get(0).setCellStyle(style2);
//        }
//        //重新计算excel里面的公式
//        reCalculating(workbook);
//    }

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

//            //插入图片
//            if (i > 0) {
//                String fileName = filepath + "absen0.png";
//
//                byte[] bytes = new byte[0];
//                try {
//                    InputStream is = new FileInputStream(fileName);
//                    bytes = IOUtils.toByteArray(is);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
//
//                CreationHelper helper = workbook.getCreationHelper();
//                Drawing drawing = bodySheet.createDrawingPatriarch();
//                ClientAnchor anchor = helper.createClientAnchor();
//                anchor.setCol1(4);
//                anchor.setRow1(5);
//                Picture pict = drawing.createPicture(anchor, pictureIdx);
//                pict.resize();
//            }
            //生成单元格样式
            XSSFCellStyle style = titleStyle(workbook);

            XSSFCellStyle styleRise = workbook.createCellStyle();
            //创建字体 微软雅黑
            XSSFFont fontRise = workbook.createFont();
            fontRise.setFontName("微软雅黑");
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
            List<XSSFCell> cells2 = createRowAndCell(row2, 7, bodyStyle(workbook, "微软雅黑", 10));
            row2.getCell(0).setCellValue("Date:" + sdf.format(offerVo.offer.getCreateTime()));
            row2.getCell(0).setCellStyle(styleRise);
            XSSFCellStyle styleRise1 = workbook.createCellStyle();
            //创建字体 微软雅黑
            XSSFFont fontRise1 = workbook.createFont();
            fontRise1.setFontName("微软雅黑");
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
            List<XSSFCell> cells5_1 = createRowAndCell(row5_1, 7, bodyStyle(workbook, "微软雅黑", 10));
            row5_1.getCell(0).setCellValue("To: " + myOffer.getCustomerName());
            row5_1.getCell(0).setCellStyle(styleRise);


            //
            XSSFRow row5_2 = bodySheet.createRow(4);
            //设置行高
            row5_2.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells5_2 = createRowAndCell(row5_2, 7, bodyStyle(workbook, "微软雅黑", 10));
            row5_2.getCell(0).setCellValue("ATTN: ");
            row5_2.getCell(0).setCellStyle(styleRise);
            //
            XSSFRow row5_3 = bodySheet.createRow(5);
            //设置行高
            row5_3.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_3, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            //创建字体 微软雅黑
            XSSFFont fontQuotation = workbook.createFont();
            fontQuotation.setFontName("微软雅黑");
            fontQuotation.setFontHeightInPoints((short) 20);
            fontQuotation.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleQuotation.setFont(fontQuotation);
            //设置垂直居中
            styleQuotation.setVerticalAlignment(VerticalAlignment.CENTER);
            styleQuotation.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            styleQuotation.setWrapText(true);
            //创建单元格
            createRowAndCell(row1, 7, bodyStyle(workbook, "微软雅黑", 10));
            createRowAndCell(row1_1, 7, bodyStyle(workbook, "微软雅黑", 10));
            createRowAndCell(row1_2, 7, bodyStyle(workbook, "微软雅黑", 10));
            createRowAndCell(row1_3, 7, bodyStyle(workbook, "微软雅黑", 10));
            collapseAuto(bodySheet, quotation, quotation + 3, 0, 3);
            row1.getCell(0).setCellValue(
                    "Quotation of  Absen-" + offerPanelsList.get(i).getSeriesName() + " LED Display");
            row1.getCell(0).setCellStyle(styleQuotation);


            int first = 53;
            XSSFRow row5_4 = bodySheet.createRow(first);
            //设置行高
            row5_4.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_4, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, first, 0, 6);
            row5_4.getCell(0).setCellValue("技术参数:");
            row5_4.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row5_5 = bodySheet.createRow(first + 1);
            //设置行高
            row5_5.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_5, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            if (panelDetailsList.get(i).getModule().getDensity() != null) {
                row7.getCell(5)
                        .setCellValue(panelDetailsList.get(i).getModule().getDensity().doubleValue());
            }

            //第9行设置值，箱体的配置信息
            XSSFRow row8 = bodySheet.createRow(firstRow + 2);
            //设置行高
            row8.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row8, (firstRow + 2));
            row8.getCell(0).setCellValue("LED 灯");
            row8.getCell(3).setCellValue("像素组成");
            row8.getCell(5).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());

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
                createRowAndCell(row9, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
                    row12.getCell(5).setCellStyle(styleDataFormat(workbook));
                }

                //第13行设置值，
                XSSFRow row12_1 = bodySheet.createRow(firstRow + 7 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12_1, (firstRow + 7 + 6 * j));
                row12_1.getCell(0).setCellValue("证书");

                //Certificate
                row12_1.getCell(2).setCellValue("CE");
                row12_1.getCell(3).setCellValue("结构");
                //Structure
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
            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 3 + panelNum, 0, 6);

            row15.getCell(0).setCellValue("显示屏数据");
            row15.getCell(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow row16 = bodySheet.createRow(firstRow + 4 + panelNum);
            //设置行高
            row16.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "微软雅黑", 10));

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
                //第17行设置值，箱体数量
                XSSFRow row17_panel = bodySheet.createRow(firstRow + 5 + panelNum + j);
                setRowAndCell3(workbook, bodySheet, row17_panel, (firstRow + 5 + panelNum + j));
                row17_panel.getCell(0).setCellValue("箱体数量(pcs)" + (j + 1) + "(pcs)");
                row17_panel.getCell(2).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
                row17_panel.getCell(2).setCellStyle(styleDataFormat(workbook));
                row17_panel.getCell(3).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
                row17_panel.getCell(3).setCellStyle(styleDataFormat(workbook));
                row17_panel.getCell(5)
                        .setCellFormula("C" + (firstRow + 6 + panelNum + j) + "*D" + (firstRow + 6 + panelNum + j));
                row17_panel.getCell(5).setCellStyle(styleDataFormat(workbook));

                weight = weight.add(new BigDecimal(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
                                .get(i).getSplicingPanelsDto().get(j).getLcount())
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
            }
            int boxNum = panelNum + splicingPanelsSize;

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第18行设置 屏体面积(m)
                XSSFRow row18_new = bodySheet.createRow(firstRow + 5 + boxNum + j);
                setRowAndCell3(workbook, bodySheet, row18_new, (firstRow + 5 + boxNum + j));
                if (offerVo.getOffer().getSizeUnit() == 2) {
                    row18_new.getCell(0).setCellValue(msg.get(1) + "(ft)");
                } else {
                    row18_new.getCell(0).setCellValue(msg.get(1) + "(m)");
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
            row19.getCell(0).setCellValue("屏体面积(m)");
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
            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 9 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 3, 4);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 5, 6);
            //标准平均功率（Kw ）
            row21.getCell(0).setCellValue("标准平均功率（Kw ）");
            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 27 + splicingPanels, 0, 6);
            row39.getCell(0).setCellValue("工程报价表");
            row39.getCell(0).setCellStyle(titleStyleMain(workbook));

            //第43行设置值，屏体价格
            XSSFRow row40 = bodySheet.createRow(firstRow + 28 + splicingPanels);
            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "微软雅黑", 10));
            //第43行设置值，屏体价格
            XSSFRow row41 = bodySheet.createRow(firstRow + 29 + splicingPanels);
            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            XSSFRow row44 = bodySheet.createRow(firstRow + 31 + splicingPanels);
            //设置行高
            row44.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
            row44.getCell(0).setCellValue("接收卡");
            row44.getCell(2).setCellValue("Nova A8S");
            row44.getCell(3).setCellValue("1.00 ");
            row44.getCell(4).setCellValue("pc");

            //第45行设置值，屏体价格
            XSSFRow row45 = bodySheet.createRow(firstRow + 32 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 32 + splicingPanels, 0, 1);
            row45.getCell(0).setCellValue("LED播放软件");
            row45.getCell(2).setCellValue("NOVA STUDIO");
            row45.getCell(5).setCellValue("");
            row45.getCell(6).setCellValue("");

            //
            XSSFRow row46_46 = bodySheet.createRow(firstRow + 33 + splicingPanels);
            //创建单元格
            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 33 + splicingPanels, 1, 6);
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
            int index = firstRow + 34 + splicingPanels;
            //标配备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getStandardDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j);
                    //设置行高
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getUnit());
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getUnit());
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
//                    bodyStyle(workbook, "微软雅黑", 10));
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
                        bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + +freeLength + 1, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getUnit());
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }

            int totalLengthCount = totalLength + freeLength + selfFreeLength + 1;

            XSSFRow transferRowTitle = bodySheet.createRow(index + totalLengthCount);
            transferRowTitle.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells1_1 = createRowAndCell(transferRowTitle, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, index + totalLengthCount, 1, 6);
            cells1_1.get(1).setCellValue("包装");
            cells1_1.get(1).setCellStyle(style);
            cells1_1.get(0).setCellStyle(styleColor);

            //服务列表长度
            int serviceLength = 0;
            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
                serviceLength = offerVo.getServiceListDto().size();
                //服务费用
                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLengthCount + 1);
                    //设置行高
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLengthCount + 1, 0, 1);
                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
                                    .getServiceDiscount().doubleValue() / 100));
                    cells.get(5).setCellStyle(styleDataFormat(workbook));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + 2 + j + totalLengthCount) + "*F" + (index + 2 + j + totalLengthCount));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            int totalService = index + totalLengthCount + serviceLength + 1;
            List<TransportPackage> transportPackageList = offerVo.getTransport();
            if (transportPackageList.size() > 0) {
                //运输费用
                for (int j = 0; j < transportPackageList.size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(j + totalService);
                    //设置行高
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                //创建行
                XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size());
                //设置行高
                autoRowTransfer.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "微软雅黑", 10));
                //设定合并单元格的区域
                collapse(bodySheet, totalService + transportPackageList.size(), 0, 1);
                cells.get(0).setCellValue("发货日期");
                cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "0" : String.valueOf(offerVo.getTransfer().getCost()));
                cells.get(4).setCellValue("");
                cells.get(5).setCellValue(1);
                cells.get(6).setCellType(CELL_TYPE_FORMULA);
                cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 1) + "*F" + (totalService + transportPackageList.size() + 1));
                cells.get(6).setCellStyle(styleDataFormat(workbook));

                totalService = totalService + 1;
            }

            XSSFRow transferRow6 = bodySheet.createRow(totalService + transportPackageList.size());
            transferRow6.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells6_1 = createRowAndCell(transferRow6, 7, bodyStyle(workbook, "微软雅黑", 10));
            transferRow6.setHeightInPoints(20);
            collapse(bodySheet, totalService + transportPackageList.size(), 0, 4);
            collapse(bodySheet, totalService + transportPackageList.size(), 5, 6);
            XSSFRichTextString val6 = new XSSFRichTextString(
                    "(" + offerVo.getTransfer().getTrade()==null ? "":offerVo.getTransfer().getTrade() + "  深圳  " + myOffer.getMoneyUnit() + ") 总计:");
            transferRow6.getCell(0).setCellValue(val6);
            transferRow6.getCell(5).setCellFormula(
                    "SUM(G" + (42 + splicingPanels) + ":G" + (totalService + transportPackageList.size() + 1) + ")");
            transferRow6.getCell(0).setCellStyle(style);
            transferRow6.getCell(5).setCellStyle(style);

            int totalSize = totalService + transportPackageList.size();

            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 4);
            transferRow7.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells7_1 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "微软雅黑", 10));
            transferRow7.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 4, 0, 6);

            // 样式对象 
            XSSFCellStyle style2 = workbook.createCellStyle();
            //设置垂直居中
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            Font font = workbook.createFont();
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);
            style2.setFont(font);

            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
            transferRow8.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells8_1 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "微软雅黑", 10));
            transferRow8.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 5, 0, 6);
            XSSFRichTextString val8 = new XSSFRichTextString("备注:");
            transferRow8.getCell(0).setCellValue(val8);
            transferRow8.getCell(0).setCellStyle(style2);

            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
            transferRow9.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "微软雅黑", 10));
            transferRow9.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 6, 0, 6);
            XSSFRichTextString val9 = new XSSFRichTextString("1.报价有效期：此报价30天内有效");
            transferRow9.getCell(0).setCellValue(val9);
            transferRow9.getCell(0).setCellStyle(style2);

            XSSFRow transferRow10 = bodySheet.createRow(totalSize + 7);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells10 = createRowAndCell(transferRow10, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 7, 0, 6);
            StringBuffer packageStr = new StringBuffer();
            transportPackageList.forEach(item -> {
                packageStr.append(item.getPackages()).append(",");
            });
            transferRow10.getCell(0).setCellValue("2.包装方式：" + (transportPackageList.size() > 0 ? packageStr : ""));
            transferRow10.getCell(0).setCellStyle(style2);

            XSSFRow transferRow11 = bodySheet.createRow(totalSize + 8);
            transferRow11.setHeightInPoints(40);
            //创建单元格
            List<XSSFCell> cells11 = createRowAndCell(transferRow11, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 8, 0, 6);
            cells11.get(0).setCellValue("3.付款方式" + (myOffer.getPayment() == null ? "" : myOffer.getPayment()));
            cells11.get(0).setCellStyle(style2);

            XSSFRow transferRow12 = bodySheet.createRow(totalSize + 9);
            transferRow12.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells12 = createRowAndCell(transferRow12, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 9, 0, 6);
            transferRow12.getCell(0).setCellValue("4.交货周期：收到预付款" + myOffer.getWaitingDate() + "个自然日（不包含中国法定节假日）");
            transferRow12.getCell(0).setCellStyle(style2);

            XSSFRow transferRow13 = bodySheet.createRow(totalSize + 10);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells13 = createRowAndCell(transferRow13, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 10, 0, 6);
            transferRow13.getCell(0).setCellValue("5.质保期限：发货之日起，屏体质保两年，备件质保一年");
            transferRow13.getCell(0).setCellStyle(style2);

            XSSFRow transferRow14 = bodySheet.createRow(totalSize + 11);
            transferRow14.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells14 = createRowAndCell(transferRow14, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 11, 0, 6);
            cells14.get(0).setCellValue(
                    "6.培训服务：免费提供惠州工厂/德国公司7天的在线培训");
            cells14.get(0).setCellStyle(style2);

            XSSFRow transferRow15 = bodySheet.createRow(totalSize + 12);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15R = createRowAndCell(transferRow15, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 12, 0, 6);
            cells15R.get(0).setCellValue(
                    "7.安装指导和售后维保服务可选");
            cells15R.get(0).setCellStyle(style2);
        }
        //重新计算excel里面的公式
        reCalculating(workbook);
    }

    /**
     * 20180919 新增版本
     * 其他区域模板读写方法
     */
    private void templateEUNew2(List<PanelDetails> panelDetailsList, XSSFWorkbook workbook ,
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
            //创建字体 微软雅黑
            XSSFFont fontRise = workbook.createFont();
            fontRise.setFontName("微软雅黑");
            fontRise.setFontHeightInPoints((short) 12);
            fontRise.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            styleRise.setFont(fontRise);
            //设置垂直居中
            styleRise.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRise.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);

            XSSFRow row1 = bodySheet.createRow(1);
            createRowAndCell(row1, 7, bodyStyle(workbook, "微软雅黑", 10));

            //设置行高
            row1.setHeightInPoints(20);
            row1.getCell(0).setCellValue(
                    "Quotation of  Absen-" + offerPanelsList.get(i).getSeriesName() + " LED Display");
            row1.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row2 = bodySheet.createRow(2);
            //设置行高
            row2.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row2, 7, bodyStyle(workbook, "微软雅黑", 10));
            XSSFCellStyle styleRise1 = workbook.createCellStyle();
            //创建字体 微软雅黑
            XSSFFont fontRise1 = workbook.createFont();
            fontRise1.setFontName("微软雅黑");
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
            createRowAndCell(row5_4, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, first, 0, 6);
            row5_4.getCell(0).setCellValue("Screen Configuation");
            row5_4.getCell(0).setCellStyle(titleStyleMain(workbook));
            XSSFRow row5_5 = bodySheet.createRow(first + 1);
            //设置行高
            row5_5.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(row5_5, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            if (panelDetailsList.get(i).getModule().getDensity() != null) {
                row7.getCell(5)
                        .setCellValue(panelDetailsList.get(i).getModule().getDensity().doubleValue());
            }

            //第9行设置值，箱体的配置信息
            XSSFRow row8 = bodySheet.createRow(firstRow + 2);
            //设置行高
            row8.setHeightInPoints(20);
            //创建单元格
            setRowAndCell(workbook, bodySheet, row8, (firstRow + 2));
            row8.getCell(0).setCellValue("LED Lamp");
            row8.getCell(3).setCellValue("Pixel Configuration");
            row8.getCell(5).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());

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
                createRowAndCell(row9, 7, bodyStyle(workbook, "微软雅黑", 10));
                //设定合并单元格的区域
                collapse(bodySheet, firstRow + 3 + 6 * j, 1, 6);
                if (splicingBoxs == 1) {
                    row9.getCell(1).setCellValue("Stqndard Panel");
                } else {
                    row9.getCell(1).setCellValue("Stqndard Panel" + (j + 1));
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
                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
                }

                //第13行设置值，
                XSSFRow row12_1 = bodySheet.createRow(firstRow + 7 + 6 * j);
                setRowAndCell(workbook, bodySheet, row12_1, (firstRow + 7 + 6 * j));
                row12_1.getCell(0).setCellValue("Certificate");

                //Certificate
                row12_1.getCell(2).setCellValue("CE");
                row12_1.getCell(3).setCellValue("Structure");
                //Structure
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
            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 3 + panelNum, 0, 6);
            row15.getCell(0).setCellValue("Display Data");
            row15.getCell(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow row16 = bodySheet.createRow(firstRow + 4 + panelNum);
            //设置行高
            row16.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "微软雅黑", 10));

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
                //第17行设置值，箱体数量
                XSSFRow row17_panel = bodySheet.createRow(firstRow + 5 + panelNum + j);
                setRowAndCell3(workbook, bodySheet, row17_panel, (firstRow + 5 + panelNum + j));
                row17_panel.getCell(0).setCellValue("Panel Quantity(pcs)" + (j + 1) + "(pcs)");
                row17_panel.getCell(2).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
                row17_panel.getCell(2).setCellStyle(styleDataFormat(workbook));
                row17_panel.getCell(3).setCellValue(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
                row17_panel.getCell(3).setCellStyle(styleDataFormat(workbook));
                row17_panel.getCell(5)
                        .setCellFormula("C" + (firstRow + 6 + panelNum + j) + "*D" + (firstRow + 6 + panelNum + j));
                row17_panel.getCell(5)
                        .setCellStyle(styleDataFormat(workbook));

                weight = weight.add(new BigDecimal(
                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
                                .get(i).getSplicingPanelsDto().get(j).getLcount())
                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
            }
            int boxNum = panelNum + splicingPanelsSize;

            for (int j = 0; j < splicingPanelsSize; j++) {
                //第18行设置 屏体面积(m)
                XSSFRow row18_new = bodySheet.createRow(firstRow + 5 + boxNum + j);
                setRowAndCell3(workbook, bodySheet, row18_new, (firstRow + 5 + boxNum + j));
                if (offerVo.getOffer().getSizeUnit() == 2) {
                    row18_new.getCell(0).setCellValue(msg.get(1) + "(ft)");
                } else {
                    row18_new.getCell(0).setCellValue(msg.get(1) + "(m)");
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
            row19.getCell(0).setCellValue("Screen Area Dimension(m)");
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
            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 9 + splicingPanels, 0, 1);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 3, 4);
            collapse(bodySheet, firstRow + 9 + splicingPanels, 5, 6);
            //标准平均功率（Kw ）
            row21.getCell(0).setCellValue("Total Average Power(watts)");
            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
            row25.getCell(1).setCellValue("Brightness Control");
            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());

            //第28行设置值，色温
            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 10);
            row26.getCell(1).setCellValue("Gray scale ");
            row26.getCell(2).setCellValue("6500K");

            //第29行设置值，刷新率
            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 10);
            row27.getCell(1).setCellValue("Refresh frequency");
            row27.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");

            //第34行设置值,对比度
            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 10);
            row28.getCell(1).setCellValue("Driving mode");
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
            row30.getCell(1).setCellValue("Blind spot rate");
            row30.getCell(2).setCellValue("＜1/10000");

            //第34行设置值,使用寿命（50%亮度）
            XSSFRow row31 = bodySheet.createRow(firstRow + 21 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row31, (firstRow + 21 + splicingPanels), 10);
            row31.getCell(1).setCellValue("Lifetime at 50% brightness");
            row31.getCell(2).setCellValue("100000 hours");

            //第34行设置值，防护等级
            XSSFRow row32 = bodySheet.createRow(firstRow + 22 + splicingPanels);
            setRowAndCell1(workbook, bodySheet, row32, (firstRow + 22 + splicingPanels), 10);
            row32.getCell(1).setCellValue("Ingress Protection");
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
            row38.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");

            //第43行设置值，屏体价格
            XSSFRow row39 = bodySheet.createRow(firstRow + 27 + splicingPanels);
            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 27 + splicingPanels, 0, 6);
            row39.getCell(0).setCellValue("QUOTATION");
            row39.getCell(0).setCellStyle(titleStyleMain(workbook));

            //第43行设置值，屏体价格
            XSSFRow row40 = bodySheet.createRow(firstRow + 28 + splicingPanels);
            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "微软雅黑", 10));
            //第43行设置值，屏体价格
            XSSFRow row41 = bodySheet.createRow(firstRow + 29 + splicingPanels);
            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "微软雅黑", 10));
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
            XSSFRow row44 = bodySheet.createRow(firstRow + 31 + splicingPanels);
            //设置行高
            row44.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
            row44.getCell(0).setCellValue("Receiving Card");
            row44.getCell(2).setCellValue("Nova A8S");
            row44.getCell(3).setCellValue("1.00 ");
            row44.getCell(4).setCellValue("pc");

            //第45行设置值，屏体价格
            XSSFRow row45 = bodySheet.createRow(firstRow + 32 + splicingPanels);
            //设置行高
            row43.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, firstRow + 32 + splicingPanels, 0, 1);
            row45.getCell(0).setCellValue("LED Software");
            row45.getCell(2).setCellValue("NOVA STUDIO");
            row45.getCell(5).setCellValue("");
            row45.getCell(6).setCellValue("");

            //
            XSSFRow row46_46 = bodySheet.createRow(firstRow + 33 + splicingPanels);
            //创建单元格
            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + standardLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
                    cells.get(3)
                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getUnit());
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getUnit());
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
                        bodyStyle(workbook, "微软雅黑", 10));
                //设定合并单元格的区域
                collapse(bodySheet, index + totalLength, 1, 6);
                cells1_free.get(0).setCellStyle(styleColor);
                cells1_free.get(1).setCellValue("Spare parts--free");
                cells1_free.get(1).setCellStyle(style);
            }

            //免费备件信息循环插入
            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
                for (int j = 0; j < panelDetailsList.get(i).getFreeDetailList().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + 1);
                    //设置行高
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + +freeLength + 1, 0, 1);
                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getType());
                    cells.get(2)
                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getUnit());
                    cells.get(5).setCellValue(msg.get(3));
                    cells.get(6).setCellValue(0);
                }
            }

            int totalLengthCount = totalLength + freeLength + selfFreeLength + 1;

            XSSFRow transferRowTitle = bodySheet.createRow(index + totalLengthCount);
            transferRowTitle.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells1_1 = createRowAndCell(transferRowTitle, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            //设定合并单元格的区域
            collapse(bodySheet, index + totalLengthCount, 1, 6);
            cells1_1.get(0).setCellStyle(styleColor);
            cells1_1.get(1).setCellValue("Package");
            cells1_1.get(1).setCellStyle(style);

            //服务列表长度
            int serviceLength = 0;
            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
                serviceLength = offerVo.getServiceListDto().size();
                //服务费用
                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLengthCount + 1);
                    //设置行高
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
                    //设定合并单元格的区域
                    collapse(bodySheet, index + j + totalLengthCount + 1, 0, 1);
                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
                    cells.get(5).setCellValue(format(
                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
                                    .getServiceDiscount().doubleValue() / 100));
                    cells.get(6).setCellType(CELL_TYPE_FORMULA);
                    cells.get(6).setCellFormula(
                            "D" + (index + 2 + j + totalLengthCount) + "*F" + (index + 2 + j + totalLengthCount));
                    cells.get(6).setCellStyle(styleDataFormat(workbook));
                }
            }

            int totalService = index + totalLengthCount + serviceLength + 1;
            List<TransportPackage> transportPackageList = offerVo.getTransport();
            if (transportPackageList.size() > 0) {
                //运输费用
                for (int j = 0; j < transportPackageList.size(); j++) {
                    //创建行
                    XSSFRow autoRow = bodySheet.createRow(j + totalService);
                    //设置行高
                    autoRow.setHeightInPoints(20);
                    //创建单元格
                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                //创建行
                XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size());
                //设置行高
                autoRowTransfer.setHeightInPoints(20);
                //创建单元格
                List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "微软雅黑", 10));
                //设定合并单元格的区域
                collapse(bodySheet, totalService + transportPackageList.size(), 0, 1);
                cells.get(0).setCellValue("EST.Shipping");
                cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "0" : String.valueOf(offerVo.getTransfer().getCost()));
                cells.get(4).setCellValue("0");
                cells.get(5).setCellValue(1);
                cells.get(6).setCellType(CELL_TYPE_FORMULA);
                cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 1) + "*F" + (totalService + transportPackageList.size() + 1));
                cells.get(6).setCellStyle(styleDataFormat(workbook));
                totalService = totalService + 1;
            }

            XSSFRow transferRow6 = bodySheet.createRow(totalService + transportPackageList.size());
            transferRow6.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells6_1 = createRowAndCell(transferRow6, 7, bodyStyle(workbook, "微软雅黑", 10));
            transferRow6.setHeightInPoints(20);
            collapse(bodySheet, totalService + transportPackageList.size(), 0, 4);
            collapse(bodySheet, totalService + transportPackageList.size(), 5, 6);
            XSSFRichTextString val6 = new XSSFRichTextString(
                    "(" + offerVo.getTransfer().getTrade()==null ? "":offerVo.getTransfer().getTrade()  + "  Shenzhen  " + myOffer.getMoneyUnit() + ") Total:");
            transferRow6.getCell(0).setCellValue(val6);

            transferRow6.getCell(5).setCellFormula(
                    "SUM(G" + (20 + splicingPanels) + ":G" + (totalService + transportPackageList.size() + 1) + ")");
            transferRow6.getCell(0).setCellStyle(style);
            transferRow6.getCell(5).setCellStyle(style);


            int totalSize = totalService + transportPackageList.size();

            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 4);
            transferRow7.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells7_1 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "微软雅黑", 10));
            transferRow7.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 4, 0, 6);

            // 样式对象 
            XSSFCellStyle style2 = workbook.createCellStyle();
            //设置垂直居中
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
            Font font = workbook.createFont();
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);
            style2.setFont(font);

            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
            transferRow8.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells8_1 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "微软雅黑", 10));
            transferRow8.setHeightInPoints(20);
            collapse(bodySheet, totalSize + 5, 0, 6);
            XSSFRichTextString val8 = new XSSFRichTextString("Remarks:");
            transferRow8.getCell(0).setCellValue(val8);
            transferRow8.getCell(0).setCellStyle(style2);

            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
            transferRow9.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "微软雅黑", 10));
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
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 7, 0, 6);
            StringBuffer packageStr = new StringBuffer();
            transportPackageList.forEach(item -> {
                packageStr.append(item.getPackages()).append(",");
            });

            transferRow10.getCell(0).setCellValue("2. Packing: Dolly " + (transportPackageList.size() > 0 ? packageStr : " "));
            transferRow10.getCell(0).setCellStyle(style2);

            XSSFRow transferRow11 = bodySheet.createRow(totalSize + 8);
            transferRow11.setHeightInPoints(40);
            //创建单元格
            List<XSSFCell> cells11 = createRowAndCell(transferRow11, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 8, 0, 6);
            cells11.get(0).setCellValue("3. Payment method:  TBC  ( We are checking the maximun credit amount of Alabama now. The current credit we are using cannot cover this order if there is 80% paid after the shipment. New credit amount is under application).");
            style2.setWrapText(true);
            cells11.get(0).setCellStyle(style2);

            XSSFRow transferRow12 = bodySheet.createRow(totalSize + 9);
            transferRow12.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells12 = createRowAndCell(transferRow12, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 9, 0, 6);
            transferRow12.getCell(0).setCellValue("4. Production lead time: " + myOffer.getWaitingDate() + " days.");
            transferRow12.getCell(0).setCellStyle(style2);

            XSSFRow transferRow13 = bodySheet.createRow(totalSize + 10);
            transferRow10.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells13 = createRowAndCell(transferRow13, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 10, 0, 6);
            transferRow13.getCell(0).setCellValue("5. Quality Warranty Period: two years from delivery date  ");
            transferRow13.getCell(0).setCellStyle(style2);

            XSSFRow transferRow14 = bodySheet.createRow(totalSize + 11);
            transferRow14.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells14 = createRowAndCell(transferRow14, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 11, 0, 6);
            cells14.get(0).setCellValue(
                    "6. Free trainning: 7 days on-site complete technical trainning in our Shenzhen factory");
            cells14.get(0).setCellStyle(style2);

            XSSFRow transferRow15 = bodySheet.createRow(totalSize + 12);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells15R = createRowAndCell(transferRow15, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 12, 0, 6);
            cells15R.get(0).setCellValue(
                    "7. Installation Guidance and after-sales premium service package: optional ");
            cells15R.get(0).setCellStyle(style2);

            //插入图片
            XSSFRow transferRow16 = bodySheet.createRow(totalSize + 13);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(transferRow16, 7,
                    bodyStyle(workbook, "微软雅黑", 10));

            XSSFRow transferRow17 = bodySheet.createRow(totalSize + 14);
            transferRow17.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(transferRow17, 7,
                    bodyStyle(workbook, "微软雅黑", 10));

            XSSFRow transferRow18 = bodySheet.createRow(totalSize + 15);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(transferRow18, 7,
                    bodyStyle(workbook, "微软雅黑", 10));

            XSSFRow transferRow19 = bodySheet.createRow(totalSize + 16);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            createRowAndCell(transferRow19, 7,
                    bodyStyle(workbook, "微软雅黑", 10));

            String fileName = filepath + "absen.png";

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
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short) 0, totalSize + 13, (short) 2, totalSize + 16);
//            anchor.setAnchorType(3);
//            ClientAnchor anchor = helper.createClientAnchor();

            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.resize();


            XSSFRow transferRow21 = bodySheet.createRow(totalSize + 18);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells21R = createRowAndCell(transferRow21, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 18, 0, 6);
            cells21R.get(0).setCellValue(
                    "Shenzhen Absen Optoelectronic Co., LTD.");
            cells21R.get(0).setCellStyle(titleStyleMain(workbook));

            XSSFRow transferRow22 = bodySheet.createRow(totalSize + 19);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells22R = createRowAndCell(transferRow22, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 19, 0, 6);
            cells22R.get(0).setCellValue(
                    "Add: 18-20F building 3A, Cloud Park, Bantian, Longgang District, Shenzhen 518129, P.R. China");
            cells22R.get(0).setCellStyle(style2);

            XSSFRow transferRow23 = bodySheet.createRow(totalSize + 20);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells23R = createRowAndCell(transferRow23, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 20, 0, 6);
            cells23R.get(0).setCellValue(
                    "E-mail: absen@absen.com; Http:// www.absen.com");
            cells23R.get(0).setCellStyle(style2);
            XSSFRow transferRow24 = bodySheet.createRow(totalSize + 21);
            transferRow15.setHeightInPoints(20);
            //创建单元格
            List<XSSFCell> cells24R = createRowAndCell(transferRow24, 7,
                    bodyStyle(workbook, "微软雅黑", 10));
            collapse(bodySheet, totalSize + 21, 0, 6);
            cells24R.get(0).setCellValue(
                    "Tel: + 86-755-89747399  Fax: + 86-755-89747599");
            cells24R.get(0).setCellStyle(style2);
        }
        //重新计算excel里面的公式
        reCalculating(workbook);
    }

    /**
     * 其他区域模板读写方法  20180919 变更
     */
//    private void templateEUNew(List<PanelDetails> panelDetailsList, XSSFWorkbook workbook,
//                               MyOfferDto myOffer, List<String> msg, OfferVo offerVo, XSSFSheet
//                                       sheet, List<OfferPanelsDto> offerPanelsList,
//                               String filepath, User userMsg) {
//        // 样式对象 
//        XSSFCellStyle styleColor = workbook.createCellStyle();
//        //单元格背景色
//        styleColor.setFillForegroundColor(new XSSFColor(new java.awt.Color(245, 131, 32)));
//        styleColor.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//
//        for (int i = 0; i < panelDetailsList.size(); i++) {
//
//            //总重量
//            BigDecimal weight = new BigDecimal(0);
//            //总面积
//            BigDecimal area = new BigDecimal(0);
//            // 箱体 宽数
//            int wNum = 0;
//            // 箱体 高数
//            int lNum = 0;
//            //平均功耗
//            BigDecimal avgPower = new BigDecimal(0);
//
//            BigDecimal maxPower = new BigDecimal(0);
//            //读取sheet
//            XSSFSheet bodySheet = workbook.getSheetAt(i);
//
//            //插入图片
//            if (i > 0) {
//                String fileName = filepath + "absen.jpeg";
//
//                byte[] bytes = new byte[0];
//                try {
//                    InputStream is = new FileInputStream(fileName);
//                    bytes = IOUtils.toByteArray(is);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                int pictureIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
//
//                CreationHelper helper = workbook.getCreationHelper();
//                Drawing drawing = bodySheet.createDrawingPatriarch();
//                ClientAnchor anchor = helper.createClientAnchor();
//                anchor.setCol1(0);
//                anchor.setRow1(0);
//                Picture pict = drawing.createPicture(anchor, pictureIdx);
//                pict.resize();
//            }
//            //生成单元格样式
//            XSSFCellStyle style = titleStyle(workbook);
//
//
//            XSSFCellStyle styleRise = workbook.createCellStyle();
//            //创建字体 微软雅黑
//            XSSFFont fontRise = workbook.createFont();
//            fontRise.setFontName("微软雅黑");
//            fontRise.setFontHeightInPoints((short) 12);
//            fontRise.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
//            styleRise.setFont(fontRise);
//            //设置垂直居中
//            styleRise.setVerticalAlignment(VerticalAlignment.CENTER);
//            styleRise.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
//
//            XSSFRow row2 = bodySheet.createRow(2);
//            //设置行高
//            row2.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells2 = createRowAndCell(row2, 7, bodyStyle(workbook, "微软雅黑", 10));
//            row2.getCell(0).setCellValue("Date:" + panelDetailsList.get(i).getParams().getCreateTime());
//            row2.getCell(0).setCellStyle(styleRise);
//            XSSFCellStyle styleRise1 = workbook.createCellStyle();
//            //创建字体 微软雅黑
//            XSSFFont fontRise1 = workbook.createFont();
//            fontRise1.setFontName("微软雅黑");
//            fontRise1.setFontHeightInPoints((short) 12);
//            fontRise1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
//            styleRise1.setFont(fontRise1);
//            //设置垂直居中
//            styleRise1.setVerticalAlignment(VerticalAlignment.CENTER);
//            styleRise1.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
//            row2.getCell(6).setCellValue("Quotation Number:" + offerVo.getOffer().getNum());
//            row2.getCell(6).setCellStyle(styleRise1);
//
//            //
//            XSSFRow row5_1 = bodySheet.createRow(3);
//            //设置行高
//            row5_1.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells5_1 = createRowAndCell(row5_1, 7, bodyStyle(workbook, "微软雅黑", 10));
//            row5_1.getCell(0).setCellValue("To: " + myOffer.getCustomerName());
//            row5_1.getCell(0).setCellStyle(styleRise);
//
//
//            //
//            XSSFRow row5_2 = bodySheet.createRow(4);
//            //设置行高
//            row5_2.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells5_2 = createRowAndCell(row5_2, 7, bodyStyle(workbook, "微软雅黑", 10));
//            row5_2.getCell(0).setCellValue("ATTN: ");
//            row5_2.getCell(0).setCellStyle(styleRise);
//            //
//            XSSFRow row5_3 = bodySheet.createRow(5);
//            //设置行高
//            row5_3.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(row5_3, 7, bodyStyle(workbook, "微软雅黑", 10));
//            row5_3.getCell(0).setCellValue("From: " + userMsg.getName());
//            row5_3.getCell(0).setCellStyle(styleRise);
//
//            int quotation = 21;
//            XSSFRow row1 = bodySheet.createRow(quotation);
//            XSSFRow row1_1 = bodySheet.createRow(quotation + 1);
//            XSSFRow row1_2 = bodySheet.createRow(quotation + 2);
//            XSSFRow row1_3 = bodySheet.createRow(quotation + 3);
//            //设置行高
//            row1.setHeightInPoints(20);
//            // 样式对象 
//            XSSFCellStyle styleQuotation = workbook.createCellStyle();
//            //创建字体 微软雅黑
//            XSSFFont fontQuotation = workbook.createFont();
//            fontQuotation.setFontName("微软雅黑");
//            fontQuotation.setFontHeightInPoints((short) 20);
//            fontQuotation.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
//            styleQuotation.setFont(fontQuotation);
//            //设置垂直居中
//            styleQuotation.setVerticalAlignment(VerticalAlignment.CENTER);
//            styleQuotation.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
//            styleQuotation.setWrapText(true);
//            //创建单元格
//            createRowAndCell(row1, 7, bodyStyle(workbook, "微软雅黑", 10));
//            createRowAndCell(row1_1, 7, bodyStyle(workbook, "微软雅黑", 10));
//            createRowAndCell(row1_2, 7, bodyStyle(workbook, "微软雅黑", 10));
//            createRowAndCell(row1_3, 7, bodyStyle(workbook, "微软雅黑", 10));
//            collapseAuto(bodySheet, quotation, quotation + 3, 0, 3);
//            row1.getCell(0).setCellValue(
//                    "Quotation of  Absen-" + offerPanelsList.get(i).getSeriesName() + " LED Display");
//            row1.getCell(0).setCellStyle(styleQuotation);
//
//
//            int first = 53;
//            XSSFRow row5_4 = bodySheet.createRow(first);
//            //设置行高
//            row5_4.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(row5_4, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, first, 0, 6);
//            row5_4.getCell(0).setCellValue("Screen Configuation");
//            row5_4.getCell(0).setCellStyle(titleStyleMain(workbook));
//            XSSFRow row5_5 = bodySheet.createRow(first + 1);
//            //设置行高
//            row5_5.setHeightInPoints(20);
//            //创建单元格
//            createRowAndCell(row5_5, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, first + 1, 1, 6);
//
//            row5_5.getCell(0).setCellStyle(styleColor);
//            row5_5.getCell(1).setCellValue("Module");
//            row5_5.getCell(1).setCellStyle(style);
//
//            int firstRow = first + 2;
//
//            XSSFRow row6 = bodySheet.createRow(firstRow);
//            //设置行高
//            row6.setHeightInPoints(20);
//            //创建单元格
//            setRowAndCell(workbook, bodySheet, row6, firstRow);
//            row6.getCell(0).setCellValue("Dimensions (w x h) (mm)");
//
//            //尺寸信息
//            if (panelDetailsList.get(i).getModule().getWidth() != null
//                    && panelDetailsList.get(i).getModule().getHeight() != null) {
//                row6.getCell(2).setCellValue(
//                        panelDetailsList.get(i).getModule().getWidth().intValue() + " X " + panelDetailsList
//                                .get(i).getModule().getHeight().intValue());
//            }
//
//            row6.getCell(3).setCellValue("Resolution (w x h)");
//            //分辨率
//            if (panelDetailsList.get(i).getModule().getTransverse() != null
//                    && panelDetailsList.get(i).getModule().getPortrait() != null) {
//                row6.getCell(5).setCellValue(
//                        panelDetailsList.get(i).getModule().getTransverse().intValue() + " X "
//                                + panelDetailsList.get(i).getModule().getPortrait().intValue());
//            }
//
//            //第8行设置值，模组的点间距 和 像素点（点数/㎡）
//            XSSFRow row7 = bodySheet.createRow(firstRow + 1);
//            //设置行高
//            row7.setHeightInPoints(20);
//            //创建单元格
//            setRowAndCell(workbook, bodySheet, row7, (firstRow + 1));
//            row7.getCell(0).setCellValue("Pixel pitch (mm)");
//            //模组的点间距
//            if (panelDetailsList.get(i).getModule().getPitch() != null) {
//                row7.getCell(2).setCellValue(panelDetailsList.get(i).getModule().getPitch().doubleValue());
//            }
//            row7.getCell(3).setCellValue("Pixel Density (pixels/m2)");
//            //像素点（点数/㎡）
//            if (panelDetailsList.get(i).getModule().getDensity() != null) {
//                row7.getCell(5)
//                        .setCellValue(panelDetailsList.get(i).getModule().getDensity().doubleValue());
//            }
//
//            //第9行设置值，箱体的配置信息
//            XSSFRow row8 = bodySheet.createRow(firstRow + 2);
//            //设置行高
//            row8.setHeightInPoints(20);
//            //创建单元格
//            setRowAndCell(workbook, bodySheet, row8, (firstRow + 2));
//            row8.getCell(0).setCellValue("LED Lamp");
//            row8.getCell(3).setCellValue("Pixel Configuration");
//            row8.getCell(5).setCellValue(panelDetailsList.get(i).getModule().getConfiguration());
//
//            List<Box> boxList = Lists.newArrayList();
//            if (panelDetailsList.get(i).getSplicingPanelsDto().size() > 0) {
//                for (OfferPanelsDto offerPanels : panelDetailsList.get(i).getSplicingPanelsDto()) {
//                    boxList.add(offerPanels.getBox());
//                }
//            }
//
//            //拼屏数量
//            int splicingBoxs = boxList.size();
//
//            for (int j = 0; j < splicingBoxs; j++) {
//                //第10行设置值，箱体的尺寸 模组个数
//                XSSFRow row9 = bodySheet.createRow(firstRow + 3 + 6 * j);
//                //设置行高
//                row9.setHeightInPoints(20);
//                //创建单元格
//                createRowAndCell(row9, 7, bodyStyle(workbook, "微软雅黑", 10));
//                //设定合并单元格的区域
//                collapse(bodySheet, firstRow + 3 + 6 * j, 1, 6);
//                if (splicingBoxs == 1) {
//                    row9.getCell(1).setCellValue("Stqndard Panel");
//                } else {
//                    row9.getCell(1).setCellValue("Stqndard Panel" + (j + 1));
//                }
//                row9.getCell(0).setCellStyle(styleColor);
//                row9.getCell(1).setCellStyle(style);
//
//                //第11行设置值，箱体的尺寸 模组个数
//                XSSFRow row10 = bodySheet.createRow(firstRow + 4 + 6 * j);
//                setRowAndCell(workbook, bodySheet, row10, (firstRow + 4 + 6 * j));
//                row10.getCell(0).setCellValue("Dimensions(w x h x d)(mm)");
//                row10.getCell(3).setCellValue("Module Quantity ");
//                //箱体的尺寸
//                if (boxList.get(j).getWidth() != null && boxList.get(j).getHeight() != null
//                        && boxList.get(j).getThickness() != null) {
//                    row10.getCell(2).setCellValue(
//                            boxList.get(j).getWidth().intValue() + " x " + boxList.get(j).getHeight().intValue()
//                                    + " x " + boxList.get(j).getThickness().intValue());
//                }
//                //模组个数
//                if (boxList.get(j).getWidth() != null) {
//                    row10.getCell(5).setCellValue(
//                            boxList.get(j).getTransverseCount().intValue() * boxList.get(j).getPortraitCount()
//                                    .intValue());
//                }
//
//                //第12行设置值，分辨率 (w x h)	 实像素点数
//                XSSFRow row11 = bodySheet.createRow(firstRow + 5 + 6 * j);
//                setRowAndCell(workbook, bodySheet, row11, (firstRow + 5 + 6 * j));
//                row11.getCell(0).setCellValue("Physical Resolution (w x h)");
//                row11.getCell(3).setCellValue("Physical Pixels (total)");
//                //分辨率
//                if (boxList.get(j).getTransversePix() != null && boxList.get(j).getPortraitPix() != null) {
//                    row11.getCell(2).setCellValue(
//                            boxList.get(j).getTransversePix().intValue() + " x " + boxList.get(j).getPortraitPix()
//                                    .intValue());
//                }
//                //实像素点数
//                if (boxList.get(j).getWidth() != null) {
//                    row11.getCell(5).setCellValue(
//                            boxList.get(j).getTransversePix().intValue() * boxList.get(j).getPortraitPix()
//                                    .intValue());
//                }
//
//                //第13行设置值，箱体的重量 材质
//                XSSFRow row12 = bodySheet.createRow(firstRow + 6 + 6 * j);
//                setRowAndCell(workbook, bodySheet, row12, (firstRow + 6 + 6 * j));
//                row12.getCell(0).setCellValue("Weight/Panel (kg)");
//                row12.getCell(3).setCellValue("Material");
//                //箱体的重量
//                if (boxList.get(j).getWeight() != null) {
//                    row12.getCell(2).setCellValue(boxList.get(j).getWeight().doubleValue());
//                }
//                //材质
//                if (boxList.get(j).getBoxType() != null) {
//                    row12.getCell(5).setCellValue(boxList.get(j).getBoxType());
//                }
//
//                //第13行设置值，
//                XSSFRow row12_1 = bodySheet.createRow(firstRow + 7 + 6 * j);
//                setRowAndCell(workbook, bodySheet, row12_1, (firstRow + 7 + 6 * j));
//                row12_1.getCell(0).setCellValue("Certificate");
//
//                //Certificate
//                row12_1.getCell(2).setCellValue("CE");
//                row12_1.getCell(3).setCellValue("Structure");
//                //Structure
//                row12_1.getCell(5).setCellValue("Rental, Rear maintenance");
//
//                //第14行设置值， 单个屏的功率  标准平均功率（Kw ） 标准最大功率（Kw）
//                //标准平均功率
//                XSSFRow row13 = bodySheet.createRow(firstRow + 8 + 6 * j);
//                setRowAndCell(workbook, bodySheet, row13, (firstRow + 8 + 6 * j));
//                row13.getCell(0).setCellValue("Average Power/sqm(watts)");
//                row13.getCell(3).setCellValue("Max Power/sqm(watts)");
//                if (panelDetailsList.get(i).getParams().getPowerAvg() != null) {
//                    row13.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getPowerAvg());
//                }
//                //标准最大功率
//                if (panelDetailsList.get(i).getParams().getPowerMax() != null) {
//                    row13.getCell(5).setCellValue(panelDetailsList.get(i).getParams().getPowerMax());
//                }
//            }
//
//            //拼接箱体 占用行数
//            int panelNum = splicingBoxs * 6;
//
//            XSSFRow row15 = bodySheet.createRow(firstRow + 3 + panelNum);
//            //设置行高
//            row15.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells15 = createRowAndCell(row15, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 3 + panelNum, 0, 6);
//            row15.getCell(0).setCellValue("Display Data");
//            row15.getCell(0).setCellStyle(titleStyleMain(workbook));
//
//            XSSFRow row16 = bodySheet.createRow(firstRow + 4 + panelNum);
//            //设置行高
//            row16.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells16 = createRowAndCell(row16, 7, bodyStyle(workbook, "微软雅黑", 10));
//
//            //设定合并单元格的区域
////            collapse(bodySheet, firstRow + 4 + panelNum, 1, 1);
//            collapse(bodySheet, firstRow + 4 + panelNum, 3, 4);
//            collapse(bodySheet, firstRow + 4 + panelNum, 5, 6);
//            row16.getCell(1).setCellValue("ITEM");
//            row16.getCell(2).setCellValue("Width ");
//            row16.getCell(3).setCellValue("Height");
//            row16.getCell(5).setCellValue("Total");
//
//            row16.getCell(0).setCellStyle(styleColor);
//            row16.getCell(1).setCellStyle(style);
//            row16.getCell(2).setCellStyle(style);
//            row16.getCell(3).setCellStyle(style);
//            row16.getCell(5).setCellStyle(style);
//
//            int splicingPanelsSize = panelDetailsList.get(i).getSplicingPanelsDto().size();
//
//            for (int j = 0; j < splicingPanelsSize; j++) {
//                //第17行设置值，箱体数量
//                XSSFRow row17_panel = bodySheet.createRow(firstRow + 5 + panelNum + j);
//                setRowAndCell3(workbook, bodySheet, row17_panel, (firstRow + 5 + panelNum + j));
//                row17_panel.getCell(0).setCellValue("Panel Quantity(pcs)" + (j + 1) + "(pcs)");
//                row17_panel.getCell(2).setCellValue(
//                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() : 0);
//                row17_panel.getCell(3).setCellValue(
//                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount() : 0);
//                row17_panel.getCell(5)
//                        .setCellFormula("C" + (firstRow + 6 + panelNum + j) + "*D" + (firstRow + 6 + panelNum + j));
//
//                weight = weight.add(new BigDecimal(
//                        panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount() * panelDetailsList
//                                .get(i).getSplicingPanelsDto().get(j).getLcount())
//                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getBox().getWeight()));
//                wNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getWcount();
//                lNum += panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLcount();
//            }
//            int boxNum = panelNum + splicingPanelsSize;
//
//            for (int j = 0; j < splicingPanelsSize; j++) {
//                //第18行设置 屏体面积(m)
//                XSSFRow row18_new = bodySheet.createRow(firstRow + 5 + boxNum + j);
//                setRowAndCell3(workbook, bodySheet, row18_new, (firstRow + 5 + boxNum + j));
//                if (offerVo.getOffer().getSizeUnit() == 2) {
//                    row18_new.getCell(0).setCellValue(msg.get(1) + "(ft)");
//                } else {
//                    row18_new.getCell(0).setCellValue(msg.get(1) + "(m)");
//                }
//                row18_new.getCell(2).setCellValue(
//                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                                : new BigDecimal(0)).toString());
//                row18_new.getCell(3).setCellValue(
//                        (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null
//                                ? panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()
//                                : new BigDecimal(0)).toString());
//                //计算面积
//                if (panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal() != null
//                        && panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal() != null) {
//                    row18_new.getCell(5).setCellFormula("C" + (firstRow + 6 + boxNum + j) + "*D" + (firstRow + 6 + boxNum + j));
//                    area = area.add(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                            .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal()));
//
//                }
//
//                avgPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
//                        .multiply(new BigDecimal(
//                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerAvg()))
//                        .add(avgPower);
//                maxPower = panelDetailsList.get(i).getSplicingPanelsDto().get(j).getHorizontal()
//                        .multiply(panelDetailsList.get(i).getSplicingPanelsDto().get(j).getLongitudinal())
//                        .multiply(new BigDecimal(
//                                panelDetailsList.get(i).getSplicingPanelsDto().get(j).getParams().getPowerMax()))
//                        .add(maxPower);
//            }
//            int splicingPanels = boxNum + splicingPanelsSize - 2;
//            //第19行设置值，
//            XSSFRow row19 = bodySheet.createRow(firstRow + 7 + splicingPanels);
//            setRowAndCell3(workbook, bodySheet, row19, (firstRow + 7 + splicingPanels));
//            row19.getCell(0).setCellValue("Screen Area Dimension(m)");
//            row19.getCell(2)
//                    .setCellValue(panelDetailsList.get(i).getModule().getTransverse().intValue() * wNum);
//            row19.getCell(3)
//                    .setCellValue(panelDetailsList.get(i).getModule().getPortrait().intValue() * lNum);
//            row19.getCell(5).setCellFormula("C" + (firstRow + 8 + splicingPanels) + "*D" + (firstRow + 8 + splicingPanels));
//
//            //第20行设置值，显示屏重量(kg)
//            XSSFRow row20 = bodySheet.createRow(firstRow + 8 + splicingPanels);
//            //设置行高
//            row20.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells20 = createRowAndCell(row20, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 8 + splicingPanels, 0, 1);
//            collapse(bodySheet, firstRow + 8 + splicingPanels, 2, 6);
//            row20.getCell(0).setCellValue("Total Net Weight (kgs)");
//            row20.getCell(2).setCellValue(weight.toString());
//
//            //第21行设置值，标准平均功率（Kw ）	 标准最大功率（Kw）
//            XSSFRow row21 = bodySheet.createRow(firstRow + 9 + splicingPanels);
//            //设置行高
//            row20.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells21 = createRowAndCell(row21, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 9 + splicingPanels, 0, 1);
//            collapse(bodySheet, firstRow + 9 + splicingPanels, 3, 4);
//            collapse(bodySheet, firstRow + 9 + splicingPanels, 5, 6);
//            //标准平均功率（Kw ）
//            row21.getCell(0).setCellValue("Total Average Power(watts)");
//            row21.getCell(2).setCellValue(avgPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//            //标准最大功率（Kw）
//            row21.getCell(3).setCellValue("Total Max Power(watts)");
//            row21.getCell(5).setCellValue(maxPower.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//
//            //第22行设置值，亮度
//            XSSFRow row22_21 = bodySheet.createRow(firstRow + 10 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row22_21, (firstRow + 10 + splicingPanels), 12);
//            row22_21.getCell(0).setCellStyle(styleColor);
//            row22_21.getCell(1).setCellValue("Parameter");
//            row22_21.getCell(2).setCellValue("Value");
//            row22_21.getCell(1).setCellStyle(style);
//            row22_21.getCell(2).setCellStyle(style);
//
//            //第22行设置值，亮度
//            XSSFRow row22 = bodySheet.createRow(firstRow + 11 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row22, (firstRow + 11 + splicingPanels), 10);
//            row22.getCell(1).setCellValue("Brightness ");
//            row22.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getBrightness() != null ?
//                    panelDetailsList.get(i).getParams().getBrightness() + "cd/㎡" : "");
//
//            //第23行设置值，视角
//            XSSFRow row23 = bodySheet.createRow(firstRow + 12 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row23, (firstRow + 12 + splicingPanels), 10);
//            row23.getCell(1).setCellValue("Viewing Angle ");
//            row23.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getViewing());
//
//            //第24行设置值，最小可视距离
//            XSSFRow row24 = bodySheet.createRow(firstRow + 13 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row24, (firstRow + 13 + splicingPanels), 10);
//            row24.getCell(1).setCellValue("Minimum Viewing Distance");
//            if (panelDetailsList.get(i).getModule().getPitch() != null) {
//                row24.getCell(2)
//                        .setCellValue(panelDetailsList.get(i).getModule().getPitch().intValue() + " meters");
//            }
//            //第28行设置值，灰度
//            XSSFRow row25 = bodySheet.createRow(firstRow + 14 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row25, (firstRow + 14 + splicingPanels), 10);
//            row25.getCell(1).setCellValue("Brightness Control");
//            row25.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getGrayScale());
//
//            //第28行设置值，色温
//            XSSFRow row26 = bodySheet.createRow(firstRow + 15 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row26, (firstRow + 15 + splicingPanels), 10);
//            row26.getCell(1).setCellValue("Gray scale ");
//            row26.getCell(2).setCellValue("6500K");
//
//            //第29行设置值，刷新率
//            XSSFRow row27 = bodySheet.createRow(firstRow + 16 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row27, (firstRow + 16 + splicingPanels), 10);
//            row27.getCell(1).setCellValue("Refresh frequency");
//            row27.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getRefresh() != null ?
//                    panelDetailsList.get(i).getParams().getRefresh() + " Hertz" : "");
//
//            //第34行设置值,对比度
//            XSSFRow row28 = bodySheet.createRow(firstRow + 17 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row28, (firstRow + 17 + splicingPanels), 10);
//            row28.getCell(1).setCellValue("Driving mode");
//            row28.getCell(2).setCellValue(panelDetailsList.get(i).getParams().getContrastRatio());
//
//            //第34行设置值,
//            XSSFRow row29 = bodySheet.createRow(firstRow + 18 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row29, (firstRow + 18 + splicingPanels), 10);
//            row29.getCell(1).setCellValue("Input power frequency");
//            row29.getCell(2).setCellValue("50 or 60 Hertz");
//
//            //第34行设置值,工作电压
//            XSSFRow row29_1 = bodySheet.createRow(firstRow + 19 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row29_1, (firstRow + 19 + splicingPanels), 10);
//            row29_1.getCell(1).setCellValue("Input Voltage");
//            row29_1.getCell(2).setCellValue("110~240 Volt");
//
//            //第34行设置值,盲点率
//            XSSFRow row30 = bodySheet.createRow(firstRow + 20 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row30, (firstRow + 20 + splicingPanels), 10);
//            row30.getCell(1).setCellValue("Blind spot rate");
//            row30.getCell(2).setCellValue("＜1/10000");
//
//            //第34行设置值,使用寿命（50%亮度）
//            XSSFRow row31 = bodySheet.createRow(firstRow + 21 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row31, (firstRow + 21 + splicingPanels), 10);
//            row31.getCell(1).setCellValue("Lifetime at 50% brightness");
//            row31.getCell(2).setCellValue("100000 hours");
//
//            //第34行设置值，防护等级
//            XSSFRow row32 = bodySheet.createRow(firstRow + 22 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row32, (firstRow + 22 + splicingPanels), 10);
//            row32.getCell(1).setCellValue("Ingress Protection");
//            row32.getCell(2).setCellValue("Front IP65,  Rear IP54");
//            //第34行设置值，工作环境温度
//            XSSFRow row33 = bodySheet.createRow(firstRow + 23 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row33, (firstRow + 23 + splicingPanels), 10);
//            row33.getCell(1).setCellValue("Operating temperature");
//            row33.getCell(2).setCellValue(" ﹣10 ～﹢40 ℃");
//
//            //第34行设置值，工作环境湿度
//            XSSFRow row35 = bodySheet.createRow(firstRow + 24 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row35, (firstRow + 24 + splicingPanels), 10);
//            row35.getCell(1).setCellValue("Operating humidity ");
//            row35.getCell(2).setCellValue(" 10％ ～ 90％");
//
//            //第34行设置值，控制距离
//            XSSFRow row37 = bodySheet.createRow(firstRow + 25 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row37, (firstRow + 25 + splicingPanels), 10);
//            row37.getCell(1).setCellValue("Control distance");
//            row37.getCell(2).setCellValue("CAT5 cable:＜100 m; Single mode fiber:＜10 km");
//            //第34行设置值，操作系统平台
//            XSSFRow row38 = bodySheet.createRow(firstRow + 26 + splicingPanels);
//            setRowAndCell1(workbook, bodySheet, row38, (firstRow + 26 + splicingPanels), 10);
//            row38.getCell(1).setCellValue("Signal input format");
//            row38.getCell(2).setCellValue("AV, S-Video, VGA, DVI, YPbPr, HDMI, SDI");
//
//            //第43行设置值，屏体价格
//            XSSFRow row39 = bodySheet.createRow(firstRow + 27 + splicingPanels);
//            List<XSSFCell> cells39 = createRowAndCell(row39, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 27 + splicingPanels, 0, 6);
//            row39.getCell(0).setCellValue("QUOTATION");
//            row39.getCell(0).setCellStyle(titleStyleMain(workbook));
//
//            //第43行设置值，屏体价格
//            XSSFRow row40 = bodySheet.createRow(firstRow + 28 + splicingPanels);
//            List<XSSFCell> cells40 = createRowAndCell(row40, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //第43行设置值，屏体价格
//            XSSFRow row41 = bodySheet.createRow(firstRow + 29 + splicingPanels);
//            List<XSSFCell> cells41 = createRowAndCell(row41, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            row41.getCell(5).setCellValue("(" + myOffer.getMoneyUnit() + ")");
//            row41.getCell(5).setCellStyle(style);
//            row41.getCell(6).setCellValue("(" + myOffer.getMoneyUnit() + ")");
//            row41.getCell(6).setCellStyle(style);
//            row41.getCell(0).setCellStyle(styleColor);
//            //设定合并单元格的区域
//
//            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 1, 1);
//            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 2, 2);
//            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 3, 4);
////            collapseAuto(bodySheet, firstRow + 28 + splicingPanels, firstRow + 29 + splicingPanels, 5, 5);
//            row40.getCell(0).setCellStyle(styleColor);
//            row40.getCell(1).setCellValue("Item No.");
//            row40.getCell(2).setCellValue("Model No.");
//            row40.getCell(3).setCellValue("Qty.");
//            row40.getCell(5).setCellValue("Unit Price");
//            row40.getCell(6).setCellValue("Sub Total");
//
//            row40.getCell(1).setCellStyle(style);
//            row40.getCell(2).setCellStyle(style);
//            row40.getCell(3).setCellStyle(style);
//            row40.getCell(5).setCellStyle(style);
//            row40.getCell(6).setCellStyle(style);
//
//            //第43行设置值，屏体价格
//            XSSFRow row43 = bodySheet.createRow(firstRow + 30 + splicingPanels);
//            //设置行高
//            row43.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells43 = createRowAndCell(row43, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 30 + splicingPanels, 0, 1);
//            row43.getCell(0).setCellValue("LED screen");
//            row43.getCell(2).setCellValue(offerPanelsList.get(i).getSeriesName());
//
//
//            row43.getCell(3).setCellValue(String.valueOf(area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP)));
//            row43.getCell(4).setCellValue("sqm");
//            BigDecimal panelPrice = panelDetailsList.get(i).getOfferPanels().getPrice() == null ? new BigDecimal(0)
//                    : panelDetailsList.get(i).getOfferPanels().getPrice();
//            System.out.println("计算总价：" + area.multiply(new BigDecimal(panelDetailsList.get(i).getOfferPanels().getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(panelPrice));
//
//            row43.getCell(5).setCellValue(String.valueOf(panelPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
//
//            row43.getCell(6).setCellFormula("D" + (firstRow + 31 + splicingPanels) + "*F" + (firstRow + 31 + splicingPanels));
//            //第45行设置值，屏体价格
//            XSSFRow row44 = bodySheet.createRow(firstRow + 31 + splicingPanels);
//            //设置行高
//            row44.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells44 = createRowAndCell(row44, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 31 + splicingPanels, 0, 1);
//            row44.getCell(0).setCellValue("Receiving Card");
//            row44.getCell(2).setCellValue("Nova A8S");
//            row44.getCell(3).setCellValue("1.00 ");
//            row44.getCell(4).setCellValue("pc");
//
//            //第45行设置值，屏体价格
//            XSSFRow row45 = bodySheet.createRow(firstRow + 32 + splicingPanels);
//            //设置行高
//            row43.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells45 = createRowAndCell(row45, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 32 + splicingPanels, 0, 1);
//            row45.getCell(0).setCellValue("LED Software");
//            row45.getCell(2).setCellValue("NOVA STUDIO");
//            row45.getCell(5).setCellValue("0");
//            row45.getCell(6).setCellValue("0");
//
//            //
//            XSSFRow row46_46 = bodySheet.createRow(firstRow + 33 + splicingPanels);
//            //创建单元格
//            List<XSSFCell> cells46 = createRowAndCell(row46_46, 7, bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, firstRow + 33 + splicingPanels, 1, 6);
//            cells46.get(0).setCellStyle(styleColor);
//            cells46.get(1).setCellValue("Recommended spare parts");
//            cells46.get(1).setCellStyle(style);
//
//            //获取各种备件集合的长度
//            int standardLength = 0;
//            int selfStandardLength = 0;
//            int spareLength = 0;
//            int selfSpareLength = 0;
//            int freeLength = 0;
//            int selfFreeLength = 0;
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
//                standardLength = panelDetailsList.get(i).getStandardDetailList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
//                selfStandardLength = panelDetailsList.get(i).getSelfStandardList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
//                spareLength = panelDetailsList.get(i).getSpareDetailList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
//                selfSpareLength = panelDetailsList.get(i).getSelfSpareList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
//                freeLength = panelDetailsList.get(i).getFreeDetailList().size();
//            }
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
//                selfFreeLength = panelDetailsList.get(i).getSelfFreeList().size();
//            }
//            int totalLength = standardLength + selfStandardLength + spareLength + selfSpareLength;
//            int index = firstRow + 34 + splicingPanels;
//            //标配备件信息循环插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getStandardDetailList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getStandardDetailList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j, 0, 1);
//                    cells.get(0)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getModel());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getRealCount());
//                    cells.get(4)
//                            .setCellValue(panelDetailsList.get(i).getStandardDetailList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getStandardDetailList().get(j).getRealPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellType(XSSFCell.CELL_TYPE_FORMULA);
//                    cells.get(6).setCellFormula("D" + (index + j + 1) + "*F" + (index + j + 1));
//                }
//            }
//            //自定义标配备件信息插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfStandardList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSelfStandardList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + standardLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getSpareType());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getAmount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfStandardList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getSelfStandardList().get(j).getPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getStandardDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellType(XSSFCell.CELL_TYPE_FORMULA);
//                    cells.get(6).setCellFormula(
//                            "D" + (index + j + 1 + standardLength) + "*F" + (index + j + 1 + standardLength));
//                }
//            }
//
//            //选配备件信息循环插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSpareDetailList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSpareDetailList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + standardLength + selfStandardLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + standardLength + selfStandardLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getType());
//                    cells.get(2).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getModel());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getRealCount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSpareDetailList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getSpareDetailList().get(j).getRealPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellFormula("D" + (index + j + 1 + standardLength + selfStandardLength) +
//                            "*F" + (index + j + 1 + standardLength + selfStandardLength));
//                }
//            }
//            //自定义选配信息插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfSpareList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSelfSpareList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength - selfSpareLength);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + totalLength - selfSpareLength, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getSpareType());
//                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getAmount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfSpareList().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            panelDetailsList.get(i).getSelfSpareList().get(j).getPrice().doubleValue()
//                                    * panelDetailsList.get(i).getOfferPanels().getSpareDiscount().doubleValue()
//                                    / 100));
//                    cells.get(6).setCellFormula(
//                            "D" + (index + j + totalLength - selfSpareLength + 1) + "*F" + (
//                                    index + j + totalLength - selfSpareLength + 1));
//                }
//            }
//
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList()) || CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
//                XSSFRow transferRowTitleFree = bodySheet.createRow(index + totalLength);
//                transferRowTitleFree.setHeightInPoints(20);
//                //创建单元格
//                List<XSSFCell> cells1_free = createRowAndCell(transferRowTitleFree, 7,
//                        bodyStyle(workbook, "微软雅黑", 10));
//                //设定合并单元格的区域
//                collapse(bodySheet, index + totalLength, 1, 6);
//                cells1_free.get(0).setCellStyle(styleColor);
//                cells1_free.get(1).setCellValue("Spare parts--free");
//                cells1_free.get(1).setCellStyle(style);
//            }
//
//            //免费备件信息循环插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getFreeDetailList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getFreeDetailList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + 1);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + totalLength + 1, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getType());
//                    cells.get(2).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getModel());
//                    cells.get(3)
//                            .setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getRealCount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getFreeDetailList().get(j).getUnit());
//                    cells.get(5).setCellValue(msg.get(3));
//                    cells.get(6).setCellValue(0);
//                }
//            }
//            //自定义免费备件信息插入
//            if (CollectionUtils.isNotEmpty(panelDetailsList.get(i).getSelfFreeList())) {
//                for (int j = 0; j < panelDetailsList.get(i).getSelfFreeList().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLength + freeLength + 1);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + +freeLength + 1, 0, 1);
//                    cells.get(0).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getType());
//                    cells.get(2)
//                            .setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getSpareType());
//                    cells.get(3).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getAmount());
//                    cells.get(4).setCellValue(panelDetailsList.get(i).getSelfFreeList().get(j).getUnit());
//                    cells.get(5).setCellValue(msg.get(3));
//                    cells.get(6).setCellValue(0);
//                }
//            }
//
//            int totalLengthCount = totalLength + freeLength + selfFreeLength + 1;
//
//            XSSFRow transferRowTitle = bodySheet.createRow(index + totalLengthCount);
//            transferRowTitle.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells1_1 = createRowAndCell(transferRowTitle, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            //设定合并单元格的区域
//            collapse(bodySheet, index + totalLengthCount, 1, 6);
//            cells1_1.get(0).setCellStyle(styleColor);
//            cells1_1.get(1).setCellValue("Package");
//            cells1_1.get(1).setCellStyle(style);
//
//            //服务列表长度
//            int serviceLength = 0;
//            if (CollectionUtils.isNotEmpty(offerVo.getServiceListDto())) {
//                serviceLength = offerVo.getServiceListDto().size();
//                //服务费用
//                for (int j = 0; j < offerVo.getServiceListDto().size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(index + j + totalLengthCount + 1);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, index + j + totalLengthCount + 1, 0, 1);
//                    cells.get(0).setCellValue(offerVo.getServiceListDto().get(j).getName());
//                    cells.get(3).setCellValue(offerVo.getServiceListDto().get(j).getCounts());
//                    cells.get(4).setCellValue(offerVo.getServiceListDto().get(j).getUnit());
//                    cells.get(5).setCellValue(format(
//                            offerVo.getServiceListDto().get(j).getPrice().doubleValue() * offerVo.getOffer()
//                                    .getServiceDiscount().doubleValue() / 100));
//                    cells.get(6).setCellType(XSSFCell.CELL_TYPE_FORMULA);
//                    cells.get(6).setCellFormula(
//                            "D" + (index + 2 + j + totalLengthCount) + "*F" + (index + 2 + j + totalLengthCount));
//                }
//            }
//
//            int totalService = index + totalLengthCount + serviceLength + 1;
//            List<TransportPackage> transportPackageList = offerVo.getTransport();
//            if (transportPackageList.size() > 0) {
//                //运输费用
//                for (int j = 0; j < transportPackageList.size(); j++) {
//                    //创建行
//                    XSSFRow autoRow = bodySheet.createRow(j + totalService);
//                    //设置行高
//                    autoRow.setHeightInPoints(20);
//                    //创建单元格
//                    List<XSSFCell> cells = createRowAndCell(autoRow, 7, bodyStyle(workbook, "微软雅黑", 10));
//                    //设定合并单元格的区域
//                    collapse(bodySheet, j + totalService, 0, 1);
//                    cells.get(0).setCellValue(transportPackageList.get(j).getPackages());
//                    cells.get(3).setCellValue(transportPackageList.get(j).getNumber());
//                    cells.get(4).setCellValue("");
//                    cells.get(5).setCellValue(String.valueOf(transportPackageList.get(j).getPriceUnit()));
//                    cells.get(6).setCellType(XSSFCell.CELL_TYPE_FORMULA);
//                    cells.get(6).setCellFormula("D" + (j + totalService + 1) + "*F" + (j + totalService + 1));
//                }
//            }
//            if (offerVo.getTransfer() != null) {
//                //创建行
//                XSSFRow autoRowTransfer = bodySheet.createRow(totalService + transportPackageList.size());
//                //设置行高
//                autoRowTransfer.setHeightInPoints(20);
//                //创建单元格
//                List<XSSFCell> cells = createRowAndCell(autoRowTransfer, 7, bodyStyle(workbook, "微软雅黑", 10));
//                //设定合并单元格的区域
//                collapse(bodySheet, totalService + transportPackageList.size(), 0, 1);
//                cells.get(0).setCellValue("EST.Shipping");
//                cells.get(3).setCellValue(offerVo.getTransfer().getCost() == null ? "0" : String.valueOf(offerVo.getTransfer().getCost()));
//                cells.get(4).setCellValue("");
//                cells.get(5).setCellValue(1);
//                cells.get(6).setCellType(XSSFCell.CELL_TYPE_FORMULA);
//                cells.get(6).setCellFormula("D" + (totalService + transportPackageList.size() + 1) + "*F" + (totalService + transportPackageList.size() + 1));
//
//                totalService = totalService + 1;
//            }
//
//            XSSFRow transferRow6 = bodySheet.createRow(totalService + transportPackageList.size());
//            transferRow6.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells6_1 = createRowAndCell(transferRow6, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow6.setHeightInPoints(20);
//            collapse(bodySheet, totalService + transportPackageList.size(), 0, 4);
//            collapse(bodySheet, totalService + transportPackageList.size(), 5, 6);
//            XSSFRichTextString val6 = new XSSFRichTextString(
//                    "(" + offerVo.getTransfer().getTrade() + "  Shenzhen  " + myOffer.getMoneyUnit() + ") Total:");
//            transferRow6.getCell(0).setCellValue(val6);
//            transferRow6.getCell(5).setCellFormula(
//                    "SUM(G" + (42 + splicingPanels) + ":G" + (totalService + transportPackageList.size() + 1) + ")");
//            transferRow6.getCell(0).setCellStyle(style);
//            transferRow6.getCell(5).setCellStyle(style);
//
//            int totalSize = totalService + transportPackageList.size();
//
//            XSSFRow transferRow7 = bodySheet.createRow(totalSize + 4);
//            transferRow7.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells7_1 = createRowAndCell(transferRow7, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow7.setHeightInPoints(20);
//            collapse(bodySheet, totalSize + 4, 0, 6);
//
//            // 样式对象 
//            XSSFCellStyle style2 = workbook.createCellStyle();
//            //设置垂直居中
//            style2.setVerticalAlignment(VerticalAlignment.CENTER);
//            style2.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT);
//            Font font = workbook.createFont();
//            font.setFontName("微软雅黑");
//            font.setFontHeightInPoints((short) 10);
//            style2.setFont(font);
//            //设置边框
////      style2.setBorderBottom(BorderStyle.THIN);
////      style2.setBorderLeft(BorderStyle.THIN);
////      style2.setBorderRight(BorderStyle.THIN);
////      style2.setBorderTop(BorderStyle.THIN);
//
//            XSSFRow transferRow8 = bodySheet.createRow(totalSize + 5);
//            transferRow8.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells8_1 = createRowAndCell(transferRow8, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow8.setHeightInPoints(20);
//            collapse(bodySheet, totalSize + 5, 0, 6);
//            XSSFRichTextString val8 = new XSSFRichTextString("Remarks:");
//            transferRow8.getCell(0).setCellValue(val8);
//            transferRow8.getCell(0).setCellStyle(style2);
//
//            XSSFRow transferRow9 = bodySheet.createRow(totalSize + 6);
//            transferRow9.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells9 = createRowAndCell(transferRow9, 7, bodyStyle(workbook, "微软雅黑", 10));
//            transferRow9.setHeightInPoints(20);
//            collapse(bodySheet, totalSize + 6, 0, 6);
//            XSSFRichTextString val9 = new XSSFRichTextString(
//                    "1. Quotation valid time: 30 days from quotation date");
//            transferRow9.getCell(0).setCellValue(val9);
//            transferRow9.getCell(0).setCellStyle(style2);
//
//            XSSFRow transferRow10 = bodySheet.createRow(totalSize + 7);
//            transferRow10.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells10 = createRowAndCell(transferRow10, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            collapse(bodySheet, totalSize + 7, 0, 6);
//            StringBuffer packageStr = new StringBuffer();
//            transportPackageList.forEach(item -> {
//                packageStr.append(item.getPackages()).append(",");
//            });
//
//            transferRow10.getCell(0).setCellValue("2. Packing: Dolly " + (transportPackageList.size() > 0 ? packageStr : " "));
//            transferRow10.getCell(0).setCellStyle(style2);
//
//            XSSFRow transferRow11 = bodySheet.createRow(totalSize + 8);
//            transferRow11.setHeightInPoints(40);
//            //创建单元格
//            List<XSSFCell> cells11 = createRowAndCell(transferRow11, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            collapse(bodySheet, totalSize + 8, 0, 6);
//            cells11.get(0).setCellValue("3. Payment method:  TBC  ( We are checking the maximun credit amount of Alabama now. The current credit we are using cannot cover this order if there is 80% paid after the shipment. New credit amount is under application).");
//            style2.setWrapText(true);
//            cells11.get(0).setCellStyle(style2);
//
//            XSSFRow transferRow12 = bodySheet.createRow(totalSize + 9);
//            transferRow12.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells12 = createRowAndCell(transferRow12, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            collapse(bodySheet, totalSize + 9, 0, 6);
//            transferRow12.getCell(0).setCellValue("4. Production lead time: " + myOffer.getWaitingDate() + " days.");
//            transferRow12.getCell(0).setCellStyle(style2);
//
//            XSSFRow transferRow13 = bodySheet.createRow(totalSize + 10);
//            transferRow10.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells13 = createRowAndCell(transferRow13, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            collapse(bodySheet, totalSize + 10, 0, 6);
//            transferRow13.getCell(0).setCellValue("5. Quality Warranty Period: two years from delivery date  ");
//            transferRow13.getCell(0).setCellStyle(style2);
//
//            XSSFRow transferRow14 = bodySheet.createRow(totalSize + 11);
//            transferRow14.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells14 = createRowAndCell(transferRow14, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            collapse(bodySheet, totalSize + 11, 0, 6);
//            cells14.get(0).setCellValue(
//                    "6. Free trainning: 7 days on-site complete technical trainning in our Shenzhen factory");
//            cells14.get(0).setCellStyle(style2);
//
//            XSSFRow transferRow15 = bodySheet.createRow(totalSize + 12);
//            transferRow15.setHeightInPoints(20);
//            //创建单元格
//            List<XSSFCell> cells15R = createRowAndCell(transferRow15, 7,
//                    bodyStyle(workbook, "微软雅黑", 10));
//            collapse(bodySheet, totalSize + 12, 0, 6);
//            cells15R.get(0).setCellValue(
//                    "7. Installation Guidance and after-sales premium service package: optional ");
//            cells15R.get(0).setCellStyle(style2);
//        }
//        //重新计算excel里面的公式
//        reCalculating(workbook);
//    }

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
        List<XSSFCell> cells10 = createRowAndCell(row, 7, bodyStyle(workbook, "微软雅黑", 10));
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
        List<XSSFCell> cells10 = createRowAndCell(row, 7, bodyStyle(workbook, "微软雅黑", size));
        //设定合并单元格的区域
//        collapse(sheet, num, 1, 1);
        collapse(sheet, num, 2, 6);
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

    private void setRowAndCell3(XSSFWorkbook workbook, XSSFSheet sheet, XSSFRow row, int num) {
        //设置行高
        row.setHeightInPoints(20);
        //创建单元格
        List<XSSFCell> cells10 = createRowAndCell(row, 7, bodyStyle(workbook, "微软雅黑", 10));
        //设定合并单元格的区域
        collapse(sheet, num, 0, 1);
        collapse(sheet, num, 3, 4);
        collapse(sheet, num, 5, 6);
    }
}

package com.jtech.toa.controller.importExcel;

import com.google.common.base.Strings;

import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.entity.system.Attachment;
import com.jtech.toa.model.dto.prices.PricesDetailDto;
import com.jtech.toa.model.dto.products.SparePriceDetailsDto;
import com.jtech.toa.service.prices.IPricesDetailsService;
import com.jtech.toa.service.system.IAttachmentService;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class DownlandController {
    @Value("${template.import}")
    private String filepath;
    private final IAttachmentService attachmentService;
    private final String path;
    private final IPricesDetailsService detailsService;
    private final IPricesDetailsService detailsPriceService;

    @Autowired
    public DownlandController(IAttachmentService attachmentService, OaProperties oaProperties, IPricesDetailsService detailsService,
                              IPricesDetailsService detailsPriceService) {
        this.attachmentService = attachmentService;
        this.detailsService = detailsService;
        this.path = oaProperties.getMediaPath();
        this.detailsPriceService = detailsPriceService;
    }

    /**
     * 错误详情下载
     */
    @GetMapping("/download")
    public HttpServletResponse download(HttpServletResponse response, int attachmentId, String fileName) {
        InputStream bis = null;
        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        try {
            File file = FileUtils.getFile(pathname);
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String("error.xlsx".getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param type 模板类型
     *             excel模板下载
     */
    @GetMapping("/excel/download")
    public HttpServletResponse download(HttpServletResponse response, String type) {
        File file;
        try {
            if (Strings.isNullOrEmpty(type)) {
                throw new FileNotFoundException("模板丟失");
            }
            if ("1".equals(type)) {
                file = new File(filepath + "module.xlsx");
            } else if ("2".equals(type)) {
                file = new File(filepath + "box.xlsx");
            } else if ("3".equals(type)) {
                file = new File(filepath + "product.xlsx");
            } else if ("4".equals(type)) {
                file = new File(filepath + "spare.xlsx");
            } else if ("5".equals(type) || "6".equals(type) || "7".equals(type)) {
                file = new File(filepath + "standard.xlsx");
            } else if ("8".equals(type)) {
                file = new File(filepath + "price.xlsx");
            } else if ("9".equals(type)) {
                file = new File(filepath + "spareprice.xlsx");
            } else {
                throw new FileNotFoundException("模板丟失");
            }

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param type 模板类型
     *             excel当前页面表格数据下载
     */
    @PostMapping("/spare/price/currDownload")
    public HttpServletResponse currDownload(HttpServletResponse response, String type, String param) {
        List<SparePriceDetailsDto> detailsSpare = null;
        List<PricesDetailDto> detailsPrice = null;
        if (String.valueOf(10).equals(type)) {
            detailsSpare = detailsService.getListBySparePriceSystemsId(Integer.valueOf(param));
        } else {
            detailsPrice = detailsPriceService.getDetailList(Integer.valueOf(param));
        }

        XSSFWorkbook workbook;
        XSSFSheet sheet;
        Row row;
        OutputStream out;

        try {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet();
            row = sheet.createRow(0);
            if (String.valueOf(10).equals(type)) {
                row.createCell(0).setCellValue("备件物料号");
                row.createCell(1).setCellValue("成本价");
                row.createCell(2).setCellValue("销售价");
                for (int i = 0; i < detailsSpare.size(); i++) {
                    row = sheet.createRow(i+1);
                    row.createCell(0).setCellValue(detailsSpare.get(i).getMaterial());
                    row.createCell(1).setCellValue(Double.valueOf(String.valueOf(detailsSpare.get(i).getPrice())));
                    row.createCell(2).setCellValue(Double.valueOf(String.valueOf(detailsSpare.get(i).getSalePrice())));
                }
            } else {
                row.createCell(0).setCellValue("屏体物料名称");
                row.createCell(1).setCellValue("屏体物料号");
                row.createCell(2).setCellValue("屏体价格");
                row.createCell(3).setCellValue("模组价格");
                for (int i = 0; i < detailsPrice.size(); i++) {
                    row = sheet.createRow(i+1);
                    row.createCell(0).setCellValue(detailsPrice.get(i).getProductState());
                    row.createCell(1).setCellValue(detailsPrice.get(i).getScnNo());
                    row.createCell(2).setCellValue(Double.valueOf(String.valueOf(detailsPrice.get(i).getPrice())));
                    row.createCell(3).setCellValue(Double.valueOf(String.valueOf(detailsPrice.get(i).getModual())));
                }
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download");
            String filedisplay = "price_download.xlsx";
            filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="+filedisplay);
            out = response.getOutputStream();
            workbook.write(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

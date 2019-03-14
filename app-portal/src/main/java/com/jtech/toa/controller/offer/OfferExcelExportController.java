package com.jtech.toa.controller.offer;

import com.google.common.collect.Lists;

import com.jtech.toa.user.entity.User;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtech.marble.StringPool;
import com.jtech.marble.error.ErrorModel;
import com.jtech.toa.controller.BaseController;
import com.jtech.toa.entity.mail.MailFile;
import com.jtech.toa.model.dto.offer.MyOfferDto;
import com.jtech.toa.model.dto.offer.OfferPanelsDto;
import com.jtech.toa.model.vo.MailVo;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.service.ExportExcel;
import com.jtech.toa.service.MailService;
import com.jtech.toa.service.offer.IOfferPanelsService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.user.constants.UserCode;
import com.jtech.toa.user.entity.Department;
import com.jtech.toa.user.service.IDepartmentService;
import com.jtech.toa.user.service.IUserService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/offer/export")
public class OfferExcelExportController extends BaseController {
    @Value("${template.export}")
    private String filepath;
    @Value("${spring.mail.username}")
    private String from;

    private final IOfferService offerService;
    private final IUserService userService;
    private final IDepartmentService departmentService;
    private final IOfferPanelsService offerPanelsService;
    private final MailService mailService;


    @Autowired
    public OfferExcelExportController(IOfferService offerService, IUserService userService, IDepartmentService departmentService, IOfferPanelsService offerPanelsService, MailService mailService) {
        this.offerService = offerService;
        this.userService = userService;
        this.departmentService = departmentService;
        this.offerPanelsService = offerPanelsService;
        this.mailService = mailService;
    }

    /**
     * 创建报价单Excel
     *
     * @param offerId 报价单id
     */
    @RequestMapping("/create")
    public void createOfferExcel(HttpServletResponse res, long offerId, String language) {
        final OfferVo offerVo = offerService.getOfferDetailsExport(offerId, language);
        final MyOfferDto myOffer = offerService.findOfferById(offerId, language);
        final User userMsg = userService.selectById(offerVo.getOffer().getCreater());
        final Department department = departmentService.selectParentById(offerVo.getOffer().getArea());
        final List<OfferPanelsDto> offerPanelsList = offerPanelsService.selectListByOffer(offerId, language);
        final ExportExcel exportExcel = new ExportExcel();
        final XSSFWorkbook workbook = exportExcel.createXSSFWorkbook(offerVo, myOffer, department, offerPanelsList,
                language, filepath,userMsg);
        //设置输出文件
        final String fileName = offerVo.getOffer().getNum() +"("+language+")_"+ ".xlsx";
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            workbook.write(baos);
            byte[] data = baos.toByteArray();
            downLoad(res, data, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建报价单Excel Pdf
     *
     * @param offerId 报价单id
     */
    @RequestMapping("/createPdf")
    public void createOfferExcelPdf(HttpServletResponse res, long offerId, String language) {
        final OfferVo offerVo = offerService.getOfferDetailsExport(offerId, language);
        final MyOfferDto myOffer = offerService.findOfferById(offerId, language);
        final User userMsg = userService.selectById(offerVo.getOffer().getCreater());
        final Department department = departmentService.selectParentById(offerVo.getOffer().getArea());
        final List<OfferPanelsDto> offerPanelsList = offerPanelsService.selectListByOffer(offerId, language);
        final ExportExcel exportExcel = new ExportExcel();
        final List<XSSFWorkbook> workbooks = exportExcel.createXSSFWorkbookPdf(offerVo, myOffer, department, offerPanelsList,
                language, filepath,userMsg);
        FileOutputStream fos;

        XSSFWorkbook workbook ;
        List<String> fileNames = new ArrayList<>(workbooks.size());
        try {
            for (int i = 0 ; i< workbooks.size() ; i++){
                //设置输出文件
                  String fileName = filepath + offerVo.getOffer().getNum()+"("+language+")_" +i+ ".xlsx";
                  workbook = workbooks.get(i);
                fos = new FileOutputStream(fileName);
                workbook.write(fos);
                fileNames.add(fileName);
            }

            String path =  this.createOfferPdf(fileNames);
            for (String str:fileNames) {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
            }

            byte[] content;
            try {
                BufferedInputStream in = new BufferedInputStream(
                        new FileInputStream(path));
                ByteArrayOutputStream out2 = new ByteArrayOutputStream(1024);
                byte[] temp = new byte[1024];
                int size = 0;
                while ((size = in.read(temp)) != -1) {
                    out2.write(temp, 0, size);
                }
                in.close();
                content = out2.toByteArray();
                out2.close();
                byte[] data = content;
                downLoad(res, data, path);
                File pdfFile = new File(path);
                if (pdfFile.exists()) {
                    pdfFile.delete();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }


    /**
     * 发送 报价单Excel
     *
     * @param id 报价单id
     */
    @RequestMapping("/send")
    public ResponseEntity sendOfferExcel(long id, String language, MailVo mailVo, HttpServletRequest request) {
        String fileName = this.createOfferExcel(id, language, filepath);
        try {

            Map<String, Object> model = new HashMap<>(4);

            List<MailFile> mailFiles = Lists.newArrayList();

            MailFile mailFile = new MailFile();

            mailFile.setSendMail(from);
            mailFile.setAcceptMail(mailVo.getAcceptMail());
            mailFile.setMailSubject(mailVo.getMailSubject());
            mailFiles.add(mailFile);


            // 发送邮件
            model.put("mailFiles", mailFiles);
            model.put("filePath", fileName);
            model.put("path", filepath);
            model.put("deployServerPath", mailService.getDeployServerPath(request));
            mailService.sendHtmlMailUsingFreeMarkerOffer(from, mailVo.getAcceptMail(), mailVo.getMailSubject(), model);
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }

        } catch (Exception e) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("邮件发送失败").createErrorModel();
            ResponseEntity.badRequest().body(errorModel);
        }

        return ResponseEntity.ok(StringPool.EMPTY);
    }

    /**
     * 创建报价单Excel Pdf
     *
     * @param id
     */
    @RequestMapping("/sendPdf")
    public ResponseEntity sendOfferExcelPdf(long id, String language, MailVo mailVo, HttpServletRequest request) {
        List<String> fileNames = this.createOfferExcelPdf(id, language, filepath);

        String path = (String) this.createOfferPdf(fileNames);

        try {

            for (String fileName: fileNames) {
                File file1 = new File(fileName);
                if (file1.exists()) {
                    file1.delete();
                }
            }
            Map<String, Object> model = new HashMap<>(4);

            List<MailFile> mailFiles = Lists.newArrayList();

            MailFile mailFile = new MailFile();

            mailFile.setSendMail(from);
            mailFile.setAcceptMail(mailVo.getAcceptMail());
            mailFile.setMailSubject(mailVo.getMailSubject());
            mailFiles.add(mailFile);


            // 发送邮件
            model.put("mailFiles", mailFiles);
            model.put("filePath", path);
            model.put("path", filepath);
            model.put("deployServerPath", mailService.getDeployServerPath(request));
            mailService.sendHtmlMailUsingFreeMarkerOffer(from, mailVo.getAcceptMail(), mailVo.getMailSubject(), model);
            File file = new File(path);
            for (String fileName: fileNames) {
                File fileExcel = new File(fileName);
                if (file.exists()) {
                    file.delete();
                }
                if (fileExcel.exists()) {
                    fileExcel.delete();
                }
            }


        } catch (Exception e) {
            ErrorModel errorModel = ErrorModel.builder()
                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
                    .setMessage("邮件发送失败").createErrorModel();
            ResponseEntity.badRequest().body(errorModel);
        }

        return ResponseEntity.ok(StringPool.EMPTY);

    }

    private static void downLoad(HttpServletResponse res, byte[] data, String filepath) {
        OutputStream out;
        try {
            if (data != null) {
                res.reset();
                res.setContentType("application/x-download;charset=UTF-8");
                res.setHeader("Content-Disposition", "attachment;filename=" +
                        new String(filepath.getBytes("gb2312"), "ISO8859-1"));
                out = new BufferedOutputStream(res.getOutputStream());
                out.write(data);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package com.jtech.toa.controller.importExcel.rest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import com.jtech.marble.StringPool;
import com.jtech.marble.error.BaseErrorCode;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.exception.InternalServerErrorException;
import com.jtech.marble.exception.ParamCheckException;
import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;
import com.jtech.marble.util.IdUtil;
import com.jtech.marble.util.io.FilePathUtil;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.entity.system.Attachment;
import com.jtech.toa.model.dto.imports.PriceDetailsImportDto;
import com.jtech.toa.model.dto.imports.SpareImportDto;
import com.jtech.toa.model.dto.imports.SparePriceImportDto;
import com.jtech.toa.model.dto.imports.StandardImportDto;
import com.jtech.toa.model.dto.products.BoxImportDto;
import com.jtech.toa.model.dto.products.ModuleImportDto;
import com.jtech.toa.model.dto.products.ProductImportDto;
import com.jtech.toa.service.product.*;
import com.jtech.toa.service.system.IAttachmentService;

import com.jtech.toa.util.ReplaceUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/import")
public class DataImportController {

    private final IAttachmentService attachmentService;
    private final IBoxService boxService;
    private final IModuleService moduleService;
    private final ISpareService spareService;
    private final IProductService productService;
    private final String path;
    private final IStandardService standardService;

    @Autowired
    public DataImportController(IAttachmentService attachmentService,
                                IBoxService boxService, IModuleService moduleService,
                                ISpareService spareService, OaProperties oaProperties,
                                IProductService productService,
                                IStandardService standardService) {
        this.attachmentService = attachmentService;
        this.boxService = boxService;
        this.moduleService = moduleService;
        this.spareService = spareService;
        this.productService = productService;
        this.path = oaProperties.getMediaPath();
        this.standardService = standardService;
    }
    /**
     * 普通文件上传请求
     *
     * @param file 上传的文件
     * @return 请求响应体
     */
    @PostMapping("/upload")
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

            final ShiroUser shiroUser = ShiroUtil.getUser();
            return attachmentService.saveFile(saveFile, uploadFileName, (int) shiroUser.getId());
        } catch (IOException e) {
            throw new InternalServerErrorException("文件上传失败");
        }

    }

    /**
     * module  excel文件导入
     *
     * @param attachmentId 数据库保存文件信息主键
     * @return 请求相应体
     */
    @PostMapping("/module")
    public ResponseEntity importModule(Integer attachmentId) {

        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);

        File file = new File(pathname);
        List<ModuleImportDto> list;
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_NULL).
                            setMessage("Excel不存在，请重新上传").createErrorModel());
        }
        try {
            list = ExcelImportUtil.importExcel(file, ModuleImportDto.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_INVALID).
                            setMessage("Excel不合法，无法导入").createErrorModel());
        }
        Map<Integer, String> map = moduleService.importModule(list);
        writeExcel(map, pathname);
        return ResponseEntity.ok(map);
    }

    /**
     * box  excel文件导入
     *
     * @param attachmentId 数据库保存文件信息主键
     * @return 相应请求
     */
    @PostMapping("/box")
    public ResponseEntity importBox(int attachmentId) {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);
        File file = new File(pathname);
        //文件不存在
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_NULL).
                            setMessage("Excel不存在，请重新上传").createErrorModel());
        }
        List<BoxImportDto> boxDtoList;
        try {
            boxDtoList = ExcelImportUtil.importExcel(file, BoxImportDto.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_INVALID).
                            setMessage("Excel不合法，无法导入").createErrorModel());
        }
        Map<Integer, String> map = boxService.importBox(boxDtoList);
        writeExcel(map, pathname);
        return ResponseEntity.ok(map);
    }

    /**
     * product  excel文件导入
     *
     * @param attachmentId 数据库保存文件信息主键
     * @return 请求相应体
     */
    @PostMapping("/product")
    public ResponseEntity importProduct(int attachmentId) {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);

        File file = new File(pathname);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_NULL).
                            setMessage("Excel不存在，请重新上传").createErrorModel());
        }
        List<ProductImportDto> list;
        try {
            list = ExcelImportUtil.importExcel(file, ProductImportDto.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_INVALID).
                            setMessage("Excel不合法或存在数据格式错误，无法导入").createErrorModel());
        }
        Map<Integer, String> map = productService.importProduct(list);
        writeExcel(map, pathname);
        return ResponseEntity.ok(map);
    }

    /**
     * spare  excel文件导入
     *
     * @param attachmentId 数据库保存文件信息主键
     * @return 请求相应体
     */
    @PostMapping("/spare")
    public ResponseEntity importSpare(int attachmentId) {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);
        File file = new File(pathname);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_NULL).
                            setMessage("Excel不存在，请重新上传").createErrorModel());
        }
        List<SpareImportDto> list;
        try {
            list = ExcelImportUtil.importExcel(file, SpareImportDto.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_INVALID).
                            setMessage("Excel不合法或存在数据格式错误，无法导入").createErrorModel());
        }
        Map<Integer, String> map = spareService.importSpare(list);
        writeExcel(map, pathname);
        return ResponseEntity.ok(map);
    }

    /**
     * 标准配件  excel文件导入
     *
     * @param attachmentId 数据库保存文件信息主键
     * @return 请求相应体
     */
    @PostMapping("/standard")
    public ResponseEntity importStandard(int attachmentId) {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);
        File file = new File(pathname);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_NULL).
                            setMessage("Excel不存在，请重新上传").createErrorModel());
        }
        List<StandardImportDto> list;
        try {
            list = ExcelImportUtil.importExcel(file, StandardImportDto.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_INVALID).
                            setMessage("Excel不合法或存在数据格式错误，无法导入").createErrorModel());
        }
        Map<Integer, String> map = standardService.importStandard(list, 1);
        writeExcel(map, pathname);
        return ResponseEntity.ok(map);
    }

    /**
     * 选配配件  excel文件导入
     *
     * @param attachmentId 数据库保存文件信息主键
     * @return 请求相应体
     */
    @PostMapping("/standard/select")
    public ResponseEntity importStandardSelect(int attachmentId) {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);


        File file = new File(pathname);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_NULL).
                            setMessage("Excel不存在，请重新上传").createErrorModel());
        }
        List<StandardImportDto> list;
        try {
            list = ExcelImportUtil.importExcel(file, StandardImportDto.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_INVALID).
                            setMessage("Excel不合法或存在数据格式错误，无法导入").createErrorModel());
        }
        Map<Integer, String> map = standardService.importStandard(list, 2);
        writeExcel(map, pathname);
        return ResponseEntity.ok(map);
    }

    /**
     * product  excel文件导入
     *
     * @param attachmentId 数据库保存文件信息主键
     * @return 请求相应体
     */
    @PostMapping("/standard/free")
    public ResponseEntity importStandardFree(int attachmentId) {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        final String pathname = path + attachment.getFilePath();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);


        File file = new File(pathname);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_NULL).
                            setMessage("Excel不存在，请重新上传").createErrorModel());
        }
        List<StandardImportDto> list;
        try {
            list = ExcelImportUtil.importExcel(file, StandardImportDto.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorModel.builder().setCode(ProductCode.EXCEL_IS_INVALID).
                            setMessage("Excel不合法或存在数据格式错误，无法导入").createErrorModel());
        }
        Map<Integer, String> map = standardService.importStandard(list, 3);
        writeExcel(map, pathname);
        return ResponseEntity.ok(map);
    }


    private void writeExcel(Map<Integer, String> map, String pathname) {
        FileInputStream excelFileInputStream = null;
        FileOutputStream excelFileOutPutStream = null;
        try {
            excelFileInputStream = new FileInputStream(pathname);
            XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            // 样式对象 
            XSSFCellStyle style = workbook.createCellStyle();
            //创建字体样式
            XSSFFont font = workbook.createFont();
            font.setColor(XSSFFont.COLOR_RED);
            style.setFont(font);
            //设置垂直居中
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
            if (map.size() > 0) {
                for (Integer integer : map.keySet()) {
                    int rowNum = sheet.getLastRowNum();
                    if (integer - 1 > rowNum) {
                        break;
                    }
                    XSSFRow row = sheet.getRow(integer - 1);
                    if(row == null) {
                    	continue;
                    }
                    XSSFCell cell = row.createCell(coloumNum);
                    cell.setCellStyle(style);
                    cell.setCellValue(map.get(integer));
                }
            }
            //自动调整列宽
            sheet.autoSizeColumn((short)coloumNum);
            excelFileOutPutStream = new FileOutputStream(pathname);

            workbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (excelFileInputStream != null) {
                //关闭流
                try {
                    excelFileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (excelFileOutPutStream != null) {
                // 关闭输出流对象
                try {
                    excelFileOutPutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("/price")
    public ResponseEntity importPrice(int attachmentId) throws Exception {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        String filepath = path + attachment.getFilePath();
        Map<String, List<PriceDetailsImportDto>> map = Maps.newHashMap();
        List<PriceDetailsImportDto> successList = Lists.newArrayList();
        List<PriceDetailsImportDto> errorList = Lists.newArrayList();
        if(!filepath.endsWith(".xls")&&!filepath.endsWith(".xlsx")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.EXCEL_IS_INVALID)
                    .setMessage("系统出错").createErrorModel());
        }
        FileInputStream fis =null;
        Workbook workBook = null;
        try {
            //获取一个绝对地址的流
            fis = new FileInputStream(filepath);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.EXCEL_IS_NULL)
                    .setMessage("系统出错").createErrorModel());
        }
        if (filepath.endsWith(".xls")) {
            workBook = new HSSFWorkbook(fis);
        }else {
            workBook = new XSSFWorkbook(fis);
        }
        //得到一个工作表
        Sheet sheet = workBook.getSheetAt(0);

        //获得表头
        Row rowHead = sheet.getRow(0);

        //判断表头是否正确
        if(!rowHead.getCell(0).getStringCellValue().equals(ReplaceUtil.replaceStr("屏体物料名称"))
                || !rowHead.getCell(1).getStringCellValue().equals(ReplaceUtil.replaceStr("屏体物料号"))
                || !rowHead.getCell(2).getStringCellValue().equals(ReplaceUtil.replaceStr("屏体价格"))
                || !rowHead.getCell(3).getStringCellValue().equals(ReplaceUtil.replaceStr("模组价格"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.EXCEL_IS_INVALID)
                    .setMessage("模板列头有误").createErrorModel());
        }
        //样式对象
        CellStyle style = workBook.createCellStyle();
        //创建字体样式
        Font font = workBook.createFont();
        font.setColor(Font.COLOR_RED);
        style.setFont(font);
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();

        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();

        //获取所有数据
        for(int i = 1; i <= totalRowNum; i++) {
            //获得第i行对象
            Row row = sheet.getRow(i);
            //在当前行最右边一列创建一个单元格
            Cell cell = row.createCell(coloumNum);
            cell.setCellStyle(style);

            PriceDetailsImportDto success = new PriceDetailsImportDto();
            PriceDetailsImportDto error = new PriceDetailsImportDto();
            //获得获得第i行Cells对象
            Cell c1 = row.getCell(1);
            Cell c2 = row.getCell(2);
            Cell c3 = row.getCell(3);
            if (c1 == null) {
                c1 = row.createCell(1);
            }else {
                c1.setCellType(CellType.STRING);
            }
            if (c2 == null) {
                c2 = row.createCell(2);
            }
            if (c3 == null) {
                c3 = row.createCell(3);
            }
            if (c1.getStringCellValue() == null) {
                error.setError("import.error.codeIsNull");
                cell.setCellValue("物料号为空");
                error.setRowNum(i+1);
                errorList.add(error);
                map.put("error", errorList);
                continue;
            }
            Product product = productService.selectOne(new EntityWrapper<Product>().
                    eq("part_no", c1.getStringCellValue().trim()));
            if (product == null) {
                error.setError("import.error.productInvalid");
                cell.setCellValue("物料号无效");
                error.setPartNo(c1.getStringCellValue());
                error.setRowNum(i+1);
                errorList.add(error);
                map.put("error", errorList);
                continue;
            }
            success.setPanel(product.getId());
            success.setPartNo(product.getPartNo());
            success.setState(product.getState());
            try {
                success.setPrice(BigDecimal.valueOf(c2.getNumericCellValue()));
                success.setModual(BigDecimal.valueOf(c3.getNumericCellValue()));
            } catch (Exception e) {
                e.printStackTrace();
                error.setError("import.error.format");
                error.setPartNo(c1.getStringCellValue().trim());
                error.setRowNum(i+1);
                cell.setCellValue("数据格式异常");
            }
            error.setRowNum(i+1);
            success.setRowNum(i+1);
            if (error.getError() != null) {
                errorList.add(error);
                map.put("error", errorList);
            }else {
                //判断excel中是否存在重复物料号
                boolean repeat = false;
                for (PriceDetailsImportDto priceDetailsImportDto : successList) {
                    if (priceDetailsImportDto.getPartNo().equals(success.getPartNo())) {
                        repeat = true;
                    }
                }
                if (!repeat) {
                    successList.add(success);
                    map.put("success", successList);
                }
            }
        }
        FileOutputStream fio = null;
        try {
            fio = new FileOutputStream(filepath);

            workBook.write(fio);
            fio.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fio != null) {
            fio.close();
        }
        return ResponseEntity.ok(map);
    }

    @PostMapping("/spare/price")
    public ResponseEntity importSparePrice(int attachmentId)throws Exception {
        final Attachment attachment = attachmentService.selectById(attachmentId);
        String filepath = path + attachment.getFilePath();
        Map<String, List<SparePriceImportDto>> map = Maps.newHashMap();
        List<SparePriceImportDto> successList = Lists.newArrayList();
        List<SparePriceImportDto> errorList = Lists.newArrayList();
        if(!filepath.endsWith(".xls")&&!filepath.endsWith(".xlsx")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.EXCEL_IS_INVALID)
                    .setMessage("系统出错").createErrorModel());
        }
        FileInputStream fis =null;
        Workbook workBook = null;
        try {
            //获取一个绝对地址的流
            fis = new FileInputStream(filepath);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.EXCEL_IS_NULL)
                    .setMessage("系统出错").createErrorModel());
        }
        if (filepath.endsWith(".xls")) {
            workBook = new HSSFWorkbook(fis);
        }else {
            workBook = new XSSFWorkbook(fis);
        }
        //得到一个工作表
        Sheet sheet = workBook.getSheetAt(0);

        //获得表头
        Row rowHead = sheet.getRow(0);

        //判断表头是否正确
        if(rowHead.getPhysicalNumberOfCells() != 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.EXCEL_IS_INVALID)
                    .setMessage("系统出错").createErrorModel());
        }
        //样式对象
        CellStyle style = workBook.createCellStyle();
        //创建字体样式
        Font font = workBook.createFont();
        font.setColor(Font.COLOR_RED);
        style.setFont(font);
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();

        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();

        //获取所有数据
        for(int i = 1; i <= totalRowNum; i++) {
            //获得第i行对象
            Row row = sheet.getRow(i);
            //在当前行最右边一列创建一个单元格
            Cell cell = row.createCell(coloumNum);
            cell.setCellStyle(style);

            SparePriceImportDto success = new SparePriceImportDto();
            SparePriceImportDto error = new SparePriceImportDto();
            //获得获得第i行Cells对象
            Cell c1 = row.getCell(1);
            Cell c2 = row.getCell(2);
            Cell c3 = row.getCell(3);
            if (c1 == null) {
                c1 = row.createCell(1);
            }else {
                c1.setCellType(CellType.STRING);
            }
            if (c2 == null) {
                c2 = row.createCell(2);
            }
            if (c3 == null) {
                c3 = row.createCell(3);
            }
            if (c1.getStringCellValue() == null) {
                error.setError("import.error.codeIsNull");
                cell.setCellValue("物料号为空");
                error.setRowNum(i+1);
                errorList.add(error);
                map.put("error", errorList);
                continue;
            }
            Spare spare = spareService.selectOne(new EntityWrapper<Spare>().eq("material", c1.getStringCellValue().trim()));
            if (spare == null) {
                error.setError("import.error.spareIsInvalid");
                cell.setCellValue("物料号无效");
                error.setMaterial(c1.getStringCellValue());
                error.setRowNum(i+1);
                errorList.add(error);
                map.put("error", errorList);
                continue;
            }
            success.setSpare(spare.getId());
            success.setMaterial(spare.getMaterial());
            success.setRemark(spare.getRemark());
            try {
                success.setPrice(BigDecimal.valueOf(c2.getNumericCellValue()));
                success.setSalePrice(BigDecimal.valueOf(c3.getNumericCellValue()));
            } catch (Exception e) {
                e.printStackTrace();
                error.setError("import.error.format");
                error.setMaterial(c1.getStringCellValue().trim());
                error.setRowNum(i+1);
                cell.setCellValue("数据格式异常");
            }
            error.setRowNum(i+1);
            success.setRowNum(i+1);
            if (error.getError() != null) {
                errorList.add(error);
                map.put("error", errorList);
            }else {
                //判断excel中是否存在重复物料号
                boolean repeat = false;
                for (SparePriceImportDto sparePriceImportDto : successList) {
                    if (sparePriceImportDto.getMaterial().equals(success.getMaterial())) {
                        repeat = true;
                    }
                }
                if (!repeat) {
                    successList.add(success);
                    map.put("success", successList);
                }
            }
        }
        FileOutputStream fio = null;
        try {
            fio = new FileOutputStream(filepath);

            workBook.write(fio);
            fio.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fis.close();
        if (fio != null) {
            fio.close();
        }
        return ResponseEntity.ok(map);
    }
}

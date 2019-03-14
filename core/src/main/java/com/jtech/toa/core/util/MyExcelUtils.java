package com.jtech.toa.core.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 新的ExcelUtils
 * 
 * @author Administrator
 * 
 */
public abstract class MyExcelUtils
{
	private static final Logger logger = LoggerFactory.getLogger(MyExcelUtils.class);
   
    /**
     * 保存数据
     * 
     * @param sheetData
     *            表单数据
     * @param os
     *            输出流
     * @param isSavedAs2007
     *            是否保存为2007格式
     * @return 是否保存成功
     */
    public static boolean saveData(List<List<String>> sheetData,
            OutputStream os, boolean isSavedAs2007, Map<Integer,Integer> typeMap)
    {
        if (sheetData == null || os == null)
        {
            return false;
        }
        Workbook wb = isSavedAs2007 ? new XSSFWorkbook() : new HSSFWorkbook();
        int rowCount = sheetData.size();
        Sheet sheet = wb.getSheet("Sheet1");
        if(sheet == null)
        {
            sheet = wb.createSheet("Sheet1");
        }
        for (int i = 0; i < rowCount; i++)
        {
            Row row = sheet.createRow(i);
            List<String> rowData = sheetData.get(i);
            setRowDataByType(0,typeMap, row, rowData);
        }
        return saveData(wb, os);
    }

    /**
     * 将行数据插入行中。且根据列类型来插入
     * @param typeMap
     * @param row
     * @param rowData
     */
    private static void setRowDataByType(int offset,Map<Integer, Integer> typeMap, Row row, List<String> rowData)
    {
        int rowCount = rowData.size();
        for (int j = 0; j < rowCount; j++)
        {
            Cell cell = row.createCell(j + offset);
            if(typeMap != null)
            {
                Integer type = typeMap.get(j);
                if(type != null)
                {
                    //设置单元格的类型
                    if(type == 0)
                    {
                        try
                        {
                            cell.setCellValue(Double.parseDouble(rowData.get(j)));
                            
                        } catch (Exception e)
                        {
                            cell.setCellValue(rowData.get(j));
                        }
                        continue;
                    }
                }
            }
            cell.setCellValue(rowData.get(j));
        }
    }


    /**
     * 保存数据
     * 
     * @param wb
     *            workbook对象
     * @param os
     *            输出流
     * @return 是否成功
     */
    public static boolean saveData(Workbook wb, OutputStream os)
    {
        if (wb == null || os == null)
        {
            return false;
        }
        try
        {
            wb.write(os);
            os.flush();
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
        finally
        {
            close(os);
        }
    }
    
    /**
	 * 关闭流
	 * 
	 * @param obj 需要关闭的流
	 */
	public static void close(Closeable obj) {
		if (obj != null) {
			try {
				obj.close();
			} catch (IOException e) {
				logger.info("关闭文件出现异常" + e.getMessage());
			} finally {
				obj = null;
			}
		}
	}

}

package com.jtech.toa.core.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于处理web方面的工具类
 * 
 * @author Administrator
 *
 */
public abstract class WebUtils {

	private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

	/**
	 * 将2D数据导出到前台
	 * 
	 * @param resp        response对象
	 * @param sheetData   表单二维数据
	 * @param outFileName 导出的文件名称
	 */
	public static void exportExcel(HttpServletResponse resp, List<List<String>> sheetData, String outFileName) {
		exportExcel(resp, sheetData, outFileName, null);
	}

	/**
	 * 将2D数据导出到前台
	 * 
	 * @param resp        response对象
	 * @param sheetData   表单二维数据
	 * @param outFileName 导出的文件名称
	 */
	public static void exportExcel(HttpServletResponse resp, List<List<String>> sheetData, String outFileName,
			Map<Integer, Integer> typeMap) {
		resp.setContentType("application/vnd.ms-excel");
		resp.setCharacterEncoding("utf-8");
		// 这里对文件名进行编码，保证下载时汉字显示正常
		OutputStream os = null;
		try {
			String fileName = URLEncoder.encode(outFileName, "utf-8");
			// Content-disposition属性设置成以附件方式进行下载
			resp.setHeader("Content-disposition", "attachment;filename=" + fileName);
			os = resp.getOutputStream();
			MyExcelUtils.saveData(sheetData, os, true, typeMap);
			os.flush();
			resp.flushBuffer();
		} catch (IOException e) {
			logger.error("导出excel失败", e);
		} finally {
			MyExcelUtils.close(os);
		}
	}

	/**
	 * 导出excel数据
	 * 
	 * @param resp
	 * @param wb
	 * @param outFileName
	 */
	public static void exportExcel(HttpServletResponse resp, Workbook wb, String outFileName) {
		resp.setContentType("application/vnd.ms-excel");
		resp.setCharacterEncoding("utf-8");
		// 这里对文件名进行编码，保证下载时汉字显示正常
		OutputStream os = null;
		try {
			String fileName = URLEncoder.encode(outFileName, "utf-8");
			// Content-disposition属性设置成以附件方式进行下载
			resp.setHeader("Content-disposition", "attachment;filename=" + fileName);
			os = resp.getOutputStream();
			MyExcelUtils.saveData(wb, os);
			os.flush();
		} catch (IOException e) {
			logger.error("导出excel失败", e);
		} finally {
			MyExcelUtils.close(os);
		}

	}

	/**
	 * 导出list集合的属性名
	 * 
	 * @param resp
	 * @param voList
	 * @param string
	 */
	public static <T> void exportExcelObj(HttpServletResponse resp, List<T> voList, String fileName) {
		exportExcelObj(resp, voList, fileName, null, null);
	}

	/**
	 * 导出实体List为excel对象
	 * 
	 * @param           <T>
	 * @param resp      response对象
	 * @param voList    实体集合对象
	 * @param fileName  导出的excel文件名称
	 * @param headerMap 属性名和标题的映射关系map
	 */
	public static <T> void exportExcelObj(HttpServletResponse resp, List<T> voList, String fileName,
			Map<String, String> headerMap) {
		exportExcelObj(resp, voList, fileName, headerMap, null);
	}

	/**
	 * 导出实体List为excel对象
	 * 
	 * @param           <T>
	 * @param resp      response对象
	 * @param voList    实体集合对象
	 * @param fileName  导出的excel文件名称
	 * @param headerMap 属性名和标题的映射关系map
	 */
	public static <T> void exportExcelObj(HttpServletResponse resp, List<T> voList, String fileName,
			Map<String, String> headerMap, Map<Integer, Integer> typeMap) {
		List<List<String>> list = get2DList(voList, true, headerMap);
		exportExcel(resp, list, fileName, typeMap);
	}

	/**
	 * 将实体对象的list转为2维的list数据,
	 * 
	 * @param voList       实体对象list
	 * @param containTitle true：包含头信息,头信息是属性的名称
	 * @return
	 */
	public static <T> List<List<String>> get2DList(List<T> voList, boolean containTitle,
			Map<String, String> headerMap) {
		List<List<String>> list = new ArrayList<List<String>>();
		if (voList != null) {
			T firstVo = voList.get(0);
			Field[] fList = firstVo.getClass().getDeclaredFields();

			// 1. 获取头信息
			List<String> header = new ArrayList<String>();
			for (Field f : fList) {
				f.setAccessible(true);
				String fieldName = f.getName();
				if (headerMap != null) {
					String fn = headerMap.get(fieldName);
					fieldName = fn == null ? fieldName : fn;
				}
				header.add(fieldName);
			}
			list.add(header);

			// 2. 获取数据,列数据
			for (T t : voList) {
				List<String> rowList = new ArrayList<String>();
				// 2.1 获取行数据
				for (Field f : fList) {
					try {
						f.setAccessible(true);
						rowList.add(String.valueOf(f.get(t)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(rowList);
			}
		}
		return list;
	}
}

package com.jtech.toa.core.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.jtech.marble.StringPool;

import java.util.List;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class FileTypeUtils {

    /**
     * 压缩文件
     */
    private static final List<String> CompressedFile = Lists.newArrayList("RAR", "TAR", "ZIP", "GZIP", "CAB", "UUE", "ARY", "BZ2", "LZH", "JAR", "ACE", "ISO", "7Z", "7ZIP", "Z");
    /**
     * 音频文件
     */
    private static final List<String> SoundFile = Lists.newArrayList("CD", "OGG", "MP3", "ASF", "WAV", "MP3PRO", "RM", "REAL", "APE", "MODULE", "MIDI", "VQF");
    /**
     * 视频文件
     */
    private static final List<String> VideoFile = Lists.newArrayList("AVI", "WMA", "RMVB", "RM", "FLASH", "DIVX", "VOB", "MKV", "MP4", "MID", "3GP", "MPEG", "MPF", "MPG", "DAT", "MOV", "QT", "RAM", "VIV", "RA", "ASF");
    /**
     * 图片文件
     */
    private static final List<String> ImageFile = Lists.newArrayList("BMP", "GIF", "JPEG", "JPG", "JPEG2000", "PSD", "PNG", "SWF", "SVG", "PCX", "DXF", "WMF", "EMF", "LIC", "FLI", "FLC", "EPS", "TGA");
    /**
     * 安装包，包括电脑和手机的
     */
    private static final List<String> ExeFile = Lists.newArrayList("EXE", "MSI", "IPA", "PXL", "DBD", "APK", "SIS", "SISX", "XAP", "APP", "DMG", "PKG");
    /**
     * office的doc文件
     */
    private static final List<String> DocFile = Lists.newArrayList("DOC", "DOCX", "MDB", "DOCM", "DOTX", "DOTM");
    /**
     * excel文件
     */
    private static final List<String> ExcelFile = Lists.newArrayList("XLS", "XLSM", "XLTX", "XLTM", "XLSB", "XLAM", "XLSX");
    /**
     * ppt文件
     */
    private static final List<String> PPTFile = Lists.newArrayList("PPT", "PPTX", "PPTM", "PPSX", "PPSM", "POTX", "POTM", "PPAM", "MPP");
    /**
     * 文本文件
     */
    private static final List<String> TxtFile = Lists.newArrayList("TXT", "RTF");
    /**
     * 说明文档或者电子文档之类的文件
     */
    private static final List<String> BookFile = Lists.newArrayList("PDF");
    /**
     * 网站脚本
     */
    private static final List<String> WebFile = Lists.newArrayList("HTML", "JSP", "JS", "CSS", "FTL", "JSF", "ASPX", "ASP", "PHP");
    private static final String FILE_UNKNOWN = "file_unknown";
    private static final String FILE_RAR = "file_rar";
    private static final String FILE_AUDIO = "file_audio";
    private static final String FILE_VIDEO = "file_video";
    private static final String FILE_PIC = "file_pic";
    private static final String FILE_APP = "file_app";
    private static final String FILE_DOC = "file_doc";
    private static final String FILE_TXT = "file_txt";
    private static final String FILE_PDF = "file_pdf";
    private static final String FILE_WEB = "file_web";
    private static final String FILE_EXCEL = "file_excel";
    private static final String FILE_PPT = "file_ppt";

    /**
     * 将文件后缀转换成对应的文件映射
     *
     * @param type 文件后缀
     * @return 文件映射
     */
    public static String fileType(String type) {
        if (Strings.isNullOrEmpty(type)) {
            return FILE_UNKNOWN;
        }
        type = type.replace(StringPool.DOT, StringPool.EMPTY).toUpperCase();
        if (CompressedFile.contains(type)) {
            return FILE_RAR;
        } else if (SoundFile.contains(type)) {
            return FILE_AUDIO;
        } else if (VideoFile.contains(type)) {
            return FILE_VIDEO;
        } else if (ImageFile.contains(type)) {
            return FILE_PIC;
        } else if (ExeFile.contains(type)) {
            return FILE_APP;
        } else if (DocFile.contains(type)) {
            return FILE_DOC;
        } else if (TxtFile.contains(type)) {
            return FILE_TXT;
        } else if (BookFile.contains(type)) {
            return FILE_PDF;
        } else if (WebFile.contains(type)) {
            return FILE_WEB;
        } else if (ExcelFile.contains(type)) {
            return FILE_EXCEL;
        } else if (PPTFile.contains(type)) {
            return FILE_PPT;
        } else {
            return FILE_UNKNOWN;
        }
    }

    /**
     * 将文件后缀转换成对应的文件映射
     *
     * @param type 文件后缀
     * @return 文件映射下标
     */
    public static int fileTypeToInt(String type) {
        type = type.replace(StringPool.DOT, StringPool.EMPTY).toUpperCase();
        if (CompressedFile.contains(type)) {
            return 1;
        } else if (SoundFile.contains(type)) {
            return 2;
        } else if (VideoFile.contains(type)) {
            return 3;
        } else if (ImageFile.contains(type)) {
            return 4;
        } else if (ExeFile.contains(type)) {
            return 5;
        } else if (DocFile.contains(type)) {
            return 6;
        } else if (TxtFile.contains(type)) {
            return 7;
        } else if (BookFile.contains(type)) {
            return 8;
        } else if (WebFile.contains(type)) {
            return 9;
        } else if (ExcelFile.contains(type)) {
            return 10;
        } else if (PPTFile.contains(type)) {
            return 11;
        } else {
            return 12;
        }
    }
}

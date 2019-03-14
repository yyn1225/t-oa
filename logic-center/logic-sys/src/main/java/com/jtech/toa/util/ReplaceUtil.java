package com.jtech.toa.util;

import com.baomidou.mybatisplus.toolkit.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>转换字符串的工具类</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public class ReplaceUtil {
    /**
     * 匹配换行，制表符的正则
     */
    private static final Pattern PATTERN = Pattern.compile("\t|\r|\n");
    /**
     * 字符串去除空格、制表符、换行符
     * @param str 原始字符串
     * @return 替换后的字符串
     */
    public static String replaceStr(String str) {
        if (StringUtils.isNotEmpty(str)) {
            Matcher m = ReplaceUtil.PATTERN.matcher(str.trim());
            return m.replaceAll(StringUtils.EMPTY);
        }
        return null;
    }

    /**
     * Decimal转换函数
     * @param str 原始字符串
     * @return 返回BigDecimal类型
     */
    public static BigDecimal replaceDecimal(String str) {
        if (StringUtils.isNotEmpty(str)) {
            Matcher m = ReplaceUtil.PATTERN.matcher(str.trim());
            return new BigDecimal(m.replaceAll(StringUtils.EMPTY));
        }
        return null;
    }

    /**
     * Integer转换函数
     * @param str 原始字符串
     * @return 返回Integer类型
     */
    public static Integer replaceInteger(String str) {
        if (StringUtils.isNotEmpty(str)) {
            Matcher m = ReplaceUtil.PATTERN.matcher(str.trim());
            return Integer.parseInt(m.replaceAll(StringUtils.EMPTY));
        }
        return null;
    }
}

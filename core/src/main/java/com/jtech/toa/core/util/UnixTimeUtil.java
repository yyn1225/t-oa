package com.jtech.toa.core.util;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p> unix时间戳处理类</p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class UnixTimeUtil {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMD_CN = "yyyy年MM月dd";
    private static final long MILLISECONDS_A_DAY = 24 * 3600 * 1000;
    public static String YYYYMMDDHHMM = "YYYY-MM-dd HH:mm";

    public static int unixTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Date toDate(int dateline) {
        return new Date(dateline * 1000L);
    }

    public static int unixTime(Date dateTime) {
        if (dateTime == null) {
            return 0;
        }
        return (int) (dateTime.getTime() / 1000);
    }

    public static int unixTime(DateTime dateTime) {
        if (dateTime == null) {
            return 0;
        }
        return (int) (dateTime.getMillis() / 1000);
    }

    public static String toDateString(int datetime, String format) {
        return new DateTime(UnixTimeUtil.toDate(datetime)).toString(format);
    }

    /**
     * 判断当前日期是星期几
     *
     * @param dateTiem 修要判断的时间
     * @return dayForWeek 判断结果
     */
    public static String dayForWeek(int dateTiem) {
        Calendar c = Calendar.getInstance();
        c.setTime(toDate(dateTiem));
        int dayForWeek;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        String result;
        switch (dayForWeek) {
            case 1:
                result = "周一";
                break;
            case 2:
                result = "周二";
                break;
            case 3:
                result = "周三";
                break;
            case 4:
                result = "周四";
                break;
            case 5:
                result = "周五";
                break;
            case 6:
                result = "周六";
                break;
            case 7:
                result = "周日";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    /**
     * 判断今天，明天
     *
     * @param dateTiem 需要判断的日期
     * @return
     */
    private static String todayAndYesterday(int dateTiem) {
        String format = "yyyy-MM-dd";
        final Date currentDate = new Date();
        final Date whenDate = toDate(dateTiem);

        final String currentTime = toDateString(unixTime(currentDate), "yyyy-MM-dd");
        final String whenTime = toDateString(unixTime(whenDate), "yyyy-MM-dd");

        long quot = 0;
        format = format.length() > 0 ? format : DEFAULT_DATE_FORMAT;
        SimpleDateFormat ft = new SimpleDateFormat(format);
        try {
            Date date1 = ft.parse(whenTime);
            Date date2 = ft.parse(currentTime);
            quot = date1.getTime() - date2.getTime();
            quot = quot / MILLISECONDS_A_DAY;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result;
        if ((int) quot == 0) {
            result = "今天";
        } else if ((int) quot == 1) {
            result = "明天";
        } else {
            result = "其他";
        }
        return result;
    }

    /**
     * 任务接口返回的时间
     *
     * @param dateTime 需要改变的时间
     * @return
     */
    public static String timeReslut(int dateTime, boolean flag) {
        String result1 = "";
        String time = "";
        if (flag) {
            time = toDateString(dateTime, "HH:mm");
        }
        final String dayForWeek = dayForWeek(dateTime);
        final String today = todayAndYesterday(dateTime);
        final String dateString = toDateString(dateTime, "YYYY-MM-DD");
        if (today.equals("其他")) {
            result1 = dateString + " " + dayForWeek + " " + time;
        } else {
            result1 = today + " " + dayForWeek + " " + time;
        }
        return result1;
    }

}

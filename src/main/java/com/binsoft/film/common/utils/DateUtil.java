package com.binsoft.film.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 */
public final class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置 SimpleDateFormat pattern 格式字符串
     *
     * @param formatStr
     */
    public static void setSimpleDateFormat(String formatStr) {
        sdf = new SimpleDateFormat(formatStr);
    }

    /**
     * 设置 DateTimeFormatter pattern 格式字符串
     *
     * @param formatStr
     */
    public static void setDateTimeFormatter(String formatStr) {
        dtf = DateTimeFormatter.ofPattern(formatStr);
    }

    /**
     * 字符串转换为日期
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date convertToDate(String strDate) throws ParseException {
        Date date = null;
        if (strDate != null) {
            date = sdf.parse(strDate);

        }
        return date;
    }

    /**
     * 字符串转换为日期
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static LocalDateTime convertToLocalDateTime(String strDate) throws ParseException {
        LocalDateTime localDateTime = null;
        if (strDate != null) {
            localDateTime = LocalDateTime.parse(strDate, dtf);

        }
        return localDateTime;
    }

    /**
     * 日期转换为字符串
     *
     * @param date
     * @return
     */
    public static String convertToString(Date date) {

        String dateStr = null;
        if (date != null) {
            dateStr = sdf.format(date);
        }
        return dateStr;
    }

    /**
     * 日期转换为字符串
     *
     * @param time
     * @return
     */
    public static String convertToString(LocalDateTime time) {
        String dateTimeStr = null;
        if (time != null) {
            dateTimeStr = sdf.format(time);
        }
        return dateTimeStr;
    }

}

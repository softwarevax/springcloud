package com.wit.sc.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author twcao
 * @Title: DateUtils
 * @ProjectName sc-parent
 * @Description: 日期工具
 * @date 2019/1/16/01619:06
 */
public class DateUtils {

    public static class Format {
        /**
         默认的时间格式
         */
        public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

        /**
         * yyyy_MM_dd
         */
        public static final String yyyy_MM_dd = "yyyy-MM-dd";

        /**
         * yyyyMM_d
         */
        public static final String yyyyMMdd = "yyyyMMdd";

        public static final String FORMAT_yyyy_MM_DD_HHmmss = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 将制定日期转化为时间字符串
     * @return
     */
    public static String format(Date date, String format) {
        if(StringUtils.isBlank(format)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将制定日期转化为时间字符串
     * @return
     */
    public static String format(Date date) {
        return format(date, Format.yyyyMMddHHmmss);
    }

    /**
     * 将制定日期转化为时间字符串
     * @return
     */
    public static String format() {
        return format(new Date());
    }

    /**
     * 将Long类型日期转化为时间字符串
     * @return
     */
    public static String format(Long msTime, String format) {
        if(null == msTime) {
            return "";
        }
        return format(new Date(msTime), format);
    }

    public static Date parse2Date(String time, String format) {
        if(StringUtils.isAnyBlank(time, format)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(time);
            return date;
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将srcFormat格式的时间字符串，转化为targetFormat时间字符串
     * @param time
     * @param srcFormat
     * @param targetFormat
     * @return
     */
    public static String parse(String time, String srcFormat, String targetFormat) {
        Date date = parse2Date(time, srcFormat);
        if(date == null) {
            return null;
        }
        return format(date, targetFormat);
    }

    public static String getDate(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if(StringUtils.isBlank(date)){
            throw new RuntimeException("date is null .");
        }
        Date tempDate = simpleDateFormat.parse(date);
        return getDate(tempDate, Format.yyyyMMddHHmmss);
    }

    public static String getDate(Date date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Long getMsByDate(String date, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date tempDate = simpleDateFormat.parse(date);
            return tempDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parseMillis2Str(Long millis, String format) {
        Date time =  new Date(millis);
        return getDate(time, format);
    }

    /**
     * 将毫秒时间转化为时间字符串
     * @param millisecond
     * @return
     */
    public static String parseMs2Str(Long millisecond) {
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 根据时间字符串获得整点时间
     * @param date
     * @return
     */
    public static Long getLatestPointTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:00:00");
        String time = sdf.format(date);
        Date now = null;
        try {
            now = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return now.getTime();
    }

    /**
     * 根据时间字符串获得整点时间
     * @param millisecond
     * @return
     */
    public static Long getLatestPointTime(Long millisecond) {
        Date date = new Date(millisecond);
        return getLatestPointTime(date);
    }

    /**
     * 获取数据库时间格式 String
     * @param time
     * @return
     */
    public static String getOraTime(String time) {
        String dateString = time.replace(" ", "").replace("-","").replace(":", "") ;
        dateString = dateString + "000000";
        return dateString;
    }

}

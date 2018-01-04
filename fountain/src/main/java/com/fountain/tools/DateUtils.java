package com.fountain.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creator : Fountain Tao<br/>
 * Creation Time : 2017/12/14<br/>
 * Description : Time工具类<br/>
 */
public class DateUtils {
    private static class SingletonHolder {
        private volatile static DateUtils mInstance = new DateUtils();
    }

    public static DateUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private DateUtils() {
    }

    // TODO: [record] 获取当前时间戳
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    // TODO: [record] 获取当前时间的指定时间格式的时间
    public String getCurrentTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(getCurrentTimeMillis());
    }

    // TODO: [record] 获取当前时间的年份
    public String getCurrentYear() {
        return getCurrentTime("yyyy");
    }

    // TODO: [record] 获取当前时间的月份
    public String getCurrentMonth() {
        return getCurrentTime("MM");
    }

    // TODO: [record] 获取当前时间的天数
    public String getCurrentDay() {
        return getCurrentTime("dd");
    }

    // TODO: [record] 获取当前时间的小时数
    public String getCurrentHour() {
        return getCurrentTime("HH");
    }

    // TODO: [record] 获取当前时间的分钟数
    public String getCurrentMinute() {
        return getCurrentTime("mm");
    }

    // TODO: [record] 获取当前时间的秒数
    public String getCurrentSecond() {
        return getCurrentTime("ss");
    }

    // TODO: [record] 获取当前时间的毫秒数
    public String getCurrentMillisecond() {
        return getCurrentTime("SSS");
    }

    // TODO: [record] 获取当前时间是周几
    public String getCurrentWeek() {
        return getCurrentTime("E");
    }

    // TODO: [record] 获取指定时间，指定时间格式的时间
    public String getTime(String format, long time) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(time);
    }

    // TODO: [record] 获取指定时间的年份
    public String getYear(long time) {
        return getTime("yyyy", time);
    }

    // TODO: [record] 获取指定时间的月份
    public String getMonth(long time) {
        return getTime("MM", time);
    }

    // TODO: [record] 获取指定时间的天数
    public String getDay(long time) {
        return getTime("dd", time);
    }

    // TODO: [record] 获取指定时间的小时数
    public String getHour(long time) {
        return getTime("HH", time);
    }

    // TODO: [record] 获取指定时间的分钟数
    public String getMinute(long time) {
        return getTime("mm", time);
    }

    // TODO: [record] 获取指定时间的秒数
    public String getSecond(long time) {
        return getTime("ss", time);
    }

    // TODO: [record] 获取指定时间的毫秒数
    public String getMillisecond(long time) {
        return getTime("SSS", time);
    }

    // TODO: [record] 获取指定时间是周几
    public String getWeek(long time) {
        return getTime("E", time);
    }

    // TODO: [record] 将指定格式的字符串型日期转换时间戳
    public long stringToTimestamp(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
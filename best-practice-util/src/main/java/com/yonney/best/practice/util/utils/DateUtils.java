package com.yonney.best.practice.util.utils;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yonney.yang on 2015/4/29.
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
    public static final String[] WEEKDAYS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    public static final int CONSTANT_LENGTH = 3;

    /**
     * 设定date的时分秒
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date formatDate(Date date, int hour, int minute, int second) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), hour, minute, second);
        return c.getTime();
    }

    /**
     * 获取beginDate的后一天
     *
     * @param beginDate
     * @return
     */
    public static Date getNextDate(Date beginDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
        return c.getTime();
    }

    /**
     * 获取beginDate的前一天
     *
     * @param beginDate
     * @return
     */
    public static Date getPreviousDate(Date beginDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
        return c.getTime();
    }

    /**
     * 获取本周第一天
     *
     * @return
     */
    public static Date getFirstDayOfCurrentWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    /**
     * 取Date0和Date1的时间差，取整，不满一天就不计入在内。 比如:输入2013.3.1-2013.3.4
     * 0:00:01得到3天,输入2013.3.1-2013.3.4 0:00:00也是得到3天
     *
     * @param date0
     * @param date1
     * @return
     */
    public static int getIntervalDay(Date date0, Date date1) {
        long milliSeconds = date1.getTime() - date0.getTime();
        int intervalDay = (int) (milliSeconds / 1000 / 60 / 60 / 24);
        return intervalDay;
    }

    /**
     * 取日期差，但只要endDate比beginDate多1秒，也算是多一天 比如:输入2013.3.1-2013.3.4
     * 0:00:01得到4天,输入2013.3.1-2013.3.4 0:00:00得到3天
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int daysBetween(Date beginDate, Date endDate) {
        int days = 0;// 两个日期之前的天数
        Calendar beginCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        beginCalendar.setTime(beginDate);
        endCalendar.setTime(endDate);
        // 计算天数
        while (beginCalendar.before(endCalendar)) {
            days++;
            beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    /**
     * 根据开始时间得到对应的当天结束时间如：2012-03-08 00:00:00返回2012-03:08:23:59:59
     *
     * @param
     * @return
     */
    public static Date getCurrentEndDateFormat(Date endDate) {
        try {
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(endDate);
            date.set(Calendar.AM_PM, 1);
            date.set(Calendar.HOUR, 11);
            date.set(Calendar.MINUTE, 59);
            date.set(Calendar.SECOND, 59);
            return dft.parse(dft.format(date.getTime()));
        } catch (Exception ex) {
            return endDate;
        }
    }

    /**
     * 根据开始时间得到对应的当天开始时间如：2012-03-08 23:59:00返回2012-03:08 00:00:00
     *
     * @param
     * @return
     */
    public static Date getCurrentBeginDateFormat(Date beginDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(Calendar.AM_PM, 0);
            date.set(Calendar.HOUR, 0);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            return dft.parse(dft.format(date.getTime()));
        } catch (Exception ex) {
            return beginDate;
        }
    }

    /**
     * 时间相加
     *
     * @param beginDate
     * @param duration
     * @return
     */
    public static Date getDateAdded(Date beginDate, int duration) {
        try {
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(Calendar.DATE, date.get(Calendar.DATE) + duration);
            return date.getTime();
        } catch (Exception ex) {
            return beginDate;
        }
    }

    /**
     * 对传入的beginDate，格式化为：yyyy-MM-01 00:00:00
     *
     * @param beginDate
     * @return
     */
    public static Date formatBeginDate(String beginDate) {
        return DateUtils.parse(beginDate, new SimpleDateFormat("yyyy-MM"));
    }

    /**
     * 对传入的endDate，格式化为本月最后一天的23:59:59：yyyy-MM-[28\30\31] 23:59:59
     *
     * @param endDate
     * @return
     */
    public static Date formatEndDate(String endDate) {
        Date date = DateUtils.parse(endDate, new SimpleDateFormat("yyyy-MM"));
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) - 1);
        return c.getTime();
    }

    public static Date parse(String date, SimpleDateFormat sdf) {
        return parse(date, sdf);
    }

    public static Date parse(String date, DateFormat df) {
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("parse date [" + date + "] failed in use [" + new SimpleDateFormat("yyyy-MM-dd") + "]", e);
        }
    }

    /**
     * @param year  参数2011时为2011年
     * @param month 参数1时表示1月
     * @param day   参数1为1号
     * @return
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    public static List<Date> getPreviousThreeDays(Date date) {
        if (date == null) return null;
        List<Date> result = new ArrayList<Date>();

        result = getPreviousThreeDaysList(date, result);

        return result;
    }

    private static List<Date> getPreviousThreeDaysList(Date date, List<Date> result) {
        if (result.size() < CONSTANT_LENGTH) {
            result.add(date);
            getPreviousThreeDaysList(getPreviousDate(date), result);
        }
        return result;

    }

    /**
     * 获取某年的所有月份
     *
     * @param year
     * @return
     */
    public static List<Date> getAllMonthOfYear(int year) {
        List<Date> result = new LinkedList<Date>();
        for (int i = 0; i < 12; i++) {
            result.add(getMonth(year, i));
        }
        return result;
    }

    public static Date getMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static Date getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取下个月份
     *
     * @return
     */
    public static Date getNextMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(getCurrentMonth());
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    /**
     * 当前时间加上区间得到的月份
     *
     * @param date
     * @param space
     * @return
     */
    public static Date addMonth(Date date, int space) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, space);
        return c.getTime();
    }

    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static Date startOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取某月的最后一天
     *
     * @param monthBetween 与当前月相隔的月数
     * @return
     */
    public static Date endOfMonth(int monthBetween) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + monthBetween);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取本月的第一天
     *
     * @return
     */
    public static Date getCurMonthBeginDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @return
     */
    public static Date getCurMonthEndDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 获取本周的星期一
     *
     * @return
     */
    public static Date getCurWeekBeginDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * 获取本周的星期日
     *
     * @return
     */
    public static Date getCurWeekEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获取上周的星期一
     *
     * @return
     */
    public static Date getLastWeekBeginDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * 获取上周的星期日
     *
     * @return
     */
    public static Date getLastWeekEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static String getWeekDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int w = c.get(Calendar.DAY_OF_WEEK) - 1;
        return WEEKDAYS[w];
    }

    /**
     * 计算两天差值的天数，不受时分秒影响
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getBalanceOfDay(Date date1, Date date2) {
        LocalDate localDate1 = new LocalDate(date1);
        LocalDate localDate2 = new LocalDate(date2);
        int days = Days.daysBetween(localDate1, localDate2).getDays();

        return Math.abs(days);
    }

    /**
     * 计算现在到指定时间的差值天数，不受时分秒影响
     *
     * @param date
     * @return
     */
    public static int getBalanceWithNow(Date date) {
        Date now = DateUtils.getCurrent();
        return getBalanceOfDay(now, date);
    }

    public static Date getCurrent() {
        return new Date();
    }

    public static Date getLastMonday(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday >= Calendar.MONDAY) {
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else {
            c.add(Calendar.DATE, -7);
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        return c.getTime();
    }

    public static Date getMonday(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    public static Date getNextLastMonday(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }
}

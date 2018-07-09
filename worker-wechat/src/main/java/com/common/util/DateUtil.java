package com.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

public class DateUtil {

    private final static Log logger = LogFactory.getLog(DateUtil.class);

    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 字符串转换成日期，如果需转换的字符串为null，则返回为null
     *
     * @param value
     *            String 需转换的字符串 格式为yyyy-MM-dd
     * @return Date 日期
     */


    public static Date string2date(String value) {
        if (value == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转换成日期，如果需转换的字符串为null，则返回为null
     *
     * @param value
     *            String 需转换的字符串
     * @param format
     *            String 转换成字符型的日期格式
     * @return Date 日期
     */
    public static Date string2date(String value, String format) {
        if (value == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期转换成字符串，如果需转换的日期为null，则返回为null
     *
     * @param date
     *            Date 需转换的日期 格式为yyyy-MM-dd
     * @return String 日期字符串
     */
    public static String date2string(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * 日期转换成字符串，如果需转换的日期为null，则返回为null
     *
     * @param date
     *            Date 需转换的日期
     * @param format
     *            String 转换成字符型的日期格式
     * @return String 日期字符串
     */
    public static String date2string(Date date, String format) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     *
     * @param format
     *            yyyy-MM-dd HH:mm:ss:S 年月日时分秒毫杪
     * @return
     */
    public static String getNowDate(String format) {
        String dateTime = "";
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateTime = sdf.format(now);
        } catch (Exception e) {
            logger.error(e);
        }
        return dateTime;
    }

    /**
     *
     * @return 以 yyyy-mm-dd 形式返回当前日期
     */
    public static String getNowDate() {
        return getNowDate(DATE_FORMAT);
    }

    /**
     *
     * @return 以 yyyy-MM-dd HH:mm:ss 形式返回当前日期时间
     */
    public static String getNowDateTime() {
        return getNowDate(DATE_TIME_FORMAT);
    }

    /**
     *
     * @param date
     * @param date2
     * @return date2-date的天数
     */
    public static long getDaysInterval(Date date, Date date2) {
        return (date2.getTime() - date.getTime()) / 86400000;
    }

    public static java.sql.Timestamp getTimestamp(){
        return java.sql.Timestamp.valueOf(getNowDateTime());
    }
    /**
     * @param days
     * @return 和当前相差days天的日期
     */
    public static String getDate(int days) {
        Date date = new Date();
        date.setTime(date.getTime() + 86400000L * days);
        return date2string(date);
    }

    /**
     *
     * @param date
     * @return date对应的年
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

    /**
     *
     * @param date
     * @return date对应的月
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     *
     * @param date
     * @return 对应的天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getNowDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 根据日期算年龄
     * @param birthDay 出生日期
     */
    public static int yearOLD(Date birthDay)
    {
        Calendar sysCalendar=Calendar.getInstance();
        Calendar birCalendar=Calendar.getInstance();
        birCalendar.setTime(birthDay);
        if(sysCalendar.get(Calendar.MONTH)>birCalendar.get(Calendar.MONTH))
            return sysCalendar.get(Calendar.YEAR)-birCalendar.get(Calendar.YEAR)+1;
        else
            return sysCalendar.get(Calendar.YEAR)-birCalendar.get(Calendar.YEAR);
    }


    /**
     * 将当前时间转换为固定的yyyy-MM-dd HH:mm:ss格式 以字符串的形式输出
     *
     * @return
     */
    public static String getCurrentDate() {
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 将当前时间根据传入的格式以字符串的形式输出
     *
     * @param str
     * @return
     */
    public static String getCurrentDate(String str) {
        format = new SimpleDateFormat(str);
        return format.format(new Date());
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     *
     * @param currentDate
     *            被格式化日期字符串 必须为yyyymmdd
     * @param pattern
     *            字符串的格式
     * @return String 已格式化的日期字符串
     * @throws NullPointerException
     *             如果参数为空
     * @throws ParseException
     *             若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static String formatDate(String currentDate, String pattern)

    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(currentDate);
            sdf.applyPattern(pattern);
            return sdf.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

    public static String formatDate(String currentDate,
                                    String currentDatePattern, String pattern)

    {

        SimpleDateFormat sdf = new SimpleDateFormat(currentDatePattern);// yyyyMMdd
        Date date;
        try {
            date = sdf.parse(currentDate);
            sdf.applyPattern(pattern);
            return sdf.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 比较两个日期相差的天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalDays(Date fDate, Date oDate) {

        if (null == fDate || null == oDate) {

            return -1;

        }

        long intervalMilli = oDate.getTime() - fDate.getTime();

        return (int) (intervalMilli / (24 * 60 * 60 * 1000));

    }

    /**
     * 日期天数加1
     *
     * @param currentDate
     * @param pattern
     * @return
     */
    public static String getDayAdd1(String currentDate, String pattern,
                                    int dates) throws Exception {

        Date date = DateUtil.convertStringToDate(currentDate, pattern);

        date.setDate(date.getDate() + dates);

        return DateUtil.getDateTime(date, pattern);
    }

    public static String getDateTime(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 比较2个时间相差的分钟数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalMinutes(Date fDate, Date oDate) {

        if (null == fDate || null == oDate) {
            return -1;
        }

        long intervalMilli = oDate.getTime() - fDate.getTime();
        return (int) (intervalMilli / (60 * 1000));
    }

    /**
     * 根据传入的模式参数返回当天的日期
     *
     * @param pattern
     *            传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getToday(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 比较两个日期大小
     *
     * @param date1
     *            日期字符串
     * @param pattern1
     *            日期格式
     * @param date2
     *            日期字符串
     * @param pattern2
     *            日期格式
     * @return boolean 若是date1比date2小则返回true
     * @throws ParseException
     */
    public static boolean compareMinDate(String date1, String pattern1,
                                         String date2, String pattern2) throws ParseException {
        Date d1 = convertToCalendar(date1, pattern1).getTime();
        Date d2 = convertToCalendar(date2, pattern2).getTime();
        return d1.before(d2);
    }

    /**
     * 比较两个日期大小
     *
     * @param date1
     *            Date
     * @param date2
     *            Date
     * @return boolean 若是date1比date2小则返回true
     */
    public static boolean compareMinDate(Date date1, Date date2) {
        try {
            return DateUtil.compareMinDate(DateUtil.formatDate(date1,
                    "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", DateUtil
                            .formatDate(date2, "yyyy-MM-dd HH:mm:ss"),
                    "yyyy-MM-dd HH:mm:ss");
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 比较两个日期大小
     *
     * @param date1
     *            Date
     * @param date2
     *            Date
     * @return boolean 若是date1比date2小则返回true
     */
    public static boolean compareMinDateForDay(Date date1, Date date2) {
        try {
            return DateUtil.compareMinDate(DateUtil.formatDate(date1,
                    "yyyy-MM-dd"), "yyyy-MM-dd", DateUtil.formatDate(date2,
                    "yyyy-MM-dd"), "yyyy-MM-dd");
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 根据传入的日期字符串以及格式，产生一个Calendar对象
     *
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return Calendar
     * @throws ParseException
     *             当格式与日期字符串不匹配时抛出该异常
     */
    public static Calendar convertToCalendar(String date, String pattern)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = sdf.parse(date);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        return calendar;
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     *
     * @param pattern
     *            字符串的格式
     * @param currentDate
     *            被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException
     *             如果参数为空
     */
    public static String formatDate(Calendar currentDate, String pattern) {

        Date date = currentDate.getTime();
        return formatDate(date, pattern);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     *
     * @param pattern
     *            字符串的格式
     * @param currentDate
     *            被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException
     *             如果参数为空
     */
    public static String formatDate(Date currentDate, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(currentDate);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     *
     * @param strDate
     *            被格式化日期字符串 必须为yyyymmdd
     * @param formator
     *            格式字符串
     * @return String 已格式化的日期字符串
     * @throws NullPointerException
     *             如果参数为空
     * @throws ParseException
     *             若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static Calendar strToDate(String strDate, String formator) {

        Calendar date = Calendar.getInstance();
        date.setTime(java.sql.Date.valueOf(strDate));
        return date;
    }

    /**
     * 判断当前时间是否在参数时间内（当开始时间大于结束时间表示时间段的划分从begin到第二天的end时刻） 例如当前时间在12：00
     * 传入参数为（12,12,0,1）返回true 例如当前时间在12：00 传入参数为（12,12,1,0）返回true
     *
     * @param beginHour
     *            int 开始的小时值
     * @param endHour
     *            int 结束的小时值
     * @param beginMinu
     *            int 开始的分钟值
     * @param endMinu
     *            int 结束的分钟值
     * @return boolean
     */
    public static boolean isInTime(int beginHour, int endHour, int beginMinu,
                                   int endMinu) {
        Date date1 = new Date();
        Date date2 = new Date();
        Date nowDate = new Date();
        date1.setHours(beginHour);
        date2.setHours(endHour);
        date1.setMinutes(beginMinu);
        date2.setMinutes(endMinu);
        if (date1 == date2) {
            return false;
        }
        // yyyy-MM-dd HH:mm:ss
        if (DateUtil.compare(date2, date1)) {
            if (!DateUtil.compare(nowDate, date1)
                    || DateUtil.compare(nowDate, date2)) {
                return true;
            }
        } else {
            if (!DateUtil.compare(nowDate, date1)
                    && DateUtil.compare(nowDate, date2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 开始时间小于结束时间返回true，否则返回false
     *
     * @param beginDate
     *            Date
     * @param endDate
     *            Date
     * @return boolean
     */
    private static boolean compare(Date beginDate, Date endDate) {
        try {

            return DateUtil.compareMinDate(DateUtil.formatDate(beginDate,
                    "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", DateUtil
                            .formatDate(endDate, "yyyy-MM-dd HH:mm:ss"),
                    "yyyy-MM-dd HH:mm:ss");

        } catch (Exception ex) {
            // log.error ( "时间格式转换错误" + ex ) ;
            return false;
        }
    }

    /**
     * 将指定格式的时间String转为Date类型
     *
     * @param dateStr
     *            String 待转换的时间String
     * @param pattern
     *            String 转换的类型
     * @throws ParseException
     * @return Date
     */
    public static Date convertStringToDate(String dateStr, String pattern)
            throws ParseException {
        if (dateStr == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    public static String convertDateToString(Date date) throws ParseException {
        if (date == null) {
            return "";
        }
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取比当前日期早多少天或者晚多少天的日期 例如 前五天 －5 后五天 5
     *
     * @param days
     * @param format
     *            返回日期的格式
     * @return 格式化好的字符串
     */

    public static String DateBefAft(int days, String format) {
        //
        if (format == null || "".equals(format))
            format = "yyyy-MM-dd";
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        now.add(Calendar.DAY_OF_YEAR, -days);
        return formatter.format(now.getTime());
    }

    /**
     * 获取比当前日期早多少天或者晚多少天的日期 例如 前五天 －5 后五天 5
     *
     * @param days
     * @param format
     *            返回日期的格式
     * @return 日期
     * @throws ParseException
     */

    public static Date DateBefAft_returnDate(int days, String format)
            throws ParseException {
        //
        if (format == null || "".equals(format))
            format = "yyyy-MM-dd";
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        now.add(Calendar.DAY_OF_YEAR, -days);
        return convertStringToDate(formatter.format(now.getTime()),
                "yyyy-MM-dd");
    }

    /**
     * 格式化 Date 类型的日期
     *
     * @param date
     *            传入日期
     * @param format
     *            设定日期的显示格式 默认 2006-12-25
     * @return 格式化后的日期
     */
    public static String DatetoString(Date date, String format) {
        if (format == null || "".equals(format))
            format = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static void shortDateToLongDate(String strDate) {

    }

    /**
     * 获取某时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
     *
     * @param date
     *            ：日期
     * @return
     */
    public static String getWeekCS(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] week = { "0", "1", "2", "3", "4", "5", "6" };
        return week[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取当前时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
     *
     * @return
     */
    public static String getWeekCSToday() {
        return getWeekCS(new Date());
    }

    /**
     * 用当前日期作为文件名，一般不会重名取到的值是从当前时间的字符串格式，带有微秒，建议作为记录id
     *
     * @return
     */
    public static String getTimeStamp(String strFormat) {
        Date currentTime = new Date();
        return dateToString(currentTime, strFormat);
    }

    /**
     * 用当前日期作为文件名，一般不会重名取到的值是从1970年1月1日00:00:00开始算起所经过的微秒数
     *
     * @return
     */
    public static String getFileName() {
        Calendar calendar = Calendar.getInstance();
        String filename = String.valueOf(calendar.getTimeInMillis());
        return filename;
    }

    /**
     * 获取两个日期之间所差的天数
     *
     * @param from
     *            ：开始日期
     * @param to
     *            ：结束日期
     * @return：所差的天数(非负整数)
     */
    public static int dateNum(Date from, Date to) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date fromDate = calendar.getTime();

        calendar.setTime(to);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date toDate = calendar.getTime();
        int diff = Math
                .abs((int) ((fromDate.getTime() - toDate.getTime()) / (24 * 3600 * 1000)));
        return diff;
    }

    public static int datenuM(Date one, Date to) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(one);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date fromDate = calendar.getTime();

        calendar.setTime(to);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date toDate = calendar.getTime();
        int diff = Math
                .abs((int) ((fromDate.getTime() - toDate.getTime()) / (24 * 3600 * 1000)));
        return diff;
    }

    /**
     * 获取两个日期之间所差的周数
     *
     * @param from
     * @param to
     * @return
     */
    public static int weekNum(Date from, Date to) {

        return 0;
    }

    /**
     * 获取date前或后nDay天的日期
     *
     * @param date
     *            ：开始日期
     * @param nDay
     *            ：天数
     * @param type
     *            ：正为后nDay天的日期，否则为前nDay天的日期。
     * @return
     */
    private static Date getDate(Date date, int nDay, int type, String format) {
        long millisecond = date.getTime(); // 从1970年1月1日00:00:00开始算起所经过的微秒数
        long msel = nDay * 24 * 3600 * 1000l; // 获取nDay天总的毫秒数
        millisecond = millisecond + ((type > 0) ? msel : (-msel));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        return calendar.getTime();
    }

    /**
     * 获取n天后的日期
     *
     * @param date
     * @param nDay
     * @return
     */
    public static Date dateAfterNDate(Date date, int nDay, String format) {
        return getDate(date, nDay, 1, format);
    }

    /**
     * 获取n天后的日期
     *
     * @param strDate
     * @param nDay
     * @return
     */
    public static Date dateAfterNDate(String strDate, int nDay, String format) {
        Date date = stringToDate(strDate, format);
        return dateAfterNDate(date, nDay, format);
    }

    /**
     * 获取n天前的日期
     *
     * @param date
     * @param nDay
     * @return
     */
    public static Date dateBeforeNDate(Date date, int nDay, String format) {
        return getDate(date, nDay, -1, format);
    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param s
     * @param e
     * @return
     */
    public static int getMonth(Date s, Date e) {
        if (s.after(e)) {
            Date t = s;
            s = e;
            e = t;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(s);
        Calendar end = Calendar.getInstance();
        end.setTime(e);
        Calendar temp = Calendar.getInstance();
        temp.setTime(e);
        temp.add(Calendar.DATE, 1);

        int y = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int m = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

        if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
            return y * 12 + m + 1;
        } else if ((start.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
            return y * 12 + m;
        } else if ((start.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
            return y * 12 + m;
        } else {// 前破月后破月
            return (y * 12 + m - 1) < 0 ? 0 : (y * 12 + m - 1);
        }
    }

    /**
     * 获取n天前的日期
     *
     * @param strDate
     * @param nDay
     * @return
     */
    public static Date dateBeforeNDate(String strDate, int nDay, String format) {
        Date date = stringToDate(strDate, format);
        return dateBeforeNDate(date, nDay, format);
    }

    /**
     * 将日期转化为字符串的形式
     *
     * @param date
     * @param strFormat
     * @return
     */
    public static String dateToString(Date date, String strFormat) {
        if (date == null) {
            return null;
        }
        if (strFormat == null) {
            strFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(date);
    }

    /**
     * 将字符串转化为Date类型。如果该字符串无法转化为Date类型的数据，则返回null。
     *
     * @param strDate
     * @param strFormat
     *            strDate和strFormat的格式要一样。即如果strDate="20061112"，则strFormat="yyyyMMdd"
     * @return
     */
    public static Date stringToDate(String strDate, String strFormat) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            date = sdf.parse(strDate);
            if (!sdf.format(date).equals(strDate)) {
                date = null;
            }
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 获取n月之前或之后的日期
     *
     * @param date
     * @param nMonth
     * @param type
     *            (只能为-1或1)
     * @return
     */
    public static Date getDateMonth(Date date, int nMonth, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int nYear = nMonth / 12;
        int month = nMonth % 12;
        calendar.add(Calendar.YEAR, nYear * type);
        Date desDate = calendar.getTime();
        calendar.add(Calendar.MONTH, month * type);
        if (type < 0) {
            while (!calendar.getTime().before(desDate)) {
                calendar.add(Calendar.YEAR, type);
            }
        } else {
            while (!calendar.getTime().after(desDate)) {
                calendar.add(Calendar.YEAR, type);
            }
        }
        return calendar.getTime();
    }

    /**
     * 获取当前时间所在的周的最后一天（周一为第一天）
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        index = (index == 1) ? (index + 7) : index;
        date = DateUtil.dateAfterNDate(date, 8 - index, "yyyy-MM-dd");
        return date;
    }

    /**
     * 获取当前时间所在的周的第一天（周一为第一天）
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        index = (index == 1) ? (index + 7) : index;
        date = DateUtil.dateBeforeNDate(date, index - 2, "yyyy-MM-dd");
        return date;
    }

    /**
     * 获取date所在的月份的最后一天 方法是获取下个月的第一天，然后获取前一天的日期
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        date = calendar.getTime();
        date = DateUtil.getDateMonth(date, 1, 1);
        date = DateUtil.dateBeforeNDate(date, 1, "yyyy-MM-dd");
        return date;
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取季度的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfSeason(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.MONTH);
        index = index / 3;
        Date[] dates = new Date[4];
        calendar.set(calendar.get(Calendar.YEAR), 2, 31);
        dates[0] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), 5, 30);
        dates[1] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), 8, 30);
        dates[2] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), 11, 31);
        dates[3] = calendar.getTime();
        return dates[index];
    }

    /**
     * 创建日期date
     *
     * @param year
     *            ：年
     * @param month
     *            ：月
     * @param day
     *            ：日
     * @return
     */
    public static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static Date getDateByPattern(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = null;
        try {
            curDate = format.parse(format.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDate;
    }

    public static Date getBeginDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 根据指定日期参数，判断该日期是否在某段时间内
     *
     * @param curDate
     *            当前时间
     * @param begDate
     *            起始时间
     * @param endDate
     *            结束时间
     * @return
     */
    public static boolean isInDate(Date curDate, Date begDate, Date endDate) {
        boolean isIn = false;
        Date b = new Date(begDate.getYear(), begDate.getMonth(), begDate
                .getDate() - 1);
        Date e = new Date(endDate.getYear(), endDate.getMonth(), endDate
                .getDate() + 1);
        if (null != begDate && null != endDate && curDate.after(b)
                && curDate.before(e)) {
            isIn = true;
        }

        return isIn;
    }

    public static boolean afterDate(Date d1, Date d2) {
        if (d1.after(d2)) {
            return true;
        } else {
            return false;
        }
    }

    public static Date changeHours(Date target, int hours) {
        long msPerHour = 3600000L;
        long msTarget = target.getTime();
        long msSum = msTarget + msPerHour * (long) hours;
        Date result = new Date();
        result.setTime(msSum);
        return result;
    }

    /**
     * 获取N个月后日期
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getDateAfterMonths(Date date, int n) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime(date);
        grc.add(GregorianCalendar.MONTH, n);
        return grc.getTime();
    }

    /**
     * 格式化日期串
     *
     * @param orgStr
     *            待加工的原始时间串
     * @param inReg
     *            待加工串的分割符
     * @param outReg
     *            组装新串连接符
     * @return
     */
    public static String getDateStr(String orgStr, String inReg, String outReg) {
        String dateStr = "";
        String yyyy = "";
        String mm = "";
        String dd = "";
        String[] str = orgStr.split(inReg);

        if (str[0].length() < 4 && str[0].length() == 2)
            yyyy = "20" + str[0];
        else
            yyyy = str[0];
        if (Integer.parseInt(str[1]) < 10) {
            mm = "0" + Integer.parseInt(str[1]);
        } else
            mm = str[1];
        if (Integer.parseInt(str[2]) < 10) {
            dd = "0" + Integer.parseInt(str[2]);
        } else
            dd = str[2];
        dateStr = yyyy + outReg + mm + outReg + dd;
        return dateStr;
    }

    /**
     * 返回excel cell在java中日期值
     *
     * @param DateNumber
     *            日期cell 的值
     * @return
     */
    public static Date getExcelDateNumberToDate(int DateNumber) {
        // 发现excel单元格的日期值1900-1-1值为2 java的月份是0-11表示 故初始值设置如下
        Calendar calendar = new GregorianCalendar(1900, 0, -1);
        calendar.add(calendar.DATE, DateNumber);
        return calendar.getTime();
    }

    private static boolean isReservedAddr(InetAddress inetAddr) {
        if (inetAddr.isAnyLocalAddress() || inetAddr.isLinkLocalAddress()
                || inetAddr.isLoopbackAddress()) {
            return true;
        }
        return false;
    }

    public static String getLocalIPv6Address() throws Exception {
        InetAddress inetAddress = null;
        Enumeration<NetworkInterface> network = NetworkInterface
                .getNetworkInterfaces();
        while (network.hasMoreElements()) {
            Enumeration<InetAddress> in = network.nextElement()
                    .getInetAddresses();
            while (in.hasMoreElements()) {
                inetAddress = in.nextElement();
                if (inetAddress instanceof Inet6Address
                        && !isReservedAddr(inetAddress)) {
                    break;
                }
            }
        }
        String ip = inetAddress.getHostAddress();
        int index = ip.indexOf('%');
        if (index > 0) {
            ip = ip.substring(0, index);
        }
        return ip;
    }

//	public static void main(String[] args) throws Exception {
//		Timestamp t = new Timestamp((convertStringToDate("2011-11-11", "yyyy-MM-dd")).getTime());
//		System.out.println(t);
//	}


    public static void main(String[] args) {
/*		System.out.println(new Date());
		System.out.println(getNowDate());
		System.out.println(getNowDateTime());
		Date date = string2date("2007-01-06");
		Date date2 = string2date("2007-01-08");
		System.out.println(getDaysInterval(date, date2));
		System.out.println(getYear(date));
		System.out.println(getMonth(date));
		System.out.println(getDay(date));
		System.out.println(string2date("2007-01-01"));
		System.out.println(date2string(string2date("2007-01-01 12:13:14",
				DATE_TIME_FORMAT), DATE_TIME_FORMAT));
		System.out.println(date2string(string2date("2007-01-01 12:13:14")));
		System.out.println(DateUtil.getNowDate("yyMMddHHmmss"));
		System.out.println(getDate(-21));
		System.out.println((86400000L*(-300)));
		System.out.println(string2date(date2string(new Date(),DATE_TIME_FORMAT),DATE_TIME_FORMAT));*/
    }
}

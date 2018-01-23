package com.bsit.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	
	/** 简化日期格式 */
	public static final String DATE_SIMPLE_FORMAT = "yyyyMMdd";

	/** 默认日期格式 */
	public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
	
	/** 短时间默认格式 */
	public static final String TIME_SHORT_DEFAULT_FORMAT = "HH:mm";
	
	/** 短时间简化格式 */
	public static final String TIME_SHORT_SIMPLE_FORMAT = "HHmm";
	
	/** 简化时间格式 */
	public static final String TIME_SIMPLE_FORMAT = "HHmmss";

	/** 默认时间格式 */
	public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";
	
	/** 简化月份格式 */
	public static final String MONTH_SIMPLE_FORMAT = "yyyyMM";

	/** 默认月份格式 */
	public static final String MONTH_DEFAULT_FORMAT = "yyyy-MM";
	
	/** 简化日期时间格式 */
	public static final String DATETIME_SIMPLE_FORMAT = "yyyyMMddHHmmss";

	/** 默认日期时间格式 */
	public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取当前日期时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getNow(){
		SimpleDateFormat df = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
		String date = df.format(new Date());
		return date;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getDate(){
		SimpleDateFormat df = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		String date = df.format(new Date());
		return date;
	}

	/**
	 * 获取当前短时间
	 * 
	 * @return HH:mm
	 */
	public static String getShortTime(){
		SimpleDateFormat df = new SimpleDateFormat(TIME_SHORT_DEFAULT_FORMAT);//设置日期格式
		String date = df.format(new Date());
		return date;
	}
	
	/**
	 * 获取当前短时间
	 * 
	 * @return HH:mm:ss
	 */
	public static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
		String date = df.format(new Date());
		return date;
	}
	
	/**
	 * 获取当前日期时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 获取当前简化日期时间
	 * 
	 * @return yyyyMMddHHmmss
	 */
	public static String getSimpleCurrentDateTime()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_SIMPLE_FORMAT);
		return sdf.format(date);
	}
	
	/**
	 * 获取昨天日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getYesterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat(DATE_DEFAULT_FORMAT).format(cal.getTime());
		return yesterday;
	}

	/**
	 * 获取明天日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getTomorrow(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		String yesterday = new SimpleDateFormat(DATE_DEFAULT_FORMAT).format(cal.getTime());
		return yesterday;
	}
	
	/**
	 * 得到得到今天的几天前的日期
	 * 
	 * @param days
	 * @return yyyy-MM-dd
	 */
	public static String getTodayBefore(int days) {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - days);
		String dayBefore = new SimpleDateFormat(DATE_DEFAULT_FORMAT).format(c.getTime());
		return dayBefore;
	}
	
	/**
	 * 得到得到今天的几天后的日期
	 * 
	 * @param days
	 * @return yyyy-MM-dd
	 */
	public static String getTodayAfter(int days) {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + days);
		String dayBefore = new SimpleDateFormat(DATE_DEFAULT_FORMAT).format(c.getTime());
		return dayBefore;
	}

	/**
	 * 得到某天的几天前的日期
	 * @param d
	 * @param day
	 * @return yyyy-MM-dd
	 */
	public static String getDateBefore(Date d, int day){
		SimpleDateFormat df = new SimpleDateFormat(DATE_DEFAULT_FORMAT);// 设置日期格式
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE,now.get(Calendar.DATE) - day);
		return df.format(now.getTime());
	}

	/**
	 * 得到某天的几天后的日期
	 * @param d
	 * @param day
	 * @return yyyy-MM-dd
	 */
	public static String getDateAfter(Date d, int day){
		SimpleDateFormat df = new SimpleDateFormat(DATE_DEFAULT_FORMAT);// 设置日期格式
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE,now.get(Calendar.DATE) + day);
		return  df.format(now.getTime());
	}

	/**
	 * 获取当前年份
	 * @return yyyy
	 */
	public static int getYear(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 获取当前月份
	 * @return MM
	 */
	public static int getMonth(){
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;//获取月份
		return month;
	}

	/**
	 * 获取当前日子
	 * @return dd
	 */
	public static int getDay(){
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);
		return day;
	}

	/**
	 * 获取当前小时
	 * @return HH
	 */
	public static int getHour(){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);//Calendar.HOUR：12小时制。Calendar.HOUR_OF_DAY：24小时制
		return hour;
	}

	/**
	 * 获取当前分钟
	 * @return mm
	 */
	public static int getMinute(){
		Calendar c = Calendar.getInstance();
		int minute = c.get(Calendar.MINUTE);//分
		return minute;
	}
	
	/**
	 * 获取当前秒数
	 * @return SS
	 */
	public static int getSecond(){
		Calendar c = Calendar.getInstance();
		int second = c.get(Calendar.SECOND);//秒
		return second;
	}
	
	/**
	 * 获取给定日期的年份
	 * @param dateTime
	 * @param format
	 * @return yyyy
	 */
	public static int getYearByDate(String dateTime, String format) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 获取给定日期的月份
	 * @param dateTime
	 * @param format
	 * @return MM
	 */
	public static int getMonthByDate(String dateTime, String format) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}
	
	/**
	 * 获取给定日期的日子
	 * @param dateTime
	 * @param format
	 * @return dd
	 */
	public static int getDayByDate(String dateTime, String format) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.DATE);
		return year;
	}

	/**
	 * 获取当前月份
	 * @return yyyy-MM
	 */
	public static String getCurrMonth(){
		SimpleDateFormat df = new SimpleDateFormat(MONTH_DEFAULT_FORMAT);//设置日期格式
		String date = df.format(new Date());
		return date;
	}
	
	/**
	 * 获取当前日期是星期几<br>
	 *
	 * @param date
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}
		return weekDays[w];
	}
	
	/**
	 * 获取当前日期是一周的第几天
	 * @param date yyyy-MM-dd
	 * @return 1-7，默认星期天是第一天，星期六是第7天
	 */
	public static int getWeekNoOfDate(String dateStr) {
		int w = 0;
		try {
			Calendar cal = Calendar.getInstance();
			java.text.SimpleDateFormat formt = new java.text.SimpleDateFormat(DATE_DEFAULT_FORMAT);
			Date date = formt.parse(dateStr);
			cal.setTime(date);
			w = cal.get(Calendar.DAY_OF_WEEK);
			if (w < 0){
				w = 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return w;
	}
	
	/**
	 * 获得当月月末是几号
	 *
	 * @param year
	 * @return 返回19位的日期字符串
	 */
	public static String getLastDayOfMonth(String year) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		// 获得月末是几号
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 当前季度的开始时间，即2012-01-01 00:00:00
	 *
	 * @return
	 */
	public static String getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		String date = "";
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
			date = longSdf.format(now);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static String getCurrentQuarterEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null;
		String date = "";
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
			date = longSdf.format(now);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 计算两个日期之间的天数</br>
	 * 任何一个参数传空都会返回-1</br>
	 * 返回两个日期的时间差，不关心两个日期的先后</br>
	 * @param dateStart yyyy-MM-dd
	 * @param dateEnd yyyy-MM-dd
	 * @return int
	 */
	public static int getDaysBetweenTwoDate(Date dateStart, Date dateEnd){
		if(null == dateStart || null == dateEnd){
			return -1;
		}
		long diff = Math.abs(dateStart.getTime() - dateEnd.getTime());
		diff = diff / (1000 * 60 * 60 * 24);
		return (int) diff;
	}
	
	/**
	 * 两个时间相差距离多少小时多少分
	 * 
	 * @param str1 时间参数 1 格式：11:50
	 * @param str2 时间参数 2 格式：12:10
	 * @param str2 HH:mm
	 * @return long[] 返回值为：8小时26分
	 */
	public static String getSimpleDistanceTimes(String str1, String str2, String formatStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date1 = sdf.parse(str1);
		Date date2 = sdf.parse(str2);
		long l = date2.getTime() - date1.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		return hour + "小时" + min +"分";
	}
	
	/**
	 * 两个时间相差距离多少小时多少分
	 * 
	 * @param str1 时间参数 1 格式：11:50
	 * @param str2 时间参数 2 格式：12:10
	 * @param str2 HH:mm
	 * @return long[] 返回值为：{时, 分}
	 */
	public static long[] getDisMin(String time1, String time2, String formatStr){
		SimpleDateFormat dfs = new SimpleDateFormat(formatStr);
		long between = 0;
		try {
			Date begin = dfs.parse(time1);
			Date end = dfs.parse(time2);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long[] times = { hour, min};
		return times;
	}
	
	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1 时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2 时间参数 2 格式：2009-01-01 12:00:00
	 * @param str2 HH:mm
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(String str1, String str2, String formatString) {
		DateFormat df = new SimpleDateFormat(formatString);
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}
	
	/**
	 * 两个时间做对比返回值为：1,0，-1
	 * 
	 * @param str1
	 * @param str2
	 * @return str1>str2 返回1，str1=str2 返回0，str1<str2 返回-1
	 */
	public static int compareDate(String str1, String str2, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		int result = 0;
		try {
				Date dt1 = df.parse(str1);
				Date dt2 = df.parse(str2);
				if (dt1.getTime() > dt2.getTime()) {
					result = 1;
				} else if (dt1.getTime() == dt2.getTime()) {
					result = 0;
				} else if (dt1.getTime() < dt2.getTime()) {
					result = -1;
				}
		} catch (Exception exception) {
			exception.printStackTrace();
			result = 9999;
		}
		return result;
	}

	/**
	 * 根据出生日期计算年龄
	 * @param brithday
	 * @return 年龄
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static int getCurrentAgeByBirthdate(String brithday)
			throws ParseException, Exception {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
			String currentTime = formatDate.format(calendar.getTime());
			Date today = formatDate.parse(currentTime);
			Date brithDay = formatDate.parse(brithday);
			return today.getYear() - brithDay.getYear();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 两个时间的差额，返回值的秒数
	 * @param str1 时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2 时间参数 2 格式：2009-01-01 12:00:00
	 * @param 时间格式： yyyy-MM-dd HH:mm:ss
	 * @return 返回值的秒数
	 * @throws Exception
	 */
	public static long getSimpleDistanceSecs(String str1, String str2, String formatString){
		DateFormat df = new SimpleDateFormat(formatString);
		Date one;
		Date two;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			sec = (diff / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sec;
	}
	
	/**
	 * 两个时间的差额，返回值的毫秒数
	 * @param str1 时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2 时间参数 2 格式：2009-01-01 12:00:00
	 * @param 时间格式： yyyy-MM-dd HH:mm:ss
	 * @return 返回值的秒数
	 * @throws Exception
	 */
	public static long getSimpleDistanceMilliSecs(String str1, String str2, String formatString){
		DateFormat df = new SimpleDateFormat(formatString);
		Date one;
		Date two;
		long diff = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}
	
	/**
	 * 当前日期后i天的日期
	 * @param str时间参数 1 格式：1990-01-01
	 * @param i 
	 * @param 时间格式： yyyy-MM-dd
	 */
	public static String addDays(String str, int i, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
		String after_day = "";
		// 通过格式化输出日期
		try {
			cal.setTime(df.parse(str));
			cal.add(Calendar.DAY_OF_MONTH, i);// 取当前日期的后i天
			after_day = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return after_day;
	}
	
	/**
	 * 当前日期后i小时后的时间
	 * @param str 时间参数 1 格式：1990-01-01 12:00:00
	 * @param i 
	 * @param 时间格式： yyyy-MM-dd HH:mm:ss
	 */
	public static String addHour(String str, int i, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
		String after_day = "";
		try {
			cal.setTime(df.parse(str));
			cal.add(Calendar.HOUR_OF_DAY, i);// i小时后的时间
			after_day = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return after_day;
	}
	
	/**
	 * 当前日期后i分钟后的时间
	 * @param str 时间参数 1 格式：1990-01-01 12:00:00
	 * @param i 
	 * @param 时间格式： yyyy-MM-dd HH:mm:ss
	 */
	public static String addMinutes(String str, int i, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
		String after_day = "";
		try {
			cal.setTime(df.parse(str));
			cal.add(Calendar.MINUTE, i);// i分钟后的时间
			after_day = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return after_day;
	}
	
	/**
	 * 当前日期后i秒后的时间
	 * @param str 时间参数 1 格式：1990-01-01 12:00:00
	 * @param i 
	 * @param 时间格式： yyyy-MM-dd HH:mm:ss
	 */
	public static String addSeconds(String str, int i, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
		String after_day = "";
		try {
			cal.setTime(df.parse(str));
			cal.add(Calendar.SECOND, i);// i秒后的时间
			after_day = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return after_day;
	}
	
	/**
	 * 当前日期后i毫秒后的时间
	 * @param str 时间参数 1 格式：1990-01-01 12:00:00
	 * @param i 
	 * @param 时间格式： yyyy-MM-dd HH:mm:ss
	 */
	public static String addMilliSeconds(String str, int i, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历
		String after_day = "";
		try {
			cal.setTime(df.parse(str));
			cal.add(Calendar.MILLISECOND, i);// i毫秒后的时间
			after_day = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return after_day;
	}

	/**
	 * 判断某字符串是否是日期类型
	 * @param strDate
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 使用参数Format格式化Date成字符串
	 * @param date
	 * @param formatString
	 * @return 返回日期格式化之后的字符串
	 */
	public static String format(Date date, String formatString) {
		return date == null ? " " : new SimpleDateFormat(formatString).format(date);
	}
	
	/**
	 * 使用参数Format格式化默认Date成字符串
	 * @param date
	 * @return 返回日期格式化之后的字符串
	 */
	public static String formatDate(Date date) {
		if (null == date) {
			return "";
		}
		return new SimpleDateFormat(DATETIME_DEFAULT_FORMAT).format(date);
	}
	
	/**
	 * 将20180120102050转化为2018-01-20 10:20:50
	 * @param s
	 * @return
	 */
	public static String getDate14To19(String s){
		if (s.length() == 14) {
			return s.substring(0,4) + "-" + s.substring(4,6) + "-" + s.substring(6,8) + " " + 
					s.substring(8,10) + ":" + s.substring(10,12) + ":" + s.substring(12,14);
		}
		return null;
	}

	/**
	 * 将2018-01-20 10:20:50转化为20180120102050
	 * @param s
	 * @return
	 */
	public static String getDate19To14(String s) throws ParseException {
		Date date = StringToDate(s, DATETIME_DEFAULT_FORMAT);
		String date14 = format(date, DATETIME_SIMPLE_FORMAT);
		return date14;
	}
	
	/**
	 * 将年月日转换为标准格式
	 * @param strDate
	 * @param strDate
	 * @param pattern
	 * @return 返回格式化之后的date类型
	 */
	public static Date getDate(String year, String month, String day, String formatString)
			throws ParseException {
		String result = year + "- "
				+ (month.length() == 1 ? ("0 " + month) : month) + "- "
				+ (day.length() == 1 ? ("0 " + day) : day);
		return parse(result, formatString);
	}

	/**
	 * 使用参数Format将字符串转为Date
	 *
	 * @param strDate
	 * @param pattern
	 * @return 返回格式化之后的date类型
	 */
	public static Date parse(String strDate, String formatString)
			throws ParseException {
		return StringUtil.isEmpty(strDate) ? null : new SimpleDateFormat(
				formatString).parse(strDate);
	}

	/**
	 * 字符串转化为日期
	 * @param str
	 * @param format
	 * @return date
	 * @throws ParseException
	 */
	public static Date StringToDate(String str, String formatString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		Date date = null;
		date = sdf.parse(str);
		return date;
	}
	
	/**
	 * 将时间毫秒值转换为固定格式("yyyy-MM-dd HH:mm:ss")的字符串
	 * @param lo
	 * @return
	 */
	public static String getLongToDate(long lo, String formatString){
		Date date = new Date(lo);
		SimpleDateFormat sd = new SimpleDateFormat(formatString);
		return sd.format(date);
	}
	
	/**
	 * 根据完整日期时间格式获取时间
	 * @param dateTime
	 * @param sourceFormatString
	 * @param resultFormatString
	 * @return dateStr
	 */
	public static String getTimeByComplateDateTime(String dateTime, String sourceFormatString, String resultFormatString){
		SimpleDateFormat sf = new SimpleDateFormat(sourceFormatString);
		Date date = null;
		try {
			date = sf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat df = new SimpleDateFormat(resultFormatString);//设置日期格式
		String time = df.format(date);
		return time;
	}

	/**
	 * 根据完整日期时间获取对应的毫秒数
	 * @param dateTime
	 * @param sourceFormatString
	 * @param resultFormatString
	 * @return dateStr
	 */
	public static long stringToLong(String s, String formatString){
		SimpleDateFormat sf = new SimpleDateFormat(formatString);
		Date d;
		try {
			d = sf.parse(s);
			return d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0L;
		}
	}

	/**
	 * 返回java.sql.Date类型的时间
	 * @param date
	 * @return
	 */
	public static java.sql.Date getSqlDate(Date date){
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 日期转换为时间字符串
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String dateToStr(Date date, String formatString) {
		String s = "";
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		try {
			s = sdf.format(date);
		} catch (Exception e) {
		}
		return s;
	}
	
	/**
	 * 时间字符串转换为日期
	 * @param dateStr
	 * @param formatString
	 * @return
	 */
	public static Date strToDate(String s, String formatString) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		try {
			d = sdf.parse(s);
		} catch (Exception e) {
		}
		return d;
	}
	
	public static void main(String[] args) throws Exception  {
		System.out.println(getTodayAfter(2));
		System.out.println(getSimpleDistanceTimes("09:44", "18:10", "HH:mm"));
		System.out.println(getWeekNoOfDate("2017-09-27"));
		System.out.println(getTodayAfter(-1));

		System.out.println(compareDate("09:00", "12:00", "HH:mm"));

		System.out.println(Arrays.toString(getDisMin("11:50", "12:10", "HH:mm")) );

		System.out.println("当前时间： " + getShortTime());

		//获取当前日期的15天之前的日期
		System.out.println("15 天之前的日期是：" + getDateBefore(new Date(), 15));

		//获取某一个日期后520天的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2016-02-14");
		System.out.println("当前日期的520天之后的日期是： " + getDateAfter(date, 520));

		//获取年龄
		System.out.println("My age is: " + getCurrentAgeByBirthdate("1990-05-16"));

		//获取当前月份，某一个月的最后一天、昨天、明天
		System.out.println("The current month is : " + getCurrMonth());
		System.out.println("This month the last day is " + getLastDayOfMonth("2017"));
		System.out.println("yesterday: " + getYesterday() + ", tomorrow: " + getTomorrow());

		//分别获取年月日时分秒
		System.out.println(getYear() + ", " + getMonth() + ", " + getDay());
		System.out.println(getHour() + ", " + getMinute() + ", " + getSecond());
		System.out.println(getYearByDate("2017-11-22", "yyyy-MM-dd"));
		System.out.println(getMonthByDate("2017-11-22", "yyyy-MM-dd"));
		System.out.println(getDayByDate("2017-11-22", "yyyy-MM-dd"));

		//根据完整日期时间格式获取时间
		System.out.println("根据完整日期时间格式获取时间： " + getTimeByComplateDateTime("2017-11-10 19:34:00", "yyyy-MM-dd HH:mm:ss", "HHmm"));

		//本季度的开始和结束时间
		System.out.println("本季度开始时间：" + getCurrentQuarterStartTime() + ", 本季度结束时间：" + getCurrentQuarterEndTime());
		
		System.out.println(getSimpleDistanceSecs("2017-12-19 16:30:15","2018-01-19 16:30:30", "yyyy-MM-dd HH:mm:ss"));
		System.out.println(getSimpleDistanceMilliSecs("20180104165253000","20180119165254000", "yyyyMMddHHmmssSSS"));
		System.out.println(addSeconds("20171219163015", -1, "yyyyMMddHHmms"));
		System.out.println(addMilliSeconds("20180104165253900" , 100, "yyyyMMddHHmmssSSS"));
	}

}

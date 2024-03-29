package com.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * 日期转换类
 * 
 * @date 2019年4月20日
 * @auto lihui
 */
@Slf4j
public class EDateUtil {

	public static final String PATTERN_YYYY_DD_MM = "yyyy-MM-dd";
	public static final String PATTERN_YYYYDDMM = "yyyyMMdd";
	public static final String PATTERN_YYYY_DD_MM_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final long DAY_SECOND = 1000 * 60 * 60 * 24L;
	public static final String ZONE_TIME = " 00:00:00";

	private EDateUtil() {
	}

	/**
	 * string转date
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date getDateByString(String str, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			log.error("日期转换错误", e);
			return null;
		}
	}

	/**
	 * string转long
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Long getLongByString(String str, String pattern) {
		Date d = getDateByString(str, pattern);
		if (null == d) {
			return -1L;
		}
		return d.getTime();
	}

	/**
	 * 计算s与e之间差几天
	 * 
	 * @param s
	 * @param e
	 * @return
	 */
	public static Long getBetweenByString(String s, String e, String pattern) {
		Long startLong = EDateUtil.getLongByString(s, pattern);
		Long endLong = EDateUtil.getLongByString(e, pattern);
		return ((startLong - endLong) / DAY_SECOND);
	}

	/**
	 * 获取指定日期后的第l天
	 * 
	 * @param str     20190415
	 * @param pattern PATTERN_YYYYDDMM
	 * @param l       1
	 * @return
	 */
	public static String getStringByAfterString(String str, String pattern, int l) {
		String newDateStr = "0";
		Date d = getDateByString(str, pattern);
		if (null == d) {
			log.error("{}转换错误", str);
			return newDateStr;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, l);

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		newDateStr = sdf.format(c.getTime());
		return newDateStr;
	}

	/**
	 * 获取某个日期几天后的日期
	 * 
	 * @param d    日期实例，如果为null则默认为当前系统时间
	 * @param pram 要加几天（可以是负数，表示减）
	 * @return 结果Date实例
	 */
	public static Date addDay(Date d, int pram) {
		if (d == null) {
			d = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_YEAR, pram);
		return c.getTime();
	}

	/**
	 * 获取某个日期几周后的日期
	 * 
	 * @param d    日期实例，如果为null则默认为当前系统时间
	 * @param pram 要加几周（可以是负数，表示减）
	 * @return 结果Date实例
	 */
	public static Date addWeek(Date d, int pram) {
		if (d == null) {
			d = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.WEEK_OF_YEAR, pram);
		return c.getTime();
	}

	/**
	 * 获取某个日期几月后的日期
	 * 
	 * @param d    日期实例，如果为null则默认为当前系统时间
	 * @param pram 要加几月（可以是负数，表示减）
	 * @return 结果Date实例
	 */
	public static Date addMonth(Date d, int pram) {
		if (d == null) {
			d = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, pram);
		return c.getTime();
	}

	public static String getStringByDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}

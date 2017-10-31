package com.lanwantec.manage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期字符串工具，输出格式为 2017-01-01
 */
public class DateUtil {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static Calendar cale = null;

	/** 今天日期 */
	public static String getCurrentDate() {
		return format.format(new Date());
	}

	/** 本周第一天日期，以周一为第一天 */
	public static String getFirstDateOfCurrentWeek() {
		return getDateOfCurrentWeek(1);
	}

	/** 本周最后一天日期，以周一为第一天 */
	public static String getLastDateOfCurrentWeek() {
		return getDateOfCurrentWeek(7);
	}

	/** 本周第days天的日期，以周一为第一天，days取值[1,7] */
	public static String getDateOfCurrentWeek(int days) {
		cale = Calendar.getInstance();
		if (cale.get(Calendar.DAY_OF_WEEK) == 1) {
			cale.add(Calendar.DAY_OF_WEEK, days - 7);
			return format.format(cale.getTime());
		} else {
			cale.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cale.add(Calendar.DAY_OF_WEEK, days - 1);
			return format.format(cale.getTime());
		}
	}

	/** 本月最大天数 */
	public static int getActualMaximumOfCurrentMoth() {
		cale = Calendar.getInstance();
		return cale.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/** 本月第days天日期， days取值[1,本月最大天数] */
	public static String getDateOfCurrentMoth(int days) {
		if (days > 0 && days <= getActualMaximumOfCurrentMoth()) {
			cale = Calendar.getInstance();
			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH, days);
			return format.format(cale.getTime());
		} else {
			return null;
		}
	}

	/** 本月第一天日期 */
	public static String getFirstDateOfCurrentMoth() {
		return getDateOfCurrentMoth(1);
	}

	/** 本月最后一天日期 */
	public static String getLastDateOfCurrentMonth() {
		return getDateOfCurrentMoth(getActualMaximumOfCurrentMoth());
	}

	/** 今年第一天日期 */
	public static String getFirstDateOfCurrentYear() {
		cale = Calendar.getInstance();
		cale.add(Calendar.YEAR, 0);
		cale.set(Calendar.DAY_OF_YEAR, 1);
		return format.format(cale.getTime());
	}

	/** 今年最后一天日期 */
	public static String getLastDateOfCurrentYear() {
		cale = Calendar.getInstance();
		cale.add(Calendar.YEAR, 1);
		cale.set(Calendar.DAY_OF_YEAR, 0);
		return format.format(cale.getTime());
	}

	/** 今年第month的月的日期，month取值[1,12]，输出格式为 2017-01 */
	public static String getMonthOfCurrentYear(int month) {
		cale = Calendar.getInstance();
		cale.set(Calendar.MONTH, Calendar.JANUARY);
		cale.add(Calendar.MONTH, month - 1);
		return new SimpleDateFormat("yyyy-MM").format(cale.getTime());
	}

	/** 两日期间的天数差 */
	public static int dateDiff(String startTime, String endTime) {
		try {
			Date d1 = format.parse(startTime);
			Date d2 = format.parse(endTime);
			long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			return (int) days;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/** 获取给定时间，增加days天后的时间 */
	public static String getDateAddDays(String date, int days) {
		try {
			Date parse = format.parse(date);
			cale = Calendar.getInstance();
			cale.setTime(parse);
			cale.add(Calendar.DATE, days);
			return format.format(cale.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
	}

}

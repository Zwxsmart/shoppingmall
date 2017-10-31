package com.lanwantec.manage.utils;

import java.util.Calendar;
import java.util.Random;

/**
 * ID生成工具
 */
public class IDUtils {
	private static int seed = -1;
	private static String initDate = "";
	private static boolean isInit = false;
	private static int flowSeed = -1;
	private static String initFlowDate = "";
	private static boolean isFlowInit = false;
	private static long baseTimeMills = 1446307239260l / 6000;
	private static long baseTimeMills_flow = 1446307239260l / 1000;

	private static void initCreateUserNo() {
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);
		int currentDay = cal.get(Calendar.DATE);
		int currentHour = cal.get(Calendar.HOUR_OF_DAY);
		int currentMinute = cal.get(Calendar.MINUTE);
		cal.set(currentYear, currentMonth, currentDay, currentHour, currentMinute);
		isInit = true;
		initDate = String.format("%tF%tR", cal.getTime(), cal.getTime());
	}

	/** 生成用户编号(12位) */
	public static String createUserNo() {
		if (isInit == false) {
			initCreateUserNo();
		}
		Calendar cur = Calendar.getInstance();
		long cTime = cur.getTimeInMillis() / 6000;
		String curDate = String.format("%tF%tR", cur.getTime(), cur.getTime());
		String uniquenessNo = "";
		if (!curDate.equalsIgnoreCase(initDate) && seed >= 99) {
			initDate = curDate;
			seed = 0;
		}
		synchronized (uniquenessNo) {
			long timeLength = cTime - baseTimeMills;
			seed++;
			Random r = new Random();
			uniquenessNo = String.format("%08d%02d%02d", timeLength, seed, r.nextInt(99));
		}
		return uniquenessNo;
	}

	private static void initCreateFlowNo() {
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);
		int currentDay = cal.get(Calendar.DATE);
		int currentHour = cal.get(Calendar.HOUR_OF_DAY);
		int currentMinute = cal.get(Calendar.MINUTE);
		cal.set(currentYear, currentMonth, currentDay, currentHour, currentMinute);
		isInit = true;
		initFlowDate = String.format("%tF%tR", cal.getTime(), cal.getTime());
	}

	/** 生成指定字符前缀的编号(共16位) */
	public static String createFlowNo(char prefix) {
		if (isFlowInit == false) {
			initCreateFlowNo();
		}
		Calendar cur = Calendar.getInstance();
		long cTime = cur.getTimeInMillis() / 1000;
		String curDate = String.format("%tF%tR", cur.getTime(), cur.getTime());
		String uniquenessNo = "";
		if (!curDate.equalsIgnoreCase(initFlowDate) && flowSeed >= 999) {
			initFlowDate = curDate;
			flowSeed = 0;
		}
		synchronized (uniquenessNo) {
			long timeLength = cTime - baseTimeMills_flow;
			flowSeed++;
			Random r = new Random();
			uniquenessNo = prefix + String.format("%09d%03d%03d", timeLength, flowSeed, r.nextInt(999));
		}
		return uniquenessNo;
	}

}

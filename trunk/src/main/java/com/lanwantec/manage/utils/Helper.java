package com.lanwantec.manage.utils;

import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

public class Helper {

	/**
	 * 生成验证码
	 * 
	 * @param length
	 *            生成的字符长度
	 * @return
	 */
	public static String createCode(int length) {
		Random rand = new Random();
		String code = "";
		for (int i = 0; i < length; i++) {
			int temp = rand.nextInt(10);
			code = code + temp;
		}
		return code;
	}

	/**
	 * 校验手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		return phone.matches("^(1[0-9][0-9])\\d{8}$");
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		return email.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
	}

	/**
	 * 进制转换
	 * 
	 * @param val
	 * @param digits
	 * @return
	 */
	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		// return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX).substring(1);
		return DigitTools.numericToString(hi | (val & (hi - 1)), DigitTools.MAX_RADIX).substring(1);
	}

	/** 以62进制（字母加数字）生成19位UUID，最短的UUID */
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();
		sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
		sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
		sb.append(digits(uuid.getMostSignificantBits(), 4));
		sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
		sb.append(digits(uuid.getLeastSignificantBits(), 12));
		return sb.toString();
	}

	/** 获取cookie里的token */
	public static String getToken(HttpSession session) {
		Object attr = session.getAttribute(Constants.ACCESS_TOKEN);
		return attr == null ? null : attr.toString();
	}

}

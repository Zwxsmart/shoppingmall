package com.lanwantec.manage.utils;


/**
 * 进制转换工具(可以最大转换62进制）
 * @author lys
 * @since 2016-02-22
 *
 */
public class DigitTools {

	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',  
        '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',  
        'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',  
        'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',  
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',  
        'Z' };  
	
	/**
	 * 支持的最大进制数
	 */
	public static final int MAX_RADIX = digits.length;

	/**
	 * 支持的最小进制数
	 */
	public static final int MIN_RADIX = 2;

	/**
	 * 将十进制的数字转换为指定进制的字符串。
	 * 
	 * @param i
	 *            十进制的数字。
	 * @param system
	 *            指定的进制，常见的2/8/16。
	 * @return 转换后的字符串。
	 */
	public static String numericToString(long i, int system) {
		long num = 0;
		if (i < 0) {
			num = ((long) 2 * 0x7fffffff) + i + 2;
		} else {
			num = i;
		}
		char[] buf = new char[64];
		int charPos = 64;
		while ((num / system) > 0) {
			buf[--charPos] = digits[(int) (num % system)];
			num /= system;
		}
		buf[--charPos] = digits[(int) (num % system)];
		return new String(buf, charPos, (64 - charPos));
	}

	/**
	 * 将其它进制的数字（字符串形式）转换为十进制的数字。
	 * 
	 * @param s
	 *            其它进制的数字（字符串形式）
	 * @param system
	 *            指定的进制，常见的2/8/16。
	 * @return 转换后的数字。
	 */
	public static long stringToNumeric(String s, int system) {
		char[] buf = new char[s.length()];
		s.getChars(0, s.length(), buf, 0);
		long num = 0;
		for (int i = 0; i < buf.length; i++) {
			for (int j = 0; j < digits.length; j++) {
				if (digits[j] == buf[i]) {
					num += j * Math.pow(system, buf.length - i - 1);
					break;
				}
			}
		}
		return (long) num;
	}

}

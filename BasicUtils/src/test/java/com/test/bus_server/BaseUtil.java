package com.test.bus_server;

import java.security.MessageDigest;

public class BaseUtil{

	/**
	 * 
	 * @param target
	 * @param ch
	 * @param iLen
	 * @param isLeft
	 * @return 指定字符左或右补齐字符串
	 */
	public static String getFixStr(String target, char ch, int iLen, boolean isLeft) {
		int perlen = target.length();
		if (iLen < perlen) {
			return target.substring(perlen - iLen, perlen);
		}
		for (int i = 0; i < iLen - perlen; i++) {
			if (isLeft) {
				target = ch + target;
			} else {
				target = target + ch;
			}
		}
		return target.toUpperCase();
	}

	/**
	 * @param i
	 * @return 整数变成十六进制字符串,并自动补齐偶数个字符
	 */
	public static String Integer2HexString(int i, int length) {
		String hex = Integer.toHexString(i);
		if (hex.length() % 2 > 0) {
			hex = '0' + hex;
		}
		return getFixStr(hex, '0', length, true);
	}

	/**
	 * @param b
	 * @return 单个字节变成2个十六进制字符串
	 */
	public static String Byte2HexString(byte b) {
		String ret = "";
		String hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		ret = hex.toUpperCase();
		return ret;
	}

	/**
	 * 把字符串左边补齐规定的长度
	 * 
	 * @param str
	 *            需要补齐的字符串
	 * @param len
	 *            需要补到多长
	 * @param c
	 *            补得字符
	 * @return 补齐后的字符串
	 */
	public static String padLeft(String str, int len, char c) {
		int sLen = str.length();
		for (int i = sLen; i < len; i++) {
			str = c + str;
		}
		return str.toUpperCase();
	}

	public static byte[] hex2bytes(String s) {
		int length = s.length() / 2;
		byte[] result = new byte[length];
		for (int n = 0; n < length; n++) {
			String item = s.substring(2 * n, 2 * (n + 1));
			result[n] = (byte) Integer.parseInt(item, 16);
		}
		return result;
	}

	public static byte[] getAsciiBytes(String input) {
		char[] c = input.toCharArray();
		byte[] b = new byte[c.length];
		for (int i = 0; i < c.length; i++) {
			b[i] = (byte) (c[i] & 0x007F);
		}
		return b;
	}

	/** */
	/** 
	* @函数功能: BCD码转为10进制串(阿拉伯数据) 
	* @输入参数: BCD码 
	* @输出结果: 10进制串 
	*/
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	public static String Bytes2HexStringBigFirst(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/** 
	* @功能: 10进制串转为BCD码 
	* @参数: 10进制串 
	* @结果: BCD码 
	*/
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * 截取字符串后几位
	 */
	public static String subString(String str, int length) {
		if (str.length() > length) {
			return str.substring(str.length() - length);
		} else {
			return padLeft(str, length, '0');
		}

	}

	// 对字符串进行MD5编码
	public static String encodeByMD5(String originstr) {
		if (originstr != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后的更新，然后完成摘要计算
				byte[] results = md.digest(originstr.getBytes());
				// 将得到的字节数组编程字符窜返回
				return Bytes2HexStringBigFirst(results).toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	// 对字符串进行MD5编码
	public static byte[] encodeByMD5Byte(String originstr) {
		if (originstr != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后的更新，然后完成摘要计算
				return md.digest(originstr.getBytes());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

}

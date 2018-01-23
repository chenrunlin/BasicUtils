package com.bsit.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F' };

	public static String md5(String inputStr){
		byte[] inStrBytes = inputStr.getBytes();
		try {
			MessageDigest MD = MessageDigest.getInstance("MD5");
			MD.update(inStrBytes);
			byte[] mdByte = MD.digest();
			char[] str = new char[mdByte.length * 2];
			int k = 0;
			for (int i = 0; i < mdByte.length; i++) {
				byte temp = mdByte[i];
				str[k++] = hexDigits[temp >>> 4 & 0xf];
				str[k++] = hexDigits[temp & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		String pwd1 ="123456";
		String pwd2 ="12345678";
		System.out.println(MD5Util.md5(pwd1));
		System.out.println(MD5Util.md5(pwd2));
	}

}

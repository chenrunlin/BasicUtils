package com.test.simple_test;

import java.io.UnsupportedEncodingException;

public class StringTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] b = new byte[] { 'a', 'b', 'c' };
		String s = new String(b, "utf-8");
		String s1 = new String(b, "GBK");
		byte[] bt = "中文".getBytes();
		String s2 = new String(bt,"utf-8");
		String s3 = new String(bt,"GBK");		
		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		
		String posDate = "16100901";
		String date = "20161009";
		System.out.println(posDate.substring(0, 6));
		System.out.println(date.equals("20" + posDate.substring(0, 6)));
		
	}
}

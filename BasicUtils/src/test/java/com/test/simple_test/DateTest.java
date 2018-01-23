package com.test.simple_test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		System.out.println(getDate());
		System.out.println(getDate());
		System.out.println(getDate());
		System.out.println(getDate());
		System.out.println(getDate());
	}
	
	public static String getDate(){
		SimpleDateFormat df = new SimpleDateFormat("HHmmssSSS");// 设置日期格式
		String date = df.format(new Date());
		return date;
	}
}

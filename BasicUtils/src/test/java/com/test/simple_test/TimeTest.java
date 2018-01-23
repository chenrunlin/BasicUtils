package com.test.simple_test;

import com.bsit.utils.DateUtil;

public class TimeTest {

	public static void main(String[] args) {
		System.out.println(DateUtil.getCurrentDateTime());
		long startTimeMillis = System.currentTimeMillis();
		// 增加CPU的繁忙程度,不必关心其逻辑含义
        for (int i = 0; i < 200000000; i++) {
            Math.hypot(Math.pow(92456789, i), Math.cos(i));
        }
		long endTimeMillis = System.currentTimeMillis();
		long interval = (endTimeMillis - startTimeMillis) / (1000 * 60);
		System.out.println("startTimeMillis：" + startTimeMillis + " --- " + "endTimeMillis：" + endTimeMillis);
		System.out.println("时间间隔：" + (endTimeMillis - startTimeMillis));
		System.out.println("时间间隔：" + interval + "分钟");
		System.out.println(DateUtil.getCurrentDateTime());
		
	}

}

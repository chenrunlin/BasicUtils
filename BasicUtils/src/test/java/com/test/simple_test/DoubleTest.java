package com.test.simple_test;

import java.text.NumberFormat;

public class DoubleTest {
	
	public static void main(String[] args) {
		
		double s1 = Double.parseDouble("300152") + 600;
		int s2 = 311752;
		double result = (double)s1 / s2;
		System.out.println("小数：" + result);
		//获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		//设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		//最后格式化并输出
		System.out.println("百分数：" + nt.format(result));
	}

}

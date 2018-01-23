package com.test.simple_test;

public class MathTest {
	/**
	 * 向上取整测试
	 */
	public static void main(String[] args) {
		double d = 1.2;
		double e = 1.6;
		System.out.println(Math.ceil(d) + "  " + Math.ceil(e));
		int a = (int) Math.ceil(43767 / 400.00);
		System.out.println(a);
	}
}

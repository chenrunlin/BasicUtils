package com.test.simple_test;

public class ExceptionSeqTest {

	public static void main(String[] args) {
		int result = 0;
		int resultNum = 0;
		boolean flag = false;// 上传成功与否的标志
		for (int i = 0; i < 5; i++) {
			result++;
			try {
				resultNum = Integer.parseInt("sa");
				System.out.println("异常之后执行");
			} catch (Exception e) {
				System.out.println("转换异常");
				continue ;
			}
			if (0 == resultNum) {
				flag = true;// 上传成功
				break;
			}
		}
		
		if (flag) {
			System.out.println("1 : " + flag + " . " + result);
		} else {
			System.out.println("2 ： " + flag + " . " + result);
		}
		
	}
	
}

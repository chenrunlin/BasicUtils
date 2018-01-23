package com.test.simple_test;

import java.util.Arrays;

public class ByteTest {
	
	public static void main(String[] args) {
		byte[] bt = null;
		for(int i = 0; i < 2; i++) {
			if(i == 0) {
				bt = "hello".getBytes();
				System.out.println(Arrays.toString(bt));
			}
			if(i == 1) {
				bt = "world".getBytes();
				System.out.println(Arrays.toString(bt));
			}
		}
		
		String str1 = "00000001";
		String str2 = "00980E94";
		int sum = Integer.parseInt(str1, 16) + Integer.parseInt(str2, 16);
		System.out.println(Integer.toHexString(sum));
		
	}
}

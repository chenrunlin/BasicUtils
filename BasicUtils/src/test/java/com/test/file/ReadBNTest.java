package com.test.file;

import java.io.DataInput;
import java.io.File;
import java.io.RandomAccessFile;

public class ReadBNTest {
	
	public static void main(String[] args) {
		File file = new File("f:\\BN170911030007044982800001000000A");
		readBN(file);
	}
	
	//白名单下发文件
	private static int readBN(File file){
		int result = 0;
		try {
			RandomAccessFile in = new RandomAccessFile(file, "r");
			in.seek(4);
			int size = Integer.valueOf(readString(6, in).trim());
			for (int i = 0; i < size; i++) {
				 in.seek(i * 23 + 32);
				 String cityid = readString(11, in).trim();
				 String kh = readString(10, in);
				 result++; 
				 System.out.println(cityid + " " + kh);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result : " + result);
		return result;
	}
		
	// 读入最大长度为size的字符串
	private static String readString(int size, DataInput in) throws Exception {
		byte bytes []= new byte[size];
		for (int i = 0; i < size; i++) {
			bytes[i] = in.readByte();
		}
		return new String(bytes, "gbk");
	}

}

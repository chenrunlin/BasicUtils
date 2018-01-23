package com.bsit.splitFile;

import java.io.File;
import java.io.IOException;

public class SplitFileTest {

	public static void main(String[] args) {
		
		File fileBinary = new File("f:/jquery.easyui.min.js");
		
		splitFileByBinary(fileBinary);
		
		File fileText = new File("f:/ftp.txt");
		
		splitFileByText(fileText);
	}
	
	/**
	 * 二进制文件分割
	 * 2016年4月13日下午2:37:29
	 */
	public static void splitFileByBinary(File file){
		PartitionBinary p = new PartitionBinary();
		String strs[];
		try {
			//获取文件长度
			long length = p.getFileLength(file);
			//文件分几段
			int num = p.getPartitionFileNum(length);
			//分割文件
			strs = p.partitionFile(file, num);
			for (String string : strs) {
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void splitFileByText(File file){
		PartitionTextFile p = new PartitionTextFile();
		String strs[];
		try {
			//获取文件长度
			long length = p.getFileLength(file);
			//文件分几段
			int num = p.getPartitionFileNum(length);
			//分割文件
			strs = p.partitionFile(file, num);
			for (String string : strs) {
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

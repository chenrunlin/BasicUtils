package com.test.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.bsit.utils.Bytes;


public class BinaryFileReadTest {

	public static void main(String[] args) {
		List<String> cardNoList = readFile();
		if(cardNoList == null) {
			System.out.println("");
		}
	}
	
	public static List<String> readFile(){
		try {
			RandomAccessFile randomFile = new RandomAccessFile("E:\\test\\abc.csn", "rw");
			long fileLen = randomFile.length();
			System.out.println("file length:" + fileLen);
			randomFile.seek(0);
			// 读文件的起始位置
			int beginIndex = 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[11];
			int byteread = 0;
			int n = 0;
			// 一次读1024个字节，如果文件内容不足1024个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			int count = 0;
			List<String> cardNoList = new ArrayList<String>();
			while ((byteread = randomFile.read(bytes)) != -1) {
				count++;
				byte[] cardNoBy = new byte[10];
				byte[] cardActionBy = new byte[1];
				System.arraycopy(bytes, 0, cardNoBy, 0, 10);
				System.arraycopy(bytes, 10, cardActionBy, 0, 1);
				String cardNo = Bytes.BCD_To_Str(cardNoBy);
				String cardAction = Bytes.Bytes2HexString(cardActionBy);
				cardNoList.add(cardNo + cardAction);
				System.out.println(count + "," + "cardNo:" + cardNo + ",cardAction:" + cardAction);
				bytes = new byte[11];
				n += byteread;
			}
			randomFile.close();
			System.out.println("文件总的字节数：" + n);
			return cardNoList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
}

package com.test.simple_test;

public class FileNameTest {
	
	public static void main(String[] args) {
		
		String fileFullName = "112244557788999028.par";
		String fileName = fileFullName.substring(0, 15);
		String fileVer = fileFullName.substring(15, 18);
		String fileSuff = fileFullName.substring(18);
		System.out.println(fileName + " : " + fileVer + " : " + fileSuff); 
	}

}

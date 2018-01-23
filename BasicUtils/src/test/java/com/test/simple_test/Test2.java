package com.test.simple_test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
	
	public static void main(String[] args) {
		
		System.out.println(Integer.parseInt("0000012042"));
		
		System.out.println("----------------");
		
		File file = new File("E\\:/home/island/bsit/equ_param/11223344556677703.pro");
		
		String currFileName = file.getName();
		String currFileDir = file.getPath();
		String companyId = currFileName.substring(0, 15);
		String currFileVer = currFileName.split("\\.")[0].substring(15, currFileName.indexOf("."));
		
		System.out.println(currFileDir + " : " + currFileName);
		System.out.println(companyId + " : " + currFileVer);
		
		System.out.println(currFileName.startsWith("112233445566777"));
		
		byte[] fileHead = "*\\\\par.0=".getBytes();	//9
		
		System.out.println(fileHead.length + "    " + new String(fileHead));
		
		List<String> list1 = new ArrayList<String>();
		list1.add("11223344555667788");
		list1.add("2233445566778899");
		List<String> list2 = new ArrayList<String>();
		list2.add("2233445566778899");
		System.out.println(list1.get(0));
		
		System.out.println(Integer.parseInt("00000000000011"));
	}
}

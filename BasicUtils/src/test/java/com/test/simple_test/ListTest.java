package com.test.simple_test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	
	public static void main(String[] args) {
		
		List<String> listStr1 = new ArrayList<String>();
		List<String> listStr2 = new ArrayList<String>();
		
		listStr1.add("112244557788999");
		listStr1.add("123456789");
		listStr2.add("999999999999999");
		listStr2.add("      123456789");
		
		System.out.println(listStr1.get(0) + listStr2.get(0));
		
		System.out.println(listStr1.get(1).length() + " . " + listStr2.get(1).length());
		
		
		List<String> listStr11 = new ArrayList<String>();
		listStr11.add("13572484225");
		listStr11.add("13572484225");
		listStr11.add("13572484225");
		System.out.println(listStr11.size());
	}

}

package com.bsit.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StringUtil {
	
	private static Logger logger = LogManager.getLogger(StringUtil.class);
	
	public static boolean isEmpty(String str) {
		if (null == str) {
			return true;
		} else if ("".equals(str) || "".equals(str.trim()) || str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 字符编码
	 * @see 该方法默认会以UTF-8编码字符串
	 */
	public static String encode(String chineseStr){
        return encode(chineseStr, "UTF-8");
    }
	
	/**
     * 字符编码
     * @see 该方法通常用于对中文进行编码
     * @see 若系统不支持指定的编码字符集,则直接将chineseStr原样返回
     */
	public static String encode(String chineseStr, String charset){
		chineseStr = (chineseStr == null ? "" : chineseStr);
        try {
            return URLEncoder.encode(chineseStr, charset);
        } catch (UnsupportedEncodingException e) {
        	logger.error("编码字符串[" + chineseStr + "]时发生异常:系统不支持该字符集[" + charset + "]");
            return chineseStr;
        }
    }
	
	/**
     * 字符解码
     * @see 该方法默认会以UTF-8解码字符串
     */
	public static String decode(String chineseStr){
        return decode(chineseStr, "UTF-8");
    }
	
	/**
     * 字符解码
     * @see 该方法通常用于对中文进行解码
     * @see 若系统不支持指定的解码字符集,则直接将<code>chinese</code>原样返回
     */
    public static String decode(String chineseStr, String charset){
    	chineseStr = (chineseStr == null ? "" : chineseStr);
        try {
            return URLDecoder.decode(chineseStr, charset);
        } catch (UnsupportedEncodingException e) {
        	logger.error("解码字符串[" + chineseStr + "]时发生异常:系统不支持该字符集[" + charset + "]");
            return chineseStr;
        }
    }
    
    /**
     * 总长度为n，不够在初始字符串s前面补"0"
     * @param s 初始字符串s
     * @param num 最终字符串总长度
     * @return 最终字符串
     */
    public static String prefixZero(String s, int num) {
		String oriStr = "";
		for (int i = 0; i < num; i++) {
			oriStr += "0";
		}
		int len = s.length();
		int n = oriStr.length() - len;
		return oriStr.substring(0, n) + s;
	}
    
    /**
     * 总长度为n，不够在初始字符s串前面补r
     * @param s 初始字符串s
     * @param r 要补的字符
     * @param num 最终字符串总长度
     * @return 最终字符串
     */
	public static String prefixString(String s, String r, int num) {
		String oriStr = "";
		for (int i = 0; i < num; i++) {
			oriStr += r;
		}
		int len = s.length();
		int n = oriStr.length() - len;
		return oriStr.substring(0, n) + s;
	}
	
	/**
     * 总长度为n，不够在初始字符s串后面面补r
     * @param s 初始字符串s
     * @param r 要补的字符
     * @param num 最终字符串总长度
     * @return 最终字符串
     */
	public static String suffixString(String s, String r, int num) {
		String oriStr = "";
		for (int i = 0; i < num; i++) {
			oriStr += r;
		}
		int len = s.length();
		int n = num - (oriStr.length() - len);
		return s + oriStr.substring(n);
	}
	
	public static void main(String[] args) {
		System.out.println(prefixZero("1234567890", 16));			// 0000001234567890
		System.out.println(prefixString("1234567890", "F", 16));	// FFFFFF1234567890
		System.out.println(suffixString("1234567890", "F", 16));	// 1234567890FFFFFF
		String str1 = "周伟龙,15389632598,4000.0,,,200.0,200.0,200.0,200.0,300.0,550.56,600.55,20.2,,";
		String str2 = "周伟龙,15389632598,4000.0,,,200.0,200.0,200.0,200.0,300.0,550.56,600.55,20.2,8000,";
		testSplit1(str1);
		testSplit2(str2);
	}
	
	public static void testSplit1(String str){
		String[] salary1 = str.split(",");
		System.out.println(salary1.length);
		String[] salary2 = str.split(",", 13);
		System.out.println(salary2.length);
		System.out.println("*************");
	}
	public static void testSplit2(String str){
		System.out.println("*************");
		String[] salary3 = str.split(",");
		System.out.println(salary3.length);
		String[] salary4 = str.split(",", 13);
		System.out.println(salary4.length);
	}

}

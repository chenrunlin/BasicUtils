package com.test.bus_server;

public class Content {
	
	//帧头,帧尾
	public static final byte[] TM = {0x7F};
	
	//格式类型标识，'B'（0x42）：Binary 报文
	public static final byte[] FTI = {0x42};
	
	//无符号整数 INT32，二进制报文固定为 0
	public static final byte[] DBL = {0x00, 0x00, 0x00, 0x00};
	
	//请求类型标识：'R'（0x52）：请求（Request） ；'A'（0x41）：回应（Answer）
	public static final byte[] RTI = {0x52};
	
	//状态标识：0x00：正常处理，0x01：捕获未处理异常，0x02：未能支持的报文 ，0x03：CRC 校验错误
	public static final byte[] SI = {0x00};
	
	///是否校验：0：表示不校验，1：表示校验，2：3DES加密
	public static final byte[] SW = {0x00};
	
	//CRC16校验：二进制数据域的 CRC 校验，默认：0 
	public static final byte[] CRC = {0x00, 0x00, 0x00, 0x00};
	
	//保留域
	public static final byte[] RESERVE = {0x00};
	

}

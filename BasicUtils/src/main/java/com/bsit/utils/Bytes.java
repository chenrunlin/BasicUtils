package com.bsit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bytes {
	
	private final static byte[] hex = "0123456789ABCDEF".getBytes();
	
	private final static String[] binaryArray = { 
		"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", 
		"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
	
	private static int parse(char c) {  
        if (c >= 'a')  
            return (c - 'a' + 10) & 0x0f;  
        if (c >= 'A')  
            return (c - 'A' + 10) & 0x0f;  
        return (c - '0') & 0x0f;  
    } 
	
	// 从字节数组到十六进制字符串转换  
    public static String Bytes2HexString(byte[] b) {  
        byte[] buff = new byte[2 * b.length];  
        for (int i = 0; i < b.length; i++) {  
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];  
            buff[2 * i + 1] = hex[b[i] & 0x0f];  
        }  
        return new String(buff);  
    }  
    
    // 从十六进制字符串到字节数组转换  
    public static byte[] HexString2Bytes(String hexstr) {  
        byte[] b = new byte[hexstr.length() / 2];  
        int j = 0;  
        for (int i = 0; i < b.length; i++) {  
            char c0 = hexstr.charAt(j++);  
            char c1 = hexstr.charAt(j++);  
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));  
        }  
        return b;  
    }  
    
    //字符串转换成十六进制字符串 
    public static String String2HexString(String str) {  
        char[] chars = "0123456789ABCDEF".toCharArray();  
        StringBuilder sb = new StringBuilder("");  
        byte[] bs = str.getBytes();  
        int bit;  
        for (int i = 0; i < bs.length; i++) {  
            bit = (bs[i] & 0x0f0) >> 4;  
            sb.append(chars[bit]);  
            bit = bs[i] & 0x0f;  
            sb.append(chars[bit]);  
        }  
        return sb.toString();  
    }  
    
    //十六进制字符串转换字符串 
    public static String HexString2String(String hexStr) {  
        String str = "0123456789ABCDEF";  
        char[] hexs = hexStr.toCharArray();  
        byte[] bytes = new byte[hexStr.length() / 2];  
        int n;  
        for (int i = 0; i < bytes.length; i++) {  
            n = str.indexOf(hexs[2 * i]) * 16;  
            n += str.indexOf(hexs[2 * i + 1]);  
            bytes[i] = (byte) (n & 0xff);  
        }  
        return new String(bytes);  
    } 
    
    //位图信息（字节码）解析成二进制字符串
  	@SuppressWarnings("unused")
	private static String bytes2BinaryStr(byte[] bArray) {
  		String outStr = "";
  		int pos = 0;
  		for (byte b : bArray) {
  			// 高四位
  			pos = (b & 0xF0) >> 4;
  			outStr += binaryArray[pos];
  			// 低四位
  			pos = b & 0x0F;
  			outStr += binaryArray[pos];
  		}
  		return outStr;
  	}
  	
  	//单个字节压缩成BCD
    public static byte ASCII_To_BCD(byte asc)
    {
        byte bcd;
        
        if ((asc >= '0') && (asc <= '9'))
            bcd = (byte)(asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            bcd = (byte)(asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            bcd = (byte)(asc - 'a' + 10);
        else
            bcd = (byte)(asc - 48);
        return bcd;
    }
    
    //压缩成BCD码
    public static byte[] ASCII_To_BCD(byte[] ascii)
    {
    	int asc_len = ascii.length;
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++)
        {
            bcd[i] = ASCII_To_BCD(ascii[j++]);
            bcd[i] = (byte)(((j >= asc_len) ? 0x00 : ASCII_To_BCD(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }
    
    //BCD码解压缩成string
    public static String BCD_To_Str(byte[] bytes)
    {
        char temp[] = new char[bytes.length * 2], val;
        
        for (int i = 0; i < bytes.length; i++)
        {
            val = (char)(((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char)(val > 9 ? val + 'A' - 10 : val + '0');
            
            val = (char)(bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char)(val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }
    
    public static byte[] sumList(List<byte[]> list) {
		int length = 0;
		for (byte[] b : list) {
			length += b.length;
		}
		byte[] bb = new byte[length];
		int index = 0;
		for (byte[] b : list) {
			System.arraycopy(b, 0, bb, index, b.length);
			index += b.length;
		}
		return bb;
	}
    
    public static void main(String[] args) throws Exception {  
    	String source = "hello world";
    	System.out.println("字符串：" + source);
    	
    	byte[] bt = source.getBytes();
    	System.out.println("字符串的字节数组：" + Arrays.toString(bt));
    	
        //字节数组转16进制字符串
        String hexString = Bytes2HexString(bt);
        System.out.println("字节数组转16进制字符串：" + hexString);  
        
        //字符串转16进制字符串
        String hexStr = String2HexString(source);
        System.out.println("字符串转16进制字符串：" + hexStr);
        
        //16进制字符串转字符串
        String str = HexString2String("68656C6C6F20776F726C64");
        System.out.println("16进制字符串转字符串：" + str);
        
        //16进制字符串转字节数组
        byte[] b = HexString2Bytes(hexString);
        System.out.println("16进制字符串转字节数组：" + Arrays.toString(b));
        
/*        byte[] fileContent = createFileContent();
        System.out.println(Arrays.toString(fileContent));
        String fileName = "e:\\home/island/bsit/equ_param/abc.par";
        FileUtil.writeFileData(fileContent, fileName);*/
        
        System.out.println(Integer.toHexString(9965204));
        System.out.println(Integer.parseInt("00980E94", 16));
        
    }  
    
    //构造参数文件
    public static byte[] createFileContent() {
		byte[] fileHead = "*\\\\par.0=".getBytes();	//9
		byte[] paramVer = ASCII_To_BCD("00000000000000".getBytes());	//7
		byte[] transLimit = ASCII_To_BCD("000000001000".getBytes());	//6
		byte[] transMaxNums = ASCII_To_BCD("1000".getBytes());			//2
		byte[] timeInterval = ASCII_To_BCD("0010".getBytes());			//2
		byte[] batchUploadNum = ASCII_To_BCD("10".getBytes());			//1
		byte[] timeout = ASCII_To_BCD("10".getBytes());					//1
		byte[] acceptCardType = HexString2Bytes("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");	//32
		byte[] activitySeq = ASCII_To_BCD("0001".getBytes());			//2
		byte[] serverIp = HexString2Bytes("C0A8012e");
//		byte[] serverIp = HexString2Bytes("0A1521CA");
		byte[] firstPer= ASCII_To_BCD("040100103000".getBytes());
		byte[] firstMoney = HexString2Bytes(StringUtil.prefixString(Integer.toHexString(500), "0", 8));
		//byte[] firstMoney = ASCII_To_BCD(StringUtil.prefixString("350", "0", 8).getBytes());
		byte[] secondPer= ASCII_To_BCD("103100133000".getBytes());
		byte[] secondMoney = HexString2Bytes(StringUtil.prefixString(Integer.toHexString(0), "0", 8));
		//byte[] secondMoney = ASCII_To_BCD(StringUtil.prefixString("1550", "0", 8).getBytes());
		byte[] thirdPer= ASCII_To_BCD("133100203000".getBytes());
		byte[] thirdMoney = HexString2Bytes(StringUtil.prefixString(Integer.toHexString(0), "0", 8));
		//byte[] thirdMoney = ASCII_To_BCD(StringUtil.prefixString("2050", "0", 8).getBytes());
		byte[] fourthPer= ASCII_To_BCD("203100040100".getBytes());
		byte[] fourthMoney = HexString2Bytes(StringUtil.prefixString(Integer.toHexString(1000), "0", 8));
		//byte[] fourthMoney = ASCII_To_BCD(StringUtil.prefixString("1000", "0", 8).getBytes());
		byte[] fileFoot = "//*".getBytes();	//3
		List<byte[]> list = new ArrayList<byte[]>();
		list.add(fileHead);
		list.add(paramVer);
		list.add(transLimit);
		list.add(transMaxNums);
		list.add(timeInterval);
		list.add(batchUploadNum);
		list.add(timeout);
		list.add(acceptCardType);
		list.add(activitySeq);
		list.add(serverIp);
		list.add(firstPer);
		list.add(firstMoney);
		list.add(secondPer);
		list.add(secondMoney);
		list.add(thirdPer);
		list.add(thirdMoney);
		list.add(fourthPer);
		list.add(fourthMoney);
		list.add(fileFoot);
		return sumList(list);
	}
    
}

package com.bsit.encrpt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.bsit.utils.Bytes;

public class DES3Util {
	
	public static final String key = "0B2358E74C4D9102";
	
	/**
	 * 加密工作秘钥
	 * @param source	原串
	 * @param key		秘钥
	 * @return
	 * @throws UnsupportedEncodingException
	 * byte[]
	 * @author runlin.chen
	 * @date 2016年5月30日下午4:27:23
	 */
	@SuppressWarnings("unused")
	private static byte[] encrypt(String source) throws UnsupportedEncodingException
    {
        byte[] keyData = key.getBytes("utf-8");
        byte[] sourceData = source.getBytes("utf-8");
        //加密
        byte[] one = EncryptUtil.triplDes(sourceData, keyData);
        return one;
    }
    
    /**
     * 登陆解密
     * 64位---压缩成BCD
     * @param encryptData  加密过后的字符串
     * @param key	
     * @return String
     * @author runlin.chen
     * @date 2016年5月30日下午5:22:13
     */
    public static String decrypt(String encryptData) throws UnsupportedEncodingException
    {
    	byte[] data = encryptData.getBytes("utf-8");
    	byte[] dataBCD = Bytes.ASCII_To_BCD(data);
        byte[] keyData = key.getBytes("utf-8");
        //解密
        byte[] keyOne = EncryptUtil.tripleUnDes(dataBCD, keyData);
        String returnKey = new String(keyOne,"utf-8");
        
        return returnKey;
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException
    {
    	//解密：登录
    	String mi1 = "0AD63EA1FB13E1006DAF99BF623C5500A6A2BB99F574AC4D5F7254749D4CC936";
    	String str1 = decrypt(mi1);//明文：11135724842250612345600000000000
    	System.out.println(str1);
    	
    	//解密：保存交易记录
    	String mi2 = "5020BA3693CD91496BE8156543D0FE76596734708D44CF045F7254749D4CC936";
    	String str2 = decrypt(mi2);//密文：0456306671135724842250A8C9D120001000000000000000
    	System.out.println(str2);
    	
    	String resource = "04563066717787588600001000000000";
    	Map<String, String> map = new HashMap<String, String>();
		int n = 0;
		String merchantNo = resource.substring(0, n + 10);
		n += 10;
		String checkCode = resource.substring(n, n + 8);
		n += 8;
		String amount = resource.substring(n, n + 8);
		map.put("merchantNo", merchantNo);
		map.put("checkCode", checkCode);
		map.put("amount", amount);
		for (String key : map.keySet()) {
			System.out.println("key= "+ key + " and value= " + map.get(key));
		}
    }
}

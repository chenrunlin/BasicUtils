package com.bsit.encrpt;

import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptUtil
{
    
    private static final String ALGORITHM = "DES";
    
    /**
     * 3des加密
     */
    public static byte[] triplDes(byte[] keySet, byte[] keyBcd)
    {
        // 解密密钥BCD码前8位
        byte[] keyBcdOne = distributeBytes(keyBcd, 0);
        // 解密密钥BCD码后8位
        byte[] keyBcdTwo = distributeBytes(keyBcd, 1);
        
        byte[] tempStr1 = encrypt(keySet, keyBcdOne);
        byte[] tempStr2 = decrypt(tempStr1, keyBcdTwo);
        byte[] tempStr3 = encrypt(tempStr2, keyBcdOne);
        
        return tempStr3;
    }
    
    /**
     * 3des解密
     */
    public static byte[] tripleUnDes(byte[] keySet, byte[] keyBcd)
    {
        // 解密密钥BCD码前8位
        byte[] keyBcdOne = distributeBytes(keyBcd, 0);
        // 解密密钥BCD码后8位
        byte[] keyBcdTwo = distributeBytes(keyBcd, 1);
        
        byte[] tempStr1 = decrypt(keySet, keyBcdOne);
        byte[] tempStr2 = encrypt(tempStr1, keyBcdTwo);
        byte[] tempStr3 = decrypt(tempStr2, keyBcdOne);
        
        return tempStr3;
    }
    
    /**
     * 加密
     * 
     * @param datasource byte[]
     * @param password String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, byte[] password)
    {
        try
        {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(datasource);
            DESKeySpec desKey = new DESKeySpec(password);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 解密
     * 
     * @param src byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] password)
    {
        byte[] a = {};
        Cipher cipher = null;
        try
        {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(src);
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // 真正开始解密操作
        try
        {
            a = cipher.doFinal(src);
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e1)
        {
            e1.printStackTrace();
        }
        return a;
    }
    
    /**
     * 把by按照8字节数组进行拆分
     */
    public static byte[] distributeBytes(byte[] by, int j)
    {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++)
        {
            b[i] = by[i + j * 8];
        }
        return b;
    }
    
    public static byte[] combineBytes(byte[] b1, byte[] b2)
    {
        int length = b1.length + b2.length;
        byte[] nbs = new byte[length];
        for (int i = 0; i < b1.length; i++)
        {
            nbs[i] = b1[i];
        }
        for (int i = 0; i < b2.length; i++)
        {
            nbs[b1.length + i] = b2[i];
        }
        return nbs;
    }
    
    /**
     * 将a与b进行按位异或
     */
    public byte[] xor(byte[] a, byte[] b, int j)
    {
        byte[] rb = new byte[a.length];
        for (int i = 0; i < a.length; i++)
        {
            rb[i] = (byte)(a[i] ^ b[j * 8 + i]);
        }
        return rb;
    }
    
}

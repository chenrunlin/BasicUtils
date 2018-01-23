package com.bsit.QR.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ImageWrite {
	private static final int BLACK = 0xFF000000;
	
    private static final int WHITE = 0xFFFFFFFF;
    
    private ImageWrite() {}
    
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth(); 
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
        for (int x = 0; x < width; x++) { 
            for (int y = 0; y < height; y++) { 
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE); 
            }   
        } 
        return image;
    }      
    
    public static void getQrcodeImg(String content, String path) {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter(); 
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();      
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 140, 140, hints);   
            File file = new File(path); 
            ImageWrite.writeToFile(bitMatrix, "png", file);
            System.out.println("二维码已生成！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        } 
    }
    
    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException { 
        BufferedImage image = toBufferedImage(matrix); 
        if (!ImageIO.write(image, format, file)) {    
            throw new IOException("Could not write an image of format " + format + " to " + file);  
        }  
    }  
    
    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)throws IOException {
        BufferedImage image = toBufferedImage(matrix); 
        if (!ImageIO.write(image, format, stream)) {      
            throw new IOException("Could not write an image of format " + format);  
        }   
    }  
    
    public static void main(String[] args) {
		getQrcodeImg("weixin://wxpay/bizpayurl?pr=KZ56rik", "E:\\test_zxing.png");
	}
}

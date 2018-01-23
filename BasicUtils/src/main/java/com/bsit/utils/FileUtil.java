package com.bsit.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;

public class FileUtil {
	
	//判断文件是否存在，不存在则创建
	public static void createFileIfNotExists(String filePath){
		File file = new File(filePath);
		if (!file.exists()) {
			try {    
	        	file.createNewFile(); 
		    } catch (IOException e) {    
		        e.printStackTrace();    
			}   
		}  
	}
	
	//判断文件目录是否存在，不存在则创建
	public static void createDirIfNotExists(String fileDir){
		File file = new File(fileDir);
		if(!file.exists() && !file.isDirectory()) {
			file.mkdirs();// 创建多级目录
		}
	}
	
	/**
	 * 从文件指定字节处读取1000byte的字节，返回
	 * @param fileFullPath
	 * @param position
	 * @return
	 */
	@SuppressWarnings("resource")
	public static byte[] readFile(String fileFullPath, int position) {
		byte[] bytes = new byte[1000];
		RandomAccessFile randomFile;
		try {
			randomFile = new RandomAccessFile(fileFullPath, "r");
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(position);
			int byteRead = 0;
			if ((byteRead = randomFile.read(bytes)) != -1) {
				byte[] bt = new byte[byteRead];
				System.arraycopy(bytes, 0, bt, 0, byteRead);
				return bt;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 写字节到文件，提前会创建文件
	 * @param bt
	 * @param fileName
	 * @return
	 */
    public static boolean writeFileData(byte[] bt, String fileName) {
		RandomAccessFile randomWriteFile = null;
		try {
			FileUtil.createFileIfNotExists(fileName);
			randomWriteFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomWriteFile.length();
			randomWriteFile.seek(fileLength);
			randomWriteFile.write(bt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				randomWriteFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
    /**
     * 将内容追加到文件尾部,一行一行写
     * @param fileName
     * @param content
     */
    public static void writeFileByLine(String fileName, String content){
    	try {
		    //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		    FileWriter writer = new FileWriter(fileName, true);
		    writer.write(content + "\n");
		    writer.close();
	    } catch (IOException e) {
		    e.printStackTrace();
	   }
	}
    
    /**
	 * @Description: 读取文件最后一行
	 * @param @param file
	 * @return String
	 * @author chenrl
	 * 2016年3月9日下午5:20:12
	 */
	public static String readLastLine(File file) throws IOException{
		if (!file.exists() || file.isDirectory() || !file.canRead()) {  
		    return null;  
		} 
		RandomAccessFile raf = null; 
		String charset = "UTF-8";
		try {
			raf = new RandomAccessFile(file, "r");
			long len = raf.length();  
			if (len == 0L) {  
				return "";  
		    } else {
		    	long pos = len - 1; 
		    	while (pos > 0) {  
		            pos--;  
		            raf.seek(pos);  
		            if (raf.readByte() == '\n') {  
		              break;  
		            }  
		          }  
		          if (pos == 0) {  
		            raf.seek(0);  
		          }  
		          byte[] bytes = new byte[(int) (len - pos)];  
		          raf.read(bytes);  
		          return new String(bytes, charset);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {  
				try {  
					raf.close();  
				} catch (Exception e2) { 
					e2.printStackTrace();
				}  
			}
		}
		return null;
	}
	
	/**
	 * @Description: 文件的行数
	 * @param sourceFile
	 * @return int
	 * @author chenrl
	 * 2016年3月10日上午10:58:10
	 */
	public static int getFileLineCount(File sourceFile) {
        int cnt = 0;
        FileReader in = null;
        LineNumberReader reader = null;
        try {
        	in = new FileReader(sourceFile);
            reader = new LineNumberReader(in);
            while ((reader.readLine()) != null) {
            }
            cnt = reader.getLineNumber();
        } catch (Exception ex) {
            cnt = -1;
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return cnt;
    }
}

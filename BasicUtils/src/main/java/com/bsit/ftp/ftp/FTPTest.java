package com.bsit.ftp.ftp;


public class FTPTest {
	
	public static void main(String[] args) {
		try {
	        FTPUtil.connect("139.129.6.204", 21, "test", "test");
	        
//	        System.out.println("===================列举目录及文件=====================");
//	        FTPFile[] ftpFiles = myFtp.GetDirAndFilesInfo(null);
//	        for (FTPFile ftpFile : ftpFiles) {
//	            System.out.println(ftpFile.getName());
//	        }
//	        
//	        System.out.println("===================列举文件=====================");
//	        String[] dirs = myFtp.GetFileNames(null);
//	        for (String dir : dirs) {
//	            System.out.println(dir);
//	        }
//	        
	        System.out.println("===================下载文件=====================");
	        DownloadStatus ds = FTPUtil.download("/wy/copy.zip", "f:\\copy_download.zip");
	        System.out.println(ds);
	        
	        System.out.println("===================上传文件=====================");            
	        System.out.println(FTPUtil.upload("f:\\ftp.txt", "/wy/ftp_abc.txt"));

	        FTPUtil.disconnect();
	    } catch (Exception e) {

	        System.out.println("连接FTP出错：" + e.getMessage());
	        e.printStackTrace();
	    }
	}
}

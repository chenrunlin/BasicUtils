package com.bsit.ftp.ftp;

/**
  * 下载状态枚举
  */
public enum DownloadStatus {
	
	 Remote_File_Noexist, // 远程文件不存在
     Download_New_Success, // 下载文件成功
     Download_New_Failed, // 下载文件失败
     Local_Bigger_Remote, // 本地文件大于远程文件
     Download_From_Break_Success, // 断点续传成功
     Download_From_Break_Failed; // 断点续传失败

}

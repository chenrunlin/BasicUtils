package com.bsit.netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.mail.MessagingException;

import com.bsit.netWork.http.HttpUtils;
import com.bsit.netWork.mail163.MailUtil;
import com.bsit.utils.DateUtil;
import com.bsit.utils.FileUtil;

public class Ping58 {

	public static void main(String[] args) {
		boolean res = false; // 结果
		String date = "";
		String fileName = "";
		String backStr = "";
		int i = 0;
		while(true){
			date = DateUtil.getCurrentDateTime();
			fileName = "e:\\logs/58.log/" + date + "_58_log.txt";
			try {
				backStr = HttpUtils.sendPost("http://58.56.141.202:8088/pos/", "");
				if(backStr.contains("Hello World!")){
					try {
						System.out.println(DateUtil.getCurrentDateTime() + " : 服务器正常");
						FileUtil.writeFileByLine(fileName, DateUtil.getCurrentDateTime() + " : 服务器正常" );
						Thread.sleep(1000 * 10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				System.out.println(DateUtil.getCurrentDateTime() + " : 发送请求：http://58.56.141.202:8081/pos/，响应为空。");
				FileUtil.writeFileByLine(fileName, DateUtil.getCurrentDateTime() + " : 发送请求：http://58.56.141.202:8081/pos/，响应为空。");
				res = ping(fileName);
				if (res) {
					System.out.println(DateUtil.getCurrentDateTime() + " : tomcat服务器网络异常，系统服务器正常。");
					FileUtil.writeFileByLine(fileName, DateUtil.getCurrentDateTime() + " : tomcat服务器网络异常，系统服务器正常。" );
				}
				if (!res) {
					i++;
				}
				long startTimeMillis = 0;
				long endTimeMillis = 0;
				if (i == 1) {
					startTimeMillis = System.currentTimeMillis();
				}
				if (i == 5) {
					endTimeMillis = System.currentTimeMillis();
					long interval = (endTimeMillis - startTimeMillis) / (1000 * 60);//时间间隔
					if (interval < 10) {
						System.out.println(DateUtil.getCurrentDateTime() + " : 服务器宕机了");
						FileUtil.writeFileByLine(fileName, DateUtil.getCurrentDateTime() + " : 服务器宕机了" );
						try {
							MailUtil.sendMail();
						} catch (MessagingException e1) {
							e1.printStackTrace();
						}
					}
					i= 0;
				}
			}
		}
	}

	public static boolean ping(String fileName) {
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		Process process = null; // 声明处理类对象
		String line = null; // 返回行信息
		InputStream is = null; // 输入流
		InputStreamReader isr = null; // 字节流
		BufferedReader br = null;
		String ip = "58.56.141.202";
		boolean res = false; // 结果
		FileUtil.createFileIfNotExists(fileName);
		try {
			process = runtime.exec("ping " + ip); // PING
			is = process.getInputStream(); // 实例化输入流
			isr = new InputStreamReader(is); // 把输入流转换成字节流
			br = new BufferedReader(isr); // 从字节中读取文本
			while ((line = br.readLine()) != null) {
				if (line.contains("TTL")) {
					res = true;
					break;
				}
			}
			is.close();
			isr.close();
			br.close();
			if (res) {
				System.out.println("ping 58.56.141.202 有响应");
				FileUtil.writeFileByLine(fileName, "ping 58.56.141.202 有响应");
			} else {
				System.out.println("ping 58.56.141.202 没有响应");
				FileUtil.writeFileByLine(fileName, "ping 58.56.141.202 没有响应");
			}
		} catch (IOException e) {
			System.out.println(e);
			runtime.exit(1);
		}
		return res;
	}

}

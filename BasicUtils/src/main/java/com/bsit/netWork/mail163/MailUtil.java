package com.bsit.netWork.mail163;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil {
	
	public static void main(String[] args) {
		try {
			sendMail();
		} catch (MessagingException e) {
			System.out.println("邮件发送失败");
			e.printStackTrace();
		}
	}

	public static void sendMail() throws MessagingException {
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		/*
		 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
		 * mail.user / mail.from
		 */
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.163.com");
		// 发件人的账号
		props.put("mail.user", "chenrunlin516@163.com");
		// 163邮箱设置客户端授权码，而不是登录163的密码
		props.put("mail.password", "crl900516");
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(
				props.getProperty("mail.user"));
		message.setFrom(form);
		// 设置收件人
		InternetAddress to = new InternetAddress("569062838@qq.com");
		message.setRecipient(RecipientType.TO, to);
		// 设置抄送
//		InternetAddress cc = new InternetAddress("18864830605@163.com");
//		message.setRecipient(RecipientType.CC, cc);
		// 设置密送，其他的收件人不能看到密送的邮件地址
//		InternetAddress bcc = new InternetAddress("18864830605@163.com");
//		message.setRecipient(RecipientType.CC, bcc);
		// 设置邮件标题
		message.setSubject("检测服务器");
		// 设置邮件的内容体
		message.setContent("<a href='http://58.56.141.202:8088/pos/'>下班了，回家吃饭。</a>",
				"text/html;charset=UTF-8");
		// 发送邮件
		Transport.send(message);
	}

}

package com.bsit.ftp.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class MySFTP {
	
	Session session = null;
    Channel channel = null;

	public ChannelSftp connect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			session = jsch.getSession(username, host, port);
			System.out.println("SFTP Session created.");
			session.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			session.setConfig(sshConfig);
			session.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftp;
	}

	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			// sftp.mkdir(directory);
			sftp.cd(directory);
			File file = new File(uploadFile);
			InputStream is = new FileInputStream(file);
			sftp.put(is, file.getName());
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void download(String directory, String downloadFile,
			String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream os = new FileOutputStream(file);
			sftp.get(downloadFile, os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public Vector listFiles(String directory, ChannelSftp sftp) {
		try {
			return sftp.ls(directory);
		} catch (Exception e) {
			System.out.println("文件或目录不存在--" + directory);
		}
		return new Vector();
	}

	@Autowired
	public void createDir(String createpath, ChannelSftp sftp)
			throws SystemException {
		try {
			if (isDirExist(createpath, sftp)) {
				sftp.cd(createpath);
			}
			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString(), sftp)) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}
			}
			sftp.cd(createpath);
		} catch (SftpException e) {
			throw new SystemException("创建路径错误：" + createpath);
		}
	}

	/**
	 * 判断目录是否存在
	 */
	public boolean isDirExist(String directory, ChannelSftp sftp) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			isDirExistFlag = false;
		}
		return isDirExistFlag;
	}
	
	public void disconnect(ChannelSftp sftp) {
        if(sftp != null){
            if(sftp.isConnected()){
                try {
					if (null != sftp.getSession()) { 
					    sftp.getSession().disconnect(); 
					}
				} catch (JSchException e) {
					e.printStackTrace();
				} 
                sftp.disconnect();
            }else if(sftp.isClosed()){
                System.out.println("sftp is closed already");
            }
        }
    }
	
	public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

	public static void main(String[] args) {
		MySFTP sf = new MySFTP();
		String host = "192.168.0.1";
		int port = 22;
		String username = "root";
		String password = "root";
		String directory = "/home/httpd/test/";
		String uploadFile = "D:\\tmp\\upload.txt";
		String downloadFile = "upload.txt";
		String saveFile = "D:\\tmp\\download.txt";
		String deleteFile = "delete.txt";
		ChannelSftp sftp = sf.connect(host, port, username, password);
		sf.upload(directory, uploadFile, sftp);
		sf.download(directory, downloadFile, saveFile, sftp);
		sf.delete(directory, deleteFile, sftp);
		try {
			sftp.cd(directory);

			System.out.println("finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package com.bsit.ftp.sftp.type1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class MySFTP {
	
	Session session = null;
    Channel channel = null;
    ChannelSftp sftp = null;

	public ChannelSftp connect(String host, int port, String username,
			String password) {
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

		}
		return sftp;
	}
	
	public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
	
	/** 
     * 进入指定的目录并设置为当前目录 
     * @param sftpPath    "/dcds/abc/" ,将abc目录设置成当前目录 
     * @throws Exception 
     */  
    public void cd (String sftpPath) throws SftpException {  
    	sftp.cd(sftpPath);  
    } 
    
    /** 
     * 得到当前用户当前工作目录地址 
     * @return 返回当前工作目录地址 
     * @throws SftpException  
     *  
     */  
    public String pwd () throws SftpException {  
        return sftp.pwd();  
    }

	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			// 
			if(!isDirExist(directory, sftp)){
				sftp.mkdir(directory);
			}
			cd(directory);
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
			cd(directory);
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
			cd(directory);
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

	public void createDir(String createpath, ChannelSftp sftp){
		try {
			if (isDirExist(createpath, sftp)) {
				cd(createpath);
			}
			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString(), sftp)) {
					cd(filePath.toString());
				} else {
					// 建立目录
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					cd(filePath.toString());
				}
			}
			cd(createpath);
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 获取远程文件的流文件 
     * @param sftpFilePath 
     * @return 
     * @throws SftpException 
     */  
    public InputStream getFile (String sftpFilePath) throws SftpException {  
        if (isFileExist(sftpFilePath)) {  
            return sftp.get(sftpFilePath);  
        }  
        return null;  
    }  
    
    /** 
     * 判断远程文件是否存在 
     * @param srcSftpFilePath 
     * @return 
     * @throws SftpException 
     */  
    public boolean isFileExist (String srcSftpFilePath) throws SftpException {  
        boolean isExitFlag = false;  
        // 文件大于等于0则存在文件  
        if (getFileSize(srcSftpFilePath) >= 0) {  
            isExitFlag = true;  
        }  
        return isExitFlag;  
    }
    
    
    /** 得到远程文件大小 
     * @see   返回文件大小 
     * @param srcSftpFilePath 
     * @return 返回文件大小，如返回-2 文件不存在，-1文件读取异常 
     * @throws SftpException 
     */  
    public long getFileSize (String srcSftpFilePath) throws SftpException {  
        long filesize = 0;//文件大于等于0则存在  
        try {  
            SftpATTRS sftpATTRS = sftp.lstat(srcSftpFilePath);  
            filesize = sftpATTRS.getSize();  
        } catch (Exception e) {  
            filesize = -1;//获取文件大小异常  
            if (e.getMessage().toLowerCase().equals("no such file")) {  
                filesize = -2;//文件不存在  
            }  
        }  
        return filesize;  
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
                sftp.disconnect();
            }else if(sftp.isClosed()){
                System.out.println("sftp is closed already");
            }
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
			sf.cd(directory);

			System.out.println("finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
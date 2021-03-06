package com.bsit.splitFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TEXT 文件分割，该分割方式按行分割,
 * 分割TEXT文本内容，一个200行的文本文件分割成5个子文件，每个子文件组合起来便是整个文件
 * 文件合并时，空行删除
 * 
 * @description
 * @DATE 2012-1-8下午01:36:39
 */
public class PartitionTextFile extends PartitionFile {

	@SuppressWarnings("finally")
	@Override
	public long getFileLength(File file) throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		long fileSize = 0;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				fileSize += line.length();
				line = br.readLine();
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (br != null)
				br.close();
			if (fr != null)
				fr.close();
			return fileSize;
		}
	}

	/**
	 * 分割Byte文件
	 */
	@Override
	public String[] partitionFile(File srcFile, int partitionFileNum)
			throws IOException {
		if (partitionFileNum <= 0)
			return null;
		FileReader fr = null;
		BufferedReader br = null;
		long readNum = 0;
		String[] partitions = new String[partitionFileNum];
		try {
			fr = new FileReader(srcFile);
			br = new BufferedReader(fr);
			int i = 0;
			while (partitionFileNum > i) {
				String name = null;
				if (srcFile.getName().indexOf(".") != -1) {
					name = srcFile.getName().substring(0,
							srcFile.getName().indexOf("."));
				} else {
					name = srcFile.getName();
				}
				partitions[i] = srcFile.getParent() + "/" + name + "_" + i;
				File wfile = new File(partitions[i]);
				if (!wfile.exists()) {
					wfile.getParentFile().mkdirs();
					wfile.createNewFile();
				}
				FileWriter fw = new FileWriter(wfile, false);
				BufferedWriter bw = new BufferedWriter(fw);
				String line = br.readLine();
				int flush = 0;
				while (line != null) {
					if (line.trim().length() == 0) {
						line = br.readLine();
						continue;
					}
					readNum += line.length();
					if (i + 1 == partitionFileNum) {
						bw.write(line);
						bw.newLine();
					} else {
						if (readNum >= MAX_BYTE) {
							bw.write(line);
							bw.newLine();
							break;
						} else {
							bw.write(line);
							bw.newLine();
						}
					}
					line = br.readLine();
					if (flush % 1000 == 0) {
						bw.flush();
					}
				}
				bw.flush();
				fw.flush();
				bw.close();
				fw.close();
				readNum = 0;
				i++;
			}
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException e) {
			} finally {
				br = null;
				fr = null;
			}
		}
		return partitions;
	}

	/**
	 * 合并文件
	 */
	@Override
	public void uniteFile(String[] files, String newFile) throws IOException {
		File wFile = new File(newFile);
		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		try {
			writer = new FileWriter(wFile, false);
			bufferedWriter = new BufferedWriter(writer);
			for (int i = 0; i < files.length; i++) {
				File rFile = new File(files[i]);
				FileReader reader = new FileReader(rFile);
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = bufferedReader.readLine();
				while (line != null) {
					if (line.trim().length() == 0) {
						line = bufferedReader.readLine();
						continue;
					}
					bufferedWriter.write(line);
					bufferedWriter.newLine();
					line = bufferedReader.readLine();
				}
				bufferedWriter.flush();
				writer.flush();
			}
		} finally {
			if (bufferedWriter != null)
				bufferedWriter.close();
			bufferedWriter = null;
			if (writer != null)
				writer.close();
			writer = null;
		}
	}
}

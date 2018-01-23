package com.test.bus_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 枣庄  获取数据
 * 内容: 上传消费数据
 * @author Administrator
 *	
 */
public class SocketClient{

	private final static Logger log = Logger.getLogger(SocketClient.class);

	private static final SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

	private static Socket client = null;

	private static LoginResponse response = new LoginResponse();

	private String ip;

	private Integer port;

	private byte[] JSDY;

	private byte[] PWD;

	public static void main(String[] args) {
		SocketClient client = new SocketClient("192.168.1.194", 4700, "00000001", "8DDCFF3A80F4189CA1C9D4D902C3C909");
		client.getConnection();
		/*client.sendBlack("0000000");
		client.sendWhite("0000000");
		client.close(); //手动关闭
*/	}

	public SocketClient(String ip, Integer port, String jSDY, String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.JSDY = BaseUtil.hex2bytes(jSDY);
		this.PWD = BaseUtil.hex2bytes(password);
		try {
			client = new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传消费信息
	 * @param list
	 * @return  map
	 *          key: list中的id
	 *          value:返回状态 (0:上传成功 1:重复数据  2: 记录解析错误,数据异常)
	 */
/*	public Map<String, Object> getPayOff(List<TConsume> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (PubMethod.isEmpty(list)) {
			return map;
		}
		if (!isConnection()) {
			log.debug("连接不可用,尝试重新链接");
			if (!getConnection()) {
				return map;
			}
		}

		for (TConsume consume : list) {
			ByteBuffer buffer = ByteBuffer.allocate(85);
			buffer.put(response.getConnectCode());
			buffer.put((byte) 1);// 数据数目  1
			buffer.put(JSDY);
			buffer.put(BaseUtil.hex2bytes(BaseUtil.subString(consume.getIpsamcode(), 12)));//终端机编号
			buffer.put(BaseUtil.hex2bytes(consume.getIlinecode() + ""));//线路编号
			buffer.put(BaseUtil.hex2bytes("000001"));//司机
			buffer.put(BaseUtil.hex2bytes(BaseUtil.subString(consume.getCbuscode(), 12)));//车辆编号
			buffer.put(new byte[4]);//班次号
			buffer.put(BaseUtil.hex2bytes((consume.getCissuercode())));//发卡机构
			buffer.put(BaseUtil.hex2bytes(consume.getCcitycode()));//城市代码
			buffer.put(BaseUtil.hex2bytes(("0" + consume.getCappcode())));//卡号
			buffer.put(BaseUtil.hex2bytes(consume.getIcardtype()));//卡类型
			buffer.put(BaseUtil.hex2bytes(BaseUtil.Integer2HexString(consume.getIcardsn(), 4)));//卡交易序号
			buffer.put(BaseUtil.hex2bytes(BaseUtil.Integer2HexString(consume.getIcost(), 8)));//交易金额
			buffer.put(BaseUtil.hex2bytes(consume.getItradetype()));//交易类型
			buffer.put(BaseUtil.hex2bytes(BaseUtil.Integer2HexString(Integer.valueOf(consume.getIpsamsn()), 8)));//终端交易序号
			buffer.put(BaseUtil.hex2bytes(sf.format(consume.getDttrade())));//交易时间
			buffer.put(BaseUtil.hex2bytes(consume.getItaccode()));//TAC
			buffer.put(BaseUtil.hex2bytes(BaseUtil.Integer2HexString(consume.getImoney(), 8)));
			buffer.put(BaseUtil.hex2bytes(BaseUtil.Integer2HexString(consume.getImoney() + consume.getIcost(), 8)));
			buffer.put(BaseUtil.hex2bytes(consume.getIrandom()));//随机数
			byte[] data = getData(BaseUtil.hex2bytes(MessageTypeEnum.A042.toString()), buffer.array());
			log.info("payOff send:" + BaseUtil.Bytes2HexStringBigFirst(data));
			byte[] receiveDate = sendMessage(data);
			switch (receiveDate[1]) {
			case (byte) 0xF0:
				map.put(consume.getId(), 0);
				break;
			case (byte) 0xF1:
				map.put(consume.getId(), 1);
				break;
			case (byte) 0xF2:
				map.put(consume.getId(), 2);
				break;
			}

			log.info("payOff receive:" + BaseUtil.Bytes2HexStringBigFirst(receiveDate));

		}
		return map;
	}*/

	/**
	 * 登录
	 * @return
	 */
	private boolean getConnection() {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(20);
			buffer.put(JSDY);
			buffer.put(PWD);
			byte[] data = getData(BaseUtil.hex2bytes(MessageTypeEnum.B002.toString()), buffer.array());
			log.info("Login send:" + BaseUtil.Bytes2HexStringBigFirst(data));
			byte[] b = sendMessage(data);
			log.info("Login receive:" + BaseUtil.Bytes2HexStringBigFirst(b));
			if (b.length != 2) {
				byte[] up = new byte[7];
				System.arraycopy(b, 0, up, 0, 7);
				response.setDateTime(up);
				byte[] Black = new byte[4];
				System.arraycopy(b, 7, Black, 0, 4);
				response.setBlack_version(Black);
				System.arraycopy(b, 11, Black, 0, 4);
				response.setConnectCode(Black);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



	private static byte[] getData(byte[] mit, byte[] senddata) {
		int length = senddata.length;
		int count = 0;
		for (int i = 0; i < senddata.length; i++) {
			if (senddata[i] == 0x7e || senddata[i] == 0x7f) {
				count++;
			}
		}
		byte[] extData;
		if (count > 0) {
			extData = new byte[senddata.length + count];
			for (int i = 0; i < extData.length; i++) {
				if (senddata[i] == 0x7e || senddata[i] == 0x7f) {
					extData[i] = (byte) 0x7e;
					extData[i] = (byte) (senddata[i] ^ 0x20);
				} else {
					extData[i] = senddata[i];
				}
			}
		} else {
			extData = senddata;
		}
		// FTI 1  MTI 2 DBL  4 RTI 1  SI  2  LEN  2 SW 1  CRC    4  RESERVE  1 DATA
		ByteBuffer buffer = ByteBuffer.allocate(19 + extData.length);
		buffer.put(Content.TM);
		buffer.put(Content.FTI);
		buffer.put(mit);
		buffer.put(Content.DBL);
		buffer.put(Content.RTI);
		buffer.put(Content.SI);
		buffer.put(new byte[] { (byte) (length >> 8), (byte) (length & 0xFF) });
		buffer.put(Content.SW);
		buffer.put(Content.CRC);
		buffer.put(Content.RESERVE);
		buffer.put(extData);
		buffer.put(Content.TM);
		return buffer.array();
	}

	private static byte[] analysisReceive(byte[] receive) {
		byte[] result = null;
		int index = 0;
		for (int i = 1; i < receive.length - 1; i++) {
			if (receive[i] == 0x7E) {
				index++;
			}
		}
		log.debug("receive 返回7E个数:" + index);
		ByteBuffer byteBuffer = ByteBuffer.allocate(receive.length - index - 2);
		if (index > 0) {
			for (int i = 1; i < receive.length - 1; i++) {
				if (receive[i] == 0x7E) {
					byteBuffer.put((byte) (receive[i + 1] ^ 0x20));
					i++;
				} else {
					byteBuffer.put(receive[i]);
				}
			}
		} else {
			byteBuffer.put(receive, 1, receive.length - 2);
		}
		byte[] data = byteBuffer.array();
		log.info("返回:" + BaseUtil.Bytes2HexStringBigFirst(data));
		if (Arrays.equals(new byte[] { data[data.length - 2], data[data.length - 1] }, new byte[] { (byte) 0xE0, 00 })) {
			int len = ((data[9] << 8) + data[10]);
			result = new byte[len - 2]; //去除结果集  E000
			System.arraycopy(data, data.length - len, result, 0, result.length);
		} else {
			result = new byte[] { (byte) 0x00, (byte) 0x00 };
		}
		return result;

	}

	private static byte[] receiveMessage(InputStream in) throws IOException {
		byte[] cash = new byte[1024];
		byte[] result = null;
		int index = 1;
		int length = 0;
		while ((length = in.read(cash)) != -1) {
			if (length == 1024) {
				index++;
//				result = copyOf(cash, index * 1024);
			} else {
				System.arraycopy(cash, 0, result, 0, length);
				break;
			}
		}
		return analysisReceive(result);

	}

	private static byte[] sendMessage(byte[] sendByte) {
		try {
			//向服务器端发送请求，服务器IP地址和服务器监听的端口号  
			OutputStream out = client.getOutputStream();
			out.write(sendByte);
			out.flush();
			//发送消息  
			return receiveMessage(client.getInputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
